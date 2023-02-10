package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;

public class FieldBiome extends BTABiome {
    public FieldBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 7;
        this.btaBiomeDecorator.grassPerChunk = 30;
        this.btaBiomeDecorator.flowersPerChunk = 30;
    }

    public void initTreeGrowerMap() {
        this.treeGrowers.put(TreeGrowers.SPRUCE_TREE, 1);
        this.treeGrowers.put(BTATreeGrowers.SPRUCE_BUSH, 4);
    }

    @Override
    public int getBiomeGrassColor() {
        return 0x69A565;
    }
}
