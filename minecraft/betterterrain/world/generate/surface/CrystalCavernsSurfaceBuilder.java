package betterterrain.world.generate.surface;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class CrystalCavernsSurfaceBuilder extends NetherSurfaceBuilder {
	protected static OpenSimplexOctaves amethystNoiseGen;
	protected static OpenSimplexOctaves amethystNoiseGen2;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random grassRand = new Random(seed + 3000);
		
		amethystNoiseGen = new OpenSimplexOctaves(grassRand.nextLong(), 2);
		amethystNoiseGen2 = new OpenSimplexOctaves(grassRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, boolean isReversed, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double pumiceNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useAmethyst = amethystNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		boolean useAmethyst2 = amethystNoiseGen2.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), pumiceNoiseScale) > 0.2;
		
		if (useAmethyst && DecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {DecoIntegration.amethyst.blockID, 0};
		}
		else if (useAmethyst2 && DecoIntegration.isDecoInstalled() && worldType.isDeco() && surfaceType != SurfaceType.SUBFILLER) {
			return new int[] {DecoIntegration.amethyst.blockID, 0};
		}
		else {
			return new int[] {getDefaultSurfaceBlock(i, k, surfaceType, Block.netherrack.blockID), 0};
		}
	}
}