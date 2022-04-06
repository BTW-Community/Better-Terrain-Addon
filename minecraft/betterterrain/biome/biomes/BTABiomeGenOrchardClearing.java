package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenOrchardClearing extends BTABiomeGenBase {
	public BTABiomeGenOrchardClearing(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 3;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 30;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return new WorldGenTrees(false, 6, 0, 0, false);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 13236061;
    }
}