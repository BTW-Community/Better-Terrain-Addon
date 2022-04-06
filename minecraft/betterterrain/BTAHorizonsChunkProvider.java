package betterterrain;

import java.util.List;
import java.util.Random;

import betterterrain.biome.BTABiomeConfiguration;
import betterterrain.biome.biomes.BTABiomeGenBase;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.MathHelper;
import net.minecraft.src.SpawnerAnimals;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDungeons;
import net.minecraft.src.WorldGenFlowers;
import net.minecraft.src.WorldGenLakes;

public class BTAHorizonsChunkProvider implements IChunkProvider
{
	/** RNG. */
	private Random rand;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private BTAHorizonsNoiseOctaves biomeHeightNoiseGen;
	private BTAHorizonsNoiseOctaves blockModifierNoiseGen;
	private BTAHorizonsNoiseOctaves blockNoiseGen1;
	private BTAHorizonsNoiseOctaves blockNoiseGen2;
	private BTAHorizonsNoiseOctaves soilDepthNoiseGen;
	
	//Cave noise
	private BTAHorizonsNoiseOctaves caveNoiseGenBase;

	/** Reference to the World object. */
	private World worldObj;

	/** are map structures going to be generated (e.g. strongholds) */
	private final boolean mapFeaturesEnabled;

	/** Holds the overall noise array used in chunk generation */
	private double[] noiseArray;
	private double[] noiseArrayCaves;
	private double[] soilDepthNoise = new double[256];
	
	private BTAMapGenBase caveGenerator = new BTAHorizonsMapGenCave();
	private BTAMapGenBase ravineGenerator = new BTAMapGenRavine();
	private BTAMapGenStronghold strongholdGenerator = new BTAMapGenStronghold();
	private BTAMapGenVillage villageGenerator = new BTAMapGenVillage();
	private BTAMapGenMineshaft mineshaftGenerator = new BTAMapGenMineshaft();
	private BTAMapGenScatteredFeature scatteredFeatureGenerator = new BTAMapGenScatteredFeature();

	/** The biomes that are used to generate the chunk */
	private BiomeGenBase[] biomesForGeneration;

	double[] biomeHeightNoise;
	double[] blockModifierNoise;
	double[] blockNoise1;
	double[] blockNoise2;
	
	double[] caveNoise;

	/**
	 * Used to store the 5x5 parabolic field that is used during terrain generation.
	 */
	float[] parabolicField;
	private Random structureRand;
	
	private int parabolicRadius;
	private int seaLevel;

	public BTAHorizonsChunkProvider(World par1World, long par2, boolean par4) {
		this.worldObj = par1World;
		this.mapFeaturesEnabled = par4;
		this.rand = new Random(par2);
		this.structureRand = new Random(par2);
		this.biomeHeightNoiseGen = new BTAHorizonsNoiseOctaves(this.rand, 16);
		this.blockModifierNoiseGen = new BTAHorizonsNoiseOctaves(this.rand, 8);
		this.blockNoiseGen1 = new BTAHorizonsNoiseOctaves(this.rand, 16);
		this.blockNoiseGen2 = new BTAHorizonsNoiseOctaves(this.rand, 16);
		this.soilDepthNoiseGen = new BTAHorizonsNoiseOctaves(this.rand, 4);
		
		this.caveNoiseGenBase = new BTAHorizonsNoiseOctaves(this.rand, 16);
	}

	/**
	 * Generates the shape of the terrain for the chunk though its all stone though the water is frozen if the
	 * temperature is low enough
	 */
	public void generateTerrain(int chunkX, int chunkZ, int[][][] blockArray) {
		int terrainHeight = 256;

		this.parabolicRadius = 2;
		this.seaLevel = 127;
		
		byte xzResolution = 4;
		byte yResolution = 16;
		int noiseFieldSizeX = xzResolution + 1;
		byte noiseFieldSizeY = 49;
		int noiseFieldSizeZ = xzResolution + 1;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, noiseFieldSizeX + (parabolicRadius * 2 + 1), noiseFieldSizeZ + (parabolicRadius * 2 + 1));
		
