package betterterrain.world;

import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.World;

public class NetherWorldChunkManager extends BTAWorldChunkManager {
	public NetherWorldChunkManager(WorldConfigurationInfo generatorInfo) {
		super(generatorInfo);
	}

	public NetherWorldChunkManager(World world, WorldConfigurationInfo generatorInfo) {
		super(world, generatorInfo);
	}
}
