package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderBadlands extends BTASurfaceBuilder {
	public void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();

		double sandNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useSand = sandNoiseGenSimplex.noise2((this.chunkX * 16 + k) * sandNoiseScale, (this.chunkZ * 16 + i) * sandNoiseScale) + rand.nextDouble() * 0.2D > 0;
		
		boolean useGravel = this.gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(this.soilDepthNoise[i + k * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int remaingDepth = -1;
		int topBlock;
		int fillerBlock;

		topBlock = ((BTABiomeGenBase) biome).topBlockExt;
		fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;

		for (int j = 127; j >= 0; --j) {
			int index = (k * 16 + i) * 128 + j;

			if (j <= 0 + rand.nextInt(5)) {
				blockArray[index] = (byte)Block.bedrock.blockID;
			}
			else {
				int blockID = blockArray[index];

				if (blockID == 0) {
					remaingDepth = -1;
				}
				else if (blockID == Block.stone.blockID) {
					if (remaingDepth == -1) {
						if (soilDepthNoiseSample <= 0) {
							topBlock = 0;
							fillerBlock = (byte)Block.stone.blockID;
						}
						else if (j >= seaLevel - (8 + rand.nextInt(2)) && j <= seaLevel + 1) {
							topBlock = (byte)Block.sand.blockID;
							fillerBlock = (byte)Block.sand.blockID;

							if (generatorInfo.generatePerlinBeaches()) {
								if (useGravel) {
									topBlock = 0;
									fillerBlock = Block.gravel.blockID;
								}

								if (useSand) {
									topBlock = BTADecoIntegration.redSand.blockID;
									fillerBlock = BTADecoIntegration.redSand.blockID;
								}
							}
						}
						else if (j >= seaLevel + 9) {
							if (biome instanceof BTABiomeGenBase) {
								topBlock = ((BTABiomeGenBase) biome).topBlockExt;
								fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
							}
							else {
								topBlock = biome.topBlock;
								fillerBlock = biome.fillerBlock;
							}
						}

						if (j < seaLevel && topBlock == 0) {
							if (temperature < 0.15F) {
								topBlock = (byte)Block.ice.blockID;
							}
							else {
								topBlock = (byte)Block.waterStill.blockID;
							}
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

						if (remaingDepth == 0 && fillerBlock == Block.sand.blockID) {
							remaingDepth = rand.nextInt(4);
							fillerBlock = (byte)Block.sandStone.blockID;
						}
						else if (BTADecoIntegration.isDecoInstalled() && remaingDepth == 0 && fillerBlock == BTADecoIntegration.redSand.blockID) {
							remaingDepth = rand.nextInt(4);
							fillerBlock = BTADecoIntegration.redSandStone.blockID;
						}
					}
				}
			}
		}
	}
}