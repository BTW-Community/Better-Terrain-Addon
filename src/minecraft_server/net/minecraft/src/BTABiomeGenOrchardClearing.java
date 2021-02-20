package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOrchardClearing extends BTABiomeGenBase {
	public BTABiomeGenOrchardClearing(int id) {
		super(id);
		this.btaiomeDecorator.treesPerChunk = 3;
		this.btaiomeDecorator.grassPerChunk = 30;
		this.btaiomeDecorator.flowersPerChunk = 30;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return new WorldGenTrees(false, 6, 0, 0, false);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 13236061;
    }
}