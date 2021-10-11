package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderConiferousForest extends BTASurfaceBuilder {
	protected static BTAUtilsOpenSimplexOctaves coarseDirtNoiseGenSimplex;
	protected static BTAUtilsOpenSimplexOctaves podzolNoiseGenSimplex;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random soilRand = new Random(seed);

		if (coarseDirtNoiseGenSimplex == null)
			coarseDirtNoiseGenSimplex = new BTAUtilsOpenSimplexOctaves(soilRand.nextLong(), 4);
		if (podzolNoiseGenSimplex == null)
			podzolNoiseGenSimplex = new BTAUtilsOpenSimplexOctaves(soilRand.nextLong(), 4);

		if (this.treeNoiseGen == null);
			this.treeNoiseGen = new BTAUtilsOpenSimplexOctaves(soilRand.nextLong(), 2);

		this.treeNoiseScale = 1/256D;
	}

	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();
		
		double grassNoiseScale = 1/96D;
		//k and i swapped because apparently I messed something up somewhere
		boolean usePodzol = coarseDirtNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.1D - .25 > 0;
		//k and i swapped because apparently I messed something up somewhere
		boolean useCoarseDirt = podzolNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.1D - .25 > 0;

		double sandNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useSand = sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), sandNoiseScale) + rand.nextDouble() * 0.2D > 0;

		//boolean useSand = sandNoise[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(soilDepthNoiseLegacy[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int remaingDepth = -1;
		int topBlock;
		int fillerBlock;

		topBlock = ((BTABiomeGenBase) biome).topBlockExt;
		fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;

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
						if (useCoarseDirt && BTADecoIntegration.isDecoInstalled() && worldType.isDeco()) {
							topBlock = BTADecoIntegration.coarseDirt.blockID;
						}

						if (usePodzol && BTADecoIntegration.isDecoInstalled() && worldType.isDeco()) {
							topBlock = BTADecoIntegration.podzol.blockID;
						}
						
						if (soilDepthNoiseSample <= 0) {
							topBlock = 0;
							fillerBlock = (byte)Block.stone.blockID;
						}
						else if (j >= seaLevel - (8 + rand.nextInt(2)) && j <= seaLevel + 1) {
							topBlock = ((BTABiomeGenBase) biome).topBlockExt;
							fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;

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
							topBlock = ((BTABiomeGenBase) biome).topBlockExt;
							fillerBlock = ((BTABiomeGenBase) biome).fillerBlockExt;
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

	public void generateTreesForBiome(World world, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		int numTrees = 6;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;

			WorldGenerator gen;

			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .375 > 0 && rand.nextInt(6) < 3) {
				if (rand.nextInt(5) == 0) {
					gen = new WorldGenTaiga2(false);
				}
				else if (rand.nextInt(3) == 0) {
					gen = new BTAWorldGenTaiga5(false);
				}
				else {
					gen = new BTAWorldGenTaiga7(false);
				}
			}
			else {
				if (rand.nextInt(5) == 0) {
					gen = new BTAWorldGenTaiga3(false);
				}
				else if (rand.nextInt(3) == 0) {
					gen = new BTAWorldGenTaiga4(false);
				}
				else {
					gen = new BTAWorldGenTaiga7(false);
				}
			}

			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}