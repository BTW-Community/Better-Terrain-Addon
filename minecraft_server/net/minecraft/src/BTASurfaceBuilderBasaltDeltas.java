package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderBasaltDeltas extends BTASurfaceBuilderNether {
	protected BTAUtilsOpenSimplexOctaves lavaNoiseGen;
	protected BTAUtilsOpenSimplexOctaves spikeNoiseGen;
	protected BTAUtilsOpenSimplexOctaves spikeHeightNoiseGen;
	protected BTAUtilsOpenSimplexOctaves ashNoiseGen;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random noiseRand = new Random(seed + 2500);
		
		lavaNoiseGen = new BTAUtilsOpenSimplexOctaves(noiseRand.nextLong(), 8);
		spikeNoiseGen = new BTAUtilsOpenSimplexOctaves(noiseRand.nextLong(), 8);
		spikeHeightNoiseGen = new BTAUtilsOpenSimplexOctaves(noiseRand.nextLong(), 8);
		ashNoiseGen = new BTAUtilsOpenSimplexOctaves(noiseRand.nextLong(), 2);
	}
	
	@Override
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 32;

		int soilDepthNoiseSample = (int)(soilDepthNoise[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int remaingDepth = -1;
		int topBlock;
		int fillerBlock;

		topBlock = ((BTABiomeGenBase) biome).topBlockExt;
		fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
		
		int lastBlockId = 1;
		//boolean useSpikes = spikeNoiseGen.noise2(this.chunkX * 16 + k, this.chunkZ * 16 + i, 1/24D) + rand.nextDouble() * 0.2 > 0;
		int spikeHeightBase = (int) ((spikeHeightNoiseGen.noise2(this.chunkX * 16 + k, this.chunkZ * 16 + i, 1/24D)) * 6);
		boolean useSpikes = spikeHeightBase > 0;
		int spikeHeight = spikeHeightBase;

		double ashNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useAsh = ashNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), ashNoiseScale) + rand.nextDouble() * 0.4 > 0.75;

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
						
						if (useAsh && !useSpikes) {
							topBlock = BTADecoIntegration.ash.blockID;
							fillerBlock = BTADecoIntegration.ash.blockID;
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
					
					if ((lastBlockId != 0 && lastBlockId != Block.lavaStill.blockID) && spikeHeight > 0 && useSpikes) {
						blockArray[index] = ((BTABiomeGenBase) biome).fillerBlockExt;
						
						spikeHeight -= rand.nextInt(3);
					}
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
						spikeHeight = spikeHeightBase;
					}
				}
			}
			
			lastBlockId = blockArray[index];
		}
	}
}