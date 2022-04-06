package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;

public class BTABiomeGenRiverPatagonia extends BTABiomeGenRiver {
	public BTABiomeGenRiverPatagonia(int par1) {
		super(par1, BTABiomeConfiguration.patagonia.climate);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.patagonia.getBiomeGrassColor();
    }
}