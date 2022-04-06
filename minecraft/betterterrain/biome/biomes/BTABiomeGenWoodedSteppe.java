package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenShrubSmall;
import betterterrain.feature.BTAWorldGenShrubTiny;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenWoodedSteppe extends BTABiomeGenSteppe {
	public BTABiomeGenWoodedSteppe(int id, BTAEnumClimate climate) {
		super(id, climate);
		btaBiomeDecorator.treesPerChunk = 15;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenShrubSmall();
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new BTAWorldGenShrubTiny();
    	}
    	
    	return gen;
    }
}