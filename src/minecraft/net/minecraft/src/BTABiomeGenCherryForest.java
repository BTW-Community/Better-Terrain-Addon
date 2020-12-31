package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenCherryForest extends BTABiomeGenBase {
    public BTABiomeGenCherryForest(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btwgBiomeDecorator.treesPerChunk = 10;
        this.btwgBiomeDecorator.grassPerChunk = 2;
        this.btwgBiomeDecorator.flowersPerChunk = 15;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	/*
    	if (rand.nextInt(10) == 0) {
    		gen = new BTWGWorldGenCherryTreeBig(false);
    	}
    	else {
    		gen = new BTWGWorldGenCherryTree();
    	}
    	*/
		gen = new BTAWorldGenCherryTree();
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