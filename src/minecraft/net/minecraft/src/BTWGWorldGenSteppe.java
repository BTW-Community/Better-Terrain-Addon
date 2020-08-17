package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTWGWorldGenSteppe extends WorldGenerator
{
	/** Stores ID for WorldGenTallGrass */
	private int tallGrassID;
	private int tallGrassMetadata;

	public BTWGWorldGenSteppe(int par1, int par2)
	{
		tallGrassID = par1;
		tallGrassMetadata = par2;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var11;

		for (boolean var6 = false; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; --par4)
		{
			;
		}

		for (int var7 = 0; var7 < 128; ++var7)
		{
			int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(var8, var9, var10) && par1World.getBlockId(var8, var9 - 1, var10) == Block.grass.blockID)
			{
				par1World.setBlock(var8, var9 - 1, var10, Block.sand.blockID);
				par1World.setBlock(var8, var9, var10, 0);
			}
		}

		return true;
	}
}
