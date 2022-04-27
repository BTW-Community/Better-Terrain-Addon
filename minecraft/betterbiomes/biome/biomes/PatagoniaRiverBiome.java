package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;

public class PatagoniaRiverBiome extends RiverBiome {
	public PatagoniaRiverBiome(int par1) {
		super(par1, BetterBiomesConfiguration.patagonia.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.patagonia.getBiomeGrassColor();
    }
}