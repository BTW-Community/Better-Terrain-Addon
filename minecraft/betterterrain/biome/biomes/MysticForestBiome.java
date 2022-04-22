package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.MassiveOakGen;
import betterterrain.feature.MysticTreeGen;
import betterterrain.feature.TallSwampTreeGen;
import betterterrain.feature.TallGrassGen;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class MysticForestBiome extends BTABiome {
	public MysticForestBiome(int id, Climate climate) {
		super(id, climate);
		waterColorMultiplier = 15349914;
		this.btaBiomeDecorator.treesPerChunk = 15;
		this.btaBiomeDecorator.grassPerChunk = 7;
		this.btaBiomeDecorator.flowersPerChunk = 8;
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = new MassiveOakGen(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new TallSwampTreeGen();
    	}
    	else {
    		gen = new MysticTreeGen(false);
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

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 634550;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return 11429335;
    }
}