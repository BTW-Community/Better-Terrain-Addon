package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenMountain extends BTABiomeGenBase {

	public BTABiomeGenMountain(int id) {
		super(id);
		this.btaiomeDecorator.treesPerChunk = 2;
		this.btaiomeDecorator.grassPerChunk = 3;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? worldGeneratorTrees : new BTAWorldGenPineTree(false);
	}

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}