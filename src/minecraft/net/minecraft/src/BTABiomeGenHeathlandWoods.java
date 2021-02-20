package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenHeathlandWoods extends BTABiomeGenBase {
	public BTABiomeGenHeathlandWoods(int id) {
		super(id);
		this.btaiomeDecorator.treesPerChunk = 12;
		this.btaiomeDecorator.grassPerChunk = 15;
		this.btaiomeDecorator.flowersPerChunk = 30;
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