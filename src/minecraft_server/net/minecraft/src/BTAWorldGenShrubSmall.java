package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenShrubSmall extends WorldGenerator {
	private int woodMeta;
	private int leafMeta;
	
	public BTAWorldGenShrubSmall() {
		this(0, 0);
	}
	
	public BTAWorldGenShrubSmall(int woodMeta, int leafMeta) {
		this.woodMeta = woodMeta;
		this.leafMeta = leafMeta;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;
		
		world.setBlockAndMetadata(x, y, z, Block.wood.blockID, woodMeta);
		world.setBlockAndMetadata(x, y + 1, z, Block.wood.blockID, woodMeta);

		world.setBlockAndMetadata(x + 1, y + 1, z, Block.leaves.blockID, leafMeta);
		world.setBlockAndMetadata(x, y + 1, z + 1, Block.leaves.blockID, leafMeta);
		world.setBlockAndMetadata(x - 1, y + 1, z, Block.leaves.blockID, leafMeta);
		world.setBlockAndMetadata(x, y + 1, z - 1, Block.leaves.blockID, leafMeta);
		world.setBlockAndMetadata(x, y + 2, z, Block.leaves.blockID, leafMeta);
		
		return true;
	}
}