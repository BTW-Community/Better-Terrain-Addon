package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.BTAVersion;
import betterterrain.feature.OldOakGen;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class AncientForestSurfaceBuilder extends SurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random treeRand = new Random(seed + 2000);

		this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);

		this.treeNoiseScale = 1/256D;
	}

	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getCompatMode().isVersionAtLeast(BTAVersion.V2_0_0)) {
			int numTrees = (int) (8 + 2.9 * treeNoiseGen.noise2(this.chunkX + 16, this.chunkZ + 16, treeNoiseScale));

			for (int i = 0; i < numTrees; ++i)
			{
				int x = this.chunkX + rand.nextInt(16) + 8;
				int z = this.chunkZ + rand.nextInt(16) + 8;

				WorldGenerator gen;

				if (rand.nextInt(7) == 0) {
					gen = new OldOakGen(false);
				}
				else if (rand.nextInt(2) == 0) {
					gen = new WorldGenBigTree(false);
				}
				else {
					gen = new WorldGenTrees(false, 6, 0, 0, false);
				}

				gen.setScale(1.0D, 1.0D, 1.0D);
				gen.generate(world, rand, x, world.getHeightValue(x, z), z);
			}
		}
		else {
			super.generateTreesForBiome(world, rand, generatorInfo);
		}
	}
}