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
}