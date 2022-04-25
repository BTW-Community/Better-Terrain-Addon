package betterterrain.world.generate.surface;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.WorldType;

public class AshFieldsSurfaceBuilder extends NetherSurfaceBuilder {
	protected static OpenSimplexOctaves pumiceNoiseGen;
	protected static OpenSimplexOctaves pumiceNoiseGen2;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random pumiceRand = new Random(seed + 3000);
		
		pumiceNoiseGen = new OpenSimplexOctaves(pumiceRand.nextLong(), 2);
		pumiceNoiseGen2 = new OpenSimplexOctaves(pumiceRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, boolean isReversed, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double pumiceNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean usePumice = pumiceNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		boolean usePumice2 = pumiceNoiseGen2.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		
		if (usePumice && DecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {DecoIntegration.pumice.blockID, 0};
		}
		else if (usePumice2 && DecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {DecoIntegration.pumice.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
}