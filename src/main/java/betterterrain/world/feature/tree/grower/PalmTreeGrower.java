package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import btw.world.util.BlockPos;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class PalmTreeGrower extends AbstractTreeGrower {
    private boolean spawnCocoa;

    protected PalmTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType, boolean spawnCocoa) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
        this.spawnCocoa = spawnCocoa;
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
            return false;

        int treeHeight = this.minTreeHeight + rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1);
        //How many times the trunk shifts to the side, controls the angle of the trunk
        int trunkShifts = 1 + rand.nextInt(2);
        //Which direction the trunk leans
        int leanDirection = rand.nextInt(4);
        int leanX = 0;
        int leanZ = 0;
        int[] trunkShiftHeights;

        switch (leanDirection) {
            //North
            case 0:
                leanX = 0;
                leanZ = -1;
                break;
            //West
            case 1:
                leanX = -1;
                leanZ = 0;
                break;
            //South
            case 2:
                leanX = 0;
                leanZ = 1;
                break;
            //East
            case 3:
                leanX = 1;
                leanZ = 0;
                break;
            default:
        }

        if (!checkForValidTreeSpawn(world, x, y, z, treeHeight, trunkShifts, leanX, leanZ))
            return false;

        //Gets the heights at which to split the trunk
        if (trunkShifts == 1) {
            trunkShiftHeights = new int[2];

            if (treeHeight == 6) {
                trunkShiftHeights[0] = 3;
                trunkShiftHeights[1] = 3;
            }
            else if (treeHeight == 7) {
                trunkShiftHeights[0] = 4;
                trunkShiftHeights[1] = 3;
            }
            else if (treeHeight == 8) {
                trunkShiftHeights[0] = 4;
                trunkShiftHeights[1] = 4;
            }
        }
        else {
            trunkShiftHeights = new int[3];

            if (treeHeight == 6) {
                trunkShiftHeights[0] = 2;
                trunkShiftHeights[1] = 2;
                trunkShiftHeights[2] = 2;
            }
            else if (treeHeight == 7) {
                trunkShiftHeights[0] = 3;
                trunkShiftHeights[1] = 2;
                trunkShiftHeights[2] = 2;
            }
            else if (treeHeight == 8) {
                trunkShiftHeights[0] = 3;
                trunkShiftHeights[1] = 3;
                trunkShiftHeights[2] = 2;
            }
        }

        int height = y;

        for (int i = 0; i < trunkShifts + 1; i++) {
            for (int j = 0; j < trunkShiftHeights[i]; j++) {
                this.setBlockAndMetadata(world, x + leanX * i, height, z + leanZ * i, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);

                if (rand.nextInt(5) == 0 && this.spawnCocoa) {
                    int facing = rand.nextInt(4) + 2;

                    BlockPos pos = new BlockPos(x + leanX * i, height, z + leanZ * i, facing);

                    int meta = 0;

                    if (facing - 2 == 1) {
                        meta = 2;
                    }
                    else if (facing - 2 == 2) {
                        meta = 3;
                    }
                    else if (facing - 2 == 3) {
                        meta = 1;
                    }

                    this.setBlockAndMetadata(world, pos.x, pos.y, pos.z, Block.cocoaPlant.blockID, meta, isWorldGen);
                }

                height++;
            }
        }

        //Places a stump
        this.setBlockAndMetadata(world, x, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);

        generatePalmLeaves(world, x + leanX * trunkShifts, height, z + leanZ * trunkShifts, isWorldGen);

        return true;
    }

    public void generatePalmLeaves(World world, int x, int y, int z, boolean isWorldGen) {
        //Central leaves
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                this.setBlockAndMetadata(world, x + i, y, z+ j, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
            }
        }

        //Lower leaves around log
        this.setBlockAndMetadata(world, x + 1, y - 1, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        this.setBlockAndMetadata(world, x, y - 1, z + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        this.setBlockAndMetadata(world, x - 1, y - 1, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
        this.setBlockAndMetadata(world, x, y - 1, z - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);

        //Hanging leaves - uses metadata to not despawn
        for (int i = 2; i < 5; i++) {
            int j = 0;
            int k = 0;

            if (i == 3)
                k = 1;
            if (i == 4)
                j = 1;

            //Sides
            this.setBlockAndMetadata(world, x + i, y - j, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
            this.setBlockAndMetadata(world, x, y - j, z + i, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
            this.setBlockAndMetadata(world, x - i, y - j, z, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
            this.setBlockAndMetadata(world, x, y - j, z - i, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);

            //Corners
            if (i < 4) {
                this.setBlockAndMetadata(world, x + i, y - k, z + i, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
                this.setBlockAndMetadata(world, x - i, y - k, z + i, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
                this.setBlockAndMetadata(world, x + i, y - k, z - i, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
                this.setBlockAndMetadata(world, x - i, y - k, z - i, this.woodType.leavesBlockID, this.woodType.leavesMetadata | 4, isWorldGen);
            }
        }
    }

    //Checks to make sure the space is clear for a tree to spawn
    private boolean checkForValidTreeSpawn(World world, int x, int y, int z, int treeHeight, int trunkShifts, int leanX, int leanZ) {
        leanX *= trunkShifts;
        leanZ *= trunkShifts;

        //Checks trunk


        //Checks leaves
        for (int i = -4 + leanX; i < 5 + leanZ; i++) {
            for (int j = -1; j < 1; j++) {
                for (int k = -4 + leanZ; k < 5 + leanZ; k++) {
                    if (!world.isAirBlock(i + x, j + y + treeHeight, k + z)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
