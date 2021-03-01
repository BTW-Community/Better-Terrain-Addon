package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenRiverMystic extends BTABiomeGenBase {
	public BTABiomeGenRiverMystic(int par1) {
		super(par1);
		this.waterColorMultiplier = BTABiomeConfiguration.mysticForest.waterColorMultiplier;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.mysticForest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.mysticForest.getBiomeFoliageColor();
    }
}