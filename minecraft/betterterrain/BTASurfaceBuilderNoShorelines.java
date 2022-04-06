package betterterrain;

import java.util.Random;

import betterterrain.BTASurfaceBuilder.SurfaceType;

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