package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BirchForestBiome extends BTABiome {
    public BirchForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

    public void initTreeGrowerMap() {
        this.treeGrowers.put(TreeGrowers.BIRCH_TREE, 7);
        this.treeGrowers.put(BTATreeGrowers.TALL_BIRCH_TREE, 3);
        this.treeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorForest;
    }
}