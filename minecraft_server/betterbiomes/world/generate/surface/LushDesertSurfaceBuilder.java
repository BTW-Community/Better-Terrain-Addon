package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.BTAVersion;
import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import net.minecraft.src.Block;
import net.minecraft.src.FCUtilsColor;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class LushDesertSurfaceBuilder extends NoShorelineSurfaceBuilder {
	public OpenSimplexOctaves springsNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random noiseRand = new Random(seed + 2500);

		springsNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 6);
	}

	@Override
	public void decorateSurface(World world, Random rand, BTABiome biome, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		if (generatorOptions.getBTAVersion().isVersionAtLeast(BTAVersion.V3_0_0)) {
			for (int i = chunkX + 8; i < chunkX + 24; i++) {
				for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
					if (world.getBiomeGenForCoords(i, k) != this.biome) {
						continue;
					}
					
					double waterNoiseScale = 1/192D;
					double waterNoise = springsNoiseGen.noise2(i, k, waterNoiseScale);

					int previousBlockID = -1;
					int thisBlockID = -1;
					int nextBlockID = -1;

					int depth = this.getSoilDepth(i - chunkX - 8, k - chunkZ - 8, rand, generatorOptions);

					for (int j = 60; j < 65; j++) {
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
								(thisBlockID == ((BTABiome) this.biome).topBlockExt || thisBlockID == Block.grass.blockID) && 
								nextBlockID == 0)
						{
							if (waterNoise > 0.125) {
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
									fillToDepth(world, i, j - 1, k, depth, Block.dirt.blockID, 0);
								}
								else {
									world.setBlock(i, j, k, Block.grass.blockID);
									fillToDepth(world, i, j - 1, k, depth, Block.dirt.blockID, 0);
								}

								for (int offsetI = -1; offsetI <= 1; offsetI++) {
									for (int offsetK = -1; offsetK <= 1; offsetK++) {
										if (offsetI == 0 && offsetK == 0) {
											break;
										}

										if (springsNoiseGen.noise2(i + offsetI, k + offsetK, waterNoiseScale) <= 0.825) {
											world.setBlock(i + offsetI, j, k + offsetK, Block.grass.blockID);
											fillToDepth(world, i + offsetI, j - 1, k + offsetK, depth, Block.dirt.blockID, 0);
										}
									}
								}
							}
							else if (waterNoise > 0) {
								world.setBlock(i, j, k, Block.grass.blockID);
								fillToDepth(world, i, j - 1, k, depth, Block.dirt.blockID, 0);
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