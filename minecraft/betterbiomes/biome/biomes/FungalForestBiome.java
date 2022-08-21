package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.MassiveOakGen;
import betterbiomes.feature.tree.TallSwampTreeGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class FungalForestBiome extends BTABiome {
	public FungalForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 2;
        this.btaBiomeDecorator.bigRedMushroomsPerChunk = 5;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
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
    		gen = new MassiveOakGen(true);
    	}
    	else {
    		gen = new TallSwampTreeGen();
    	}
    	
    	return gen;
    }
}