package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class BTACommandVillage extends CommandBase {

	@Override
	public String getCommandName() {
		return "village";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		ChunkProviderServer provider = (ChunkProviderServer) MinecraftServer.getServer().worldServers[0].getChunkProvider();
		ChunkCoordinates coords = var1.getPlayerCoordinates();
		
		BTAChunkProvider btwgProvider = null;
		
		try {
			Field chunkProviderField = (ChunkProviderServer.class.getDeclaredField("currentChunkProvider"));
			chunkProviderField.setAccessible(true);
			
			btwgProvider = (BTAChunkProvider) chunkProviderField.get(provider);
			chunkProviderField.setAccessible(false);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		ChunkPosition position = btwgProvider.villageGenerator.getNearestInstance(MinecraftServer.getServer().worldServers[0], coords.posX, coords.posY, coords.posZ);
		
		if (position != null)
			var1.sendChatToPlayer("Nearest village at: " + position.x + ", " + position.z);
		else
			var1.sendChatToPlayer("No village nearby");
		
		if (MinecraftServer.getServer().worldServers[0].getWorldChunkManager().areBiomesViable(coords.posX, coords.posZ, 0, BTABiomeConfiguration.getVillageBiomes())) {
			var1.sendChatToPlayer("Chunks are valid");
		}
	}

}
