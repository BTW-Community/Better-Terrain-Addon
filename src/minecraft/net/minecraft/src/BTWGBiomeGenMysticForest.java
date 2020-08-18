package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenMysticForest extends BTWGBiomeGenBase {
	public BTWGBiomeGenMysticForest(int id) {
		super(id);
		waterColorMultiplier = 15349914;
		this.btwgBiomeDecorator.treesPerChunk = 15;
		this.btwgBiomeDecorator.grassPerChunk = 7;
		this.btwgBiomeDecorator.flowersPerChunk = 8;
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
    		gen = new BTWGWorldGenMassiveOak(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new BTWGWorldGenSwampTreeTall();
    	}
    	else {
    		gen = new BTWGWorldGenMystic(false);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
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