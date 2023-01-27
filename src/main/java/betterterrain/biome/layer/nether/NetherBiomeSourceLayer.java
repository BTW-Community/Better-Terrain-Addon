package betterterrain.biome.layer.nether;

import betterterrain.biome.layer.BTALayer;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.GenLayer;
import net.minecraft.src.WorldType;

public abstract class NetherBiomeSourceLayer extends BTALayer {
    public NetherBiomeSourceLayer(long seed) {
        super(seed);
    }

    public static GenLayer initializeAllNetherBiomeGenerators(long seed, WorldType worldType, WorldConfigurationInfo generatorInfo) {
        return null;
    }
}
