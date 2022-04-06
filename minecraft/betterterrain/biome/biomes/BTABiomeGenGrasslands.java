package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenOldOak;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenGrasslands extends BTABiomeGenBase {
	public BTABiomeGenGrasslands(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 20;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(2) == 0) {
    		gen = new BTAWorldGenOldOak(false);
    	}
    	else {
    		gen = new WorldGenBigTree(false);
    	}
    	
    	return gen;
    }
}