package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.SmallShrubGen;
import betterbiomes.feature.tree.TinyShrubGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class PlainsBiome extends BTABiome {
	public PlainsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.fractionalTreeChance = 5;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 20;
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