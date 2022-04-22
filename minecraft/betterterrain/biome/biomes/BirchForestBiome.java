package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BirchForestBiome extends BTABiome {
    public BirchForestBiome(int id, Climate climate) {
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