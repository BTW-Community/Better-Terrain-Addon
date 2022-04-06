package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiomeConfiguration;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;

public class BTABiomeGenRiverMystic extends BTABiomeGenRiver {
	public BTABiomeGenRiverMystic(int par1) {
		super(par1, BTABiomeConfiguration.mysticForest.climate);
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