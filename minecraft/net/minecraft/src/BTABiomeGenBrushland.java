package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenBrushland extends BTABiomeGenBase {
	public BTABiomeGenBrushland(int id, BTAEnumClimate climate) {
		super(id, climate);
		btaBiomeDecorator.treesPerChunk = 2;
		btaBiomeDecorator.grassPerChunk = 5;
		btaBiomeDecorator.generateStoneInGrass = true;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(6) == 0) {
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

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(7) == 0 ? new BTAWorldGenTallGrass(Block.tallGrass.blockID, 0) : new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
	}
}