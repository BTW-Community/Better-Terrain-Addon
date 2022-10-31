package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.AutumnTreeGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.tree.HazelTreeGen;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class AutumnForestBiome extends BTABiome {
	public AutumnForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	int r = rand.nextInt(7);
    	
    	if (r == 6) {
			if (rand.nextInt(4) == 0) {
				gen = new HazelTreeGen();
			}
			else {
				gen = this.worldGeneratorForest;
			}
    	}
    	else {
    		gen = new AutumnTreeGen(r % 3);
    	}
    	
    	return gen;
    }
}