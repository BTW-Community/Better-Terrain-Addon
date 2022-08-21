package betterbiomes.biome.biomes.deprecated;

import java.util.Random;

import betterbiomes.biome.biomes.SteppeBiome;
import betterbiomes.feature.tree.SmallShrubGen;
import betterbiomes.feature.tree.TinyShrubGen;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class WoodedSteppeBiome extends SteppeBiome {
	public WoodedSteppeBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		btaBiomeDecorator.treesPerChunk = 15;
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
    	else if (rand.nextInt(10) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new TinyShrubGen();
    	}
    	
    	return gen;
    }
}