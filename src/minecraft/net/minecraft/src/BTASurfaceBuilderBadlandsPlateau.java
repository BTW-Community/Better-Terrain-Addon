package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class BTASurfaceBuilderBadlandsPlateau extends BTASurfaceBuilder {
	private static ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	private static int[] metaLocations;

	@Override
	public void init(Random rand) {
		super.init(rand);
		
		//Init terracotta striping data - done statically in case multiple badlands variants use this surface builder
		if (allowedTerracottaMetadata.size() == 0) {
			allowedTerracottaMetadata.add(1);
			allowedTerracottaMetadata.add(3);
			allowedTerracottaMetadata.add(7);
			allowedTerracottaMetadata.add(11);
			allowedTerracottaMetadata.add(14);
			allowedTerracottaMetadata.add(15);
		}

		if (metaLocations == null) {
			metaLocations = new int[16];
			
			for (int i = 0; i < 16; i++) {
				metaLocations[i] = i;
			}
			
			//Randomizes the locations of the stripes based on the seed
			for (int i = metaLocations.length; i > 1; i--) {
	            swap(metaLocations, i - 1, rand.nextInt(i));
	        }
		}
	}
	
	private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

	public void replaceBlocksForBiome(Random rand, int x, int z, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;
		double soilDepthNoiseScalar = 0.03125D;
		this.sandNoise = this.sandNoiseGen.generateNoiseOctaves(this.sandNoise, x, z, 0.0D, 1, 1, 1, soilDepthNoiseScalar, soilDepthNoiseScalar, 1.0D);
		this.gravelNoise = this.sandNoiseGen.generateNoiseOctaves(this.gravelNoise, x, 109.0134D, z, 1, 1, 1, soilDepthNoiseScalar, 1.0D, soilDepthNoiseScalar);
		this.soilDepthNoise = this.soilDepthNoiseGen.generateNoiseOctaves(this.soilDepthNoise, x, z, 0, 1, 1, 1, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D);

		int i = x & 15;
		int k = z & 15;

		float temperature = biome.getFloatTemperature();
		boolean useSand = this.sandNoise[0] + rand.nextDouble() * 0.2D > 0.0D;
		boolean useGravel = this.gravelNoise[0] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(this.soilDepthNoise[0] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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

							if (generatorInfo.generatePerlinBeaches() && BTABiomeConfiguration.shouldBiomeSpawnPerlinBeach(biome.biomeID)) {
								if (useGravel) {
									topBlock = 0;
									fillerBlock = Block.gravel.blockID;
								}

								if (useSand) {
									topBlock = BTADecoIntegration.redSand.blockID;
									fillerBlock = BTADecoIntegration.redSand.blockID;
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

						remaingDepth = soilDepthNoiseSample + 10;

						if (j >= seaLevel - 1) {
							blockArray[index] = topBlock;
						}
						else {
							blockArray[index] = fillerBlock;
						}
						
						if (blockArray[index] == BTADecoIntegration.terracotta.blockID && allowedTerracottaMetadata.contains(metaLocations[j & 15])) {
							blockArray[index] = BTADecoIntegration.stainedTerracotta.blockID;
							metaArray[index] = metaLocations[j & 15];
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