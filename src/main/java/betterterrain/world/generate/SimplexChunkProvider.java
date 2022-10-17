package betterterrain.world.generate;

import java.util.List;
import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.structure.mapgen.BTAMapGenBase;
import betterterrain.structure.mapgen.BTAMapGenCave;
import betterterrain.structure.mapgen.BTAMapGenMineshaft;
import betterterrain.structure.mapgen.BTAMapGenRavine;
import betterterrain.structure.mapgen.BTAMapGenScatteredFeature;
import betterterrain.structure.mapgen.BTAMapGenStronghold;
import betterterrain.structure.mapgen.BTAMapGenVillage;
import betterterrain.world.BTAChunk;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NoiseGeneratorOctaves;
import net.minecraft.src.SpawnerAnimals;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDungeons;
import net.minecraft.src.WorldGenFlowers;
import net.minecraft.src.WorldGenLakes;

public class SimplexChunkProvider implements IChunkProvider
{
	/** RNG. */
	private Random rand;

	private OpenSimplexOctaves blockNoiseGen;
	private OpenSimplexOctaves blockThresholdNoiseGen;
	
	private OpenSimplexOctaves ridgeNoiseGen;
	private OpenSimplexOctaves erosionLevelNoiseGen;
	private OpenSimplexOctaves erosionNoiseGen;
	private OpenSimplexOctaves mountainNoiseGen;
	private OpenSimplexOctaves oceanNoiseGen;
	private OpenSimplexOctaves riverNoiseGen;
	private OpenSimplexOctaves riverNoiseGen2;
	
	private OpenSimplexOctaves cheeseCaveNoiseGen;
	private OpenSimplexOctaves spaghettiCaveNoiseGen;
	private OpenSimplexOctaves spaghettiCaveNoiseGen2;
	
	private OpenSimplexOctaves caveHollownessNoiseGen;
	private OpenSimplexOctaves caveFrequencyNoiseGen;
	private OpenSimplexOctaves caveFlatnessNoiseGen;

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

	public WorldConfigurationInfo generatorInfo;
	private long seed;
	
	private double[][][] gaussianKernel;

	public SimplexChunkProvider(World world, long seed, boolean mapFeaturesEnabled, WorldConfigurationInfo generatorInfo)
	{
		this.worldObj = world;
		this.mapFeaturesEnabled = mapFeaturesEnabled;
		this.rand = new Random(seed);
		this.m_structureRand = new Random(seed);
		this.generatorInfo = generatorInfo;
		
		long blockSeed = rand.nextLong();
		this.blockNoiseGen = new OpenSimplexOctaves(blockSeed, 5);
		this.blockThresholdNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 1);
		
