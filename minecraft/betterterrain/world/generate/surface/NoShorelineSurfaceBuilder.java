package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;

public class NoShorelineSurfaceBuilder extends SurfaceBuilder {
	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		return false;
	}
	
	@Override
	protected boolean useGravelAtLocation(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		return false;
	}
}