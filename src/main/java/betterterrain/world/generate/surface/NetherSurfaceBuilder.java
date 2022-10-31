package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class NetherSurfaceBuilder extends SurfaceBuilder {
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, boolean isReversed, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		int blockID = -1;
		int metadata = 0;

		if (surfaceJ <= 64 && !isReversed) {
			if (useSandAtLocation(i, k, rand)) {
				blockID = Block.slowSand.blockID;
			}
			else if (surfaceJ > 58 && useGravelAtLocation(i, k, rand, generatorInfo)) {
				if (surfaceType == SurfaceType.TOP) {
					blockID = Block.gravel.blockID;
				}
			}
		}

		if (blockID == -1) {
			blockID = getDefaultSurfaceBlock(i, k, surfaceType, Block.netherrack.blockID);
		}

		return new int[] {blockID, metadata};
	}
	
	@Override
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo, WorldType worldType, boolean isNether) {
		byte seaLevel = 0;

		float temperature = biome.getFloatTemperature();

		int soilDepth = getSoilDepth(i, k, rand, generatorInfo);
		int remainingDepth = -1;

		boolean useSubfiller = false;

		int surfaceJ = 127;

		for (int j = 127; j >= 0; j--) {
			if (j <= 0 + rand.nextInt(5) && !((WorldTypeInterface) worldType).isSky()) {
				setBlockValue(blockArray, i, j, k, Block.bedrock.blockID);
			}
			else {
				SurfaceProcessingResult result = generateSurfaceAtLocation(blockArray, metaArray, i, j, k, surfaceJ, soilDepth, remainingDepth, useSubfiller, seaLevel, temperature, false, rand, generatorInfo, worldType);
				remainingDepth = result.remainingDepth;
				useSubfiller = result.useSubfiller;
				surfaceJ = result.surfaceJ;
			}
		}
		
		surfaceJ = 0;

		for (int j = 0; j <= 127; j++) {
			if (j >= 127 - rand.nextInt(5) && !((WorldTypeInterface) worldType).isSky()) {
				setBlockValue(blockArray, i, j, k, Block.bedrock.blockID);
			}
			else {
				SurfaceProcessingResult result = generateSurfaceAtLocation(blockArray, metaArray, i, j, k, surfaceJ, soilDepth, remainingDepth, useSubfiller, seaLevel, temperature, true, rand, generatorInfo, worldType);
				remainingDepth = result.remainingDepth;
				useSubfiller = result.useSubfiller;
				surfaceJ = result.surfaceJ;
			}
		}
	}

	@Override
	protected boolean useSandAtLocation(int i, int k, Random rand) {
		double beachNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		return sandNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), beachNoiseScale) > 0.925;
	}

	@Override
	protected boolean useGravelAtLocation(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		double beachNoiseScale = 1/384D;
		//k and i swapped because apparently I messed something up somewhere
		return gravelNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), beachNoiseScale) > 0.9625;
	}

	@Override
	protected int getSoilDepth(int i, int k, Random rand, WorldConfigurationInfo generatorInfo) {
		return 3;
	}

	@Override
	protected int getSubsurfaceDepth(Random rand) {
		return 0;
	}
}
