package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenAspenGrove extends BTWGBiomeGenAlpine {
	protected BTWGBiomeGenAspenGrove(int id) {
		super(id);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(7) == 0 ? new WorldGenTaiga2(false) : this.worldGeneratorForest);
    }
}