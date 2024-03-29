package betterterrain.world.feature.terrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class OasisGen extends WorldGenerator
{
	/** Stores ID for WorldGenSand */
	private int sandID;

	/** The maximum radius used when generating a patch of blocks. */
	private int radius;

	public OasisGen(int par1, int par2)
	{
		sandID = par2;
		radius = par1;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		if (par1World.getBlockMaterial(par3, par4, par5) != Material.water)
			return false;
		else
		{
			int var6 = par2Random.nextInt(radius - 2) + 2;
			byte var7 = 2;

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

							if (var13 == Block.sand.blockID)
							{
								if (sandID == Block.grass.blockID && par1World.getBlockId(var8, var12 + 1, var9) != 0) {
									par1World.setBlock(var8, var12, var9, Block.dirt.blockID);
								}
								else {
									par1World.setBlock(var8, var12, var9, sandID);
								}
							}
						}
					}
				}
			}

			return true;
		}
	}
}
