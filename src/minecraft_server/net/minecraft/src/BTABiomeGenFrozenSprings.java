package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenFrozenSprings extends BTABiomeGenBase {
	public BTABiomeGenFrozenSprings(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 2;
		this.btaBiomeDecorator.grassPerChunk = 5;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0 && BTADecoIntegration.isDecoInstalled()) {
    		gen = new BTAWorldGenCherryTree();
    	}
    	else {
    		gen = this.worldGeneratorForest;
    	}
    	
    	return gen;
    }
}