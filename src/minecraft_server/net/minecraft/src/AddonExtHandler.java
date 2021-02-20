package net.minecraft.src;

import java.util.ArrayList;

import net.minecraft.server.MinecraftServer;

public class AddonExtHandler {
	private static NetServerHandler netServerHandler;
	private static ArrayList<String> ackCheckFails = new ArrayList<String>();

	public static void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP) {
		netServerHandler = serverHandler;

		for (Object mod : FCAddOnHandler.m_ModList) {
			if (mod instanceof AddonExt) {
				((AddonExt) mod).serverPlayerConnectionInitialized(serverHandler, playerMP);
			}
		}
	}

	public static boolean getAwaitingLoginAck() {
		for (Object mod : FCAddOnHandler.m_ModList) {
			if (mod instanceof AddonExt && ((AddonExt) mod).getAwaitingLoginAck()) {
				return true;
			}
		}

		return false;
	}

	public static void incrementTicksSinceAckRequested() {
		for (Object mod : FCAddOnHandler.m_ModList) {
			if (mod instanceof AddonExt) {
				((AddonExt) mod).incrementTicksSinceAckRequested();
			}
		}
	}

	public static void handleAckCheck() {
		for (Object mod : FCAddOnHandler.m_ModList) {
			if (mod instanceof AddonExt && !((AddonExt) mod).handleAckCheck()) {
				ackCheckFails.add(((AddonExt) mod).getName());
			}
		}

		if (!ackCheckFails.isEmpty()) {
			String message = "";
			
			for (int i = 0; i < ackCheckFails.size(); i++) {
				if (i > 0)
					message += ", ";
				message += ackCheckFails.get(i);
			}
			
			FCUtilsWorld.SendPacketToPlayer(netServerHandler, new Packet3Chat("WARNING: Client missing the following addons, or very high latency connection: " + message));
		}
	}
}
