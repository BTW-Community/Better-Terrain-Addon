package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOasis extends BTABiomeGenBase {
	public BTABiomeGenOasis(int par1, BTAEnumClimate climate)
	{
		super(par1, climate);
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 2;
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
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