package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;

public class BTABiomeGenRiverWetlands extends BTABiomeGenRiver {
	public BTABiomeGenRiverWetlands(int par1) {
		super(par1, BTABiomeConfiguration.wetlands.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.wetlands.waterColorMultiplier;
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
    	return BTABiomeConfiguration.wetlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.wetlands.getBiomeFoliageColor();
    }
}