package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class HugeRedwoodTreeGrower extends AbstractTreeGrower {
	protected HugeRedwoodTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		// Hack to fix offset
		x++;
		z++;
		
		int treeHeight = rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1) + this.minTreeHeight;
		int var7 = rand.nextInt(5) + 5;
		int var8 = treeHeight - var7;
		int var9 = 2 + rand.nextInt(2);
		boolean var10 = true;
		
		if (y >= 1 && y + treeHeight + 1 <= 256) {
			int var11;
			int var13;
			int var14;
			int var15;
			int var24;
			
			for (var11 = y; var11 <= y + 1 + treeHeight && var10; ++var11) {
				boolean var12 = true;
				
				if (var11 - y < var7) {
					var24 = 0;
				}
				else {
					var24 = var9;
				}
				
				for (var13 = x - var24; var13 <= x + var24 && var10; ++var13) {
					for (var14 = z - var24; var14 <= z + var24 && var10; ++var14) {
						if (var11 >= 0 && var11 < 256) {
							if (!this.isReplaceable(world, var13, var11, var14)) {
								var10 = false;
							}
						}
						else {
							var10 = false;
						}
					}
				}
			}
			
			if (!var10) {
				return false;
			}
			else {
				Block blockBelow = Block.blocksList[world.getBlockId(x, y - 1, z)];
				
				if (blockBelow == null || !blockBelow.canSaplingsGrowOnBlock(world, x, y - 1, z)) {
					return false;
				}
				
				if (y < 256 - treeHeight - 1) {
					if (y < 256 - treeHeight - 1) {
						if (y < 256 - treeHeight - 1) {
							if (y < 256 - treeHeight - 1) {
								var15 = rand.nextInt(2);
								int var16 = 1;
								boolean var17 = false;
								int var19;
								int var18;
								int var20;
								
								for (var18 = 0; var18 <= var8; ++var18) {
									var19 = y + treeHeight - var18;
									
									for (var20 = x - var15; var20 <= x + var15; ++var20) {
										int var21 = var20 - x;
										
										for (int var22 = z - var15; var22 <= z + var15; ++var22) {
											int var23 = var22 - z;
											
											if ((Math.abs(var21) != var15 || Math.abs(var23) != var15 || var15 <= 0) && this.isReplaceable(world, var20, var19, var22)) {
												this.setBlockAndMetadata(world, var20, var19, var22, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
												this.setBlockAndMetadata(world, var20 - 1, var19, var22, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
												this.setBlockAndMetadata(world, var20, var19, var22 - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
												this.setBlockAndMetadata(world, var20 - 1, var19, var22 - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
											}
										}
									}
									
									if (var15 >= var16) {
										var15 = var17 ? 1 : 0;
										var17 = true;
										++var16;
										
										if (var16 > var9) {
											var16 = var9;
										}
									}
									else {
										++var15;
									}
								}
								
								var18 = rand.nextInt(3);
								
								for (var19 = 0; var19 < treeHeight - var18; ++var19) {
									var20 = world.getBlockId(x, y + var19, z);
									
									if (this.isLogReplaceable(world, x, y + var19, z)) {
										if (var19 == 0) {
											this.setBlockAndMetadata(world, x, y + var19, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
											this.setBlockAndMetadata(world, x - 1, y + var19, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
											this.setBlockAndMetadata(world, x, y + var19, z - 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
											this.setBlockAndMetadata(world, x - 1, y + var19, z - 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
										}
										else {
											this.setBlockAndMetadata(world, x, y + var19, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
											this.setBlockAndMetadata(world, x - 1, y + var19, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
											this.setBlockAndMetadata(world, x, y + var19, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
											this.setBlockAndMetadata(world, x - 1, y + var19, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
										}
									}
								}
								
								return true;
							}
							else {
								return false;
							}
						}
						else {
							return false;
						}
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		else {
			return false;
		}
	}
}
