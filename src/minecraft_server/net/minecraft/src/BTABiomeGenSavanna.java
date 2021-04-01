package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenSavanna extends BTABiomeGenBase {

	public BTABiomeGenSavanna(int id, BTAEnumClimate climate) {
		super(id, climate);
        this.btaBiomeDecorator.treesPerChunk = 2;
        this.btaBiomeDecorator.grassPerChunk = 25;
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