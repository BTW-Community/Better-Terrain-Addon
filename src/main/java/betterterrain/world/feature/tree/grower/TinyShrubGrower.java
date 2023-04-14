package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class TinyShrubGrower extends AbstractTreeGrower {
    protected TinyShrubGrower(String name, TreeGrowers.TreeWoodType woodType) {
        super(name, 1, 1, woodType);
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
            return false;

        this.setBlockAndMetadata(world, x, y - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
        this.setBlockAndMetadata(world, x, y, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);

        return true;
    }
}
