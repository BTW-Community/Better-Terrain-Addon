package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.SmallShrubGen;
import betterterrain.world.feature.tree.legacy.TaigaGen5;
import betterterrain.world.feature.tree.legacy.TaigaGen6;
import betterterrain.world.feature.tree.legacy.TaigaGen7;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class SiberiaBiome extends BTABiome {
	public SiberiaBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 20;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

	public void initTreeGrowerMap() {
		treeGrowers.put(TreeGrowers.SPRUCE_TREE, 1);
		treeGrowers.put(BTATreeGrowers.TALL_SPRUCE_TREE, 1);
		treeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 1);
		treeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 1);
		treeGrowers.put(BTATreeGrowers.SMALL_SPRUCE_SHRUB, 3);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(7) == 0) {
    		gen = new WorldGenTaiga2(false);
    	}
    	else if (rand.nextInt(6) == 0) {
    		gen = new TaigaGen5(false);
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new TaigaGen6(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new TaigaGen7(false);
    	}
    	else {
    		gen = new SmallShrubGen(1, 1);
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