package betterbiomes.world.generate.surface;

import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.StonySurfaceBuilder;

import java.util.Random;

public class ShieldSurfaceBuilder extends StonySurfaceBuilder {
    @Override
    protected boolean useSandAtLocation(int i, int k, Random rand) {
        return false;
    }

    @Override
    protected boolean useGravelAtLocation(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
        return false;
    }
}
