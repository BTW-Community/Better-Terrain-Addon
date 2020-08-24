package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenBorealForest extends BTWGBiomeGenBase {
	public BTWGBiomeGenBorealForest(int id) {
		super(id);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btwgBiomeDecorator.treesPerChunk = 30;
        this.btwgBiomeDecorator.grassPerChunk = 5;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(4) == 0) {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTWGWorldGenBorealBirch();
    	}
    	else {
    		gen = new BTWGWorldGenTaiga6(false);
    	}
    	
    	return gen;
    }
}