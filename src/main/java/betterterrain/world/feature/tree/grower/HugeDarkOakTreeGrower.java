package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class HugeDarkOakTreeGrower extends AbstractTreeGrower {
	protected HugeDarkOakTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int treeHeight = rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1) + minTreeHeight;
		
		if (!canTreeGrowHere(world, x, y, z, treeHeight, isWorldGen)) {
			return false;
		}
		
		// Place trunk
		for (int j = 0; j <= treeHeight; j++) {
			for (int i = 0; i <= 1; i++) {
				for (int k = 0; k <= 1; k++) {
					if (j == 0) {
						this.setBlockAndMetadata(world, x + i, y + j, z + k, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
					}
					else {
						this.setBlockAndMetadata(world, x + i, y + j, z + k, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
					}
				}
			}
		}
		
		// Place branches
		for (int i = -1; i <= 2; i++) {
			for (int k = -1; k <= 2; k++) {
				// Skip main trunk
				if (i == -1 || i == 2 || k == -1 || k == 2) {
					if (rand.nextInt(4) == 0) {
						int branchHeight = 3 + rand.nextInt(3);
						
						for (int j = treeHeight; j > treeHeight - branchHeight; j--) {
							this.setBlockAndMetadata(world, x + i, y + j, z + k, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
						}
					}
				}
			}
		}
		
		// Place canopy
		// Top layer
		for (int i = -2; i <= 2; i++) {
			for (int k = -2; k <= 2; k++) {
				if (i * i + k * k <= 2 * 2) {
					if (this.isReplaceable(world, x + i + 0, y + treeHeight + 1, z + k + 0))
						this.setBlockAndMetadata(world, x + i + 0, y + treeHeight + 1, z + k + 0, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					if (this.isReplaceable(world, x + i + 1, y + treeHeight + 1, z + k + 0))
						this.setBlockAndMetadata(world, x + i + 1, y + treeHeight + 1, z + k + 0, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					if (this.isReplaceable(world, x + i + 0, y + treeHeight + 1, z + k + 1))
						this.setBlockAndMetadata(world, x + i + 0, y + treeHeight + 1, z + k + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					if (this.isReplaceable(world, x + i + 1, y + treeHeight + 1, z + k + 1))
						this.setBlockAndMetadata(world, x + i + 1, y + treeHeight + 1, z + k + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}
		
		// Middle and bottom layer
		for (int j = treeHeight; j >= treeHeight - 1; j--) {
			for (int i = -3; i <= 3; i++) {
				for (int k = -3; k <= 3; k++) {
					double radius = rand.nextInt(2) == 0 ? 4 : 3;
					
					if (i * i + k * k <= radius * radius) {
						if (this.isReplaceable(world, x + i + 0, y + j, z + k + 0))
							this.setBlockAndMetadata(world, x + i + 0, y + j, z + k + 0, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
						if (this.isReplaceable(world, x + i + 1, y + j, z + k + 0))
							this.setBlockAndMetadata(world, x + i + 1, y + j, z + k + 0, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
						if (this.isReplaceable(world, x + i + 0, y + j, z + k + 1))
							this.setBlockAndMetadata(world, x + i + 0, y + j, z + k + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
						if (this.isReplaceable(world, x + i + 1, y + j, z + k + 1))
							this.setBlockAndMetadata(world, x + i + 1, y + j, z + k + 1, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
					}
				}
			}
		}
		
		return true;
	}
	
	protected boolean canTreeGrowHere(World world, int x, int y, int z, int treeHeight, boolean isWorldGen) {
		Block blockBelow = Block.blocksList[world.getBlockId(x, y - 1, z)];
		
		if (blockBelow == null || !blockBelow.canSaplingsGrowOnBlock(world, x, y - 1, z)) {
			return false;
		}
		
		for (int j = 0; j < treeHeight; j++) {
			int trunkOffset = j > treeHeight - 5 ? 1 : 0;
			
			for (int i = -trunkOffset; i <= 1 + trunkOffset; i++) {
				for (int k = -trunkOffset; k <= 1 + trunkOffset; k++) {
					if (!isLogReplaceable(world, x + i, y + j, z + k)) {
						return false;
					}
				}
			}
			
			if (j > treeHeight - 3 && isWorldGen) {
				int canopyOffset = 3;
				
				for (int i = -canopyOffset; i <= 1 + canopyOffset; i++) {
					for (int k = -canopyOffset; k <= 1 + canopyOffset; k++) {
						if (!isReplaceable(world, x + i, y + j + 1, z + k)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
}
