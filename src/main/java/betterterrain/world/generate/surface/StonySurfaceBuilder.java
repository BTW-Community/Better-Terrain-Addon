package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import deco.block.DecoBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class StonySurfaceBuilder extends SurfaceBuilder {
	protected static OpenSimplexOctaves stoneNoiseGenSimplex;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random stoneRand = new Random(seed + 1000);
		
		stoneNoiseGenSimplex = new OpenSimplexOctaves(stoneRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double stoneNoiseScale = 1/16D;
		boolean useStone = stoneNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), stoneNoiseScale) - 0.625 > 0;
		
		boolean sandOrGravel = (this.useSandAtLocation(i, k, rand) || this.useGravelAtLocation(i, k, rand, generatorInfo)) && surfaceJ <= seaLevel + 1;
		
		if (useStone && BTAMod.isDecoInstalled() && worldType.isDeco() && !sandOrGravel) {
			return new int[] {Block.stone.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
	
	public void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;

		float temperature = biome.getFloatTemperature();

		double sandNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useSand = sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), sandNoiseScale) + rand.nextDouble() * 0.2D > 0;
		
		double grassNoiseScale = 1/16D;
		boolean useStone = stoneNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) - 0.625 > 0;
		
		boolean useGravel = this.gravelNoise[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
		int soilDepthNoiseSample = (int)(this.soilDepthNoiseLegacy[i + k * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int remaingDepth = -1;
		int topBlock;
		int fillerBlock;

		topBlock = ((BTABiome) biome).topBlockExt;
		fillerBlock = ((BTABiome) biome).fillerBlockExt;

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
							if (biome instanceof BTABiome) {
								topBlock = ((BTABiome) biome).topBlockExt;
								fillerBlock = ((BTABiome) biome).fillerBlockExt;
							}
							else {
								topBlock = biome.topBlock;
								fillerBlock = biome.fillerBlock;
							}
						}
						
						if (useStone && topBlock == Block.grass.blockID && j >= seaLevel - 1) {
							topBlock = Block.stone.blockID;
							fillerBlock = Block.stone.blockID;
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
						else if (BTAMod.isDecoInstalled() && remaingDepth == 0 && fillerBlock == DecoBlocks.legacyRedSand.blockID) {
							remaingDepth = rand.nextInt(4);
							fillerBlock = DecoBlocks.redSandstone.blockID;
						}
					}
				}
			}
		}
	}
}