package net.minecraft.src;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

//FCMOD: Added
import java.util.LinkedList;
// END FCMOD

public abstract class World implements IBlockAccess
{
    /**
     * boolean; if true updates scheduled by scheduleBlockUpdate happen immediately
     */
    public boolean scheduledUpdatesAreImmediate = false;

    /** A list of all Entities in all currently-loaded chunks */
    public List loadedEntityList = new ArrayList();
    protected List unloadedEntityList = new ArrayList();

    /** A list of all TileEntities in all currently-loaded chunks */
    public List loadedTileEntityList = new ArrayList();
    private List addedTileEntityList = new ArrayList();

    /** Entities marked for removal. */
    private List entityRemoval = new ArrayList();

    /** Array list of players in the world. */
    public List playerEntities = new ArrayList();

    /** a list of all the lightning entities */
    public List weatherEffects = new ArrayList();
    private long cloudColour = 16777215L;

    /** How much light is subtracted from full daylight */
    public int skylightSubtracted = 0;

    /**
     * Contains the current Linear Congruential Generator seed for block updates. Used with an A value of 3 and a C
     * value of 0x3c6ef35f, producing a highly planar series of values ill-suited for choosing random blocks in a
     * 16x128x16 field.
     */
    protected int updateLCG = (new Random()).nextInt();

    /**
     * magic number used to generate fast random numbers for 3d distribution within a chunk
     */
    protected final int DIST_HASH_MAGIC = 1013904223;
    protected float prevRainingStrength;
    protected float rainingStrength;
    protected float prevThunderingStrength;
    protected float thunderingStrength;

    /**
     * Set to 2 whenever a lightning bolt is generated in SSP. Decrements if > 0 in updateWeather(). Value appears to be
     * unused.
     */
    public int lastLightningBolt = 0;

    /** Whether monsters are enabled or not. (1 = on, 0 = off) */
    public int difficultySetting;

    /** RNG for World. */
    public Random rand = new Random();

    /** The WorldProvider instance that World uses. */
    public final WorldProvider provider;
    protected List worldAccesses = new ArrayList();

    /** Handles chunk operations and caching */
    protected IChunkProvider chunkProvider;
    protected final ISaveHandler saveHandler;

    /**
     * holds information about a world (size on disk, time, spawn point, seed, ...)
     */
    protected WorldInfo worldInfo;

    /**
     * if set, this flag forces a request to load a chunk to load the chunk rather than defaulting to the world's
     * chunkprovider's dummy if possible
     */
    public boolean findingSpawnPoint;
    public MapStorage mapStorage;
    public final VillageCollection villageCollectionObj;
    // FCMOD: Removed
    //protected final VillageSiege villageSiegeObj = new VillageSiege(this);
    // END FCMOD
    public final Profiler theProfiler;

    /** The world-local pool of vectors */
    private final Vec3Pool vecPool = new Vec3Pool(300, 2000);
    private final Calendar theCalendar = Calendar.getInstance();
    protected Scoreboard worldScoreboard = new Scoreboard();

    /** The log agent for this world. */
    private final ILogAgent worldLogAgent;
    private ArrayList collidingBoundingBoxes = new ArrayList();
    private boolean scanningTileEntities;

    /** indicates if enemies are spawned or not */
    protected boolean spawnHostileMobs = true;

    /** A flag indicating whether we should spawn peaceful mobs. */
    protected boolean spawnPeacefulMobs = true;

    /** populated by chunks that are within 9 chunks of any player */
    // FCMOD: Changed to use same type as ChunkProviderServer.loadedChunkHashMap, to avoid
    // duplicate hash values.  Renamed as new type requires existing references to be adapated
    // appropriately.
    //protected Set activeChunkSet = new HashSet();
    /** 
     * populated by chunks that are within the view distance (default 8 chunks) of any player,
     * the original spawn, or any other chunk loader
     */
    protected LongHashMap m_activeChunksCoordsMap = new LongHashMap();
    
    /** 
     * list of same coords in m_activeChunksCoordsMap
     */
    protected LinkedList<ChunkCoordIntPair> m_activeChunksCoordsList = 
    	new LinkedList<ChunkCoordIntPair>(); 
    // END FCMOD

    /** number of ticks until the next random ambients play */
    private int ambientTickCountdown;

    /**
     * is a temporary list of blocks and light values used when updating light levels. Holds up to 32x32x32 blocks (the
     * maximum influence of a light source.) Every element is a packed bit value: 0000000000LLLLzzzzzzyyyyyyxxxxxx. The
     * 4-bit L is a light level used when darkening blocks. 6-bit numbers x, y and z represent the block's offset from
     * the original block, plus 32 (i.e. value of 31 would mean a -1 offset
     */
    int[] lightUpdateBlockList;

    /** This is set to true for client worlds, and false for server worlds. */
    public boolean isRemote;
    
    //BTAMOD
    private BTAWorldConfigurationInfo generatorInfoCache;

    /**
     * Gets the biome for a given set of x/z coordinates
     */
    public BiomeGenBase getBiomeGenForCoords(int par1, int par2)
    {
        if (this.blockExists(par1, 0, par2))
        {
            Chunk var3 = this.getChunkFromBlockCoords(par1, par2);

            if (var3 != null)
            {
                return var3.getBiomeGenForWorldCoords(par1 & 15, par2 & 15, this.provider.worldChunkMgr);
            }
        }

        return this.provider.worldChunkMgr.getBiomeGenAt(par1, par2);
    }

    public WorldChunkManager getWorldChunkManager()
    {
        return this.provider.worldChunkMgr;
    }

    public World(ISaveHandler par1ISaveHandler, String par2Str, WorldSettings par3WorldSettings, WorldProvider par4WorldProvider, Profiler par5Profiler, ILogAgent par6ILogAgent)
    {
        // FCMOD: Added
    	// function tests internally to make sure this only occurs once
        FCAddOnHandler.InitializeMods(); 
        // END FCMOD
    	
        this.ambientTickCountdown = this.rand.nextInt(12000);
        this.lightUpdateBlockList = new int[32768];
        this.isRemote = false;
        this.saveHandler = par1ISaveHandler;
        this.theProfiler = par5Profiler;
        this.mapStorage = new MapStorage(par1ISaveHandler);
        this.worldLogAgent = par6ILogAgent;
        this.worldInfo = par1ISaveHandler.loadWorldInfo();

        if (par4WorldProvider != null)
        {
            this.provider = par4WorldProvider;
        }
        else if (this.worldInfo != null && this.worldInfo.getDimension() != 0)
        {
            this.provider = WorldProvider.getProviderForDimension(this.worldInfo.getDimension());
        }
        else
        {
            this.provider = WorldProvider.getProviderForDimension(0);
        }

        if (this.worldInfo == null)
        {
            this.worldInfo = new WorldInfo(par3WorldSettings, par2Str);
        }
        else
        {
            this.worldInfo.setWorldName(par2Str);
        }

        this.provider.registerWorld(this);
        this.chunkProvider = this.createChunkProvider();

        if (!this.worldInfo.isInitialized())
        {
            try
            {
                this.initialize(par3WorldSettings);
            }
            catch (Throwable var11)
            {
                CrashReport var8 = CrashReport.makeCrashReport(var11, "Exception initializing level");

                try
                {
                    this.addWorldInfoToCrashReport(var8);
                }
                catch (Throwable var10)
                {
                    ;
                }

                throw new ReportedException(var8);
            }

            this.worldInfo.setServerInitialized(true);
        }

        VillageCollection var7 = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, "villages");

        if (var7 == null)
        {
            this.villageCollectionObj = new VillageCollection(this);
            this.mapStorage.setData("villages", this.villageCollectionObj);
        }
        else
        {
            this.villageCollectionObj = var7;
            this.villageCollectionObj.func_82566_a(this);
        }

