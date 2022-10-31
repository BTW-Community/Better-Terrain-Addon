package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SurfaceBuilder;

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