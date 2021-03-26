package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenWillowGrove extends BTABiomeGenBase {

	public BTABiomeGenWillowGrove(int id) {
		super(id);
        this.btaiomeDecorator.treesPerChunk = 7;
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
        this.waterColorMultiplier = 3653595;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return new BTAWorldGenWillow();
    }
}