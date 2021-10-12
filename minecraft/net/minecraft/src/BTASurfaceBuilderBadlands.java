package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.BTASurfaceBuilder.SurfaceType;

public class BTASurfaceBuilderBadlands extends BTASurfaceBuilder {
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		int blockID = getDefaultSurfaceBlock(i, k, surfaceType);
		return new int[] {blockID, 0};
	}
}