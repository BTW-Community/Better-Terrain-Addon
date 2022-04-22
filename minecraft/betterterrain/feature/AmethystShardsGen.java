package betterterrain.feature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import betterterrain.DecoIntegration;
import net.minecraft.src.DecoUtilsBlock;
import net.minecraft.src.FCUtilsBlockPos;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class AmethystShardsGen extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int count = 0; count < 64; count++) {
			int i = x + rand.nextInt(8) - rand.nextInt(8);
			int j = y + rand.nextInt(8) - rand.nextInt(8);
			int k = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(i, j, k)) {
				ArrayList<Integer> validOrientations = new ArrayList(0);

				for (int dir = 0; dir < 6; dir++) {
					FCUtilsBlockPos pos = new FCUtilsBlockPos(i, j, k, DecoUtilsBlock.getOppositeFacing(dir));

					if (world.getBlockId(pos.i, pos.j, pos.k) == DecoIntegration.amethyst.blockID) {
						validOrientations.add(dir);
					}
				}

				if (validOrientations.size() > 0) {
					int meta = validOrientations.get(rand.nextInt(validOrientations.size()));
					world.setBlock(i, j, k, DecoIntegration.amethystShardBlock.blockID, meta, 2);
				}
			}
		}

		return true;
	}
}