package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenRiverBadlands extends BTABiomeGenRiver {
	public BTABiomeGenRiverBadlands(int par1) {
		super(par1, BTABiomeConfiguration.badlands.climate);
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
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
    	return BTABiomeConfiguration.badlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.badlands.getBiomeFoliageColor();
    }
}