package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenConiferousForestClearing extends BTABiomeGenBase {
	public BTABiomeGenConiferousForestClearing(int id, BTAEnumClimate climate) {
		super(id, climate);
		spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 8, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 3;
        this.btaBiomeDecorator.grassPerChunk = 15;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0) {
    		gen = new WorldGenTaiga2(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenTaiga5(false);
    	}
    	else {
    		gen = new BTAWorldGenTaiga7(false);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? new BTAWorldGenTallGrass(Block.tallGrass.blockID, 2) : new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
	}
}