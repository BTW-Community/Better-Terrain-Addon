package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderAlpine extends BTASurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random treeRand = new Random(seed + 2000);
		
		if (this.treeNoiseGen == null);
			this.treeNoiseGen = new BTAOpenSimplexOctavesFast(treeRand.nextLong(), 2);
	}
	
	public void generateTreesForBiome(World world, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		int numTrees = 20;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;
			
			WorldGenerator gen;
			
			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .25 > 0) {
				if (rand.nextInt(5) == 0) {
					gen = new BTAWorldGenTaiga5(false);
				}
				else {
					gen = new BTAWorldGenPineTree(false, 2, 2);
				}
			}
			else {
				if (rand.nextInt(3) == 0) {
					gen = new BTAWorldGenTaiga5(false);
				}
				else {
					gen = new WorldGenTaiga2(false);
				}
			}
			
			if (world.getHeightValue(x, z) > 85 && rand.nextInt(3) == 0)
				continue;
			if (world.getHeightValue(x, z) > 100 && rand.nextInt(2) == 0)
				continue;
			
			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}