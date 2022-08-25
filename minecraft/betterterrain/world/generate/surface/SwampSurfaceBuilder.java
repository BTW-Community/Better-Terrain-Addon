package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class SwampSurfaceBuilder extends NoShorelineSurfaceBuilder {
	public OpenSimplexOctaves waterNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random noiseRand = new Random(seed + 3000);

		waterNoiseGen = new OpenSimplexOctaves(rand.nextLong(), 4);
	}

	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		if (j == seaLevel - 1) {
			double waterNoiseScale = 1/96D;
			//k and i swapped because apparently I messed something up somewhere
			boolean useWater = waterNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), waterNoiseScale) + rand.nextDouble() > 0.25;

			if (useWater && surfaceType == SurfaceType.TOP) {
				return new int[] {Block.waterStill.blockID, 0};
			}
		}
		
		return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
	}
}
