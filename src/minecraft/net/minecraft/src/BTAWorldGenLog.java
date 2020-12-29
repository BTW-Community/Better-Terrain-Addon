package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenLog extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);
		int var61 = var1.getBlockId(var3 - 1, var4, var5);
		int var62 = var1.getBlockId(var3 + 1, var4, var5);
		int var63 = var1.getBlockId(var3 - 2, var4, var5);
		int var64 = var1.getBlockId(var3 + 2, var4, var5);

		if (var6 == Block.grass.blockID)
		{
			if (var61 == Block.grass.blockID)
			{
				if (var62 == Block.grass.blockID)
				{
					if (var63 == Block.grass.blockID)
					{
						if (var64 == Block.grass.blockID)
						{
							for (int var7 = -2; var7 <= 2; ++var7)
							{
								for (int var8 = -2; var8 <= 2; ++var8)
								{
									if (!var1.isAirBlock(var3, var4 + 1, var5 + var8) && !var1.isAirBlock(var3 - 1, var4 + 1, var5 + var8) && !var1.isAirBlock(var3 + 1, var4 + 1, var5 + var8))
										return false;
								}
							}

							var1.setBlock(var3, var4 + 1, var5, Block.wood.blockID, 4, 2);
							var1.setBlock(var3 - 1, var4 + 1, var5, Block.wood.blockID, 4, 2);
							var1.setBlock(var3 + 1, var4 + 1, var5, Block.wood.blockID, 4, 2);
							return true;
						} else
							return false;
					} else
						return false;
				} else
					return false;
			} else
				return false;
		} else
			return false;

	}
}