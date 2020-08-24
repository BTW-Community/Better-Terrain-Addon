package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenAutumnForest extends BTWGBiomeGenBase {
	public BTWGBiomeGenAutumnForest(int id) {
		super(id);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btwgBiomeDecorator.treesPerChunk = 15;
        this.btwgBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = this.worldGeneratorForest;
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTWGWorldGenLog();
    	}
    	else {
    		gen = this.worldGeneratorTrees;
    	}
    	
    	return gen;
    }
}