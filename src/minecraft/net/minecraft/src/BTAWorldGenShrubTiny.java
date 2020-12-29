package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenShrubTiny extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;
		
		world.setBlock(x, y, z, Block.wood.blockID);
		world.setBlock(x, y + 1, z, Block.leaves.blockID);
		
		return true;
	}
}