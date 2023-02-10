package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.SmallShrubGen;
import betterterrain.world.feature.tree.legacy.TinyShrubGen;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class BrushlandBiome extends BTABiome {
	public BrushlandBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		btaBiomeDecorator.treesPerChunk = 2;
		btaBiomeDecorator.grassPerChunk = 5;
		btaBiomeDecorator.generateStoneInGrass = true;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(TreeGrowers.OAK_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.SMALL_OAK_SHRUB, 2);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);
		this.treeGrowers.put(BTATreeGrowers.TINY_OAK_SHRUB, 2);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(6) == 0) {
    		gen = new WorldGenTrees(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new SmallShrubGen();
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new TinyShrubGen();
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(7) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 0) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}