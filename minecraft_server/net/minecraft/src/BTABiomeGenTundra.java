package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenTundra extends BTABiomeGenBase {
	public BTABiomeGenTundra(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.flowersPerChunk = -999;
		this.btaBiomeDecorator.treesPerChunk = 2;
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