package betterterrain.biome.biomes;

import betterterrain.biome.BiomeConfiguration;

public class PatagoniaRiverBiome extends RiverBiome {
	public PatagoniaRiverBiome(int par1) {
		super(par1, BiomeConfiguration.patagonia.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BiomeConfiguration.patagonia.getBiomeGrassColor();
    }
}