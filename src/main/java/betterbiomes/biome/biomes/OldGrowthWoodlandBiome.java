package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.OldOakGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.HazelTreeGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.*;

public class OldGrowthWoodlandBiome extends BTABiome {
    public OldGrowthWoodlandBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.HUGE_OAK_TREE, 3);
		this.treeGrowers.put(TreeGrowers.BIG_OAK_TREE, 6);
		this.treeGrowers.put(BTATreeGrowers.TALL_OAK_TREE, 10);
		this.treeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.DARK_OAK_TREE, 1);

		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_OAK_TREE, 3);
		this.decoTreeGrowers.put(TreeGrowers.BIG_OAK_TREE, 6);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_OAK_TREE, 10);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.DARK_OAK_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.HAZEL_TREE, 1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType) {
    	WorldGenerator gen;

		if (((WorldTypeInterface) worldType).isDeco() && rand.nextInt(20) == 0) {
			gen = new HazelTreeGen();
		}
		else if (rand.nextInt(7) == 0) {
    		gen = new OldOakGen(false);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	
    	return gen;
    }
}