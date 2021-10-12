package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.BTASurfaceBuilder.SurfaceType;

public class BTASurfaceBuilderNoShorelines extends BTASurfaceBuilder {
	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		return false;
	}
	
	@Override
	protected boolean useGravelAtLocation(int i, int k, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		return false;
	}
}