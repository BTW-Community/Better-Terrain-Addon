package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenRainforest extends BTWGBiomeGenBase {

	public BTWGBiomeGenRainforest(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 25;
		this.btwgBiomeDecorator.grassPerChunk = 7;
		this.btwgBiomeDecorator.reedsPerChunk = 10;
		this.waterColorMultiplier = 6160128;
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
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
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = new BTWGWorldGenRainforest2();
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new BTWGWorldGenRainforest1(false);
    	}
    	else {
    		gen = new WorldGenShrub(3, 3);
    	}
    	
    	return gen;
	}
}