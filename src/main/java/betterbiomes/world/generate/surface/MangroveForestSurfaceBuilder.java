package betterbiomes.world.generate.surface;

import betterterrain.BTAMod;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.util.WorldTypeInterface;
import deco.block.DecoBlocks;
import net.minecraft.src.WorldType;

import java.util.Random;

public class MangroveForestSurfaceBuilder extends NoShorelineSurfaceBuilder {
    protected static OpenSimplexOctaves mudNoiseGen;

    @Override
    public void init(Random rand, long seed) {
        super.init(rand, seed);

        Random grassRand = new Random(seed + 3000);

        mudNoiseGen = new OpenSimplexOctaves(grassRand.nextLong(), 2);
    }

    @Override
    protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
        double grassNoiseScale = 1/256D;
        //k and i swapped because apparently I messed something up somewhere
        boolean useGrass = mudNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextFloat() * 0.1F > 0;

        if (useGrass && BTAMod.isDecoInstalled() && ((WorldTypeInterface) worldType).isDeco()) {
            return new int[] {DecoBlocks.mud.blockID, 0};
        }

        return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
    }
}
