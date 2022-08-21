package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.SmallShrubGen;
import betterbiomes.feature.tree.TinyShrubGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class AridForestBiome extends BTABiome {
	public AridForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
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
    	

    	
    	if (rand.nextInt(10) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new WorldGenTrees(false);
    	}
    	else if (rand.nextInt(3) == 0) {
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