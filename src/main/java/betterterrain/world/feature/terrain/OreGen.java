package betterterrain.world.feature.terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class OreGen extends WorldGenerator
{
	/** The block ID of the ore to be placed using this generator. */
	private int minableBlockId;
	private int meta;

	/** The number of blocks to generate. */
	private int minNumberOfBlocks;
	private int maxNumberOfBlocks;
	
	private int[] replaceIDs;

	public OreGen(int id, int numBlocks)
	{
		minableBlockId = id;
		minNumberOfBlocks = numBlocks;
		this.meta = 0;
		this.replaceIDs = new int[] {Block.grass.blockID, Block.dirt.blockID};
	}

	public OreGen(int id, int meta, int numBlocks, int replaceID) {
		this(id, meta, numBlocks, new int[] {replaceID});
	}

	public OreGen(int id, int meta, int minBlocks, int maxBlocks, int replaceID) {
		this(id, meta, minBlocks, maxBlocks, new int[] {replaceID});
	}

	public OreGen(int id, int meta, int numBlocks, int[] replaceIDs) {
		this(id, meta, numBlocks, numBlocks, replaceIDs);
	}

	public OreGen(int id, int meta, int minBlocks, int maxBlocks, int[] replaceIDs) {
		minableBlockId = id;
		minNumberOfBlocks = minBlocks;
		maxNumberOfBlocks = maxBlocks;
		this.meta = meta;
		this.replaceIDs = replaceIDs;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int numBlocks;
		
		if (minNumberOfBlocks == maxNumberOfBlocks) {
			numBlocks = maxNumberOfBlocks;
		}
		else {
			numBlocks = par2Random.nextInt(maxNumberOfBlocks - minNumberOfBlocks) + minNumberOfBlocks;
		}
		
		float var6 = par2Random.nextFloat() * (float)Math.PI;
		double var7 = par3 + 8 + MathHelper.sin(var6) * numBlocks / 8.0F;
		double var9 = par3 + 8 - MathHelper.sin(var6) * numBlocks / 8.0F;
		double var11 = par5 + 8 + MathHelper.cos(var6) * numBlocks / 8.0F;
		double var13 = par5 + 8 - MathHelper.cos(var6) * numBlocks / 8.0F;
		double var15 = par4 + par2Random.nextInt(3) - 2;
		double var17 = par4 + par2Random.nextInt(3) - 2;

		for (int var19 = 0; var19 <= numBlocks; ++var19)
		{
			double var20 = var7 + (var9 - var7) * var19 / numBlocks;
			double var22 = var15 + (var17 - var15) * var19 / numBlocks;
			double var24 = var11 + (var13 - var11) * var19 / numBlocks;
			double var26 = par2Random.nextDouble() * numBlocks / 16.0D;
			double var28 = (MathHelper.sin(var19 * (float)Math.PI / numBlocks) + 1.0F) * var26 + 1.0D;
			double var30 = (MathHelper.sin(var19 * (float)Math.PI / numBlocks) + 1.0F) * var26 + 1.0D;
			int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
			int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
			int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
			int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
			int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
			int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

			for (int var38 = var32; var38 <= var35; ++var38)
			{
				double var39 = (var38 + 0.5D - var20) / (var28 / 2.0D);

				if (var39 * var39 < 1.0D)
				{
					for (int var41 = var33; var41 <= var36; ++var41)
					{
						double var42 = (var41 + 0.5D - var22) / (var30 / 2.0D);

						if (var39 * var39 + var42 * var42 < 1.0D)
						{
							for (int var44 = var34; var44 <= var37; ++var44)
							{
								double var45 = (var44 + 0.5D - var24) / (var28 / 2.0D);

								if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && canBlockBeReplaced(par1World.getBlockId(var38, var41, var44)))
								{
									int var47 = this.meta;
                                    Block var48 = Block.blocksList[this.minableBlockId];
                                    
                                    int[] stratas = ((WorldTypeInterface) par1World.provider.terrainType).getStrataLevels();
                                    
                                    int strata1Height = stratas[0];
                                    int strata2Height = stratas[1];
                                    int strata3Height = -2;
                                    
                                    if (stratas.length > 2)
                                    	strata3Height = stratas[2];

                                    if (var48.hasStrata() && var41 <= strata1Height + par1World.rand.nextInt(2))
                                    {
                                        byte var49 = 1;
                                        
                                        if (var41 <= strata2Height + par1World.rand.nextInt(2))
                                        {
                                            var49 = 2;
                                        }
                                        
                                        if (var41 <= strata3Height + par1World.rand.nextInt(2))
                                        {
                                            var49 = 3;
                                        }

                                        var47 = var48.getMetadataConversionForStrataLevel(var49, 0);
                                    }

                                    par1World.setBlock(var38, var41, var44, this.minableBlockId, var47, 2);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
	
	public boolean canBlockBeReplaced(int blockID) {
		for (int i : this.replaceIDs) {
			if (i == blockID) {
				return true;
			}
		}
		
		return false;
	}
}
