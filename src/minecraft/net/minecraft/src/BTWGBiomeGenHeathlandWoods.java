package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenHeathlandWoods extends BTWGBiomeGenBase {
	public BTWGBiomeGenHeathlandWoods(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 12;
		this.btwgBiomeDecorator.grassPerChunk = 15;
		this.btwgBiomeDecorator.flowersPerChunk = 30;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return new WorldGenTaiga2(false);
	}
}