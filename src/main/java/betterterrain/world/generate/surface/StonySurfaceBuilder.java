package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.util.WorldTypeInterface;
import deco.block.DecoBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class StonySurfaceBuilder extends SurfaceBuilder {
	protected static OpenSimplexOctaves stoneNoiseGenSimplex;
	
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random stoneRand = new Random(seed + 1000);
		
		stoneNoiseGenSimplex = new OpenSimplexOctaves(stoneRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double stoneNoiseScale;

		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V3_2_0)) {
			stoneNoiseScale = 1/32D;
		}
		else {
			stoneNoiseScale = 1/16D;
		}

		boolean useStone = stoneNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), stoneNoiseScale) - 0.625 > 0;
		
		boolean sandOrGravel = (this.useSandAtLocation(i, k, rand) || this.useGravelAtLocation(i, k, rand, generatorInfo)) && surfaceJ <= seaLevel + 1;
		
		if (useStone && BTAMod.isDecoInstalled() && ((WorldTypeInterface) worldType).isDeco() && !sandOrGravel) {
			return new int[] {Block.stone.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
}