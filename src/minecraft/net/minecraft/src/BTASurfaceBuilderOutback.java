package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderOutback extends BTASurfaceBuilder {
	protected static double[] grassNoise = new double[1];
	protected static BTABetaNoiseOctaves grassNoiseGen;
	
	@Override
	public void init(Random rand) {
		super.init(rand);
		
		if (grassNoiseGen == null)
			this.grassNoiseGen = new BTABetaNoiseOctaves(rand, 4);
	}
	
	public void replaceBlocksForBiome(Random rand, int x, int z, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;
		double soilDepthNoiseScalar = 0.03125D;
		this.gravelNoise = this.sandNoiseGen.generateNoiseOctaves(this.gravelNoise, x, 109.0134D, z, 1, 1, 1, soilDepthNoiseScalar, 1.0D, soilDepthNoiseScalar);
		this.soilDepthNoise = this.soilDepthNoiseGen.generateNoiseOctaves(this.soilDepthNoise, x, z, 0, 1, 1, 1, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D);
		
		double grassNoiseScalar = 0.5;
		this.grassNoise = this.grassNoiseGen.generateNoiseOctaves(this.grassNoise, x, z, 0.0D, 1, 1, 1, grassNoiseScalar, grassNoiseScalar, 1.0D);

		int i = x & 15;
		int k = z & 15;

		float temperature = biome.getFloatTemperature();
		boolean useGrass = this.sandNoise[0] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = this.gravelNoise[0] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(this.soilDepthNoise[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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
						
						if (useGrass) {
							topBlock = Block.grass.blockID;
							fillerBlock = Block.dirt.blockID;
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