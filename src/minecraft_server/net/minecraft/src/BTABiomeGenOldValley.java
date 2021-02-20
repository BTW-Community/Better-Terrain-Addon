package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOldValley extends BTABiomeGenBase {
	public BTABiomeGenOldValley(int id) {
		super(id);
        this.btaiomeDecorator.treesPerChunk = 4;
        this.btaiomeDecorator.grassPerChunk = 4;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorTrees;
    }
}