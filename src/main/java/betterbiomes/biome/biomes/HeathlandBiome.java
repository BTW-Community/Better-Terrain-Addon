package betterbiomes.biome.biomes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class HeathlandBiome extends BTABiome {
	public HeathlandBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 7;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 15;
	}

	public Map<AbstractTreeGrower, Integer> spruceTreeGrowers;

	public void initTreeGrowerMap() {
		spruceTreeGrowers = new HashMap<>();

		this.treeGrowers.put(TreeGrowers.OAK_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);

		this.spruceTreeGrowers.put(TreeGrowers.SPRUCE_TREE, 1);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(3) == 0 ? this.worldGeneratorTrees : new WorldGenShrub(0,0);
	}
}