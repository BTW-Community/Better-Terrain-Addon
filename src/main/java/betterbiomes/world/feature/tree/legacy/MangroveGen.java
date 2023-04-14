package betterbiomes.world.feature.tree.legacy;

import java.util.Random;

import betterterrain.world.util.WorldTypeInterface;
import deco.block.DecoBlocks;
import deco.block.blocks.legacy.LegacyDecoSaplingBlock;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class MangroveGen extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int blockIDBelow = world.getBlockId(x, y - 1, z);
		Block blockBelow = Block.blocksList[blockIDBelow];

		if (blockBelow != null && (blockBelow.canSaplingsGrowOnBlock(world, x, y, z) || blockBelow.blockMaterial == Material.water)) {
			if (((WorldTypeInterface) world.provider.terrainType).isDeco()) {
				return ((LegacyDecoSaplingBlock) DecoBlocks.legacyMangroveSapling).generateTree(world, rand, x, y, z, 0);
			}
			else {
				return generateVanillaBlocks(world, rand, x, y, z);
			}
		}

		return false;
	}

	public boolean generateVanillaBlocks(World world, Random rand, int x, int y, int z) {
		int var7 = 9 + rand.nextInt(5);

		for (int j = 6; j < var7; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		this.createSwampRoots(world, rand, x, y, z);
		this.createRainForestBranch(world, rand, x, y + var7 - 6, z);
		this.createRainForestLeaves(world, rand, x, y + var7 - 7, z, 1);
		this.createRainForestLeaves(world, rand, x, y + var7 - 6, z, 2);
		this.createRainForestLeaves(world, rand, x, y + var7 - 5, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 - 4, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 - 3, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 - 2, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 - 1, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 + 0, z, 3);
		this.createRainForestLeaves(world, rand, x, y + var7 + 1, z, 2);
		this.createRainForestLeaves(world, rand, x, y + var7 + 2, z, 1);
		world.setBlock(x, y + var7, z, Block.wood.blockID);
		world.setBlock(x, y + var7 + 1, z, Block.wood.blockID);

		return true;
	}

	private void createSwampRoots(World world, Random rand, int x, int y, int z) {
		int offset1 = 3 + rand.nextInt(4);

		for (int var7 = offset1; var7 < 8; ++var7) {
			world.setBlock(x, y + var7, z, Block.wood.blockID);
		}

		world.setBlock(x - 1, y + offset1, z, Block.wood.blockID);
		world.setBlock(x - 1, y + offset1 - 1, z, Block.wood.blockID);
		world.setBlock(x - 2, y + offset1 - 1, z, Block.wood.blockID);
		world.setBlock(x - 2, y + offset1 - 2, z, Block.wood.blockID);
		world.setBlock(x - 2, y + offset1 - 3, z, Block.wood.blockID);
		world.setBlock(x - 3, y + offset1 - 3, z, Block.wood.blockID);

		int rootLength = 0;
		int maxRootLength = 10;
		int j;

		for (j = y + offset1 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x - 3, j - 1, z);

			if (blockID != 0 &&
					blockID != Block.waterMoving.blockID &&
					blockID != Block.waterStill.blockID &&
					blockID != Block.leaves.blockID &&
					blockID != Block.wood.blockID &&
					blockID != Block.vine.blockID &&
					blockID != Block.tallGrass.blockID)
			{
				break;
			}

			rootLength++;

			if (rootLength > maxRootLength + offset1)
				break;

			world.setBlock(x - 3, j, z, Block.wood.blockID);
		}

		world.setBlockAndMetadata(x - 3, j, z, Block.wood.blockID, 12);

		int offset2 = 3 + rand.nextInt(4);

		for (j = offset2; j < 8; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		world.setBlock(x + 1, y + offset2, z, Block.wood.blockID);
		world.setBlock(x + 1, y + offset2 - 1, z, Block.wood.blockID);
		world.setBlock(x + 2, y + offset2 - 1, z, Block.wood.blockID);
		world.setBlock(x + 2, y + offset2 - 2, z, Block.wood.blockID);
		world.setBlock(x + 2, y + offset2 - 3, z, Block.wood.blockID);
		world.setBlock(x + 3, y + offset2 - 3, z, Block.wood.blockID);

		rootLength = 0;

		for (j = y + offset2 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x + 3, j - 1, z);

			if (blockID != 0 && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}
			rootLength++;

			if (rootLength > maxRootLength + offset2)
				break;

			world.setBlock(x + 3, j, z, Block.wood.blockID);
		}

		world.setBlockAndMetadata(x + 3, j, z, Block.wood.blockID, 12);

		int offset3 = 3 + rand.nextInt(4);

		for (j = offset3; j < 8; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		world.setBlock(x, y + offset3, z - 1, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 1, z - 1, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 1, z - 2, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 2, z - 2, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 3, z - 2, Block.wood.blockID);
		world.setBlock(x, y + offset3 - 3, z - 3, Block.wood.blockID);

		rootLength = 0;

		for (j = y + offset3 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x, j - 1, z - 3);

			if (blockID != 0 && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}
			rootLength++;

			if (rootLength > maxRootLength + offset3)
				break;

			world.setBlock(x, j, z - 3, Block.wood.blockID);
		}

		world.setBlockAndMetadata(x, j, z - 3, Block.wood.blockID, 12);

		int offset4 = 3 + rand.nextInt(4);

		for (j = offset4; j < 8; ++j) {
			world.setBlock(x, y + j, z, Block.wood.blockID);
		}

		world.setBlock(x, y + offset4, z + 1, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 1, z + 1, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 1, z + 2, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 2, z + 2, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 3, z + 2, Block.wood.blockID);
		world.setBlock(x, y + offset4 - 3, z + 3, Block.wood.blockID);

		rootLength = 0;

		for (j = y + offset4 - 3; j > 50; --j) {
			int blockID = world.getBlockId(x, j - 1, z + 3);

			if (blockID != 0 && blockID != Block.waterMoving.blockID && blockID != Block.waterStill.blockID && blockID != Block.leaves.blockID && blockID != Block.wood.blockID && blockID != Block.vine.blockID && blockID != Block.tallGrass.blockID) {
				break;
			}
			rootLength++;

			if (rootLength > maxRootLength + offset4)
				break;

			world.setBlock(x, j, z + 3, Block.wood.blockID);
		}

		world.setBlockAndMetadata(x, j, z + 3, Block.wood.blockID, 12);
	}

	private void createRainForestBranch(World world, Random rand, int x, int y, int z) {
		int var6;
		int var7;

		if (rand.nextInt(4) == 0) {
			world.setBlock(x + 1, y, z, Block.wood.blockID);
			world.setBlock(x + 2, y + 1, z, Block.wood.blockID);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x + 2, y - 5 + var6, z, 1);
			this.createRainForestLeaves(world, rand, x + 2, y - 4 + var6, z, 2);
			this.createRainForestLeaves(world, rand, x + 2, y - 3 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x + 2, y - 2 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x + 2, y - 1 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x + 2, y + 0 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x + 2, y + 1 + var6, z, 2);
			this.createRainForestLeaves(world, rand, x + 2, y + 2 + var6, z, 1);

			for (var7 = 0; var7 < var6; ++var7) {
				world.setBlock(x + 2, y + 2 + var7, z, Block.wood.blockID);
			}
		}

		if (rand.nextInt(4) == 0) {
			world.setBlock(x - 1, y, z, Block.wood.blockID);
			world.setBlock(x - 2, y + 1, z, Block.wood.blockID);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x - 2, y - 5 + var6, z, 1);
			this.createRainForestLeaves(world, rand, x - 2, y - 4 + var6, z, 2);
			this.createRainForestLeaves(world, rand, x - 2, y - 3 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x - 2, y - 2 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x - 2, y - 1 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x - 2, y + 0 + var6, z, 3);
			this.createRainForestLeaves(world, rand, x - 2, y + 1 + var6, z, 2);
			this.createRainForestLeaves(world, rand, x - 2, y + 2 + var6, z, 1);

			for (var7 = 0; var7 < var6; ++var7)
			{
				world.setBlock(x - 2, y + 2 + var7, z, Block.wood.blockID);
			}
		}

		if (rand.nextInt(4) == 0) {
			world.setBlock(x, y, z + 1, Block.wood.blockID);
			world.setBlock(x, y + 1, z + 2, Block.wood.blockID);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x, y - 5 + var6, z + 2, 1);
			this.createRainForestLeaves(world, rand, x, y - 4 + var6, z + 2, 2);
			this.createRainForestLeaves(world, rand, x, y - 3 + var6, z + 2, 3);
			this.createRainForestLeaves(world, rand, x, y - 2 + var6, z + 2, 3);
			this.createRainForestLeaves(world, rand, x, y - 1 + var6, z + 2, 3);
			this.createRainForestLeaves(world, rand, x, y + 0 + var6, z + 2, 3);
			this.createRainForestLeaves(world, rand, x, y + 1 + var6, z + 2, 2);
			this.createRainForestLeaves(world, rand, x, y + 2 + var6, z + 2, 1);

			for (var7 = 0; var7 < var6; ++var7) {
				world.setBlock(x, y + 2 + var7, z + 2, Block.wood.blockID);
			}
		}

		if (rand.nextInt(4) == 0) {
			world.setBlock(x, y, z - 1, Block.wood.blockID);
			world.setBlock(x, y + 1, z - 2, Block.wood.blockID);
			var6 = rand.nextInt(4) + 4;
			this.createRainForestLeaves(world, rand, x, y - 5 + var6, z - 2, 1);
			this.createRainForestLeaves(world, rand, x, y - 4 + var6, z - 2, 2);
			this.createRainForestLeaves(world, rand, x, y - 3 + var6, z - 2, 3);
			this.createRainForestLeaves(world, rand, x, y - 2 + var6, z - 2, 3);
			this.createRainForestLeaves(world, rand, x, y - 1 + var6, z - 2, 3);
			this.createRainForestLeaves(world, rand, x, y + 0 + var6, z - 2, 3);
			this.createRainForestLeaves(world, rand, x, y + 1 + var6, z - 2, 2);
			this.createRainForestLeaves(world, rand, x, y + 2 + var6, z - 2, 1);

			for (var7 = 0; var7 < var6; ++var7) {
				world.setBlock(x, y + 2 + var7, z - 2, Block.wood.blockID);
			}
		}
	}

	private void createRainForestLeaves(World world, Random rand, int x, int y, int z, int radius) {
		for (int i = -radius + x; i < radius + 1 + x; ++i) {
			for (int k = -radius + z; k < radius + 1 + z; ++k) {
				int blockID = world.getBlockId(i, y, k);

				if (blockID == 0 && (i != -radius + x || k != -radius + z) && (i != -radius + x || k != radius + z) && (i != radius + x || k != -radius + z) && (i != radius + x || k != radius + z)) {
					if (i == -radius + x || i == radius + x || k == -radius + z || k == radius + z) {
						if (rand.nextInt(3) == 0)
							continue;
					}

					world.setBlock(i, y, k, Block.leaves.blockID);
				}
			}
		}
	}
}
