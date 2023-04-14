package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.MassiveOakGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TallSwampTreeGen;
import btw.entity.mob.ChickenEntity;
import btw.entity.mob.PigEntity;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class FungalForestBiome extends BTABiome {
	public FungalForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 2;
        this.btaBiomeDecorator.bigRedMushroomsPerChunk = 5;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 2, 2));
	}

    public void initTreeGrowerMap() {
        this.treeGrowers.put(BTATreeGrowers.HUGE_OAK_REDWOOD_TREE, 4);
        this.treeGrowers.put(BTATreeGrowers.TALL_SWAMP_OAK_TREE, 8);

        this.decoTreeGrowers.put(BTATreeGrowers.HUGE_OAK_REDWOOD_TREE, 4);
        this.decoTreeGrowers.put(BTATreeGrowers.TALL_SWAMP_OAK_TREE, 8);
        this.decoTreeGrowers.put(BTATreeGrowers.WILLOW_TREE, 1);
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