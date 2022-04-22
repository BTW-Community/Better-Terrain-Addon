package betterterrain.biome.biomes;

import betterterrain.biome.BiomeConfiguration;

public class OrchardRiverBiome extends RiverBiome {
	public OrchardRiverBiome(int par1) {
		super(par1, BiomeConfiguration.orchard.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BiomeConfiguration.orchard.getBiomeGrassColor();
    }
}