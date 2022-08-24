package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.WorldType;

public class HotSpringsSurfaceBuilder extends NoShorelineSurfaceBuilder {
	public OpenSimplexOctaves springsNoiseGen;
	protected OpenSimplexOctaves coarseDirtNoiseGen;

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
		
		if (useCoarseDirt && DecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType == SurfaceType.TOP) {
			return new int[] {DecoIntegration.coarseDirt.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}

	@Override
	protected int getSoilDepth(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		int depth = (int) (soilDepthNoiseLegacy[k * 16 + i] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		
		if (depth < 1)
			depth = 1;
		
		return depth;
	}
}
