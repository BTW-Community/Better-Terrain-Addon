package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import deco.block.DecoBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.WorldType;

public class BasaltDeltasSurfaceBuilder extends SurfaceBuilder {
	public OpenSimplexOctaves lavaNoiseGen;
	public OpenSimplexOctaves spikeNoiseGen;
	public OpenSimplexOctaves spikeHeightNoiseGen;
	public OpenSimplexOctaves ashNoiseGen;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random noiseRand = new Random(seed + 2500);

		lavaNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 8);
		spikeNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 8);
		spikeHeightNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 8);
		ashNoiseGen = new OpenSimplexOctaves(noiseRand.nextLong(), 2);
	}

	@Override
	protected void replaceBlocksForBiome(Random rand, int i, int k, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo, WorldType worldType, boolean isNether) {
		byte seaLevel = 63;

		if ((worldType).isSky() || isNether)
			seaLevel = 0;

		float temperature = biome.getFloatTemperature();

		int soilDepth = getSoilDepth(i, k, rand, generatorInfo);
		int remainingDepth = -1;

		boolean useSubfiller = false;

		int surfaceJ = 127;

		for (int j = 127; j >= 0; j--) {
			if (j <= 0 + rand.nextInt(5) && !(worldType).isSky()) {
				setBlockValue(blockArray, i, j, k, Block.bedrock.blockID);
			}
			else {
				SurfaceProcessingResult result = generateSurfaceAtLocation(blockArray, metaArray, i, j, k, surfaceJ, soilDepth, remainingDepth, useSubfiller, seaLevel, temperature, true, rand, generatorInfo, worldType);
				remainingDepth = result.remainingDepth;
				useSubfiller = result.useSubfiller;
				surfaceJ = result.surfaceJ;
			}
		}
		
		surfaceJ = 0;
		
		int lastBlockId = 1;
		//boolean useSpikes = spikeNoiseGen.noise2(this.chunkX * 16 + k, this.chunkZ * 16 + i, 1/24D) + rand.nextDouble() * 0.2 > 0;
		int spikeHeightBase = (int) ((spikeHeightNoiseGen.noise2(this.chunkX * 16 + k, this.chunkZ * 16 + i, 1/24D)) * 6);
		boolean useSpikes = spikeHeightBase > 0;
		int spikeHeight = spikeHeightBase;

		for (int j = 0; j <= 127; j++) {
			if (j >= 127 - rand.nextInt(5) && !worldType.isSky()) {
				setBlockValue(blockArray, i, j, k, Block.bedrock.blockID);
			}
			else {
				
				int blockID = getBlockValue(blockArray, i, j, k);

				if (blockID == 0) {
					remainingDepth = -1;

					if ((lastBlockId != 0 && lastBlockId != Block.lavaStill.blockID) && spikeHeight > 0 && useSpikes) {
						int[] fillerBlock = this.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, SurfaceType.FILLER, seaLevel, rand, generatorInfo, worldType);
						
						setBlockValue(blockArray, i, j, k, fillerBlock[0]);
						setBlockValue(metaArray, i, j, k, fillerBlock[1]);

						spikeHeight -= rand.nextInt(3);
					}
				}
				else if (Block.blocksList[blockID].blockMaterial == Material.lava) {
					spikeHeight = 0;
				}
				else if (blockID == Block.stone.blockID || blockID == Block.netherrack.blockID) {
					if (remainingDepth == -1) {
						remainingDepth = soilDepth;
						surfaceJ = j;

						int[] surfaceBlock = this.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, SurfaceType.TOP, seaLevel, rand, generatorInfo, worldType);

						setBlockValue(blockArray, i, j, k, surfaceBlock[0]);
						setBlockValue(metaArray, i, j, k, surfaceBlock[1]);
					}
					else if (remainingDepth > 0) {
						remainingDepth--;

						SurfaceType surfaceType = useSubfiller ? SurfaceType.SUBFILLER : SurfaceType.FILLER;
						
						int[] fillerBlock = this.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
						
						setBlockValue(blockArray, i, j, k, fillerBlock[0]);
						setBlockValue(metaArray, i, j, k, fillerBlock[1]);

						spikeHeight = spikeHeightBase;
					}
				}
			}
		}
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double ashNoiseScale = 0.0625D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useAsh = ashNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), ashNoiseScale) > 0.2;
		
		int spikeHeightBase = (int) ((spikeHeightNoiseGen.noise2(this.chunkX * 16 + k, this.chunkZ * 16 + i, 1/24D)) * 6);
		boolean useSpikes = spikeHeightBase > 0;
		
		if (useAsh && BTAMod.isDecoInstalled() && (worldType).isDeco() && surfaceType != SurfaceType.SUBFILLER && !useSpikes) {
			return new int[] {DecoBlocks.ash.blockID, 0};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
}