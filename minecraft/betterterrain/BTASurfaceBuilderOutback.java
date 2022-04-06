package betterterrain;

import java.util.Random;

import betterterrain.BTASurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;
import opensimplex2.OpenSimplex2F;

public class BTASurfaceBuilderOutback extends BTASurfaceBuilder {
	protected static BTAUtilsOpenSimplexOctaves grassNoiseGenSimplex;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random grassRand = new Random(seed + 3000);
		
		grassNoiseGenSimplex = new BTAUtilsOpenSimplexOctaves(grassRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, BTAWorldConfigurationInfo generatorInfo, WorldType worldType) {
		double grassNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useGrass = grassNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) > 0;
		
		boolean useGravel = this.useGravelAtLocation(i, k, rand, generatorInfo) && surfaceJ <= seaLevel + 1;
		
		if (useGrass && BTADecoIntegration.isDecoInstalled() && worldType.isDeco() && !useGravel) {
			if (surfaceType == SurfaceType.TOP) {
				return new int[] {Block.grass.blockID, 0};
			}
			else if (surfaceType == SurfaceType.FILLER) {
				return new int[] {Block.dirt.blockID, 0};
			}
		}
		
		return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
	}
	
	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		return false;
	}
}