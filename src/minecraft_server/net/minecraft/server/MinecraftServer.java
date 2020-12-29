package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.src.AnvilSaveConverter;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CallableIsServerModded;
import net.minecraft.src.CallableServerMemoryStats;
import net.minecraft.src.CallableServerProfiler;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.CommandBase;
import net.minecraft.src.ConvertingProgressUpdate;
import net.minecraft.src.CrashReport;
import net.minecraft.src.DedicatedServer;
import net.minecraft.src.DemoWorldServer;
import net.minecraft.src.DispenserBehaviors;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.ICommandManager;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.IPlayerUsage;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.ISaveFormat;
import net.minecraft.src.ISaveHandler;
import net.minecraft.src.IUpdatePlayerListBox;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MinecraftException;
import net.minecraft.src.NetworkListenThread;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet4UpdateTime;
import net.minecraft.src.PlayerUsageSnooper;
import net.minecraft.src.Profiler;
import net.minecraft.src.RConConsoleSource;
import net.minecraft.src.ReportedException;
import net.minecraft.src.ServerCommandManager;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.StatList;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.StringUtils;
import net.minecraft.src.ThreadDedicatedServer;
import net.minecraft.src.ThreadMinecraftServer;
import net.minecraft.src.World;
import net.minecraft.src.WorldInfo;
import net.minecraft.src.WorldManager;
import net.minecraft.src.WorldServer;
import net.minecraft.src.WorldServerMulti;
import net.minecraft.src.WorldSettings;
import net.minecraft.src.WorldType;

public abstract class MinecraftServer implements ICommandSender, Runnable, IPlayerUsage
{
    /** Instance of Minecraft Server. */
    private static MinecraftServer mcServer = null;
    private final ISaveFormat anvilConverterForAnvilFile;

    /** The PlayerUsageSnooper instance. */
    private final PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("server", this);
    private final File anvilFile;

    /** List of names of players who are online. */
    private final List playersOnline = new ArrayList();
    private final ICommandManager commandManager;
    public final Profiler theProfiler = new Profiler();

    /** The server's hostname. */
    private String hostname;

    /** The server's port. */
    private int serverPort = -1;

    /** The server world instances. */
    public WorldServer[] worldServers;

    /** The ServerConfigurationManager instance. */
    private ServerConfigurationManager serverConfigManager;

    /**
     * Indicates whether the server is running or not. Set to false to initiate a shutdown.
     */
    private boolean serverRunning = true;

    /** Indicates to other classes that the server is safely stopped. */
    private boolean serverStopped = false;

    /** Incremented every tick. */
    private int tickCounter = 0;

    /**
     * The task the server is currently working on(and will output on outputPercentRemaining).
     */
    public String currentTask;

    /** The percentage of the current task finished so far. */
    public int percentDone;

    /** True if the server is in online mode. */
    private boolean onlineMode;

    /** True if the server has animals turned on. */
    private boolean canSpawnAnimals;
    private boolean canSpawnNPCs;

    /** Indicates whether PvP is active on the server or not. */
    private boolean pvpEnabled;

    /** Determines if flight is allowed or not. */
    private boolean allowFlight;

    /** The server MOTD string. */
    private String motd;

    /** Maximum build height. */
    private int buildLimit;
    private long lastSentPacketID;
    private long lastSentPacketSize;
    private long lastReceivedID;
    private long lastReceivedSize;
    public final long[] sentPacketCountArray = new long[100];
    public final long[] sentPacketSizeArray = new long[100];
    public final long[] receivedPacketCountArray = new long[100];
    public final long[] receivedPacketSizeArray = new long[100];
    public final long[] tickTimeArray = new long[100];

    /** Stats are [dimension][tick%100] system.nanoTime is stored. */
    public long[][] timeOfLastDimensionTick;
    private KeyPair serverKeyPair;

    /** Username of the server owner (for integrated servers) */
    private String serverOwner;
    private String folderName;
    private boolean isDemo;
    private boolean enableBonusChest;

    /**
     * If true, there is no need to save chunks or stop the server, because that is already being done.
     */
    private boolean worldIsBeingDeleted;
    private String texturePack = "";
    private boolean serverIsRunning = false;

    /**
     * Set when warned for "Can't keep up", which triggers again after 15 seconds.
     */
    private long timeOfLastWarning;
    private String userMessage;
    private boolean startProfiling;
    private boolean field_104057_T = false;

    public MinecraftServer(File par1File)
    {
        mcServer = this;
        this.anvilFile = par1File;
        this.commandManager = new ServerCommandManager();
        this.anvilConverterForAnvilFile = new AnvilSaveConverter(par1File);
        this.registerDispenseBehaviors();
    }

