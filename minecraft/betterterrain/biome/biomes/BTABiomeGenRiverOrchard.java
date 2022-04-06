package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;

public class BTABiomeGenRiverOrchard extends BTABiomeGenRiver {
	public BTABiomeGenRiverOrchard(int par1) {
		super(par1, BTABiomeConfiguration.orchard.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.orchard.getBiomeGrassColor();
    }
}