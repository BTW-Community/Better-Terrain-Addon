package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.OldOakGen;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class OrchardBiome extends BTABiome {
	public OrchardBiome(int id, Climate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 3;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 20;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(20) == 0) {
    		gen = new OldOakGen(false);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	
    	return gen;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 13236061;
    }
}