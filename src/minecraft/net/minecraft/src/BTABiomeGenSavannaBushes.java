package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenSavannaBushes extends BTABiomeGenBase {

	public BTABiomeGenSavannaBushes(int id) {
		super(id);
        this.btaiomeDecorator.treesPerChunk = 10;
        this.btaiomeDecorator.grassPerChunk = 25;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(8) == 0 ? new BTAWorldGenAcacia(false) : new WorldGenShrub(0,0);
	}
}