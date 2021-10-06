package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenSiberia extends BTABiomeGenBase {
	protected BTABiomeGenSiberia(int id, BTAEnumClimate climate) {
		super(id, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 20;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(7) == 0) {
    		gen = new WorldGenTaiga2(false);
    	}
    	else if (rand.nextInt(6) == 0) {
    		gen = new BTAWorldGenTaiga5(false);
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new BTAWorldGenTaiga6(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new BTAWorldGenTaiga7(false);
    	}
    	else {
    		gen = new BTAWorldGenShrubSmall(1, 1);
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