package betterbiomes.feature.terrain;

import java.util.Random;

import betterbiomes.DecoIntegration;
import net.minecraft.src.Block;
import net.minecraft.src.FCUtilsBlockPos;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class GlowstoneGen extends WorldGenerator
{
    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (!world.isAirBlock(x, y, z)) {
            return false;
        }
        else if (!canGlowsotneGrowFromBlock(world.getBlockId(x, y + 1, z))) {
            return false;
        }
        else {
            world.setBlock(x, y, z, Block.glowStone.blockID, 0, 2);

            for (int iter = 0; iter < 1500; ++iter) {
                int i = x + rand.nextInt(8) - rand.nextInt(8);
                int j = y - rand.nextInt(12);
                int k = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.getBlockId(i, j, k) == 0) {
                    int adjacentGlowstoneCount = 0;

                    for (int dir = 0; dir < 6; ++dir) {
                        int adjacentID = 0;
                        
                        FCUtilsBlockPos pos = new FCUtilsBlockPos(i, j, k, dir);
                        adjacentID = world.getBlockId(pos.i, pos.j, pos.k);

                        if (adjacentID == Block.glowStone.blockID) {
                            adjacentGlowstoneCount++;
                        }
                    }

                    if (adjacentGlowstoneCount == 1) {
                        world.setBlock(i, j, k, Block.glowStone.blockID, 0, 2);
                    }
                }
            }

            return true;
        }
    }
    
    private boolean canGlowsotneGrowFromBlock(int blockID) {
    	return blockID == Block.netherrack.blockID || (DecoIntegration.isDecoInstalled() && blockID == DecoIntegration.basalt.blockID || blockID == Block.blockNetherQuartz.blockID);
    }
}
