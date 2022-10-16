package betterbiomes.feature.tree;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class MangroveGen extends WorldGenerator {
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int blockIDBelow = world.getBlockId(x, y - 1, z);

		if (blockIDBelow != Block.grass.blockID && blockIDBelow != Block.waterMoving.blockID && blockIDBelow != Block.waterStill.blockID) {
			return false;
		}

		int var7 = 11 + rand.nextInt(3);

		for (int j = 6; j < var7; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		this.createSwampRoots(world, rand, x, y, z);
		this.createRainForestBranch(world, rand, x, y + var7 - 5, z);
		this.createRainForestLeaves(world, rand, x, y + var7, z, 2);
		this.createRainForestLeaves(world, rand, x, y + var7 + 1, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 + 2, z, 2);
		world.setBlock(x, y + var7, z, Block.wood.blockID);
		world.setBlock(x, y + var7 + 1, z, Block.wood.blockID);

		return true;
	}

	private void createSwampRoots(World world, Random rand, int x, int y, int z) {
		int offset1 = rand.nextInt(5);

		for (int var7 = offset1; var7 < 8; ++var7) {
			world.setBlock(x, y + var7, z, Block.wood.blockID);
		}

		world.setBlock(x - 1, y + offset1, z, Block.wood.blockID);
		world.setBlock(x - 1, y + offset1 - 1, z, Block.wood.blockID);
		world.setBlock(x - 2, y + offset1 - 1, z, Block.wood.blockID);
		world.setBlock(x - 2, y + offset1 - 2, z, Block.wood.blockID);
		world.setBlock(x - 2, y + offset1 - 3, z, Block.wood.blockID);
		world.setBlock(x - 3, y + offset1 - 3, z, Block.wood.blockID);

		for (int j = y + offset1 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x - 3, j, z);

			if (blockID != 0 && blockID != Block.grass.blockID && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}

			world.setBlock(x - 3, j, z, Block.wood.blockID);
		}

		int offset2 = 2 + rand.nextInt(5);

		for (int j = offset2; j < 8; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		world.setBlock(x + 1, y + offset2, z, Block.wood.blockID);
		world.setBlock(x + 1, y + offset2 - 1, z, Block.wood.blockID);
		world.setBlock(x + 2, y + offset2 - 1, z, Block.wood.blockID);
		world.setBlock(x + 2, y + offset2 - 2, z, Block.wood.blockID);
		world.setBlock(x + 2, y + offset2 - 3, z, Block.wood.blockID);
		world.setBlock(x + 3, y + offset2 - 3, z, Block.wood.blockID);

		for (int j = y + offset2 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x + 3, j, z);

			if (blockID != 0 && blockID != Block.grass.blockID && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}

			world.setBlock(x + 3, j, z, Block.wood.blockID);
		}

		int offset3 = 2 + rand.nextInt(5);

		for (int j = offset3; j < 8; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		world.setBlock(x, y + offset3, z - 1, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 1, z - 1, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 1, z - 2, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 2, z - 2, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 3, z - 2, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 3, z - 3, Block.wood.blockID);

		for (int j = y + offset3 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x, j, z - 3);

			if (blockID != 0 && blockID != Block.grass.blockID && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}

			world.setBlock(x, j, z - 3, Block.wood.blockID);
		}

		int offset4 = 2 + rand.nextInt(5);

		for (int j = offset4; j < 8; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		world.setBlock(x, y + offset4, z + 1, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 1, z + 1, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 1, z + 2, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 2, z + 2, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 3, z + 2, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 3, z + 3, Block.wood.blockID);

		for (int j = y + offset4 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x, j, z + 3);

			if (blockID != 0 && blockID != Block.grass.blockID && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}

			world.setBlock(x, j, z + 3, Block.wood.blockID);
		}
	}

	private void createRainForestBranch(World world, Random rand, int x, int y, int z) {
		int var6;
		int var7;

		if (rand.nextInt(4) == 0) {
			world.setBlock(x + 1, y, z, Block.wood.blockID);
			world.setBlock(x + 2, y + 1, z, Block.wood.blockID);
			world.setBlock(x + 3, y + 2, z, Block.wood.blockID);
			world.setBlock(x + 4, y + 3, z, Block.wood.blockID);
			var6 = 1 + rand.nextInt(5);
			this.createRainForestLeaves(world, rand, x + 4, y + 4 + var6, z, 2);
			this.createRainForestLeaves(world, rand, x + 4, y + 5 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x + 4, y + 6 + var6, z, 2);

			for (var7 = 0; var7 <= var6; ++var7) {
				world.setBlock(x + 4, y + 4 + var7, z, Block.wood.blockID);
			}
		}

		if (rand.nextInt(4) == 0) {
			world.setBlock(x - 1, y, z, Block.wood.blockID);
			world.setBlock(x - 2, y + 1, z, Block.wood.blockID);
			world.setBlock(x - 3, y + 2, z, Block.wood.blockID);
			world.setBlock(x - 4, y + 3, z, Block.wood.blockID);
			var6 = 1 + rand.nextInt(5);
			this.createRainForestLeaves(world, rand, x - 4, y + 4 + var6, z, 2);
			this.createRainForestLeaves(world, rand, x - 4, y + 5 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x - 4, y + 6 + var6, z, 2);

			for (var7 = 0; var7 <= var6; ++var7)
			{
				world.setBlock(x - 4, y + 4 + var7, z, Block.wood.blockID);
			}
		}

		if (rand.nextInt(4) == 0) {
			world.setBlock(x, y, z + 1, Block.wood.blockID);
			world.setBlock(x, y + 1, z + 2, Block.wood.blockID);
			world.setBlock(x, y + 2, z + 3, Block.wood.blockID);
			world.setBlock(x, y + 3, z + 4, Block.wood.blockID);
			var6 = 1 + rand.nextInt(5);
			this.createRainForestLeaves(world, rand, x, y + 4 + var6, z + 4, 2);
			this.createRainForestLeaves(world, rand, x, y + 5 + var6, z + 4, 3);
			this.createRainForestLeaves(world, rand, x, y + 6 + var6, z + 4, 2);

			for (var7 = 0; var7 <= var6; ++var7) {
				world.setBlock(x, y + 4 + var7, z + 4, Block.wood.blockID);
			}
		}

		if (rand.nextInt(4) == 0) {
			world.setBlock(x, y, z - 1, Block.wood.blockID);
			world.setBlock(x, y + 1, z - 2, Block.wood.blockID);
			world.setBlock(x, y + 2, z - 3, Block.wood.blockID);
			world.setBlock(x, y + 3, z - 4, Block.wood.blockID);
			var6 = 1 + rand.nextInt(5);
			this.createRainForestLeaves(world, rand, x, y + 4 + var6, z - 4, 2);
			this.createRainForestLeaves(world, rand, x, y + 5 + var6, z - 4, 3);
			this.createRainForestLeaves(world, rand, x, y + 6 + var6, z - 4, 2);

			for (var7 = 0; var7 <= var6; ++var7) {
				world.setBlock(x, y + 4 + var7, z - 4, Block.wood.blockID);
			}
		}
	}

	private void createRainForestLeaves(World var1, Random var2, int var3, int var4, int var5, int var6) {
		for (int var7 = -var6 + var3; var7 < var6 + 1 + var3; ++var7) {
			for (int var8 = -var6 + var5; var8 < var6 + 1 + var5; ++var8) {
				int var9 = var1.getBlockId(var7, var4, var8);

				if (var9 == 0 && (var7 != -var6 + var3 || var8 != -var6 + var5) && (var7 != -var6 + var3 || var8 != var6 + var5) && (var7 != var6 + var3 || var8 != -var6 + var5) && (var7 != var6 + var3 || var8 != var6 + var5)) {
					var1.setBlock(var7, var4, var8, Block.leaves.blockID);
				}
			}
		}
	}
}
