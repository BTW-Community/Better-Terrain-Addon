package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class BTACommandBiome extends CommandBase {
	@Override
	public String getCommandName() {
		return "biome";
	}

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		ChunkCoordinates coords = var1.getPlayerCoordinates();
		
		BiomeGenBase biome = MinecraftServer.getServer().worldServers[0].getBiomeGenForCoords(coords.posX, coords.posZ);
		
		var1.sendChatToPlayer("Current biome: " + biome.biomeName);
	}
}