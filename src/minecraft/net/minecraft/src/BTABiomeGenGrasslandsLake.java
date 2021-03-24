package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenGrasslandsLake extends BTABiomeGenBase {
	public BTABiomeGenGrasslandsLake(int id) {
		super(id);
		this.btaiomeDecorator.treesPerChunk = 10;
		this.btaiomeDecorator.grassPerChunk = 30;
		this.btaiomeDecorator.flowersPerChunk = 20;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(2) == 0) {
    		gen = new BTAWorldGenOldOak(false);
    	}
    	else {
    		gen = new WorldGenBigTree(false);
    	}
    	
    	return gen;
    }
}