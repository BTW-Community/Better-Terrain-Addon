package net.minecraft.src;

public class BTABiomeGenRiverWillow extends BTABiomeGenBase {
	public BTABiomeGenRiverWillow(int par1) {
		super(par1, BTABiomeConfiguration.willowGrove.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.willowGrove.waterColorMultiplier;
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
    	return BTABiomeConfiguration.willowGrove.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.willowGrove.getBiomeFoliageColor();
    }
}