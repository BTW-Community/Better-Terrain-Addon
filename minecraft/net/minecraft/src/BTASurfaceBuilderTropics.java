package net.minecraft.src;

import java.util.Random;

public class BTASurfaceBuilderTropics extends BTASurfaceBuilder {
	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		return true;
	}
	
	@Override
	protected boolean useGravelAtLocation(int i, int k, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		return false;
	}
}