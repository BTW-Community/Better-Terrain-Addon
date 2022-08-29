package betterbiomes.feature.tree;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Direction;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class RainforestGen1 extends WorldGenerator
{
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** True if this tree should grow Vines. */
	private final boolean vinesGrow;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;

	public RainforestGen1(boolean par1)
	{
		this(par1, 8, 3, 3, false);
	}

	public RainforestGen1(boolean par1, int par2, int par3, int par4, boolean par5)
	{
		super(par1);
		minTreeHeight = par2;
		metaWood = par3;
		metaLeaves = par4;
		vinesGrow = par5;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var6 = par2Random.nextInt(8) + minTreeHeight;
		boolean var7 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 256)
		{
			int var8;
			byte var9;
			int var11;
			int var12;

			for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
			{
				var9 = 1;

				if (var8 == par4)
				{
					var9 = 0;
				}

				if (var8 >= par4 + 1 + var6 - 2)
				{
					var9 = 2;
				}

				for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
				{
					for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
					{
						if (var8 >= 0 && var8 < 256)
						{
							var12 = par1World.getBlockId(var10, var8, var11);

							if (var12 != 0 && var12 != Block.leaves.blockID && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && var12 != Block.wood.blockID)
							{
								var7 = false;
							}
						}
						else
						{
							var7 = false;
						}
					}
				}
			}

			if (!var7)
				return false;
			else
			{
				var8 = par1World.getBlockId(par3, par4 - 1, par5);

				if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 256 - var6 - 1)
				{
					this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
					var9 = 3;
					byte var18 = 0;
					int var13;
					int var14;
					int var15;

					for (var11 = par4 - var9 + var6; var11 <= par4 + var6; ++var11)
					{
						var12 = var11 - (par4 + var6);
						var13 = var18 + 1 - var12;

						for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
						{
							var15 = var14 - par3;

							for (int var16 = par5 - var13; var16 <= par5 + var13; ++var16)
							{
								int var17 = var16 - par5;

								if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var14, var11, var16)])
								{
									this.setBlockAndMetadata(par1World, var14, var11, var16, Block.leaves.blockID, metaLeaves);
								}
							}
						}
					}

					for (var11 = 0; var11 < var6; ++var11)
					{
						var12 = par1World.getBlockId(par3, par4 + var11, par5);

						if (var12 == 0 || var12 == Block.leaves.blockID)
						{
							this.setBlockAndMetadata(par1World, par3, par4 + var11, par5, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3 - 3, par4 + (var6 - 3), par5, Block.wood.blockID, 7);
							this.setBlockAndMetadata(par1World, par3 + 3, par4 + (var6 - 3), par5, Block.wood.blockID, 7);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 3), par5 - 3, Block.wood.blockID, 11);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 3), par5 + 3, Block.wood.blockID, 11);
							this.setBlockAndMetadata(par1World, par3 - 2, par4 + (var6 - 4), par5, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3 + 2, par4 + (var6 - 4), par5, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 4), par5 - 2, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 4), par5 + 2, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3 - 2, par4 + (var6 - 5), par5, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3 + 2, par4 + (var6 - 5), par5, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 5), par5 - 2, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 5), par5 + 2, Block.wood.blockID, metaWood);
							this.setBlockAndMetadata(par1World, par3 - 1, par4 + (var6 - 6), par5, Block.wood.blockID, 7);
							this.setBlockAndMetadata(par1World, par3 + 1, par4 + (var6 - 6), par5, Block.wood.blockID, 7);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 6), par5 - 1, Block.wood.blockID, 11);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 6), par5 + 1, Block.wood.blockID, 11);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 3), par5, Block.leaves.blockID, metaLeaves);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 2), par5, Block.leaves.blockID, metaLeaves);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 1), par5, Block.leaves.blockID, metaLeaves);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6), par5, Block.leaves.blockID, metaLeaves);
							this.setBlock(par1World, par3, par4 + (var6 - 4), par5, 0);
							this.setBlock(par1World, par3, par4 + (var6 - 5), par5, 0);
							this.setBlockAndMetadata(par1World, par3 - 1, par4 + (var6 - 3), par5, Block.wood.blockID, 7);
							this.setBlockAndMetadata(par1World, par3 + 1, par4 + (var6 - 3), par5, Block.wood.blockID, 7);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 3), par5 - 1, Block.wood.blockID, 11);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 3), par5 + 1, Block.wood.blockID, 11);
							this.setBlockAndMetadata(par1World, par3, par4 + (var6 - 2), par5, Block.wood.blockID, metaWood);

							if (vinesGrow && var11 > 0)
							{
								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var11, par5))
								{
									this.setBlockAndMetadata(par1World, par3 - 1, par4 + var11, par5, Block.vine.blockID, 8);
								}

								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var11, par5))
								{
									this.setBlockAndMetadata(par1World, par3 + 1, par4 + var11, par5, Block.vine.blockID, 2);
								}

								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 - 1))
								{
									this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 - 1, Block.vine.blockID, 1);
								}

								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 + 1))
								{
									this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 + 1, Block.vine.blockID, 4);
								}
							}
						}
					}

					par1World.setBlockMetadataWithClient(par3, par4, par5, metaWood | 12); // place stump

					if (vinesGrow)
					{
						for (var11 = par4 - 3 + var6; var11 <= par4 + var6; ++var11)
						{
							var12 = var11 - (par4 + var6);
							var13 = 2 - var12 / 2;

							for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
							{
								for (var15 = par5 - var13; var15 <= par5 + var13; ++var15)
								{
									if (par1World.getBlockId(var14, var11, var15) == Block.leaves.blockID)
									{
										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0)
										{
											this.growVines(par1World, var14 - 1, var11, var15, 8);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0)
										{
											this.growVines(par1World, var14 + 1, var11, var15, 2);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0)
										{
											this.growVines(par1World, var14, var11, var15 - 1, 1);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0)
										{
											this.growVines(par1World, var14, var11, var15 + 1, 4);
										}
									}
								}
								
								if (par2Random.nextInt(5) == 0 && var6 > 5)
		                        {
		                            for (int i = 0; i < 2; ++i)
		                            {
		                                for (int j = 0; j < 4; ++j)
		                                {
		                                    if (par2Random.nextInt(4 - i) == 0)
		                                    {
		                                        var13 = par2Random.nextInt(3);
		                                        this.setBlockAndMetadata(par1World, par3 + Direction.offsetX[Direction.rotateOpposite[j]], par4 + var6 - 5 + i, par5 + Direction.offsetZ[Direction.rotateOpposite[j]], Block.cocoaPlant.blockID, var13 << 2 | j);
		                                    }
		                                }
		                            }
		                        }
							}
						}
					}
					
					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	/**
	 * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
	 */
	 private void growVines(World par1World, int par2, int par3, int par4, int par5)
	{
		this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
		int var6 = 4;

		while (true)
		{
			--par3;

			if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0)
				return;

			this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
			--var6;
		}
	}
}
