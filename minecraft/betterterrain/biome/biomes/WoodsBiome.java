package betterterrain.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.LogGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class WoodsBiome extends BTABiome {
    public WoodsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0) {
    		gen = this.worldGeneratorForest;
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new LogGen();
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else {
    		gen = new WorldGenTrees(false);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}