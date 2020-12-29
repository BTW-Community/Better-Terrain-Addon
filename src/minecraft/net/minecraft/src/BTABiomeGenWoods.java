package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenWoods extends BTABiomeGenBase {
    public BTABiomeGenWoods(int par1)
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
    	
    	if (rand.nextInt(5) == 0) {
    		gen = this.worldGeneratorForest;
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new BTAWorldGenLog();
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = this.worldGeneratorBigTree;
    	}
    	else {
    		gen = this.worldGeneratorTrees;
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}
}