package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.AutumnTreeGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.HazelTreeGen;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class SeasonalForestBiome extends BTABiome {
	public SeasonalForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

	public void initTreeGrowerMap() {
		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_RED_AUTUMN_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_RED_AUTUMN_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.RED_AUTUMN_TREE, 25);

		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_ORANGE_AUTUMN_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_ORANGE_AUTUMN_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.ORANGE_AUTUMN_TREE, 25);

		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_YELLOW_AUTUMN_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_YELLOW_AUTUMN_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.YELLOW_AUTUMN_TREE, 25);

		this.decoTreeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 5);
		this.decoTreeGrowers.put(TreeGrowers.BIRCH_TREE, 25);
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