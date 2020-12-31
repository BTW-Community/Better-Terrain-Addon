package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOasis extends BTABiomeGenBase {
	public BTABiomeGenOasis(int par1)
	{
		super(par1);
		this.btwgBiomeDecorator.treesPerChunk = 10;
		this.btwgBiomeDecorator.grassPerChunk = 2;
		this.btwgBiomeDecorator.sandPerChunk = 0;
		this.btwgBiomeDecorator.sandPerChunk2 = 0;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return new BTAWorldGenPalmTreeSmall(false, false);
	}

	@Override
    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}