package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.world.WorldConfigurationInfo;

public class TropicsSurfaceBuilder extends SurfaceBuilder {
	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		return true;
	}
	
	@Override
	protected boolean useGravelAtLocation(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		return false;
	}
}