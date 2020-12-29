package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenChaparral extends BTABiomeGenBase {
	protected BTABiomeGenChaparral(int id) {
		super(id);
		btwgBiomeDecorator.treesPerChunk = 8;
		btwgBiomeDecorator.grassPerChunk = 20;
		btwgBiomeDecorator.generateStoneInGrass = true;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenShrubSmall();
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new BTAWorldGenShrubTiny();
    	}
    	
    	return gen;
    }
}