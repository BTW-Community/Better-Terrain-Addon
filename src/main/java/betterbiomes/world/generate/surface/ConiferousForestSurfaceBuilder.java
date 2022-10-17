package betterbiomes.world.generate.surface;

import java.util.Random;

import betterbiomes.feature.tree.TaigaGen3;
import betterbiomes.feature.tree.TaigaGen4;
import betterterrain.BTAMod;
import betterterrain.feature.tree.TaigaGen5;
import betterterrain.feature.tree.TaigaGen7;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import deco.block.DecoBlocks;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;

public class ConiferousForestSurfaceBuilder extends SurfaceBuilder {
	protected static OpenSimplexOctaves coarseDirtNoiseGenSimplex;
	protected static OpenSimplexOctaves podzolNoiseGenSimplex;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random soilRand = new Random(seed);

		coarseDirtNoiseGenSimplex = new OpenSimplexOctaves(soilRand.nextLong(), 4);
		podzolNoiseGenSimplex = new OpenSimplexOctaves(soilRand.nextLong(), 4);

		this.treeNoiseGen = new OpenSimplexOctaves(soilRand.nextLong(), 2);

		this.treeNoiseScale = 1/256D;
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double grassNoiseScale = 1/96D;
		//k and i swapped because apparently I messed something up somewhere
		boolean usePodzol = coarseDirtNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.1D - .25 > 0;
		boolean useCoarseDirt = podzolNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.1D - .25 > 0;
		
		boolean sandOrGravel = (this.useSandAtLocation(i, k, rand) || this.useGravelAtLocation(i, k, rand, generatorInfo)) && surfaceJ <= seaLevel + 1;
		
		if (useCoarseDirt && BTAMod.isDecoInstalled() && (worldType).isDeco() && surfaceType == SurfaceType.TOP && !sandOrGravel) {
			return new int[] {DecoBlocks.coarseDirt.blockID, 0};
		}
		else if (usePodzol && BTAMod.isDecoInstalled() && (worldType).isDeco() && surfaceType == SurfaceType.TOP && !sandOrGravel) {
			return new int[] {DecoBlocks.podzol.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}

	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
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
					gen = new TaigaGen5(false);
				}
				else {
					gen = new TaigaGen7(false);
				}
			}
			else {
				if (rand.nextInt(5) == 0) {
					gen = new TaigaGen3(false);
				}
				else if (rand.nextInt(3) == 0) {
					gen = new TaigaGen4(false);
				}
				else {
					gen = new TaigaGen7(false);
				}
			}

			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}