package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.SpawnListEntry;

public class MysticRiverBiome extends RiverBiome {
	public MysticRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.mysticValley.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.mysticValley.waterColorMultiplier;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.mysticValley.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.mysticValley.getBiomeFoliageColor();
    }
}