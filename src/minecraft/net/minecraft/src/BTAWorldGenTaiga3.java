package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTAWorldGenTaiga3 extends WorldGenerator
{
	public BTAWorldGenTaiga3(boolean var1)
	{
		super(var1);
	}

	@Override
	public boolean generate(World var1, Random var2, int x, int y, int z)
	{
		int var6 = var2.nextInt(10) + 35;
		int var7 = var2.nextInt(5) + 10;
		int var8 = var6 - var7;
		int var9 = 4;
		boolean var10 = true;

		if (y >= 1 && y + var6 + 1 <= 256)
		{
			int var11;
			int var13;
			int var14;
			int var15;
			int var24;

			for (var11 = y; var11 <= y + 1 + var6 && var10; ++var11)
			{
				boolean var12 = true;

				if (var11 - y < var7)
				{
					var24 = 0;
				}
				else
				{
					var24 = var9;
				}

				for (var13 = x - var24; var13 <= x + var24 && var10; ++var13)
				{
					for (var14 = z - var24; var14 <= z + var24 && var10; ++var14)
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
				var24 = var1.getBlockId(x - 1, y - 1, z);
				var13 = var1.getBlockId(x, y - 1, z - 1);
				var14 = var1.getBlockId(x - 1, y - 1, z - 1);

				if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && y < 256 - var6 - 1)
				{
					if ((var24 == Block.grass.blockID || var24 == Block.dirt.blockID) && y < 256 - var6 - 1)
					{
						if ((var13 == Block.grass.blockID || var24 == Block.dirt.blockID) && y < 256 - var6 - 1)
						{
							if ((var14 == Block.grass.blockID || var24 == Block.dirt.blockID) && y < 256 - var6 - 1)
							{
								var1.setBlock(x, y - 1, z, Block.dirt.blockID);
								var1.setBlock(x - 1, y - 1, z, Block.dirt.blockID);
								var1.setBlock(x, y - 1, z - 1, Block.dirt.blockID);
								var1.setBlock(x - 1, y - 1, z - 1, Block.dirt.blockID);
								var15 = var2.nextInt(2);
								int var16 = 1;
								boolean var17 = false;
								int var19;
								int var18;
								int var20;

								for (var18 = 0; var18 <= var8; ++var18)
								{
									var19 = y + var6 - var18;

									for (var20 = x - var15; var20 <= x + var15; ++var20)
									{
										int var21 = var20 - x;

										for (int var22 = z - var15; var22 <= z + var15; ++var22)
										{
											int var23 = var22 - z;

											if ((Math.abs(var21) != var15 || Math.abs(var23) != var15 || var15 <= 0) && !Block.opaqueCubeLookup[var1.getBlockId(var20, var19, var22)])
											{
												this.setBlockAndMetadata(var1, var20, var19, var22, Block.leaves.blockID, 1);
												this.setBlockAndMetadata(var1, var20 - 1, var19, var22, Block.leaves.blockID, 1);
												this.setBlockAndMetadata(var1, var20, var19, var22 - 1, Block.leaves.blockID, 1);
												this.setBlockAndMetadata(var1, var20 - 1, var19, var22 - 1, Block.leaves.blockID, 1);
											}
										}
									}

									if (var15 >= var16)
									{
										var15 = var17 ? 1 : 0;
										var17 = true;
										++var16;

										if (var16 > var9)
										{
											var16 = var9;
										}
									}
									else
									{
										++var15;
									}
								}

								var18 = var2.nextInt(3);

								for (var19 = 0; var19 < var6 - var18; ++var19)
								{
									var20 = var1.getBlockId(x, y + var19, z);

									if (var20 == 0 || var20 == Block.leaves.blockID)
									{
										this.setBlockAndMetadata(var1, x, y + var19, z, Block.wood.blockID, 1);
										this.setBlockAndMetadata(var1, x - 1, y + var19, z, Block.wood.blockID, 1);
										this.setBlockAndMetadata(var1, x, y + var19, z - 1, Block.wood.blockID, 1);
										this.setBlockAndMetadata(var1, x - 1, y + var19, z - 1, Block.wood.blockID, 1);
									}
								}

								var1.setBlockMetadataWithClient(x,     y, z,     1 | 12); // place stump
                                var1.setBlockMetadataWithClient(x - 1, y, z,     1 | 12); // place stump
                                var1.setBlockMetadataWithClient(x,     y, z - 1, 1 | 12); // place stump
                                var1.setBlockMetadataWithClient(x - 1, y, z - 1, 1 | 12); // place stump

								return true;
							} else
								return false;
						} else
							return false;
					} else
						return false;
				} else
					return false;
			}
		} else
			return false;
	}
}
