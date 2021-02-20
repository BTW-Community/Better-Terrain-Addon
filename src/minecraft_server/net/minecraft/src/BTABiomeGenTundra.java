package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenTundra extends BTABiomeGenBase {
	public BTABiomeGenTundra(int id) {
		super(id);
		this.btaiomeDecorator.flowersPerChunk = -999;
		this.btaiomeDecorator.treesPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = this.worldGeneratorTrees;
    	}
    	else {
    		gen = new WorldGenShrub(1, 1);
    	}
    	
    	return gen;
    }
}