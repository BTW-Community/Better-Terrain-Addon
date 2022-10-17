package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import btw.util.ColorUtils;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class IvoryHillsSurfaceBuilder extends SurfaceBuilder {
	private static int[] metaLocations;
	
	protected OpenSimplexOctaves grassNoiseGen;
	protected OpenSimplexOctaves coarseDirtNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random metaRand = new Random(seed);

		if (metaLocations == null) {
			metaLocations = new int[128];

			for (int i = 0; i < metaLocations.length; i++) {
				switch (metaRand.nextInt(3)) {
				case 0:
					metaLocations[i] = ColorUtils.BROWN.colorID;
					break;
				case 1:
					metaLocations[i] = ColorUtils.BLACK.colorID;
					break;
				default:
					metaLocations[i] = ColorUtils.WHITE.colorID;
				}
			}
		}

		grassNoiseGen = new OpenSimplexOctaves(metaRand.nextLong(), 4);
		coarseDirtNoiseGen = new OpenSimplexOctaves(metaRand.nextLong(), 4);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double dirtNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useCoarseDirt = coarseDirtNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), dirtNoiseScale) + rand.nextDouble() * 0.15D > -0.125;
		
		useCoarseDirt = useCoarseDirt && j >= 85;
		
		if (useCoarseDirt) {
			double grassNoiseScale = 1/48D;
			//k and i swapped because apparently I messed something up somewhere
			boolean useGrass = grassNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.15D > -0.125;
			
			if (surfaceType == SurfaceType.TOP) {
				if (useGrass) {
					if (j >= seaLevel - 1) {
						return new int[] {Block.grass.blockID, 0};
					}
					else {
						return new int[] {Block.dirt.blockID, 0};
					}
				}
				else {
					return new int[] {DecoBlocks.coarseDirt.blockID, 0};
				}
			}
		}
		
		if (!useCoarseDirt || surfaceType != SurfaceType.TOP) {
			int blockID = DecoBlocks.terracotta.blockID;
			int metadata = 0;
			
			if (blockID == DecoBlocks.terracotta.blockID && metaLocations[j & 15] != -1) {
				blockID = DecoBlocks.stainedTerracotta.blockID;
				metadata = metaLocations[j & 15];
			}
			
			return new int[] {blockID, metadata};
		}
		
		//Make the compiler happy
		return null;
	}
	
	@Override
	protected int getSubsurfaceDepth(Random rand) {
		return 10;
	}
}