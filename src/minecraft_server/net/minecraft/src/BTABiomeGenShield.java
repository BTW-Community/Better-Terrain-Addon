package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenShield extends BTABiomeGenBase {
	protected BTABiomeGenShield(int id, BTAEnumClimate climate) {
		super(id, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 7;
        this.btaBiomeDecorator.grassPerChunk = 2;
        this.btaBiomeDecorator.generateStoneInGrass = true;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(4) == 0) {
    		gen = new BTAWorldGenTaiga4(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenTaiga7(false);
    	}
    	else {
    		gen = new BTAWorldGenTaiga6(false);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new BTAWorldGenTallGrass(Block.tallGrass.blockID, 2) : new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
	}
}