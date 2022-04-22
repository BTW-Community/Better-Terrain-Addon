package betterterrain.feature;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class NetherFireGen extends WorldGenerator {
    public boolean generate(World world, Random random, int x, int y, int z) {
        for (int count = 0; count < 64; ++count) {
            int i = x + random.nextInt(8) - random.nextInt(8);
            int j = y + random.nextInt(4) - random.nextInt(4);
            int k = z + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i, j, k) && world.getBlockId(i, j - 1, k) != 0 && Block.blocksList[world.getBlockId(i, j - 1, k)].DoesInfiniteBurnToFacing(world, i, j - 1, k, 0)) {
                world.setBlock(i, j, k, Block.fire.blockID, 0, 2);
            }
        }

        return true;
    }
}
