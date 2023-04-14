package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.TreeGrowers;

public class HugeDeadTreeGrower extends HugeTreeGrower {
    public HugeDeadTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
    }

    public float leafSize(int par1) {
        return -1.0F;
    }

    public void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, int blockID, int metadata, boolean isWorldGen) {}
}
