package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenMangroveSwamp extends BTABiomeGenBase {

	public BTABiomeGenMangroveSwamp(int id) {
		super(id);
        this.btwgBiomeDecorator.treesPerChunk = 10;
        this.btwgBiomeDecorator.flowersPerChunk = -999;
        this.btwgBiomeDecorator.deadBushPerChunk = 1;
        this.btwgBiomeDecorator.mushroomsPerChunk = 8;
        this.btwgBiomeDecorator.reedsPerChunk = 10;
        this.btwgBiomeDecorator.clayPerChunk = 1;
        this.btwgBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(BTAEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		//return new BTWGWorldGenMangrove();
		return this.worldGeneratorSwamp;
	}
}