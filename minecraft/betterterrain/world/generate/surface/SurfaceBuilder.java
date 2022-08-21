package betterterrain.world.generate.surface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import betterbiomes.DecoIntegration;
import betterterrain.BTAVersion;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NoiseGeneratorOctaves;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;
import opensimplex2.OpenSimplex2F;

public class SurfaceBuilder {
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

	protected static OpenSimplexOctaves sandNoiseGenSimplex;
	protected static OpenSimplexOctaves soilDepthNoiseGenSimplex;
	protected static OpenSimplexOctaves gravelNoiseGenSimplex;

	protected static OpenSimplexOctaves snowHeightNoiseGenSimplex;

	protected BiomeGenBase biome;
	public boolean hasBeenInit = false;

	protected int chunkX;
	protected int chunkZ;

	protected double treeNoiseScale = 1/64D;
	protected OpenSimplexOctaves treeNoiseGen;

	public static final SurfaceBuilder defaultBuilder = new SurfaceBuilder();
	public static final LegacySurfaceBuilder legacyBuilder = new LegacySurfaceBuilder();

	//New 3D array format
	public static void replaceSurface(Random rand, long seed, int chunkX, int chunkZ, int[][][] blockArray, int[][][] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		Set<BiomeGenBase> biomeSet = new HashSet(Arrays.asList(biomesForGeneration));

		for (BiomeGenBase b : biomeSet) {
			if (b instanceof BTABiome && ((BTABiome) b).getSurfaceBuilder() != null) {
				if (!((BTABiome) b).getSurfaceBuilder().hasBeenInit) {
					((BTABiome) b).getSurfaceBuilder().init(rand, seed);
					((BTABiome) b).getSurfaceBuilder().hasBeenInit = true;
				}

				((BTABiome) b).getSurfaceBuilder().initForChunk(chunkX, chunkZ);
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

				if (biome instanceof BTABiome && ((BTABiome) biome).getSurfaceBuilder() != null) {
					((BTABiome) biome).getSurfaceBuilder().replaceBlocksForBiome(rand, k, i, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType, ((BTABiome) biome).isNether());
				}
				else {
					defaultBuilder.setBiome(biome);
					defaultBuilder.replaceBlocksForBiome(rand, k, i, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType, false);
				}
			}
		}
	}

