package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenFrozenSpringPond extends BTABiomeGenBase {
	public BTABiomeGenFrozenSpringPond(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 5;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand, BTAWorldConfigurationInfo generatorOptions, WorldType worldType)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0 && BTADecoIntegration.isDecoInstalled() && worldType.isDeco()) {
    		gen = new BTAWorldGenCherryTree();
    	}
    	else {
    		gen = this.worldGeneratorForest;
    	}
    	
    	return gen;
    }
}