    /**
     * Register all dispense behaviors.
     */
    private void registerDispenseBehaviors()
    {
        DispenserBehaviors.func_96467_a();
    }

    /**
     * Initialises the server and starts it.
     */
    protected abstract boolean startServer() throws IOException;

    protected void convertMapIfNeeded(String par1Str)
    {
        if (this.getActiveAnvilConverter().isOldMapFormat(par1Str))
        {
            this.getLogAgent().func_98233_a("Converting map!");
            this.setUserMessage("menu.convertingLevel");
            this.getActiveAnvilConverter().convertMapFormat(par1Str, new ConvertingProgressUpdate(this));
        }
    }

    /**
     * Typically "menu.convertingLevel", "menu.loadingLevel" or others.
     */
    protected synchronized void setUserMessage(String par1Str)
    {
        this.userMessage = par1Str;
    }

    protected void loadAllWorlds(String par1Str, String par2Str, long par3, WorldType par5WorldType, String par6Str)
    {
        this.convertMapIfNeeded(par1Str);
        this.setUserMessage("menu.loadingLevel");
        this.worldServers = new WorldServer[3];
        this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
        ISaveHandler var7 = this.anvilConverterForAnvilFile.getSaveLoader(par1Str, true);
        WorldInfo var9 = var7.loadWorldInfo();
        WorldSettings var8;

        if (var9 == null)
        {
            var8 = new WorldSettings(par3, this.getGameType(), this.canStructuresSpawn(), this.isHardcore(), par5WorldType);
            var8.func_82750_a(par6Str);
        }
        else
        {
            var8 = new WorldSettings(var9);
        }

        if (this.enableBonusChest)
        {
            var8.enableBonusChest();
        }

        for (int var10 = 0; var10 < this.worldServers.length; ++var10)
        {
            byte var11 = 0;

            if (var10 == 1)
            {
                var11 = -1;
            }

            if (var10 == 2)
            {
                var11 = 1;
            }

            if (var10 == 0)
            {
                if (this.isDemo())
                {
                    this.worldServers[var10] = new DemoWorldServer(this, var7, par2Str, var11, this.theProfiler, this.getLogAgent());
                }
                else
                {
                    this.worldServers[var10] = new WorldServer(this, var7, par2Str, var11, var8, this.theProfiler, this.getLogAgent());
                }
            }
            else
            {
                this.worldServers[var10] = new WorldServerMulti(this, var7, par2Str, var11, var8, this.worldServers[0], this.theProfiler, this.getLogAgent());
            }

            this.worldServers[var10].addWorldAccess(new WorldManager(this, this.worldServers[var10]));

            if (!this.isSinglePlayer())
            {
                this.worldServers[var10].getWorldInfo().setGameType(this.getGameType());
            }

            this.serverConfigManager.setPlayerManager(this.worldServers);
        }

        this.setDifficultyForAllWorlds(this.getDifficulty());
        this.initialWorldChunkLoad();
    }

    protected void initialWorldChunkLoad()
    {
        int var5 = 0;
        this.setUserMessage("menu.generatingTerrain");
        byte var6 = 0;
        this.getLogAgent().func_98233_a("Preparing start region for level " + var6);
        WorldServer var7 = this.worldServers[var6];
        ChunkCoordinates var8 = var7.getSpawnPoint();
        long var9 = System.currentTimeMillis();

        for (int var11 = -192; var11 <= 192 && this.isServerRunning(); var11 += 16)
        {
            for (int var12 = -192; var12 <= 192 && this.isServerRunning(); var12 += 16)
            {
                long var13 = System.currentTimeMillis();

                if (var13 - var9 > 1000L)
                {
                    this.outputPercentRemaining("Preparing spawn area", var5 * 100 / 625);
                    var9 = var13;
                }

                ++var5;
                var7.theChunkProviderServer.loadChunk(var8.posX + var11 >> 4, var8.posZ + var12 >> 4);
            }
        }

        this.clearCurrentTask();
    }

    public abstract boolean canStructuresSpawn();

    public abstract EnumGameType getGameType();

    /**
     * Defaults to "1" (Easy) for the dedicated server, defaults to "2" (Normal) on the client.
     */
    public abstract int getDifficulty();

    /**
     * Defaults to false.
     */
    public abstract boolean isHardcore();

    /**
     * Used to display a percent remaining given text and the percentage.
     */
    protected void outputPercentRemaining(String par1Str, int par2)
    {
        this.currentTask = par1Str;
        this.percentDone = par2;
        this.getLogAgent().func_98233_a(par1Str + ": " + par2 + "%");
    }

    /**
     * Set current task to null and set its percentage to 0.
     */
    protected void clearCurrentTask()
    {
        this.currentTask = null;
        this.percentDone = 0;
    }

