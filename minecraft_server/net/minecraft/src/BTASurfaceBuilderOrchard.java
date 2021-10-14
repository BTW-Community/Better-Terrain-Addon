package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderOrchard extends BTASurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random treeRand = new Random(seed + 1000);

		this.treeNoiseGen = new BTAUtilsOpenSimplexOctaves(treeRand.nextLong(), 2);

		this.treeNoiseScale = 1/256D;
	}

	public void generateTreesForBiome(World world, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		int numTrees = 3;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;

			WorldGenerator gen;

			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .375 > 0) {
				gen = new WorldGenTrees(false, 6, 0, 0, false);
			}
			else {
				if (rand.nextInt(20) == 0) {
					gen = new BTAWorldGenOldOak(false);
				}
				else if (rand.nextInt(2) == 0) {
					gen = new WorldGenBigTree(false);
				}
				else {
					gen = new WorldGenTrees(false, 6, 0, 0, false);
				}
			}

			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}