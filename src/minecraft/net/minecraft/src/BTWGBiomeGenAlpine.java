package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenAlpine extends BTWGBiomeGenBase {
	protected BTWGBiomeGenAlpine(int id) {
		super(id);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btwgBiomeDecorator.treesPerChunk = 20;
        this.btwgBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new BTWGWorldGenTaiga5(false) : new WorldGenTaiga2(false));
    }
}