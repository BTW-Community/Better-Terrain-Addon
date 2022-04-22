package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.SmallShrubGen;
import betterterrain.feature.TinyShrubGen;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class ChapparalBiome extends BTABiome {
	public ChapparalBiome(int id, Climate climate) {
		super(id, climate);
		btaBiomeDecorator.treesPerChunk = 8;
		btaBiomeDecorator.grassPerChunk = 20;
		btaBiomeDecorator.generateStoneInGrass = true;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new SmallShrubGen();
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new TinyShrubGen();
    	}
    	
    	return gen;
    }
}