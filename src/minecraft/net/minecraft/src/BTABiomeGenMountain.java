package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenMountain extends BTABiomeGenBase {

	public BTABiomeGenMountain(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 2;
		this.btwgBiomeDecorator.grassPerChunk = 3;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? worldGeneratorTrees : new BTAWorldGenPineTree(false);
	}
}