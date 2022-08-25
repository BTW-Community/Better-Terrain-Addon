package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.feature.plant.DecoFlowerGen;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.DecoDefs;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldType;

public class SwampSurfaceBuilder extends NoShorelineSurfaceBuilder {
	private DecoFlowerGen orchidGen;
	private TallGrassGen fernGen;

	protected OpenSimplexOctaves waterNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random noiseRand = new Random(seed + 3000);

		waterNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 4);

		if (DecoIntegration.isDecoInstalled()) {
			orchidGen = new DecoFlowerGen(DecoIntegration.flower.blockID, 8);
		}

		fernGen = new TallGrassGen(Block.tallGrass.blockID, 2);
	}

	@Override
	public void decorateSurface(World world, Random rand, BTABiome biome, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		double waterNoiseScale = 1/96D;

		for (int i = chunkX + 8; i < chunkX + 24; i++) {
			for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
				double waterNoise = waterNoiseGen.noise2(i, k, waterNoiseScale);

				int j = 62;

				int previousBlockID = world.getBlockId(i, j - 1, k);
				int thisBlockID = world.getBlockId(i, j, k);
				int nextBlockID = world.getBlockId(i, j + 1, k);

				if (nextBlockID != 0 && Block.blocksList[nextBlockID].blockMaterial.isReplaceable() && Block.blocksList[nextBlockID].blockMaterial != Material.water) {
					world.setBlockToAir(i, j + 1, k);
					nextBlockID = 0;
				}

				if (previousBlockID != 0 && previousBlockID != Block.waterStill.blockID && 
						(thisBlockID == biome.topBlockExt || thisBlockID == Block.blockClay.blockID || (DecoIntegration.isDecoInstalled() && thisBlockID == DecoDefs.podzol.blockID)) && 
						nextBlockID == 0)
				{
					if (waterNoise + rand.nextDouble() > 0.375) {
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

							for (int offset = -1; offset <= 1; offset += 2) {
								int blockID = world.getBlockId(i + offset, j, k);

								if (DecoIntegration.isDecoInstalled() && world.provider.terrainType.isDeco()) {
									if (blockID != 0 && Block.blocksList[blockID].blockMaterial != Material.water) {
										world.setBlock(i + offset, j, k, DecoIntegration.podzol.blockID);
									}

									blockID = world.getBlockId(i, j, k + offset);

									if (blockID != 0 && Block.blocksList[blockID].blockMaterial != Material.water) {
										world.setBlock(i, j, k + offset, DecoIntegration.podzol.blockID);
									}
								}
							}
						}
					}

					if (DecoIntegration.isDecoInstalled() && world.provider.terrainType.isDeco() &&
							waterNoise + rand.nextDouble() * 0.1 > -0.5
							&& world.getBlockId(i, j, k) != 0 && Block.blocksList[world.getBlockId(i, j, k)].blockMaterial != Material.water
							&& world.getBlockId(i, j + 1, k) == 0)
					{
						world.setBlock(i, j, k, DecoIntegration.podzol.blockID);
					}
				}
			}
		}

		int x = chunkX + 16;
		int y = world.getTopSolidOrLiquidBlock(chunkX + 16, chunkZ + 16);
		int z = chunkZ + 16;

		if (y == 63)
		{
			if (DecoIntegration.isDecoInstalled() && world.provider.terrainType.isDeco() && rand.nextInt(2) == 0) {
				x = chunkX + rand.nextInt(16) + 8;
				z = chunkZ + rand.nextInt(16) + 8;
				this.orchidGen.generate(world, rand, x, y, z);
			}

			for (int i = 0; i < 5; i++) {
				x = chunkX + rand.nextInt(16) + 8;
				z = chunkZ + rand.nextInt(16) + 8;
				this.fernGen.generate(world, rand, x, y, z);
			}
		}
	}
}
