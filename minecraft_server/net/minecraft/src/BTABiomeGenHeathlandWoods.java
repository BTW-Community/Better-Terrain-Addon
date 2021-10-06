package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenHeathlandWoods extends BTABiomeGenBase {
	public BTABiomeGenHeathlandWoods(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 12;
		this.btaBiomeDecorator.grassPerChunk = 15;
		this.btaBiomeDecorator.flowersPerChunk = 30;
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