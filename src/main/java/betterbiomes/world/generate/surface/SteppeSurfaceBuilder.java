package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.util.WorldTypeInterface;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import deco.block.DecoBlocks;
import net.minecraft.src.*;

public class SteppeSurfaceBuilder extends NoShorelineSurfaceBuilder {
	protected static OpenSimplexOctaves grassNoiseGenSimplex;

	private Random soilRand = new Random();

	private int cacheX = -1;
	private int cacheZ = -1;
	private long cacheSeed = -1;

    @Override
    public void init(Random rand, long seed) {
        super.init(rand, seed);

		soilRand.setSeed(seed);
		long soilSeed = soilRand.nextLong();

		grassNoiseGenSimplex = new OpenSimplexOctaves(soilSeed, 4);

        Random treeRand = new Random(seed + 4000);

        this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);

        this.treeNoiseScale = 1 / 256D;
    }

	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V3_2_0)) {
			double grassNoiseScale = 1 / 64D;
			//k and i swapped because apparently I messed something up somewhere
			double soilNoise = grassNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) * 0.875;

			if (cacheX != this.chunkX * 16 + k || cacheZ != this.chunkZ * 16 + i) {
				cacheX = this.chunkX * 16 + k;
				cacheZ = this.chunkZ * 16 + i;
				cacheSeed = rand.nextLong();
			}

			soilRand.setSeed(cacheSeed);

			boolean useGrass = soilNoise + soilRand.nextDouble() * 1.75 - 1 > 0;
			boolean useCoarseDirt = soilNoise + soilRand.nextDouble() * 1.75 - 1 > 0;

			if (useGrass) {
				if (surfaceType == SurfaceType.TOP) {
					return new int[]{Block.grass.blockID, 0};
				}
				else {
					return new int[]{Block.dirt.blockID, 0};
				}
			}
			else if (useCoarseDirt && BTAMod.isDecoInstalled() && ((WorldTypeInterface) worldType).isDeco()) {
				if (surfaceType == SurfaceType.TOP) {
					return new int[]{DecoBlocks.coarseDirt.blockID, 0};
				}
				else {
					return new int[]{Block.dirt.blockID, 0};
				}
			}
		}

		return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
	}
	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		return false;
	}

	@Override
	protected boolean useGravelAtLocation(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		return false;
	}

    public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
        int numTrees = 8;

        for (int i = 0; i < numTrees; ++i) {
            int x = this.chunkX + rand.nextInt(16) + 8;
            int z = this.chunkZ + rand.nextInt(16) + 8;

            AbstractTreeGrower treeGrower;

            if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) + .5 > 0 && rand.nextInt(numTrees) == 0) {
				treeGrower = ((BTABiome) biome).getTreeGrower(rand, generatorInfo, world.provider.terrainType);
            }
            else {
                treeGrower = ((BTABiome) biome).getTreeGrower(rand, generatorInfo, world.provider.terrainType);
            }

            treeGrower.growTree(world, rand, x, world.getHeightValue(x, z), z, true);
        }
    }
}