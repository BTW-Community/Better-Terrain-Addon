package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenSavanna extends BTWGBiomeGenBase {

	public BTWGBiomeGenSavanna(int id) {
		super(id);
        this.btwgBiomeDecorator.treesPerChunk = 2;
        this.btwgBiomeDecorator.grassPerChunk = 25;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(3) == 0 ? new BTWGWorldGenAcacia(false) : new WorldGenShrub(0,0);
	}
}