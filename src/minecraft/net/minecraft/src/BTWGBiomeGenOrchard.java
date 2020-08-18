package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenOrchard extends BTWGBiomeGenBase {
	public BTWGBiomeGenOrchard(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 3;
		this.btwgBiomeDecorator.grassPerChunk = 30;
		this.btwgBiomeDecorator.flowersPerChunk = 8;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(2) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	
    	return gen;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 13236061;
    }
}