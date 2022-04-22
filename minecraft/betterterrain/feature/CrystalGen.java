package betterterrain.feature;

import java.util.Random;

import betterterrain.DecoIntegration;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class CrystalGen extends WorldGenerator {
	private boolean useGlowstone;
	
	public CrystalGen(boolean useGlowstone) {
		this.useGlowstone = useGlowstone;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockId(x, y, z) != 0) {
			return false;
		}
		else {
			int dir = rand.nextBoolean() ? 1 : -1;
			
			while(world.getBlockId(x, y, z) == 0 || doesCrystalIgnoreBlock(world.getBlockId(x, y, z))) {
				y -= dir;
			}
			y += dir;
			
			double radius = rand.nextInt(4) + 2;
			
			for (int i = (int) -radius; i <= radius; i++) {
				for (int k = (int) -radius; k <= radius; k++) {
					if (i * i + k * k <= radius * radius && doesCrystalIgnoreBlock(world.getBlockId(x + i, y - dir, z + k))) {
						return false;
					}
				}
			}
			
			int heightScale = rand.nextInt(4) + 6;
			double xAngle = 0;
			double zAngle = 0;

			double dx = x;
			double dy = y;
			double dz = z;
			
			while (xAngle == 0 && zAngle == 0) {
				xAngle = rand.nextInt(5) - 2;
				zAngle = rand.nextInt(5) - 2;
				
				xAngle /= 2;
				zAngle /= 2;
			}

			for (; radius >= 0; radius--) {
				if (radius < 1)
					heightScale = 1;
				
				boolean halfRadius = false;
				
				for (int height = 0; height < heightScale; height++) {
					//if (height > heightScale / 2D && !halfRadius && (radius <= 2 || xAngle == 0 || zAngle == 0)) {
					//	radius -= 0.5;
					//	halfRadius = true;
					//}
					radius -= 1D/heightScale;

					double radiusSq = radius < 2 ? (radius - 0.25) * (radius - 0.25) : radius * radius;  
					
					for (int i = (int) -radius; i <= radius; i++) {
						for (int k = (int) -radius; k <= radius; k++) {
							if (i * i + k * k <= radiusSq) {
								if (rand.nextInt(2) == 0 && useGlowstone) {
									world.setBlock((int) dx + i, (int) dy, (int) dz + k, Block.glowStone.blockID, 0, 2);
								}
								else {
									world.setBlock((int) dx + i, (int) dy, (int) dz + k, DecoIntegration.amethyst.blockID, 0, 2);
								}
							}
						}
					}

					dy += dir;
					dx += xAngle;
					dz += zAngle;

					if (dy > 125 || dy < 5) {
						return true;
					}
				}
			}

			return true;
		}
	}
	
	public boolean doesCrystalIgnoreBlock(int blockID) {
		return blockID == 0 || blockID == Block.lavaStill.blockID || blockID == DecoIntegration.amethystShardBlock.blockID;
	}
}