	//Old 1D array format
	public static void replaceSurface(Random rand, long seed, int chunkX, int chunkZ, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		if (generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			if (!legacyBuilder.hasBeenInit) {
				legacyBuilder.init(rand, seed);
				legacyBuilder.hasBeenInit = true;
			}

			legacyBuilder.replaceBlocksForBiome(rand, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo);
		}
		else {
			Set<BiomeGenBase> biomeSet = new HashSet(Arrays.asList(biomesForGeneration));

			for (BiomeGenBase b : biomeSet) {
				if (b instanceof BTABiome && ((BTABiome) b).getSurfaceBuilder() != null) {
					if (!((BTABiome) b).getSurfaceBuilder().hasBeenInit) {
						((BTABiome) b).getSurfaceBuilder().init(rand, seed);
						((BTABiome) b).getSurfaceBuilder().hasBeenInit = true;
					}

					((BTABiome) b).getSurfaceBuilder().initForChunk(chunkX, chunkZ);
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

					if (biome instanceof BTABiome && ((BTABiome) biome).getSurfaceBuilder() != null) {
						((BTABiome) biome).getSurfaceBuilder().replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType, ((BTABiome) biome).isNether());
					}
					else {
						defaultBuilder.setBiome(biome);
						defaultBuilder.replaceBlocksForBiome(rand, i, k, blockArray, metaArray, biomesForGeneration, generatorInfo, worldType, false);
					}
				}
			}
		}
	}

	public static void generateTrees(World world, Random rand, long seed, WorldConfigurationInfo generatorInfo, int chunkX, int chunkZ, BTABiome biome) {
		SurfaceBuilder builder = biome.getSurfaceBuilder();

		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_4_0)) {
			if (builder != null) {
				if (!builder.hasBeenInit) {
					builder.init(rand, seed);
				}
				
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

						if (biomeNeighbor instanceof BTABiome && ((BTABiome) biomeNeighbor).isPlateau()) {
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

				if (biomeForNoise instanceof BTABiome && ((BTABiome) biomeForNoise).isPlateau()) {
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

	public void init(Random rand, long seed) {
		Random sandRand = new Random(seed - 1000);

		sandNoiseGenSimplex = new OpenSimplexOctaves(sandRand.nextLong(), 8);
		gravelNoiseGenSimplex = new OpenSimplexOctaves(sandRand.nextLong(), 8);
		snowHeightNoiseGenSimplex = new OpenSimplexOctaves(sandRand.nextLong(), 8);
	}

	protected void initForChunk(int chunkX, int chunkZ) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;

		double surfaceNoiseScalar = 0.03125D;
		this.soilDepthNoiseLegacy = this.soilDepthNoiseGenLegacy.generateNoiseOctaves(this.soilDepthNoiseLegacy, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, surfaceNoiseScalar * 2.0D, surfaceNoiseScalar * 2.0D, surfaceNoiseScalar * 2.0D);
	}

	//New 3D array format
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[][][] blockArray, int[][][] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo, WorldType worldType, boolean isNether) {
		byte seaLevel = 100;

		float temperature = biome.getFloatTemperature();

		int soilDepth = getSoilDepth(i, k, rand, generatorInfo);
		int remainingDepth = -1;

		boolean useSubfiller = false;

		int surfaceJ = 127;

		for (int j = 127; j >= 0; --j) {
			if (j <= 0 + rand.nextInt(5) && !worldType.isSky()) {
				setBlockValue(blockArray, i, j, k, Block.bedrock.blockID);
			}
			else {
				SurfaceProcessingResult result = generateSurfaceAtLocation(blockArray, metaArray, i, j, k, surfaceJ, soilDepth, remainingDepth, useSubfiller, seaLevel, temperature, false, rand, generatorInfo, worldType);
				remainingDepth = result.remainingDepth;
				useSubfiller = result.useSubfiller;
				surfaceJ = result.surfaceJ;
			}
		}
	}

	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo, WorldType worldType, boolean isNether) {
		byte seaLevel = 63;
		
		if (worldType == BTAMod.BTAWorldTypeBeta || worldType == BTAMod.BTAWorldTypeBetaDeco) {
			seaLevel = 64;
		}
		
		if (worldType.isSky() || isNether)
			seaLevel = 0;

		float temperature = biome.getFloatTemperature();

		int soilDepth = getSoilDepth(i, k, rand, generatorInfo);
		int remainingDepth = -1;

		boolean useSubfiller = false;

		int surfaceJ = 127;

		for (int j = 127; j >= 0; j--) {
			if (j <= 0 + rand.nextInt(5) && !worldType.isSky()) {
				setBlockValue(blockArray, i, j, k, Block.bedrock.blockID);
			}
			else {
				SurfaceProcessingResult result = generateSurfaceAtLocation(blockArray, metaArray, i, j, k, surfaceJ, soilDepth, remainingDepth, useSubfiller, seaLevel, temperature, false, rand, generatorInfo, worldType);
				remainingDepth = result.remainingDepth;
				useSubfiller = result.useSubfiller;
				surfaceJ = result.surfaceJ;
			}
		}
	}
	
	protected SurfaceProcessingResult generateSurfaceAtLocation(
			Object blockArray, Object metaArray, 
			int i, int j, int k, int surfaceJ, 
			int soilDepth, int remainingDepth, boolean useSubfiller, int seaLevel, float temperature,
			boolean isReversed,
			Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) 
	{
		int blockID = getBlockValue(blockArray, i, j, k);

		if (blockID == 0) {
			remainingDepth = -1;
			useSubfiller = false;
		}
		else if (blockID == Block.stone.blockID || blockID == Block.netherrack.blockID) {
			if (remainingDepth == -1) {
				remainingDepth = soilDepth;
				surfaceJ = j;

				int[] surfaceBlock = this.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, SurfaceType.TOP, seaLevel, isReversed, rand, generatorInfo, worldType);

				if (j < seaLevel && surfaceBlock[0] == 0) {
					if (temperature < 0.15F) {
						surfaceBlock[0] = Block.ice.blockID;
						surfaceBlock[1] = 0;
					}
					else {
						surfaceBlock[0] = Block.waterStill.blockID;
						surfaceBlock[1] = 0;
					}
				}

				setBlockValue(blockArray, i, j, k, surfaceBlock[0]);
				setBlockValue(metaArray, i, j, k, surfaceBlock[1]);
			}
			else if (remainingDepth > 0) {
				remainingDepth--;

				SurfaceType surfaceType = useSubfiller ? SurfaceType.SUBFILLER : SurfaceType.FILLER;
				
				int[] fillerBlock = this.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, isReversed, rand, generatorInfo, worldType);
				
				setBlockValue(blockArray, i, j, k, fillerBlock[0]);
				setBlockValue(metaArray, i, j, k, fillerBlock[1]);

				if (remainingDepth == 0 && !useSubfiller) {
					int subfillerDepth = this.getSubsurfaceDepth(rand);
					remainingDepth += subfillerDepth;
					useSubfiller = true;
				}
			}
		}
		
		return new SurfaceProcessingResult(remainingDepth, surfaceJ, useSubfiller);
	}
	
	public static class SurfaceProcessingResult {
		public int remainingDepth;
		public int surfaceJ;
		public boolean useSubfiller;
		
		public SurfaceProcessingResult(int remainingDepth, int surfaceJ, boolean useSubfiller) {
			this.remainingDepth = remainingDepth;
			this.surfaceJ = surfaceJ;
			this.useSubfiller = useSubfiller;
		}
	}

	protected void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		int numTrees;

		if (biome instanceof BTABiome) {
			numTrees = ((BTABiome) biome).btaBiomeDecorator.treesPerChunk;

			if (rand.nextInt(((BTABiome) biome).btaBiomeDecorator.fractionalTreeChance) == 0)
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

			if (biome instanceof BTABiome) {
				gen = ((BTABiome) this.biome).getRandomWorldGenForTrees(rand, generatorInfo, world.provider.terrainType);
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
			((int[]) blockArray)[(k * 16 + i) * 128 + y] = id;
		}
		else if (blockArray instanceof int[][][]) {
			((int[][][]) blockArray)[i][k][y] = id;
		}
		else {
			throw new IllegalArgumentException("blockArray must be of type int[] or int[][][]");
		}
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
	protected int getBlockValue(Object blockArray, int i, int y, int k) {
		if (blockArray instanceof int[]) {
			return ((int[]) blockArray)[(k * 16 + i) * 128 + y];
		}
		else if (blockArray instanceof int[][][]) {
			return ((int[][][]) blockArray)[i][k][y];
		}
		else {
			throw new IllegalArgumentException("blockArray must be of type int[] or int[][][]");
		}
	}

	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, boolean isReversed, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		return this.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
	}
	
	/**
	 * Gets the block to use for the surface layer for this biome
	 * @param i Local x value for this chunk
	 * @param y Absolute y value
	 * @param k Local z value for this chunk
	 * @param isTopBlock Whether to return the top block or the filler block
	 * @param rand
	 * @param generatorInfo
	 * @param worldType
	 * @return An int array of blockID, metadata
	 */
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		int blockID = -1;
		int metadata = 0;

		if (generatorInfo.generatePerlinBeaches() && 
				surfaceJ >= seaLevel - (8 + rand.nextInt(2)) && 
				surfaceJ <= seaLevel + 1) {
			if (useGravelAtLocation(i, k, rand, generatorInfo)) {
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

		if (soilDepth <= 0) {
			if (surfaceType == SurfaceType.TOP) {
				blockID = 0;
			}
			else {
				blockID = Block.stone.blockID;
			}
		}

		if (blockID == -1) {
			if (j < seaLevel - 1 && surfaceType == surfaceType.TOP) {
				surfaceType = SurfaceType.FILLER;
			}

			blockID = getDefaultSurfaceBlock(i, k, surfaceType, Block.stone.blockID);
		}

		return new int[] {blockID, metadata};
	}
	
	protected int getDefaultSurfaceBlock(int i, int k, SurfaceType surfaceType, int baseBlock) {
		if (this.biome instanceof BTABiome) {
			switch (surfaceType) {
			case TOP:
				return ((BTABiome) this.biome).topBlockExt;
			case FILLER:
				return ((BTABiome) this.biome).fillerBlockExt;
			case SUBFILLER:
				if (((BTABiome) this.biome).topBlockExt == Block.sand.blockID) {
					return Block.sandStone.blockID;
				}
				else if (DecoIntegration.isDecoInstalled()) {
					if (((BTABiome) this.biome).topBlockExt == DecoIntegration.redSand.blockID) {
						return DecoIntegration.redSandStone.blockID;
					}
					else {
						return baseBlock;
					}
				}
				else {
					return baseBlock;
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
					return baseBlock;
				}
			}
		}

		return baseBlock;
	}

	protected boolean useSandAtLocation(int i, int k, Random rand) {
		double beachNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		return sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), beachNoiseScale) + rand.nextDouble() * 0.2D > 0;
	}

	protected boolean useGravelAtLocation(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V2_0_3)) {
			double beachNoiseScale = 1/384D;
			//k and i swapped because apparently I messed something up somewhere
			return gravelNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), beachNoiseScale) > 0.925;
		}
		else {
			return gravelNoise[k * 16 + i] + rand.nextDouble() * 0.2D > 3.0D;
		}
	}

	protected int getSoilDepth(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		return (int) (soilDepthNoiseLegacy[k * 16 + i] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
	}
	
	public int getSnowHeightOffset(int x, int z, Random rand) {
		double snowNoiseScale = 1/96D;
		int snowOffset = (int) (snowHeightNoiseGenSimplex.noise2(x, z, snowNoiseScale) * 5);
		return snowOffset;
	}
	
	protected int getSubsurfaceDepth(Random rand) {
		return rand.nextInt(4);
	}
	
	public boolean hasBeenInit() {
		return this.hasBeenInit;
	}
	
	public void setHasBeenInit(boolean hasBeenInit) {
		this.hasBeenInit = hasBeenInit;
	}

	public enum SurfaceType {
		TOP,
		FILLER,
		SUBFILLER
	}
}