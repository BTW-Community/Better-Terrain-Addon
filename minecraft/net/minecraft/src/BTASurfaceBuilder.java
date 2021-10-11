package net.minecraft.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.src.opensimplex2.OpenSimplex2F;

public class BTASurfaceBuilder {
	protected static NoiseGeneratorOctaves blockModifierNoiseGen;
	protected static NoiseGeneratorOctaves blockNoiseGen1;
	protected static NoiseGeneratorOctaves blockNoiseGen2;
	protected static NoiseGeneratorOctaves biomeHeightNoiseGen;

	protected static double[] blockModifierNoise;
	protected static double[] blockNoise1;
	protected static double[] blockNoise2;
	protected static double[] biomeHeightNoise;

	protected static int parabolicRadius = 2;
	protected static float[] parabolicField;

	protected static double[] gravelNoise = new double[256];
	protected static double[] soilDepthNoiseLegacy = new double[256];
	protected static NoiseGeneratorOctaves soilDepthNoiseGenLegacy;

	protected static BTAUtilsOpenSimplexOctaves sandNoiseGenSimplex;
	protected static BTAUtilsOpenSimplexOctaves soilDepthNoiseGenSimplex;
	protected static BTAUtilsOpenSimplexOctaves gravelNoiseGenSimplex;

	protected BiomeGenBase biome;
	protected boolean hasBeenInit = false;

	protected int chunkX;
	protected int chunkZ;

	protected double treeNoiseScale = 1/64D;
	protected BTAUtilsOpenSimplexOctaves treeNoiseGen;

	protected boolean useBidirectionalSurfacing = false;

	public static final BTASurfaceBuilder defaultBuilder = new BTASurfaceBuilder();
	public static final BTASurfaceBuilderLegacy legacyBuilder = new BTASurfaceBuilderLegacy();

