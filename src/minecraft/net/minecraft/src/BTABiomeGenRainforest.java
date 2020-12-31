package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenRainforest extends BTABiomeGenBase {

	public BTABiomeGenRainforest(int id) {
		super(id);
		this.btwgBiomeDecorator.treesPerChunk = 25;
		this.btwgBiomeDecorator.grassPerChunk = 7;
		this.btwgBiomeDecorator.reedsPerChunk = 10;
        this.btwgBiomeDecorator.waterlilyPerChunk = 4;
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
    		gen = new BTAWorldGenRainforest2();
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new BTAWorldGenRainforest1(true);
    	}
    	else {
    		gen = new WorldGenShrub(3, 3);
    	}
    	
    	return gen;
	}

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenVines var5 = new WorldGenVines();

        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 64;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }
}