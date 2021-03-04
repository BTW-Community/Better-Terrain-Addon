package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenAspenGrove extends BTABiomeGenAlpine {
	protected BTABiomeGenAspenGrove(int id) {
		super(id);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new BTAWorldGenTaiga5(false) : new BTAWorldGenPineTree(false, 2, 2));
    }

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}