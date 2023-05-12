package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class MahoganyTreeGrower extends AbstractTreeGrower {
	protected MahoganyTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int treeHeight = rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1) + this.minTreeHeight;
		boolean hasRoom = true;
		
		if (y >= 1 && y + treeHeight + 1 <= 256) {
			for (int j = y; j <= y + 1 + treeHeight; ++j) {
				int xzSize = 1;
				
				if (j == y) {
					xzSize = 0;
				}
				
				if (j >= y + 1 + treeHeight - 2) {
					xzSize = 2;
				}
				
				for (int i = x - xzSize; i <= x + xzSize && hasRoom; ++i) {
					for (int k = z - xzSize; k <= z + xzSize && hasRoom; ++k) {
						if (j >= 0 && j < 256) {
							if (!this.isLogReplaceable(world, x, y, z)) {
								hasRoom = false;
							}
						}
						else {
							hasRoom = false;
						}
					}
				}
			}
			
			if (!hasRoom) {
				return false;
			}
			else {
				int blockIDBelow = world.getBlockId(x, y - 1, z);
				
				if ((blockIDBelow == Block.grass.blockID || blockIDBelow == Block.dirt.blockID) && y < 256 - treeHeight - 1) {
					world.setBlock(x, y - 1, z, Block.dirt.blockID);
					int additionalCanopyHeight = 3;
					
					for (int j = y - additionalCanopyHeight + treeHeight; j <= y + treeHeight; ++j) {
						int offsetY = j - (y + treeHeight);
						int maxOffsetXZ = 1 - offsetY;
						
						for (int i = x - maxOffsetXZ; i <= x + maxOffsetXZ; ++i) {
							int offsetX = i - x;
							
							for (int k = z - maxOffsetXZ; k <= z + maxOffsetXZ; ++k) {
								int offsetZ = k - z;
								
								if ((Math.abs(offsetX) != maxOffsetXZ || Math.abs(offsetZ) != maxOffsetXZ || rand.nextInt(2) != 0 && offsetY != 0) &&
										!Block.opaqueCubeLookup[world.getBlockId(i, j, k)]) {
									this.setBlockAndMetadata(world, i, j, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
								}
							}
						}
					}
					
					for (int j = 0; j < treeHeight; ++j) {
						int blockID = world.getBlockId(x, y + j, z);
						
						if (world.isAirBlock(x, y + j, z) || (Block.blocksList[blockID] != null && Block.blocksList[blockID].isLeafBlock(world, x, y + j, z))) {
							this.setBlockAndMetadata(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
							
							if (j > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + j, z)) {
									this.setBlockAndMetadata(world, x - 1, y + j, z, Block.vine.blockID, 8, isWorldGen);
								}
								
								if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + j, z)) {
									this.setBlockAndMetadata(world, x + 1, y + j, z, Block.vine.blockID, 2, isWorldGen);
								}
								
								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + j, z - 1)) {
									this.setBlockAndMetadata(world, x, y + j, z - 1, Block.vine.blockID, 1, isWorldGen);
								}
								
								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + j, z + 1)) {
									this.setBlockAndMetadata(world, x, y + j, z + 1, Block.vine.blockID, 4, isWorldGen);
								}
							}
						}
					}
					
					this.setBlockAndMetadata(world, x - 3, y + (treeHeight - 3), z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
					this.setBlockAndMetadata(world, x + 3, y + (treeHeight - 3), z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 3), z - 3, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 3), z + 3, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
					this.setBlockAndMetadata(world, x - 2, y + (treeHeight - 4), z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x + 2, y + (treeHeight - 4), z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 4), z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 4), z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x - 2, y + (treeHeight - 5), z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x + 2, y + (treeHeight - 5), z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 5), z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 5), z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x - 1, y + (treeHeight - 6), z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
					this.setBlockAndMetadata(world, x + 1, y + (treeHeight - 6), z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 6), z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 6), z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 3), z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 2), z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 1), z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight), z, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 4), z, 0, 0, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 5), z, 0, 0, isWorldGen);
					this.setBlockAndMetadata(world, x - 1, y + (treeHeight - 3), z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
					this.setBlockAndMetadata(world, x + 1, y + (treeHeight - 3), z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 3), z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 3), z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
					this.setBlockAndMetadata(world, x, y + (treeHeight - 2), z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);

					this.setBlockAndMetadata(world, x, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
					
					for (int j = y - 3 + treeHeight; j <= y + treeHeight; ++j) {
						int offsetY = j - (y + treeHeight);
						int maxOffsetXZ = 2 - offsetY / 2;
						
						for (int i = x - maxOffsetXZ; i <= x + maxOffsetXZ; ++i) {
							for (int k = z - maxOffsetXZ; k <= z + maxOffsetXZ; ++k) {
								if (world.getBlockId(i, j, k) == this.woodType.leavesBlockID) {
									if (rand.nextInt(4) == 0 && world.getBlockId(i - 1, j, k) == 0) {
										growVines(world, i - 1, j, k, 8, isWorldGen);
									}
									
									if (rand.nextInt(4) == 0 && world.getBlockId(i + 1, j, k) == 0) {
										growVines(world, i + 1, j, k, 2, isWorldGen);
									}
									
									if (rand.nextInt(4) == 0 && world.getBlockId(i, j, k - 1) == 0) {
										growVines(world, i, j, k - 1, 1, isWorldGen);
									}
									
									if (rand.nextInt(4) == 0 && world.getBlockId(i, j, k + 1) == 0) {
										growVines(world, i, j, k + 1, 4, isWorldGen);
									}
								}
							}
						}
					}
					
					return true;
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
	
	private void growVines(World world, int x, int y, int z, int facing, boolean isWorldGen) {
		this.setBlockAndMetadata(world, x, y, z, Block.vine.blockID, facing, isWorldGen);
		int length = 4;
		
		while (true) {
			y--;
			
			if (world.getBlockId(x, y, z) != 0 || length <= 0) {
				return;
			}
			
			this.setBlockAndMetadata(world, x, y, z, Block.vine.blockID, facing, isWorldGen);
			length--;
		}
	}
}
