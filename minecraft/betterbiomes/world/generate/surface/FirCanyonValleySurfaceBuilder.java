package betterbiomes.world.generate.surface;

import java.util.ArrayList;
import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class FirCanyonValleySurfaceBuilder extends NoShorelineSurfaceBuilder {
	protected static OpenSimplexOctaves coarseDirtNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random noiseRand = new Random(seed + 100);
		
		coarseDirtNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 4);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double dirtNoiseScale = 1/64D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useCoarseDirt = coarseDirtNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), dirtNoiseScale) + rand.nextDouble() * 0.15D > -0.125;
		
		if (useCoarseDirt && DecoIntegration.isDecoInstalled() && worldType.isDeco()) {
			return new int[] {DecoIntegration.coarseDirt.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
}