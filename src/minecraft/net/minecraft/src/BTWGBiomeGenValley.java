package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenValley extends BTWGBiomeGenBase {
	public BTWGBiomeGenValley(int id) {
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