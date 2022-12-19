package betterterrain.world.generate.provider;

import java.util.List;
import java.util.Random;

import betterterrain.BTAMod;
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
import deco.block.DecoBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.SpawnerAnimals;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDungeons;
import net.minecraft.src.WorldGenFlowers;
import net.minecraft.src.WorldGenLakes;

public class BetaChunkProvider extends AbstractChunkProvider
{
	private Random rand;
	private BetaNoiseOctaves blockNoiseGen1;
	private BetaNoiseOctaves blockNoiseGen2;
	private BetaNoiseOctaves blockModifierNoiseGen;
	private BetaNoiseOctaves sandNoiseGen;
	private BetaNoiseOctaves stoneNoiseGen;
	public BetaNoiseOctaves field_922_a;
	public BetaNoiseOctaves field_921_b;
	public BetaNoiseOctaves mobSpawnerNoise;
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private double[] field_4180_q;
	private double[] sandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private double[] stoneNoise = new double[256];
	private BTAMapGenBase mapGenCaves = new BTAMapGenCave();
	private BTAMapGenStronghold strongholdGenerator = new BTAMapGenStronghold();
	public BTAMapGenVillage villageGenerator = new BTAMapGenVillage();
	private BTAMapGenMineshaft mineshaftGenerator = new BTAMapGenMineshaft();
	private BTAMapGenScatteredFeature scatteredFeatureGenerator = new BTAMapGenScatteredFeature();
	private BTAMapGenBase ravineGenerator = new BTAMapGenRavine();
	private BiomeGenBase[] biomesForGeneration;
	private int worldtype;
	double[] blockModiferNoise;
	double[] blockNoise1;
	double[] blockNoise2;
	double[] field_4182_g;
	double[] field_4181_h;
	int[][] field_914_i = new int[32][32];
	private Random m_structureRand;
	
	private WorldConfigurationInfo generatorInfo;
	
	private long seed;

	public BetaChunkProvider(World var1, long var2, boolean var4, WorldConfigurationInfo generatorInfo)
	{
		super(var1, var2, var4, generatorInfo);
		this.worldObj = var1;
		this.rand = new Random(var2);
		this.m_structureRand = new Random(var2);
		this.mapFeaturesEnabled = var4;
		this.generatorInfo = generatorInfo;
		this.blockNoiseGen1 = new BetaNoiseOctaves(this.rand, 16);
		this.blockNoiseGen2 = new BetaNoiseOctaves(this.rand, 16);
		this.blockModifierNoiseGen = new BetaNoiseOctaves(this.rand, 8);
		this.sandNoiseGen = new BetaNoiseOctaves(this.rand, 4);
		this.stoneNoiseGen = new BetaNoiseOctaves(this.rand, 4);
		this.field_922_a = new BetaNoiseOctaves(this.rand, 10);
		this.field_921_b = new BetaNoiseOctaves(this.rand, 16);
		this.mobSpawnerNoise = new BetaNoiseOctaves(this.rand, 8);
		this.seed = var2;
		
		SurfaceBuilder.initForNoiseField(this.seed);
	}

