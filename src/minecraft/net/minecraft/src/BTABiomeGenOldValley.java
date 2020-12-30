package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOldValley extends BTABiomeGenBase {
	public BTABiomeGenOldValley(int id) {
		super(id);
        this.btwgBiomeDecorator.treesPerChunk = 4;
        this.btwgBiomeDecorator.grassPerChunk = 4;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorTrees;
    }
}