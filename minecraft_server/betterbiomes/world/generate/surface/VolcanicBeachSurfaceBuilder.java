package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class VolcanicBeachSurfaceBuilder extends SurfaceBuilder {
	protected OpenSimplexOctaves basaltNoiseGen;
	protected OpenSimplexOctaves grassNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random noiseRand = new Random(seed + 100);

		basaltNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 4);
		grassNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 4);
	}

	/**
	 * Gets the block to use for the surface layer for this biome
	 * @param i Local x value for this chunk
	 * @param y Absolute y value
	 * @param k Local z value for this chunk
	 * @param isTopBlock Whether to return the top block or the filler block
	 * @param rand
	 * @param generatorInfo
	 * @param worldType
	 * @return An int array of blockID, metadata
	 */
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		int blockID = -1;
		int metadata = 0;

		double basaltNoiseScale = 1/128D;
		double grassNoiseScale = 1/64D;

		boolean useGrass = grassNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() > 1.75;

		if (!useGrass) {
			double basaltNoise = basaltNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), basaltNoiseScale) + rand.nextDouble() * 0.5D;

			if (basaltNoise < -0.75) {
				blockID = DecoIntegration.stoneTypes.blockID;
				metadata = 1;
			}
			else if (basaltNoise < 0.125 || basaltNoise > 1) {
				blockID = DecoIntegration.basalt.blockID;
			}
			else {
				blockID = DecoIntegration.infusedStone.blockID;
			}
		}

		if (blockID == -1) {
			if (j < seaLevel - 1 && surfaceType == surfaceType.TOP) {
				surfaceType = SurfaceType.FILLER;
			}

			blockID = getDefaultSurfaceBlock(i, k, surfaceType, Block.stone.blockID);
		}

		return new int[] {blockID, metadata};
	}
}
