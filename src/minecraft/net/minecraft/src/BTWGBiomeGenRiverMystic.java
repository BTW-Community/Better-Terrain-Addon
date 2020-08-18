package net.minecraft.src;

public class BTWGBiomeGenRiverMystic extends BTWGBiomeGenBase {
	public BTWGBiomeGenRiverMystic(int par1) {
		super(par1);
		this.waterColorMultiplier = BTWGBiomeConfiguration.mysticForest.waterColorMultiplier;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTWGBiomeConfiguration.mysticForest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTWGBiomeConfiguration.mysticForest.getBiomeFoliageColor();
    }
}