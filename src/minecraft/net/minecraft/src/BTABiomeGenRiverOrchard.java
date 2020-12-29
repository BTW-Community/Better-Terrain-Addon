package net.minecraft.src;

public class BTABiomeGenRiverOrchard extends BTABiomeGenBase {
	public BTABiomeGenRiverOrchard(int par1) {
		super(par1);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.orchard.getBiomeGrassColor();
    }
}