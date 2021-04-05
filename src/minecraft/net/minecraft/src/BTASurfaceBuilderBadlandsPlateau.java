package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class BTASurfaceBuilderBadlandsPlateau extends BTASurfaceBuilder {
	private static ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	private static int[] metaLocations;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
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
			metaLocations = new int[128];
			
			for (int i = 0; i < metaLocations.length; i++) {
				int meta = rand.nextInt(16);
				
				if (allowedTerracottaMetadata.contains(meta)) {
					metaLocations[i] = meta;
				}
				else {
					metaLocations[i] = -1;
				}
			}
		}
	}
	
	private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

	public void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, BTAWorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();
		boolean useSand = this.sandNoise[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
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
						
						if (blockArray[index] == BTADecoIntegration.terracotta.blockID && metaLocations[j & 15] != -1) {
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