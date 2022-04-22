package betterterrain.feature;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WillowGen extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(3) + 10;
		
		if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;
		
		for (int j = y; j <= y + height; j++) {
			int offset = j > y + height - 7 ? 3 : 0;
			
			for (int i = x - offset; i <= x + 1 + offset; i++) {
				for (int k = z - offset; k <= z + 1 + offset; k++) {
					if (!world.isAirBlock(i, j, k)) {
						return false;
					}
				}
			}
		}
		
		world.setBlockAndMetadata(x, y, z, Block.wood.blockID, 12);
		world.setBlockAndMetadata(x + 1, y, z, Block.wood.blockID, 12);
		world.setBlockAndMetadata(x + 1, y, z + 1, Block.wood.blockID, 12);
		world.setBlockAndMetadata(x, y, z + 1, Block.wood.blockID, 12);
		
		this.setBlockAndMetadataIfEmpty(world, x - 1, y, z + 0, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x - 1, y, z + 1, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x + 0, y, z - 1, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y, z - 1, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y, z + 0, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y, z + 1, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x + 0, y, z + 2, Block.wood.blockID, 12);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y, z + 2, Block.wood.blockID, 12);
		
		world.setBlockAndMetadata(x, y - 1, z, Block.dirt.blockID, 0);
		world.setBlockAndMetadata(x + 1, y - 1, z, Block.dirt.blockID, 0);
		world.setBlockAndMetadata(x + 1, y - 1, z + 1, Block.dirt.blockID, 0);
		world.setBlockAndMetadata(x, y - 1, z + 1, Block.dirt.blockID, 0);
		
		this.setBlockAndMetadataIfEmpty(world, x - 1, y - 1, z + 0, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x - 1, y - 1, z + 1, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x + 0, y - 1, z - 1, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y - 1, z - 1, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y - 1, z + 0, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x + 2, y - 1, z + 1, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x + 0, y - 1, z + 2, Block.dirt.blockID, 0);
		this.setBlockAndMetadataIfEmpty(world, x + 1, y - 1, z + 2, Block.dirt.blockID, 0);

		for (int j = y + 1; j <= y + height - 4; j++) {
			world.setBlock(x, j, z, Block.wood.blockID);
			world.setBlock(x + 1, j, z, Block.wood.blockID);
			world.setBlock(x + 1, j, z + 1, Block.wood.blockID);
			world.setBlock(x, j, z + 1, Block.wood.blockID);
		}
		
		world.setBlock(x - 1, y + height - 3, z, Block.wood.blockID);
		world.setBlock(x - 1, y + height - 2, z, Block.wood.blockID);
		world.setBlock(x - 2, y + height - 1, z, Block.wood.blockID);
		world.setBlock(x - 2, y + height - 0, z, Block.wood.blockID);

		world.setBlock(x + 1, y + height - 3, z - 1, Block.wood.blockID);
		world.setBlock(x + 1, y + height - 2, z - 1, Block.wood.blockID);
		world.setBlock(x + 1, y + height - 1, z - 2, Block.wood.blockID);
		world.setBlock(x + 1, y + height - 0, z - 2, Block.wood.blockID);
		
		world.setBlock(x + 2, y + height - 3, z + 1, Block.wood.blockID);
		world.setBlock(x + 2, y + height - 2, z + 1, Block.wood.blockID);
		world.setBlock(x + 3, y + height - 1, z + 1, Block.wood.blockID);
		world.setBlock(x + 3, y + height - 0, z + 1, Block.wood.blockID);
		
		world.setBlock(x, y + height - 3, z + 2, Block.wood.blockID);
		world.setBlock(x, y + height - 2, z + 2, Block.wood.blockID);
		world.setBlock(x, y + height - 1, z + 3, Block.wood.blockID);
		world.setBlock(x, y + height - 0, z + 3, Block.wood.blockID);
		
		this.generateMainLeaves(world, rand, x, y + height, z);
		
		int branchHeight;
		int branchSide;
		
		//Branch 1 (x, z - 1)
		branchHeight = y + height - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x, branchHeight, z - 1, Block.wood.blockID, 8);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2);
		this.setBlockAndMetadata(world, x - branchSide, branchHeight, z - 2, Block.wood.blockID, 8);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x - branchSide, branchHeight + 1, z - 3, Block.wood.blockID, 0);
		this.generateSmallLeaves(world, rand, x - branchSide, branchHeight + 1, z - 3);
		
		//Branch 2 (x - 1, z + 1)
		branchHeight = y + height - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x - 1, branchHeight, z + 1, Block.wood.blockID, 4);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2) + 1;
		this.setBlockAndMetadata(world, x - 2, branchHeight, z + branchSide, Block.wood.blockID, 4);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x - 3, branchHeight + 1, z + branchSide, Block.wood.blockID, 0);
		this.generateSmallLeaves(world, rand, x - 3, branchHeight + 1, z + branchSide);
		
		//Branch 3 (x + 1, z + 2)
		branchHeight = y + height - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x + 1, branchHeight, z + 2, Block.wood.blockID, 8);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2) + 1;
		this.setBlockAndMetadata(world, x + branchSide, branchHeight, z + 3, Block.wood.blockID, 8);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x + branchSide, branchHeight + 1, z + 4, Block.wood.blockID, 0);
		this.generateSmallLeaves(world, rand, x + branchSide, branchHeight + 1, z + 4);
		
		//Branch 4 (z, x + 2)
		branchHeight = y + height - (5 + rand.nextInt(3));
		this.setBlockAndMetadata(world, x + 2, branchHeight, z, Block.wood.blockID, 4);
		branchHeight += rand.nextInt(2);
		branchSide = rand.nextInt(2);
		this.setBlockAndMetadata(world, x + 3, branchHeight, z - branchSide, Block.wood.blockID, 4);
		branchSide += rand.nextInt(2);
		this.setBlockAndMetadata(world, x + 4, branchHeight + 1, z - branchSide, Block.wood.blockID, 0);
		this.generateSmallLeaves(world, rand, x + 4, branchHeight + 1, z - branchSide);
		
		return true;
	}
	
	public void setBlockAndMetadataIfEmpty(World world, int x, int y, int z, int id, int meta) {
		if (world.isAirBlock(x, y, z))
			world.setBlockAndMetadata(x, y, z, id, meta);
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
	
	private void generateMainLeaves(World world, Random rand, int x, int y, int z) {
		for (int i = x - 4; i <= x + 5; i++) {
			for (int k = z - 4; k <= z + 5; k++) {
				if (!this.isLeafOnCorner(i, k, x - 4, z - 4, x + 5, z + 5, true)) {
					this.setBlockAndMetadataIfEmpty(world, i, y, k, Block.leaves.blockID, 8);
				}
			}
		}

		for (int i = x - 3; i <= x + 4; i++) {
			for (int k = z - 3; k <= z + 4; k++) {
				if (!this.isLeafOnCorner(i, k, x - 3, z - 3, x + 4, z + 4, true)) {
					this.setBlockAndMetadataIfEmpty(world, i, y + 1, k, Block.leaves.blockID, 8);
				}
			}
		}

		for (int i = x - 2; i <= x + 3; i++) {
			for (int k = z - 2; k <= z + 3; k++) {
				if (!this.isLeafOnCorner(i, k, x - 2, z - 2, x + 3, z + 3, true)) {
					this.setBlockAndMetadataIfEmpty(world, i, y + 2, k, Block.leaves.blockID, 8);
				}
			}
		}

		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z - 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z - 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 0, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 5, y + j, z + 3, Block.leaves.blockID, 12);
		
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 4, y + j, z + 4, Block.leaves.blockID, 12);

		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 3, y + j, z + 5, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z + 5, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z + 5, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z + 5, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z + 5, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z + 5, Block.leaves.blockID, 12);
		
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 3, y + j, z + 4, Block.leaves.blockID, 12);

		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 3, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z + 0, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z - 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 4, y + j, z - 2, Block.leaves.blockID, 12);
		
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 3, y + j, z - 3, Block.leaves.blockID, 12);

		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z - 4, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z - 4, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z - 4, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z - 4, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z - 4, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 3, y + j, z - 4, Block.leaves.blockID, 12);
		
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 4, y + j, z - 3, Block.leaves.blockID, 12);
	}
	
	private void generateSmallLeaves(World world, Random rand, int x, int y, int z) {
		for (int i = x - 2; i <= x + 2; i++) {
			for (int k = z - 2; k <= z + 2; k++) {
				if (!this.isLeafOnCorner(i, k, x - 2, z - 2, x + 2, z + 2, false)) {
					this.setBlockAndMetadataIfEmpty(world, i, y, k, Block.leaves.blockID, 8);
				}
			}
		}

		for (int i = x - 1; i <= x + 1; i++) {
			for (int k = z - 1; k <= z + 1; k++) {
				if (!this.isLeafOnCorner(i, k, x - 1, z - 1, x + 1, z + 1, false)) {
					this.setBlockAndMetadataIfEmpty(world, i, y + 1, k, Block.leaves.blockID, 8);
				}
			}
		}
		
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z - 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z + 0, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 2, y + j, z + 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z + 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z + 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z + 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z + 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z + 0, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x - 2, y + j, z - 1, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x - 1, y + j, z - 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2); j--) this.setBlockAndMetadataIfEmpty(world, x + 0, y + j, z - 2, Block.leaves.blockID, 12);
		for (int j = 0; j >= -rand.nextInt(2) - 2; j--) this.setBlockAndMetadataIfEmpty(world, x + 1, y + j, z - 2, Block.leaves.blockID, 12);
	}
}