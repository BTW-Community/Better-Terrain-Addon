package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.opensimplex2.OpenSimplex2F;

public class BTASurfaceBuilderOutback extends BTASurfaceBuilder {
	protected static double[] grassNoise = new double[256];
	protected static BTABetaNoiseOctaves grassNoiseGen;
	protected static OpenSimplex2F grassNoiseGenSimplex;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		if (grassNoiseGen == null)
			grassNoiseGen = new BTABetaNoiseOctaves(rand, 4);
		
		if (grassNoiseGenSimplex == null)
			grassNoiseGenSimplex = new OpenSimplex2F(seed);
	}
	
	@Override
	public void initForChunk(int chunkX, int chunkZ) {
		super.initForChunk(chunkX, chunkZ);

		double grassScalar = 0.625D;
		this.grassNoise = this.grassNoiseGen.generateNoiseOctaves(this.grassNoise, (double)(chunkX * 16), (double)(chunkZ * 16), 0.0D, 16, 16, 1, grassScalar, grassScalar, 1.0D);
	}
	
	public void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();
		//boolean useGrass = this.grassNoise[i + k * 16] + 0.5D > 0;
		double grassNoiseScale = 0.03125D;
		boolean useGrass = grassNoiseGenSimplex.noise2((this.chunkX * 16 + k) * grassNoiseScale, (this.chunkZ * 16 + i) * grassNoiseScale) > 0;
		
		boolean useGravel = this.gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(this.soilDepthNoise[i + k * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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
						if (soilDepthNoiseSample <= 0) {
							topBlock = 0;
							fillerBlock = (byte)Block.stone.blockID;
						}
						else if (j >= seaLevel - (8 + rand.nextInt(2)) && j <= seaLevel + 1) {
							topBlock = (byte)Block.sand.blockID;
							fillerBlock = (byte)Block.sand.blockID;

							if (generatorInfo.generatePerlinBeaches()) {
								if (useGravel) {
									topBlock = 0;
									fillerBlock = Block.gravel.blockID;
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
						
						if (useGrass) {
							topBlock = Block.grass.blockID;
							fillerBlock = Block.dirt.blockID;
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
}