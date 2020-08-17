package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenWoodedSteppe extends BTWGBiomeGenSteppe {
	public BTWGBiomeGenWoodedSteppe(int id) {
		super(id);
		btwgBiomeDecorator.treesPerChunk = 7;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new BTWGWorldGenShrubSmall();
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new BTWGWorldGenShrubTiny();
    	}
    	
    	return gen;
    }
}