package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class HugeTaigaTreeGrower extends AbstractTreeGrower {
	protected HugeTaigaTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		// Hack to fix alignment
		x++;
		z++;
		
		int treeHeight = rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1) + this.minTreeHeight;
		int canopyHeight = rand.nextInt(5) + 10;
		int var8 = treeHeight - canopyHeight;
		int var9 = 4;
		boolean canGrow = true;
		
		if (y >= 1 && y + treeHeight + 1 <= 256) {
			for (int j = y; j <= y + 1 + treeHeight && canGrow; ++j) {
				int var24;
				
				if (j - y < canopyHeight) {
					var24 = 0;
				}
				else {
					var24 = var9;
				}
				
				for (int i = x - var24; i <= x + var24 && canGrow; ++i) {
					for (int k = z - var24; k <= z + var24 && canGrow; ++k) {
						if (j >= 0 && j < 256) {
							int var15 = world.getBlockId(i, j, k);
							
							if (var15 != 0 && !Block.blocksList[var15].isLeafBlock(world, i, j, k)) {
								canGrow = false;
							}
						}
						else {
							canGrow = false;
						}
					}
				}
			}
			
			if (canGrow) {
				int blockIDBelow1 = world.getBlockId(x, y - 1, z);
				int blockIDBelow2 = world.getBlockId(x - 1, y - 1, z);
				int blockIDBelow3 = world.getBlockId(x, y - 1, z - 1);
				int blockBelowID4 = world.getBlockId(x - 1, y - 1, z - 1);
				
				if (y < 256 - treeHeight - 1 && blockIDBelow1 != 0 && Block.blocksList[blockIDBelow1].canSaplingsGrowOnBlock(world, x, y - 1, z) &&
						blockIDBelow2 != 0 && Block.blocksList[blockIDBelow2].canSaplingsGrowOnBlock(world, x - 1, y, z) && blockIDBelow3 != 0 &&
						Block.blocksList[blockIDBelow3].canSaplingsGrowOnBlock(world, x, y, z - 1) && blockBelowID4 != 0 &&
						Block.blocksList[blockBelowID4].canSaplingsGrowOnBlock(world, x - 1, y, z - 1))
				{
					int canopySize = rand.nextInt(2);
					int var16 = 1;
					boolean isSmallCanopyLayer = false;
					
					for (int yOffset = 0; yOffset <= var8; ++yOffset) {
						int j = y + treeHeight - yOffset;
						
						for (int i = x - canopySize; i <= x + canopySize; ++i) {
							int xOffset = i - x;
							
							for (int k = z - canopySize; k <= z + canopySize; ++k) {
								int zOffset = k - z;
								
								if ((Math.abs(xOffset) != canopySize || Math.abs(zOffset) != canopySize || canopySize <= 0) &&
										(world.isAirBlock(i, j, k) || Block.blocksList[world.getBlockId(i, j, k)].blockMaterial.isReplaceable()))
								{
									this.setBlockAndMetadata(world, i, j, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
									this.setBlockAndMetadata(world, i - 1, j, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
									this.setBlockAndMetadata(world, i, j, k - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
									this.setBlockAndMetadata(world, i - 1, j, k - 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
								}
							}
						}
						
						if (canopySize >= var16) {
							canopySize = isSmallCanopyLayer ? 1 : 0;
							isSmallCanopyLayer = true;
							++var16;
							
							if (var16 > var9) {
								var16 = var9;
							}
						}
						else {
							++canopySize;
						}
					}
					
					int logHeightOffset = rand.nextInt(3);
					
					for (int j = 0; j < treeHeight - logHeightOffset; ++j) {
						int blockID = world.getBlockId(x, y + j, z);
						
						if (blockID == 0 || Block.blocksList[blockID].isLeafBlock(world, x, y + j, z)) {
							this.setBlockAndMetadata(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
							this.setBlockAndMetadata(world, x - 1, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
							this.setBlockAndMetadata(world, x, y + j, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
							this.setBlockAndMetadata(world, x - 1, y + j, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
						}
					}
					
					this.setBlockAndMetadata(world, x, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x - 1, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x, y, z - 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
					this.setBlockAndMetadata(world, x - 1, y, z - 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
					
					return true;
				}
			}
		}
		
		return false;
	}
}
