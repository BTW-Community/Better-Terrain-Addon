package betterterrain;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.World;

public class BiomeCommand extends CommandBase {
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
		
		World world;
		
		if (var1 instanceof EntityPlayer) {
			world = ((EntityPlayer) var1).worldObj;
		}
		else {
			world = MinecraftServer.getServer().worldServers[0];
		}
		
		BiomeGenBase biome = world.getBiomeGenForCoords(coords.posX, coords.posZ);
		
		var1.sendChatToPlayer("Current biome: " + biome.biomeName);
	}
}