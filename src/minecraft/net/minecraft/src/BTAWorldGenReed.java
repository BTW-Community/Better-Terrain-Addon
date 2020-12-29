package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenReed extends WorldGenReed {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        BiomeGenBase var6 = par1World.getBiomeGenForCoords(par3, par5);
        boolean var7 = BTABiomeConfiguration.canBiomeSpawnReeds(var6);

        for (int var8 = 0; var8 < 20; ++var8)
        {
            int var9 = par3 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par4;
            int var11 = par5 + par2Random.nextInt(4) - par2Random.nextInt(4);

            if (par1World.isAirBlock(var9, par4, var11) && (par1World.getBlockMaterial(var9 - 1, par4 - 1, var11) == Material.water || par1World.getBlockMaterial(var9 + 1, par4 - 1, var11) == Material.water || par1World.getBlockMaterial(var9, par4 - 1, var11 - 1) == Material.water || par1World.getBlockMaterial(var9, par4 - 1, var11 + 1) == Material.water))
            {
                int var12 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);

                if (var7)
                {
                    for (int var13 = 0; var13 < var12; ++var13)
                    {
                        if (Block.reed.canBlockStay(par1World, var9, var10 + var13, var11))
                        {
                            par1World.setBlock(var9, var10 + var13, var11, Block.reed.blockID, 0, 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}
