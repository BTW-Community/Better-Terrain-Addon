package betterterrain.world.feature.tree.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

import java.util.Random;

public class MangroveTreeGrower extends AbstractTreeGrower {
	protected MangroveTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name, minTreeHeight, maxTreeHeight, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int treeHeight = rand.nextInt(this.maxTreeHeight - this.minTreeHeight + 1) + this.minTreeHeight;

		Block blockBelow = Block.blocksList[world.getBlockId(x, y - 1, z)];

		if (blockBelow == null ||
				(!blockBelow.canSaplingsGrowOnBlock(world, x, y - 1, z) && blockBelow.blockMaterial != Material.water))
		{
			return false;
		}
		
		for (int j = 6; j < treeHeight; ++j) {
			this.setBlockAndMetadataIfEmpty(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.createSwampRoots(world, rand, x, y, z, isWorldGen);
		this.createRainForestBranch(world, rand, x, y + treeHeight - 6, z, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 7, z, 1, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 6, z, 2, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 5, z, 3, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 4, z, 3, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 3, z, 3, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 2, z, 3, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight - 1, z, 3, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight + 0, z, 3, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight + 1, z, 2, isWorldGen);
		this.createRainForestLeaves(world, rand, x, y + treeHeight + 2, z, 1, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + treeHeight, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + treeHeight + 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		return true;
	}
	
	//------------- Class Specific Methods ------------//
	
	private void createSwampRoots(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int offset1 = 3 + rand.nextInt(4);
		
		for (int var7 = offset1; var7 < 8; ++var7) {
			this.setBlockAndMetadataIfEmpty(world, x, y + var7, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x - 1, y + offset1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x - 1, y + offset1 - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x - 2, y + offset1 - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x - 2, y + offset1 - 2, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x - 2, y + offset1 - 3, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x - 3, y + offset1 - 3, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		int rootLength = 0;
		int maxRootLength = 10;
		int j;
		
		for (j = y + offset1 - 3; j > 50; --j) {
			if (!isLogReplaceable(world, x - 3, j - 1, z)) {
				break;
			}
			
			rootLength++;
			
			if (rootLength > maxRootLength + offset1)
				break;
			
			this.setBlockAndMetadataIfEmpty(world, x - 3, j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x - 3, j, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		
		int offset2 = 3 + rand.nextInt(4);
		
		for (j = offset2; j < 8; ++j) {
			this.setBlockAndMetadataIfEmpty(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x + 1, y + offset2, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y + offset2 - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y + offset2 - 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y + offset2 - 2, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y + offset2 - 3, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x + 3, y + offset2 - 3, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		rootLength = 0;
		
		for (j = y + offset2 - 3; j > 50; --j) {
			if (!isLogReplaceable(world, x + 3, j - 1, z)) {
				break;
			}
			
			rootLength++;
			
			if (rootLength > maxRootLength + offset2)
				break;
			
			this.setBlockAndMetadataIfEmpty(world, x + 3, j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x + 3, j, z, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		
		int offset3 = 3 + rand.nextInt(4);
		
		for (j = offset3; j < 8; ++j) {
			this.setBlockAndMetadataIfEmpty(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x, y + offset3, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset3 - 1, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset3 - 1, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset3 - 2, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset3 - 3, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset3 - 3, z - 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		rootLength = 0;
		
		for (j = y + offset3 - 3; j > 50; --j) {
			if (!isLogReplaceable(world, x, j - 1, z - 3)) {
				break;
			}
			
			rootLength++;
			
			if (rootLength > maxRootLength + offset3)
				break;
			
			this.setBlockAndMetadataIfEmpty(world, x, j, z - 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x, j, z - 3, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
		
		int offset4 = 3 + rand.nextInt(4);
		
		for (j = offset4; j < 8; ++j) {
			this.setBlockAndMetadataIfEmpty(world, x, y + j, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x, y + offset4, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset4 - 1, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset4 - 1, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset4 - 2, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset4 - 3, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		this.setBlockAndMetadataIfEmpty(world, x, y + offset4 - 3, z + 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		
		rootLength = 0;
		
		for (j = y + offset4 - 3; j > 50; --j) {
			if (!isLogReplaceable(world, x, j - 1, z + 3)) {
				break;
			}
			
			rootLength++;
			
			if (rootLength > maxRootLength + offset4)
				break;
			
			this.setBlockAndMetadataIfEmpty(world, x, j, z + 3, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
		}
		
		this.setBlockAndMetadataIfEmpty(world, x, j, z + 3, this.woodType.stumpBlockID, this.woodType.stumpMetadata, isWorldGen);
	}
	
	private void createRainForestBranch(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		int var6;
		int var7;
		
		if (rand.nextInt(4) == 0) {
			this.setBlockAndMetadataIfEmpty(world, x + 1, y, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadataIfEmpty(world, x + 2, y + 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x + 2, y - 5 + var6, z, 1, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y - 4 + var6, z, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y - 3 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y - 2 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y - 1 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y + 0 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y + 1 + var6, z, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x + 2, y + 2 + var6, z, 1, isWorldGen);
			
			for (var7 = 0; var7 < var6; ++var7) {
				this.setBlockAndMetadataIfEmpty(world, x + 2, y + 2 + var7, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			}
		}
		
		if (rand.nextInt(4) == 0) {
			this.setBlockAndMetadataIfEmpty(world, x - 1, y, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadataIfEmpty(world, x - 2, y + 1, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x - 2, y - 5 + var6, z, 1, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y - 4 + var6, z, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y - 3 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y - 2 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y - 1 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y + 0 + var6, z, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y + 1 + var6, z, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x - 2, y + 2 + var6, z, 1, isWorldGen);
			
			for (var7 = 0; var7 < var6; ++var7)
			{
				this.setBlockAndMetadataIfEmpty(world, x - 2, y + 2 + var7, z, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			}
		}
		
		if (rand.nextInt(4) == 0) {
			this.setBlockAndMetadataIfEmpty(world, x, y, z + 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadataIfEmpty(world, x, y + 1, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x, y - 5 + var6, z + 2, 1, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 4 + var6, z + 2, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 3 + var6, z + 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 2 + var6, z + 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 1 + var6, z + 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y + 0 + var6, z + 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y + 1 + var6, z + 2, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y + 2 + var6, z + 2, 1, isWorldGen);
			
			for (var7 = 0; var7 < var6; ++var7) {
				this.setBlockAndMetadataIfEmpty(world, x, y + 2 + var7, z + 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			}
		}
		
		if (rand.nextInt(4) == 0) {
			this.setBlockAndMetadataIfEmpty(world, x, y, z - 1, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			this.setBlockAndMetadataIfEmpty(world, x, y + 1, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x, y - 5 + var6, z - 2, 1, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 4 + var6, z - 2, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 3 + var6, z - 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 2 + var6, z - 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y - 1 + var6, z - 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y + 0 + var6, z - 2, 3, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y + 1 + var6, z - 2, 2, isWorldGen);
			this.createRainForestLeaves(world, rand, x, y + 2 + var6, z - 2, 1, isWorldGen);
			
			for (var7 = 0; var7 < var6; ++var7) {
				this.setBlockAndMetadataIfEmpty(world, x, y + 2 + var7, z - 2, this.woodType.woodBlockID, this.woodType.woodMetadata, isWorldGen);
			}
		}
	}
	
	private void createRainForestLeaves(World world, Random rand, int x, int y, int z, int radius, boolean isWorldGen) {
		for (int i = -radius + x; i < radius + 1 + x; ++i) {
			for (int k = -radius + z; k < radius + 1 + z; ++k) {
				int blockID = world.getBlockId(i, y, k);
				
				if (blockID == 0 && (i != -radius + x || k != -radius + z) && (i != -radius + x || k != radius + z) && (i != radius + x || k != -radius + z) && (i != radius + x || k != radius + z)) {
					if (i == -radius + x || i == radius + x || k == -radius + z || k == radius + z) {
						if (rand.nextInt(3) == 0)
							continue;
					}
					
					this.setBlockAndMetadataIfEmpty(world, i, y, k, this.woodType.leavesBlockID, this.woodType.leavesMetadata, isWorldGen);
				}
			}
		}
	}

	public void setBlockAndMetadataIfEmpty(World world, int x, int y, int z, int id, int meta, boolean isWorldGen) {
		if (this.isReplaceable(world, x, y, z))
			this.setBlockAndMetadata(world, x, y, z, id, meta, isWorldGen);
	}
}
