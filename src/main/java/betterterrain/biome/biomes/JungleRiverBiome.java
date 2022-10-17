package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;
import btw.entity.mob.*;
import net.minecraft.src.SpawnListEntry;

public class JungleRiverBiome extends RiverBiome {
	public JungleRiverBiome(int id, String internalName) {
		super(id, internalName, BTABiomeConfiguration.jungle.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.jungle.waterColorMultiplier;
		this.btaBiomeDecorator.sandPerChunk = 100;
		this.btaBiomeDecorator.sandPerChunk2 = 100;
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
		this.spawnableMonsterList.add(new SpawnListEntry(OcelotEntity.class, 2, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.jungle.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.jungle.getBiomeFoliageColor();
    }
}