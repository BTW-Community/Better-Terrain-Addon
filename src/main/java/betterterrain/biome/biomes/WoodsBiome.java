package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.HazelTreeGen;
import betterterrain.world.feature.tree.legacy.LogGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.*;

public class WoodsBiome extends BTABiome {
    public WoodsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

	public void initTreeGrowerMap() {
		treeGrowers.put(TreeGrowers.OAK_TREE, 20);
		treeGrowers.put(TreeGrowers.BIRCH_TREE, 2);
		treeGrowers.put(TreeGrowers.BIG_OAK_TREE, 2);
		treeGrowers.put(BTATreeGrowers.FALLEN_OAK_TREE, 2);

		decoTreeGrowers.put(TreeGrowers.OAK_TREE, 20);
		decoTreeGrowers.put(TreeGrowers.BIRCH_TREE, 2);
		decoTreeGrowers.put(TreeGrowers.BIG_OAK_TREE, 2);
		decoTreeGrowers.put(BTATreeGrowers.FALLEN_OAK_TREE, 2);
		decoTreeGrowers.put(BTATreeGrowers.HAZEL_TREE, 1);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType)
    {
    	WorldGenerator gen;

    	if (((WorldTypeInterface) worldType).isDeco() && rand.nextInt(20) == 0) {
			gen = new HazelTreeGen();
		}
    	else if (rand.nextInt(5) == 0) {
    		gen = this.worldGeneratorForest;
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new LogGen();
    	}
    	else if (rand.nextInt(10) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else {
    		gen = new WorldGenTrees(false);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}