    /**
     * par1 indicates if a log message should be output.
     */
    protected void saveAllWorlds(boolean par1)
    {
        if (!this.worldIsBeingDeleted)
        {
            WorldServer[] var2 = this.worldServers;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4)
            {
                WorldServer var5 = var2[var4];

                if (var5 != null)
                {
                    if (!par1)
                    {
                        this.getLogAgent().func_98233_a("Saving chunks for level \'" + var5.getWorldInfo().getWorldName() + "\'/" + var5.provider.getDimensionName());
                    }

                    try
                    {
                        var5.saveAllChunks(true, (IProgressUpdate)null);
                    }
                    catch (MinecraftException var7)
                    {
                        this.getLogAgent().func_98236_b(var7.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Saves all necessary data as preparation for stopping the server.
     */
    public void stopServer()
    {
        if (!this.worldIsBeingDeleted)
        {
            this.getLogAgent().func_98233_a("Stopping server");

            if (this.getNetworkThread() != null)
            {
                this.getNetworkThread().stopListening();
            }

            if (this.serverConfigManager != null)
            {
                this.getLogAgent().func_98233_a("Saving players");
                this.serverConfigManager.saveAllPlayerData();
                this.serverConfigManager.removeAllPlayers();
            }

            this.getLogAgent().func_98233_a("Saving worlds");
            this.saveAllWorlds(false);

            for (int var1 = 0; var1 < this.worldServers.length; ++var1)
            {
                WorldServer var2 = this.worldServers[var1];
                var2.flush();
            }

            if (this.usageSnooper != null && this.usageSnooper.isSnooperRunning())
            {
                this.usageSnooper.stopSnooper();
            }
        }
    }

    /**
     * "getHostname" is already taken, but both return the hostname.
     */
    public String getServerHostname()
    {
        return this.hostname;
    }

    public void setHostname(String par1Str)
    {
        this.hostname = par1Str;
    }

    public boolean isServerRunning()
    {
        return this.serverRunning;
    }

    /**
     * Sets the serverRunning variable to false, in order to get the server to shut down.
     */
    public void initiateShutdown()
    {
        this.serverRunning = false;
    }

    public void run()
    {
        try
        {
            if (this.startServer())
            {
                long var1 = System.currentTimeMillis();

                for (long var50 = 0L; this.serverRunning; this.serverIsRunning = true)
                {
                    long var5 = System.currentTimeMillis();
                    long var7 = var5 - var1;

                    if (var7 > 2000L && var1 - this.timeOfLastWarning >= 15000L)
                    {
                        this.getLogAgent().func_98236_b("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        var7 = 2000L;
                        this.timeOfLastWarning = var1;
                    }

                    if (var7 < 0L)
                    {
                        this.getLogAgent().func_98236_b("Time ran backwards! Did the system time change?");
                        var7 = 0L;
                    }

                    var50 += var7;
                    var1 = var5;

                    if (this.worldServers[0].areAllPlayersAsleep())
                    {
                        this.tick();
                        var50 = 0L;
                    }
                    else
                    {
                        while (var50 > 50L)
                        {
                            var50 -= 50L;
                            this.tick();
                        }
                    }

                    Thread.sleep(1L);
                }
            }
            else
            {
                this.finalTick((CrashReport)null);
            }
        }
        catch (Throwable var48)
        {
            var48.printStackTrace();
            this.getLogAgent().logSevereException("Encountered an unexpected exception " + var48.getClass().getSimpleName(), var48);
            CrashReport var2 = null;

            if (var48 instanceof ReportedException)
            {
                var2 = this.addServerInfoToCrashReport(((ReportedException)var48).getCrashReport());
            }
            else
            {
                var2 = this.addServerInfoToCrashReport(new CrashReport("Exception in server tick loop", var48));
            }

            File var3 = new File(new File(this.getDataDirectory(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (var2.saveToFile(var3, this.getLogAgent()))
            {
                this.getLogAgent().logSevere("This crash report has been saved to: " + var3.getAbsolutePath());
            }
            else
            {
                this.getLogAgent().logSevere("We were unable to save this crash report to disk.");
            }

            this.finalTick(var2);
        }
        finally
        {
            try
            {
                this.stopServer();
                this.serverStopped = true;
            }
            catch (Throwable var46)
            {
                var46.printStackTrace();
            }
            finally
            {
                this.systemExitNow();
            }
        }
    }

    protected File getDataDirectory()
    {
        return new File(".");
    }

    /**
     * Called on exit from the main run() loop.
     */
    protected void finalTick(CrashReport par1CrashReport) {}

    /**
     * Directly calls System.exit(0), instantly killing the program.
     */
    protected void systemExitNow() {}

    /**
     * Main function called by run() every loop.
     */
    protected void tick()
    {
        long var1 = System.nanoTime();
        AxisAlignedBB.getAABBPool().cleanPool();
        ++this.tickCounter;

        if (this.startProfiling)
        {
            this.startProfiling = false;
            this.theProfiler.profilingEnabled = true;
            this.theProfiler.clearProfiling();
        }

        this.theProfiler.startSection("root");
        this.updateTimeLightAndEntities();

        if (this.tickCounter % 900 == 0)
        {
            this.theProfiler.startSection("save");
            this.serverConfigManager.saveAllPlayerData();
            this.saveAllWorlds(true);
            this.theProfiler.endSection();
        }

        this.theProfiler.startSection("tallying");
        this.tickTimeArray[this.tickCounter % 100] = System.nanoTime() - var1;
        this.sentPacketCountArray[this.tickCounter % 100] = Packet.sentID - this.lastSentPacketID;
        this.lastSentPacketID = Packet.sentID;
        this.sentPacketSizeArray[this.tickCounter % 100] = Packet.sentSize - this.lastSentPacketSize;
        this.lastSentPacketSize = Packet.sentSize;
        this.receivedPacketCountArray[this.tickCounter % 100] = Packet.receivedID - this.lastReceivedID;
        this.lastReceivedID = Packet.receivedID;
        this.receivedPacketSizeArray[this.tickCounter % 100] = Packet.receivedSize - this.lastReceivedSize;
        this.lastReceivedSize = Packet.receivedSize;
        this.theProfiler.endSection();
        this.theProfiler.startSection("snooper");

        if (!this.usageSnooper.isSnooperRunning() && this.tickCounter > 100)
        {
            this.usageSnooper.startSnooper();
        }

        if (this.tickCounter % 6000 == 0)
        {
            this.usageSnooper.addMemoryStatsToSnooper();
        }

        this.theProfiler.endSection();
        this.theProfiler.endSection();
    }

    public void updateTimeLightAndEntities()
    {
        this.theProfiler.startSection("levels");
        int var1;

        for (var1 = 0; var1 < this.worldServers.length; ++var1)
        {
            long var2 = System.nanoTime();

            if (var1 == 0 || this.getAllowNether())
            {
                WorldServer var4 = this.worldServers[var1];
                this.theProfiler.startSection(var4.getWorldInfo().getWorldName());
                this.theProfiler.startSection("pools");
                var4.getWorldVec3Pool().clear();
                this.theProfiler.endSection();

                if (this.tickCounter % 20 == 0)
                {
                    this.theProfiler.startSection("timeSync");
                    this.serverConfigManager.sendPacketToAllPlayersInDimension(new Packet4UpdateTime(var4.getTotalWorldTime(), var4.getWorldTime()), var4.provider.dimensionId);
                    this.theProfiler.endSection();
                }

                this.theProfiler.startSection("tick");
                CrashReport var6;

                try
                {
                    var4.tick();
                }
                catch (Throwable var8)
                {
                    var6 = CrashReport.makeCrashReport(var8, "Exception ticking world");
                    var4.addWorldInfoToCrashReport(var6);
                    throw new ReportedException(var6);
                }

                try
                {
                    var4.updateEntities();
                }
                catch (Throwable var7)
                {
                    var6 = CrashReport.makeCrashReport(var7, "Exception ticking world entities");
                    var4.addWorldInfoToCrashReport(var6);
                    throw new ReportedException(var6);
                }

                this.theProfiler.endSection();
                this.theProfiler.startSection("tracker");
                var4.getEntityTracker().updateTrackedEntities();
                this.theProfiler.endSection();
                this.theProfiler.endSection();
            }

            this.timeOfLastDimensionTick[var1][this.tickCounter % 100] = System.nanoTime() - var2;
        }

        this.theProfiler.endStartSection("connection");
        this.getNetworkThread().handleNetworkListenThread();
        this.theProfiler.endStartSection("players");
        this.serverConfigManager.onTick();
        this.theProfiler.endStartSection("tickables");

        for (var1 = 0; var1 < this.playersOnline.size(); ++var1)
        {
            ((IUpdatePlayerListBox)this.playersOnline.get(var1)).update();
        }

        this.theProfiler.endSection();
    }

    public boolean getAllowNether()
    {
        return true;
    }

    public void func_82010_a(IUpdatePlayerListBox par1IUpdatePlayerListBox)
    {
        this.playersOnline.add(par1IUpdatePlayerListBox);
    }

    public static void main(String[] par0ArrayOfStr)
    {
        StatList.nopInit();
        ILogAgent var1 = null;

        try
        {
            boolean var2 = !GraphicsEnvironment.isHeadless();
            String var3 = null;
            String var4 = ".";
            String var5 = null;
            boolean var6 = false;
            boolean var7 = false;
            int var8 = -1;

            for (int var9 = 0; var9 < par0ArrayOfStr.length; ++var9)
            {
                String var10 = par0ArrayOfStr[var9];
                String var11 = var9 == par0ArrayOfStr.length - 1 ? null : par0ArrayOfStr[var9 + 1];
                boolean var12 = false;

                if (!var10.equals("nogui") && !var10.equals("--nogui"))
                {
                    if (var10.equals("--port") && var11 != null)
                    {
                        var12 = true;

                        try
                        {
                            var8 = Integer.parseInt(var11);
                        }
                        catch (NumberFormatException var14)
                        {
                            ;
                        }
                    }
                    else if (var10.equals("--singleplayer") && var11 != null)
                    {
                        var12 = true;
                        var3 = var11;
                    }
                    else if (var10.equals("--universe") && var11 != null)
                    {
                        var12 = true;
                        var4 = var11;
                    }
                    else if (var10.equals("--world") && var11 != null)
                    {
                        var12 = true;
                        var5 = var11;
                    }
                    else if (var10.equals("--demo"))
                    {
                        var6 = true;
                    }
                    else if (var10.equals("--bonusChest"))
                    {
                        var7 = true;
                    }
                }
                else
                {
                    var2 = false;
                }

                if (var12)
                {
                    ++var9;
                }
            }

            DedicatedServer var16 = new DedicatedServer(new File(var4));
            var1 = var16.getLogAgent();

            if (var3 != null)
            {
                var16.setServerOwner(var3);
            }

            if (var5 != null)
            {
                var16.setFolderName(var5);
            }

            if (var8 >= 0)
            {
                var16.setServerPort(var8);
            }

            if (var6)
            {
                var16.setDemo(true);
            }

            if (var7)
            {
                var16.canCreateBonusChest(true);
            }

            if (var2)
            {
                var16.enableGui();
            }

            var16.startServerThread();
            Runtime.getRuntime().addShutdownHook(new ThreadDedicatedServer(var16));
        }
        catch (Exception var15)
        {
            if (var1 != null)
            {
                var1.logSevereException("Failed to start the minecraft server", var15);
            }
            else
            {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", var15);
            }
        }
    }

    public void startServerThread()
    {
        (new ThreadMinecraftServer(this, "Server thread")).start();
    }

    /**
     * Returns a File object from the specified string.
     */
    public File getFile(String par1Str)
    {
        return new File(this.getDataDirectory(), par1Str);
    }

    /**
     * Logs the message with a level of INFO.
     */
    public void logInfo(String par1Str)
    {
        this.getLogAgent().func_98233_a(par1Str);
    }

    /**
     * Logs the message with a level of WARN.
     */
    public void logWarning(String par1Str)
    {
        this.getLogAgent().func_98236_b(par1Str);
    }

    /**
     * Gets the worldServer by the given dimension.
     */
    public WorldServer worldServerForDimension(int par1)
    {
        return par1 == -1 ? this.worldServers[1] : (par1 == 1 ? this.worldServers[2] : this.worldServers[0]);
    }

    /**
     * Returns the server's hostname.
     */
    public String getHostname()
    {
        return this.hostname;
    }

    /**
     * Never used, but "getServerPort" is already taken.
     */
    public int getPort()
    {
        return this.serverPort;
    }

    /**
     * Returns the server message of the day
     */
    public String getMotd()
    {
        return this.motd;
    }

    /**
     * Returns the server's Minecraft version as string.
     */
    public String getMinecraftVersion()
    {
        return "1.5.2";
    }

    /**
     * Returns the number of players currently on the server.
     */
    public int getCurrentPlayerCount()
    {
        return this.serverConfigManager.getCurrentPlayerCount();
    }

    /**
     * Returns the maximum number of players allowed on the server.
     */
    public int getMaxPlayers()
    {
        return this.serverConfigManager.getMaxPlayers();
    }

    /**
     * Returns an array of the usernames of all the connected players.
     */
    public String[] getAllUsernames()
    {
        return this.serverConfigManager.getAllUsernames();
    }

    /**
     * Used by RCon's Query in the form of "MajorServerMod 1.2.3: MyPlugin 1.3; AnotherPlugin 2.1; AndSoForth 1.0".
     */
    public String getPlugins()
    {
        return "";
    }

    /**
     * Handle a command received by an RCon instance
     */
    public String handleRConCommand(String par1Str)
    {
        RConConsoleSource.instance.resetLog();
        this.commandManager.executeCommand(RConConsoleSource.instance, par1Str);
        return RConConsoleSource.instance.getLogContents();
    }

    /**
     * Returns true if debugging is enabled, false otherwise.
     */
    public boolean isDebuggingEnabled()
    {
        return false;
    }

    /**
     * Logs the error message with a level of SEVERE.
     */
    public void logSevere(String par1Str)
    {
        this.getLogAgent().logSevere(par1Str);
    }

    /**
     * If isDebuggingEnabled(), logs the message with a level of INFO.
     */
    public void logDebug(String par1Str)
    {
        if (this.isDebuggingEnabled())
        {
            this.getLogAgent().func_98233_a(par1Str);
        }
    }

    public String getServerModName()
    {
        return "vanilla";
    }

    /**
     * Adds the server info, including from theWorldServer, to the crash report.
     */
    public CrashReport addServerInfoToCrashReport(CrashReport par1CrashReport)
    {
        par1CrashReport.func_85056_g().addCrashSectionCallable("Profiler Position", new CallableIsServerModded(this));

        if (this.worldServers != null && this.worldServers.length > 0 && this.worldServers[0] != null)
        {
            par1CrashReport.func_85056_g().addCrashSectionCallable("Vec3 Pool Size", new CallableServerProfiler(this));
        }

        if (this.serverConfigManager != null)
        {
            par1CrashReport.func_85056_g().addCrashSectionCallable("Player Count", new CallableServerMemoryStats(this));
        }

        return par1CrashReport;
    }

    /**
     * If par2Str begins with /, then it searches for commands, otherwise it returns players.
     */
    public List getPossibleCompletions(ICommandSender par1ICommandSender, String par2Str)
    {
        ArrayList var3 = new ArrayList();

        if (par2Str.startsWith("/"))
        {
            par2Str = par2Str.substring(1);
            boolean var10 = !par2Str.contains(" ");
            List var11 = this.commandManager.getPossibleCommands(par1ICommandSender, par2Str);

            if (var11 != null)
            {
                Iterator var12 = var11.iterator();

                while (var12.hasNext())
                {
                    String var13 = (String)var12.next();

                    if (var10)
                    {
                        var3.add("/" + var13);
                    }
                    else
                    {
                        var3.add(var13);
                    }
                }
            }

            return var3;
        }
        else
        {
            String[] var4 = par2Str.split(" ", -1);
            String var5 = var4[var4.length - 1];
            String[] var6 = this.serverConfigManager.getAllUsernames();
            int var7 = var6.length;

            for (int var8 = 0; var8 < var7; ++var8)
            {
                String var9 = var6[var8];

                if (CommandBase.doesStringStartWith(var5, var9))
                {
                    var3.add(var9);
                }
            }

            return var3;
        }
    }

    /**
     * Gets mcServer.
     */
    public static MinecraftServer getServer()
    {
        return mcServer;
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getCommandSenderName()
    {
        return "Server";
    }

    public void sendChatToPlayer(String par1Str)
    {
        this.getLogAgent().func_98233_a(StringUtils.stripControlCodes(par1Str));
    }

    /**
     * Returns true if the command sender is allowed to use the given command.
     */
    public boolean canCommandSenderUseCommand(int par1, String par2Str)
    {
        return true;
    }

    /**
     * Translates and formats the given string key with the given arguments.
     */
    public String translateString(String par1Str, Object ... par2ArrayOfObj)
    {
        return StringTranslate.getInstance().translateKeyFormat(par1Str, par2ArrayOfObj);
    }

    public ICommandManager getCommandManager()
    {
        return this.commandManager;
    }

    /**
     * Gets KeyPair instanced in MinecraftServer.
     */
    public KeyPair getKeyPair()
    {
        return this.serverKeyPair;
    }

    /**
     * Gets serverPort.
     */
    public int getServerPort()
    {
        return this.serverPort;
    }

    public void setServerPort(int par1)
    {
        this.serverPort = par1;
    }

    /**
     * Returns the username of the server owner (for integrated servers)
     */
    public String getServerOwner()
    {
        return this.serverOwner;
    }

    /**
     * Sets the username of the owner of this server (in the case of an integrated server)
     */
    public void setServerOwner(String par1Str)
    {
        this.serverOwner = par1Str;
    }

    public boolean isSinglePlayer()
    {
        return this.serverOwner != null;
    }

    public String getFolderName()
    {
        return this.folderName;
    }

    public void setFolderName(String par1Str)
    {
        this.folderName = par1Str;
    }

    public void setKeyPair(KeyPair par1KeyPair)
    {
        this.serverKeyPair = par1KeyPair;
    }

    public void setDifficultyForAllWorlds(int par1)
    {
        for (int var2 = 0; var2 < this.worldServers.length; ++var2)
        {
            WorldServer var3 = this.worldServers[var2];

            if (var3 != null)
            {
                if (var3.getWorldInfo().isHardcoreModeEnabled())
                {
                    var3.difficultySetting = 3;
                    var3.setAllowedSpawnTypes(true, true);
                }
                else if (this.isSinglePlayer())
                {
                    var3.difficultySetting = par1;
                    var3.setAllowedSpawnTypes(var3.difficultySetting > 0, true);
                }
                else
                {
                    var3.difficultySetting = par1;
                    var3.setAllowedSpawnTypes(this.allowSpawnMonsters(), this.canSpawnAnimals);
                }
            }
        }
    }

    protected boolean allowSpawnMonsters()
    {
        return true;
    }

    /**
     * Gets whether this is a demo or not.
     */
    public boolean isDemo()
    {
        return this.isDemo;
    }

    /**
     * Sets whether this is a demo or not.
     */
    public void setDemo(boolean par1)
    {
        this.isDemo = par1;
    }

    public void canCreateBonusChest(boolean par1)
    {
        this.enableBonusChest = par1;
    }

    public ISaveFormat getActiveAnvilConverter()
    {
        return this.anvilConverterForAnvilFile;
    }

    /**
     * WARNING : directly calls
     * getActiveAnvilConverter().deleteWorldDirectory(theWorldServer[0].getSaveHandler().getWorldDirectoryName());
     */
    public void deleteWorldAndStopServer()
    {
        this.worldIsBeingDeleted = true;
        this.getActiveAnvilConverter().flushCache();

        for (int var1 = 0; var1 < this.worldServers.length; ++var1)
        {
            WorldServer var2 = this.worldServers[var1];

            if (var2 != null)
            {
                var2.flush();
            }
        }

        this.getActiveAnvilConverter().deleteWorldDirectory(this.worldServers[0].getSaveHandler().getWorldDirectoryName());
        this.initiateShutdown();
    }

    public String getTexturePack()
    {
        return this.texturePack;
    }

    public void setTexturePack(String par1Str)
    {
        this.texturePack = par1Str;
    }

    public void addServerStatsToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper)
    {
        par1PlayerUsageSnooper.addData("whitelist_enabled", Boolean.valueOf(false));
        par1PlayerUsageSnooper.addData("whitelist_count", Integer.valueOf(0));
        par1PlayerUsageSnooper.addData("players_current", Integer.valueOf(this.getCurrentPlayerCount()));
        par1PlayerUsageSnooper.addData("players_max", Integer.valueOf(this.getMaxPlayers()));
        par1PlayerUsageSnooper.addData("players_seen", Integer.valueOf(this.serverConfigManager.getAvailablePlayerDat().length));
        par1PlayerUsageSnooper.addData("uses_auth", Boolean.valueOf(this.onlineMode));
        par1PlayerUsageSnooper.addData("gui_state", this.getGuiEnabled() ? "enabled" : "disabled");
        par1PlayerUsageSnooper.addData("avg_tick_ms", Integer.valueOf((int)(MathHelper.average(this.tickTimeArray) * 1.0E-6D)));
        par1PlayerUsageSnooper.addData("avg_sent_packet_count", Integer.valueOf((int)MathHelper.average(this.sentPacketCountArray)));
        par1PlayerUsageSnooper.addData("avg_sent_packet_size", Integer.valueOf((int)MathHelper.average(this.sentPacketSizeArray)));
        par1PlayerUsageSnooper.addData("avg_rec_packet_count", Integer.valueOf((int)MathHelper.average(this.receivedPacketCountArray)));
        par1PlayerUsageSnooper.addData("avg_rec_packet_size", Integer.valueOf((int)MathHelper.average(this.receivedPacketSizeArray)));
        int var2 = 0;

        for (int var3 = 0; var3 < this.worldServers.length; ++var3)
        {
            if (this.worldServers[var3] != null)
            {
                WorldServer var4 = this.worldServers[var3];
                WorldInfo var5 = var4.getWorldInfo();
                par1PlayerUsageSnooper.addData("world[" + var2 + "][dimension]", Integer.valueOf(var4.provider.dimensionId));
                par1PlayerUsageSnooper.addData("world[" + var2 + "][mode]", var5.getGameType());
                par1PlayerUsageSnooper.addData("world[" + var2 + "][difficulty]", Integer.valueOf(var4.difficultySetting));
                par1PlayerUsageSnooper.addData("world[" + var2 + "][hardcore]", Boolean.valueOf(var5.isHardcoreModeEnabled()));
                par1PlayerUsageSnooper.addData("world[" + var2 + "][generator_name]", var5.getTerrainType().getWorldTypeName());
                par1PlayerUsageSnooper.addData("world[" + var2 + "][generator_version]", Integer.valueOf(var5.getTerrainType().getGeneratorVersion()));
                par1PlayerUsageSnooper.addData("world[" + var2 + "][height]", Integer.valueOf(this.buildLimit));
                par1PlayerUsageSnooper.addData("world[" + var2 + "][chunks_loaded]", Integer.valueOf(var4.getChunkProvider().getLoadedChunkCount()));
                ++var2;
            }
        }

        par1PlayerUsageSnooper.addData("worlds", Integer.valueOf(var2));
    }

    public void addServerTypeToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper)
    {
        par1PlayerUsageSnooper.addData("singleplayer", Boolean.valueOf(this.isSinglePlayer()));
        par1PlayerUsageSnooper.addData("server_brand", this.getServerModName());
        par1PlayerUsageSnooper.addData("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        par1PlayerUsageSnooper.addData("dedicated", Boolean.valueOf(this.isDedicatedServer()));
    }

    /**
     * Returns whether snooping is enabled or not.
     */
    public boolean isSnooperEnabled()
    {
        return true;
    }

    /**
     * This is checked to be 16 upon receiving the packet, otherwise the packet is ignored.
     */
    public int textureSize()
    {
        return 16;
    }

    public abstract boolean isDedicatedServer();

    public boolean isServerInOnlineMode()
    {
        return this.onlineMode;
    }

    public void setOnlineMode(boolean par1)
    {
        this.onlineMode = par1;
    }

    public boolean getCanSpawnAnimals()
    {
        return this.canSpawnAnimals;
    }

    public void setCanSpawnAnimals(boolean par1)
    {
        this.canSpawnAnimals = par1;
    }

    public boolean getCanSpawnNPCs()
    {
        return this.canSpawnNPCs;
    }

    public void setCanSpawnNPCs(boolean par1)
    {
        this.canSpawnNPCs = par1;
    }

    public boolean isPVPEnabled()
    {
        return this.pvpEnabled;
    }

    public void setAllowPvp(boolean par1)
    {
        this.pvpEnabled = par1;
    }

    public boolean isFlightAllowed()
    {
        return this.allowFlight;
    }

    public void setAllowFlight(boolean par1)
    {
        this.allowFlight = par1;
    }

    /**
     * Return whether command blocks are enabled.
     */
    public abstract boolean isCommandBlockEnabled();

    public String getMOTD()
    {
        return this.motd;
    }

    public void setMOTD(String par1Str)
    {
        this.motd = par1Str;
    }

    public int getBuildLimit()
    {
        return this.buildLimit;
    }

    public void setBuildLimit(int par1)
    {
        this.buildLimit = par1;
    }

    public boolean isServerStopped()
    {
        return this.serverStopped;
    }

    public ServerConfigurationManager getConfigurationManager()
    {
        return this.serverConfigManager;
    }

    public void setConfigurationManager(ServerConfigurationManager par1ServerConfigurationManager)
    {
        this.serverConfigManager = par1ServerConfigurationManager;
    }

    /**
     * Sets the game type for all worlds.
     */
    public void setGameType(EnumGameType par1EnumGameType)
    {
        for (int var2 = 0; var2 < this.worldServers.length; ++var2)
        {
            getServer().worldServers[var2].getWorldInfo().setGameType(par1EnumGameType);
        }
    }

    public abstract NetworkListenThread getNetworkThread();

    public boolean getGuiEnabled()
    {
        return false;
    }

    /**
     * On dedicated does nothing. On integrated, sets commandsAllowedForAll, gameType and allows external connections.
     */
    public abstract String shareToLAN(EnumGameType var1, boolean var2);

    public int getTickCounter()
    {
        return this.tickCounter;
    }

    public void enableProfiling()
    {
        this.startProfiling = true;
    }

    /**
     * Return the position for this command sender.
     */
    public ChunkCoordinates getCommandSenderPosition()
    {
        return new ChunkCoordinates(0, 0, 0);
    }

    /**
     * Return the spawn protection area's size.
     */
    public int getSpawnProtectionSize()
    {
        return 16;
    }

    public boolean func_96290_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        return false;
    }

    public abstract ILogAgent getLogAgent();

    public void func_104055_i(boolean par1)
    {
        this.field_104057_T = par1;
    }

    public boolean func_104056_am()
    {
        return this.field_104057_T;
    }

    /**
     * Gets the current player count, maximum player count, and player entity list.
     */
    public static ServerConfigurationManager getServerConfigurationManager(MinecraftServer par0MinecraftServer)
    {
        return par0MinecraftServer.serverConfigManager;
    }
}
