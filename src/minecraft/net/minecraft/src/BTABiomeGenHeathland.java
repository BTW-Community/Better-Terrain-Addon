package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenHeathland extends BTABiomeGenBase {
	public BTABiomeGenHeathland(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 7;
		this.btwgBiomeDecorator.grassPerChunk = 30;
		this.btwgBiomeDecorator.flowersPerChunk = 15;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(3) == 0 ? this.worldGeneratorTrees : new WorldGenShrub(0,0);
	}
}