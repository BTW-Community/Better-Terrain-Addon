package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenHighlands extends BTABiomeGenBase {

	public BTABiomeGenHighlands(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.fractionalTreeChance = 2;
		this.btaBiomeDecorator.grassPerChunk = 3;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? worldGeneratorTrees : new WorldGenShrub(0, 0);
	}

    public void decorate(World var1, Random var2, int var3, int var4, BTAWorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}