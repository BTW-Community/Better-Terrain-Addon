package betterterrain.feature.tree;

import java.util.Random;

import betterbiomes.DecoIntegration;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class TaigaGen6 extends WorldGenerator
{
	public TaigaGen6(boolean par1)
	{
		super(par1);
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z)
	{
		int var6 = par2Random.nextInt(9) + 13;
		int var7 = 4 + par2Random.nextInt(4);
		int var8 = var6 - var7;
		int var9 = 2;
		boolean var10 = true;

		if (y >= 1 && y + var6 + 1 <= 256)
		{
			int var11;
			int var13;
			int var15;
			int var21;

			for (var11 = y; var11 <= y + 1 + var6 && var10; ++var11)
			{
				boolean var12 = true;

				if (var11 - y < var7)
				{
					var21 = 0;
				}
				else
				{
					var21 = var9;
				}

				for (var13 = x - var21; var13 <= x + var21 && var10; ++var13)
				{
					for (int var14 = z - var21; var14 <= z + var21 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = par1World.getBlockId(var13, var11, var14);

							if (var15 != 0 && var15 != Block.leaves.blockID)
							{
								var10 = false;
							}
						}
						else
						{
							var10 = false;
						}
					}
				}
			}

			if (!var10)
				return false;
			else
			{
				var11 = par1World.getBlockId(x, y - 1, z);

				if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID || (DecoIntegration.isDecoInstalled() && (var11 == DecoIntegration.coarseDirt.blockID || var11 == DecoIntegration.podzol.blockID))) && y < 256 - var6 - 1)
				{
					this.setBlock(par1World, x, y - 1, z, Block.dirt.blockID);
					var21 = par2Random.nextInt(2);
					var13 = 1;
					byte var22 = 0;
					int var17;
					int var16;

					for (var15 = 0; var15 <= var8; ++var15)
					{
						var16 = y + var6 - var15;

						for (var17 = x - var21; var17 <= x + var21; ++var17)
						{
							int var18 = var17 - x;

							for (int var19 = z - var21; var19 <= z + var21; ++var19)
							{
								int var20 = var19 - z;

								if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var17, var16, var19)])
								{
									this.setBlockAndMetadata(par1World, var17, var16, var19, Block.leaves.blockID, 1);
								}
							}
						}

						if (var21 >= var13)
						{
							var21 = var22;
							var22 = 1;
							++var13;

							if (var13 > var9)
							{
								var13 = var9;
							}
						}
						else
						{
							++var21;
						}
					}

					var15 = par2Random.nextInt(3);

					for (var16 = 0; var16 < var6 - var15; ++var16)
					{
						var17 = par1World.getBlockId(x, y + var16, z);

						if (var17 == 0 || var17 == Block.leaves.blockID)
						{
							this.setBlockAndMetadata(par1World, x, y + var16, z, Block.wood.blockID, 1);
						}
					}
					par1World.setBlockMetadataWithClient(x, y, z, 1 | 12); // place stump

					return true;
				} else
					return false;
			}
		} else
			return false;
	}
}
