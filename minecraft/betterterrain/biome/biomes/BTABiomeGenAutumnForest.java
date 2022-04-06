package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenAutumnTree;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenAutumnForest extends BTABiomeGenBase {
	public BTABiomeGenAutumnForest(int id, BTAEnumClimate climate) {
		super(id, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	int r = rand.nextInt(7);
    	
    	if (r == 6) {
    		gen = this.worldGeneratorForest;
    	}
    	else {
    		gen = new BTAWorldGenAutumnTree(r % 3);
    	}
    	
    	return gen;
    }
}