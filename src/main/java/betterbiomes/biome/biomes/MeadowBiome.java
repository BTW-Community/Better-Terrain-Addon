package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.MeadowTreeGen1;
import betterbiomes.world.feature.tree.legacy.MeadowTreeGen2;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenerator;

public class MeadowBiome extends BTABiome {
	public MeadowBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 1;
		this.btaBiomeDecorator.grassPerChunk = 15;
		this.btaBiomeDecorator.flowersPerChunk = 30;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.MEADOW_SPRUCE_TREE, 1);

		this.decoTreeGrowers.put(BTATreeGrowers.MEADOW_SPRUCE_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.MEADOW_DARK_OAK_TREE, 1);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new MeadowTreeGen2() : new MeadowTreeGen1();
	}
}