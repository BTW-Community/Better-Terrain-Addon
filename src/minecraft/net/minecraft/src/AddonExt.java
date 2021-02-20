package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public abstract class AddonExt extends FCAddOn {
	private String addonName;
	private String versionString;

	/** Set this to false if your addon is client only, or if you implement your own version checking */
	protected boolean shouldVersionCheck = true;

	private boolean awaitingLoginAck = false;
	private int ticksSinceAckRequested = 0;
	private static final int maxTicksForAckWait = 50;

	public final String addonCustomPacketChannelVersionCheck;
	public final String addonCustomPacketChannelVersionCheckAck;

	/**
	 * @param addonName Used for display in version checking
	 * @param versionString Used for version checking
	 * @param prefix Used for translations and packet channels
	 */
	public AddonExt(String addonName, String versionString, String prefix) {
		this.addonName = addonName;
		this.versionString = versionString;
		this.addonCustomPacketChannelVersionCheck = prefix + "|VC";
		this.addonCustomPacketChannelVersionCheckAck = prefix + "|VC_Ack";
	}

	/**
	 * Called when a player joins the world
	 * Used for version checking
	 */
	public void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP) {
		if (!MinecraftServer.getServer().isSinglePlayer())
		{
			FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + addonName + " V" + versionString));

			if (shouldVersionCheck) {
				ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
				DataOutputStream dataOutput = new DataOutputStream(byteArrayOutput);

				try
				{
					dataOutput.writeUTF(versionString);
				}
				catch (Exception var9)
				{
					var9.printStackTrace();
				}

				Packet250CustomPayload var4 = new Packet250CustomPayload(addonCustomPacketChannelVersionCheck, byteArrayOutput.toByteArray());
				FCUtilsWorld.SendPacketToPlayer(serverHandler, var4);
				awaitingLoginAck = true;
			}
		}
		else {
			FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + addonName + " V" + versionString));
		}
	}

	/**
	 * Called when a custom packet is received by the NetServerHandler
	 * If overriding, make sure to make a call to the super method if you want to maintain version checking
	 * @return true if packet was handled, false otherwise
	 */
	@Override
	public boolean ServerCustomPacketReceived(NetServerHandler serverHandler, Packet250CustomPayload packet) {
		if (addonCustomPacketChannelVersionCheckAck.equals(packet.channel)) {
			FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat("\u00a7f" + addonName + " version check successful."));
			awaitingLoginAck = false;
			ticksSinceAckRequested = 0;
		}

		return false;
	}

	/**
	 * @return whether the server is awaiting the client's response to the version check
	 */
	public boolean getAwaitingLoginAck() {
		return awaitingLoginAck;
	}

	public void incrementTicksSinceAckRequested() {
		ticksSinceAckRequested++;
	}

	public boolean handleAckCheck() {
		if (ticksSinceAckRequested > maxTicksForAckWait) {
			awaitingLoginAck = false;
			ticksSinceAckRequested = 0;
			return false;
		}

		return true;
	}

	public String getName() {
		return this.addonName;
	}

	public String getVersionString() {
		return this.versionString;
	}

	//Client only
	/**
	 * Used to modify existing client side packet250 behavior (For modifying BTW behavior)
	 * @return true if packet was handled, false otherwise
	 */
	public boolean interceptCustomClientPacket(Minecraft mc, Packet250CustomPayload packet) {
		return false;
	}

	/**
	 * Called when a custom packet is received by the NetClientHandler
	 * If overriding, make sure to make a call to the super method
	 * @return true if packet was handled, false otherwise
	 */
	public boolean ClientCustomPacketReceived(Minecraft mc, Packet250CustomPayload packet)
	{
		try
		{
			WorldClient world = mc.theWorld;
			DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int packetType;
			int var9;

			if (packet.channel.equals(addonCustomPacketChannelVersionCheck))
			{
				String var33 = dataStream.readUTF();

				if (!var33.equals(versionString))
				{
					mc.thePlayer.addChatMessage("\u00a74" + "WARNING: " + this.getName() + " version mismatch detected! Local Version: " + this.versionString + " Server Version: " + var33);
				}

				ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
				DataOutputStream dataOutput = new DataOutputStream(byteArrayOutput);

				try
				{
					dataOutput.writeUTF(versionString);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				Packet250CustomPayload ackPacket = new Packet250CustomPayload(addonCustomPacketChannelVersionCheckAck, byteArrayOutput.toByteArray());
				mc.getNetHandler().addToSendQueue(ackPacket);

				return true;
			}
		}
		catch (IOException var23)
		{
			var23.printStackTrace();
		}

		return false;
	}

	/**
	 * Spawns a custom particle based on a string specifying the type
	 * @return the spawned particle, or null if type is not handled
	 */
	public EntityFX spawnCustomParticle(World world, String particleType, double x, double y, double z, double velX, double velY, double velZ) {
		return null;
	}
}