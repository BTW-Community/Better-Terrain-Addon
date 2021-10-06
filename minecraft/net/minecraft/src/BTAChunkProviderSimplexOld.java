package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAChunkProviderSimplexOld implements IChunkProvider
{
	/** RNG. */
	private Random rand;

	private BTAUtilsOpenSimplexOctaves blockNoiseGen;
	private BTAUtilsOpenSimplexOctaves blockNoiseGen2;

	private BTAUtilsFastNoiseOctaves blockNoiseGenPerlin;
	
	private BTAUtilsOpenSimplexOctaves blockNoiseSwitcherGen;
	private BTAUtilsOpenSimplexOctaves blockThresholdNoiseGen;
	
	private BTAUtilsOpenSimplexOctaves heightMapNoiseGen;
	private BTAUtilsOpenSimplexOctaves depthMapNoiseGen;
	
	private BTAUtilsOpenSimplexOctaves riverNoiseGen;
	private BTAUtilsOpenSimplexOctaves riverNoiseGen2;
	
	private BTAUtilsOpenSimplexOctaves cheeseCaveNoiseGen;
	private BTAUtilsOpenSimplexOctaves spaghettiCaveNoiseGen;
	private BTAUtilsOpenSimplexOctaves spaghettiCaveNoiseGen2;
	
	private BTAUtilsOpenSimplexOctaves caveHollownessNoiseGen;
	private BTAUtilsOpenSimplexOctaves caveFrequencyNoiseGen;
	private BTAUtilsOpenSimplexOctaves caveFlatnessNoiseGen;

	private NoiseGeneratorOctaves soilDepthNoiseGen;

	/** Reference to the World object. */
	private World worldObj;

	/** are map structures going to be generated (e.g. strongholds) */
	private final boolean mapFeaturesEnabled;

	private BTAMapGenBase caveGenerator = new BTAMapGenCave();

	/** Holds Stronghold Generator */
	private BTAMapGenStronghold strongholdGenerator = new BTAMapGenStronghold();

	/** Holds Village Generator */
	public BTAMapGenVillage villageGenerator = new BTAMapGenVillage();

	/** Holds Mineshaft Generator */
	private BTAMapGenMineshaft mineshaftGenerator = new BTAMapGenMineshaft();
	private BTAMapGenScatteredFeature scatteredFeatureGenerator = new BTAMapGenScatteredFeature();

	/** Holds ravine generator */
	private BTAMapGenBase ravineGenerator = new BTAMapGenRavine();

	/** The biomes that are used to generate the chunk */
	private BiomeGenBase[] biomesForGeneration;

	/**
	 * Used to store the 5x5 parabolic field that is used during terrain generation.
	 */
	float[] parabolicField;
	private Random m_structureRand;

	public BTAWorldConfigurationInfo generatorInfo;
	private long seed;
	
	private double[][][] gaussianKernel;

	public BTAChunkProviderSimplexOld(World world, long seed, boolean mapFeaturesEnabled, BTAWorldConfigurationInfo generatorInfo)
	{
		this.worldObj = world;
		this.mapFeaturesEnabled = mapFeaturesEnabled;
		this.rand = new Random(seed);
		this.m_structureRand = new Random(seed);
		this.generatorInfo = generatorInfo;
		
		long blockSeed = rand.nextLong();
		this.blockNoiseGen = new BTAUtilsOpenSimplexOctaves(blockSeed, 5);
		this.blockNoiseGen2 = new BTAUtilsOpenSimplexOctaves(-blockSeed, 5);

		this.blockNoiseGenPerlin = new BTAUtilsFastNoiseOctaves(blockSeed, 7);
		
		this.blockNoiseSwitcherGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 1);
		this.blockThresholdNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 1);

		long heightMapSeed = rand.nextLong();
		this.heightMapNoiseGen = new BTAUtilsOpenSimplexOctaves(heightMapSeed, 1);
		this.depthMapNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 1);
		
		this.riverNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 7);
		this.riverNoiseGen2 = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 7);
		
		this.cheeseCaveNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 4);
		this.spaghettiCaveNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 5);
		this.spaghettiCaveNoiseGen2 = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 5);

		this.caveHollownessNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 1);
		this.caveFrequencyNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 1);
		this.caveFlatnessNoiseGen = new BTAUtilsOpenSimplexOctaves(rand.nextLong(), 1);
		
		this.seed = seed;

		BTASurfaceBuilder.initForNoiseField(this.seed);
	}

	/**
	 * Generates the shape of the terrain for the chunk using only stone, though air below sea level gets filled with water.
	 */
	public void generateTerrain(int chunkX, int chunkZ, int[][][] blockArray) {
		int seaLevel = 100;
		
		int maxHeight;
		int minHeight;
		
		double blockNoiseScaleXZ;
		double blockNoiseScaleY;
		
		double blockSwitchScale;

		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				int x = chunkX * 16 + i;
				int z = chunkZ * 16 + k;
				
				//maxHeight = 216;
				maxHeight = getMaxTerrainHeight(x, z);
				minHeight = getMinTerrainHeight(x, z);
				
				int jStop = Math.max(maxHeight, seaLevel);
				//int jStop = maxHeight;
				
				for (int j = 0; j <= jStop; j++) {
					//Biases the generation based on y level.
					//Below min height is guaranteed to be filled, above max height is guaranteed to be air.
					//Between those is a weighting based on height.
					double noise = 0;
					
					double noiseThresholdBase = 0;
					double noiseThresholdTop = -0.125;
					double noiseThreshold = noiseThresholdBase;
					
					if (j > maxHeight)
						noise = -1;
					else if (j < minHeight)
						noise = 1;
					else {
						blockNoiseScaleXZ = 1/384D;
						blockNoiseScaleY = 1/384D;
						
						double blockNoise = blockNoiseGen.noise3(x, j, z, blockNoiseScaleXZ, blockNoiseScaleY);
						
						/*
						double blockNoise2 = blockNoiseGen2.noise3(x, j, z, blockNoiseScaleXZ, blockNoiseScaleY);
						
						blockSwitchScale = 1/512D;
						
						double blockNoiseSwitch = blockNoiseSwitcherGen.noise2(x, z, blockSwitchScale);
						
						double switchModified = blockNoiseSwitch / 2 + 0.5;
						double blockNoise = blockNoise + (blockNoise2 - blockNoise) * (switchModified);
						*/
						
						noise = biasNoiseForHeight(blockNoise, j, seaLevel, maxHeight, minHeight);
						/*
						noise = generateNoiseWithConvolution(
								x, j, z,
								blockNoiseGen, blockNoiseScaleXZ, blockNoiseScaleY,
								seaLevel, maxHeight, minHeight,
								1
						);
						*/
						
					}

					if (noise > noiseThreshold) {
						blockArray[i][k][j] = Block.stone.blockID;
					}
					else if (j == getMaxTerrainHeight(x, z)) { //Debug
						blockArray[i][k][j] = Block.glass.blockID;
					}
					else if (j <= seaLevel) {
						blockArray[i][k][j] = Block.waterStill.blockID;
					}
				}
			}
		}
	}
	
	/**
	 * Generates block noise and convolves it with a gaussian kernel to smooth the result
	 * @param x
	 * @param y
	 * @param z
	 * @param noiseGen
	 * @param noiseScaleXZ
	 * @param noiseScaleY
	 * @param gaussianRadius
	 * @return
	 */
	private double generateNoiseWithConvolution(
			int x, int y, int z,
			BTAUtilsOpenSimplexOctaves noiseGen, double noiseScaleXZ, double noiseScaleY,
			int seaLevel, int maxHeight, int minHeight,
			int gaussianRadius) {
		
		int gaussianSize = 2 * gaussianRadius - 1;
		
		//Caches kernel to avoid regenerating it on every call
		//since this will be called thousands of times
		if (gaussianKernel == null || gaussianSize != gaussianKernel.length) {
			gaussianKernel = new double[gaussianSize][gaussianSize][gaussianSize];
			
			for (int i = -gaussianRadius + 1; i < gaussianRadius; i++) {
				for (int j = -gaussianRadius + 1; j < gaussianRadius; j++) {
					for (int k = -gaussianRadius + 1; k < gaussianRadius; k++) {
						double normalizationConstant = 1/(Math.pow(gaussianSize, 3) * Math.pow(2 * Math.PI, 3 / 2D));
						double gaussian = normalizationConstant * Math.exp(-(i*i + j*j + k*k) / (2 * Math.pow(gaussianSize, 2)));
						
						gaussianKernel[i + gaussianRadius - 1]
									  [j + gaussianRadius - 1]
									  [k + gaussianRadius - 1] = gaussian;
					}
				}
			}
			
			/*
			for (int i = 0; i < gaussianSize; i++) {
				System.out.print("[");
				
				for (int j = 0; j < gaussianSize; j++) {
					System.out.print(gaussianKernel[gaussianRadius - 1][i][j] + ", ");
				}
				
				System.out.print("]\n");
			}
			*/
			
			double total = 0;

			for (int i = 0; i < gaussianSize; i++) {
				for (int j = 0; j < gaussianSize; j++) {
					for (int k = 0; k < gaussianSize; k++) {
						total += gaussianKernel[i][j][k];
					}
				}
			}
			
			System.out.println(total);
		}
		
		double noiseTotal = 0;

		for (int i = 0; i < gaussianSize; i++) {
			for (int j = 0; j < gaussianSize; j++) {
				for (int k = 0; k < gaussianSize; k++) {
					double noise = noiseGen.noise3(
							x + i - gaussianRadius + 1,
							y + j - gaussianRadius + 1,
							z + k - gaussianRadius + 1,
							noiseScaleXZ, noiseScaleY
					);
					
					noise = biasNoiseForHeight(noise, y, seaLevel, maxHeight, minHeight);
					
					noiseTotal += noise * gaussianKernel[i][j][k];
				}
			}
		}
		
		return noiseTotal;
	}
	
	public double biasNoiseForHeight(double noise, int height, int seaLevel, int maxHeight, int minHeight) {
		double noiseModifier;
		
		int heightDivideLevel = seaLevel + 0;
		int heightSmoothingLevel = heightDivideLevel + 10;
		
		if (height >= heightDivideLevel) {
			noiseModifier = (height - heightDivideLevel) / (double) (maxHeight - heightDivideLevel);
			
			/*
			double thresholdScale = 1/256D;
			double noiseThresholdModifer = blockThresholdNoiseGen.noise2(x, z, thresholdScale);
			noiseThresholdModifer = (noiseThresholdModifer + 1) / 2;
			noiseThresholdModifer = Math.pow(noiseThresholdModifer, 3);
			noiseThresholdModifer = noiseThresholdModifer * 2 - 1;
			
			noiseThreshold = noiseThresholdTop * noiseThresholdModifer * (j - heightDivideLevel) / (double) (maxHeight - heightDivideLevel);
			
			float noiseSmoothFactor = ((j - heightDivideLevel) / (float) (heightSmoothingLevel - heightDivideLevel));
			noiseSmoothFactor = MathHelper.clamp_float(noiseSmoothFactor, 0, 1);
			noiseSmoothFactor = (float) Math.pow(noiseSmoothFactor, 2);
			//noiseModifier = noiseModifier + Math.abs(Math.pow(noiseModifier, 2) - noiseModifier) * noiseSmoothFactor;
			noiseModifier = Math.pow(noiseModifier, 1/2D);
			*/
			
			noiseModifier = noiseModifier * (0.75) + 0.3;
			
		}
		else {
			noiseModifier = (height - minHeight) / (double) (heightDivideLevel - minHeight);
			noiseModifier = Math.pow(noiseModifier, 2D);
			noiseModifier = noiseModifier * 0.3;
		}

		noiseModifier *= 2;
		
		return noise + 1 - noiseModifier;
	}

	/**
	 * Generates the shape of the terrain for the chunk using only stone, though air below sea level gets filled with water.
	 */
	public void generateTerrainPerlin(int chunkX, int chunkZ, int[][][] blockArray) {
		int seaLevel = 100;
		
		int maxHeight;
		int minHeight;
		
		double blockNoiseScaleXZ;
		double blockNoiseScaleY;

		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				int x = chunkX * 16 + i;
				int z = chunkZ * 16 + k;
				
				//maxHeight = 216;
				maxHeight = getMaxTerrainHeight(x, z);
				minHeight = getMinTerrainHeight(x, z);
				
				int jStop = Math.max(maxHeight, seaLevel);
				//int jStop = maxHeight;
				
				for (int j = 0; j <= jStop; j++) {
					//Biases the generation based on y level.
					//Below min height is guaranteed to be filled, above max height is guaranteed to be air.
					//Between those is a weighting based on height.
					double noise = 0;
					
					double noiseThresholdBase = 0;
					double noiseThresholdTop = -0.125;
					double noiseThreshold = noiseThresholdBase;
					
					if (j > maxHeight)
						noise = -1;
					else if (j < minHeight)
						noise = 1;
					else {
						blockNoiseScaleXZ = 1/2048D;
						blockNoiseScaleY = 1/1024D;
						
						double blockNoise = blockNoiseGenPerlin.noise3(x, j, z, blockNoiseScaleXZ, blockNoiseScaleY);
						
						//Old linear weighting
						//noise = blockNoise + 1 - 2 * ((j - minHeight) / (double) (maxHeight - minHeight));
						
						double noiseModifier = 0;
						
						//Height weighting
						int heightDivideLevel = seaLevel + 0;
						int heightSmoothingLevel = heightDivideLevel + 10;
						
						if (j >= heightDivideLevel) {
							noiseModifier = (j - heightDivideLevel) / (double) (maxHeight - heightDivideLevel);
							
							/*
							float noiseSmoothFactor = ((j - heightDivideLevel) / (float) (heightSmoothingLevel - heightDivideLevel));
							noiseSmoothFactor = MathHelper.clamp_float(noiseSmoothFactor, 0, 1);
							noiseSmoothFactor = (float) Math.pow(noiseSmoothFactor, 2);
							//noiseModifier = noiseModifier + Math.abs(Math.pow(noiseModifier, 2) - noiseModifier) * noiseSmoothFactor;
							noiseModifier = Math.pow(noiseModifier, 1/2D); 
							noiseModifier = noiseModifier * 0.75 + 0.3;
							*/
							
							double thresholdScale = 1/256D;
							double noiseThresholdModifer = blockThresholdNoiseGen.noise2(x, z, thresholdScale);
							noiseThresholdModifer = (noiseThresholdModifer + 1) / 2;
							noiseThresholdModifer = Math.pow(noiseThresholdModifer, 3);
							noiseThresholdModifer = noiseThresholdModifer * 2 - 1;
							
							noiseThreshold = noiseThresholdTop * noiseThresholdModifer * (j - heightDivideLevel) / (double) (maxHeight - heightDivideLevel);
							
						}
						else {
							noiseModifier = (j - minHeight) / (double) (heightDivideLevel - minHeight);
							noiseModifier = Math.pow(noiseModifier, 2D);
							noiseModifier = noiseModifier * 0.3;
						}

						noiseModifier *= 2;
						
						noise = blockNoise + 1 - noiseModifier;
					}

					if (noise > noiseThreshold) {
					//if (j <= getMaxTerrainHeight(x, z)) { //Debug
						blockArray[i][k][j] = Block.stone.blockID;
					}
					else if (j <= seaLevel) {
						blockArray[i][k][j] = Block.waterStill.blockID;
					}
				}
			}
		}
	}
	
	/**
	 * Generates maximum terrain height
	 * River noise is incorporated into the final output
	 * @param x
	 * @param z
	 * @return the maximum height for generation at the given location as an integer
	 */
	public int getMaxTerrainHeight(int x, int z, boolean generateRivers) {
		//double heightMapNoiseScale = 1/1024D;
		double heightMapNoiseScale = 1/512D;
		
		double heightMapNoise = heightMapNoiseGen.noise2(x, z, heightMapNoiseScale);
		double heightMapNoiseModified = (heightMapNoise + 1) / 2;
		
		//Sigmoid function
		//heightMapNoiseModified = 0.5 * heightMapNoiseModified + 0.5 * (1 / (1 + Math.exp(-(heightMapNoiseModified * 20 - 10))));
		
		//int maxHeight = (int) (120 + heightMapNoiseModified * 80);
		int maxHeight = 200;
		
		double riverNoise = getRiverNoise(x, z);
		
		if (generateRivers)
			maxHeight -= riverNoise * (maxHeight - 91);
		
		//maxHeight = MathHelper.clamp_int(maxHeight, 96, 216);
		
		return maxHeight;
	}
	
	public int getMaxTerrainHeight(int x, int z) {
		return this.getMaxTerrainHeight(x, z, true);
	}
	
	public double getMaxTerrainBias(int x, int z) {
		double heightMapNoiseScale = 1/1024D;
		
		double heightMapNoise = heightMapNoiseGen.noise2(x, z, heightMapNoiseScale);
		double heightMapNoiseModified = 2 - (heightMapNoise + 1) * 3 + 0.5;
		
		return heightMapNoiseModified;
	}
	
	/**
	 * Generates minimum terrain height
	 * @param x
	 * @param z
	 * @return the minimum height for generation at the given location as an integer
	 */
	public int getMinTerrainHeight(int x, int z, boolean generateRivers) {
		double depthMapNoise = depthMapNoiseGen.noise2(x, z, 1/512D);
		double depthMapNoiseModified = (depthMapNoise + 1) / 2;
		
		int minHeight = (int) (95 - depthMapNoiseModified * 20);

		double riverNoise = getRiverNoise(x, z);
		
		if (generateRivers)
			minHeight -= riverNoise * (minHeight - 80);
		
		return minHeight;
	}
	
	public int getMinTerrainHeight(int x, int z) {
		return this.getMinTerrainHeight(x, z, true);
	}
	
	/**
	 * Calculates the noise value for the river map at each location.
	 * Low values of the river noise are culled to not affect terrain.
	 * High values decrease maximum height until they fall below sea level,
	 * creating guaranteed water that is far more consistent than vanilla.
	 * @param x
	 * @param z
	 * @return The noise value as a double between 0 and 1
	 */
	public double getRiverNoise(int x, int z) {
		double riverScale = 1/2048D;
		
		double riverNoise1 = riverNoiseGen.noise2(x, z, riverScale);
		double riverNoise2 = riverNoiseGen2.noise2(x, z, riverScale);
		
		//Final river noise is generated from the intersection of the primary noise fields
		//Dividing by 2 keeps the range (-1, 1)
		double riverNoise = Math.abs(riverNoise1 - riverNoise2) / 2;
		//Higher values mean a river, but the intersection creates low values
		riverNoise = 1 - riverNoise;
		
		//Sigmoid function to bias to edges
		riverNoise = 1 / (1 + Math.exp(-(riverNoise * 16 - 11)));
		
		//Pulls bias towards lower values
		//then redefines the range to (-1, 1) because the sigmoid
		//output is (0, 1)
		//riverNoise = Math.pow(riverNoise, 3D) * 2 - 1;
		riverNoise = Math.pow(riverNoise, 6D);
		
		//Culls lower half of values so that max height does not get increased
		if (riverNoise < 0) {
			riverNoise = 0;
		}
		
		return riverNoise;
	}
	
	public void generateCaves(int chunkX, int chunkZ, int[][][] blockArray) {
		double minHeight = 8;
		double maxHeight = 128;
		
		double minFuzzyHeight = 12;
		double maxFuzzyHeight = 96;
		
		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				for (int j = (int) minHeight; j <= maxHeight; j++) {
					double cheeseNoise = 0;
					
					double spaghettiNoise1 = 0;
					double spaghettiNoise2 = 0;
					
					double hollownessThresholdCheese = 0;
					double hollownessThresholdSpaghetti = 0;
					
					//Generate caves based on noise values
					if (cheeseNoise > hollownessThresholdCheese && blockArray[i][k][j] != Block.waterStill.blockID) {
						if (j < 16)
							blockArray[i][k][j] = Block.lavaStill.blockID;
						else
							blockArray[i][k][j] = 0;
					}

					double spaghettiBalanceScale = 0.2;
					
					if (spaghettiNoise1 * (1 - spaghettiBalanceScale) + spaghettiNoise2 * spaghettiBalanceScale < hollownessThresholdSpaghetti && 
							spaghettiNoise1 * (1 - spaghettiBalanceScale) + spaghettiNoise2 * spaghettiBalanceScale > -hollownessThresholdSpaghetti &&
							spaghettiNoise2 * (1 - spaghettiBalanceScale) + spaghettiNoise1 * spaghettiBalanceScale < hollownessThresholdSpaghetti && 
							spaghettiNoise2 * (1 - spaghettiBalanceScale) + spaghettiNoise1 * spaghettiBalanceScale > -hollownessThresholdSpaghetti &&
							blockArray[i][k][j] != Block.waterStill.blockID) {
						if (j < 16)
							blockArray[i][k][j] = Block.lavaStill.blockID;
						else
							blockArray[i][k][j] = 0;
					}
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int par1, int par2)
	{
		return this.provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	public Chunk provideChunk(int chunkX, int chunkZ)
	{
		this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
		int[][][] blockArray = new int[16][16][256];
		int[][][] metaArray = new int[16][16][256];
		this.generateTerrain(chunkX, chunkZ, blockArray);
		//this.generateTerrainPerlin(chunkX, chunkZ, blockArray);

		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);

		BTASurfaceBuilder.replaceSurface(this.rand, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);
		
		//this.generateCaves(chunkX, chunkZ, blockArray);

		this.caveGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		
		/*
		this.ravineGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.villageGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.strongholdGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		}
		 */

		Chunk chunk = new BTAChunk(this.worldObj, blockArray, metaArray, chunkX, chunkZ);
		byte[] chunkBiomeArray = chunk.getBiomeArray();

		for (int i = 0; i < chunkBiomeArray.length; ++i)
		{
			chunkBiomeArray[i] = (byte)this.biomesForGeneration[i].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
	{
		if (1 == 1)
			return;
		
		BlockSand.fallInstantly = true;
		int x = chunkX * 16;
		int z = chunkZ * 16;
		BiomeGenBase b = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		long var11 = this.rand.nextLong() / 2L * 2L + 1L;
		long var13 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)chunkX * var7 + (long)chunkZ * var9 ^ this.worldObj.getSeed());
		boolean var15 = false;

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
			this.m_structureRand.setSeed((long)chunkX * var11 + (long)chunkZ * var13 ^ this.worldObj.getSeed());
			var15 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, chunkX, chunkZ);
			this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, chunkX, chunkZ);
			this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, chunkX, chunkZ);
		}

		int var16;
		int var17;
		int var18;

		if (!var15 && this.rand.nextInt(4) == 0)
		{
			var16 = x + this.rand.nextInt(16) + 8;
			var17 = this.rand.nextInt(128);
			var18 = z + this.rand.nextInt(16) + 8;
			(new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var16, var17, var18);
		}

		if (!var15 && this.rand.nextInt(8) == 0)
		{
			var16 = x + this.rand.nextInt(16) + 8;
			var17 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			var18 = z + this.rand.nextInt(16) + 8;

			if (var17 < 63 || this.rand.nextInt(10) == 0)
			{
				(new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, var16, var17, var18);
			}
		}

		for (var16 = 0; var16 < 8; ++var16)
		{
			var17 = x + this.rand.nextInt(16) + 8;
			var18 = this.rand.nextInt(128);
			int var19 = z + this.rand.nextInt(16) + 8;

			if ((new WorldGenDungeons()).generate(this.worldObj, this.rand, var17, var18, var19))
			{
				;
			}
		}

		if (b instanceof BTABiomeGenBase)
			((BTABiomeGenBase) b).decorate(this.worldObj, this.rand, x, z, this.generatorInfo);
		else
			b.decorate(this.worldObj, this.rand, x, z);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, b, x + 8, z + 8, 16, 16, this.rand);

		x += 8;
		z += 8;

		for (var16 = 0; var16 < 16; ++var16)
		{
			for (var17 = 0; var17 < 16; ++var17)
			{
				var18 = this.worldObj.getPrecipitationHeight(x + var16, z + var17);

				if (this.worldObj.isBlockFreezable(var16 + x, var18 - 1, var17 + z))
				{
					this.worldObj.setBlock(var16 + x, var18 - 1, var17 + z, Block.ice.blockID, 0, 2);
				}

				if (this.worldObj.canSnowAt(var16 + x, var18, var17 + z))
				{
					this.worldObj.setBlock(var16 + x, var18, var17 + z, Block.snow.blockID, 0, 2);
				}
			}
		}

		BlockSand.fallInstantly = false;
		this.BTWPostProcessChunk(this.worldObj, x - 8, z - 8);
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}

	public void func_104112_b() {}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	 */
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	public String makeString()
	{
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	{
		BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
		return var5 == null ? null : (var5 == BiomeGenBase.swampland && par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(par2, par3, par4) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : var5.getSpawnableList(par1EnumCreatureType));
	}

	/**
	 * Returns the location of the closest structure of the specified type. If not found returns null.
	 */
	public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
	{
		return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(par1World, par3, par4, par5) : null;
	}

	public int getLoadedChunkCount()
	{
		return 0;
	}

	public void recreateStructures(int par1, int par2)
	{
		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
			this.villageGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
			this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
		}
	}

	private void BTWPostProcessChunk(World var1, int var2, int var3)
	{
		if (var1.provider.dimensionId == 0)
		{
			this.GenerateStrata(var1, var2, var3);
			this.GenerateAdditionalBrownMushrooms(var1, var2, var3);
		}
	}

	private void GenerateAdditionalBrownMushrooms(World var1, int var2, int var3)
	{
		if (var1.rand.nextInt(4) == 0)
		{
			WorldGenFlowers var4 = new WorldGenFlowers(Block.mushroomBrown.blockID);
			int var5 = var2 + var1.rand.nextInt(16) + 8;
			int var6 = var1.rand.nextInt(25);
			int var7 = var3 + var1.rand.nextInt(16) + 8;
			var4.generate(var1, var1.rand, var5, var6, var7);
		}
	}

	private void GenerateStrata(World var1, int var2, int var3)
	{
		Chunk var4 = var1.getChunkFromChunkCoords(var2 >> 4, var3 >> 4);

		for (int var5 = 0; var5 < 16; ++var5)
		{
			for (int var6 = 0; var6 < 16; ++var6)
			{
				int var7 = 0;
				int var8;
				int var9;

				for (var8 = 24 + var1.rand.nextInt(2); var7 <= var8; ++var7)
				{
					var9 = var4.getBlockID(var5, var7, var6);

					if (var9 == Block.stone.blockID)
					{
						var4.setBlockMetadata(var5, var7, var6, 2);
					}
				}

				for (var8 = 48 + var1.rand.nextInt(2); var7 <= var8; ++var7)
				{
					var9 = var4.getBlockID(var5, var7, var6);

					if (var9 == Block.stone.blockID)
					{
						var4.setBlockMetadata(var5, var7, var6, 1);
					}
				}
			}
		}
	}
}
