package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenMeadow extends BTWGBiomeGenBase {
	public BTWGBiomeGenMeadow(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 1;
		this.btwgBiomeDecorator.grassPerChunk = 15;
		this.btwgBiomeDecorator.flowersPerChunk = 30;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new BTWGWorldGenMeadowTree2() : new BTWGWorldGenMeadowTree1();
	}
}