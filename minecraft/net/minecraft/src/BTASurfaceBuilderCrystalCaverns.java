package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.BTASurfaceBuilder.SurfaceType;

public class BTASurfaceBuilderCrystalCaverns extends BTASurfaceBuilder {
	protected static BTAUtilsOpenSimplexOctaves amethystNoiseGen;
	protected static BTAUtilsOpenSimplexOctaves amethystNoiseGen2;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random grassRand = new Random(seed + 3000);
		
		amethystNoiseGen = new BTAUtilsOpenSimplexOctaves(grassRand.nextLong(), 2);
		amethystNoiseGen2 = new BTAUtilsOpenSimplexOctaves(grassRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		double pumiceNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useAmethyst = amethystNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		boolean useAmethyst2 = amethystNoiseGen2.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		
		if (useAmethyst && BTADecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {BTADecoIntegration.amethyst.blockID, 0};
		}
		else if (useAmethyst2 && BTADecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {BTADecoIntegration.amethyst.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
}