package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilder {
	protected static double[] sandNoise = new double[1];
	protected static double[] gravelNoise = new double[1];
	protected static double[] soilDepthNoise = new double[1];
	protected static BTABetaNoiseOctaves sandNoiseGen;
	protected static NoiseGeneratorOctaves soilDepthNoiseGen;
	
	protected BiomeGenBase biome;
	protected boolean hasBeenInit = false;
	
	public static final BTASurfaceBuilder defaultBuilder = new BTASurfaceBuilder();
	public static final BTASurfaceBuilderLegacy defaultBuilderLegacy = new BTASurfaceBuilderLegacy();

	public static void replaceSurface(Random rand, int chunkX, int chunkZ, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		/*
		if (generatorInfo.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_2_1)) {
			if (!defaultBuilderLegacy.hasBeenInit) {
				defaultBuilderLegacy.init(rand);
				defaultBuilderLegacy.hasBeenInit = true;
			}
			
			defaultBuilderLegacy.replaceBlocksForBiome(rand, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo);
		}
		else {
			for (int i = 0; i < 16; i++) {
				for (int k = 0; k < 16; k++) {
					BiomeGenBase biome = biomesForGeneration[k + i*16];
					
					if (biome instanceof BTABiomeGenBase && ((BTABiomeGenBase) biome).getSurfaceBuilder() != null) {
						if (!((BTABiomeGenBase) biome).getSurfaceBuilder().hasBeenInit) {
							((BTABiomeGenBase) biome).getSurfaceBuilder().init(rand);
							((BTABiomeGenBase) biome).getSurfaceBuilder().hasBeenInit = true;
						}
						
						((BTABiomeGenBase) biome).getSurfaceBuilder().replaceBlocksForBiome(rand, chunkX + i, chunkZ + k, blockArray, metaArray, biomesForGeneration, generatorInfo);
					}
					else {
						if (!defaultBuilder.hasBeenInit) {
							defaultBuilder.init(rand);
							defaultBuilder.hasBeenInit = true;
						}
						
						defaultBuilder.setBiome(biome);
						defaultBuilder.replaceBlocksForBiome(rand, chunkX + i, chunkZ + k, blockArray, metaArray, biomesForGeneration, generatorInfo);
					}
				}
			}
		}
		*/
		
		if (!defaultBuilderLegacy.hasBeenInit) {
			defaultBuilderLegacy.init(rand);
			defaultBuilderLegacy.hasBeenInit = true;
		}
		
		defaultBuilderLegacy.replaceBlocksForBiome(rand, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo);
	}

	public void init(Random rand) {
		//if (sandNoiseGen == null)
			sandNoiseGen = new BTABetaNoiseOctaves(rand, 4);
		//if (soilDepthNoise == null)
			soilDepthNoiseGen = new NoiseGeneratorOctaves(rand, 4);
	}

	public void replaceBlocksForBiome(Random rand, int x, int z, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;
		double soilDepthNoiseScalar = 0.03125D;
		sandNoise = sandNoiseGen.generateNoiseOctaves(sandNoise, x, z, 0.0D, 1, 1, 1, soilDepthNoiseScalar, soilDepthNoiseScalar, 1.0D);
		gravelNoise = sandNoiseGen.generateNoiseOctaves(gravelNoise, x, 109.0134D, z, 1, 1, 1, soilDepthNoiseScalar, 1.0D, soilDepthNoiseScalar);
		soilDepthNoise = soilDepthNoiseGen.generateNoiseOctaves(soilDepthNoise, x, z, 0, 1, 1, 1, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D);

		int i = x & 15;
		int k = z & 15;

		float temperature = biome.getFloatTemperature();
		boolean useSand = sandNoise[0] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = gravelNoise[0] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(soilDepthNoise[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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
							if (biome instanceof BTABiomeGenBase) {
								topBlock = ((BTABiomeGenBase) biome).topBlockExt;
								fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
							}
							else {
								topBlock = biome.topBlock;
								fillerBlock = biome.fillerBlock;
							}

							if (generatorInfo.generatePerlinBeaches()) {
								if (useGravel) {
									topBlock = 0;
									fillerBlock = Block.gravel.blockID;
								}

								if (useSand) {
									topBlock = Block.sand.blockID;
									fillerBlock = Block.sand.blockID;
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

	public BiomeGenBase getBiome() {
		return biome;
	}

	public void setBiome(BiomeGenBase biome) {
		this.biome = biome;
	}
}