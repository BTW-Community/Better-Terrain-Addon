package betterterrain.feature;

import java.util.Random;

import betterbiomes.DecoIntegration;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class ClayGen extends WorldGenerator {
    /** The block ID for clay. */
    private int clayBlockId;

    /** The number of blocks to generate. */
    private int numberOfBlocks;

    public ClayGen(int par1)
    {
        this.clayBlockId = Block.blockClay.blockID;
        this.numberOfBlocks = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        if (par1World.getBlockMaterial(par3, par4, par5) != Material.water)
        {
            return false;
        }
        else
        {
            int var6 = par2Random.nextInt(this.numberOfBlocks - 2) + 2;
            byte var7 = 1;

            for (int var8 = par3 - var6; var8 <= par3 + var6; ++var8)
            {
                for (int var9 = par5 - var6; var9 <= par5 + var6; ++var9)
                {
                    int var10 = var8 - par3;
                    int var11 = var9 - par5;

                    if (var10 * var10 + var11 * var11 <= var6 * var6)
                    {
                        for (int var12 = par4 - var7; var12 <= par4 + var7; ++var12)
                        {
                            int var13 = par1World.getBlockId(var8, var12, var9);

                            if (var13 == Block.dirt.blockID || var13 == Block.blockClay.blockID)
                            {
                                par1World.setBlock(var8, var12, var9, this.clayBlockId, 0, 2);
                            }
                            else if (var13 == Block.sand.blockID)
                            {
                                par1World.setBlock(var8, var12, var9, this.clayBlockId, 1, 2);
                            }
                            else if (DecoIntegration.isDecoInstalled() && var13 == DecoIntegration.redSand.blockID)
                            {
                                par1World.setBlock(var8, var12, var9, this.clayBlockId, 2, 2);
                            }
                            else if (var13 == Block.grass.blockID)
                            {
                                par1World.setBlock(var8, var12, var9, this.clayBlockId, 3, 2);
                            }
                            else if (var13 == Block.blockClay.blockID)
                            {
                            	int meta = par1World.getBlockMetadata(par3, par4, par5);
                                par1World.setBlock(var8, var12, var9, this.clayBlockId, meta, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
