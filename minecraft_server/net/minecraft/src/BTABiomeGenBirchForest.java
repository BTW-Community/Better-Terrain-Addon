package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenBirchForest extends BTABiomeGenBase {
    public BTABiomeGenBirchForest(int id, BTAEnumClimate climate) {
        super(id, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorForest;
    }
}