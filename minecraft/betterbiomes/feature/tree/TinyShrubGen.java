package betterbiomes.feature.tree;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class TinyShrubGen extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;
		
		world.setBlock(x, y - 1, z, Block.wood.blockID);
		world.setBlock(x, y, z, Block.leaves.blockID);
		
		return true;
	}
}