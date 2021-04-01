package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenMelons extends WorldGenerator {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        boolean var6 = BTABiomeConfiguration.canBiomeSpawnMelon(par1World.getBiomeGenForCoords(par3, par5));
        int var7 = 0;

        for (int var9 = 0; var9 < 64; ++var9)
        {
            int var10 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var11 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var12 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var10, var11, var12) && par1World.getBlockId(var10, var11 - 1, var12) == Block.grass.blockID && Block.pumpkin.canPlaceBlockAt(par1World, var10, var11, var12))
            {
                int var13 = par2Random.nextInt(4);

                if (var6 && var7 < 3)
                {
                    par1World.setBlock(var10, var11, var12, Block.melon.blockID, var13, 2);

                    ++var7;
                }
            }
        }

        return true;
    }
}