        this.calculateInitialSkylight();
        this.calculateInitialWeather();
    }

    /**
     * Creates the chunk provider for this world. Called in the constructor. Retrieves provider from worldProvider?
     */
    protected abstract IChunkProvider createChunkProvider();

    protected void initialize(WorldSettings par1WorldSettings)
    {
        this.worldInfo.setServerInitialized(true);
    }

    /**
     * Returns the block ID of the first block at this (x,z) location with air above it, searching from sea level
     * upwards.
     */
    public int getFirstUncoveredBlock(int par1, int par2)
    {
        int var3;

        for (var3 = 63; !this.isAirBlock(par1, var3 + 1, par2); ++var3)
        {
            ;
        }

        return this.getBlockId(par1, var3, par2);
    }

    /**
     * Returns the block ID at coords x,y,z
     */
    public int getBlockId(int par1, int par2, int par3)
    {
        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
        {
            if (par2 < 0)
            {
                return 0;
            }
            else if (par2 >= 256)
            {
                return 0;
            }
            else
            {
                Chunk var4 = null;

                try
                {
                    var4 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
                    return var4.getBlockID(par1 & 15, par2, par3 & 15);
                }
                catch (Throwable var8)
                {
                    CrashReport var6 = CrashReport.makeCrashReport(var8, "Exception getting block type in world");
                    CrashReportCategory var7 = var6.makeCategory("Requested block coordinates");
                    var7.addCrashSection("Found chunk", Boolean.valueOf(var4 == null));
                    var7.addCrashSection("Location", CrashReportCategory.getLocationInfo(par1, par2, par3));
                    throw new ReportedException(var6);
                }
            }
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns true if the block at the specified coordinates is empty
     */
    public boolean isAirBlock(int par1, int par2, int par3)
    {
    	// FCMOD: Change
		/*
        return this.getBlockId(par1, par2, par3) == 0;
		*/
    	int iBlockID = getBlockId(par1, par2, par3);
    	
        return iBlockID == 0 || Block.blocksList[iBlockID].IsAirBlock(); 
        // END FCMOD
    }

    /**
     * Checks if a block at a given position should have a tile entity.
     */
    public boolean blockHasTileEntity(int par1, int par2, int par3)
    {
        int var4 = this.getBlockId(par1, par2, par3);
        return Block.blocksList[var4] != null && Block.blocksList[var4].hasTileEntity();
    }

    /**
     * Returns the render type of the block at the given coordinate.
     */
    public int blockGetRenderType(int par1, int par2, int par3)
    {
        int var4 = this.getBlockId(par1, par2, par3);
        return Block.blocksList[var4] != null ? Block.blocksList[var4].getRenderType() : -1;
    }

    /**
     * Returns whether a block exists at world coordinates x, y, z
     */
    public boolean blockExists(int par1, int par2, int par3)
    {
        return par2 >= 0 && par2 < 256 ? this.chunkExists(par1 >> 4, par3 >> 4) : false;
    }

    /**
     * Checks if any of the chunks within distance (argument 4) blocks of the given block exist
     */
    public boolean doChunksNearChunkExist(int par1, int par2, int par3, int par4)
    {
        return this.checkChunksExist(par1 - par4, par2 - par4, par3 - par4, par1 + par4, par2 + par4, par3 + par4);
    }

    /**
     * Checks between a min and max all the chunks inbetween actually exist. Args: minX, minY, minZ, maxX, maxY, maxZ
     */
    public boolean checkChunksExist(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        if (par5 >= 0 && par2 < 256)
        {
            par1 >>= 4;
            par3 >>= 4;
            par4 >>= 4;
            par6 >>= 4;

            for (int var7 = par1; var7 <= par4; ++var7)
            {
                for (int var8 = par3; var8 <= par6; ++var8)
                {
                    if (!this.chunkExists(var7, var8))
                    {
                        return false;
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns whether a chunk exists at chunk coordinates x, y
     */
    protected boolean chunkExists(int par1, int par2)
    {
        return this.chunkProvider.chunkExists(par1, par2);
    }

    /**
     * Returns a chunk looked up by block coordinates. Args: x, z
     */
    public Chunk getChunkFromBlockCoords(int par1, int par2)
    {
        return this.getChunkFromChunkCoords(par1 >> 4, par2 >> 4);
    }

    /**
     * Returns back a chunk looked up by chunk coordinates Args: x, y
     */
    public Chunk getChunkFromChunkCoords(int par1, int par2)
    {
        return this.chunkProvider.provideChunk(par1, par2);
    }

    /**
     * Sets the block ID and metadata at a given location. Args: X, Y, Z, new block ID, new metadata, flags. Flag 1 will
     * cause a block update. Flag 2 will send the change to clients (you almost always want this). Flag 4 prevents the
     * block from being re-rendered, if this is a client world. Flags can be added together.
     */
    /**
     * FCNOTE: Bit 1 notify neighbors.  Bit 2 sends change to clients.
     * Bit 4 seems to prevent a render update when called on client
     */
    public boolean setBlock(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
        {
            if (par2 < 0)
            {
                return false;
            }
            else if (par2 >= 256)
            {
                return false;
            }
            else
            {
                Chunk var7 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
                int var8 = 0;

                if ((par6 & 1) != 0)
                {
                    var8 = var7.getBlockID(par1 & 15, par2, par3 & 15);
                }

                boolean var9 = var7.setBlockIDWithMetadata(par1 & 15, par2, par3 & 15, par4, par5);
                this.theProfiler.startSection("checkLight");
                this.updateAllLightTypes(par1, par2, par3);
                this.theProfiler.endSection();

                if (var9)
                {
                    if ((par6 & 2) != 0 && (!this.isRemote || (par6 & 4) == 0))
                    {
                        this.markBlockForUpdate(par1, par2, par3);
                    }

                    if (!this.isRemote && (par6 & 1) != 0)
                    {
                        this.notifyBlockChange(par1, par2, par3, var8);
                        Block var10 = Block.blocksList[par4];

                        if (var10 != null && var10.hasComparatorInputOverride())
                        {
                            this.func_96440_m(par1, par2, par3, par4);
                        }
                    }
                }

                return var9;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the block's material.
     */
    public Material getBlockMaterial(int par1, int par2, int par3)
    {
        int var4 = this.getBlockId(par1, par2, par3);
        return var4 == 0 ? Material.air : Block.blocksList[var4].blockMaterial;
    }

    /**
     * Returns the block metadata at coords x,y,z
     */
    public int getBlockMetadata(int par1, int par2, int par3)
    {
        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
        {
            if (par2 < 0)
            {
                return 0;
            }
            else if (par2 >= 256)
            {
                return 0;
            }
            else
            {
                Chunk var4 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
                par1 &= 15;
                par3 &= 15;
                return var4.getBlockMetadata(par1, par2, par3);
            }
        }
        else
        {
            return 0;
        }
    }

    /**
     * Sets the blocks metadata and if set will then notify blocks that this block changed, depending on the flag. Args:
     * x, y, z, metadata, flag. See setBlock for flag description
     */
    /**
     * FCNOTE: Bit 1 notify neighbors.  Bit 2 sends change to clients.
     * Bit 4 seems to prevent a render update when called on client
     */
    public boolean setBlockMetadata(int par1, int par2, int par3, int par4, int par5)
    {
        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
        {
            if (par2 < 0)
            {
                return false;
            }
            else if (par2 >= 256)
            {
                return false;
            }
            else
            {
                Chunk var6 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
                int var7 = par1 & 15;
                int var8 = par3 & 15;
                boolean var9 = var6.setBlockMetadata(var7, par2, var8, par4);

                if (var9)
                {
                    int var10 = var6.getBlockID(var7, par2, var8);

                    if ((par5 & 2) != 0 && (!this.isRemote || (par5 & 4) == 0))
                    {
                        this.markBlockForUpdate(par1, par2, par3);
                    }

                    if (!this.isRemote && (par5 & 1) != 0)
                    {
                        this.notifyBlockChange(par1, par2, par3, var10);
                        Block var11 = Block.blocksList[var10];

                        if (var11 != null && var11.hasComparatorInputOverride())
                        {
                            this.func_96440_m(par1, par2, par3, var10);
                        }
                    }
                }

                return var9;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Sets a block to 0 and notifies relevant systems with the block change  Args: x, y, z
     */
    public boolean setBlockToAir(int par1, int par2, int par3)
    {
        return this.setBlock(par1, par2, par3, 0, 0, 3);
    }

    /**
     * Destroys a block and optionally drops items. Args: X, Y, Z, dropItems
     */
    public boolean destroyBlock(int par1, int par2, int par3, boolean par4)
    {
        int var5 = this.getBlockId(par1, par2, par3);

        if (var5 > 0)
        {
            int var6 = this.getBlockMetadata(par1, par2, par3);
            this.playAuxSFX(2001, par1, par2, par3, var5 + (var6 << 12));

            if (par4)
            {
                Block.blocksList[var5].dropBlockAsItem(this, par1, par2, par3, var6, 0);
            }

            return this.setBlock(par1, par2, par3, 0, 0, 3);
        }
        else
        {
            return false;
        }
    }

    /**
     * Sets a block and notifies relevant systems with the block change  Args: x, y, z, blockID
     */
    public boolean setBlock(int par1, int par2, int par3, int par4)
    {
        return this.setBlock(par1, par2, par3, par4, 0, 3);
    }

    /**
     * On the client, re-renders the block. On the server, sends the block to the client (which will re-render it),
     * including the tile entity description packet if applicable. Args: x, y, z
     */
    public void markBlockForUpdate(int par1, int par2, int par3)
    {
        for (int var4 = 0; var4 < this.worldAccesses.size(); ++var4)
        {
            ((IWorldAccess)this.worldAccesses.get(var4)).markBlockForUpdate(par1, par2, par3);
        }
    }

    /**
     * The block type change and need to notify other systems  Args: x, y, z, blockID
     */
    public void notifyBlockChange(int par1, int par2, int par3, int par4)
    {
        this.notifyBlocksOfNeighborChange(par1, par2, par3, par4);
    }

    /**
     * marks a vertical line of blocks as dirty
     */
    public void markBlocksDirtyVertical(int par1, int par2, int par3, int par4)
    {
        int var5;

        if (par3 > par4)
        {
            var5 = par4;
            par4 = par3;
            par3 = var5;
        }

        if (!this.provider.hasNoSky)
        {
            for (var5 = par3; var5 <= par4; ++var5)
            {
                this.updateLightByType(EnumSkyBlock.Sky, par1, var5, par2);
            }
        }

        this.markBlockRangeForRenderUpdate(par1, par3, par2, par1, par4, par2);
    }

    /**
     * On the client, re-renders all blocks in this range, inclusive. On the server, does nothing. Args: min x, min y,
     * min z, max x, max y, max z
     */
    public void markBlockRangeForRenderUpdate(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        for (int var7 = 0; var7 < this.worldAccesses.size(); ++var7)
        {
            ((IWorldAccess)this.worldAccesses.get(var7)).markBlockRangeForRenderUpdate(par1, par2, par3, par4, par5, par6);
        }
    }

    /**
     * Notifies neighboring blocks that this specified block changed  Args: x, y, z, blockID
     */
    public void notifyBlocksOfNeighborChange(int par1, int par2, int par3, int par4)
    {
        this.notifyBlockOfNeighborChange(par1 - 1, par2, par3, par4);
        this.notifyBlockOfNeighborChange(par1 + 1, par2, par3, par4);
        this.notifyBlockOfNeighborChange(par1, par2 - 1, par3, par4);
        this.notifyBlockOfNeighborChange(par1, par2 + 1, par3, par4);
        this.notifyBlockOfNeighborChange(par1, par2, par3 - 1, par4);
        this.notifyBlockOfNeighborChange(par1, par2, par3 + 1, par4);
    }

    /**
     * Calls notifyBlockOfNeighborChange on adjacent blocks, except the one on the given side. Args: X, Y, Z,
     * changingBlockID, side
     */
    public void notifyBlocksOfNeighborChange(int par1, int par2, int par3, int par4, int par5)
    {
        if (par5 != 4)
        {
            this.notifyBlockOfNeighborChange(par1 - 1, par2, par3, par4);
        }

        if (par5 != 5)
        {
            this.notifyBlockOfNeighborChange(par1 + 1, par2, par3, par4);
        }

        if (par5 != 0)
        {
            this.notifyBlockOfNeighborChange(par1, par2 - 1, par3, par4);
        }

        if (par5 != 1)
        {
            this.notifyBlockOfNeighborChange(par1, par2 + 1, par3, par4);
        }

        if (par5 != 2)
        {
            this.notifyBlockOfNeighborChange(par1, par2, par3 - 1, par4);
        }

        if (par5 != 3)
        {
            this.notifyBlockOfNeighborChange(par1, par2, par3 + 1, par4);
        }
    }

    /**
     * Notifies a block that one of its neighbor change to the specified type Args: x, y, z, blockID
     */
    public void notifyBlockOfNeighborChange(int par1, int par2, int par3, int par4)
    {
        if (!this.isRemote)
        {
            int var5 = this.getBlockId(par1, par2, par3);
            Block var6 = Block.blocksList[var5];

            if (var6 != null)
            {
                try
                {
                    var6.onNeighborBlockChange(this, par1, par2, par3, par4);
                }
                catch (Throwable var13)
                {
                    CrashReport var8 = CrashReport.makeCrashReport(var13, "Exception while updating neighbours");
                    CrashReportCategory var9 = var8.makeCategory("Block being updated");
                    int var10;

                    try
                    {
                        var10 = this.getBlockMetadata(par1, par2, par3);
                    }
                    catch (Throwable var12)
                    {
                        var10 = -1;
                    }

                    var9.addCrashSectionCallable("Source block type", new CallableLvl1(this, par4));
                    CrashReportCategory.func_85068_a(var9, par1, par2, par3, var5, var10);
                    throw new ReportedException(var8);
                }
            }
        }
    }

    /**
     * Returns true if the given block will receive a scheduled tick in the future. Args: X, Y, Z, blockID
     */
    public boolean isBlockTickScheduled(int par1, int par2, int par3, int par4)
    {
        return false;
    }

    /**
     * Checks if the specified block is able to see the sky
     */
    public boolean canBlockSeeTheSky(int par1, int par2, int par3)
    {
        return this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4).canBlockSeeTheSky(par1 & 15, par2, par3 & 15);
    }

    /**
     * gets the block's light value - without the _do function's checks.
     */
    public int getFullBlockLightValue(int par1, int par2, int par3)
    {
        if (par2 < 0)
        {
            return 0;
        }
        else
        {
            if (par2 >= 256)
            {
                par2 = 255;
            }

            return this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4).getBlockLightValue(par1 & 15, par2, par3 & 15, 0);
        }
    }

    /**
     * Gets the light value of a block location
     */
    public int getBlockLightValue(int par1, int par2, int par3)
    {
        return this.getBlockLightValue_do(par1, par2, par3, true);
    }

    /**
     * Gets the light value of a block location. This is the actual function that gets the value and has a bool flag
     * that indicates if its a half step block to get the maximum light value of a direct neighboring block (left,
     * right, forward, back, and up)
     */
    public int getBlockLightValue_do(int par1, int par2, int par3, boolean par4)
    {
        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
        {
            if (par4)
            {
                int var5 = this.getBlockId(par1, par2, par3);

                if (Block.useNeighborBrightness[var5])
                {
                    int var6 = this.getBlockLightValue_do(par1, par2 + 1, par3, false);
                    int var7 = this.getBlockLightValue_do(par1 + 1, par2, par3, false);
                    int var8 = this.getBlockLightValue_do(par1 - 1, par2, par3, false);
                    int var9 = this.getBlockLightValue_do(par1, par2, par3 + 1, false);
                    int var10 = this.getBlockLightValue_do(par1, par2, par3 - 1, false);

                    if (var7 > var6)
                    {
                        var6 = var7;
                    }

                    if (var8 > var6)
                    {
                        var6 = var8;
                    }

                    if (var9 > var6)
                    {
                        var6 = var9;
                    }

                    if (var10 > var6)
                    {
                        var6 = var10;
                    }

                    return var6;
                }
            }

            if (par2 < 0)
            {
                return 0;
            }
            else
            {
                if (par2 >= 256)
                {
                    par2 = 255;
                }

                Chunk var11 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
                par1 &= 15;
                par3 &= 15;
                return var11.getBlockLightValue(par1, par2, par3, this.skylightSubtracted);
            }
        }
        else
        {
            return 15;
        }
    }

    /**
     * Returns the y coordinate with a block in it at this x, z coordinate
     */
    public int getHeightValue(int par1, int par2)
    {
        if (par1 >= -30000000 && par2 >= -30000000 && par1 < 30000000 && par2 < 30000000)
        {
            if (!this.chunkExists(par1 >> 4, par2 >> 4))
            {
                return 0;
            }
            else
            {
                Chunk var3 = this.getChunkFromChunkCoords(par1 >> 4, par2 >> 4);
                return var3.getHeightValue(par1 & 15, par2 & 15);
            }
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gets the heightMapMinimum field of the given chunk, or 0 if the chunk is not loaded. Coords are in blocks. Args:
     * X, Z
     */
    public int getChunkHeightMapMinimum(int par1, int par2)
    {
        if (par1 >= -30000000 && par2 >= -30000000 && par1 < 30000000 && par2 < 30000000)
        {
            if (!this.chunkExists(par1 >> 4, par2 >> 4))
            {
                return 0;
            }
            else
            {
                Chunk var3 = this.getChunkFromChunkCoords(par1 >> 4, par2 >> 4);
                return var3.heightMapMinimum;
            }
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns saved light value without taking into account the time of day.  Either looks in the sky light map or
     * block light map based on the enumSkyBlock arg.
     */
    public int getSavedLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4)
    {
        if (par3 < 0)
        {
            par3 = 0;
        }

        if (par3 >= 256)
        {
            par3 = 255;
        }

        if (par2 >= -30000000 && par4 >= -30000000 && par2 < 30000000 && par4 < 30000000)
        {
            int var5 = par2 >> 4;
            int var6 = par4 >> 4;

            if (!this.chunkExists(var5, var6))
            {
                return par1EnumSkyBlock.defaultLightValue;
            }
            else
            {
                Chunk var7 = this.getChunkFromChunkCoords(var5, var6);
                return var7.getSavedLightValue(par1EnumSkyBlock, par2 & 15, par3, par4 & 15);
            }
        }
        else
        {
            return par1EnumSkyBlock.defaultLightValue;
        }
    }

    /**
     * Sets the light value either into the sky map or block map depending on if enumSkyBlock is set to sky or block.
     * Args: enumSkyBlock, x, y, z, lightValue
     */
    public void setLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, int par5)
    {
        if (par2 >= -30000000 && par4 >= -30000000 && par2 < 30000000 && par4 < 30000000)
        {
            if (par3 >= 0)
            {
                if (par3 < 256)
                {
                    if (this.chunkExists(par2 >> 4, par4 >> 4))
                    {
                        Chunk var6 = this.getChunkFromChunkCoords(par2 >> 4, par4 >> 4);
                        var6.setLightValue(par1EnumSkyBlock, par2 & 15, par3, par4 & 15, par5);

                        for (int var7 = 0; var7 < this.worldAccesses.size(); ++var7)
                        {
                            ((IWorldAccess)this.worldAccesses.get(var7)).markBlockForRenderUpdate(par2, par3, par4);
                        }
                    }
                }
            }
        }
    }

    /**
     * On the client, re-renders this block. On the server, does nothing. Used for lighting updates.
     */
    public void markBlockForRenderUpdate(int par1, int par2, int par3)
    {
        for (int var4 = 0; var4 < this.worldAccesses.size(); ++var4)
        {
            ((IWorldAccess)this.worldAccesses.get(var4)).markBlockForRenderUpdate(par1, par2, par3);
        }
    }

    /**
     * Returns how bright the block is shown as which is the block's light value looked up in a lookup table (light
     * values aren't linear for brightness). Args: x, y, z
     */
    public float getLightBrightness(int par1, int par2, int par3)
    {
        return this.provider.lightBrightnessTable[this.getBlockLightValue(par1, par2, par3)];
    }

    /**
     * Checks whether its daytime by seeing if the light subtracted from the skylight is less than 4
     */
    public boolean isDaytime()
    {
        return this.skylightSubtracted < 4;
    }

    /**
     * ray traces all blocks, including non-collideable ones
     */
    public MovingObjectPosition rayTraceBlocks(Vec3 par1Vec3, Vec3 par2Vec3)
    {
        return this.rayTraceBlocks_do_do(par1Vec3, par2Vec3, false, false);
    }

    public MovingObjectPosition rayTraceBlocks_do(Vec3 par1Vec3, Vec3 par2Vec3, boolean par3)
    {
        return this.rayTraceBlocks_do_do(par1Vec3, par2Vec3, par3, false);
    }

    // FCMOD: Code removed and replaced later
    /*
    public MovingObjectPosition rayTraceBlocks_do_do(Vec3 par1Vec3, Vec3 par2Vec3, boolean par3, boolean par4)
    {
        if (!Double.isNaN(par1Vec3.xCoord) && !Double.isNaN(par1Vec3.yCoord) && !Double.isNaN(par1Vec3.zCoord))
        {
            if (!Double.isNaN(par2Vec3.xCoord) && !Double.isNaN(par2Vec3.yCoord) && !Double.isNaN(par2Vec3.zCoord))
            {
                int var5 = MathHelper.floor_double(par2Vec3.xCoord);
                int var6 = MathHelper.floor_double(par2Vec3.yCoord);
                int var7 = MathHelper.floor_double(par2Vec3.zCoord);
                int var8 = MathHelper.floor_double(par1Vec3.xCoord);
                int var9 = MathHelper.floor_double(par1Vec3.yCoord);
                int var10 = MathHelper.floor_double(par1Vec3.zCoord);
                int var11 = this.getBlockId(var8, var9, var10);
                int var12 = this.getBlockMetadata(var8, var9, var10);
                Block var13 = Block.blocksList[var11];

                if ((!par4 || var13 == null || var13.getCollisionBoundingBoxFromPool(this, var8, var9, var10) != null) && var11 > 0 && var13.canCollideCheck(var12, par3))
                {
                    MovingObjectPosition var14 = var13.collisionRayTrace(this, var8, var9, var10, par1Vec3, par2Vec3);

                    if (var14 != null)
                    {
                        return var14;
                    }
                }

                var11 = 200;

                while (var11-- >= 0)
                {
                    if (Double.isNaN(par1Vec3.xCoord) || Double.isNaN(par1Vec3.yCoord) || Double.isNaN(par1Vec3.zCoord))
                    {
                        return null;
                    }

                    if (var8 == var5 && var9 == var6 && var10 == var7)
                    {
                        return null;
                    }

                    boolean var39 = true;
                    boolean var40 = true;
                    boolean var41 = true;
                    double var15 = 999.0D;
                    double var17 = 999.0D;
                    double var19 = 999.0D;

                    if (var5 > var8)
                    {
                        var15 = (double)var8 + 1.0D;
                    }
                    else if (var5 < var8)
                    {
                        var15 = (double)var8 + 0.0D;
                    }
                    else
                    {
                        var39 = false;
                    }

                    if (var6 > var9)
                    {
                        var17 = (double)var9 + 1.0D;
                    }
                    else if (var6 < var9)
                    {
                        var17 = (double)var9 + 0.0D;
                    }
                    else
                    {
                        var40 = false;
                    }

                    if (var7 > var10)
                    {
                        var19 = (double)var10 + 1.0D;
                    }
                    else if (var7 < var10)
                    {
                        var19 = (double)var10 + 0.0D;
                    }
                    else
                    {
                        var41 = false;
                    }

                    double var21 = 999.0D;
                    double var23 = 999.0D;
                    double var25 = 999.0D;
                    double var27 = par2Vec3.xCoord - par1Vec3.xCoord;
                    double var29 = par2Vec3.yCoord - par1Vec3.yCoord;
                    double var31 = par2Vec3.zCoord - par1Vec3.zCoord;

                    if (var39)
                    {
                        var21 = (var15 - par1Vec3.xCoord) / var27;
                    }

                    if (var40)
                    {
                        var23 = (var17 - par1Vec3.yCoord) / var29;
                    }

                    if (var41)
                    {
                        var25 = (var19 - par1Vec3.zCoord) / var31;
                    }

                    boolean var33 = false;
                    byte var42;

                    if (var21 < var23 && var21 < var25)
                    {
                        if (var5 > var8)
                        {
                            var42 = 4;
                        }
                        else
                        {
                            var42 = 5;
                        }

                        par1Vec3.xCoord = var15;
                        par1Vec3.yCoord += var29 * var21;
                        par1Vec3.zCoord += var31 * var21;
                    }
                    else if (var23 < var25)
                    {
                        if (var6 > var9)
                        {
                            var42 = 0;
                        }
                        else
                        {
                            var42 = 1;
                        }

                        par1Vec3.xCoord += var27 * var23;
                        par1Vec3.yCoord = var17;
                        par1Vec3.zCoord += var31 * var23;
                    }
                    else
                    {
                        if (var7 > var10)
                        {
                            var42 = 2;
                        }
                        else
                        {
                            var42 = 3;
                        }

                        par1Vec3.xCoord += var27 * var25;
                        par1Vec3.yCoord += var29 * var25;
                        par1Vec3.zCoord = var19;
                    }

                    Vec3 var34 = this.getWorldVec3Pool().getVecFromPool(par1Vec3.xCoord, par1Vec3.yCoord, par1Vec3.zCoord);
                    var8 = (int)(var34.xCoord = (double)MathHelper.floor_double(par1Vec3.xCoord));

                    if (var42 == 5)
                    {
                        --var8;
                        ++var34.xCoord;
                    }

                    var9 = (int)(var34.yCoord = (double)MathHelper.floor_double(par1Vec3.yCoord));

                    if (var42 == 1)
                    {
                        --var9;
                        ++var34.yCoord;
                    }

                    var10 = (int)(var34.zCoord = (double)MathHelper.floor_double(par1Vec3.zCoord));

                    if (var42 == 3)
                    {
                        --var10;
                        ++var34.zCoord;
                    }

                    int var35 = this.getBlockId(var8, var9, var10);
                    int var36 = this.getBlockMetadata(var8, var9, var10);
                    Block var37 = Block.blocksList[var35];

                    if ((!par4 || var37 == null || var37.getCollisionBoundingBoxFromPool(this, var8, var9, var10) != null) && var35 > 0 && var37.canCollideCheck(var36, par3))
                    {
                        MovingObjectPosition var38 = var37.collisionRayTrace(this, var8, var9, var10, par1Vec3, par2Vec3);

                        if (var38 != null)
                        {
                            return var38;
                        }
                    }
                }

                return null;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    */
    // END FCMOD

    /**
     * Plays a sound at the entity's position. Args: entity, sound, volume (relative to 1.0), and frequency (or pitch,
     * also relative to 1.0).
     */
    public void playSoundAtEntity(Entity par1Entity, String par2Str, float par3, float par4)
    {
        if (par1Entity != null && par2Str != null)
        {
            for (int var5 = 0; var5 < this.worldAccesses.size(); ++var5)
            {
                ((IWorldAccess)this.worldAccesses.get(var5)).playSound(par2Str, par1Entity.posX, par1Entity.posY - (double)par1Entity.yOffset, par1Entity.posZ, par3, par4);
            }
        }
    }

    /**
     * Plays sound to all near players except the player reference given
     */
    public void playSoundToNearExcept(EntityPlayer par1EntityPlayer, String par2Str, float par3, float par4)
    {
        if (par1EntityPlayer != null && par2Str != null)
        {
            for (int var5 = 0; var5 < this.worldAccesses.size(); ++var5)
            {
                ((IWorldAccess)this.worldAccesses.get(var5)).playSoundToNearExcept(par1EntityPlayer, par2Str, par1EntityPlayer.posX, par1EntityPlayer.posY - (double)par1EntityPlayer.yOffset, par1EntityPlayer.posZ, par3, par4);
            }
        }
    }

    /**
     * Play a sound effect. Many many parameters for this function. Not sure what they do, but a classic call is :
     * (double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, 'random.door_open', 1.0F, world.rand.nextFloat() * 0.1F +
     * 0.9F with i,j,k position of the block.
     */
    public void playSoundEffect(double par1, double par3, double par5, String par7Str, float par8, float par9)
    {
        if (par7Str != null)
        {
            for (int var10 = 0; var10 < this.worldAccesses.size(); ++var10)
            {
                ((IWorldAccess)this.worldAccesses.get(var10)).playSound(par7Str, par1, par3, par5, par8, par9);
            }
        }
    }

    /**
     * par8 is loudness, all pars passed to minecraftInstance.sndManager.playSound
     */
    public void playSound(double par1, double par3, double par5, String par7Str, float par8, float par9, boolean par10) {}

    /**
     * Plays a record at the specified coordinates of the specified name. Args: recordName, x, y, z
     */
    public void playRecord(String par1Str, int par2, int par3, int par4)
    {
        for (int var5 = 0; var5 < this.worldAccesses.size(); ++var5)
        {
            ((IWorldAccess)this.worldAccesses.get(var5)).playRecord(par1Str, par2, par3, par4);
        }
    }

    /**
     * Spawns a particle.  Args particleName, x, y, z, velX, velY, velZ
     */
    public void spawnParticle(String par1Str, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        for (int var14 = 0; var14 < this.worldAccesses.size(); ++var14)
        {
            ((IWorldAccess)this.worldAccesses.get(var14)).spawnParticle(par1Str, par2, par4, par6, par8, par10, par12);
        }
    }

    /**
     * adds a lightning bolt to the list of lightning bolts in this world.
     */
    public boolean addWeatherEffect(Entity par1Entity)
    {
        this.weatherEffects.add(par1Entity);
        return true;
    }

    /**
     * Called to place all entities as part of a world
     */
    public boolean spawnEntityInWorld(Entity entity) {
        int var2 = MathHelper.floor_double(entity.posX / 16.0D);
        int var3 = MathHelper.floor_double(entity.posZ / 16.0D);
        boolean var4 = entity.field_98038_p;

        if (entity instanceof EntityPlayer)
        {
            var4 = true;
        }

        if (!var4 && !this.chunkExists(var2, var3))
        {
            return false;
        }
        else
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer var5 = (EntityPlayer)entity;
                this.playerEntities.add(var5);
                this.updateAllPlayersSleepingFlag();
            }

            this.getChunkFromChunkCoords(var2, var3).addEntity(entity);
            this.loadedEntityList.add(entity);
            this.obtainEntitySkin(entity);
            return true;
        }
    }

    /**
     * Start the skin for this entity downloading, if necessary, and increment its reference counter
     */
    protected void obtainEntitySkin(Entity par1Entity)
    {
        for (int var2 = 0; var2 < this.worldAccesses.size(); ++var2)
        {
            ((IWorldAccess)this.worldAccesses.get(var2)).onEntityCreate(par1Entity);
        }
    }

    /**
     * Decrement the reference counter for this entity's skin image data
     */
    protected void releaseEntitySkin(Entity par1Entity)
    {
        for (int var2 = 0; var2 < this.worldAccesses.size(); ++var2)
        {
            ((IWorldAccess)this.worldAccesses.get(var2)).onEntityDestroy(par1Entity);
        }
    }

    /**
     * Schedule the entity for removal during the next tick. Marks the entity dead in anticipation.
     */
    public void removeEntity(Entity par1Entity)
    {
        if (par1Entity.riddenByEntity != null)
        {
            par1Entity.riddenByEntity.mountEntity((Entity)null);
        }

        if (par1Entity.ridingEntity != null)
        {
            par1Entity.mountEntity((Entity)null);
        }

        par1Entity.setDead();

        if (par1Entity instanceof EntityPlayer)
        {
            this.playerEntities.remove(par1Entity);
            this.updateAllPlayersSleepingFlag();
        }
    }

    /**
     * Do NOT use this method to remove normal entities- use normal removeEntity
     */
    public void removePlayerEntityDangerously(Entity par1Entity)
    {
        par1Entity.setDead();

        if (par1Entity instanceof EntityPlayer)
        {
            this.playerEntities.remove(par1Entity);
            this.updateAllPlayersSleepingFlag();
        }

        int var2 = par1Entity.chunkCoordX;
        int var3 = par1Entity.chunkCoordZ;

        if (par1Entity.addedToChunk && this.chunkExists(var2, var3))
        {
            this.getChunkFromChunkCoords(var2, var3).removeEntity(par1Entity);
        }

        this.loadedEntityList.remove(par1Entity);
        this.releaseEntitySkin(par1Entity);
    }

    /**
     * Adds a IWorldAccess to the list of worldAccesses
     */
    public void addWorldAccess(IWorldAccess par1IWorldAccess)
    {
        this.worldAccesses.add(par1IWorldAccess);
    }

    /**
     * Returns a list of bounding boxes that collide with aabb excluding the passed in entity's collision. Args: entity,
     * aabb
     */
    public List getCollidingBoundingBoxes(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB)
    {
        this.collidingBoundingBoxes.clear();
        int var3 = MathHelper.floor_double(par2AxisAlignedBB.minX);
        int var4 = MathHelper.floor_double(par2AxisAlignedBB.maxX + 1.0D);
        int var5 = MathHelper.floor_double(par2AxisAlignedBB.minY);
        int var6 = MathHelper.floor_double(par2AxisAlignedBB.maxY + 1.0D);
        int var7 = MathHelper.floor_double(par2AxisAlignedBB.minZ);
        int var8 = MathHelper.floor_double(par2AxisAlignedBB.maxZ + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9)
        {
            for (int var10 = var7; var10 < var8; ++var10)
            {
                if (this.blockExists(var9, 64, var10))
                {
                    for (int var11 = var5 - 1; var11 < var6; ++var11)
                    {
                        Block var12 = Block.blocksList[this.getBlockId(var9, var11, var10)];

                        if (var12 != null)
                        {
                            var12.addCollisionBoxesToList(this, var9, var11, var10, par2AxisAlignedBB, this.collidingBoundingBoxes, par1Entity);
                        }
                    }
                }
            }
        }

        double var14 = 0.25D;
        // FCMOD: Line replaced. Expanded this to help with vertical collisions on Wind Mills
		/*
        List var16 = this.getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB.expand(var14, var14, var14));
		*/
        List var16 = this.getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB.expand(var14, 2.0, var14));
        // END FCMOD

        for (int var15 = 0; var15 < var16.size(); ++var15)
        {
        	// FCMOD: Code added
        	if ( !par1Entity.CanCollideWithEntity( (Entity)var16.get( var15 ) ) )
        	{
        		continue;
        	}
        	// END FCMOD
            AxisAlignedBB var13 = ((Entity)var16.get(var15)).getBoundingBox();

            if (var13 != null && var13.intersectsWith(par2AxisAlignedBB))
            {
                this.collidingBoundingBoxes.add(var13);
            }

            var13 = par1Entity.getCollisionBox((Entity)var16.get(var15));

            if (var13 != null && var13.intersectsWith(par2AxisAlignedBB))
            {
                this.collidingBoundingBoxes.add(var13);
            }
        }

        return this.collidingBoundingBoxes;
    }

    /**
     * calculates and returns a list of colliding bounding boxes within a given AABB
     */
    public List getCollidingBlockBounds(AxisAlignedBB par1AxisAlignedBB)
    {
        this.collidingBoundingBoxes.clear();
        int var2 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        for (int var8 = var2; var8 < var3; ++var8)
        {
            for (int var9 = var6; var9 < var7; ++var9)
            {
                if (this.blockExists(var8, 64, var9))
                {
                    for (int var10 = var4 - 1; var10 < var5; ++var10)
                    {
                        Block var11 = Block.blocksList[this.getBlockId(var8, var10, var9)];

                        if (var11 != null)
                        {
                            var11.addCollisionBoxesToList(this, var8, var10, var9, par1AxisAlignedBB, this.collidingBoundingBoxes, (Entity)null);
                        }
                    }
                }
            }
        }

        return this.collidingBoundingBoxes;
    }

    /**
     * Returns the amount of skylight subtracted for the current time
     */
    public int calculateSkylightSubtracted(float par1)
    {
        float var2 = this.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * (float)Math.PI * 2.0F) * 2.0F + 0.5F);

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        var3 = 1.0F - var3;
        var3 = (float)((double)var3 * (1.0D - (double)(this.getRainStrength(par1) * 5.0F) / 16.0D));
        var3 = (float)((double)var3 * (1.0D - (double)(this.getWeightedThunderStrength(par1) * 5.0F) / 16.0D));
        var3 = 1.0F - var3;
        return (int)(var3 * 11.0F);
    }

    /**
     * calls calculateCelestialAngle
     */
    public float getCelestialAngle(float par1)
    {
        return this.provider.calculateCelestialAngle(this.worldInfo.getWorldTime(), par1);
    }

    public int getMoonPhase()
    {
        return this.provider.getMoonPhase(this.worldInfo.getWorldTime());
    }

    /**
     * Return getCelestialAngle()*2*PI
     */
    public float getCelestialAngleRadians(float par1)
    {
        float var2 = this.getCelestialAngle(par1);
        return var2 * (float)Math.PI * 2.0F;
    }

    /**
     * Gets the height to which rain/snow will fall. Calculates it if not already stored.
     */
    public int getPrecipitationHeight(int par1, int par2)
    {
        return this.getChunkFromBlockCoords(par1, par2).getPrecipitationHeight(par1 & 15, par2 & 15);
    }
    
    /**
     * Finds the highest block on the x, z coordinate that is solid and returns its y coord. Args x, z
     * FCNOTE: Despite name, actually returns the block ABOVE the top one, and does not count liquids
     */
    public int getTopSolidOrLiquidBlock(int par1, int par2)
    {
        Chunk var3 = this.getChunkFromBlockCoords(par1, par2);
        int var4 = var3.getTopFilledSegment() + 15;
        par1 &= 15;

        for (par2 &= 15; var4 > 0; --var4)
        {
            int var5 = var3.getBlockID(par1, var4, par2);

            if (var5 != 0 && Block.blocksList[var5].blockMaterial.blocksMovement() && Block.blocksList[var5].blockMaterial != Material.leaves)
            {
                return var4 + 1;
            }
        }

        return -1;
    }

    /**
     * Used to schedule a call to the updateTick method on the specified block.
     */
    public void scheduleBlockUpdate(int par1, int par2, int par3, int par4, int par5) {}

    public void func_82740_a(int par1, int par2, int par3, int par4, int par5, int par6) {}

    /**
     * Schedules a block update from the saved information in a chunk. Called when the chunk is loaded.
     */
    public void scheduleBlockUpdateFromLoad(int par1, int par2, int par3, int par4, int par5, int par6) {}

    /**
     * Updates (and cleans up) entities and tile entities
     */
    public void updateEntities()
    {
        this.theProfiler.startSection("entities");
        this.theProfiler.startSection("global");
        int var1;
        Entity var2;
        CrashReport var4;
        CrashReportCategory var5;

        for (var1 = 0; var1 < this.weatherEffects.size(); ++var1)
        {
            var2 = (Entity)this.weatherEffects.get(var1);

            try
            {
                ++var2.ticksExisted;
                var2.onUpdate();
            }
            catch (Throwable var8)
            {
                var4 = CrashReport.makeCrashReport(var8, "Ticking entity");
                var5 = var4.makeCategory("Entity being ticked");

                if (var2 == null)
                {
                    var5.addCrashSection("Entity", "~~NULL~~");
                }
                else
                {
                    var2.func_85029_a(var5);
                }

                throw new ReportedException(var4);
            }

            if (var2.isDead)
            {
                this.weatherEffects.remove(var1--);
            }
        }

        this.theProfiler.endStartSection("remove");
        this.loadedEntityList.removeAll(this.unloadedEntityList);
        int var3;
        int var13;

        for (var1 = 0; var1 < this.unloadedEntityList.size(); ++var1)
        {
            var2 = (Entity)this.unloadedEntityList.get(var1);
            var3 = var2.chunkCoordX;
            var13 = var2.chunkCoordZ;

            if (var2.addedToChunk && this.chunkExists(var3, var13))
            {
                this.getChunkFromChunkCoords(var3, var13).removeEntity(var2);
            }
        }

        for (var1 = 0; var1 < this.unloadedEntityList.size(); ++var1)
        {
            this.releaseEntitySkin((Entity)this.unloadedEntityList.get(var1));
        }

        this.unloadedEntityList.clear();
        // FCMOD: Code added to prevent jerkiness when one entity pushes another (like on Platforms)
        for (int l = 0; l < loadedEntityList.size(); l++)
        {
            Entity tempEntity = (Entity)loadedEntityList.get(l);
            
            if (!tempEntity.isDead)
            {
            	tempEntity.lastTickPosX = tempEntity.posX;
            	tempEntity.lastTickPosY = tempEntity.posY;
            	tempEntity.lastTickPosZ = tempEntity.posZ;        
            	tempEntity.prevRotationYaw = tempEntity.rotationYaw;
            	tempEntity.prevRotationPitch = tempEntity.rotationPitch;
            }
        }        
        // END FCMOD
        this.theProfiler.endStartSection("regular");

        for (var1 = 0; var1 < this.loadedEntityList.size(); ++var1)
        {
            var2 = (Entity)this.loadedEntityList.get(var1);

            if (var2.ridingEntity != null)
            {
                if (!var2.ridingEntity.isDead && var2.ridingEntity.riddenByEntity == var2)
                {
                    continue;
                }

                var2.ridingEntity.riddenByEntity = null;
                var2.ridingEntity = null;
            }

            this.theProfiler.startSection("tick");

            if (!var2.isDead)
            {
                try
                {
                    this.updateEntity(var2);
                }
                catch (Throwable var7)
                {
                    var4 = CrashReport.makeCrashReport(var7, "Ticking entity");
                    var5 = var4.makeCategory("Entity being ticked");
                    var2.func_85029_a(var5);
                    throw new ReportedException(var4);
                }
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("remove");

            if (var2.isDead)
            {
                var3 = var2.chunkCoordX;
                var13 = var2.chunkCoordZ;

                if (var2.addedToChunk && this.chunkExists(var3, var13))
                {
                    this.getChunkFromChunkCoords(var3, var13).removeEntity(var2);
                }

                this.loadedEntityList.remove(var1--);
                this.releaseEntitySkin(var2);
            }

            this.theProfiler.endSection();
        }

        this.theProfiler.endStartSection("tileEntities");
        this.scanningTileEntities = true;
        Iterator var14 = this.loadedTileEntityList.iterator();

        while (var14.hasNext())
        {
            TileEntity var9 = (TileEntity)var14.next();

        	// FCCHUNK: Decide on updates around original spawn
            // FCMOD: Changed
            //if (!var9.isInvalid() && var9.func_70309_m() && this.blockExists(var9.xCoord, var9.yCoord, var9.zCoord))
            if ( !var9.isInvalid() && var9.func_70309_m() && 
            	IsBlockPosActive( var9.xCoord, var9.yCoord, var9.zCoord ) )            	            	
        	// END FCMOD
            {
                try
                {
                    var9.updateEntity();
                }
                catch (Throwable var6)
                {
                    var4 = CrashReport.makeCrashReport(var6, "Ticking tile entity");
                    var5 = var4.makeCategory("Tile entity being ticked");
                    var9.func_85027_a(var5);
                    throw new ReportedException(var4);
                }
            }

            if (var9.isInvalid())
            {
                var14.remove();

                if (this.chunkExists(var9.xCoord >> 4, var9.zCoord >> 4))
                {
                    Chunk var11 = this.getChunkFromChunkCoords(var9.xCoord >> 4, var9.zCoord >> 4);

                    if (var11 != null)
                    {
                        var11.removeChunkBlockTileEntity(var9.xCoord & 15, var9.yCoord, var9.zCoord & 15);
                    }
                }
            }
        }

        this.scanningTileEntities = false;

        if (!this.entityRemoval.isEmpty())
        {
            this.loadedTileEntityList.removeAll(this.entityRemoval);
            this.entityRemoval.clear();
        }

        this.theProfiler.endStartSection("pendingTileEntities");

        if (!this.addedTileEntityList.isEmpty())
        {
            for (int var10 = 0; var10 < this.addedTileEntityList.size(); ++var10)
            {
                TileEntity var12 = (TileEntity)this.addedTileEntityList.get(var10);

                if (!var12.isInvalid())
                {
                    if (!this.loadedTileEntityList.contains(var12))
                    {
                        this.loadedTileEntityList.add(var12);
                    }

                    if (this.chunkExists(var12.xCoord >> 4, var12.zCoord >> 4))
                    {
                        Chunk var15 = this.getChunkFromChunkCoords(var12.xCoord >> 4, var12.zCoord >> 4);

                        if (var15 != null)
                        {
                            var15.setChunkBlockTileEntity(var12.xCoord & 15, var12.yCoord, var12.zCoord & 15, var12);
                        }
                    }

                    this.markBlockForUpdate(var12.xCoord, var12.yCoord, var12.zCoord);
                }
            }

            this.addedTileEntityList.clear();
        }

        this.theProfiler.endSection();
        this.theProfiler.endSection();
    }

    public void addTileEntity(Collection par1Collection)
    {
        if (this.scanningTileEntities)
        {
            this.addedTileEntityList.addAll(par1Collection);
        }
        else
        {
            this.loadedTileEntityList.addAll(par1Collection);
        }
    }

    /**
     * Will update the entity in the world if the chunk the entity is in is currently loaded. Args: entity
     */
    public void updateEntity(Entity par1Entity)
    {
        this.updateEntityWithOptionalForce(par1Entity, true);
    }

    /**
     * Will update the entity in the world if the chunk the entity is in is currently loaded or its forced to update.
     * Args: entity, forceUpdate
     * FCNOTE: 2nd parameter is more "bOnlyUpdateInLoadedChunks" rather than what is said above
     * The "OptionalForce" in the function name refers to forcing the update, not a physical force
     */
    public void updateEntityWithOptionalForce(Entity par1Entity, boolean par2)
    {
        int var3 = MathHelper.floor_double(par1Entity.posX);
        int var4 = MathHelper.floor_double(par1Entity.posZ);
        // FCMOD: Changed
        //byte var5 = 32;
        int var5 = m_iLoadedChunksUpdateRange;
        // END FCMOD

    	// FCCHUNK: Decide on updates around original spawn
        // FCMOD: Changed
        //if (!par2 || this.checkChunksExist(var3 - var5, 0, var4 - var5, var3 + var5, 0, var4 + var5))
        if ( !par2 || IsBlockPosActive( var3, 0, var4 ) )
        	// END FCMOD
        {
            // FCMOD: Removed and moved to a pre-update loop elsewhere in this file
            /*
            par1Entity.lastTickPosX = par1Entity.posX;
            par1Entity.lastTickPosY = par1Entity.posY;
            par1Entity.lastTickPosZ = par1Entity.posZ;
            par1Entity.prevRotationYaw = par1Entity.rotationYaw;
            par1Entity.prevRotationPitch = par1Entity.rotationPitch;
		    */
		    // END FCMOD

            if (par2 && par1Entity.addedToChunk)
            {
                if (par1Entity.ridingEntity != null)
                {
                    par1Entity.updateRidden();
                }
                else
                {
                    ++par1Entity.ticksExisted;
                    par1Entity.onUpdate();
                }
            }

            this.theProfiler.startSection("chunkCheck");

            if (Double.isNaN(par1Entity.posX) || Double.isInfinite(par1Entity.posX))
            {
                par1Entity.posX = par1Entity.lastTickPosX;
            }

            if (Double.isNaN(par1Entity.posY) || Double.isInfinite(par1Entity.posY))
            {
                par1Entity.posY = par1Entity.lastTickPosY;
            }

            if (Double.isNaN(par1Entity.posZ) || Double.isInfinite(par1Entity.posZ))
            {
                par1Entity.posZ = par1Entity.lastTickPosZ;
            }

            if (Double.isNaN((double)par1Entity.rotationPitch) || Double.isInfinite((double)par1Entity.rotationPitch))
            {
                par1Entity.rotationPitch = par1Entity.prevRotationPitch;
            }

            if (Double.isNaN((double)par1Entity.rotationYaw) || Double.isInfinite((double)par1Entity.rotationYaw))
            {
                par1Entity.rotationYaw = par1Entity.prevRotationYaw;
            }

            int var6 = MathHelper.floor_double(par1Entity.posX / 16.0D);
            int var7 = MathHelper.floor_double(par1Entity.posY / 16.0D);
            int var8 = MathHelper.floor_double(par1Entity.posZ / 16.0D);

            if (!par1Entity.addedToChunk || par1Entity.chunkCoordX != var6 || par1Entity.chunkCoordY != var7 || par1Entity.chunkCoordZ != var8)
            {
                if (par1Entity.addedToChunk && this.chunkExists(par1Entity.chunkCoordX, par1Entity.chunkCoordZ))
                {
                    this.getChunkFromChunkCoords(par1Entity.chunkCoordX, par1Entity.chunkCoordZ).removeEntityAtIndex(par1Entity, par1Entity.chunkCoordY);
                }

                if (this.chunkExists(var6, var8))
                {
                    par1Entity.addedToChunk = true;
                    this.getChunkFromChunkCoords(var6, var8).addEntity(par1Entity);
                }
                else
                {
                    par1Entity.addedToChunk = false;
                }
            }

            this.theProfiler.endSection();

            if (par2 && par1Entity.addedToChunk && par1Entity.riddenByEntity != null)
            {
                if (!par1Entity.riddenByEntity.isDead && par1Entity.riddenByEntity.ridingEntity == par1Entity)
                {
                    this.updateEntity(par1Entity.riddenByEntity);
                }
                else
                {
                    par1Entity.riddenByEntity.ridingEntity = null;
                    par1Entity.riddenByEntity = null;
                }
            }
        }
    }

    /**
     * Returns true if there are no solid, live entities in the specified AxisAlignedBB
     */
    public boolean checkNoEntityCollision(AxisAlignedBB par1AxisAlignedBB)
    {
        return this.checkNoEntityCollision(par1AxisAlignedBB, (Entity)null);
    }

    /**
     * Returns true if there are no solid, live entities in the specified AxisAlignedBB, excluding the given entity
     */
    public boolean checkNoEntityCollision(AxisAlignedBB par1AxisAlignedBB, Entity par2Entity)
    {
        List var3 = this.getEntitiesWithinAABBExcludingEntity((Entity)null, par1AxisAlignedBB);

        for (int var4 = 0; var4 < var3.size(); ++var4)
        {
            Entity var5 = (Entity)var3.get(var4);

            if (!var5.isDead && var5.preventEntitySpawning && var5 != par2Entity)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if there are any blocks in the region constrained by an AxisAlignedBB
     */
    public boolean checkBlockCollision(AxisAlignedBB par1AxisAlignedBB)
    {
        int var2 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        if (par1AxisAlignedBB.minX < 0.0D)
        {
            --var2;
        }

        if (par1AxisAlignedBB.minY < 0.0D)
        {
            --var4;
        }

        if (par1AxisAlignedBB.minZ < 0.0D)
        {
            --var6;
        }

        for (int var8 = var2; var8 < var3; ++var8)
        {
            for (int var9 = var4; var9 < var5; ++var9)
            {
                for (int var10 = var6; var10 < var7; ++var10)
                {
                    Block var11 = Block.blocksList[this.getBlockId(var8, var9, var10)];

                    if (var11 != null)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Returns if any of the blocks within the aabb are liquids. Args: aabb
     */
    public boolean isAnyLiquid(AxisAlignedBB par1AxisAlignedBB)
    {
        int var2 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        if (par1AxisAlignedBB.minX < 0.0D)
        {
            --var2;
        }

        if (par1AxisAlignedBB.minY < 0.0D)
        {
            --var4;
        }

        if (par1AxisAlignedBB.minZ < 0.0D)
        {
            --var6;
        }

        for (int var8 = var2; var8 < var3; ++var8)
        {
            for (int var9 = var4; var9 < var5; ++var9)
            {
                for (int var10 = var6; var10 < var7; ++var10)
                {
                    Block var11 = Block.blocksList[this.getBlockId(var8, var9, var10)];

                    if (var11 != null && var11.blockMaterial.isLiquid())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Returns whether or not the given bounding box is on fire or not
     */
    // FCMOD: Changed
    //public boolean isBoundingBoxBurning(AxisAlignedBB par1AxisAlignedBB)
    public boolean isBoundingBoxBurning( Entity entity )
    // END FCMOD
    {
    	// FCMOD: Added
    	AxisAlignedBB par1AxisAlignedBB = entity.boundingBox.contract(0.001D, 0.001D, 0.001D);
    	// END FCMOD
    	
        int var2 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        if (this.checkChunksExist(var2, var4, var6, var3, var5, var7))
        {
            for (int var8 = var2; var8 < var3; ++var8)
            {
                for (int var9 = var4; var9 < var5; ++var9)
                {
                    for (int var10 = var6; var10 < var7; ++var10)
                    {
                        // FCMOD: Changed
						/*
                        int var11 = this.getBlockId(var8, var9, var10);

                        if (var11 == Block.fire.blockID || var11 == Block.lavaMoving.blockID || var11 == Block.lavaStill.blockID)
                        */
                        Block block = Block.blocksList[getBlockId(var8, var9, var10)];
                        
                        if ( block != null && block.GetDoesFireDamageToEntities( this, var8, var9, var10, entity ) )
                    	// END FCMOD
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * handles the acceleration of an object whilst in water. Not sure if it is used elsewhere.
     */
    public boolean handleMaterialAcceleration(AxisAlignedBB par1AxisAlignedBB, Material par2Material, Entity par3Entity)
    {
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var8 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var9 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        if (!this.checkChunksExist(var4, var6, var8, var5, var7, var9))
        {
            return false;
        }
        else
        {
            boolean var10 = false;
            Vec3 var11 = this.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);

            for (int var12 = var4; var12 < var5; ++var12)
            {
                for (int var13 = var6; var13 < var7; ++var13)
                {
                    for (int var14 = var8; var14 < var9; ++var14)
                    {
                        Block var15 = Block.blocksList[this.getBlockId(var12, var13, var14)];

                        if (var15 != null && var15.blockMaterial == par2Material)
                        {
                            double var16 = (double)((float)(var13 + 1) - BlockFluid.getFluidHeightPercent(this.getBlockMetadata(var12, var13, var14)));

                            if ((double)var7 >= var16)
                            {
                                var10 = true;
                                var15.velocityToAddToEntity(this, var12, var13, var14, par3Entity, var11);
                            }
                        }
                    }
                }
            }

            if (var11.lengthVector() > 0.0D && par3Entity.func_96092_aw())
            {
                var11 = var11.normalize();
                double var18 = 0.014D;
                par3Entity.motionX += var11.xCoord * var18;
                par3Entity.motionY += var11.yCoord * var18;
                par3Entity.motionZ += var11.zCoord * var18;
            }

            return var10;
        }
    }

    /**
     * Returns true if the given bounding box contains the given material
     */
    public boolean isMaterialInBB(AxisAlignedBB par1AxisAlignedBB, Material par2Material)
    {
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var8 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9)
        {
            for (int var10 = var5; var10 < var6; ++var10)
            {
                for (int var11 = var7; var11 < var8; ++var11)
                {
                    Block var12 = Block.blocksList[this.getBlockId(var9, var10, var11)];

                    if (var12 != null && var12.blockMaterial == par2Material)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * checks if the given AABB is in the material given. Used while swimming.
     */
    public boolean isAABBInMaterial(AxisAlignedBB par1AxisAlignedBB, Material par2Material)
    {
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.maxX + 1.0D);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY + 1.0D);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var8 = MathHelper.floor_double(par1AxisAlignedBB.maxZ + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9)
        {
            for (int var10 = var5; var10 < var6; ++var10)
            {
                for (int var11 = var7; var11 < var8; ++var11)
                {
                    Block var12 = Block.blocksList[this.getBlockId(var9, var10, var11)];

                    if (var12 != null && var12.blockMaterial == par2Material)
                    {
                        int var13 = this.getBlockMetadata(var9, var10, var11);
                        double var14 = (double)(var10 + 1);

                        if (var13 < 8)
                        {
                            var14 = (double)(var10 + 1) - (double)var13 / 8.0D;
                        }

                        if (var14 >= par1AxisAlignedBB.minY)
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Creates an explosion. Args: entity, x, y, z, strength
     */
    public Explosion createExplosion(Entity par1Entity, double par2, double par4, double par6, float par8, boolean par9)
    {
        return this.newExplosion(par1Entity, par2, par4, par6, par8, false, par9);
    }

    /**
     * returns a new explosion. Does initiation (at time of writing Explosion is not finished)
     */
    public Explosion newExplosion(Entity par1Entity, double par2, double par4, double par6, float par8, boolean par9, boolean par10)
    {
        Explosion var11 = new Explosion(this, par1Entity, par2, par4, par6, par8);
        var11.isFlaming = par9;
        var11.isSmoking = par10;
        var11.doExplosionA();
        var11.doExplosionB(true);
        return var11;
    }

    /**
     * Gets the percentage of real blocks within within a bounding box, along a specified vector.
     */
    public float getBlockDensity(Vec3 par1Vec3, AxisAlignedBB par2AxisAlignedBB)
    {
        double var3 = 1.0D / ((par2AxisAlignedBB.maxX - par2AxisAlignedBB.minX) * 2.0D + 1.0D);
        double var5 = 1.0D / ((par2AxisAlignedBB.maxY - par2AxisAlignedBB.minY) * 2.0D + 1.0D);
        double var7 = 1.0D / ((par2AxisAlignedBB.maxZ - par2AxisAlignedBB.minZ) * 2.0D + 1.0D);
        int var9 = 0;
        int var10 = 0;

        for (float var11 = 0.0F; var11 <= 1.0F; var11 = (float)((double)var11 + var3))
        {
            for (float var12 = 0.0F; var12 <= 1.0F; var12 = (float)((double)var12 + var5))
            {
                for (float var13 = 0.0F; var13 <= 1.0F; var13 = (float)((double)var13 + var7))
                {
                    double var14 = par2AxisAlignedBB.minX + (par2AxisAlignedBB.maxX - par2AxisAlignedBB.minX) * (double)var11;
                    double var16 = par2AxisAlignedBB.minY + (par2AxisAlignedBB.maxY - par2AxisAlignedBB.minY) * (double)var12;
                    double var18 = par2AxisAlignedBB.minZ + (par2AxisAlignedBB.maxZ - par2AxisAlignedBB.minZ) * (double)var13;

                    if (this.rayTraceBlocks(this.getWorldVec3Pool().getVecFromPool(var14, var16, var18), par1Vec3) == null)
                    {
                        ++var9;
                    }

                    ++var10;
                }
            }
        }

        return (float)var9 / (float)var10;
    }

    /**
     * If the block in the given direction of the given coordinate is fire, extinguish it. Args: Player, X,Y,Z,
     * blockDirection
     */
    public boolean extinguishFire(EntityPlayer par1EntityPlayer, int par2, int par3, int par4, int par5)
    {
        if (par5 == 0)
        {
            --par3;
        }

        if (par5 == 1)
        {
            ++par3;
        }

        if (par5 == 2)
        {
            --par4;
        }

        if (par5 == 3)
        {
            ++par4;
        }

        if (par5 == 4)
        {
            --par2;
        }

        if (par5 == 5)
        {
            ++par2;
        }

        if (this.getBlockId(par2, par3, par4) == Block.fire.blockID)
        {
        	// FCMOD: Code change to fix fizz effect
        	/*
            this.playAuxSFXAtEntity(par1EntityPlayer, 1004, par2, par3, par4, 0);
            */
            playAuxSFX( 1004, par2, par3, par4, 0);
            // END FCMOD
            this.setBlockToAir(par2, par3, par4);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the TileEntity associated with a given block in X,Y,Z coordinates, or null if no TileEntity exists
     */
    public TileEntity getBlockTileEntity(int par1, int par2, int par3)
    {
        if (par2 >= 0 && par2 < 256)
        {
            TileEntity var4 = null;
            int var5;
            TileEntity var6;

            if (this.scanningTileEntities)
            {
                for (var5 = 0; var5 < this.addedTileEntityList.size(); ++var5)
                {
                    var6 = (TileEntity)this.addedTileEntityList.get(var5);

                    if (!var6.isInvalid() && var6.xCoord == par1 && var6.yCoord == par2 && var6.zCoord == par3)
                    {
                        var4 = var6;
                        break;
                    }
                }
            }

            if (var4 == null)
            {
                Chunk var7 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);

                if (var7 != null)
                {
                    var4 = var7.getChunkBlockTileEntity(par1 & 15, par2, par3 & 15);
                }
            }

            if (var4 == null)
            {
                for (var5 = 0; var5 < this.addedTileEntityList.size(); ++var5)
                {
                    var6 = (TileEntity)this.addedTileEntityList.get(var5);

                    if (!var6.isInvalid() && var6.xCoord == par1 && var6.yCoord == par2 && var6.zCoord == par3)
                    {
                        var4 = var6;
                        break;
                    }
                }
            }

            return var4;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the TileEntity for a given block in X, Y, Z coordinates
     */
    public void setBlockTileEntity(int par1, int par2, int par3, TileEntity par4TileEntity)
    {
        if (par4TileEntity != null && !par4TileEntity.isInvalid())
        {
            if (this.scanningTileEntities)
            {
                par4TileEntity.xCoord = par1;
                par4TileEntity.yCoord = par2;
                par4TileEntity.zCoord = par3;
                Iterator var5 = this.addedTileEntityList.iterator();

                while (var5.hasNext())
                {
                    TileEntity var6 = (TileEntity)var5.next();

                    if (var6.xCoord == par1 && var6.yCoord == par2 && var6.zCoord == par3)
                    {
                        var6.invalidate();
                        var5.remove();
                    }
                }

                this.addedTileEntityList.add(par4TileEntity);
            }
            else
            {
                this.loadedTileEntityList.add(par4TileEntity);
                Chunk var7 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);

                if (var7 != null)
                {
                    var7.setChunkBlockTileEntity(par1 & 15, par2, par3 & 15, par4TileEntity);
                }
            }
        }
    }

    /**
     * Removes the TileEntity for a given block in X,Y,Z coordinates
     */
    public void removeBlockTileEntity(int par1, int par2, int par3)
    {
        TileEntity var4 = this.getBlockTileEntity(par1, par2, par3);

        if (var4 != null && this.scanningTileEntities)
        {
            var4.invalidate();
            this.addedTileEntityList.remove(var4);
        }
        else
        {
            if (var4 != null)
            {
                this.addedTileEntityList.remove(var4);
                this.loadedTileEntityList.remove(var4);
            }

            Chunk var5 = this.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);

            if (var5 != null)
            {
                var5.removeChunkBlockTileEntity(par1 & 15, par2, par3 & 15);
            }
        }
    }

    /**
     * Adds TileEntity to despawn list
     */
    public void markTileEntityForDespawn(TileEntity par1TileEntity)
    {
        this.entityRemoval.add(par1TileEntity);
    }

    /**
     * Returns true if the block at the specified coordinates is an opaque cube. Args: x, y, z
     */
    public boolean isBlockOpaqueCube(int par1, int par2, int par3)
    {
        Block var4 = Block.blocksList[this.getBlockId(par1, par2, par3)];
        return var4 == null ? false : var4.isOpaqueCube();
    }

    /**
     * Returns true if the block at the specified coordinates is an opaque cube. Args: x, y, z
     */
    public boolean isBlockNormalCube(int par1, int par2, int par3)
    {
    	// FCMOD: Changed
        //return Block.isNormalCube(this.getBlockId(par1, par2, par3));
    	int iBlockID = getBlockId( par1, par2, par3 );

        Block block = Block.blocksList[iBlockID];

        if( block != null)
        {
			return block.IsNormalCube( this, par1, par2, par3 );
        }
        
        return false;
        // END FCMOD
    }

    public boolean func_85174_u(int par1, int par2, int par3)
    {
        int var4 = this.getBlockId(par1, par2, par3);

        if (var4 != 0 && Block.blocksList[var4] != null)
        {
            AxisAlignedBB var5 = Block.blocksList[var4].getCollisionBoundingBoxFromPool(this, par1, par2, par3);
            return var5 != null && var5.getAverageEdgeLength() >= 1.0D;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns true if the block at the given coordinate has a solid (buildable) top surface.
     */
    // FCMOD: Removed (replaced later)
    /*
    public boolean doesBlockHaveSolidTopSurface(int par1, int par2, int par3)
    {
        Block var4 = Block.blocksList[this.getBlockId(par1, par2, par3)];
        return this.isBlockTopFacingSurfaceSolid(var4, this.getBlockMetadata(par1, par2, par3));
    }
    */
    // END FCMOD

    /**
     * Performs check to see if the block is a normal, solid block, or if the metadata of the block indicates that its
     * facing puts its solid side upwards. (inverted stairs, for example)
     */
    // FCMOD: Removed (deprecated)
    /*
    public boolean isBlockTopFacingSurfaceSolid(Block par1Block, int par2)
    {
        return par1Block == null ? false : (par1Block.blockMaterial.isOpaque() && par1Block.renderAsNormalBlock() ? true : (par1Block instanceof BlockStairs ? (par2 & 4) == 4 : (par1Block instanceof BlockHalfSlab ? (par2 & 8) == 8 : (par1Block instanceof BlockHopper ? true : (par1Block instanceof BlockSnow ? (par2 & 7) == 7 : false)))));
    }
    */
    // END FCMOD

    /**
     * Checks if the block is a solid, normal cube. If the chunk does not exist, or is not loaded, it returns the
     * boolean parameter.
     */
    public boolean isBlockNormalCubeDefault(int par1, int par2, int par3, boolean par4)
    {
        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
        {
            Chunk var5 = this.chunkProvider.provideChunk(par1 >> 4, par3 >> 4);

            if (var5 != null && !var5.isEmpty())
            {
                Block var6 = Block.blocksList[this.getBlockId(par1, par2, par3)];
                // FCMOD: Change so that torches will stick to the appropriate blocks
                /*
                return var6 == null ? false : var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock();
                */
                return ( var6 != null && isBlockNormalCube( par1, par2, par3 ) );
                // END FCMOD                
            }
            else
            {
                return par4;
            }
        }
        else
        {
            return par4;
        }
    }

    /**
     * Called on construction of the World class to setup the initial skylight values
     */
    public void calculateInitialSkylight()
    {
        int var1 = this.calculateSkylightSubtracted(1.0F);

        if (var1 != this.skylightSubtracted)
        {
            this.skylightSubtracted = var1;
        }
    }

    /**
     * first boolean for hostile mobs and second for peaceful mobs
     */
    public void setAllowedSpawnTypes(boolean par1, boolean par2)
    {
    	// FCMOD: Code change to remove peaceful difficulty.  If animals can spawn, then so can mobs
    	/*
        this.spawnHostileMobs = par1;
        */
        this.spawnHostileMobs = par2;
    	// END FCMOD
        this.spawnPeacefulMobs = par2;
    }

    /**
     * Runs a single tick for the world
     */
    public void tick()
    {
    	// FCMOD: Added
    	UpdateActiveChunkMap();
    	// END FCMOD
    	
        this.updateWeather();
        
        // FCMOD: Added
        ModSpecificTick();
        // END FCMOD
    }

    /**
     * Called from World constructor to set rainingStrength and thunderingStrength
     */
    private void calculateInitialWeather()
    {
        if (this.worldInfo.isRaining())
        {
            this.rainingStrength = 1.0F;

            if (this.worldInfo.isThundering())
            {
                this.thunderingStrength = 1.0F;
            }
        }
    }

    /**
     * Updates all weather states.
     */
    // FCMOD: Code removed and replaced later
    /*
    protected void updateWeather()
    {
        if (!this.provider.hasNoSky)
        {
            int var1 = this.worldInfo.getThunderTime();

            if (var1 <= 0)
            {
                if (this.worldInfo.isThundering())
                {
                    this.worldInfo.setThunderTime(this.rand.nextInt(12000) + 3600);
                }
                else
                {
                    this.worldInfo.setThunderTime(this.rand.nextInt(168000) + 12000);
                }
            }
            else
            {
                --var1;
                this.worldInfo.setThunderTime(var1);

                if (var1 <= 0)
                {
                    this.worldInfo.setThundering(!this.worldInfo.isThundering());
                }
            }

            int var2 = this.worldInfo.getRainTime();

            if (var2 <= 0)
            {
                if (this.worldInfo.isRaining())
                {
                    this.worldInfo.setRainTime(this.rand.nextInt(12000) + 12000);
                }
                else
                {
                    this.worldInfo.setRainTime(this.rand.nextInt(168000) + 12000);
                }
            }
            else
            {
                --var2;
                this.worldInfo.setRainTime(var2);

                if (var2 <= 0)
                {
                    this.worldInfo.setRaining(!this.worldInfo.isRaining());
                }
            }

            this.prevRainingStrength = this.rainingStrength;

            if (this.worldInfo.isRaining())
            {
                this.rainingStrength = (float)((double)this.rainingStrength + 0.01D);
            }
            else
            {
                this.rainingStrength = (float)((double)this.rainingStrength - 0.01D);
            }

            if (this.rainingStrength < 0.0F)
            {
                this.rainingStrength = 0.0F;
            }

            if (this.rainingStrength > 1.0F)
            {
                this.rainingStrength = 1.0F;
            }

            this.prevThunderingStrength = this.thunderingStrength;

            if (this.worldInfo.isThundering())
            {
                this.thunderingStrength = (float)((double)this.thunderingStrength + 0.01D);
            }
            else
            {
                this.thunderingStrength = (float)((double)this.thunderingStrength - 0.01D);
            }

            if (this.thunderingStrength < 0.0F)
            {
                this.thunderingStrength = 0.0F;
            }

            if (this.thunderingStrength > 1.0F)
            {
                this.thunderingStrength = 1.0F;
            }
        }
    }
    */
    // END FCMOD

    /**
     * start precipitation in this world (2 ticks after command posted)
     */
    public void commandToggleDownfall()
    {
        this.worldInfo.setRainTime(1);
    }

    protected void setActivePlayerChunksAndCheckLight()
    {
    	// FCMOD: Changed with active chunk functionality replaced elsewhere
    	/*
        this.activeChunkSet.clear();
        this.theProfiler.startSection("buildList");
        int var1;
        EntityPlayer var2;
        int var3;
        int var4;

        for (var1 = 0; var1 < this.playerEntities.size(); ++var1)
        {
            var2 = (EntityPlayer)this.playerEntities.get(var1);
            var3 = MathHelper.floor_double(var2.posX / 16.0D);
            var4 = MathHelper.floor_double(var2.posZ / 16.0D);
            byte var5 = 7;

            for (int var6 = -var5; var6 <= var5; ++var6)
            {
                for (int var7 = -var5; var7 <= var5; ++var7)
                {
                    this.activeChunkSet.add(new ChunkCoordIntPair(var6 + var3, var7 + var4));
                }
            }
        }

        this.theProfiler.endSection();
        */
        int var1;
        EntityPlayer var2;
        int var3;
        int var4;    	
    	// END FCMOD

        if (this.ambientTickCountdown > 0)
        {
            --this.ambientTickCountdown;
        }

        this.theProfiler.startSection("playerCheckLight");

        if (!this.playerEntities.isEmpty())
        {
            var1 = this.rand.nextInt(this.playerEntities.size());
            var2 = (EntityPlayer)this.playerEntities.get(var1);
            var3 = MathHelper.floor_double(var2.posX) + this.rand.nextInt(11) - 5;
            var4 = MathHelper.floor_double(var2.posY) + this.rand.nextInt(11) - 5;
            int var8 = MathHelper.floor_double(var2.posZ) + this.rand.nextInt(11) - 5;
            this.updateAllLightTypes(var3, var4, var8);
        }

        this.theProfiler.endSection();
    }

    protected void moodSoundAndLightCheck(int par1, int par2, Chunk par3Chunk)
    {
        this.theProfiler.endStartSection("moodSound");

        if (this.ambientTickCountdown == 0 && !this.isRemote)
        {
            this.updateLCG = this.updateLCG * 3 + 1013904223;
            int var4 = this.updateLCG >> 2;
            int var5 = var4 & 15;
            int var6 = var4 >> 8 & 15;
            int var7 = var4 >> 16 & 127;
            int var8 = par3Chunk.getBlockID(var5, var7, var6);
            var5 += par1;
            var6 += par2;

            if (var8 == 0 && this.getFullBlockLightValue(var5, var7, var6) <= this.rand.nextInt(8) && this.getSavedLightValue(EnumSkyBlock.Sky, var5, var7, var6) <= 0)
            {
                EntityPlayer var9 = this.getClosestPlayer((double)var5 + 0.5D, (double)var7 + 0.5D, (double)var6 + 0.5D, 8.0D);

                if (var9 != null && var9.getDistanceSq((double)var5 + 0.5D, (double)var7 + 0.5D, (double)var6 + 0.5D) > 4.0D)
                {
                    this.playSoundEffect((double)var5 + 0.5D, (double)var7 + 0.5D, (double)var6 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.rand.nextFloat() * 0.2F);
                    this.ambientTickCountdown = this.rand.nextInt(12000) + 6000;
                }
            }
        }

        this.theProfiler.endStartSection("checkLight");
        par3Chunk.enqueueRelightChecks();
    }

    /**
     * plays random cave ambient sounds and runs updateTick on random blocks within each chunk in the vacinity of a
     * player
     */
    protected void tickBlocksAndAmbiance()
    {
        this.setActivePlayerChunksAndCheckLight();
    }

    /**
     * checks to see if a given block is both water and is cold enough to freeze
     */
    public boolean isBlockFreezable(int par1, int par2, int par3)
    {
        return this.canBlockFreeze(par1, par2, par3, false);
    }

    /**
     * checks to see if a given block is both water and has at least one immediately adjacent non-water block
     */
    public boolean isBlockFreezableNaturally(int par1, int par2, int par3)
    {
        return this.canBlockFreeze(par1, par2, par3, true);
    }

    /**
     * checks to see if a given block is both water, and cold enough to freeze - if the par4 boolean is set, this will
     * only return true if there is a non-water block immediately adjacent to the specified block
     */
    public boolean canBlockFreeze(int par1, int par2, int par3, boolean par4)
    {
        BiomeGenBase var5 = this.getBiomeGenForCoords(par1, par3);
        float var6 = var5.getFloatTemperature();

        if (var6 > 0.15F)
        {
            return false;
        }
        else
        {
            if (par2 >= 0 && par2 < 256 && this.getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10)
            {
                int var7 = this.getBlockId(par1, par2, par3);

                if ((var7 == Block.waterStill.blockID || var7 == Block.waterMoving.blockID) && this.getBlockMetadata(par1, par2, par3) == 0)
                {
                    if (!par4)
                    {
                        return true;
                    }

                    boolean var8 = true;

                    if (var8 && this.getBlockMaterial(par1 - 1, par2, par3) != Material.water)
                    {
                        var8 = false;
                    }

                    if (var8 && this.getBlockMaterial(par1 + 1, par2, par3) != Material.water)
                    {
                        var8 = false;
                    }

                    if (var8 && this.getBlockMaterial(par1, par2, par3 - 1) != Material.water)
                    {
                        var8 = false;
                    }

                    if (var8 && this.getBlockMaterial(par1, par2, par3 + 1) != Material.water)
                    {
                        var8 = false;
                    }

                    if (!var8)
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    /**
     * Tests whether or not snow can be placed at a given location
     */
    //BTAMOD
    public boolean canSnowAt(int x, int y, int z)
    {
        BiomeGenBase biome = this.getBiomeGenForCoords(x, z);
        float temperature = biome.getFloatTemperature();
        
        if (this.generatorInfoCache == null && this.provider.terrainType.isBTA()) {
        	if (this.provider.generatorOptions.equals(""))
        		this.generatorInfoCache = BTAWorldConfigurationInfo.createDefaultConfigurationLegacy(this.provider.terrainType.isDeco());
        	else
        		this.generatorInfoCache = BTAWorldConfigurationInfo.createInfoFromString(this.provider.generatorOptions);
        }
        
        if (this.provider.terrainType.isBTA() && this.getBiomeGenForCoords(x, z) instanceof BTABiomeGenBase && this.generatorInfoCache.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_0)) {
        	BTABiomeGenBase btaBiome = (BTABiomeGenBase) biome;
        	
        	if (y >= btaBiome.climate.minHeightForSnow + this.provider.terrainType.getColdBiomeSnowLevelModifier(this.generatorInfoCache) && y < 256) {
        		return this.getSavedLightValue(EnumSkyBlock.Block, x, y, z) < 10 && FCBlockSnowCover.CanSnowCoverReplaceBlock(this, x, y, z) && Block.snow.canPlaceBlockAt(this, x, y, z);
        	}
        	else {
        		return false;
        	}
        }
        else {
        	return temperature > 0.15F ? false : y >= 0 && y < 256 && this.getSavedLightValue(EnumSkyBlock.Block, x, y, z) < 10 && FCBlockSnowCover.CanSnowCoverReplaceBlock(this, x, y, z) && Block.snow.canPlaceBlockAt(this, x, y, z);
        }
    }

    public void updateAllLightTypes(int par1, int par2, int par3)
    {
        if (!this.provider.hasNoSky)
        {
            this.updateLightByType(EnumSkyBlock.Sky, par1, par2, par3);
        }

        this.updateLightByType(EnumSkyBlock.Block, par1, par2, par3);
    }

    private int computeLightValue(int par1, int par2, int par3, EnumSkyBlock par4EnumSkyBlock)
    {
        if (par4EnumSkyBlock == EnumSkyBlock.Sky && this.canBlockSeeTheSky(par1, par2, par3))
        {
            return 15;
        }
        else
        {
            int var5 = this.getBlockId(par1, par2, par3);
            int var6 = par4EnumSkyBlock == EnumSkyBlock.Sky ? 0 : Block.lightValue[var5];
            int var7 = Block.lightOpacity[var5];

            if (var7 >= 15 && Block.lightValue[var5] > 0)
            {
                var7 = 1;
            }

            if (var7 < 1)
            {
                var7 = 1;
            }

            if (var7 >= 15)
            {
                return 0;
            }
            else if (var6 >= 14)
            {
                return var6;
            }
            else
            {
                for (int var8 = 0; var8 < 6; ++var8)
                {
                    int var9 = par1 + Facing.offsetsXForSide[var8];
                    int var10 = par2 + Facing.offsetsYForSide[var8];
                    int var11 = par3 + Facing.offsetsZForSide[var8];
                    int var12 = this.getSavedLightValue(par4EnumSkyBlock, var9, var10, var11) - var7;

                    if (var12 > var6)
                    {
                        var6 = var12;
                    }

                    if (var6 >= 14)
                    {
                        return var6;
                    }
                }

                return var6;
            }
        }
    }

    public void updateLightByType(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4)
    {
        if (this.doChunksNearChunkExist(par2, par3, par4, 17))
        {
            int var5 = 0;
            int var6 = 0;
            this.theProfiler.startSection("getBrightness");
            int var7 = this.getSavedLightValue(par1EnumSkyBlock, par2, par3, par4);
            int var8 = this.computeLightValue(par2, par3, par4, par1EnumSkyBlock);
            int var9;
            int var10;
            int var11;
            int var12;
            int var13;
            int var14;
            int var15;
            int var17;
            int var16;

            if (var8 > var7)
            {
                this.lightUpdateBlockList[var6++] = 133152;
            }
            else if (var8 < var7)
            {
                this.lightUpdateBlockList[var6++] = 133152 | var7 << 18;

                while (var5 < var6)
                {
                    var9 = this.lightUpdateBlockList[var5++];
                    var10 = (var9 & 63) - 32 + par2;
                    var11 = (var9 >> 6 & 63) - 32 + par3;
                    var12 = (var9 >> 12 & 63) - 32 + par4;
                    var13 = var9 >> 18 & 15;
                    var14 = this.getSavedLightValue(par1EnumSkyBlock, var10, var11, var12);

                    if (var14 == var13)
                    {
                        this.setLightValue(par1EnumSkyBlock, var10, var11, var12, 0);

                        if (var13 > 0)
                        {
                            var15 = MathHelper.abs_int(var10 - par2);
                            var16 = MathHelper.abs_int(var11 - par3);
                            var17 = MathHelper.abs_int(var12 - par4);

                            if (var15 + var16 + var17 < 17)
                            {
                                for (int var18 = 0; var18 < 6; ++var18)
                                {
                                    int var19 = var10 + Facing.offsetsXForSide[var18];
                                    int var20 = var11 + Facing.offsetsYForSide[var18];
                                    int var21 = var12 + Facing.offsetsZForSide[var18];
                                    int var22 = Math.max(1, Block.lightOpacity[this.getBlockId(var19, var20, var21)]);
                                    var14 = this.getSavedLightValue(par1EnumSkyBlock, var19, var20, var21);

                                    if (var14 == var13 - var22 && var6 < this.lightUpdateBlockList.length)
                                    {
                                        this.lightUpdateBlockList[var6++] = var19 - par2 + 32 | var20 - par3 + 32 << 6 | var21 - par4 + 32 << 12 | var13 - var22 << 18;
                                    }
                                }
                            }
                        }
                    }
                }

                var5 = 0;
            }

            this.theProfiler.endSection();
            this.theProfiler.startSection("checkedPosition < toCheckCount");

            while (var5 < var6)
            {
                var9 = this.lightUpdateBlockList[var5++];
                var10 = (var9 & 63) - 32 + par2;
                var11 = (var9 >> 6 & 63) - 32 + par3;
                var12 = (var9 >> 12 & 63) - 32 + par4;
                var13 = this.getSavedLightValue(par1EnumSkyBlock, var10, var11, var12);
                var14 = this.computeLightValue(var10, var11, var12, par1EnumSkyBlock);

                if (var14 != var13)
                {
                    this.setLightValue(par1EnumSkyBlock, var10, var11, var12, var14);

                    if (var14 > var13)
                    {
                        var15 = Math.abs(var10 - par2);
                        var16 = Math.abs(var11 - par3);
                        var17 = Math.abs(var12 - par4);
                        boolean var23 = var6 < this.lightUpdateBlockList.length - 6;

                        if (var15 + var16 + var17 < 17 && var23)
                        {
                            if (this.getSavedLightValue(par1EnumSkyBlock, var10 - 1, var11, var12) < var14)
                            {
                                this.lightUpdateBlockList[var6++] = var10 - 1 - par2 + 32 + (var11 - par3 + 32 << 6) + (var12 - par4 + 32 << 12);
                            }

                            if (this.getSavedLightValue(par1EnumSkyBlock, var10 + 1, var11, var12) < var14)
                            {
                                this.lightUpdateBlockList[var6++] = var10 + 1 - par2 + 32 + (var11 - par3 + 32 << 6) + (var12 - par4 + 32 << 12);
                            }

                            if (this.getSavedLightValue(par1EnumSkyBlock, var10, var11 - 1, var12) < var14)
                            {
                                this.lightUpdateBlockList[var6++] = var10 - par2 + 32 + (var11 - 1 - par3 + 32 << 6) + (var12 - par4 + 32 << 12);
                            }

                            if (this.getSavedLightValue(par1EnumSkyBlock, var10, var11 + 1, var12) < var14)
                            {
                                this.lightUpdateBlockList[var6++] = var10 - par2 + 32 + (var11 + 1 - par3 + 32 << 6) + (var12 - par4 + 32 << 12);
                            }

                            if (this.getSavedLightValue(par1EnumSkyBlock, var10, var11, var12 - 1) < var14)
                            {
                                this.lightUpdateBlockList[var6++] = var10 - par2 + 32 + (var11 - par3 + 32 << 6) + (var12 - 1 - par4 + 32 << 12);
                            }

                            if (this.getSavedLightValue(par1EnumSkyBlock, var10, var11, var12 + 1) < var14)
                            {
                                this.lightUpdateBlockList[var6++] = var10 - par2 + 32 + (var11 - par3 + 32 << 6) + (var12 + 1 - par4 + 32 << 12);
                            }
                        }
                    }
                }
            }

            this.theProfiler.endSection();
        }
    }

    /**
     * Runs through the list of updates to run and ticks them
     */
    public boolean tickUpdates(boolean par1)
    {
        return false;
    }

    public List getPendingBlockUpdates(Chunk par1Chunk, boolean par2)
    {
        return null;
    }

    /**
     * Will get all entities within the specified AABB excluding the one passed into it. Args: entityToExclude, aabb
     */
    public List getEntitiesWithinAABBExcludingEntity(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB)
    {
        return this.getEntitiesWithinAABBExcludingEntity(par1Entity, par2AxisAlignedBB, (IEntitySelector)null);
    }

    public List getEntitiesWithinAABBExcludingEntity(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB, IEntitySelector par3IEntitySelector)
    {
        ArrayList var4 = new ArrayList();
        // FCMOD: Change to increased size of box to be able to handle wind mills extending accross chunk boundaries
        /*
        int var5 = MathHelper.floor_double((par2AxisAlignedBB.minX - 2.0D) / 16.0D);
        int var6 = MathHelper.floor_double((par2AxisAlignedBB.maxX + 2.0D) / 16.0D);
        int var7 = MathHelper.floor_double((par2AxisAlignedBB.minZ - 2.0D) / 16.0D);
        int var8 = MathHelper.floor_double((par2AxisAlignedBB.maxZ + 2.0D) / 16.0D);
        */
        int var5 = MathHelper.floor_double((par2AxisAlignedBB.minX - 6.0D) / 16.0D);
        int var6 = MathHelper.floor_double((par2AxisAlignedBB.maxX + 6.0D) / 16.0D);
        int var7 = MathHelper.floor_double((par2AxisAlignedBB.minZ - 6.0D) / 16.0D);
        int var8 = MathHelper.floor_double((par2AxisAlignedBB.maxZ + 6.0D) / 16.0D);
        // END FCMOD

        for (int var9 = var5; var9 <= var6; ++var9)
        {
            for (int var10 = var7; var10 <= var8; ++var10)
            {
                if (this.chunkExists(var9, var10))
                {
                    this.getChunkFromChunkCoords(var9, var10).getEntitiesWithinAABBForEntity(par1Entity, par2AxisAlignedBB, var4, par3IEntitySelector);
                }
            }
        }

        return var4;
    }

    /**
     * Returns all entities of the specified class type which intersect with the AABB. Args: entityClass, aabb
     */
    public List getEntitiesWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB)
    {
        return this.selectEntitiesWithinAABB(par1Class, par2AxisAlignedBB, (IEntitySelector)null);
    }

    public List selectEntitiesWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, IEntitySelector par3IEntitySelector)
    {
        // FCMOD: Increased size of box to be able to handle wind mills extending accross chunk boundaries
    	/*
        int var4 = MathHelper.floor_double((par2AxisAlignedBB.minX - 2.0D) / 16.0D);
        int var5 = MathHelper.floor_double((par2AxisAlignedBB.maxX + 2.0D) / 16.0D);
        int var6 = MathHelper.floor_double((par2AxisAlignedBB.minZ - 2.0D) / 16.0D);
        int var7 = MathHelper.floor_double((par2AxisAlignedBB.maxZ + 2.0D) / 16.0D);
        */
        int var4 = MathHelper.floor_double((par2AxisAlignedBB.minX - 6.0D) / 16.0D);
        int var5 = MathHelper.floor_double((par2AxisAlignedBB.maxX + 6.0D) / 16.0D);
        int var6 = MathHelper.floor_double((par2AxisAlignedBB.minZ - 6.0D) / 16.0D);
        int var7 = MathHelper.floor_double((par2AxisAlignedBB.maxZ + 6.0D) / 16.0D);
    	// END FCMOD
        ArrayList var8 = new ArrayList();

        for (int var9 = var4; var9 <= var5; ++var9)
        {
            for (int var10 = var6; var10 <= var7; ++var10)
            {
                if (this.chunkExists(var9, var10))
                {
                    this.getChunkFromChunkCoords(var9, var10).getEntitiesOfTypeWithinAAAB(par1Class, par2AxisAlignedBB, var8, par3IEntitySelector);
                }
            }
        }

        return var8;
    }

    public Entity findNearestEntityWithinAABB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, Entity par3Entity)
    {
        List var4 = this.getEntitiesWithinAABB(par1Class, par2AxisAlignedBB);
        Entity var5 = null;
        double var6 = Double.MAX_VALUE;

        for (int var8 = 0; var8 < var4.size(); ++var8)
        {
            Entity var9 = (Entity)var4.get(var8);

            if (var9 != par3Entity)
            {
                double var10 = par3Entity.getDistanceSqToEntity(var9);

                if (var10 <= var6)
                {
                    var5 = var9;
                    var6 = var10;
                }
            }
        }

        return var5;
    }

    /**
     * Returns the Entity with the given ID, or null if it doesn't exist in this World.
     */
    public abstract Entity getEntityByID(int var1);

    /**
     * marks the chunk that contains this tilentity as modified and then calls worldAccesses.doNothingWithTileEntity
     */
    public void updateTileEntityChunkAndDoNothing(int par1, int par2, int par3, TileEntity par4TileEntity)
    {
        if (this.blockExists(par1, par2, par3))
        {
            this.getChunkFromBlockCoords(par1, par3).setChunkModified();
        }
    }

    /**
     * Counts how many entities of an entity class exist in the world. Args: entityClass
     */
    // FCMOD: Code removed and replaced by custom function later
    /*
    public int countEntities(Class par1Class)
    {
        int var2 = 0;

        for (int var3 = 0; var3 < this.loadedEntityList.size(); ++var3)
        {
            Entity var4 = (Entity)this.loadedEntityList.get(var3);

            if ((!(var4 instanceof EntityLiving) || !((EntityLiving)var4).func_104002_bU()) && par1Class.isAssignableFrom(var4.getClass()))
            {
                ++var2;
            }
        }

        return var2;
    }
    */
    // END FCMOD

    /**
     * adds entities to the loaded entities list, and loads thier skins.
     */
    public void addLoadedEntities(List par1List)
    {
        this.loadedEntityList.addAll(par1List);

        for (int var2 = 0; var2 < par1List.size(); ++var2)
        {
            this.obtainEntitySkin((Entity)par1List.get(var2));
        }
    }

    /**
     * adds entities to the list of unloaded entities
     */
    public void unloadEntities(List par1List)
    {
        this.unloadedEntityList.addAll(par1List);
    }

    /**
     * Returns true if the given Entity can be placed on the given side of the given block position.
     */
    public boolean canPlaceEntityOnSide(int par1, int par2, int par3, int par4, boolean par5, int par6, Entity par7Entity, ItemStack par8ItemStack)
    {
        int var9 = this.getBlockId(par2, par3, par4);
        Block var10 = Block.blocksList[var9];
        Block var11 = Block.blocksList[par1];
        AxisAlignedBB var12 = var11.getCollisionBoundingBoxFromPool(this, par2, par3, par4);

        if (par5)
        {
            var12 = null;
        }

        if (var12 != null && !this.checkNoEntityCollision(var12, par7Entity))
        {
            return false;
        }
        else
        {
            // FCMOD: Change
    		/*
            if (var10 != null && (var10 == Block.waterMoving || var10 == Block.waterStill || var10 == Block.lavaMoving || var10 == Block.lavaStill || var10 == Block.fire || var10.blockMaterial.isReplaceable()))
            */
            if (var10 != null && (var10 == Block.waterMoving || var10 == Block.waterStill || var10 == Block.lavaMoving || var10 == Block.lavaStill || var10 == Block.fire || var10.blockMaterial.isReplaceable() ||
            	var10 == FCBetterThanWolves.fcBlockDetectorLogic || var10 == FCBetterThanWolves.fcBlockDetectorGlowingLogic || 
            	var10 == FCBetterThanWolves.fcBlockFireStoked ) )
        	// END FCMOD        	
            {
                var10 = null;
            }

            return var10 != null && var10.blockMaterial == Material.circuits && var11 == Block.anvil ? true : par1 > 0 && var10 == null && var11.canPlaceBlockOnSide(this, par2, par3, par4, par6, par8ItemStack);
        }
    }

    public PathEntity getPathEntityToEntity(Entity par1Entity, Entity par2Entity, float par3, boolean par4, boolean par5, boolean par6, boolean par7)
    {
        this.theProfiler.startSection("pathfind");
        int var8 = MathHelper.floor_double(par1Entity.posX);
        int var9 = MathHelper.floor_double(par1Entity.posY + 1.0D);
        int var10 = MathHelper.floor_double(par1Entity.posZ);
        int var11 = (int)(par3 + 16.0F);
        int var12 = var8 - var11;
        int var13 = var9 - var11;
        int var14 = var10 - var11;
        int var15 = var8 + var11;
        int var16 = var9 + var11;
        int var17 = var10 + var11;
        ChunkCache var18 = new ChunkCache(this, var12, var13, var14, var15, var16, var17, 0);
        PathEntity var19 = (new PathFinder(var18, par4, par5, par6, par7)).createEntityPathTo(par1Entity, par2Entity, par3);
        this.theProfiler.endSection();
        return var19;
    }

    public PathEntity getEntityPathToXYZ(Entity par1Entity, int par2, int par3, int par4, float par5, boolean par6, boolean par7, boolean par8, boolean par9)
    {
        this.theProfiler.startSection("pathfind");
        int var10 = MathHelper.floor_double(par1Entity.posX);
        int var11 = MathHelper.floor_double(par1Entity.posY);
        int var12 = MathHelper.floor_double(par1Entity.posZ);
        int var13 = (int)(par5 + 8.0F);
        int var14 = var10 - var13;
        int var15 = var11 - var13;
        int var16 = var12 - var13;
        int var17 = var10 + var13;
        int var18 = var11 + var13;
        int var19 = var12 + var13;
        ChunkCache var20 = new ChunkCache(this, var14, var15, var16, var17, var18, var19, 0);
        PathEntity var21 = (new PathFinder(var20, par6, par7, par8, par9)).createEntityPathTo(par1Entity, par2, par3, par4, par5);
        this.theProfiler.endSection();
        return var21;
    }

    /**
     * Is this block powering in the specified direction Args: x, y, z, direction
     */
    public int isBlockProvidingPowerTo(int par1, int par2, int par3, int par4)
    {
        int var5 = this.getBlockId(par1, par2, par3);
        return var5 == 0 ? 0 : Block.blocksList[var5].isProvidingStrongPower(this, par1, par2, par3, par4);
    }

    /**
     * Returns the highest redstone signal strength powering the given block. Args: X, Y, Z.
     */
    public int getBlockPowerInput(int par1, int par2, int par3)
    {
        byte var4 = 0;
        int var5 = Math.max(var4, this.isBlockProvidingPowerTo(par1, par2 - 1, par3, 0));

        if (var5 >= 15)
        {
            return var5;
        }
        else
        {
            var5 = Math.max(var5, this.isBlockProvidingPowerTo(par1, par2 + 1, par3, 1));

            if (var5 >= 15)
            {
                return var5;
            }
            else
            {
                var5 = Math.max(var5, this.isBlockProvidingPowerTo(par1, par2, par3 - 1, 2));

                if (var5 >= 15)
                {
                    return var5;
                }
                else
                {
                    var5 = Math.max(var5, this.isBlockProvidingPowerTo(par1, par2, par3 + 1, 3));

                    if (var5 >= 15)
                    {
                        return var5;
                    }
                    else
                    {
                        var5 = Math.max(var5, this.isBlockProvidingPowerTo(par1 - 1, par2, par3, 4));

                        if (var5 >= 15)
                        {
                            return var5;
                        }
                        else
                        {
                            var5 = Math.max(var5, this.isBlockProvidingPowerTo(par1 + 1, par2, par3, 5));
                            return var5 >= 15 ? var5 : var5;
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the indirect signal strength being outputted by the given block in the *opposite* of the given direction.
     * Args: X, Y, Z, direction
     */
    public boolean getIndirectPowerOutput(int par1, int par2, int par3, int par4)
    {
        return this.getIndirectPowerLevelTo(par1, par2, par3, par4) > 0;
    }

    /**
     * Gets the power level from a certain block face.  Args: x, y, z, direction
     */
    public int getIndirectPowerLevelTo(int par1, int par2, int par3, int par4)
    {
        if (this.isBlockNormalCube(par1, par2, par3))
        {
        	// FCMOD: Code added so solid blocks can act as power sources
            int iBlockID = getBlockId( par1, par2, par3 );
            
        	if ( Block.blocksList[iBlockID].canProvidePower() )
        	{
        		int iPowerInput = getBlockPowerInput(par1, par2, par3);
        		int iPowerProvided = Block.blocksList[iBlockID].isProvidingWeakPower(this, par1, par2, par3, par4);
        		
        		if ( iPowerInput > iPowerProvided )
        		{
        			return iPowerInput;
        		}
        		
        		return iPowerProvided;
        	}
        	// END FCMOD
            return this.getBlockPowerInput(par1, par2, par3);
        }
        else
        {
            int var5 = this.getBlockId(par1, par2, par3);
            return var5 == 0 ? 0 : Block.blocksList[var5].isProvidingWeakPower(this, par1, par2, par3, par4);
        }
    }

    /**
     * Used to see if one of the blocks next to you or your block is getting power from a neighboring block. Used by
     * items like TNT or Doors so they don't have redstone going straight into them.  Args: x, y, z
     */
    public boolean isBlockIndirectlyGettingPowered(int par1, int par2, int par3)
    {
        return this.getIndirectPowerLevelTo(par1, par2 - 1, par3, 0) > 0 ? true : (this.getIndirectPowerLevelTo(par1, par2 + 1, par3, 1) > 0 ? true : (this.getIndirectPowerLevelTo(par1, par2, par3 - 1, 2) > 0 ? true : (this.getIndirectPowerLevelTo(par1, par2, par3 + 1, 3) > 0 ? true : (this.getIndirectPowerLevelTo(par1 - 1, par2, par3, 4) > 0 ? true : this.getIndirectPowerLevelTo(par1 + 1, par2, par3, 5) > 0))));
    }

    public int getStrongestIndirectPower(int par1, int par2, int par3)
    {
        int var4 = 0;

        for (int var5 = 0; var5 < 6; ++var5)
        {
            int var6 = this.getIndirectPowerLevelTo(par1 + Facing.offsetsXForSide[var5], par2 + Facing.offsetsYForSide[var5], par3 + Facing.offsetsZForSide[var5], var5);

            if (var6 >= 15)
            {
                return 15;
            }

            if (var6 > var4)
            {
                var4 = var6;
            }
        }

        return var4;
    }

    /**
     * Gets the closest player to the entity within the specified distance (if distance is less than 0 then ignored).
     * Args: entity, dist
     */
    public EntityPlayer getClosestPlayerToEntity(Entity par1Entity, double par2)
    {
        return this.getClosestPlayer(par1Entity.posX, par1Entity.posY, par1Entity.posZ, par2);
    }

    /**
     * Gets the closest player to the point within the specified distance (distance can be set to less than 0 to not
     * limit the distance). Args: x, y, z, dist
     */
    public EntityPlayer getClosestPlayer(double par1, double par3, double par5, double par7)
    {
        double var9 = -1.0D;
        EntityPlayer var11 = null;

        for (int var12 = 0; var12 < this.playerEntities.size(); ++var12)
        {
            EntityPlayer var13 = (EntityPlayer)this.playerEntities.get(var12);
            double var14 = var13.getDistanceSq(par1, par3, par5);

            if ((par7 < 0.0D || var14 < par7 * par7) && (var9 == -1.0D || var14 < var9))
            {
                var9 = var14;
                var11 = var13;
            }
        }

        return var11;
    }

    /**
     * Returns the closest vulnerable player to this entity within the given radius, or null if none is found
     */
    public EntityPlayer getClosestVulnerablePlayerToEntity(Entity par1Entity, double par2)
    {
        return this.getClosestVulnerablePlayer(par1Entity.posX, par1Entity.posY, par1Entity.posZ, par2);
    }

    /**
     * Returns the closest vulnerable player within the given radius, or null if none is found.
     */
    public EntityPlayer getClosestVulnerablePlayer(double par1, double par3, double par5, double par7)
    {
        double var9 = -1.0D;
        EntityPlayer var11 = null;

        for (int var12 = 0; var12 < this.playerEntities.size(); ++var12)
        {
            EntityPlayer var13 = (EntityPlayer)this.playerEntities.get(var12);

            if (!var13.capabilities.disableDamage && var13.isEntityAlive())
            {
                double var14 = var13.getDistanceSq(par1, par3, par5);
                double var16 = par7;

                if (var13.isSneaking())
                {
                    var16 = par7 * 0.800000011920929D;
                }

                if (var13.isInvisible())
                {
                    float var18 = var13.func_82243_bO();

                    if (var18 < 0.1F)
                    {
                        var18 = 0.1F;
                    }

                    var16 *= (double)(0.7F * var18);
                }

                if ((par7 < 0.0D || var14 < var16 * var16) && (var9 == -1.0D || var14 < var9))
                {
                    var9 = var14;
                    var11 = var13;
                }
            }
        }

        return var11;
    }

    /**
     * Find a player by name in this world.
     */
    public EntityPlayer getPlayerEntityByName(String par1Str)
    {
        for (int var2 = 0; var2 < this.playerEntities.size(); ++var2)
        {
            if (par1Str.equals(((EntityPlayer)this.playerEntities.get(var2)).username))
            {
                return (EntityPlayer)this.playerEntities.get(var2);
            }
        }

        return null;
    }

    /**
     * Checks whether the session lock file was modified by another process
     */
    public void checkSessionLock() throws MinecraftException
    {
        this.saveHandler.checkSessionLock();
    }

    /**
     * gets the random world seed
     */
    public long getSeed()
    {
        return this.worldInfo.getSeed();
    }

    public long getTotalWorldTime()
    {
        return this.worldInfo.getWorldTotalTime();
    }

    public long getWorldTime()
    {
        return this.worldInfo.getWorldTime();
    }

    /**
     * Sets the world time.
     */
    public void setWorldTime(long par1)
    {
        this.worldInfo.setWorldTime(par1);
    }

    /**
     * Returns the coordinates of the spawn point
     */
    public ChunkCoordinates getSpawnPoint()
    {
        return new ChunkCoordinates(this.worldInfo.getSpawnX(), this.worldInfo.getSpawnY(), this.worldInfo.getSpawnZ());
    }

    /**
     * Called when checking if a certain block can be mined or not. The 'spawn safe zone' check is located here.
     */
    public boolean canMineBlock(EntityPlayer par1EntityPlayer, int par2, int par3, int par4)
    {
        return true;
    }

    /**
     * sends a Packet 38 (Entity Status) to all tracked players of that entity
     */
    public void setEntityState(Entity par1Entity, byte par2) {}

    /**
     * gets the world's chunk provider
     */
    public IChunkProvider getChunkProvider()
    {
        return this.chunkProvider;
    }

    /**
     * Adds a block event with the given Args to the blockEventCache. During the next tick(), the block specified will
     * have its onBlockEvent handler called with the given parameters. Args: X,Y,Z, BlockID, EventID, EventParameter
     */
    public void addBlockEvent(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        if (par4 > 0)
        {
            Block.blocksList[par4].onBlockEventReceived(this, par1, par2, par3, par5, par6);
        }
    }

    /**
     * Returns this world's current save handler
     */
    public ISaveHandler getSaveHandler()
    {
        return this.saveHandler;
    }

    /**
     * Returns the world's WorldInfo object
     */
    public WorldInfo getWorldInfo()
    {
        return this.worldInfo;
    }

    /**
     * Gets the GameRules instance.
     */
    public GameRules getGameRules()
    {
        return this.worldInfo.getGameRulesInstance();
    }

    /**
     * Updates the flag that indicates whether or not all players in the world are sleeping.
     */
    public void updateAllPlayersSleepingFlag() {}

    public float getWeightedThunderStrength(float par1)
    {
        return (this.prevThunderingStrength + (this.thunderingStrength - this.prevThunderingStrength) * par1) * this.getRainStrength(par1);
    }

    /**
     * Not sure about this actually. Reverting this one myself.
     */
    public float getRainStrength(float par1)
    {
        return this.prevRainingStrength + (this.rainingStrength - this.prevRainingStrength) * par1;
    }

    /**
     * Returns true if the current thunder strength (weighted with the rain strength) is greater than 0.9
     */
    public boolean isThundering()
    {
        return (double)this.getWeightedThunderStrength(1.0F) > 0.9D;
    }

    /**
     * Returns true if the current rain strength is greater than 0.2
     */
    public boolean isRaining()
    {
        return (double)this.getRainStrength(1.0F) > 0.2D;
    }

    /** 
     * FCNOTE: Terribly named.  Was actually whether a specific block is currently being rained on
     * Deprecated through sending message to avoid modifying vanilla classes that use it
     * while still ensuring that it isn't actually called.
     */
    public boolean canLightningStrikeAt(int par1, int par2, int par3)
    {
    	// FCMOD: Changed
    	/*    	 
        if (!this.isRaining())
        {
            return false;
        }
        else if (!this.canBlockSeeTheSky(par1, par2, par3))
        {
            return false;
        }
        else if (this.getPrecipitationHeight(par1, par3) > par2)
        {
            return false;
        }
        else
        {
            BiomeGenBase var4 = this.getBiomeGenForCoords(par1, par3);
            return var4.getEnableSnow() ? false : var4.canSpawnLightningBolt();
        }
        */
    	if ( !m_bCanLightningStrikeAtMessageSent )
    	{
	    	FCBetterThanWolves.DebugWarning( (new StringBuilder()).
	    		append( "Deprecated method World.canLightningStrikeAt() called.  " ).
	    		append( "Use IsRainingAtPos() or CanLightingStrikeAtPos() instead." ).toString() );
	    	
	    	m_bCanLightningStrikeAtMessageSent = true;
    	}
    	
    	return IsRainingAtPos( par1, par2, par3 );
    	// END FCMOD
    }

    /**
     * Checks to see if the biome rainfall values for a given x,y,z coordinate set are extremely high
     */
    public boolean isBlockHighHumidity(int par1, int par2, int par3)
    {
        BiomeGenBase var4 = this.getBiomeGenForCoords(par1, par3);
        return var4.isHighHumidity();
    }

    /**
     * Assigns the given String id to the given MapDataBase using the MapStorage, removing any existing ones of the same
     * id.
     */
    public void setItemData(String par1Str, WorldSavedData par2WorldSavedData)
    {
        this.mapStorage.setData(par1Str, par2WorldSavedData);
    }

    /**
     * Loads an existing MapDataBase corresponding to the given String id from disk using the MapStorage, instantiating
     * the given Class, or returns null if none such file exists. args: Class to instantiate, String dataid
     */
    public WorldSavedData loadItemData(Class par1Class, String par2Str)
    {
        return this.mapStorage.loadData(par1Class, par2Str);
    }

    /**
     * Returns an unique new data id from the MapStorage for the given prefix and saves the idCounts map to the
     * 'idcounts' file.
     */
    public int getUniqueDataId(String par1Str)
    {
        return this.mapStorage.getUniqueDataId(par1Str);
    }

    /**
     * FCNOTE: Sends an auxSFX to all players, regardless of distance to the effect.
     * Good for stuff like long range wolf howls and thunder claps.  
     */
    public void func_82739_e(int par1, int par2, int par3, int par4, int par5)
    {
        for (int var6 = 0; var6 < this.worldAccesses.size(); ++var6)
        {
            ((IWorldAccess)this.worldAccesses.get(var6)).broadcastSound(par1, par2, par3, par4, par5);
        }
    }

    /**
     * Plays a sound or particle effect. Parameters: Effect ID, X, Y, Z, Data. For a list of ids and data, see
     * http://wiki.vg/Protocol#Effects
     */
    public void playAuxSFX(int par1, int par2, int par3, int par4, int par5)
    {
        this.playAuxSFXAtEntity((EntityPlayer)null, par1, par2, par3, par4, par5);
    }

    /**
     * See description for playAuxSFX.
     */
    public void playAuxSFXAtEntity(EntityPlayer par1EntityPlayer, int par2, int par3, int par4, int par5, int par6)
    {
        try
        {
            for (int var7 = 0; var7 < this.worldAccesses.size(); ++var7)
            {
                ((IWorldAccess)this.worldAccesses.get(var7)).playAuxSFX(par1EntityPlayer, par2, par3, par4, par5, par6);
            }
        }
        catch (Throwable var10)
        {
            CrashReport var8 = CrashReport.makeCrashReport(var10, "Playing level event");
            CrashReportCategory var9 = var8.makeCategory("Level event being played");
            var9.addCrashSection("Block coordinates", CrashReportCategory.getLocationInfo(par3, par4, par5));
            var9.addCrashSection("Event source", par1EntityPlayer);
            var9.addCrashSection("Event type", Integer.valueOf(par2));
            var9.addCrashSection("Event data", Integer.valueOf(par6));
            throw new ReportedException(var8);
        }
    }

    /**
     * Returns maximum world height.
     */
    public int getHeight()
    {
        return 256;
    }

    /**
     * Returns current world height.
     */
    public int getActualHeight()
    {
        return this.provider.hasNoSky ? 128 : 256;
    }

    public IUpdatePlayerListBox func_82735_a(EntityMinecart par1EntityMinecart)
    {
        return null;
    }

    /**
     * puts the World Random seed to a specific state dependant on the inputs
     */
    public Random setRandomSeed(int par1, int par2, int par3)
    {
        long var4 = (long)par1 * 341873128712L + (long)par2 * 132897987541L + this.getWorldInfo().getSeed() + (long)par3;
        this.rand.setSeed(var4);
        return this.rand;
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(String par1Str, int par2, int par3, int par4)
    {
        return this.getChunkProvider().findClosestStructure(this, par1Str, par2, par3, par4);
    }

    /**
     * Adds some basic stats of the world to the given crash report.
     */
    public CrashReportCategory addWorldInfoToCrashReport(CrashReport par1CrashReport)
    {
        CrashReportCategory var2 = par1CrashReport.makeCategoryDepth("Affected level", 1);
        var2.addCrashSection("Level name", this.worldInfo == null ? "????" : this.worldInfo.getWorldName());
        var2.addCrashSectionCallable("All players", new CallableLvl2(this));
        var2.addCrashSectionCallable("Chunk stats", new CallableLvl3(this));

        try
        {
            this.worldInfo.addToCrashReport(var2);
        }
        catch (Throwable var4)
        {
            var2.addCrashSectionThrowable("Level Data Unobtainable", var4);
        }

        return var2;
    }

    /**
     * Starts (or continues) destroying a block with given ID at the given coordinates for the given partially destroyed
     * value
     */
    public void destroyBlockInWorldPartially(int par1, int par2, int par3, int par4, int par5)
    {
        for (int var6 = 0; var6 < this.worldAccesses.size(); ++var6)
        {
            IWorldAccess var7 = (IWorldAccess)this.worldAccesses.get(var6);
            var7.destroyBlockPartially(par1, par2, par3, par4, par5);
        }
    }

    /**
     * Return the Vec3Pool object for this world.
     */
    public Vec3Pool getWorldVec3Pool()
    {
        return this.vecPool;
    }

    /**
     * returns a calendar object containing the current date
     */
    public Calendar getCurrentDate()
    {
        if (this.getTotalWorldTime() % 600L == 0L)
        {
            this.theCalendar.setTimeInMillis(System.currentTimeMillis());
        }

        return this.theCalendar;
    }

    public Scoreboard getScoreboard()
    {
        return this.worldScoreboard;
    }

    public void func_96440_m(int par1, int par2, int par3, int par4)
    {
        for (int var5 = 0; var5 < 4; ++var5)
        {
            int var6 = par1 + Direction.offsetX[var5];
            int var7 = par3 + Direction.offsetZ[var5];
            int var8 = this.getBlockId(var6, par2, var7);

            if (var8 != 0)
            {
                Block var9 = Block.blocksList[var8];

                if (Block.redstoneComparatorIdle.func_94487_f(var8))
                {
                    var9.onNeighborBlockChange(this, var6, par2, var7, par4);
                }
                else if (Block.isNormalCube(var8))
                {
                    var6 += Direction.offsetX[var5];
                    var7 += Direction.offsetZ[var5];
                    var8 = this.getBlockId(var6, par2, var7);
                    var9 = Block.blocksList[var8];

                    if (Block.redstoneComparatorIdle.func_94487_f(var8))
                    {
                        var9.onNeighborBlockChange(this, var6, par2, var7, par4);
                    }
                }
            }
        }
    }

    public ILogAgent getWorldLogAgent()
    {
        return this.worldLogAgent;
    }

    // FCMOD: Added New
    private boolean m_bCanLightningStrikeAtMessageSent = false;
    
    private long m_lTimeOfLastSquidPossessionCapCount = -1;
    private int m_iLastSquidPossessionCapCount = 0;
    
    public static final int m_iLoadedChunksUpdateRange = 32;

    static public boolean InstallationIntegrityTest()
    {
    	return true;
    }

    public void ModSpecificTick()
    {
    }
    
    public boolean IsUpdateScheduledForBlock( int i, int j, int k, int iBlockID )
    {   
        return false;
    }
    
    /** 
     * Wrapper function for clarity: while ticks are being processed they are not still scheduled,
     * but are in a separate list for this tick.
     */
    public boolean IsUpdatePendingThisTickForBlock( int i, int j, int k, int iBlockID )
    {   
        return isBlockTickScheduled( i, j, k, iBlockID );
    }
    
    public boolean setBlockAndMetadataWithNotify( int i, int j, int k, int iBlockID, int iMetadata )
    {
        return this.setBlock( i, j, k, iBlockID, iMetadata, 3 );
    }

    public boolean setBlockWithNotify( int i, int j, int k, int iBlockID )
    {
        return this.setBlock( i, j, k, iBlockID, 0, 3 );
    }
    
    /**
	 * Alias to avoid inconsistent naming between client and server
     * Bit 1 notify neighbors.  Bit 2 sends change to clients.
     * Bit 4 seems to prevent a render update when called on client
     */
    public boolean SetBlockMetadataWithNotify( int i, int j, int k, int iMetadata, int iNotifyBitField )
    {
    	// client
        //return setBlockMetadataWithNotify( i, j, k, iMetadata, iNotifyBitField );
    	// server
        return setBlockMetadata( i, j, k, iMetadata, iNotifyBitField );
    }
    
    public boolean setBlockMetadata( int i, int j, int k, int iMetadata )
    {
        return this.setBlockMetadata( i, j, k, iMetadata, 0 );
    }

    public boolean setBlockMetadataWithNotify( int i, int j, int k, int iMetadata )
    {
        return this.setBlockMetadata( i, j, k, iMetadata, 3 );
    }
    
    public boolean setBlockMetadataWithClient( int i, int j, int k, int iMetadata )
    {
        return this.setBlockMetadata( i, j, k, iMetadata, 2 );
    }
    
    public boolean setBlockMetadataWithNotifyNoClient( int i, int j, int k, int iMetadata )
    {
        return this.setBlockMetadata( i, j, k, iMetadata, 1 );
    }
    
    public boolean setBlockAndMetadata( int i, int j, int k, int iBlockID, int iMetadata )
    {
        return this.setBlock( i, j, k, iBlockID, iMetadata, 2 );
    }
    
    public boolean isBlockGettingPowered( int i, int j, int k )
    {
    	return getBlockPowerInput( i, j, k ) > 0;
    }

    
    public MovingObjectPosition rayTraceBlocks_do_do( Vec3 startVec, Vec3 endVec, boolean bHitFluidSources, boolean bIgnoreNonMovementBlockingBlocks )
    {
    	// refactored version of vanilla function to try to optimize and correct errors
    	
        if ( !Double.isNaN(startVec.xCoord) && !Double.isNaN(startVec.yCoord) && !Double.isNaN(startVec.zCoord) &&
        	!Double.isNaN(endVec.xCoord) && !Double.isNaN(endVec.yCoord) && !Double.isNaN(endVec.zCoord) )
        {
        	Vec3 currentVec = getWorldVec3Pool().getVecFromPool( startVec.xCoord, startVec.yCoord, startVec.zCoord );
        	
        	double dTotalDeltaX = endVec.xCoord - startVec.xCoord;
        	double dTotalDeltaY = endVec.yCoord - startVec.yCoord;
        	double dTotalDeltaZ = endVec.zCoord - startVec.zCoord;
        	
            int iEndPosI = MathHelper.floor_double(endVec.xCoord);
            int iEndPosJ = MathHelper.floor_double(endVec.yCoord);
            int iEndPosK = MathHelper.floor_double(endVec.zCoord);
            
            int iCurrentPosI = MathHelper.floor_double(currentVec.xCoord);
            int iCurrentPosJ = MathHelper.floor_double(currentVec.yCoord);
            int iCurrentPosK = MathHelper.floor_double(currentVec.zCoord);
            
            double dProportionOfLengthToNextBlockBoundaryX;
            double dProportionOfLengthToNextBlockBoundaryY;
            double dProportionOfLengthToNextBlockBoundaryZ;
            
        	int iAxisFinishedCount = 0;
        	
        	int iIncrementI = -1;
        	
        	double dNextBlockBoundaryX = (double)iCurrentPosI;
        	double dBlockBoundaryIncrementX = -1D; 
        	
        	if ( iEndPosI > iCurrentPosI )
        	{
        		iIncrementI = 1;
        		
            	dNextBlockBoundaryX += 1D;
            	dBlockBoundaryIncrementX = 1D;                	
        	}
        	else if ( iEndPosI == iCurrentPosI )
        	{
        		iIncrementI = 0;
        		
        		iAxisFinishedCount++;
        	}
        	
        	int iIncrementJ = -1;
        	
        	double dNextBlockBoundaryY = (double)iCurrentPosJ;
        	double dBlockBoundaryIncrementY = -1D;
        	
        	if ( iEndPosJ > iCurrentPosJ )
        	{
        		iIncrementJ = 1;
        		
            	dNextBlockBoundaryY += 1D;
            	dBlockBoundaryIncrementY = 1D; 
        	}
        	else if ( iEndPosJ == iCurrentPosJ )
        	{
        		iIncrementJ = 0;
        		
        		iAxisFinishedCount++;
        	}
        	
        	int iIncrementK = -1;
        	
        	double dNextBlockBoundaryZ = (double)iCurrentPosK;
        	double dBlockBoundaryIncrementZ = -1D;
        	
        	if ( iEndPosK > iCurrentPosK )
        	{
        		iIncrementK = 1;
        		
            	dNextBlockBoundaryZ += 1D;
            	dBlockBoundaryIncrementZ = 1D; 
        	}
        	else if ( iEndPosK == iCurrentPosK )
        	{
        		iIncrementK = 0;
        		
        		iAxisFinishedCount++;
        	}
        	
            int iTempCount = 200;

            while ( iTempCount-- >= 0 )
            {                    
                int iCurrentBlockID = getBlockId( iCurrentPosI, iCurrentPosJ, iCurrentPosK );
                
                if ( iCurrentBlockID > 0 )
                {
                    Block currentBlock = Block.blocksList[iCurrentBlockID];

	                if ( !bIgnoreNonMovementBlockingBlocks || currentBlock.getCollisionBoundingBoxFromPool(this, iCurrentPosI, iCurrentPosJ, iCurrentPosK ) != null )
	                {
	                    int iFirstBlockMetadata = getBlockMetadata( iCurrentPosI, iCurrentPosJ, iCurrentPosK );
	                    
	                	if (  currentBlock.canCollideCheck( iFirstBlockMetadata, bHitFluidSources ) )
	                	{
		                    MovingObjectPosition collisionPosition = currentBlock.collisionRayTrace(this, iCurrentPosI, iCurrentPosJ, iCurrentPosK, currentVec, endVec);
		
		                    if ( collisionPosition != null )
		                    {
		                        return collisionPosition;
		                    }
	                	}
	                }
                }

                if ( iAxisFinishedCount >= 3 )
                {
                    return null;
                }
                
                if ( iIncrementI != 0 )
                {
                    dProportionOfLengthToNextBlockBoundaryX = ( dNextBlockBoundaryX - currentVec.xCoord ) / dTotalDeltaX;                        
                }
                else
                {
                	dProportionOfLengthToNextBlockBoundaryX = 999.0D;
                }
                
                
                if ( iIncrementJ != 0 )
                {
                    dProportionOfLengthToNextBlockBoundaryY = ( dNextBlockBoundaryY - currentVec.yCoord ) / dTotalDeltaY;                        
                }
                else
                {
                	dProportionOfLengthToNextBlockBoundaryY = 999.0D;
                }
                
                
                if ( iIncrementK != 0 )
                {
                    dProportionOfLengthToNextBlockBoundaryZ = ( dNextBlockBoundaryZ - currentVec.zCoord ) / dTotalDeltaZ;                        
                }
                else
                {
                	dProportionOfLengthToNextBlockBoundaryZ = 999.0D;
                }
                
                if ( dProportionOfLengthToNextBlockBoundaryX < dProportionOfLengthToNextBlockBoundaryY && 
                	dProportionOfLengthToNextBlockBoundaryX < dProportionOfLengthToNextBlockBoundaryZ )
                {
                    currentVec.xCoord = dNextBlockBoundaryX;
                    currentVec.yCoord += dTotalDeltaY * dProportionOfLengthToNextBlockBoundaryX;
                    currentVec.zCoord += dTotalDeltaZ * dProportionOfLengthToNextBlockBoundaryX;
                    
                    iCurrentPosI += iIncrementI;
                	dNextBlockBoundaryX += dBlockBoundaryIncrementX;
                    
                    if ( iCurrentPosI == iEndPosI )
                    {
                    	iAxisFinishedCount++;
                    	iIncrementI = 0;
                    }
                    
                }
                else if (dProportionOfLengthToNextBlockBoundaryY < dProportionOfLengthToNextBlockBoundaryZ)
                {
                    currentVec.xCoord += dTotalDeltaX * dProportionOfLengthToNextBlockBoundaryY;
                    currentVec.yCoord = dNextBlockBoundaryY;
                    currentVec.zCoord += dTotalDeltaZ * dProportionOfLengthToNextBlockBoundaryY;
                    
                    iCurrentPosJ += iIncrementJ;
                	dNextBlockBoundaryY += dBlockBoundaryIncrementY;
                    
                    if ( iCurrentPosJ == iEndPosJ )
                    {
                    	iAxisFinishedCount++;
                    	iIncrementJ = 0;
                    }                        
                }
                else
                {
                    currentVec.xCoord += dTotalDeltaX * dProportionOfLengthToNextBlockBoundaryZ;
                    currentVec.yCoord += dTotalDeltaY * dProportionOfLengthToNextBlockBoundaryZ;
                    currentVec.zCoord = dNextBlockBoundaryZ;
                    
                    iCurrentPosK += iIncrementK;
                	dNextBlockBoundaryZ += dBlockBoundaryIncrementZ;
                	
                    if ( iCurrentPosK == iEndPosK )
                    {
                    	iAxisFinishedCount++;
                    	iIncrementK = 0;
                    }                        
                }
            }
        }
        
        return null;
    }
    
    public MovingObjectPosition MouseOverRayTrace( Vec3 startVec, Vec3 endVec )
    {
        if (!Double.isNaN(startVec.xCoord) && !Double.isNaN(startVec.yCoord) && !Double.isNaN(startVec.zCoord))
        {
            if (!Double.isNaN(endVec.xCoord) && !Double.isNaN(endVec.yCoord) && !Double.isNaN(endVec.zCoord))
            {
                int iEndI = MathHelper.floor_double(endVec.xCoord);
                int iEndJ = MathHelper.floor_double(endVec.yCoord);
                int iEndK = MathHelper.floor_double(endVec.zCoord);
                int iTempI = MathHelper.floor_double(startVec.xCoord);
                int iTempJ = MathHelper.floor_double(startVec.yCoord);
                int iTempK = MathHelper.floor_double(startVec.zCoord);
                
                MovingObjectPosition tempCollisionPos = 
                	CheckLocationForMouseOverRayTrace( iTempI, iTempJ, iTempK,
            		startVec, endVec );
                
                if ( tempCollisionPos != null )
                {
                	return tempCollisionPos;
                }                    

                int iTempCount = 200;

                while (iTempCount-- >= 0)
                {
                    if (Double.isNaN(startVec.xCoord) || Double.isNaN(startVec.yCoord) || Double.isNaN(startVec.zCoord))
                    {
                        return null;
                    }

                    if (iTempI == iEndI && iTempJ == iEndJ && iTempK == iEndK)
                    {
                        return null;
                    }

                    boolean var39 = true;
                    boolean var40 = true;
                    boolean var41 = true;
                    double var15 = 999.0D;
                    double var17 = 999.0D;
                    double var19 = 999.0D;

                    if (iEndI > iTempI)
                    {
                        var15 = (double)iTempI + 1.0D;
                    }
                    else if (iEndI < iTempI)
                    {
                        var15 = (double)iTempI + 0.0D;
                    }
                    else
                    {
                        var39 = false;
                    }

                    if (iEndJ > iTempJ)
                    {
                        var17 = (double)iTempJ + 1.0D;
                    }
                    else if (iEndJ < iTempJ)
                    {
                        var17 = (double)iTempJ + 0.0D;
                    }
                    else
                    {
                        var40 = false;
                    }

                    if (iEndK > iTempK)
                    {
                        var19 = (double)iTempK + 1.0D;
                    }
                    else if (iEndK < iTempK)
                    {
                        var19 = (double)iTempK + 0.0D;
                    }
                    else
                    {
                        var41 = false;
                    }

                    double var21 = 999.0D;
                    double var23 = 999.0D;
                    double var25 = 999.0D;
                    double var27 = endVec.xCoord - startVec.xCoord;
                    double var29 = endVec.yCoord - startVec.yCoord;
                    double var31 = endVec.zCoord - startVec.zCoord;

                    if (var39)
                    {
                        var21 = (var15 - startVec.xCoord) / var27;
                    }

                    if (var40)
                    {
                        var23 = (var17 - startVec.yCoord) / var29;
                    }

                    if (var41)
                    {
                        var25 = (var19 - startVec.zCoord) / var31;
                    }

                    boolean var33 = false;
                    byte var42;

                    if (var21 < var23 && var21 < var25)
                    {
                        if (iEndI > iTempI)
                        {
                            var42 = 4;
                        }
                        else
                        {
                            var42 = 5;
                        }

                        startVec.xCoord = var15;
                        startVec.yCoord += var29 * var21;
                        startVec.zCoord += var31 * var21;
                    }
                    else if (var23 < var25)
                    {
                        if (iEndJ > iTempJ)
                        {
                            var42 = 0;
                        }
                        else
                        {
                            var42 = 1;
                        }

                        startVec.xCoord += var27 * var23;
                        startVec.yCoord = var17;
                        startVec.zCoord += var31 * var23;
                    }
                    else
                    {
                        if (iEndK > iTempK)
                        {
                            var42 = 2;
                        }
                        else
                        {
                            var42 = 3;
                        }

                        startVec.xCoord += var27 * var25;
                        startVec.yCoord += var29 * var25;
                        startVec.zCoord = var19;
                    }

                    Vec3 var34 = this.getWorldVec3Pool().getVecFromPool(startVec.xCoord, startVec.yCoord, startVec.zCoord);
                    iTempI = (int)(var34.xCoord = (double)MathHelper.floor_double(startVec.xCoord));

                    if (var42 == 5)
                    {
                        --iTempI;
                        ++var34.xCoord;
                    }

                    iTempJ = (int)(var34.yCoord = (double)MathHelper.floor_double(startVec.yCoord));

                    if (var42 == 1)
                    {
                        --iTempJ;
                        ++var34.yCoord;
                    }

                    iTempK = (int)(var34.zCoord = (double)MathHelper.floor_double(startVec.zCoord));

                    if (var42 == 3)
                    {
                        --iTempK;
                        ++var34.zCoord;
                    }

                    tempCollisionPos = CheckLocationForMouseOverRayTrace( 
                    	iTempI, iTempJ, iTempK, startVec, endVec );
                    
                    if ( tempCollisionPos != null )
                    {
                    	return tempCollisionPos;
                    }                    
                }
            }
        }
        
        return null;
    }
    
    public MovingObjectPosition CheckLocationForMouseOverRayTrace( int i, 
    	int j, int k, Vec3 startVec, Vec3 endVec )
    {
    	MovingObjectPosition closestPos = null;
    	double dClosestDistSq = 0D;
    	
    	for ( int iTempJ = j - 1; iTempJ <= j + 1; iTempJ++ )
    	{
            int iTempBlockID = getBlockId( i, iTempJ, k );

            if ( iTempBlockID > 0 )
            {
                int iTempMetadata = getBlockMetadata( i, iTempJ, k );                    
                Block tempBlock = Block.blocksList[iTempBlockID];

                if ( tempBlock.canCollideCheck( iTempMetadata, false ) )
                {
                    MovingObjectPosition tempCollisionPos = tempBlock.MouseOverRayTrace( 
                    	this, i, iTempJ, k, startVec, endVec);
                    
                    if ( tempCollisionPos != null )
                    {
                    	double tempDistSq = tempCollisionPos.hitVec.squareDistanceTo( startVec );
                    	
                    	if ( closestPos == null || tempDistSq < dClosestDistSq )
                    	{
                    		dClosestDistSq = tempDistSq;
                    		
                    		closestPos = tempCollisionPos;
                    	} 
                    }
                }
            }
    	}
    	
    	return closestPos;
    }
    
    public void playSound(double par1, double par3, double par5, String par7Str, float par8, float par9 ) 
    {
        playSound( par1, par3, par5, par7Str, par8, par9, false );    	
    }
    
    public Entity GetClosestEntityMatchingCriteriaWithinRange( double dSourcePosX, double dSourcePosY, double dSourcePosZ, double dRange, FCClosestEntitySelectionCriteria criteria )
    {
		int iChunkEntityListMinVerticalIndex = MathHelper.floor_double( ( dSourcePosY - dRange ) / 16.0D );
		int iChunkEntityListMaxVerticalIndex = MathHelper.floor_double( ( dSourcePosY + dRange ) / 16.0D ) + 1;
		
		if ( iChunkEntityListMinVerticalIndex < 0 )
		{
			iChunkEntityListMinVerticalIndex = 0;
			iChunkEntityListMaxVerticalIndex = Math.max( iChunkEntityListMinVerticalIndex, iChunkEntityListMaxVerticalIndex );			
		}
		
		if ( iChunkEntityListMaxVerticalIndex > 15 )
		{
			iChunkEntityListMaxVerticalIndex = 15;
			iChunkEntityListMinVerticalIndex = Math.min( iChunkEntityListMinVerticalIndex, iChunkEntityListMaxVerticalIndex );			
		}
		
    	FCClosestEntityInfo closestEntityInfo = 
    		new FCClosestEntityInfo( dSourcePosX, dSourcePosY, dSourcePosZ, dRange * dRange, null, criteria, iChunkEntityListMinVerticalIndex, iChunkEntityListMaxVerticalIndex );
    	
        int iMinChunkX = MathHelper.floor_double( ( dSourcePosX - dRange ) / 16D  );
        int iMaxChunkX = MathHelper.floor_double( ( dSourcePosX + dRange ) / 16D  ) + 1;
        int iMinChunkZ = MathHelper.floor_double( ( dSourcePosZ - dRange ) / 16D  );
        int iMaxChunkZ = MathHelper.floor_double( ( dSourcePosZ + dRange ) / 16D  ) + 1;
        
        for ( int iTempChunkX = iMinChunkX; iTempChunkX <= iMaxChunkX; iTempChunkX++ )
        {
            for ( int iTempChunkZ = iMinChunkZ; iTempChunkZ <= iMaxChunkZ; iTempChunkZ++ )
            {
                if ( chunkExists( iTempChunkX, iTempChunkZ ) )
                {
                    getChunkFromChunkCoords( iTempChunkX, iTempChunkZ ).GetClosestEntityMatchingCriteriaWithinRangeSq( closestEntityInfo );
                }
            }
        }

        return closestEntityInfo.m_closestEntity;
    }
    
    public int CountEntitiesThatApplyToSpawnCap( Class classToCount )
    {
    	// replacement for CountEntities that is slightly optimized
    	
        int iEntityCount = 0;

        for ( int iTempEntityIndex = 0; iTempEntityIndex < this.loadedEntityList.size(); ++iTempEntityIndex )
        {
            Entity tempEntity = (Entity)this.loadedEntityList.get(iTempEntityIndex);

            if ( tempEntity.DoesEntityApplyToSpawnCap() && classToCount.isAssignableFrom( tempEntity.getClass() ) )
            {
            	// verify that the entity is actually active
            	
            	++iEntityCount;
            }
        }

        return iEntityCount;
    }
    
    public int GetNumEntitiesThatApplyToSquidPossessionCap()
    {
    	long lCurrentTime = getWorldTime();
    	
    	if ( lCurrentTime != m_lTimeOfLastSquidPossessionCapCount )
    	{
    		m_iLastSquidPossessionCapCount = 0;
    		m_lTimeOfLastSquidPossessionCapCount = lCurrentTime;
    		
            for ( int iTempEntityIndex = 0; iTempEntityIndex < this.loadedEntityList.size(); ++iTempEntityIndex )
            {
                Entity tempEntity = (Entity)this.loadedEntityList.get(iTempEntityIndex);

                if ( tempEntity.DoesEntityApplyToSquidPossessionCap() )
                {
                	m_iLastSquidPossessionCapCount++;
                }
            }            
    	}
    	
		return m_iLastSquidPossessionCapCount;
    }

	public void NotifyNearbyAnimalsOfPlayerBlockAddOrRemove( EntityPlayer player, Block block, int i, int j, int k )
	{
		if ( !isRemote && block.blockMaterial.blocksMovement() && !player.capabilities.isCreativeMode )
		{
			double dXBlock = (double)i + 0.5D;
			double dYBlock = (double)j + 0.5D;
			double dZBlock = (double)k + 0.5D;
			
			AxisAlignedBB targetBox = AxisAlignedBB.getAABBPool().getAABB( dXBlock - 8D, dYBlock - 4D, dZBlock - 8D, dXBlock + 8D, dYBlock + 4D, dZBlock + 8D );
			
	        List animalList = this.getEntitiesWithinAABB( EntityAnimal.class, targetBox );
	        
	        Iterator animalIterator = animalList.iterator();
	
	        while ( animalIterator.hasNext())
	        {
	    		EntityAnimal tempAnimal = (EntityAnimal)animalIterator.next();
	    		
		        if ( !tempAnimal.isLivingDead )
		        {
		        	tempAnimal.OnNearbyPlayerBlockAddOrRemove( player ); 
		        }		        
	        }
		}
	}
	
    public int GetBlockNaturalLightValue( int i, int j, int k )
    {
        return GetBlockNaturalLightValue_do( i, j, k, true, skylightSubtracted );
    }

    public int GetBlockNaturalLightValueMaximum( int i, int j, int k )
    {
        return GetBlockNaturalLightValue_do( i, j, k, true, 0 );
    }

    public float GetNaturalLightBrightness( int i, int j, int k )
    {
        return provider.lightBrightnessTable[GetBlockNaturalLightValue(i, j, k)];
    }

    private int GetBlockNaturalLightValue_do( int i, int j, int k, boolean bConsiderNeighbors, int iSkylightToSubtract )
    {
    	// Version of getBlockLightValue_do modified to only consider natural light    	
        if ( i >= -30000000 && k >= -30000000 && i < 30000000 && k < 30000000 )
        {
            if ( bConsiderNeighbors )
            {
                int iBlockID = getBlockId( i, j, k );

                if ( Block.useNeighborBrightness[iBlockID] )
                {
                    int iNeighbor1 = GetBlockNaturalLightValue_do( i, j + 1, k, false, iSkylightToSubtract );
                    int iNeighbor2 = GetBlockNaturalLightValue_do( i + 1, j, k, false, iSkylightToSubtract );
                    int iNeighbor3 = GetBlockNaturalLightValue_do( i - 1, j, k, false, iSkylightToSubtract );
                    int iNeighbor4 = GetBlockNaturalLightValue_do( i, j, k + 1, false, iSkylightToSubtract );
                    int iNeighbor5 = GetBlockNaturalLightValue_do( i, j, k - 1, false, iSkylightToSubtract );

                    if (iNeighbor2 > iNeighbor1)
                    {
                        iNeighbor1 = iNeighbor2;
                    }

                    if (iNeighbor3 > iNeighbor1)
                    {
                        iNeighbor1 = iNeighbor3;
                    }

                    if (iNeighbor4 > iNeighbor1)
                    {
                        iNeighbor1 = iNeighbor4;
                    }

                    if (iNeighbor5 > iNeighbor1)
                    {
                        iNeighbor1 = iNeighbor5;
                    }

                    return iNeighbor1;
                }
            }

            if (j < 0)
            {
                return 0;
            }
            else
            {
                if (j >= 256)
                {
                    j = 255;
                }

                Chunk chunk = this.getChunkFromChunkCoords(i >> 4, k >> 4);
                
                i &= 15;
                k &= 15;
                
                return chunk.GetBlockNaturalLightValue( i, j, k, iSkylightToSubtract );
            }
        }
        else
        {
            return 15;
        }
    }
    
    // @Override // client only override
    public boolean doesBlockHaveSolidTopSurface( int i, int j, int k )
    {
        Block block = Block.blocksList[this.getBlockId(i, j, k)];
        
        return block != null && block.HasLargeCenterHardPointToFacing( this, i, j, k, 1 );
    }
    
    //------------- Hardcore of Darkness Handling ------------//    
    
    static private double[] m_dMoonBrightnessByPhase = new double[] { 1.25D, 0.875, 0.75D, 0.5D, 0D, 0.5D, 0.75D, 1.25D }; 
	
    public float ComputeOverworldSunBrightnessWithMoonPhases()
    {
		// slight modified version of regular moon phase equation so that phase switches over at noon to avoid sudden jump in lighting at dawn
		long lOffsetWorldTime = worldInfo.getWorldTime() - 12000L;
		
		if ( lOffsetWorldTime < 0L )
		{
			lOffsetWorldTime = 0L;
		}
		
		int iMoonPhase = (int)( ( lOffsetWorldTime / 24000L ) % 8L );		
		double dMoonBrightness = m_dMoonBrightnessByPhase[iMoonPhase];
		
        float fCelestialAngle = getCelestialAngle( 1F );
        
        // slight modifcation from vanilla calc so that gloom starts to set in on moonless nights the moment the sun drops beneath the horizon, and is removed at the moment of rise
        //float fSunInvertedBrightness = 1.0F - (MathHelper.cos(fCelestialAngle * (float)Math.PI * 2.0F) * 2.0F + 0.2F);
        float fSunInvertedBrightness = 1.0F - ( ( MathHelper.cos( fCelestialAngle * (float)Math.PI * 2.0F ) * 2.0F ) + 0.25F );

        if ( fSunInvertedBrightness < 0.0F)
        {
        	fSunInvertedBrightness = 0.0F;
        }
        else if ( fSunInvertedBrightness > 1.0F)
        {
        	fSunInvertedBrightness = 1.0F;
        }

        double dSunBrightness = 1.0D - fSunInvertedBrightness;
        
        double dRainBrightnessModifier = ( 1.0D - (double)( getRainStrength( 1F ) * 5.0F ) / 16.0D );
        double dStormBrightnessModifier = ( 1.0D - (double)( getWeightedThunderStrength( 1F ) * 5.0F ) / 16.0D );
        
        dSunBrightness = dSunBrightness * dRainBrightnessModifier * dStormBrightnessModifier;

        double dMinBrightness = 0.2D;
        
        dMinBrightness *= dMoonBrightness * dRainBrightnessModifier * dStormBrightnessModifier;
        
        // clamp at a value that causes the world to descend into gloom on a new moon during a storm
        if ( dMinBrightness < 0.05D )
        {
        	dMinBrightness = 0D;
        }            
    	
        return (float)( dSunBrightness * ( 1D - dMinBrightness ) + dMinBrightness );
    }
    
    public boolean IsTheEndNigh()
    {
    	return false;
    }
    
    //------------- Magnetic Point Handling ------------//
    
    protected FCMagneticPointList m_MagneticPointList = new FCMagneticPointList();
    
    public FCMagneticPointList GetMagneticPointList()
    {
    	return m_MagneticPointList;
    }
    
    //------------- Ender Chest Handling ------------//
    
    protected InventoryEnderChest m_localEnderChestInventory = new InventoryEnderChest();
    protected InventoryEnderChest m_localLowPowerEnderChestInventory = new InventoryEnderChest();
    
    public InventoryEnderChest GetLocalEnderChestInventory()
    {
    	return m_localEnderChestInventory;
    }
    
    public InventoryEnderChest GetLocalLowPowerEnderChestInventory()
    {
    	return m_localLowPowerEnderChestInventory;
    }
    
    //------------- Looting Beacon Handling ------------//
    
    protected FCBeaconEffectLocationList m_LootingBeaconLocationList = new FCBeaconEffectLocationList();
    
    public FCBeaconEffectLocationList GetLootingBeaconLocationList()
    {
    	return m_LootingBeaconLocationList;
    }
    
    public int GetAmbientLootingEffectAtLocation( int iLocI, int iLocJ, int iLocK )
    {
    	return m_LootingBeaconLocationList.GetMostPowerfulBeaconEffectForLocation( iLocI, iLocK );
    }
    
    //------------- Spawn Location Handling ------------//
    
    protected FCSpawnLocationList m_SpawnLocationList = new FCSpawnLocationList();
    
    public FCSpawnLocationList GetSpawnLocationList()
    {
    	return m_SpawnLocationList;
    }    
    
    //------------- Explosion Handling ------------//
    
    /**
     * Copy of newExplosion() that suppresses the audio/visual effects
     */
    public Explosion NewExplosionNoFX( Entity par1Entity, double dPosX, double dPosY, double dPosZ, 
    	float fExplosionSize, boolean bCreatesFlames, boolean bDestroysBlocks )
    {
        Explosion explosion = new Explosion( this, par1Entity, dPosX, dPosY, dPosZ, fExplosionSize );
        
        explosion.isFlaming = bCreatesFlames;
        explosion.isSmoking = bDestroysBlocks;
        explosion.m_bSuppressFX = true;
        
        explosion.doExplosionA();
        explosion.doExplosionB( false ); // false tells individual block destruction effects not to play
        
        return explosion;
    }
    
    //------------- "View Distance" / Chunk Update Handling ------------//
    
    /** 
     * The "view distance" is actually the range at which chunks are loaded, specified as 
     * a vanilla server parameter (default is 10 chunks). 
     */
    public int GetClampedViewDistanceInChunks()
    {
    	return 10;
    }
    
    public int GetMobSpawnRangeInChunks()
    {
    	return GetClampedViewDistanceInChunks() - 2;
    }
    
    /** 
     * "Active" chunks handle stuff like random block updates (grass growing, etc.)
     * and precipitation
     */
    public int GetActiveChunkRangeInChunks()
    {
    	int iRange = GetClampedViewDistanceInChunks() - 3;
    	
    	if ( iRange < 1 )
    	{
    		iRange = 1;
    	}
    	
    	return iRange;
    }
    
    protected void UpdateActiveChunkMap()
    {
    	ClearActiveChunkMap();
    	
        for ( int iTempIndex = 0; iTempIndex < playerEntities.size(); ++iTempIndex )
        {
            EntityPlayer tempPlayer = (EntityPlayer)playerEntities.get( iTempIndex );
            
            AddEntityToActiveChunkMap( tempPlayer );
        }
    }
    
    protected void AddEntityToActiveChunkMap( Entity entity )
    {
        int iChunkX = MathHelper.floor_double( entity.posX / 16D );
        int iChunkZ = MathHelper.floor_double( entity.posZ / 16D );
        
        AddAreaAroundChunkToActiveChunkMap( iChunkX, iChunkZ );
    }
    
    protected void AddAreaAroundChunkToActiveChunkMap( int iChunkX, int iChunkZ )
    {
        int iActiveRange = GetActiveChunkRangeInChunks();
        
        for ( int iTempChunkX = iChunkX - iActiveRange; 
        	iTempChunkX <= iChunkX + iActiveRange; iTempChunkX++ )
        {
            for ( int iTempChunkZ = iChunkZ - iActiveRange; 
            	iTempChunkZ <= iChunkZ + iActiveRange; iTempChunkZ++ )
            {
            	AddToActiveChunkMap( iTempChunkX, iTempChunkZ );
            }
        }
    }
    
    protected void ClearActiveChunkMap()
    {
        Iterator<ChunkCoordIntPair> tempIterator = m_activeChunksCoordsList.iterator();

        while ( tempIterator.hasNext() )
        {
        	ChunkCoordIntPair coords = tempIterator.next();
            Long lTempKey = ChunkCoordIntPair.chunkXZ2Int( coords.chunkXPos, coords.chunkZPos );
            
            tempIterator.remove();
            m_activeChunksCoordsMap.remove( lTempKey ); 
        }
    }
    
    protected void AddToActiveChunkMap( int iChunkX, int iChunkZ )
    {
        long lCoordKey = ChunkCoordIntPair.chunkXZ2Int( iChunkX, iChunkZ );
        
        if ( !m_activeChunksCoordsMap.containsItem( lCoordKey ) )
        {
	    	ChunkCoordIntPair coords = new ChunkCoordIntPair( iChunkX, iChunkZ );
	    	
	        m_activeChunksCoordsList.add( coords );
	        
	        m_activeChunksCoordsMap.add( lCoordKey, coords );
        }
    }
    
    public boolean IsChunkActive( int iChunkX, int iChunkZ )
    {
        long lCoordKey = ChunkCoordIntPair.chunkXZ2Int( iChunkX, iChunkZ );
        
        return m_activeChunksCoordsMap.containsItem( lCoordKey );
    }
    
    public boolean IsBlockPosActive( int i, int j, int k )
    {    	
        if ( j >= 0 && j < 256 )
        {
        	return IsChunkActive( i >> 4, k >> 4 );
        }
        
        return false;
    }
    
    public LinkedList<ChunkCoordIntPair> GetActiveChunksCoordsList()
    {
    	return m_activeChunksCoordsList;
    }
    
    //------------- Weather Handling ------------//
    
    protected void updateWeather()
    {
    	// replacement for vanilla code to clean up and ensure that storms only start after it's been raining awhile
    	
        if ( !provider.hasNoSky )
        {
            int iRainTime = worldInfo.getRainTime();

            if ( iRainTime <= 0 )
            {
                if ( worldInfo.isRaining() )
                {
                    worldInfo.setRainTime( rand.nextInt( 12000 ) + 12000 );
                }
                else
                {
                    worldInfo.setRainTime( rand.nextInt( 168000 ) + 12000 );
                }
            }
            else
            {
                --iRainTime;
                
                worldInfo.setRainTime( iRainTime );

                if ( iRainTime <= 0 )
                {
                    worldInfo.setRaining( !worldInfo.isRaining() );
                    
                    if ( worldInfo.isRaining() && !worldInfo.isThundering() )
                    {
                    	// min of a minute when rain starts before storm.  About a 1/5 chance of rain turning into a storm at some point in its duration
                    	
                		worldInfo.setThunderTime( rand.nextInt( 84000 ) + 1200 );
                    }
                }
            }

            if ( worldInfo.isRaining() )
            {
	            int iThunderTime = worldInfo.getThunderTime();
	
	            if ( iThunderTime <= 0 )
	            {
	                if ( worldInfo.isThundering() )
	                {
	                    worldInfo.setThunderTime( rand.nextInt( 12000 ) + 3600 );
	                }
	                else
	                {
                		worldInfo.setThunderTime( rand.nextInt( 84000 ) + 1200 );
	                }
	            }
	            else
	            {
	                --iThunderTime;
	                
	                worldInfo.setThunderTime( iThunderTime );
	
	                if ( iThunderTime <= 0 )
	                {
	                    worldInfo.setThundering( !worldInfo.isThundering() );
	                }
	            }
            }
            else if ( worldInfo.isThundering() )
        	{
        		worldInfo.setThundering( false );
        	}

            // update the rain and thunder strength counters
            
            prevRainingStrength = rainingStrength;

            if ( worldInfo.isRaining() )
            {
                rainingStrength = rainingStrength + 0.01F;
                
                if ( rainingStrength > 1F )
                {
                    rainingStrength = 1F;
                }
            }
            else
            {
                rainingStrength = rainingStrength - 0.01F;
                
                if ( rainingStrength < 0F )
                {
                    rainingStrength = 0F;
                }
            }

            prevThunderingStrength = thunderingStrength;

            if ( worldInfo.isThundering() )
            {
                thunderingStrength = thunderingStrength + 0.01F;
                
                if ( thunderingStrength > 1F )
                {
                    thunderingStrength = 1F;
                }
            }
            else
            {
                thunderingStrength = thunderingStrength - 0.01F;
                
                if ( thunderingStrength < 0F )
                {
                    thunderingStrength = 0F;
                }
            }
        }
    }
    
    public boolean IsRainingAtPos( int i, int j, int k )
    {
        if ( isRaining() && canBlockSeeTheSky( i, j, k ) &&
        	j >= getPrecipitationHeight( i, k )  )
        {
            BiomeGenBase biome = getBiomeGenForCoords( i, k );
            
            return biome.CanRainInBiome();
        }
        
        return false;
    }
    
    public boolean IsSnowingAtPos( int i, int j, int k )
    {
        if ( isRaining() && canBlockSeeTheSky( i, j, k ) &&
        	j >= getPrecipitationHeight( i, k )  )
        {
            BiomeGenBase biome = getBiomeGenForCoords( i, k );
            
            return biome.getEnableSnow();
        }
        
        return false;
    }
    
    public boolean IsPrecipitatingAtPos( int i, int j, int k )
    {
        if ( isRaining() && canBlockSeeTheSky( i, j, k ) &&
        	j >= getPrecipitationHeight( i, k )  )
        {
            BiomeGenBase biome = getBiomeGenForCoords( i, k );
            
            return biome.getEnableSnow() || biome.CanRainInBiome();
        }
        
        return false;
    }
    
    public boolean IsPrecipitatingAtPos( int i, int k )
    {
        if ( isRaining() )
        {
            BiomeGenBase biome = getBiomeGenForCoords( i, k );
            
            return biome.getEnableSnow() || biome.CanRainInBiome();
        }
        
        return false;
    }
    
    public boolean CanLightningStrikeAtPos( int i, int j, int k )
    {
        if ( isRaining() && canBlockSeeTheSky( i, j, k ) &&
        	j >= getPrecipitationHeight( i, k )  )
        {
            BiomeGenBase biome = getBiomeGenForCoords( i, k );
            
            return biome.CanLightningStrikeInBiome();
        }
        
        return false;
    }
    // END FCMOD
}
