package betterterrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTASkyWorldGenClay extends WorldGenerator {
    /** The block ID for clay. */
    private int clayBlockId;

    /** The number of blocks to generate. */
    private int numberOfBlocks;

    public BTASkyWorldGenClay(int par1)
    {
        this.clayBlockId = Block.blockClay.blockID;
        this.numberOfBlocks = par1;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (world.getBlockId(x, y - 1, z) == Block.grass.blockID)
        {
            int radius = rand.nextInt(this.numberOfBlocks - 2) + 2;
            byte heightCheck = 1;

            for (int i = x - radius; i <= x + radius; ++i)
            {
                for (int k = z - radius; k <= z + radius; ++k)
                {
                    int localX = i - x;
                    int localZ = k - z;

                    if (localX * localX + localZ * localZ <= radius * radius)
                    {
                        for (int j = y - heightCheck; j <= y + heightCheck; ++j)
                        {
                            int var13 = world.getBlockId(i, j, k);

                            if (var13 == Block.dirt.blockID || var13 == Block.blockClay.blockID)
                            {
                                world.setBlock(i, j, k, this.clayBlockId, 0, 2);
                            }
                            else if (var13 == Block.grass.blockID)
                            {
                                world.setBlock(i, j, k, this.clayBlockId, 3, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
        
        return false;
    }
}
