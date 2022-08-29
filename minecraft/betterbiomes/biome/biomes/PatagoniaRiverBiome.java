package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;

public class PatagoniaRiverBiome extends RiverBiome {
	public PatagoniaRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.patagonia.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.patagonia.getBiomeGrassColor();
    }
}