		this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * xzResolution, 0, chunkZ * xzResolution, noiseFieldSizeX, noiseFieldSizeY, noiseFieldSizeZ);

		for (int i = 0; i < xzResolution; ++i) {
			for (int k = 0; k < xzResolution; ++k) {
				for (int j = 0; j < yResolution; ++j) {
					//y noise values
					double noiseScalar1 = 0.125D;
					double yn1 = this.noiseArray[((i + 0) * noiseFieldSizeZ + k + 0) * noiseFieldSizeY + j + 0];
					double yn2 = this.noiseArray[((i + 0) * noiseFieldSizeZ + k + 1) * noiseFieldSizeY + j + 0];
					double yn3 = this.noiseArray[((i + 1) * noiseFieldSizeZ + k + 0) * noiseFieldSizeY + j + 0];
					double yn4 = this.noiseArray[((i + 1) * noiseFieldSizeZ + k + 1) * noiseFieldSizeY + j + 0];
					double ymod1 = (this.noiseArray[((i + 0) * noiseFieldSizeZ + k + 0) * noiseFieldSizeY + j + 1] - yn1) * noiseScalar1;
					double ymod2 = (this.noiseArray[((i + 0) * noiseFieldSizeZ + k + 1) * noiseFieldSizeY + j + 1] - yn2) * noiseScalar1;
					double ymod3 = (this.noiseArray[((i + 1) * noiseFieldSizeZ + k + 0) * noiseFieldSizeY + j + 1] - yn3) * noiseScalar1;
					double ymod4 = (this.noiseArray[((i + 1) * noiseFieldSizeZ + k + 1) * noiseFieldSizeY + j + 1] - yn4) * noiseScalar1;

					for (int y = 0; y < (terrainHeight / yResolution); ++y) {
						//x noise values
						double noiseScalar2 = 0.25D;
						double xn1 = yn1;
						double xn2 = yn2;
						double xmod1 = (yn3 - yn1) * noiseScalar2;
						double xmod2 = (yn4 - yn2) * noiseScalar2;

						for (int x = 0; x < (16 / xzResolution); ++x) {
							//z noise values
							double noiseScalar3 = 0.25D;
							double zmod = (xn2 - xn1) * noiseScalar3;
							double zn = xn1 - zmod;

							int index = x + i * 4 << 11 | 0 + k * 4 << 7 | j * 8 + y;
							index -= terrainHeight;

							for (int z = 0; z < (16 / xzResolution); ++z) {
								zn += zmod;
								index += terrainHeight;
								
								if ((zn) > 0.0D) {
									blockArray[i*4+x][k*4+z][j*8+y] = Block.stone.blockID;
								}
								else if (j * 8 + y < seaLevel) {
									blockArray[i*4+x][k*4+z][j*8+y] = Block.waterStill.blockID;
								}
								else {
									blockArray[i*4+x][k*4+z][j*8+y] = 0;
								}
							}

							xn1 += xmod1;
							xn2 += xmod2;
						}

						yn1 += ymod1;
						yn2 += ymod2;
						yn3 += ymod3;
						yn4 += ymod4;
					}
				}
			}
		}
	}
	
	public void generateCaves(int chunkX, int chunkZ, int[][][] blockArray) {
		byte xzResolution = 4;
		byte yResolution = 16;
		int noiseFieldSizeX = xzResolution + 1;
		byte noiseFieldSizeY = 17;
		int noiseFieldSizeZ = xzResolution + 1;
		
		this.noiseArrayCaves = this.initializeCaveNoiseField(this.noiseArrayCaves, chunkX * xzResolution, 0, chunkZ * xzResolution, noiseFieldSizeX, noiseFieldSizeY, noiseFieldSizeZ);
	}

	/**
	 * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
	 * size.
	 */
	private double[] initializeNoiseField(double[] noiseArray, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ) {
		if (noiseArray == null) {
			noiseArray = new double[sizeX * sizeY * sizeZ];
		}

		//Parabolic field is used for smoothing between biomes
		int parabolicDiameter = parabolicRadius * 2 + 1;
		
		if (this.parabolicField == null) {
			this.parabolicField = new float[(int) Math.pow(parabolicDiameter, 2)];

			for (int i = -parabolicRadius; i <= parabolicRadius; ++i) {
				for (int k = -parabolicRadius; k <= parabolicRadius; ++k) {
					float parabolicValue = 10.0F / MathHelper.sqrt_float((float)(i * i + k * k) + 0.2F);
					this.parabolicField[i + parabolicRadius + (k + parabolicRadius) * parabolicDiameter] = parabolicValue;
				}
			}
		}

		double octaveScalarXZ = 684.412D;
		double octaveScalarY = 684.412D;
		//double octaveScalarXZ = 1000D;
		//double octaveScalarY = 1000D;
		this.biomeHeightNoise = this.biomeHeightNoiseGen.generateNoiseOctaves(this.biomeHeightNoise, posX, posZ, sizeX, sizeZ, 200.0D, 200.0D, 0.5D);
		this.blockModifierNoise = this.blockModifierNoiseGen.generateNoiseOctaves(this.blockModifierNoise, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ / 80.0D, octaveScalarY / 160.0D, octaveScalarXZ / 80.0D);
		this.blockNoise1 = this.blockNoiseGen1.generateNoiseOctaves(this.blockNoise1, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		this.blockNoise2 = this.blockNoiseGen2.generateNoiseOctaves(this.blockNoise2, posX, posY, posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		int biomeHeightNoiseIndex = 0;
		int blockNoiseIndex = 0;

		//Iterates through the entire noise array for population
		//Loops first through x and z, getting biome height data, then iterates through y values
		for (int i = 0; i < sizeX; ++i) {
			for (int k = 0; k < sizeZ; ++k) {
				float biomeMaxHeightSample = 0.0F;
				float biomeMinHeightSample = 0.0F;
				float biomeModifierTotal = 0.0F;
				BiomeGenBase biome = this.biomesForGeneration[i + parabolicRadius + (k + parabolicRadius) * (sizeX + parabolicDiameter)];

				//Gets biome height values influenced by neighboring biomes
				//Weights provided by the parabolic field
				for (int c = -parabolicRadius; c <= parabolicRadius; ++c) {
					for (int d = -parabolicRadius; d <= parabolicRadius; ++d) {
						BiomeGenBase biomeNeighbor = this.biomesForGeneration[i + c + parabolicRadius + (k + d + parabolicRadius) * (sizeX + parabolicDiameter)];
						float biomeHeightModifierValue = this.parabolicField[c + parabolicRadius + (d + parabolicRadius) * parabolicDiameter] / (biomeNeighbor.minHeight + 2.0F);

						if (biomeNeighbor.minHeight > biome.minHeight) {
							biomeHeightModifierValue /= 2.0F;
						}

						biomeMaxHeightSample += biomeNeighbor.maxHeight * biomeHeightModifierValue;
						biomeMinHeightSample += biomeNeighbor.minHeight * biomeHeightModifierValue;
						biomeModifierTotal += biomeHeightModifierValue;
					}
				}

				biomeMaxHeightSample /= biomeModifierTotal;
				biomeMinHeightSample /= biomeModifierTotal;
				biomeMaxHeightSample = biomeMaxHeightSample * 0.9F + 0.1F;
				biomeMinHeightSample = (biomeMinHeightSample * 4.0F - 1.0F) / 8.0F;
				
				//Gets the value from the biome noise array to modify the biome height values above
				double biomeHeightNoiseModifier = this.biomeHeightNoise[biomeHeightNoiseIndex] / 8000.0D;
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
					double blockNoiseModifierSample = (this.blockModifierNoise[blockNoiseIndex] / 10.0D + 1.0D) / 2.0D;
					
					double blockNoiseSample1 = this.blockNoise1[blockNoiseIndex] / 512.0D;
					double blockNoiseSample2 = this.blockNoise2[blockNoiseIndex] / 512.0D;
					
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
	
	private double[] initializeCaveNoiseField(double[] noiseArray, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ) {
		if (noiseArray == null) {
			noiseArray = new double[sizeX * sizeY * sizeZ];
		}
		
		double xzScale = 600;
		double yScale = 300;
		
		this.caveNoise = caveNoiseGenBase.generateNoiseOctaves(noiseArray, posX, posY, posZ, sizeX, sizeY, sizeZ, xzScale, yScale, xzScale);
		
		return null;
	}

	/**
	 * Replaces the stone that was placed in with blocks that match the biome
	 */
	public void replaceBlocksForBiome(int chunkX, int chunkZ, int[][][] blockArray, BiomeGenBase[] biomeArray) {
		double stoneNoiseOctaveScalar = 0.03125D;
		this.soilDepthNoise = this.soilDepthNoiseGen.generateNoiseOctaves(this.soilDepthNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, stoneNoiseOctaveScalar * 2.0D, stoneNoiseOctaveScalar * 2.0D, stoneNoiseOctaveScalar * 2.0D);

		for (int i = 0; i < 16; ++i) {
			for (int k = 0; k < 16; ++k) {
				BiomeGenBase biome = biomeArray[i + k * 16];
				
				float biomeTemp = biome.getFloatTemperature();
				int stoneNoiseSample = (int)(this.soilDepthNoise[k + i * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int remainingFillerDepth = -1;
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
					if (j <= 0 + this.rand.nextInt(5)) {
						blockArray[i][k][j] = (byte)Block.bedrock.blockID;
					}
					else {
						int blockID = blockArray[i][k][j];

						if (blockID == 0) {
							remainingFillerDepth = -1;
						}
						else if (blockID == Block.stone.blockID) {
							if (remainingFillerDepth == -1) {
								if (stoneNoiseSample <= 0) {
									topBlock = 0;
									fillerBlock = (byte)Block.stone.blockID;
								}
								else if (j >= seaLevel - 4 && j <= seaLevel + 1) {
									if(biome.biomeID == BTABiomeConfiguration.oldValley.biomeID || biome.biomeID == BTABiomeConfiguration.valleyMountains.biomeID || biome.biomeID == BTABiomeConfiguration.valley.biomeID) {
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
								}
								else if (j >= seaLevel + 9) {
									if(biome.biomeID == BTABiomeConfiguration.badlands.biomeID || biome.biomeID == BTABiomeConfiguration.riverBadlands.biomeID) {
										topBlock = BTADecoIntegration.terracotta.blockID;
										fillerBlock = BTADecoIntegration.terracotta.blockID;
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
								}

								if (j < seaLevel && topBlock == 0) {
									if (biomeTemp < 0.15F) {
										topBlock = (byte)Block.ice.blockID;
									}
									else {
										topBlock = (byte)Block.waterStill.blockID;
									}
								}

								remainingFillerDepth = stoneNoiseSample;
								
								if (biome.biomeID == BTABiomeConfiguration.badlandsPlateau.biomeID)
									remainingFillerDepth += 10;

								if (j >= seaLevel - 1) {
									blockArray[i][k][j] = topBlock;
								}
								else {
									blockArray[i][k][j] = fillerBlock;
								}
							}
							else if (remainingFillerDepth > 0) {
								--remainingFillerDepth;
								blockArray[i][k][j] = fillerBlock;

								if (remainingFillerDepth == 0 && fillerBlock == Block.sand.blockID) {
									remainingFillerDepth = this.rand.nextInt(4);
									fillerBlock = (byte)Block.sandStone.blockID;
								}
								else if (BTADecoIntegration.isDecoInstalled() && remainingFillerDepth == 0 && fillerBlock == BTADecoIntegration.redSand.blockID) {
									remainingFillerDepth = this.rand.nextInt(4);
									fillerBlock = BTADecoIntegration.redSandStone.blockID;
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int par1, int par2) {
		return this.provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	public Chunk provideChunk(int chunkX, int chunkZ) {
		this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
		int[][][] blockArrayBase = new int[16][16][256];
		this.generateTerrain(chunkX, chunkZ, blockArrayBase);
		
		
		/*
		//Raises entire terrain by 64 blocks
		int[][][] blockArray = new int[16][16][256];
		
		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				for (int j = 0; j < 256; j++) {
					if (j < 64) {
						blockArray[i][k][j] = Block.stone.blockID;
					}
					else {
						blockArray[i][k][j] = blockArrayBase[i][k][j - 64];
					}
				}
			}
		}
		*/
		int[][][] blockArray = blockArrayBase;

		this.generateCaves(chunkX, chunkZ, blockArray);
		
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		
		this.replaceBlocksForBiome(chunkX, chunkZ, blockArray, this.biomesForGeneration);
		//this.caveGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		//this.ravineGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);

		if (this.mapFeaturesEnabled) {
			//this.mineshaftGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			//this.villageGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			//this.strongholdGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			//this.scatteredFeatureGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		}

		Chunk chunk = new BTAChunk(this.worldObj, blockArray, chunkX, chunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)this.biomesForGeneration[i].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int chunkX, int chunkZ) {
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
		BlockSand.fallInstantly = true;
		int baseX = chunkX * 16;
		int baseZ = chunkZ * 16;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(baseX + 16, baseZ + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		long var11 = this.rand.nextLong() / 2L * 2L + 1L;
		long var13 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)chunkX * var7 + (long)chunkZ * var9 ^ this.worldObj.getSeed());
		boolean var15 = false;

		if (this.mapFeaturesEnabled) {
			this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
			this.structureRand.setSeed((long)chunkX * var11 + (long)chunkZ * var13 ^ this.worldObj.getSeed());
			var15 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.structureRand, chunkX, chunkZ);
			this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.structureRand, chunkX, chunkZ);
			this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.structureRand, chunkX, chunkZ);
		}

		int i;
		int k;
		int j;

		if (!var15 && this.rand.nextInt(4) == 0) {
			i = baseX + this.rand.nextInt(16) + 8;
			k = this.rand.nextInt(128);
			j = baseZ + this.rand.nextInt(16) + 8;
			(new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, i, k, j);
		}

		if (!var15 && this.rand.nextInt(8) == 0) {
			i = baseX + this.rand.nextInt(16) + 8;
			k = this.rand.nextInt(this.rand.nextInt(120) + 8);
			j = baseZ + this.rand.nextInt(16) + 8;

			if (k < 63 || this.rand.nextInt(10) == 0) {
				(new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, i, k, j);
			}
		}

		for (i = 0; i < 8; ++i) {
			k = baseX + this.rand.nextInt(16) + 8;
			j = this.rand.nextInt(128);
			int var19 = baseZ + this.rand.nextInt(16) + 8;

			if ((new WorldGenDungeons()).generate(this.worldObj, this.rand, k, j, var19)) {
				;
			}
		}

		biome.decorate(this.worldObj, this.rand, baseX, baseZ);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, biome, baseX + 8, baseZ + 8, 16, 16, this.rand);

		baseX += 8;
		baseZ += 8;

		for (i = 0; i < 16; ++i) {
			for (k = 0; k < 16; ++k) {
				j = this.worldObj.getPrecipitationHeight(baseX + i, baseZ + k);

				if (this.worldObj.isBlockFreezable(i + baseX, j - 1, k + baseZ)) {
					this.worldObj.setBlock(i + baseX, j - 1, k + baseZ, Block.ice.blockID, 0, 2);
				}

				if (this.worldObj.canSnowAt(i + baseX, j, k + baseZ)) {
					this.worldObj.setBlock(i + baseX, j, k + baseZ, Block.snow.blockID, 0, 2);
				}
			}
		}

		BlockSand.fallInstantly = false;
		this.BTWPostProcessChunk(this.worldObj, baseX - 8, baseZ - 8);
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
		return true;
	}

	public void func_104112_b() {}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	 */
	public boolean unloadQueuedChunks() {
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	public boolean canSave() {
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	public String makeString() {
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);
		return biome == null ? null : (biome == BiomeGenBase.swampland && creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(x, y, z) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biome.getSpawnableList(creatureType));
	}

	/**
	 * Returns the location of the closest structure of the specified type. If not found returns null.
	 */
	public ChunkPosition findClosestStructure(World world, String structureType, int x, int y, int z) {
		return "Stronghold".equals(structureType) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(world, x, y, z) : null;
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	public void recreateStructures(int chunkX, int chunkZ) {
		if (this.mapFeaturesEnabled) {
			this.mineshaftGenerator.generate(this, this.worldObj, chunkX, chunkZ, (byte[])null);
			this.villageGenerator.generate(this, this.worldObj, chunkX, chunkZ, (byte[])null);
			this.strongholdGenerator.generate(this, this.worldObj, chunkX, chunkZ, (byte[])null);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, chunkX, chunkZ, (byte[])null);
		}
	}

	private void BTWPostProcessChunk(World world, int x, int z) {
		if (world.provider.dimensionId == 0) {
			this.GenerateStrata(world, x, z);
			this.GenerateAdditionalBrownMushrooms(world, x, z);
		}
	}

	private void GenerateAdditionalBrownMushrooms(World world, int x, int z) {
		if (world.rand.nextInt(4) == 0) {
			WorldGenFlowers mushroomGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
			int genX = x + world.rand.nextInt(16) + 8;
			int genY = world.rand.nextInt(25);
			int genZ = z + world.rand.nextInt(16) + 8;
			mushroomGen.generate(world, world.rand, genX, genY, genZ);
		}
	}

	private void GenerateStrata(World world, int x, int z) {
		Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);

		for (int i = 0; i < 16; ++i) {
			for (int k = 0; k < 16; ++k) {
				int j = 0;

				for (int maxY = 48 + world.rand.nextInt(2); j <= maxY; ++j) {
					int blockID = chunk.getBlockID(i, j, k);

					if (blockID == Block.stone.blockID) {
						chunk.setBlockMetadata(i, j, k, 2);
					}
				}

				for (int maxY = 96 + world.rand.nextInt(2); j <= maxY; ++j) {
					int blockID = chunk.getBlockID(i, j, k);

					if (blockID == Block.stone.blockID) {
						chunk.setBlockMetadata(i, j, k, 1);
					}
				}
			}
		}
	}
}
