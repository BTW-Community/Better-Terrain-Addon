package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.SpawnListEntry;

public class WillowRiverBiome extends RiverBiome {
	public WillowRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.willowGrove.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.willowGrove.waterColorMultiplier;
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
    	return BetterBiomesConfiguration.willowGrove.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.willowGrove.getBiomeFoliageColor();
    }
}