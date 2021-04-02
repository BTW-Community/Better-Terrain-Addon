package net.minecraft.src;

public class BTABiomeGenRiverRainforest extends BTABiomeGenRiver {
	protected BTABiomeGenRiverRainforest(int id) {
		super(id, BTABiomeConfiguration.rainforest.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.rainforest.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityJungleSpider.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySpider.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityZombie.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySkeleton.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityCreeper.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityEnderman.class, 1, 1, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityOcelot.class, 2, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.rainforest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.rainforest.getBiomeFoliageColor();
    }
}