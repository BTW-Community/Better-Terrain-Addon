package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenBasaltPillar extends WorldGenerator {

	public BTAWorldGenBasaltPillar() {
		
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int radius = 4;
		int id = BTADecoIntegration.isDecoInstalled() ? BTADecoIntegration.basalt.blockID : Block.stone.blockID;
		
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