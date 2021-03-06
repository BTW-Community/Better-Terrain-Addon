package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAChunkProviderSimplex implements IChunkProvider
{
	/** RNG. */
	private Random rand;

	private BTAOpenSimplexOctavesFast blockNoiseGen;
	
	private BTAOpenSimplexOctavesFast cheeseCaveNoiseGen;
	private BTAOpenSimplexOctavesFast spaghettiCaveNoiseGen;
	private BTAOpenSimplexOctavesFast spaghettiCaveNoiseGen2;
	
	private BTAOpenSimplexOctavesFast caveHollownessNoiseGen;
	private BTAOpenSimplexOctavesFast caveFrequencyNoiseGen;
	private BTAOpenSimplexOctavesFast caveFlatnessNoiseGen;

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
	private int parabolicRadius = 7;

	public BTAChunkProviderSimplex(World world, long seed, boolean mapFeaturesEnabled, BTAWorldConfigurationInfo generatorInfo)
	{
		this.worldObj = world;
		this.mapFeaturesEnabled = mapFeaturesEnabled;
		this.rand = new Random(seed);
		this.m_structureRand = new Random(seed);
		this.generatorInfo = generatorInfo;
		
		long blockSeed = rand.nextLong();
		this.blockNoiseGen = new BTAOpenSimplexOctavesFast(blockSeed, 5);
		
		this.cheeseCaveNoiseGen = new BTAOpenSimplexOctavesFast(rand.nextLong(), 4);
		this.spaghettiCaveNoiseGen = new BTAOpenSimplexOctavesFast(rand.nextLong(), 5);
		this.spaghettiCaveNoiseGen2 = new BTAOpenSimplexOctavesFast(rand.nextLong(), 5);

		this.caveHollownessNoiseGen = new BTAOpenSimplexOctavesFast(rand.nextLong(), 1);
		this.caveFrequencyNoiseGen = new BTAOpenSimplexOctavesFast(rand.nextLong(), 1);
		this.caveFlatnessNoiseGen = new BTAOpenSimplexOctavesFast(rand.nextLong(), 1);
		
		this.seed = seed;

		BTASurfaceBuilder.initForNoiseField(this.seed);
	}

	/**
	 * Generates the shape of the terrain for the chunk using only stone, though air below sea level gets filled with water.
	 */
	public void generateTerrain(int chunkX, int chunkZ, int[][][] blockArray) {
		int seaLevel = 100;

		//Parabolic field is used for smoothing between biomes
		int parabolicDiameter = parabolicRadius * 2 + 1;

		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16 - parabolicRadius, chunkZ * 16 - parabolicRadius, 16 + parabolicDiameter, 16 + parabolicDiameter);

		if (this.parabolicField == null) {
			this.parabolicField = new float[(int) Math.pow(parabolicDiameter, 2)];

			for (int i = -parabolicRadius; i <= parabolicRadius; ++i) {
				for (int k = -parabolicRadius; k <= parabolicRadius; ++k) {
					float parabolicValue = 10.0F / MathHelper.sqrt_float((float)(i * i + k * k) + 0.2F);
					this.parabolicField[i + parabolicRadius + (k + parabolicRadius) * parabolicDiameter] = parabolicValue;
				}
			}
		}
		
		int maxHeight = 200;
		int minHeight = 75;

		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				float biomeMaxHeightSample = 0.0F;
				float biomeMinHeightSample = 0.0F;
				float biomeModifierTotal = 0.0F;
				BiomeGenBase biome = this.biomesForGeneration[i + parabolicRadius + (k + parabolicRadius) * (16 + parabolicDiameter)];

				//Gets biome height values influenced by neighboring biomes
				//Weights provided by the parabolic field
				for (int c = -parabolicRadius; c <= parabolicRadius; ++c) {
					for (int d = -parabolicRadius; d <= parabolicRadius; ++d) {
						BiomeGenBase biomeNeighbor = this.biomesForGeneration[i + c + parabolicRadius + (k + d + parabolicRadius) * (16 + parabolicDiameter)];
						float biomeHeightModifierValue = this.parabolicField[c + parabolicRadius + (d + parabolicRadius) * parabolicDiameter] / (biomeNeighbor.minHeight + 2.0F);

						if (biomeNeighbor.minHeight > biome.minHeight) {
							biomeHeightModifierValue /= 2.0F;
						}

						if (c * c + d * d < parabolicRadius * parabolicRadius) {
							biomeMaxHeightSample += biomeNeighbor.maxHeight * biomeHeightModifierValue;
							biomeMinHeightSample += biomeNeighbor.minHeight * biomeHeightModifierValue;
							biomeModifierTotal += biomeHeightModifierValue;
						}
					}
				}

				//Scales min and max biome heights based on surrounding biomes
				biomeMaxHeightSample /= biomeModifierTotal;
				biomeMinHeightSample /= biomeModifierTotal;

				double biomeHeightTransitionWidth = .5;
				double biomeHeightToBlockScalar = 25;

				int biomeMaxHeightScaled = (int) (biomeMaxHeightSample * biomeHeightToBlockScalar) + seaLevel;
				int biomeMinHeightScaled = (int) (biomeMinHeightSample * biomeHeightToBlockScalar / 2) + seaLevel;

				for (int j = 0; j <= maxHeight; j++) {
					//Biases the generation based on y level.
					//Below min height is guaranteed to be filled, above max height is guaranteed to be air.
					//Between those is a linear weighting based on height, then modification to target the biome height.
					double baseNoise = 0;

					if (j > maxHeight)
						baseNoise = -1;
					else if (j < minHeight)
						baseNoise = 1;
					else {
						double blockNoise = blockNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/256D, 1/256D);
						baseNoise = blockNoise + 1 - 2 * ((j - minHeight) / (double) (maxHeight - minHeight));
					}

					double biomeNoise;

					if (j > biomeMaxHeightScaled)
						biomeNoise = -1;
					else if (j < biomeMinHeightScaled)
						biomeNoise = 1;
					else {
						double noise = blockNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/256D, 1/256D);
						biomeNoise = noise + 1 - 2 * ((j - biomeMinHeightScaled) / (double) (biomeMaxHeightScaled - biomeMinHeightScaled));
					}

					double baseWeight = 1;
					double biomeWeight = 0;

					double noise = (baseNoise * baseWeight + biomeNoise * biomeWeight) / (baseWeight + biomeWeight);

					if (noise > 0) {
						blockArray[i][k][j] = Block.stone.blockID;
					}
					else if (j <= seaLevel) {
						blockArray[i][k][j] = Block.waterStill.blockID;
					}
				}
			}
		}
	}
	
	public void generateCaves(int chunkX, int chunkZ, int[][][] blockArray) {
		double minHeight = 8;
		double maxHeight = 128;
		
		double minFuzzyHeight = 12;
		double maxFuzzyHeight = 96;
		
		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				for (int j = (int) minHeight; j <= maxHeight; j++) {
					double hollownessParamterScale = 256;
					double frequencyParameterScale = 512;
					double flatnessParameterScale = 128;
					
					double hollowness = caveHollownessNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/hollownessParamterScale);
					
					double hollownessModifierScaleCheese = .2;
					double baseHollownessCheese = .8;
					double hollownessThresholdCheese = hollowness * hollownessModifierScaleCheese + baseHollownessCheese;

					double hollownessModifierScaleSpaghetti = .025;
					double baseHollownessSpaghetti = .075;
					double hollownessThresholdSpaghetti = hollowness * hollownessModifierScaleSpaghetti + baseHollownessSpaghetti;
					
					double frequency = caveFrequencyNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/frequencyParameterScale);
					double frequencyScaleCheese = 96;
					double frequencyScaleSpaghetti = 192;
					double frequencyModifierScale = .5;
					
					frequencyScaleSpaghetti = frequencyScaleSpaghetti + frequency * frequencyScaleSpaghetti * frequencyModifierScale;

					double flatness = caveFlatnessNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/flatnessParameterScale);
					flatness = 1.5 + .25 * flatness;
					
					double cheeseNoise = cheeseCaveNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/(frequencyScaleCheese * flatness), 1/frequencyScaleCheese);
					
					double spaghettiNoise1 = spaghettiCaveNoiseGen.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/(frequencyScaleSpaghetti * flatness), 1/frequencyScaleSpaghetti);
					double spaghettiNoise2 = spaghettiCaveNoiseGen2.noise3(chunkX * 16 + i, j, chunkZ * 16 + k, 1/(frequencyScaleSpaghetti * flatness), 1/frequencyScaleSpaghetti);
					
					//Limit the y values of caves
					if (j < minFuzzyHeight) {
						cheeseNoise = cheeseNoise -  (1 - ((j - minHeight) / (minFuzzyHeight - minHeight)));
						spaghettiNoise1 = spaghettiNoise1 -  (1 - ((j - minHeight) / (minFuzzyHeight - minHeight)));
						spaghettiNoise2 = spaghettiNoise2 -  (1 - ((j - minHeight) / (minFuzzyHeight - minHeight)));
					}
					else if (j > maxFuzzyHeight) {
						cheeseNoise = cheeseNoise -  (((j - maxFuzzyHeight) / (maxHeight - maxFuzzyHeight)));
						spaghettiNoise1 = spaghettiNoise1 -  (((j - maxFuzzyHeight) / (maxHeight - maxFuzzyHeight)));
						spaghettiNoise2 = spaghettiNoise2 -  (((j - maxFuzzyHeight) / (maxHeight - maxFuzzyHeight)));
					}
					
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

		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);

		BTASurfaceBuilder.replaceSurface(this.rand, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);
		
		this.generateCaves(chunkX, chunkZ, blockArray);

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
