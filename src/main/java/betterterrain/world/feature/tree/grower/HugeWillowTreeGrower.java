package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class HugeWillowTreeGrower extends AbstractTreeGrower {
	private boolean growVines;
	protected HugeWillowTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType, boolean growVines) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
		this.growVines = growVines;
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int treeHeight = rand.nextInt(3) + 10;
		
		Block blockBelow = Block.blocksList[world.getBlockId(x, y - 1, z)];
		
		if (blockBelow == null || !blockBelow.canSaplingsGrowOnBlock(world, x, y - 1, z)) {
			return false;
		}
		
		for (int j = y; j <= y + treeHeight; j++) {
			int offset = j > y + treeHeight - 7 ? 3 : 0;
			
			for (int i = x - offset; i <= x + 1 + offset; i++) {
				for (int k = z - offset; k <= z + 1 + offset; k++) {
					if (!world.isAirBlock(i, j, k)) {
						return false;
					}
				}
			}
		}
		
		this.setBlockAndMetadata(world, x, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 1, y, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 1, y, z + 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x, y, z + 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		
		this.setBlockAndMetadataIfEmpty(world, x - 1, y, z + 0, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x - 1, y, z + 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 0, y, z - 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y, z - 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y, z + 0, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y, z + 1, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 0, y, z + 2, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y, z + 2, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		
		for (int j = y + 1; j <= y + treeHeight - 4; j++) {
			this.setBlockAndMetadata(world, x, j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x + 1, j, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadata(world, x, j, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadata(world, x - 1, y + treeHeight - 3, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x - 1, y + treeHeight - 2, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x - 2, y + treeHeight - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x - 2, y + treeHeight - 0, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		this.setBlockAndMetadata(world, x + 1, y + treeHeight - 3, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 1, y + treeHeight - 2, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 1, y + treeHeight - 1, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 1, y + treeHeight - 0, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		this.setBlockAndMetadata(world, x + 2, y + treeHeight - 3, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 2, y + treeHeight - 2, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 3, y + treeHeight - 1, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x + 3, y + treeHeight - 0, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		this.setBlockAndMetadata(world, x, y + treeHeight - 3, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x, y + treeHeight - 2, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x, y + treeHeight - 1, z + 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadata(world, x, y + treeHeight - 0, z + 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		this.generateMainLeaves(world, rand, x, y + treeHeight, z, isWorldGen);
		
		int branchHeight;
		int branchSide;
		
		//Branch 1 (x, z - 1)
		branchHeight = y + treeHeight - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x, branchHeight, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2);
		this.setBlockAndMetadata(world, x - branchSide, branchHeight, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x - branchSide, branchHeight + 1, z - 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.generateSmallLeaves(world, rand, x - branchSide, branchHeight + 1, z - 3, isWorldGen);
		
		//Branch 2 (x - 1, z + 1)
		branchHeight = y + treeHeight - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x - 1, branchHeight, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2) + 1;
		this.setBlockAndMetadata(world, x - 2, branchHeight, z + branchSide, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x - 3, branchHeight + 1, z + branchSide, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.generateSmallLeaves(world, rand, x - 3, branchHeight + 1, z + branchSide, isWorldGen);
		
		//Branch 3 (x + 1, z + 2)
		branchHeight = y + treeHeight - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x + 1, branchHeight, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2) + 1;
		this.setBlockAndMetadata(world, x + branchSide, branchHeight, z + 3, this.woodType.woodBlockID, this.woodType.woodMetadata | 8, isWorldGen);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x + branchSide, branchHeight + 1, z + 4, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.generateSmallLeaves(world, rand, x + branchSide, branchHeight + 1, z + 4, isWorldGen);
		
		//Branch 4 (z, x + 2)
		branchHeight = y + treeHeight - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x + 2, branchHeight, z, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2);
		this.setBlockAndMetadata(world, x + 3, branchHeight, z - branchSide, this.woodType.woodBlockID, this.woodType.woodMetadata | 4, isWorldGen);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x + 4, branchHeight + 1, z - branchSide, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.generateSmallLeaves(world, rand, x + 4, branchHeight + 1, z - branchSide, isWorldGen);
		
		return true;
	}
	
	public void setBlockAndMetadataIfEmpty(World world, int x, int y, int z, int id, int meta, boolean isWorldGen) {
		if (this.isReplaceable(world, x, y, z))
			this.setBlockAndMetadata(world, x, y, z, id, meta, isWorldGen);
	}
	
	private boolean isLeafOnCorner(int x, int z, int minX, int minZ, int maxX, int maxZ, boolean bigCorner) {
		if (x == minX && (z == minZ || (z == minZ + 1 && bigCorner)))
			return true;
		if (z == minZ && x == minX + 1 && bigCorner)
			return true;
		
		if (x == minX && (z == maxZ || (z == maxZ - 1 && bigCorner)))
			return true;
		if (z == maxZ && x == minX + 1 && bigCorner)
			return true;
		
		if (x == maxX && (z == minZ || (z == minZ + 1 && bigCorner)))
			return true;
		if (z == minZ && x == maxX - 1 && bigCorner)
			return true;
		
		if (x == maxX && (z == maxZ || (z == maxZ - 1 && bigCorner)))
			return true;
		if (z == maxZ && x == maxX - 1 && bigCorner)
			return true;
		
		return false;
	}
	
	private void generateMainLeaves(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		for (int i = x - 4; i <= x + 5; i++) {
			for (int k = z - 4; k <= z + 5; k++) {
				if (!this.isLeafOnCorner(i, k, x - 4, z - 4, x + 5, z + 5, true)) {
					this.setBlockAndMetadataIfEmpty(world, i, y, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}
		
		for (int i = x - 3; i <= x + 4; i++) {
			for (int k = z - 3; k <= z + 4; k++) {
				if (!this.isLeafOnCorner(i, k, x - 3, z - 3, x + 4, z + 4, true)) {
					this.setBlockAndMetadataIfEmpty(world, i, y + 1, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}
		
		for (int i = x - 2; i <= x + 3; i++) {
			for (int k = z - 2; k <= z + 3; k++) {
				if (!this.isLeafOnCorner(i, k, x - 2, z - 2, x + 3, z + 3, true)) {
					this.setBlockAndMetadataIfEmpty(world, i, y + 2, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}

		if (growVines) {
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z - 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z - 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 0, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 3, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 4, y + j, z + 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 3, y + j, z + 5, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z + 5, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z + 5, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z + 5, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z + 5, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z + 5, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 3, y + j, z + 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 3, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 0, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z - 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z - 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 3, y + j, z - 3, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z - 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z - 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z - 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z - 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z - 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 3, y + j, z - 4, DecoBlocks.willowVines.blockID, 0, isWorldGen);

			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 4, y + j, z - 3, DecoBlocks.willowVines.blockID, 0, isWorldGen);
		}
	}
	
	private void generateSmallLeaves(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		for (int i = x - 2; i <= x + 2; i++) {
			for (int k = z - 2; k <= z + 2; k++) {
				if (!this.isLeafOnCorner(i, k, x - 2, z - 2, x + 2, z + 2, false)) {
					this.setBlockAndMetadataIfEmpty(world, i, y, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}
		
		for (int i = x - 1; i <= x + 1; i++) {
			for (int k = z - 1; k <= z + 1; k++) {
				if (!this.isLeafOnCorner(i, k, x - 1, z - 1, x + 1, z + 1, false)) {
					this.setBlockAndMetadataIfEmpty(world, i, y + 1, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}

		if (growVines) {
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z - 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z + 0, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z + 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z + 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z + 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z + 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z + 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z + 0, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z - 1, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z - 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z - 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
			for (int j = -1; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z - 2, DecoBlocks.willowVines.blockID, 0, isWorldGen);
		}
	}
}
