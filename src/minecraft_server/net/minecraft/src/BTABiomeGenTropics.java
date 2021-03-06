package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenTropics extends BTABiomeGenBase {
	public BTABiomeGenTropics(int par1, BTAEnumClimate climate)
	{
		super(par1, climate);
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 2;
		this.btaBiomeDecorator.sandPerChunk = 100;
		this.btaBiomeDecorator.sandPerChunk2 = 100;
		this.btaBiomeDecorator.melonChancePerChunk = 32;
		this.waterColorMultiplier = 65396;
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
    	
    	if (rand.nextInt(2) == 0) {
    		gen = new BTAWorldGenPalmTreeSmall(false, true);;
    	}
    	else {
    		gen = new WorldGenShrub(3, 3);
    	}
    	
    	return gen;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new BTAWorldGenTallGrass(Block.tallGrass.blockID, 2) : new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}