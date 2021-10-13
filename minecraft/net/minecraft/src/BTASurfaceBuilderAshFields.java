package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.BTASurfaceBuilder.SurfaceType;

public class BTASurfaceBuilderAshFields extends BTASurfaceBuilder {
	protected static BTAUtilsOpenSimplexOctaves pumiceNoiseGen;
	protected static BTAUtilsOpenSimplexOctaves pumiceNoiseGen2;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random pumiceRand = new Random(seed + 3000);
		
		pumiceNoiseGen = new BTAUtilsOpenSimplexOctaves(pumiceRand.nextLong(), 2);
		pumiceNoiseGen2 = new BTAUtilsOpenSimplexOctaves(pumiceRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		double pumiceNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean usePumice = pumiceNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		boolean usePumice2 = pumiceNoiseGen2.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		
		if (usePumice && BTADecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {BTADecoIntegration.pumice.blockID, 0};
		}
		else if (usePumice2 && BTADecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {BTADecoIntegration.pumice.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
}