package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;

public class OrchardRiverBiome extends RiverBiome {
	public OrchardRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.orchard.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.orchard.getBiomeGrassColor();
    }
}