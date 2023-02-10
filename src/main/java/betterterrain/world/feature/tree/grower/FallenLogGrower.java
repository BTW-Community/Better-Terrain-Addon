package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class FallenLogGrower extends AbstractTreeGrower {
    protected FallenLogGrower(String name, TreeGrowers.TreeWoodType woodType) {
        super(name, 4,4, woodType);
    }

    @Override
    public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
        boolean isEW = rand.nextBoolean();

        while (world.isAirBlock(x, y, z) && y > 2)
        {
            y--;
        }

        if (isEW) {
            for (int i = -2; i <= 2; i++) {
                if (world.getBlockId(x + i, y, z) != Block.grass.blockID)
                    return false;
            }

            for (int i = -1; i <= 1; i++) {
                this.setBlockAndMetadata(world, x + i, y + 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
            }

            if (rand.nextBoolean()) {
                this.setBlockAndMetadata(world, x - 2, y + 1, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
            else {
                this.setBlockAndMetadata(world, x + 2, y + 1, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
        }
        else {
            for (int i = -2; i <= 2; i++) {
                if (world.getBlockId(x, y, z + i) != Block.grass.blockID)
                    return false;
            }

            for (int i = -1; i <= 1; i++) {
                this.setBlockAndMetadata(world, x, y + 1, z + i, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
            }

            if (rand.nextBoolean()) {
                this.setBlockAndMetadata(world, x, y + 1, z - 2, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
            else {
                this.setBlockAndMetadata(world, x, y + 1, z + 2, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
            }
        }

        return true;
    }
}
