package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenFlowers extends WorldGenerator
{
    /** The ID of the plant block used in this plant generator. */
    private int plantBlockId;

    public BTAWorldGenFlowers(int par1)
    {
        this.plantBlockId = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int var6 = 0; var6 < 64; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && Block.blocksList[this.plantBlockId].CanBlockStayDuringGenerate(par1World, var7, var8, var9))
            {
                par1World.setBlock(var7, var8, var9, this.plantBlockId, 0, 2);
                
                if (par1World.getBiomeGenForCoords(var7, var9).getEnableSnow() && par1World.isAirBlock(var7, var8 + 1, var9) && par1World.canBlockSeeTheSky(var7, var8, var9)) {
                	par1World.setBlock(var7, var8 + 1, var9, Block.snow.blockID);
                }
            }
        }

        return true;
    }
}
