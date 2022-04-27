package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;

public class MysticRiverBiome extends RiverBiome {
	public MysticRiverBiome(int par1) {
		super(par1, BetterBiomesConfiguration.mysticForest.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.mysticForest.waterColorMultiplier;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.mysticForest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.mysticForest.getBiomeFoliageColor();
    }
}