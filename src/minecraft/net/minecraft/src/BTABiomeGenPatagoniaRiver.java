package net.minecraft.src;

public class BTABiomeGenPatagoniaRiver extends BTABiomeGenBase {
	public BTABiomeGenPatagoniaRiver(int par1) {
		super(par1);
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.patagonia.getBiomeGrassColor();
    }
}