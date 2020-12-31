package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTAWorldGenPineTree extends WorldGenerator
{
	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;
	
	public BTAWorldGenPineTree(boolean par1) {
		this(par1, 0, 0);
	}
	
	public BTAWorldGenPineTree(boolean par1, int metaWood, int metaLeaves) {
		super(par1);
		this.metaWood = metaWood;
		this.metaLeaves = metaLeaves;
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != Block.grass.blockID && var6 != Block.stone.blockID)
			return false;
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8) && !var1.isAirBlock(var3 + var7, var4, var5 + var8))
						return false;
				}
			}

			int var99 = var2.nextInt(2);

			if (var99 == 0)
			{
				var1.setBlock(var3, var4, var5, Block.dirt.blockID);
				var1.setBlockAndMetadata(var3, var4 + 1, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 2, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 3, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 4, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 5, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 6, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 7, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 8, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 9, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 10, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 11, var5, Block.wood.blockID, metaWood);
				var1.setBlockMetadataWithClient(var3, var4 + 1, var5, metaWood | 12); // place stump

				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 6, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5 + 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 6, var5 + 2, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 8, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5 + 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 8, var5 + 2, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 10, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 10, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 10, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 10, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 10, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 10, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 10, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 10, var5 - 1, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 11, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 11, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 11, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 11, var5 - 1, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3, var4 + 12, var5, Block.leaves.blockID, metaLeaves);
			}

			if (var99 == 1)
			{
				var1.setBlock(var3, var4, var5, Block.dirt.blockID);
				var1.setBlockAndMetadata(var3, var4 + 1, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 2, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 3, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 4, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 5, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 6, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 7, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 8, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 9, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 10, var5, Block.wood.blockID, metaWood);
				var1.setBlockAndMetadata(var3, var4 + 11, var5, Block.wood.blockID, metaWood);
                var1.setBlockMetadataWithClient(var3, var4 + 1, var5, metaWood | 12); // place stump

				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 6, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 6, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 6, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 6, var5 + 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 6, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 6, var5 + 2, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 - 1, var4 + 8, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 8, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 2, var4 + 8, var5 - 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 8, var5 + 2, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 8, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 2, var4 + 8, var5 + 2, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 10, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 10, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 10, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 10, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 10, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 + 1, var4 + 10, var5 - 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 10, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 10, var5 - 1, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3 + 1, var4 + 11, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3 - 1, var4 + 11, var5, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 11, var5 + 1, Block.leaves.blockID, metaLeaves);
				var1.setBlockAndMetadata(var3, var4 + 11, var5 - 1, Block.leaves.blockID, metaLeaves);

				var1.setBlockAndMetadata(var3, var4 + 12, var5, Block.leaves.blockID, metaLeaves);
			}

			return true;
		}
	}
}
