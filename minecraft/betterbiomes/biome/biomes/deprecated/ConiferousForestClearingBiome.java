package betterbiomes.biome.biomes.deprecated;

import java.util.Random;

import betterbiomes.feature.tree.TaigaGen5;
import betterbiomes.feature.tree.TaigaGen7;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class ConiferousForestClearingBiome extends BTABiome {
	public ConiferousForestClearingBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 8, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 3;
        this.btaBiomeDecorator.grassPerChunk = 15;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0) {
    		gen = new WorldGenTaiga2(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen5(false);
    	}
    	else {
    		gen = new TaigaGen7(false);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}