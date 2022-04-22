package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.AutmnTreeGen;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class AutmnForestBiome extends BTABiome {
	public AutmnForestBiome(int id, Climate climate) {
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
    		gen = new AutmnTreeGen(r % 3);
    	}
    	
    	return gen;
    }
}