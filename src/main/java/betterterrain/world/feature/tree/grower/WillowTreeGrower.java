package betterterrain.world.feature.tree.grower;

import betterterrain.world.util.WorldTypeInterface;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class WillowTreeGrower extends AbstractTreeGrower {
    protected WillowTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
        super(name, minTreeHeight, maxTreeHeight, woodType);
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        int treeHeight = rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1) + minTreeHeight;

        if (!canTreeGrowHere(world, x, y, z, treeHeight, isWorldGen)) {
            return false;
        }

        // Place trunk
        for (int j = 0; j <= treeHeight; j++) {
            if (j == 0) {
                this.setBlockAndMetadata(world, x, y + j, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
            else {
                this.setBlockAndMetadata(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
            }
        }

        this.setBlockAndMetadata(world, x - 1, y + treeHeight - 2, z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
        this.setBlockAndMetadata(world, x - 2, y + treeHeight - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);

        this.setBlockAndMetadata(world, x + 1, y + treeHeight - 2, z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
        this.setBlockAndMetadata(world, x + 2, y + treeHeight - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);

        this.setBlockAndMetadata(world, x, y + treeHeight - 2, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
        this.setBlockAndMetadata(world, x, y + treeHeight - 1, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);

        this.setBlockAndMetadata(world, x, y + treeHeight - 2, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
        this.setBlockAndMetadata(world, x, y + treeHeight - 1, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);

        // Place canopy
        int radius = 4;

        for (int j = 0; j <= 2; j++) {
            int modifiedRadius = radius - j;

            for (int i = -modifiedRadius; i <= modifiedRadius; i++) {
                for (int k = -modifiedRadius; k <= modifiedRadius; k++) {
                    double radiusSq = Math.pow(modifiedRadius + 0.75, 2);

                    if (i * i + k * k <= radiusSq) {
                        int height = y + treeHeight - 1 + j;

                        if (this.isReplaceable(world, x + i, height, z + k)) {
                            this.setBlockAndMetadata(world, x + i, height, z + k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
                        }
                    }
                }
            }
        }

        // Place vines
        if (((WorldTypeInterface) world.provider.terrainType).isDeco()) {
            for (int i = -radius; i <= radius; i++) {
                for (int k = -radius; k <= radius; k++) {
                    double radiusSq = Math.pow(radius + 0.75, 2);
                    int dist = i * i + k * k;

                    if ((Math.abs(i) == radius || Math.abs(k) == radius) && dist <= radiusSq) {
                        int vineLength = rand.nextInt(3) + 1;
                        vineLength *= rand.nextInt(3) == 0 ? 0 : 1;

                        for (int j = 0; j < vineLength; j++) {
                            int height = y + treeHeight - 2 - j;

                            if (this.isReplaceable(world, x + i, height, z + k)) {
                                this.setBlockAndMetadata(world, x + i, height, z + k, DecoBlocks.willowVines.blockID, 0, isWorldGen);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    protected boolean canTreeGrowHere(World world, int x, int y, int z, int treeHeight, boolean isWorldGen) {
        Block blockBelow = Block.blocksList[world.getBlockId(x, y - 1, z)];

        if (blockBelow == null || !blockBelow.canSaplingsGrowOnBlock(world, x, y - 1, z)) {
            return false;
        }

        for (int j = 0; j < treeHeight; j++) {
            int trunkOffset = j > treeHeight - 5 ? 1 : 0;

            for (int i = -trunkOffset; i <= 1 + trunkOffset; i++) {
                for (int k = -trunkOffset; k <= 1 + trunkOffset; k++) {
                    if (!isLogReplaceable(world, x + i, y + j, z + k)) {
                        return false;
                    }
                }
            }

            if (j > treeHeight - 3 && isWorldGen) {
                int canopyOffset = 4;

                for (int i = -canopyOffset; i <= canopyOffset; i++) {
                    for (int k = -canopyOffset; k <= canopyOffset; k++) {
                        if (!isReplaceable(world, x + i, y + j + 1, z + k)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
