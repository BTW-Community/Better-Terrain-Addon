package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenOldValley extends BTABiomeGenBase {
	public BTABiomeGenOldValley(int id, BTAEnumClimate climate) {
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