package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.biome.BiomeConfiguration;

public class BadlandsRiverBiome extends RiverBiome {
	public BadlandsRiverBiome(int par1) {
		super(par1, BiomeConfiguration.badlands.climate);
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.redSand.blockID;
			this.fillerBlockExt = DecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
        this.spawnableCreatureList.clear();
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BiomeConfiguration.badlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BiomeConfiguration.badlands.getBiomeFoliageColor();
    }
}