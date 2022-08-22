package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;

public class WillowRiverBiome extends RiverBiome {
	public WillowRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.willowGrove.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.willowGrove.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
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