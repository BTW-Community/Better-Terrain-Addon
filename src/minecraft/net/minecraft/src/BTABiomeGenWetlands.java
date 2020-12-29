package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenWetlands extends BTABiomeGenBase {

	public BTABiomeGenWetlands(int id) {
		super(id);
        this.btwgBiomeDecorator.treesPerChunk = 10;
        this.btwgBiomeDecorator.flowersPerChunk = -999;
        this.btwgBiomeDecorator.deadBushPerChunk = 1;
        this.btwgBiomeDecorator.mushroomsPerChunk = 8;
        this.btwgBiomeDecorator.reedsPerChunk = 10;
        this.btwgBiomeDecorator.clayPerChunk = 1;
        this.btwgBiomeDecorator.waterlilyPerChunk = 4;
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
}