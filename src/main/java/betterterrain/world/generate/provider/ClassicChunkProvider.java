package betterterrain.world.generate.provider;

import java.util.List;
import java.util.Random;

import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.structure.mapgen.BTAMapGenBase;
import betterterrain.structure.mapgen.BTAMapGenCave;
import betterterrain.structure.mapgen.BTAMapGenMineshaft;
import betterterrain.structure.mapgen.BTAMapGenNetherBridge;
import betterterrain.structure.mapgen.BTAMapGenRavine;
import betterterrain.structure.mapgen.BTAMapGenScatteredFeature;
import betterterrain.structure.mapgen.BTAMapGenStronghold;
import betterterrain.structure.mapgen.BTAMapGenVillage;
import betterterrain.world.BTAChunk;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.BetaNoiseOctaves;
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

public class ClassicChunkProvider extends AbstractChunkProvider
{
	/** RNG. */
	private Random rand;

	private NoiseGeneratorOctaves blockNoiseGen1;
	private NoiseGeneratorOctaves blockNoiseGen2;
	private NoiseGeneratorOctaves blockModifierNoiseGen;
	public NoiseGeneratorOctaves biomeHeightNoiseGen;

	private NoiseGeneratorOctaves soilDepthNoiseGen;

	/** Holds the overall noise array used in chunk generation */
	private double[] noiseArray;
	private double[] soilDepthNoise = new double[256];
	private BTAMapGenBase caveGenerator = new BTAMapGenCave();

	/** Holds Stronghold Generator */
	private BTAMapGenStronghold strongholdGenerator = new BTAMapGenStronghold();

	/** Holds Village Generator */
	public BTAMapGenVillage villageGenerator = new BTAMapGenVillage();

	/** Holds Mineshaft Generator */
	private BTAMapGenMineshaft mineshaftGenerator = new BTAMapGenMineshaft();
	protected BTAMapGenScatteredFeature scatteredFeatureGenerator = new BTAMapGenScatteredFeature();

	/** Holds ravine generator */
	private BTAMapGenBase ravineGenerator = new BTAMapGenRavine();

	/** The biomes that are used to generate the chunk */
	private BiomeGenBase[] biomesForGeneration;

	double[] blockModifierNoise;
	double[] blockNoise1;
	double[] blockNoise2;
	double[] biomeHeightNoise;

	private double[] sandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private BetaNoiseOctaves sandNoiseGen;

	/**
	 * Used to store the 5x5 parabolic field that is used during terrain generation.
	 */
	float[] parabolicField;
	int[][] field_73219_j = new int[32][32];
	private Random m_structureRand;
	private int parabolicRadius = 2;

	public ClassicChunkProvider(World world, long seed, boolean mapFeaturesEnabled, WorldConfigurationInfo generatorInfo) {
		super(world, seed, mapFeaturesEnabled, generatorInfo);
		this.m_structureRand = new Random(seed);
		this.blockNoiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.blockNoiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.blockModifierNoiseGen = new NoiseGeneratorOctaves(this.rand, 8);
		this.soilDepthNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
		this.biomeHeightNoiseGen = new NoiseGeneratorOctaves(this.rand, 16);
		this.sandNoiseGen = new BetaNoiseOctaves(this.rand, 4);

		SurfaceBuilder.initForNoiseField(this.seed);
	}

	/**
	 * Generates the shape of the terrain for the chunk though its all stone though the water is frozen if the
	 * temperature is low enough
	 */
	public void generateTerrain(int chunkX, int chunkZ, int[] blockArray)
	{
		byte var4 = 4;
		byte var5 = 16;
		byte var6 = 63;
		int var7 = var4 + 1;
		byte var8 = 17;
		int var9 = var4 + 1;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, var7 + 5, var9 + 5);

		if (this.generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_4_0)) {
			this.noiseArray = SurfaceBuilder.initializeNoiseField(this.rand, this.seed, this.noiseArray, chunkX * var4, 0, chunkZ * var4, var7, var8, var9, biomesForGeneration);
		}
		else {
			this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * var4, 0, chunkZ * var4, var7, var8, var9);
		}

		for (int var10 = 0; var10 < var4; ++var10)
		{
			for (int var11 = 0; var11 < var4; ++var11)
			{
				for (int var12 = 0; var12 < var5; ++var12)
				{
					double var13 = 0.125D;
					double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
					double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
					double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
					double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
					double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
					double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
					double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
					double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

					for (int var31 = 0; var31 < 8; ++var31)
					{
						double var32 = 0.25D;
						double var34 = var15;
						double var36 = var17;
						double var38 = (var19 - var15) * var32;
						double var40 = (var21 - var17) * var32;

						for (int var42 = 0; var42 < 4; ++var42)
						{
							int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
							short var44 = 128;
							var43 -= var44;
							double var45 = 0.25D;
							double var47 = (var36 - var34) * var45;
							double var49 = var34 - var47;

							for (int var51 = 0; var51 < 4; ++var51)
							{
								if ((var49 += var47) > 0.0D /*&& (biomesForGeneration[var42 + var10 * 4 << 4 | 0 + var11 * 4] != BTABiomeConfiguration.valleyMountains || (var43 & 127) < 90)*/)
								{
									blockArray[var43 += var44] = (byte)Block.stone.blockID;
								}
								else if (var12 * 8 + var31 < var6)
								{
									blockArray[var43 += var44] = (byte)Block.waterStill.blockID;
								}
								else
								{
									blockArray[var43 += var44] = 0;
								}
							}

							var34 += var38;
							var36 += var40;
						}

						var15 += var23;
						var17 += var25;
						var19 += var27;
						var21 += var29;
					}
				}
			}
		}
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	public Chunk provideChunk(int chunkX, int chunkZ)
	{
		this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
		int[] blockArray = new int[32768];
		int[] metaArray = new int[32768];
		this.generateTerrain(chunkX, chunkZ, blockArray);

		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		SurfaceBuilder.replaceSurface(this.rand, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);

		this.caveGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		this.ravineGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.villageGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.strongholdGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		}

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
			SurfaceBuilder surfaceBuilder = ((BTABiome) b).getSurfaceBuilder();
			
			if (surfaceBuilder != null) {
				surfaceBuilder.decorateSurface(worldObj, this.rand, (BTABiome) b, x, z, this.generatorInfo);
			}
			
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
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);

		if (biome != null) {
			if (creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(x, y, z)) {
				List spawnList = this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();

				if (spawnList != null) {
					return spawnList;
				}
				else {
					return biome.getSpawnableList(creatureType);
				}
			}
			else {
				return biome.getSpawnableList(creatureType);
			}
		}
		else {
			return null;
		}
	}

	@Override
	public List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);

		if (biome != null) {
			if (creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(x, y, z)) {
				List spawnList = this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();

				if (spawnList != null) {
					return spawnList;
				}
			}
		}
		
		return null;
	}
	
	public boolean doesStructureExistAtCoords(int x, int y, int z) {
		return this.scatteredFeatureGenerator.hasStructureAt(x, y, z);
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
			this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (int[])null);
			this.villageGenerator.generate(this, this.worldObj, par1, par2, (int[])null);
			this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (int[])null);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (int[])null);
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

	@Override
	public boolean isNether() {
		return false;
	}

	@Override
	public BTAMapGenNetherBridge getNetherBridgeGenerator() {
		return null;
	}
}
