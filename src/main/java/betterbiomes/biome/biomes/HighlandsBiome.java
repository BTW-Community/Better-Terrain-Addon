package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class HighlandsBiome extends BTABiome {

	public HighlandsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.fractionalTreeChance = 2;
		this.btaBiomeDecorator.grassPerChunk = 3;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(TreeGrowers.OAK_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 3);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? worldGeneratorTrees : new WorldGenShrub(0, 0);
	}

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}