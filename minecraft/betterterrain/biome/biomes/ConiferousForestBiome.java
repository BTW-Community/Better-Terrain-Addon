package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.TaigaGen3;
import betterterrain.feature.TaigaGen4;
import betterterrain.feature.TaigaGen7;
import betterterrain.feature.TallGrassGen;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class ConiferousForestBiome extends BTABiome {
	public ConiferousForestBiome(int id, Climate climate) {
		super(id, climate);
		spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 8, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 8;
        this.btaBiomeDecorator.grassPerChunk = 10;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0) {
    		gen = new TaigaGen3(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen4(false);
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
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}