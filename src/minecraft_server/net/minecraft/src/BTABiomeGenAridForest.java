package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenAridForest extends BTABiomeGenBase {
	protected BTABiomeGenAridForest(int id, BTAEnumClimate climate) {
		super(id, climate);
		btaBiomeDecorator.treesPerChunk = 8;
		btaBiomeDecorator.grassPerChunk = 20;
		btaBiomeDecorator.generateStoneInGrass = true;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	

    	
    	if (rand.nextInt(10) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new WorldGenTrees(false);
    	}
    	else if (rand.nextInt(3) == 0) {
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