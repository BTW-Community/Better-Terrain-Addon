package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenWetlands extends BTABiomeGenBase {

	public BTABiomeGenWetlands(int id) {
		super(id);
        this.btaiomeDecorator.treesPerChunk = 10;
        this.btaiomeDecorator.flowersPerChunk = -999;
        this.btaiomeDecorator.deadBushPerChunk = 1;
        this.btaiomeDecorator.mushroomsPerChunk = 8;
        this.btaiomeDecorator.reedsPerChunk = 10;
        this.btaiomeDecorator.clayPerChunk = 1;
        this.btaiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
        this.waterColorMultiplier = 10083127;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(7) == 0) {
    		gen = new BTAWorldGenSwampTreeTall();
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenTaiga5(false);
    	}
    	else {
    		gen = this.worldGeneratorSwamp;
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