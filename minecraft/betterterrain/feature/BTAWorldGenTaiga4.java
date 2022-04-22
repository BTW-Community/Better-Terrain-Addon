package betterterrain.feature;

import java.util.Random;

import betterterrain.BTADecoIntegration;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTAWorldGenTaiga4 extends WorldGenerator
{
	public BTAWorldGenTaiga4(boolean var1)
	{
		super(var1);
	}

	@Override
	public boolean generate(World var1, Random var2, int x, int y, int z)
	{
		int var6 = var2.nextInt(15) + 20;
		int var7 = var2.nextInt(3) + 5;
		int var8 = var6 - var7;
		int var9 = 2 + var2.nextInt(3);
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
							var15 = var1.getBlockId(var13, var11, var14);

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
				var11 = var1.getBlockId(x, y - 1, z);

				if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID || (BTADecoIntegration.isDecoInstalled() && (var11 == BTADecoIntegration.coarseDirt.blockID || var11 == BTADecoIntegration.podzol.blockID))) && y < 256 - var6 - 1)
				{
					var1.setBlock(x, y - 1, z, Block.dirt.blockID);
					var21 = var2.nextInt(2);
					var13 = 1;
					boolean var22 = false;
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

								if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[var1.getBlockId(var17, var16, var19)])
								{
									this.setBlockAndMetadata(var1, var17, var16, var19, Block.leaves.blockID, 1);
								}
							}
						}

						if (var21 >= var13)
						{
							var21 = var22 ? 1 : 0;
							var22 = true;
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

					var15 = var2.nextInt(3);

					for (var16 = 0; var16 < var6 - var15; ++var16)
					{
						var17 = var1.getBlockId(x, y + var16, z);

						if (var17 == 0 || var17 == Block.leaves.blockID)
						{
							this.setBlockAndMetadata(var1, x, y + var16, z, Block.wood.blockID, 1);
						}
					}

					var1.setBlockMetadataWithClient(x, y, z, 1 | 12); // place stump

					return true;
				} else
					return false;
			}
		} else
			return false;
	}
}
