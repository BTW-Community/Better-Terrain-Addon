package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenBorealBirch extends WorldGenerator {
	public final int minHeight = 7;

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;

		int treeHeight = minHeight + rand.nextInt(3);

		for (int i = 0; i < treeHeight; i++) {
			world.setBlockAndMetadata(x, y + i, z, Block.wood.blockID, 2);
		}
		world.setBlockAndMetadata(x, y, z, Block.wood.blockID, 2 | 12); //places stump

		int y2 = y + treeHeight - 1;
		//Level 1
		setBlockAndMetadata(world, x, y2 + 2, z, Block.leaves.blockID, 2);
		//Level 2
		setBlockAndMetadata(world, x, y2 + 1, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x + 1, y2 + 1, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x, y2 + 1, z + 1, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x - 1, y2 + 1, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x, y2 + 1, z - 1, Block.leaves.blockID, 2);
		//Level 3
		setBlockAndMetadata(world, x + 1, y2, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x, y2, z + 1, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x - 1, y2, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x, y2, z - 1, Block.leaves.blockID, 2);
		//Level 4-6
		for (int i = 1; i < 4; i++) {
			setBlockAndMetadata(world, x + 1, y2 - i, z, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x + 2, y2 - i, z, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x + 1, y2 - i, z + 1, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x, y2 - i, z + 1, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x, y2 - i, z + 2, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x - 1, y2 - i, z + 1, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x - 1, y2 - i, z, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x - 2, y2 - i, z, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x - 1, y2 - i, z - 1, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x, y2 - i, z - 1, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x, y2 - i, z - 2, Block.leaves.blockID, 2);
			setBlockAndMetadata(world, x + 1, y2 - i, z - 1, Block.leaves.blockID, 2);
		}
		//Level 7
		setBlockAndMetadata(world, x + 1, y2 - 4, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x, y2 - 4, z + 1, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x - 1, y2 - 4, z, Block.leaves.blockID, 2);
		setBlockAndMetadata(world, x, y2 - 4, z - 1, Block.leaves.blockID, 2);

		return true;
	}
	
	public void setBlockAndMetadata(World world, int x, int y, int z, int blockId, int meta) {
		int idReplace = world.getBlockId(x, y, z);
		
		if (idReplace == 0 || Block.blocksList[idReplace].blockMaterial.isReplaceable() || idReplace == Block.leaves.blockID) {
			world.setBlockAndMetadata(x, y, z, blockId, meta);
		}
	}
}