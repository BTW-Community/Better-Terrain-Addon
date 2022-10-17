package betterterrain.feature.terrain;

import java.util.Random;

import betterterrain.BTAMod;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BasaltPillarGen extends WorldGenerator {

	public BasaltPillarGen() {
		
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int radius = 4;
		int id = BTAMod.isDecoInstalled() ? DecoBlocks.basalt.blockID : Block.stone.blockID;
		
		int j = y;
		
		while (world.getBlockId(x, j, z) == 0) {
			world.setBlock(x, j, z, id);
			
			j++;
		}
		
		j = y - 1;
		
		while (world.getBlockId(x, j, z) == 0) {
			world.setBlock(x, j, z, id);
			
			j--;
		}
		
		j++;
		
		for (int i = -radius; i <= radius; i++) {
			for (int k = -radius; k <= radius; k++) {
				if (i * i + k * k <= radius * radius && world.getBlockId(x + i, j, z + k) == 0 && world.getBlockId(x + i, j - 1, z + k) != 0) {
					world.setBlock(x + i, j, z + k, id);
				}
			}
		}
		
		return true;
	}

}
