package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenIcyPeaksForested extends BTABiomeGenBase {
	protected BTABiomeGenIcyPeaksForested(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 3;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new BTAWorldGenTaiga5(false) : new WorldGenTaiga2(false));
    }

    public void decorate(World var1, Random var2, int var3, int var4, BTAWorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}