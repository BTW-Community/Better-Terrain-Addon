package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.PineTreeGen;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class MountainBiome extends BTABiome {

	public MountainBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 2;
		this.btaBiomeDecorator.grassPerChunk = 3;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.OAK_ASPEN_TREE, 2);
		this.treeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 1);

		this.decoTreeGrowers.put(BTATreeGrowers.ASPEN_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 1);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return par1Random.nextInt(4) == 0 ? worldGeneratorTrees : new PineTreeGen(false);
	}

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions) {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}