	//New 3D array format
	public static void replaceSurface(Random rand, long seed, int chunkX, int chunkZ, int[][][] blockArray, int[][][] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
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

		if (!defaultBuilder.hasBeenInit) {
			defaultBuilder.init(rand, seed);
			defaultBuilder.hasBeenInit = true;
		}

		defaultBuilder.initForChunk(chunkX, chunkZ);

		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				BiomeGenBase biome = biomesForGeneration[k + i*16];

				if (biome instanceof BTABiomeGenBase && ((BTABiomeGenBase) biome).getSurfaceBuilder() != null) {
					((BTABiomeGenBase) biome).getSurfaceBuilder().replaceBlocksForBiome(rand, k, i, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType);
				}
				else {
					defaultBuilder.setBiome(biome);
					defaultBuilder.replaceBlocksForBiome(rand, k, i, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType);
				}
			}
		}
	}

	//Old 1D array format
	public static void replaceSurface(Random rand, long seed, int chunkX, int chunkZ, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		if (generatorInfo.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
			if (!legacyBuilder.hasBeenInit) {
				legacyBuilder.init(rand, seed);
				legacyBuilder.hasBeenInit = true;
			}

			legacyBuilder.replaceBlocksForBiome(rand, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo);
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

			if (!defaultBuilder.hasBeenInit) {
				defaultBuilder.init(rand, seed);
				defaultBuilder.hasBeenInit = true;
			}

			defaultBuilder.initForChunk(chunkX, chunkZ);

			for (int i = 0; i < 16; i++) {
				for (int k = 0; k < 16; k++) {
					BiomeGenBase biome = biomesForGeneration[k + i*16];

					if (biome instanceof BTABiomeGenBase && ((BTABiomeGenBase) biome).getSurfaceBuilder() != null) {
						((BTABiomeGenBase) biome).getSurfaceBuilder().replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType);
					}
					else {
						defaultBuilder.setBiome(biome);
						defaultBuilder.replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo);
					}
				}
			}
		}
	}

	public static void generateTrees(World world, Random rand, BTAWorldConfigurationInfo generatorInfo, int chunkX, int chunkZ, BTABiomeGenBase biome) {
		BTASurfaceBuilder builder = biome.getSurfaceBuilder();

		if (generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_4_0)) {
			if (builder != null) {
				builder.initForChunk(chunkX, chunkZ);
				builder.generateTreesForBiome(world, rand, generatorInfo);
			}
			else {
				defaultBuilder.setBiome(biome);
				defaultBuilder.initForChunk(chunkX, chunkZ);
				defaultBuilder.generateTreesForBiome(world, rand, generatorInfo);
			}
		}
		else {
			legacyBuilder.generateTreesForBiome(world, rand, chunkX, chunkZ, biome, generatorInfo);
		}
	}

	public static void initForNoiseField(long seed) {
		Random rand = new Random(seed);

		blockNoiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		blockNoiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		blockModifierNoiseGen = new NoiseGeneratorOctaves(rand, 8);
		soilDepthNoiseGenLegacy = new NoiseGeneratorOctaves(rand, 4);
		biomeHeightNoiseGen = new NoiseGeneratorOctaves(rand, 16);
	}

	public static double[] initializeNoiseField(Random rand, long seed, double[] noiseArray, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ, BiomeGenBase[] biomesForGeneration) {
		if (noiseArray == null) {
			noiseArray = new double[sizeX * sizeY * sizeZ];
		}

		//Parabolic field is used for smoothing between biomes
		int parabolicDiameter = parabolicRadius * 2 + 1;

		if (parabolicField == null) {
			parabolicField = new float[(int) Math.pow(parabolicDiameter, 2)];

			for (int i = -parabolicRadius; i <= parabolicRadius; ++i) {
				for (int k = -parabolicRadius; k <= parabolicRadius; ++k) {
					float parabolicValue = 10.0F / MathHelper.sqrt_float((float)(i * i + k * k) + 0.2F);
					parabolicField[i + parabolicRadius + (k + parabolicRadius) * parabolicDiameter] = parabolicValue;
				}
			}
		}

		double octaveScalarXZ = 684.412D;
		double octaveScalarY = 684.412D;
		biomeHeightNoise = biomeHeightNoiseGen.generateNoiseOctaves(biomeHeightNoise, posX, posZ, sizeX, sizeZ, 200.0D, 200.0D, 0.5D);
		blockModifierNoise = blockModifierNoiseGen.generateNoiseOctaves(blockModifierNoise, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ / 80.0D, octaveScalarY / 160.0D, octaveScalarXZ / 80.0D);
		blockNoise1 = blockNoiseGen1.generateNoiseOctaves(blockNoise1, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		blockNoise2 = blockNoiseGen2.generateNoiseOctaves(blockNoise2, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		int biomeHeightNoiseIndex = 0;
		int blockNoiseIndex = 0;

		//Iterates through the entire noise array for population
		//Loops first through x and z, getting biome height data, then iterates through y values
		for (int i = 0; i < sizeX; ++i) {
			for (int k = 0; k < sizeZ; ++k) {
				float biomeMaxHeightSample = 0.0F;
				float biomeMinHeightSample = 0.0F;
				float biomeModifierTotal = 0.0F;
				BiomeGenBase biomeForNoise = biomesForGeneration[i + parabolicRadius + (k + parabolicRadius) * (sizeX + parabolicDiameter)];

				//Gets biome height values influenced by neighboring biomes
				//Weights provided by the parabolic field
				for (int c = -parabolicRadius; c <= parabolicRadius; ++c) {
					for (int d = -parabolicRadius; d <= parabolicRadius; ++d) {
						BiomeGenBase biomeNeighbor = biomesForGeneration[i + c + parabolicRadius + (k + d + parabolicRadius) * (sizeX + parabolicDiameter)];
						float biomeHeightModifierValue = parabolicField[c + parabolicRadius + (d + parabolicRadius) * parabolicDiameter] / (biomeNeighbor.minHeight + 2.0F);

						if (biomeNeighbor.minHeight > biomeForNoise.minHeight) {
							biomeHeightModifierValue /= 2.0F;
						}

						if (biomeNeighbor instanceof BTABiomeGenBase && ((BTABiomeGenBase) biomeNeighbor).isPlateau()) {
							biomeMaxHeightSample += 0.4 * biomeHeightModifierValue;
							biomeMinHeightSample += 0.8 * biomeHeightModifierValue;
						}
						else {
							biomeMaxHeightSample += biomeNeighbor.maxHeight * biomeHeightModifierValue;
							biomeMinHeightSample += biomeNeighbor.minHeight * biomeHeightModifierValue;
						}
						biomeModifierTotal += biomeHeightModifierValue;
					}
				}

				//Gets the value from the biome noise array to modify the biome height values above
				double biomeHeightNoiseModifier = biomeHeightNoise[biomeHeightNoiseIndex] / 8000.0D;
				biomeHeightNoiseIndex++;

				if (biomeHeightNoiseModifier < 0.0D) {
					biomeHeightNoiseModifier = -biomeHeightNoiseModifier * 0.3D;
				}

				biomeHeightNoiseModifier = biomeHeightNoiseModifier * 3.0D - 2.0D;

				if (biomeHeightNoiseModifier < 0.0D) {
					biomeHeightNoiseModifier /= 2.0D;

					if (biomeHeightNoiseModifier < -1.0D){
						biomeHeightNoiseModifier = -1.0D;
					}

					biomeHeightNoiseModifier /= 2.8D;
				}
				else {
					if (biomeHeightNoiseModifier > 1.0D) {
						biomeHeightNoiseModifier = 1.0D;
					}

					biomeHeightNoiseModifier /= 8.0D;
				}

				biomeMaxHeightSample /= biomeModifierTotal;
				biomeMinHeightSample /= biomeModifierTotal;
				biomeMaxHeightSample = biomeMaxHeightSample * 0.9F + 0.1F;

				if (biomeForNoise instanceof BTABiomeGenBase && ((BTABiomeGenBase) biomeForNoise).isPlateau()) {
					biomeMinHeightSample = biomeMinHeightSample * 1.5F - 0.5F;
				}
				else {
					biomeMinHeightSample = (biomeMinHeightSample * 4.0F - 1.0F) / 8.0F;
				}

				//After biome height information has been processed, iterates through y values
				for (int j = 0; j < sizeY; ++j) {
					//Modifies biome height for x/z based on the current y value
					double biomeMinHeightDouble = (double) biomeMinHeightSample;
					double biomeMaxHeightDouble = (double) biomeMaxHeightSample;
					biomeMinHeightDouble += biomeHeightNoiseModifier * 0.2D;
					biomeMinHeightDouble = biomeMinHeightDouble * (double) sizeY / 16.0D;
					double biomeMinHeightModified = (double) sizeY / 2.0D + biomeMinHeightDouble * 4.0D;
					double noiseSample = 0.0D;
					double biomeHeightValue = ((double) j - biomeMinHeightModified) * 12.0D / biomeMaxHeightDouble;

					if (biomeHeightValue < 0.0D) {
						biomeHeightValue *= 4.0D;
					}

					//Samples the noise fields at the current location
					double blockNoiseModifierSample = (blockModifierNoise[blockNoiseIndex] / 10.0D + 1.0D) / 2.0D;

					double blockNoiseSample1 = blockNoise1[blockNoiseIndex] / 512.0D;
					double blockNoiseSample2 = blockNoise2[blockNoiseIndex] / 512.0D;

					//Combines block noise fields depending on the value of the modifier sample
					if (blockNoiseModifierSample < 0.0D) {
						noiseSample = blockNoiseSample1;
					}
					else if (blockNoiseModifierSample > 1.0D) {
						noiseSample = blockNoiseSample2;
					}
					else {
						noiseSample = blockNoiseSample1 + (blockNoiseSample2 - blockNoiseSample1) * blockNoiseModifierSample;
					}

					//Factors biome height calculations into final noise values
					noiseSample -= biomeHeightValue;

					//Modifies noise at the top of the section
					if (j > sizeY - 4) {
						double sectionHeightModifier = (double)((float)(j - (sizeY - 4)) / 3.0F);
						noiseSample = noiseSample * (1.0D - sectionHeightModifier) + -10.0D * sectionHeightModifier;
					}

					noiseArray[blockNoiseIndex] = noiseSample;

					blockNoiseIndex++;
				}
			}
		}

		return noiseArray;
	}

	protected void init(Random rand, long seed) {
		Random sandRand = new Random(seed - 1000);

		sandNoiseGenSimplex = new BTAUtilsOpenSimplexOctaves(sandRand.nextLong(), 8);
		gravelNoiseGenSimplex = new BTAUtilsOpenSimplexOctaves(sandRand.nextLong(), 8);
	}

	protected void initForChunk(int chunkX, int chunkZ) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;

		double surfaceNoiseScalar = 0.03125D;
		this.soilDepthNoiseLegacy = this.soilDepthNoiseGenLegacy.generateNoiseOctaves(this.soilDepthNoiseLegacy, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, surfaceNoiseScalar * 2.0D, surfaceNoiseScalar * 2.0D, surfaceNoiseScalar * 2.0D);
	}

	//New 3D array format
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[][][] blockArray, int[][][] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		byte seaLevel = 100;

		float temperature = biome.getFloatTemperature();

		double sandNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useSand = sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), sandNoiseScale) + rand.nextDouble() * 0.2D > 0;

		//boolean useSand = sandNoise[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(soilDepthNoiseLegacy[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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

		for (int j = 255; j >= 0; --j) {
			if (j <= 0 + rand.nextInt(5)) {
				blockArray[i][k][j] = (byte)Block.bedrock.blockID;
			}
			else {
				int blockID = blockArray[i][k][j];

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

						if (j >= seaLevel - 1) {
							blockArray[i][k][j] = topBlock;
						}
						else {
							blockArray[i][k][j] = fillerBlock;
						}
					}
					else if (remaingDepth > 0) {
						--remaingDepth;
						blockArray[i][k][j] = fillerBlock;

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

	//Old 1D array format
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		this.replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo);
	}

	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();

		double sandNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useSand = sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), sandNoiseScale) + rand.nextDouble() * 0.2D > 0;

		//boolean useSand = sandNoise[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(soilDepthNoiseLegacy[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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

	protected void generateTreesForBiome(World world, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		int numTrees;

		if (biome instanceof BTABiomeGenBase) {
			numTrees = ((BTABiomeGenBase) biome).btaBiomeDecorator.treesPerChunk;

			if (rand.nextInt(((BTABiomeGenBase) biome).btaBiomeDecorator.fractionalTreeChance) == 0)
				numTrees++;
		}
		else {
			numTrees = biome.theBiomeDecorator.treesPerChunk;

			if (rand.nextInt(10) == 0)
				numTrees++;
		}

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;

			WorldGenerator gen;

			if (biome instanceof BTABiomeGenBase) {
				gen = ((BTABiomeGenBase) this.biome).getRandomWorldGenForTrees(rand, generatorInfo, world.provider.terrainType);
			}
			else {
				gen = this.biome.getRandomWorldGenForTrees(rand);
			}

			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}

	public BiomeGenBase getBiome() {
		return biome;
	}

	public void setBiome(BiomeGenBase biome) {
		this.biome = biome;
	}

	/**
	 * Used to set the value on an array at the index provided for the given coordinates
	 * Can be used to set block id or metadata depending on which array is passed to it
	 * @param blockArray A 1D or 3D int array to store the data
	 * @param i Local x value for this chunk
	 * @param y Absolute y value
	 * @param k Local z value for this chunk
	 * @param id Block id or metadata to set
	 */
	protected void setBlockValue(Object blockArray, int i, int y, int k, int id) {
		if (blockArray instanceof int[]) {
			((int[]) blockArray)[(i * 16 + k) * 128 + y] = id;
		}
		else if (blockArray instanceof int[][][]) {
			((int[][][]) blockArray)[i][k][y] = id;
		}
		else {
			throw new IllegalArgumentException("blockArray must be of type int[] or int[][][]");
		}
	}

	/**
	 * Gets the block to use for the surface layer for this biome
	 * @param i Local x value for this chunk
	 * @param y Absolute y value
	 * @param k Local z value for this chunk
	 * @param isTopBlock Whether to return the top block or the filler block
	 * @param rand
	 * @param generatorInfo
	 * @return A Tuple of blockID, metadata
	 */
	protected Tuple getSurfaceBlock(int i, int j, int k, SurfaceType surfaceType, int seaLevel, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		int blockID = -1;
		int metadata = 0;

		if (generatorInfo.generatePerlinBeaches() && 
				j >= seaLevel - (8 + rand.nextInt(2)) && 
				j <= seaLevel + 1) {
			if (useGravelAtLocation(i, k, rand)) {
				switch (surfaceType) {
				case TOP:
					blockID = 0;
					break;
				case FILLER:
					blockID = Block.gravel.blockID;
					break;
				case SUBFILLER:
					blockID = Block.stone.blockID;
				}
			}
			else if (useSandAtLocation(i, k, rand)) {
				if (surfaceType == SurfaceType.SUBFILLER) {
					blockID = Block.sandStone.blockID;
				}
				else {
					blockID = Block.sand.blockID;
				}
			}
		}

		if (blockID == -1) {
			if (j < seaLevel && surfaceType == surfaceType.TOP) {
				surfaceType = SurfaceType.FILLER;
			}

			blockID = getDefaultSurfaceBlock(i, blockID, surfaceType);
		}

		return new Tuple(blockID, metadata);
	}

	protected int getDefaultSurfaceBlock(int i, int k, SurfaceType surfaceType) {
		if (this.biome instanceof BTABiomeGenBase) {
			switch (surfaceType) {
			case TOP:
				return ((BTABiomeGenBase) this.biome).topBlockExt;
			case FILLER:
				return ((BTABiomeGenBase) this.biome).fillerBlockExt;
			case SUBFILLER:
				if (((BTABiomeGenBase) this.biome).topBlockExt == Block.sand.blockID) {
					return Block.sandStone.blockID;
				}
				else if (BTADecoIntegration.isDecoInstalled()) {
					if (((BTABiomeGenBase) this.biome).topBlockExt == BTADecoIntegration.redSand.blockID) {
						return BTADecoIntegration.redSandStone.blockID;
					}
				}
				else {
					return Block.stone.blockID;
				}
			}
		}
		else {
			switch (surfaceType) {
			case TOP:
				return this.biome.topBlock;
			case FILLER:
				return this.biome.fillerBlock;
			case SUBFILLER:
				if (this.biome.topBlock == Block.sand.blockID) {
					return Block.sandStone.blockID;
				}
				else {
					return Block.stone.blockID;
				}
			}
		}
		
		return 0;
	}

	protected boolean useSandAtLocation(int i, int k, Random rand) {
		double beachNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		return sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), beachNoiseScale) + rand.nextDouble() * 0.2D > 0;
	}

	protected boolean useGravelAtLocation(int i, int k, Random rand) {
		double beachNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		return gravelNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), beachNoiseScale) > 0.75;
	}

	protected int getSoilDepth(int i, int k, BTAWorldConfigurationInfo generatorInfo) {
		return 4;
	}

	public enum SurfaceType {
		TOP,
		FILLER,
		SUBFILLER
	}
}