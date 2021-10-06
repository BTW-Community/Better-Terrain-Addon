package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenCherryTree extends WorldGenerator {
	public boolean generate(World var0, Random var1, int var2, int var3, int var4) {
		int var5 = 5;
		int var6 = 0;
		int var7 = 0;
		boolean var8 = false;

		int var9 = var1.nextInt(3) + var5;
		boolean var10 = true;

		if (var3 >= 1 && var3 + var9 + 1 <= 256)
		{
			int var11;
			byte var12;
			int var14;
			int var15;

			for (var11 = var3; var11 <= var3 + 1 + var9; ++var11)
			{
				var12 = 1;

				if (var11 == var3)
				{
					var12 = 0;
				}

				if (var11 >= var3 + 1 + var9 - 2)
				{
					var12 = 2;
				}

				for (int var13 = var2 - var12; var13 <= var2 + var12 && var10; ++var13)
				{
					for (var14 = var4 - var12; var14 <= var4 + var12 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = var0.getBlockId(var13, var11, var14);

							if (!var0.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID && var15 != BTADecoIntegration.cherryLog.blockID && var15 != BTADecoIntegration.cherryLeaves.blockID)
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
			{
				return false;
			}
			else
			{
				var11 = var0.getBlockId(var2, var3 - 1, var4);

				if (FCUtilsTrees.CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4) && var3 < 256 - var9 - 1)
				{
					if (var11 == Block.grass.blockID)
					{
						var0.setBlock(var2, var3 - 1, var4, Block.dirt.blockID);
					}

					var12 = 3;
					byte var21 = 0;
					int var16;
					int var17;
					int var18;

					for (var14 = var3 - var12 + var9; var14 <= var3 + var9; ++var14)
					{
						var15 = var14 - (var3 + var9);
						var16 = var21 + 1 - var15 / 2;

						for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
						{
							var18 = var17 - var2;

							for (int var19 = var4 - var16; var19 <= var4 + var16; ++var19)
							{
								int var20 = var19 - var4;

								if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || var1.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var17, var14, var19)])
								{
									var0.setBlockAndMetadata(var17, var14, var19, BTADecoIntegration.cherryLeaves.blockID, var7);
								}
							}
						}
					}

					for (var14 = 0; var14 < var9; ++var14)
					{
						var15 = var0.getBlockId(var2, var3 + var14, var4);

						if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID || var15 == BTADecoIntegration.cherryLeaves.blockID)
						{
							var0.setBlockAndMetadata(var2, var3 + var14, var4, BTADecoIntegration.cherryLog.blockID, var6);
						}
					}

					if (var9 > 2)
					{
						var14 = var0.getBlockId(var2, var3, var4);

						if (var14 == BTADecoIntegration.cherryLog.blockID)
						{
							var15 = var0.getBlockMetadata(var2, var3, var4);

							if (var15 == var6)
							{
								var0.setBlock(var2, var3, var4, BTADecoIntegration.cherryStump.blockID);
							}
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
}
