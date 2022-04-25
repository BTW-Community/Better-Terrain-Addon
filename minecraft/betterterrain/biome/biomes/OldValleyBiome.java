package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenerator;

public class OldValleyBiome extends BTABiome {
	public OldValleyBiome(int id, Climate climate) {
		super(id, climate);
        this.btaBiomeDecorator.treesPerChunk = 4;
        this.btaBiomeDecorator.grassPerChunk = 4;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorTrees;
    }
}