package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TaigaGen5;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class IcyPeaksBiome extends BTABiome {
	public IcyPeaksBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    public void initTreeGrowerMap() {
        treeGrowers.put(TreeGrowers.SPRUCE_TREE, 2);
        treeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 1);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new TaigaGen5(false) : new WorldGenTaiga2(false));
    }

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}