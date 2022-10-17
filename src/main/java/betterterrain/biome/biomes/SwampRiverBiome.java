package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.SpawnListEntry;

public class SwampRiverBiome extends RiverBiome {
	public SwampRiverBiome(int id, String internalName) {
		super(id, internalName, BTABiomeConfiguration.swamp.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.swamp.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.swamp.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.swamp.getBiomeFoliageColor();
    }
}