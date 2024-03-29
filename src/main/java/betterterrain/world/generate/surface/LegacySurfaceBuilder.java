package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.mixins.BiomeDecoratorAccessor;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.BetaNoiseOctaves;
import deco.block.DecoBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.NoiseGeneratorOctaves;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class LegacySurfaceBuilder extends SurfaceBuilder {
	private double[] sandNoiseLegacy = new double[256];
	private double[] gravelNoiseLegacy = new double[256];
	private double[] soilDepthNoiseLegacy = new double[256];
	private BetaNoiseOctaves sandNoiseGenLegacy;
	private NoiseGeneratorOctaves soilDepthNoiseGenLegacy;

	public void init(Random rand, long seed) {
		this.sandNoiseGenLegacy = new BetaNoiseOctaves(rand, 4);
		this.soilDepthNoiseGenLegacy = new NoiseGeneratorOctaves(rand, 4);
	}

	public void replaceBlocksForBiome(Random rand, int chunkX, int chunkZ, int[] blockArray, int[] metaArray, BiomeGenBase[] biomesForGeneration, WorldConfigurationInfo generatorInfo) {
		byte seaLevel = 63;
		double soilDepthNoiseScalar = 0.03125D;
		this.sandNoiseLegacy = this.sandNoiseGenLegacy.generateNoiseOctaves(this.sandNoiseLegacy, (double)(chunkX * 16), (double)(chunkZ * 16), 0.0D, 16, 16, 1, soilDepthNoiseScalar, soilDepthNoiseScalar, 1.0D);
		this.gravelNoiseLegacy = this.sandNoiseGenLegacy.generateNoiseOctaves(this.gravelNoiseLegacy, (double)(chunkX * 16), 109.0134D, (double)(chunkZ * 16), 16, 1, 16, soilDepthNoiseScalar, 1.0D, soilDepthNoiseScalar);
		this.soilDepthNoiseLegacy = this.soilDepthNoiseGenLegacy.generateNoiseOctaves(this.soilDepthNoiseLegacy, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D, soilDepthNoiseScalar * 2.0D);

		for (int i = 0; i < 16; ++i) {
			for (int k = 0; k < 16; ++k) {
				BiomeGenBase biome = biomesForGeneration[k + i * 16];

				float temperature = biome.getFloatTemperature();
				boolean useSand = this.sandNoiseLegacy[i + k * 16] + rand.nextDouble() * 0.2D > 0.0D;
				boolean useGravel = this.gravelNoiseLegacy[i + k * 16] + rand.nextDouble() * 0.2D > 3.0D;
				int soilDepthNoiseSample = (int)(this.soilDepthNoiseLegacy[i + k * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
				int remaingDepth = -1;
				int topBlock;
				int fillerBlock;

				if (biome instanceof BTABiome) {
					topBlock = ((BTABiome) biome).topBlockExt;
					fillerBlock = ((BTABiome) biome).fillerBlockExt;
				}
				else {
					topBlock = biome.topBlock;
					fillerBlock = biome.fillerBlock;
				}

				for (int j = 127; j >= 0; --j) {
					int index = (k * 16 + i) * 128 + j;

					if (j <= 0 + rand.nextInt(5)) {
						blockArray[index] = (byte)Block.bedrock.blockID;
					}
					else {
						int blockID = blockArray[index];

						if (blockID == 0) {
							remaingDepth = -1;
						}
						else if (blockID == Block.stone.blockID) {
							if (remaingDepth == -1) {
								if (soilDepthNoiseSample <= 0) {
									topBlock = 0;
									fillerBlock = (byte)Block.stone.blockID;
								}
								else if (j >= seaLevel - (8 + rand.nextInt(2)) && j <= seaLevel + 1) {
									if(biome.biomeID == 131 /*old valley*/ || biome.biomeID == 130 /*valley mountains*/ || biome.biomeID == 162 /*valley*/ ||
											((biome.biomeID == 112 /*tropics*/ || biome.biomeID == 237 /*tropics edge*/ || biome.biomeID == 205 /*tropics river*/) && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_2_0))) {
										topBlock = (byte)Block.sand.blockID;
										fillerBlock = (byte)Block.sand.blockID;
									}
									else {
										if (biome instanceof BTABiome) {
											topBlock = ((BTABiome) biome).topBlockExt;
											fillerBlock = ((BTABiome) biome).fillerBlockExt;
										}
										else {
											topBlock = biome.topBlock;
											fillerBlock = biome.fillerBlock;
										}
									}

									if (generatorInfo.generatePerlinBeaches() && biome instanceof BTABiome && !(((BTABiome) biome).getSurfaceBuilder() instanceof NoShorelineSurfaceBuilder)) {
										if (useGravel) {
											topBlock = 0;
											fillerBlock = Block.gravel.blockID;
										}

										if (useSand) {
											if (biome.biomeID == 182 /*badlands*/ || biome.biomeID == 183 /*badlands plateau*/ || biome.biomeID == 232 /*badlands edge*/ || biome.biomeID == 204 /*badlands river*/ ||
													biome.biomeID == 180 /*outback*/ || biome.biomeID == 203 /*outback river*/ || biome.biomeID == 240 /*red sand beach*/) {
												topBlock = DecoBlocks.legacyRedSand.blockID;
												fillerBlock = DecoBlocks.legacyRedSand.blockID;
											}
											else {
												topBlock = Block.sand.blockID;
												fillerBlock = Block.sand.blockID;
											}
										}
									}
								}
								else if (j >= seaLevel + 9) {
									if (biome instanceof BTABiome) {
										topBlock = ((BTABiome) biome).topBlockExt;
										fillerBlock = ((BTABiome) biome).fillerBlockExt;
									}
									else {
										topBlock = biome.topBlock;
										fillerBlock = biome.fillerBlock;
									}
								}

								if (j < seaLevel && topBlock == 0) {
									if (temperature < 0.15F) {
										topBlock = (byte)Block.ice.blockID;
									}
									else {
										topBlock = (byte)Block.waterStill.blockID;
									}
								}

								remaingDepth = soilDepthNoiseSample;

								if (biome.biomeID == 183 /*badlands plateau*/)
									remaingDepth += 10;

								if (j >= seaLevel - 1) {
									blockArray[index] = topBlock;
								}
								else {
									blockArray[index] = fillerBlock;
								}
							}
							else if (remaingDepth > 0) {
								--remaingDepth;
								blockArray[index] = fillerBlock;

								if (remaingDepth == 0 && fillerBlock == Block.sand.blockID) {
									remaingDepth = rand.nextInt(4);
									fillerBlock = (byte)Block.sandStone.blockID;
								}
								else if (BTAMod.isDecoInstalled() && remaingDepth == 0 && fillerBlock == DecoBlocks.legacyRedSand.blockID) {
									remaingDepth = rand.nextInt(4);
									fillerBlock = DecoBlocks.redSandstone.blockID;
								}
							}
						}
					}
				}
			}
		}
	}

	protected void generateTreesForBiome(World world, Random rand, int chunkX, int chunkZ, BiomeGenBase biome, WorldConfigurationInfo generatorInfo) {
		int numTrees;

		if (biome instanceof BTABiome) {
			numTrees = ((BTABiome) biome).btaBiomeDecorator.treesPerChunk;

			if (rand.nextInt(((BTABiome) biome).btaBiomeDecorator.fractionalTreeChance) == 0)
				numTrees++;
		}
		else {
			numTrees = ((BiomeDecoratorAccessor) biome.theBiomeDecorator).getTreesPerChunk();
			
			if (rand.nextInt(10) == 0)
				numTrees++;
		}

		for (int i = 0; i < numTrees; ++i)
		{
			int x = chunkX + rand.nextInt(16) + 8;
			int z = chunkZ + rand.nextInt(16) + 8;

			WorldGenerator gen;

			if (biome instanceof BTABiome) {
				gen = ((BTABiome) biome).getRandomWorldGenForTrees(rand, generatorInfo, world.provider.terrainType);
			}
			else {
				gen = biome.getRandomWorldGenForTrees(rand);
			}

			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}