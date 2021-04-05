package net.minecraft.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BTASurfaceBuilder {
	protected static double[] sandNoise = new double[256];
	protected static double[] gravelNoise = new double[256];
	protected static double[] soilDepthNoise = new double[256];
	protected static BTABetaNoiseOctaves sandNoiseGen;
	protected static NoiseGeneratorOctaves soilDepthNoiseGen;
	
	protected BiomeGenBase biome;
	protected boolean hasBeenInit = false;
	
	protected int chunkX;
	protected int chunkZ;
	
	public static final BTASurfaceBuilder defaultBuilder = new BTASurfaceBuilder();
	public static final BTASurfaceBuilderLegacy defaultBuilderLegacy = new BTASurfaceBuilderLegacy();

	public static void replaceSurface(Random rand, long seed, int chunkX, int chunkZ, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
			if (!defaultBuilderLegacy.hasBeenInit) {
				defaultBuilderLegacy.init(rand, seed);
				defaultBuilderLegacy.hasBeenInit = true;
			}
			
			defaultBuilderLegacy.replaceBlocksForBiome(rand, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo);
		}
		else {
			Set<BiomeGenBase> biomeSet = new HashSet(Arrays.asList(biomesForGeneration));
			
			for (BiomeGenBase b : biomeSet) {
				if (b instanceof BTABiomeGenBase && ((BTABiomeGenBase) b).getSurfaceBuilder() != null) {
					if (!((BTABiomeGenBase) b).getSurfaceBuilder().hasBeenInit) {
						((BTABiomeGenBase) b).getSurfaceBuilder().init(rand, seed);
						((BTABiomeGenBase) b).getSurfaceBuilder().hasBeenInit = true;
					}
					
					((BTABiomeGenBase) b).getSurfaceBuilder().initForChunk(chunkX, chunkZ);
				}
			}
			
			for (int i = 0; i < 16; i++) {
				for (int k = 0; k < 16; k++) {
					BiomeGenBase biome = biomesForGeneration[k + i*16];
					
					if (biome instanceof BTABiomeGenBase && ((BTABiomeGenBase) biome).getSurfaceBuilder() != null) {
						((BTABiomeGenBase) biome).getSurfaceBuilder().replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo);
					}
					else {
						if (!defaultBuilder.hasBeenInit) {
							defaultBuilder.init(rand, seed);
							defaultBuilder.hasBeenInit = true;
						}
						
						defaultBuilder.setBiome(biome);
						defaultBuilder.replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo);
					}
				}
			}
		}
	}

	public void init(Random rand, long seed) {
		if (sandNoiseGen == null) {
			sandNoiseGen = new BTABetaNoiseOctaves(rand, 4);
		}
		
		if (soilDepthNoiseGen == null) {
			soilDepthNoiseGen = new NoiseGeneratorOctaves(rand, 4);
		}
	}
	
	public void initForChunk(int chunkX, int chunkZ) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		
		double surfaceNoiseScalar = 0.03125D;
		this.sandNoise = this.sandNoiseGen.generateNoiseOctaves(this.sandNoise, (double)(chunkX * 16), (double)(chunkZ * 16), 0.0D, 16, 16, 1, surfaceNoiseScalar, surfaceNoiseScalar, 1.0D);
		this.gravelNoise = this.sandNoiseGen.generateNoiseOctaves(this.gravelNoise, (double)(chunkX * 16), 109.0134D, (double)(chunkZ * 16), 16, 1, 16, surfaceNoiseScalar, 1.0D, surfaceNoiseScalar);
		this.soilDepthNoise = this.soilDepthNoiseGen.generateNoiseOctaves(this.soilDepthNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, surfaceNoiseScalar * 2.0D, surfaceNoiseScalar * 2.0D, surfaceNoiseScalar * 2.0D);
	}

	public void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();
		boolean useSand = sandNoise[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
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