package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.world.feature.plant.DecoFlowerGen;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.util.WorldTypeInterface;
import deco.block.DecoBlocks;
import deco.block.util.FlowerHelper;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class SwampSurfaceBuilder extends NoShorelineSurfaceBuilder {
	private DecoFlowerGen orchidGen;
	private TallGrassGen fernGen;

	protected OpenSimplexOctaves waterNoiseGen;

	protected boolean generateFlora;

	public SwampSurfaceBuilder() {
		this(true);
	}

	public SwampSurfaceBuilder(boolean generateFlora) {
		this.generateFlora = generateFlora;
	}

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		waterNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 4);

		if (BTAMod.isDecoInstalled()) {
			orchidGen = new DecoFlowerGen(DecoBlocks.flower.blockID, FlowerHelper.BLUE_ORCHTYPE_TYPE);
		}

		fernGen = new TallGrassGen(Block.tallGrass.blockID, 2);
	}

	@Override
	public void decorateSurface(World world, Random rand, BTABiome biome, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		double waterNoiseScale = 1/96D;

		for (int i = chunkX + 8; i < chunkX + 24; i++) {
			for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
				if (world.getBiomeGenForCoords(i, k).biomeID != this.biome.biomeID) {
					continue;
				}
				
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
						(thisBlockID == biome.topBlockExt || thisBlockID == Block.blockClay.blockID || (BTAMod.isDecoInstalled() && thisBlockID == DecoBlocks.podzol.blockID)) &&
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

								if (BTAMod.isDecoInstalled() && ((WorldTypeInterface) world.provider.terrainType).isDeco()) {
									if (blockID != 0 && Block.blocksList[blockID].blockMaterial != Material.water && blockID != Block.ice.blockID) {
										world.setBlock(i + offset, j, k, DecoBlocks.podzol.blockID);
									}

									blockID = world.getBlockId(i, j, k + offset);

									if (blockID != 0 && Block.blocksList[blockID].blockMaterial != Material.water && blockID != Block.ice.blockID) {
										world.setBlock(i, j, k + offset, DecoBlocks.podzol.blockID);
									}
								}
							}
						}
					}

					if (BTAMod.isDecoInstalled() && ((WorldTypeInterface) world.provider.terrainType).isDeco() &&
							waterNoise + rand.nextDouble() * 0.1 > -0.5
							&& world.getBlockId(i, j, k) != 0 && Block.blocksList[world.getBlockId(i, j, k)].blockMaterial != Material.water && world.getBlockId(i, j, k) != Block.ice.blockID
							&& world.getBlockId(i, j + 1, k) == 0)
					{
						world.setBlock(i, j, k, DecoBlocks.podzol.blockID);
					}
				}
			}
		}

		if (generateFlora) {
			int x = chunkX + 16;
			int y = world.getTopSolidOrLiquidBlock(chunkX + 16, chunkZ + 16);
			int z = chunkZ + 16;

			if (y == 63) {
				for (int i = 0; i < 5; i++) {
					x = chunkX + rand.nextInt(16) + 8;
					z = chunkZ + rand.nextInt(16) + 8;
					this.fernGen.generate(world, rand, x, y, z);
				}
			}

			if (BTAMod.isDecoInstalled() && ((WorldTypeInterface) world.provider.terrainType).isDeco() && rand.nextInt(2) == 0) {
				x = chunkX + rand.nextInt(16) + 8;
				z = chunkZ + rand.nextInt(16) + 8;
				this.orchidGen.generate(world, rand, x, y, z);
			}
		}
	}
}
