package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenWillow;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenWillowGrove extends BTABiomeGenBase {

	public BTABiomeGenWillowGrove(int id, BTAEnumClimate climate) {
		super(id, climate);
        this.btaBiomeDecorator.treesPerChunk = 7;
        this.btaBiomeDecorator.flowersPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 1;
        this.btaBiomeDecorator.mushroomsPerChunk = 8;
        this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.clayPerChunk = 1;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
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