package betterbiomes.feature.tree;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class LogGen extends WorldGenerator
{
	/*
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
	*/

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		boolean isEW = rand.nextBoolean();
		
		while (world.isAirBlock(x, y, z) && y > 2)
		{
			y--;
		}
		
		if (isEW) {
			for (int i = -2; i <= 2; i++) {
				if (world.getBlockId(x + i, y, z) != Block.grass.blockID)
					return false;
			}
			
			for (int i = -1; i <= 1; i++) {
				world.setBlock(x + i, y + 1, z, Block.wood.blockID, 4, 2);
			}
			
			if (rand.nextBoolean()) {
				world.setBlock(x - 2, y + 1, z, Block.wood.blockID, 12, 2);
			}
			else {
				world.setBlock(x + 2, y + 1, z, Block.wood.blockID, 12, 2);
			}
			
			return true;
		}
		else {
			for (int i = -2; i <= 2; i++) {
				if (world.getBlockId(x, y, z + i) != Block.grass.blockID)
					return false;
			}
			
			for (int i = -1; i <= 1; i++) {
				world.setBlock(x, y + 1, z + i, Block.wood.blockID, 8, 2);
			}
			
			if (rand.nextBoolean()) {
				world.setBlock(x, y + 1, z - 2, Block.wood.blockID, 12, 2);
			}
			else {
				world.setBlock(x, y + 1, z + 2, Block.wood.blockID, 12, 2);
			}
			
			return true;
		}
	}
}