package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public class BowerBiome extends BTABiome {
    public BowerBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 1;
        this.btaBiomeDecorator.grassPerChunk = 15;
    }

    public void initTreeGrowerMap() {
        this.treeGrowers.put(TreeGrowers.BIG_OAK_TREE, 1);
        this.treeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);

        this.decoTreeGrowers.put(BTATreeGrowers.BIG_RED_AUTUMN_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);
    }

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions) {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}
