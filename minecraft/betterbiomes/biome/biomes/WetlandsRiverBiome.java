package betterbiomes.biome.biomes;

import betterterrain.biome.BiomeConfiguration;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;

public class WetlandsRiverBiome extends RiverBiome {
	public WetlandsRiverBiome(int par1) {
		super(par1, BiomeConfiguration.wetlands.climate);
		this.waterColorMultiplier = BiomeConfiguration.wetlands.waterColorMultiplier;
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
    	return BiomeConfiguration.wetlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BiomeConfiguration.wetlands.getBiomeFoliageColor();
    }
}