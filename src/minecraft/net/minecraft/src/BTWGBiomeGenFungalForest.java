package net.minecraft.src;

import java.util.Random;

public class BTWGBiomeGenFungalForest extends BTWGBiomeGenBase {
	protected BTWGBiomeGenFungalForest(int id) {
		super(id);
        this.btwgBiomeDecorator.treesPerChunk = 15;
        this.btwgBiomeDecorator.grassPerChunk = 2;
        this.btwgBiomeDecorator.bigRedMushroomsPerChunk = 5;
        this.btwgBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new BTWGWorldGenMassiveOak(true);
    	}
    	else {
    		gen = new BTWGWorldGenSwampTreeTall();
    	}
    	
    	return gen;
    }
}