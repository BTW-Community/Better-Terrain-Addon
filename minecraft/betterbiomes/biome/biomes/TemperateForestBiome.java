package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.TaigaGen6;
import betterbiomes.feature.tree.TemperateBirchGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class TemperateForestBiome extends BTABiome {
	public TemperateForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
		this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.treesPerChunk = 30;
        this.btaBiomeDecorator.grassPerChunk = 5;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(4) == 0) {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TemperateBirchGen();
    	}
    	else {
    		gen = new TaigaGen6(false);
    	}
    	
    	return gen;
    }
}