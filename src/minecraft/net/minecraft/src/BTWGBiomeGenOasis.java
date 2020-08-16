package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenOasis extends BTWGBiomeGenBase {
	public BTWGBiomeGenOasis(int par1)
	{
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
	}
}