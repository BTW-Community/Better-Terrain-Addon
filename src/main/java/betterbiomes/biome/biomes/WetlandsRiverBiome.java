package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.SpawnListEntry;

public class WetlandsRiverBiome extends RiverBiome {
	public WetlandsRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.wetlands.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.wetlands.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.wetlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.wetlands.getBiomeFoliageColor();
    }
}