	public void generateTerrain(int var1, int var2, int[] var3, BiomeGenBase[] var4)
	{
		byte var6 = 4;
		byte var7 = 64;
		int var8 = var6 + 1;
		byte var9 = 17;
		int var10 = var6 + 1;
		this.field_4180_q = this.initializeNoiseField(this.field_4180_q, var1 * var6, 0, var2 * var6, var8, var9, var10);

		for (int var11 = 0; var11 < var6; ++var11)
		{
			for (int var12 = 0; var12 < var6; ++var12)
			{
				for (int var13 = 0; var13 < 16; ++var13)
				{
					double var14 = 0.125D;
					double var16 = this.field_4180_q[((var11 + 0) * var10 + var12 + 0) * var9 + var13 + 0];
					double var18 = this.field_4180_q[((var11 + 0) * var10 + var12 + 1) * var9 + var13 + 0];
					double var20 = this.field_4180_q[((var11 + 1) * var10 + var12 + 0) * var9 + var13 + 0];
					double var22 = this.field_4180_q[((var11 + 1) * var10 + var12 + 1) * var9 + var13 + 0];
					double var24 = (this.field_4180_q[((var11 + 0) * var10 + var12 + 0) * var9 + var13 + 1] - var16) * var14;
					double var26 = (this.field_4180_q[((var11 + 0) * var10 + var12 + 1) * var9 + var13 + 1] - var18) * var14;
					double var28 = (this.field_4180_q[((var11 + 1) * var10 + var12 + 0) * var9 + var13 + 1] - var20) * var14;
					double var30 = (this.field_4180_q[((var11 + 1) * var10 + var12 + 1) * var9 + var13 + 1] - var22) * var14;

					for (int var32 = 0; var32 < 8; ++var32)
					{
						double var33 = 0.25D;
						double var35 = var16;
						double var37 = var18;
						double var39 = (var20 - var16) * var33;
						double var41 = (var22 - var18) * var33;

						for (int var43 = 0; var43 < 4; ++var43)
						{
							int var44 = var43 + var11 * 4 << 11 | 0 + var12 * 4 << 7 | var13 * 8 + var32;
							short var45 = 128;
							double var46 = 0.25D;
							double var48 = var35;
							double var50 = (var37 - var35) * var46;

							for (int var52 = 0; var52 < 4; ++var52)
							{
								//double var53 = var5[(var11 * 4 + var43) * 16 + var12 * 4 + var52];
								int var55 = 0;

								if (var13 * 8 + var32 < var7)
								{
									//if (var53 < 0.5D && var13 * 8 + var32 >= var7 - 1)
										//{
										//var55 = Block.ice.blockID;
										//}
									//else
										//{
										var55 = Block.waterStill.blockID;
										//}
								}

								if (var48 > 0.0D)
								{
									var55 = Block.stone.blockID;
								}

								var3[var44] = (byte)var55;
								var44 += var45;
								var48 += var50;
							}

							var35 += var39;
							var37 += var41;
						}

						var16 += var24;
						var18 += var26;
						var20 += var28;
						var22 += var30;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int var1, int var2, int[] var3, BiomeGenBase[] var4)
	{
		byte var5 = 64;
		double var6 = 0.03125D;
		this.sandNoise = this.sandNoiseGen.generateNoiseOctaves(this.sandNoise, (double)(var1 * 16), (double)(var2 * 16), 0.0D, 16, 16, 1, var6, var6, 1.0D);
		this.gravelNoise = this.sandNoiseGen.generateNoiseOctaves(this.gravelNoise, (double)(var1 * 16), 109.0134D, (double)(var2 * 16), 16, 1, 16, var6, 1.0D, var6);
		this.stoneNoise = this.stoneNoiseGen.generateNoiseOctaves(this.stoneNoise, (double)(var1 * 16), (double)(var2 * 16), 0.0D, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int var8 = 0; var8 < 16; ++var8)
		{
			for (int var9 = 0; var9 < 16; ++var9)
			{
				BiomeGenBase var10 = var4[var8 + var9 * 16];
				boolean useSand = this.sandNoise[var9 + var8 * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
				boolean useGravel = this.gravelNoise[var9 + var8 * 16] + this.rand.nextDouble() * 0.2D > 3.0D;
				int var13 = (int)(this.stoneNoise[var9 + var8 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var14 = -1;
				int var15;
				int var16;

				if (var10 instanceof BTABiome) {
					var15 = ((BTABiome) var10).topBlockExt;
					var16 = ((BTABiome) var10).fillerBlockExt;
				}
				else {
					var15 = var10.topBlock;
					var16 = var10.fillerBlock;
				}

				for (int var17 = 127; var17 >= 0; --var17)
				{
					int var18 = (var8 * 16 + var9) * 128 + var17;

					if (var17 <= 0 + this.rand.nextInt(5))
					{
						var3[var18] = Block.bedrock.blockID;
					}
					else
					{
						int var19 = var3[var18];

						if (var19 == 0)
						{
							var14 = -1;
						}
						else if (var19 == Block.stone.blockID)
						{
							if (var14 == -1)
							{
								if (var13 <= 0)
								{
									var15 = 0;
									var16 = Block.stone.blockID;
								}
								else if (var17 >= var5 - 4 && var17 <= var5 + 1)
								{
									if (var10 instanceof BTABiome) {
										var15 = ((BTABiome) var10).topBlockExt;
										var16 = ((BTABiome) var10).fillerBlockExt;
									}
									else {
										var15 = var10.topBlock;
										var16 = var10.fillerBlock;
									}

									if (useGravel)
									{
										var15 = 0;
									}

									if (useGravel)
									{
										var16 = (byte)Block.gravel.blockID;
									}

									if (useSand)
									{
										if (var10.biomeID == 182 /*badlands*/ || var10.biomeID == 183 /*badlands plateau*/ || var10.biomeID == 180 /*outback*/) {
											var15 = DecoBlocks.legacyRedSand.blockID;
											var16 = DecoBlocks.legacyRedSand.blockID;
										}
										else {
											var15 = Block.sand.blockID;
											var16 = Block.sand.blockID;
										}
									}
								}

								if (var17 < var5 && var15 == 0)
								{
									var15 = (byte)Block.waterStill.blockID;
								}

								var14 = var13;

								if (var10.biomeID == 183 /*badlands plateau*/)
									var14 += 10;

								if (var17 >= var5 - 1)
								{
									var3[var18] = var15;
								}
								else
								{
									var3[var18] = var16;
								}
							}
							else if (var14 > 0)
							{
								--var14;
								var3[var18] = var16;
								
								if (var14 == 0 && var16 == Block.sand.blockID)
								{
									var14 = this.rand.nextInt(4);
									var16 = (byte)Block.sandStone.blockID;
								}
								else if (BTAMod.isDecoInstalled() && var14 == 0 && var16 == DecoBlocks.legacyRedSand.blockID)
								{
									var14 = this.rand.nextInt(4);
									var16 = DecoBlocks.redSandstone.blockID;
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
	public Chunk loadChunk(int var1, int var2)
	{
		return this.provideChunk(var1, var2);
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
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);

		this.generateTerrain(chunkX, chunkZ, blockArray, this.biomesForGeneration);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		SurfaceBuilder.replaceSurface(this.rand, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);
		
		this.mapGenCaves.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		this.ravineGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.villageGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.strongholdGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		}

		Chunk chunk = new BTAChunk(this.worldObj, blockArray, metaArray, chunkX, chunkZ);
		byte[] var6 = chunk.getBiomeArray();

		for (int var7 = 0; var7 < var6.length; ++var7)
		{
			var6[var7] = (byte)this.biomesForGeneration[var7].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private double[] initializeNoiseField(double[] noiseField, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ)
	{
		if (noiseField == null)
		{
			noiseField = new double[sizeX * sizeY * sizeZ];
		}

		double octaveScalarXZ = 684.412D;
		double octaveScalarY = 684.412D;
		this.field_4182_g = this.field_922_a.generateNoiseOctaves(this.field_4182_g, posX, posZ, sizeX, sizeZ, 1.121D, 1.121D, 0.5D);
		this.field_4181_h = this.field_921_b.generateNoiseOctaves(this.field_4181_h, posX, posZ, sizeX, sizeZ, 200.0D, 200.0D, 0.5D);
		this.blockModiferNoise = this.blockModifierNoiseGen.generateNoiseOctaves(this.blockModiferNoise, (double)posX, (double)posY, (double)posZ, sizeX, sizeY, sizeZ, octaveScalarXZ / 80.0D, octaveScalarY / 160.0D, octaveScalarXZ / 80.0D);
		this.blockNoise1 = this.blockNoiseGen1.generateNoiseOctaves(this.blockNoise1, (double)posX, (double)posY, (double)posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		this.blockNoise2 = this.blockNoiseGen2.generateNoiseOctaves(this.blockNoise2, (double)posX, (double)posY, (double)posZ, sizeX, sizeY, sizeZ, octaveScalarXZ, octaveScalarY, octaveScalarXZ);
		int blockNoiseIndex = 0;
		int heightmapNoiseIndex = 0;

		for (int i = 0; i < sizeX; ++i)
		{
			for (int k = 0; k < sizeZ; ++k)
			{
				double maxHeightVale = (this.field_4182_g[heightmapNoiseIndex] + 256.0D) / 512.0D;

				if (maxHeightVale > 1.0D)
				{
					maxHeightVale = 1.0D;
				}

				double heightmapNoiseModifier = this.field_4181_h[heightmapNoiseIndex] / 8000.0D;

				if (heightmapNoiseModifier < 0.0D)
				{
					heightmapNoiseModifier = -heightmapNoiseModifier * 0.3D;
				}

				heightmapNoiseModifier = heightmapNoiseModifier * 3.0D - 2.0D;

				if (heightmapNoiseModifier < 0.0D)
				{
					heightmapNoiseModifier /= 2.0D;

					if (heightmapNoiseModifier < -1.0D)
					{
						heightmapNoiseModifier = -1.0D;
					}

					heightmapNoiseModifier /= 1.4D;
					heightmapNoiseModifier /= 2.0D;
					maxHeightVale = 0.0D;
				}
				else
				{
					if (heightmapNoiseModifier > 1.0D)
					{
						heightmapNoiseModifier = 1.0D;
					}

					heightmapNoiseModifier /= 8.0D;
				}

				if (maxHeightVale < 0.0D)
				{
					maxHeightVale = 0.0D;
				}

				maxHeightVale += 0.5D;
				heightmapNoiseModifier = heightmapNoiseModifier * (double)sizeY / 16.0D;
				double minHeightModified = (double)sizeY / 2.0D + heightmapNoiseModifier * 4.0D;
				++heightmapNoiseIndex;

				for (int j = 0; j < sizeY; ++j)
				{
					double noiseSample = 0.0D;
					double heightmapValue = ((double)j - minHeightModified) * 12.0D / maxHeightVale;

					if (heightmapValue < 0.0D)
					{
						heightmapValue *= 4.0D;
					}

					double blockNoiseModiferSample = (this.blockModiferNoise[blockNoiseIndex] / 10.0D + 1.0D) / 2.0D;

					double blockNoiseSample1 = this.blockNoise1[blockNoiseIndex] / 512.0D;
					double blockNoiseSample2 = this.blockNoise2[blockNoiseIndex] / 512.0D;

					if (blockNoiseModiferSample < 0.0D)
					{
						noiseSample = blockNoiseSample1;
					}
					else if (blockNoiseModiferSample > 1.0D)
					{
						noiseSample = blockNoiseSample2;
					}
					else
					{
						noiseSample = blockNoiseSample1 + (blockNoiseSample2 - blockNoiseSample1) * blockNoiseModiferSample;
					}

					noiseSample -= heightmapValue;

					if (j > sizeY - 4)
					{
						double var44 = (double)((float)(j - (sizeY - 4)) / 3.0F);
						noiseSample = noiseSample * (1.0D - var44) + -10.0D * var44;
					}

					noiseField[blockNoiseIndex] = noiseSample;
					++blockNoiseIndex;
				}
			}
		}

		return noiseField;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int var1, int var2)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
		BlockSand.fallInstantly = true;
		int x = par2 * 16;
		int z = par3 * 16;
		BiomeGenBase b = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		long var11 = this.rand.nextLong() / 2L * 2L + 1L;
		long var13 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
		boolean var15 = false;

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
			this.m_structureRand.setSeed((long)par2 * var11 + (long)par3 * var13 ^ this.worldObj.getSeed());
			var15 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, par2, par3);
			this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, par2, par3);
			this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, par2, par3);
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

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean var1, IProgressUpdate var2)
	{
		return true;
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	 */
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	public boolean unload100OldestChunks()
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
	public ChunkPosition findClosestStructure(World var1, String var2, int var3, int var4, int var5)
	{
		return "Stronghold".equals(var2) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(var1, var3, var4, var5) : null;
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

	public void func_104112_b() {}

	@Override
	public boolean isNether() {
		return false;
	}

	@Override
	public BTAMapGenNetherBridge getNetherBridgeGenerator() {
		return null;
	}
}
