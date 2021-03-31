package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderLegacy extends BTASurfaceBuilder {
	private double[] sandNoiseLegacy = new double[256];
	private double[] gravelNoiseLegacy = new double[256];
	private double[] soilDepthNoiseLegacy = new double[256];
	private BTABetaNoiseOctaves sandNoiseGenLegacy;
	private NoiseGeneratorOctaves soilDepthNoiseGenLegacy;

	public BTASurfaceBuilderLegacy() {}

	public BTASurfaceBuilderLegacy(BTABiomeGenBase biome) {
		super(biome);
	}
	
	public void init(Random rand) {
		this.sandNoiseGenLegacy = new BTABetaNoiseOctaves(rand, 4);
		this.soilDepthNoiseGenLegacy = new NoiseGeneratorOctaves(rand, 4);
	}

	public void replaceBlocksForBiome(Random rand, int chunkX, int chunkZ, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;
		double soilDepthNoiseScalar = 0.03125D;
		this.sandNoiseLegacy = this.sandNoiseGenLegacy.generateNoiseOctaves(this.sandNoiseLegacy, (double)(chunkX * 16), (double)(chunkZ * 16), 0.0D, 16, 16, 1, soilDepthNoiseScalar, soilDepthNoiseScalar, 1.0D);
		this.gravelNoiseLegacy = this.sandNoiseGenLegacy.generateNoiseOctaves(this.gravelNoiseLegacy, (double)(chunkX * 16), 109.0134D, (double)(chunkZ * 16), 16, 1, 16, soilDepthNoiseScalar, 1.0D, soilDepthNoiseScalar);
		this.soilDepthNoiseLegacy = this.soilDepthNoiseGenLegacy.generateNoiseOctaves(this.soilDepthNoiseLegacy, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D);

		for (int i = 0; i < 16; ++i) {
			for (int k = 0; k < 16; ++k) {
				BiomeGenBase biome = biomesForGeneration[k + i * 16];

				float temperature = biome.getFloatTemperature();
				boolean useSand = this.sandNoiseLegacy[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
				boolean useGravel = this.gravelNoiseLegacy[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
				int soilDepthNoiseSample = (int)(this.soilDepthNoiseLegacy[i + k * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
				int remaingDepth = -1;
				int topBlock;
				int fillerBlock;

				if (biome instanceof BTABiomeGenBase) {
					topBlock = ((BTABiomeGenBase) biome).topBlockExt;
					fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
				}
				else {
					topBlock = biome.topBlock;
					fillerBlock = biome.fillerBlock;
				}

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
									if(biome.biomeID == BTABiomeConfiguration.oldValley.biomeID || biome.biomeID == BTABiomeConfiguration.valleyMountains.biomeID || biome.biomeID == BTABiomeConfiguration.valley.biomeID || (biome.biomeID == BTABiomeConfiguration.tropics.biomeID && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_2_0))) {
										topBlock = (byte)Block.sand.blockID;
										fillerBlock = (byte)Block.sand.blockID;
									}
									else {
										if (biome instanceof BTABiomeGenBase) {
											topBlock = ((BTABiomeGenBase) biome).topBlockExt;
											fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
										}
										else {
											topBlock = biome.topBlock;
											fillerBlock = biome.fillerBlock;
										}
									}

									if (generatorInfo.generatePerlinBeaches() && BTABiomeConfiguration.shouldBiomeSpawnPerlinBeach(biome.biomeID)) {
										if (useGravel) {
											topBlock = 0;
											fillerBlock = Block.gravel.blockID;
										}

										if (useSand) {
											if (biome == BTABiomeConfiguration.badlands || biome == BTABiomeConfiguration.badlandsPlateau || biome == BTABiomeConfiguration.badlandsEdge || biome == BTABiomeConfiguration.riverBadlands || 
													biome == BTABiomeConfiguration.outback || biome == BTABiomeConfiguration.riverOutback || biome == BTABiomeConfiguration.beachOutback) {
												topBlock = BTADecoIntegration.redSand.blockID;
												fillerBlock = BTADecoIntegration.redSand.blockID;
											}
											else {
												topBlock = Block.sand.blockID;
												fillerBlock = Block.sand.blockID;
											}
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

								if (biome.biomeID == BTABiomeConfiguration.badlandsPlateau.biomeID)
									remaingDepth += 10;

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
	}

}
