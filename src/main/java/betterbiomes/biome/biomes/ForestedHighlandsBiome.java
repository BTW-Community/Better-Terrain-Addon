package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;

import java.util.Random;

public class ForestedHighlandsBiome extends BTABiome {
    public ForestedHighlandsBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 3;
    }

    public void initTreeGrowerMap() {
        this.treeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 4);
        this.treeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);

        this.decoTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.DARK_OAK_HIGHLANDS_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.RED_HIGHLANDS_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.ORANGE_HIGHLANDS_TREE, 1);
        this.decoTreeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);
    }

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions) {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}
