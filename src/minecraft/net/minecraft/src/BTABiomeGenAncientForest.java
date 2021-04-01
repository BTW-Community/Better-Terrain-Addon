package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenAncientForest extends BTABiomeGenBase {
    public BTABiomeGenAncientForest(int par1, BTAEnumClimate climate)
    {
        super(par1, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(7) == 0) {
    		gen = new BTAWorldGenOldOak(false);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = this.worldGeneratorBigTree;
    	}
    	else {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	
    	return gen;
    }
}