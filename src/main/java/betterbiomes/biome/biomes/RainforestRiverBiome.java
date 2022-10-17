package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import btw.entity.mob.*;
import net.minecraft.src.SpawnListEntry;

public class RainforestRiverBiome extends RiverBiome {
	public RainforestRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.rainforest.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.rainforest.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(JungleSpiderEntity.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(SpiderEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(ZombieEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(SkeletonEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(CreeperEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EndermanEntity.class, 1, 1, 4));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.rainforest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.rainforest.getBiomeFoliageColor();
    }
}