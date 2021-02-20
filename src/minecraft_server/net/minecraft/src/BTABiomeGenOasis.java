package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOasis extends BTABiomeGenBase {
	public BTABiomeGenOasis(int par1)
	{
		super(par1);
		this.btaiomeDecorator.treesPerChunk = 10;
		this.btaiomeDecorator.grassPerChunk = 2;
		this.btaiomeDecorator.sandPerChunk = 0;
		this.btaiomeDecorator.sandPerChunk2 = 0;
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