package betterbiomes.world.generate.surface;

import java.util.ArrayList;
import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.util.WorldTypeInterface;
import btw.util.ColorUtils;
import deco.block.DecoBlocks;
import deco.block.blocks.StoneVariantsBlock;
import deco.block.util.SandHelper;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldType;

public class HotSpringsSurfaceBuilder extends NoShorelineSurfaceBuilder {
	public OpenSimplexOctaves springsNoiseGen;
	protected OpenSimplexOctaves coarseDirtNoiseGen;

	private final boolean generateSprings;

	private static ArrayList<Integer> springBiomes = new ArrayList();

	public HotSpringsSurfaceBuilder(int biomeID, boolean generateSprings) {
		this.generateSprings = generateSprings;
		springBiomes.add(biomeID);
	}

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random noiseRand = new Random(seed + 2500);

		springsNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 6);
		coarseDirtNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 4);
	}

	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double grassNoiseScale = 1/48D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useCoarseDirt = coarseDirtNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.1D - .25 > 0;

		if (useCoarseDirt && BTAMod.isDecoInstalled() && ((WorldTypeInterface) worldType).isDeco() && surfaceType == SurfaceType.TOP) {
			return new int[] {DecoBlocks.coarseDirt.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}

	@Override
	public void decorateSurface(World world, Random rand, BTABiome biome, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		if (this.generateSprings) {
			for (int i = chunkX + 8; i < chunkX + 24; i++) {
				for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
					if (!this.springBiomes.contains(world.getBiomeGenForCoords(i, k).biomeID)) {
						continue;
					}

					double waterNoiseScale = 1/192D;
					double waterNoise = springsNoiseGen.noise2(i, k, waterNoiseScale);

					int previousBlockID = -1;
					int thisBlockID = -1;
					int nextBlockID = -1;

					int depth = this.getSoilDepth(i - chunkX - 8, k - chunkZ - 8, rand, generatorOptions);

					for (int j = 60; j < 128; j++) {
						if (previousBlockID == -1) {
							previousBlockID = world.getBlockId(i, j - 1, k);
							thisBlockID = world.getBlockId(i, j, k);
							nextBlockID = world.getBlockId(i, j + 1, k);
						}
						else {
							previousBlockID = thisBlockID;
							thisBlockID = nextBlockID;
							nextBlockID = world.getBlockId(i, j + 1, k);
						}

						if (nextBlockID != 0 && Block.blocksList[nextBlockID].blockMaterial.isReplaceable() && Block.blocksList[nextBlockID].blockMaterial != Material.water) {
							world.setBlockToAir(i, j + 1, k);
							nextBlockID = 0;
						}

						if (previousBlockID != 0 && previousBlockID != Block.waterStill.blockID && 
								(thisBlockID == ((BTABiome) this.biome).topBlockExt || thisBlockID == DecoBlocks.stainedTerracotta.blockID || thisBlockID == DecoBlocks.coarseDirt.blockID) &&
								nextBlockID == 0)
						{
							if (waterNoise > 0.825) {
								int numBlockNeighbors = 8;

								for (int offsetI = -1; offsetI <= 1; offsetI++) {
									for (int offsetK = -1; offsetK <= 1; offsetK++) {
										if (offsetI == 0 && offsetK == 0) {
											continue;
										}

										int neighborID = world.getBlockId(i + offsetI, j, k + offsetK);
										int neighborAboveID = world.getBlockId(i + offsetI, j + 1, k + offsetK);

										if (neighborID == 0 || neighborAboveID != 0) {
											numBlockNeighbors--;
										}
									}
								}

								if (numBlockNeighbors == 8) {
									world.setBlock(i, j, k, Block.waterStill.blockID);
									fillToDepth(world, i, j - 1, k, depth, DecoBlocks.stainedTerracotta.blockID, ColorUtils.YELLOW.colorID);
								}
								else {
									fillToDepth(world, i, j, k, depth + 1, Block.sand.blockID, SandHelper.RED_SAND_TYPE);
								}

								for (int offsetI = -1; offsetI <= 1; offsetI++) {
									for (int offsetK = -1; offsetK <= 1; offsetK++) {
										if (offsetI == 0 && offsetK == 0) {
											break;
										}

										if (springsNoiseGen.noise2(i + offsetI, k + offsetK, waterNoiseScale) <= 0.825) {
											fillToDepth(world, i + offsetI, j, k + offsetK, 4, Block.sand.blockID, SandHelper.RED_SAND_TYPE);
										}
									}
								}
							}
							else if (waterNoise > 0.75) {
								fillToDepth(world, i, j, k, depth + 1, Block.sand.blockID, SandHelper.RED_SAND_TYPE);
							}
							else if (waterNoise > 0.625) {
								fillToDepth(world, i, j, k, depth + 1, DecoBlocks.infusedStone.blockID, 0);
							}
							else if (waterNoise > 0.5) {
								fillToDepth(world, i, j, k, depth + 1, DecoBlocks.basalt.blockID, 0);
							}
							else if (waterNoise > 0.375) {
								fillToDepth(world, i, j, k, depth + 1, Block.gravel.blockID, 0);
							}
							else if (waterNoise > 0.125) {
								fillToDepth(world, i, j, k, depth + 1, DecoBlocks.stoneVariants.blockID,
										StoneVariantsBlock.CALCITE_TYPE);
							}
						}
					}
				}
			}

			for (int i = chunkX + 8; i < chunkX + 24; i++) {
				for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
					for (int j = 60; j < 128; j++) {
						if (world.getBlockId(i, j, k) == Block.sand.blockID) {
							for (int offsetI = -1; offsetI <= 1; offsetI++) {
								for (int offsetK = -1; offsetK <= 1; offsetK++) {
									if (offsetI == 0 && offsetK == 0) {
										break;
									}

									int blockID = world.getBlockId(i + offsetI, j, k + offsetK);

									if (blockID != 0 && Block.blocksList[blockID].blockMaterial == Material.water) {
										fillToDepth(world, i, j, k, 1, DecoBlocks.stainedTerracotta.blockID, ColorUtils.YELLOW.colorID);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void fillToDepth(World world, int x, int y, int z, int depth, int blockID, int metadata) {
		for (int i = 0; i < depth; i++) {
			if (world.getBlockId(x, y - i, z) != 0) {
				world.setBlockAndMetadata(x, y - i, z, blockID, metadata);
			}
		}
	}
}
