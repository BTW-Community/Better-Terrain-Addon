package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenOasis extends BTWGBiomeGenBase {
	public BTWGBiomeGenOasis(int par1)
	{
		super(par1);
		this.btwgBiomeDecorator.treesPerChunk = 10;
		this.btwgBiomeDecorator.grassPerChunk = 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return new BTWGWorldGenPalmTreeSmall(false);
	}

	@Override
    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}