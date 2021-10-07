package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderAshFields extends BTASurfaceBuilderNether {
	protected static BTAUtilsOpenSimplexOctaves pumiceNoiseGen;
	protected static BTAUtilsOpenSimplexOctaves pumiceNoiseGen2;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random pumiceRand = new Random(seed + 3000);
		
		pumiceNoiseGen = new BTAUtilsOpenSimplexOctaves(pumiceRand.nextLong(), 2);
		pumiceNoiseGen2 = new BTAUtilsOpenSimplexOctaves(pumiceRand.nextLong(), 2);
	}
	
	@Override
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 32;

		double pumiceNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean usePumice = pumiceNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		boolean usePumice2 = pumiceNoiseGen2.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		
		int soilDepthNoiseSample = (int)(soilDepthNoise[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int remaingDepth = -1;
		int topBlock;
		int fillerBlock;

		topBlock = ((BTABiomeGenBase) biome).topBlockExt;
		fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;

		for (int j = 127; j >= 0; --j) {
			int index = (k * 16 + i) * 128 + j;

			if (j <= 0 + rand.nextInt(5) || j >= 127 - rand.nextInt(5)) {
				blockArray[index] = (byte)Block.bedrock.blockID;
			}
			else {
				int blockID = blockArray[index];

				if (blockID == 0) {
					remaingDepth = -1;
				}
				else if (blockID == Block.netherrack.blockID) {
					if (remaingDepth == -1) {
						if (j >= seaLevel - (8 + rand.nextInt(2))) {
							topBlock = ((BTABiomeGenBase) biome).topBlockExt;
							fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
						}
						else if (j >= seaLevel + 9) {
							topBlock = ((BTABiomeGenBase) biome).topBlockExt;
							fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
						}
						
						if (usePumice) {
							topBlock = BTADecoIntegration.pumice.blockID;
							fillerBlock = BTADecoIntegration.pumice.blockID;
						}

						remaingDepth = soilDepthNoiseSample;

						if (j >= seaLevel - 1) {
							blockArray[index] = topBlock;
						}
						else {
							blockArray[index] = fillerBlock;
						}
					}
					else if (remaingDepth > 0) {
						--remaingDepth;
						blockArray[index] = fillerBlock;
					}
				}
			}
		}
		
		for (int j = 127; j >= 0; --j) {
			int index = (k * 16 + i) * 128 + (127 - j);

			if (j <= 0 + rand.nextInt(5) || j >= 127 - rand.nextInt(5)) {
				blockArray[index] = (byte)Block.bedrock.blockID;
			}
			else {
				int blockID = blockArray[index];

				if (blockID == 0) {
					remaingDepth = -1;
				}
				else if (blockID == Block.netherrack.blockID) {
					if (remaingDepth == -1) {
						if (soilDepthNoiseSample <= 0) {
							topBlock = ((BTABiomeGenBase) biome).topBlockExt;
							fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
						}
						else if (j >= seaLevel - (8 + rand.nextInt(2))) {
							topBlock = ((BTABiomeGenBase) biome).topBlockExt;
							fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
						}
						
						if (usePumice2) {
							topBlock = BTADecoIntegration.pumice.blockID;
							fillerBlock = BTADecoIntegration.pumice.blockID;
						}

						remaingDepth = soilDepthNoiseSample;

						if (j >= seaLevel - 1) {
							blockArray[index] = topBlock;
						}
						else {
							blockArray[index] = fillerBlock;
						}
					}
					else if (remaingDepth > 0) {
						--remaingDepth;
						blockArray[index] = fillerBlock;
					}
				}
			}
		}
	}
}