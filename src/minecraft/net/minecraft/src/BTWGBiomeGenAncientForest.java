package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenAncientForest extends BTWGBiomeGenBase {
    public BTWGBiomeGenAncientForest(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btwgBiomeDecorator.treesPerChunk = 10;
        this.btwgBiomeDecorator.grassPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(7) == 0) {
    		gen = new BTWGWorldGenAncientTree(false);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = this.worldGeneratorBigTree;
    	}
    	else {
    		gen = this.worldGeneratorTrees;
    	}
    	
    	return gen;
    }
}