		this.ridgeNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 2);
		this.erosionLevelNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 1);
		this.erosionNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 2);
		this.mountainNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 1);
		this.oceanNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 3);
		this.riverNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 8);
		this.riverNoiseGen2 = new OpenSimplexOctaves(rand.nextLong(), 8);
		
		this.cheeseCaveNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 4);
		this.spaghettiCaveNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 5);
		this.spaghettiCaveNoiseGen2 = new OpenSimplexOctaves(rand.nextLong(), 5);

		this.caveHollownessNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 1);
		this.caveFrequencyNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 1);
		this.caveFlatnessNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 1);
		
		this.seed = seed;

		SurfaceBuilder.initForNoiseField(this.seed);
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
				
				//Soft max height
				maxHeight = 200;
				//Hard min height
				minHeight = 75;

				blockNoiseScaleXZ = getTerrainFrequency(x, z);
				blockNoiseScaleY = blockNoiseScaleXZ;
				
				//Hard max height
				int jStop = Math.max(maxHeight + 48, seaLevel);
				
				for (int j = 0; j <= jStop; j++) {
					double noise = 0;
					
					double noiseThreshold = 0;
					
					if (j < minHeight)
						noise = 1;
					else {
						double blockNoise = blockNoiseGen.noise3(x, j, z, blockNoiseScaleXZ, blockNoiseScaleY, false);
						
						noise = biasNoiseForHeight(blockNoise, x, j, z, seaLevel, maxHeight, minHeight);
						noise = erodeTerrain(noise, x, j, z, seaLevel);
						noise = cutRivers(noise, x, j, z, jStop);
					}

					if (noise > noiseThreshold) {
						blockArray[i][k][j] = Block.stone.blockID;
					}
					else if (j <= seaLevel) {
						blockArray[i][k][j] = Block.waterStill.blockID;
					}
					
					//Breaks inner loop if it hits air while above max height
					//Saves performance slightly and reduces chance of issues with terrain getting cut off
					if (noise < noiseThreshold && j > maxHeight) {
						break;
					}
				}
			}
		}
	}
	
	public double getTerrainFrequency(int x, int z) {
		double ridgeNoiseScale = 1/1024D;
		double ridgeNoise = (float) ridgeNoiseGen.noise2(x, z, ridgeNoiseScale);
		
		double baseTerrainFrequency = 384D;
		double frequencyVariance = 24D;
		
		double frequency = 1/(baseTerrainFrequency + frequencyVariance * Math.pow(ridgeNoise, 2));
		
		return frequency;
	}
	
	public double erodeTerrain(double noise, int x, int y, int z, int seaLevel) {
		double erosionLevelNoiseScale = 1/1024D;
		double erosionLevel = erosionLevelNoiseGen.noise2(x, z, erosionLevelNoiseScale);
		//Normalize to (0, 1)
		erosionLevel = (erosionLevel + 1) / 2;
		erosionLevel = MathHelper.clamp_float((float) erosionLevel, 0, 1);
		erosionLevel = Math.pow(erosionLevel, 12);
		
		double erosionNoiseScale = 1/32D;
		double erosion = erosionNoiseGen.noise2(x, z, erosionNoiseScale);
		//Normalize to (0, 1)
		erosion = (erosion + 1) / 2;
		erosion = Math.pow(erosion, 3);
		//Makes cutoff sharper and makes eroded outcroppings less round
		erosion = erosion * 1.5;
		erosion = MathHelper.clamp_float((float) erosion, 0, 1);
		
		double erosionConstant = 1/3D;
		double erosionFactor = erosion * erosionLevel;
		
		noise += erosionFactor * erosionConstant;
		
		return noise;
	}
	
	public double biasNoiseForHeight(double noise, int x, int y, int z, int seaLevel, int maxHeight, int minHeight) {
		double noiseModifier;
		
		int heightDivideLevel = seaLevel;
		int heightSmoothingLevel = heightDivideLevel + 10;
		
		if (y >= heightDivideLevel) {
			noiseModifier = (y - heightDivideLevel) / (double) (maxHeight - heightDivideLevel);
			noiseModifier = Math.pow(noiseModifier, 1/2D);
			noiseModifier = noiseModifier * (0.75) + 0.3;
			
		}
		else {
			noiseModifier = (y - minHeight) / (double) (heightDivideLevel - minHeight);
			noiseModifier = Math.pow(noiseModifier, 2D);
			noiseModifier = noiseModifier * 0.3;
		}
		
		return noise + 1 - (2 * noiseModifier);
	}
	
	public double cutRivers(double noise, int x, int y, int z, int maxHeight) {
		double riverNoise = this.getRiverNoise(x, z);
		
		if (y <= maxHeight - riverNoise * (maxHeight - 91)) {
			return noise;
		}
		else {
			return -1;
		}
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
		
		//riverNoise = Math.pow(riverNoise, 3D) * 3 - 2;
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

		SurfaceBuilder.replaceSurface(this.rand, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);
		
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

		if (b instanceof BTABiome) {
			((BTABiome) b).getSurfaceBuilder().decorateSurface(worldObj, this.rand, (BTABiome) b, x, z, this.generatorInfo);
			((BTABiome) b).decorate(this.worldObj, this.rand, x, z, this.generatorInfo);
		}
		else {
			b.decorate(this.worldObj, this.rand, x, z);
		}
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
