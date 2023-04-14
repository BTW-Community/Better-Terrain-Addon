package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class TemperateTreeGrower extends AbstractTreeGrower {
    protected TemperateTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
            return false;

        int treeHeight = this.minTreeHeight + rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1);

        for (int i = 0; i < treeHeight; i++) {
            if (i == 0) {
                this.setBlockAndMetadata(world, x, y + i, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
            else {
                this.setBlockAndMetadata(world, x, y + i, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
            }
        }

        int y2 = y + treeHeight - 1;
        //Level 0
        setBlockAndMetadata(world, x, y2 + 3, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        //Level 1
        setBlockAndMetadata(world, x, y2 + 2, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        //Level 2
        setBlockAndMetadata(world, x, y2 + 1, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x + 1, y2 + 1, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2 + 1, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x - 1, y2 + 1, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2 + 1, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        //Level 3
        setBlockAndMetadata(world, x + 1, y2, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x - 1, y2, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        //Level 4-7
        for (int i = 1; i < 5; i++) {
            setBlockAndMetadata(world, x + 1, y2 - i, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x + 2, y2 - i, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x + 1, y2 - i, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x, y2 - i, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x, y2 - i, z + 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x - 1, y2 - i, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x - 1, y2 - i, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x - 2, y2 - i, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x - 1, y2 - i, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x, y2 - i, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x, y2 - i, z - 2, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            setBlockAndMetadata(world, x + 1, y2 - i, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        }
        //Level 8
        setBlockAndMetadata(world, x + 1, y2 - 5, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2 - 5, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x - 1, y2 - 5, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2 - 5, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        //Level 9
        setBlockAndMetadata(world, x + 1, y2 - 6, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2 - 6, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x - 1, y2 - 6, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        setBlockAndMetadata(world, x, y2 - 6, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);

        return true;
    }
}
