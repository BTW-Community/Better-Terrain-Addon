package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenSahel extends BTABiomeGenBase {
	public BTABiomeGenSahel(int id) {
		super(id);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(3) == 0 ? new BTAWorldGenAcacia(false) : new WorldGenShrub(0,0);
	}
}