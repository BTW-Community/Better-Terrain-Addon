package betterbiomes.world.generate.surface;

import java.util.ArrayList;
import java.util.Random;

import betterterrain.BTAMod;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.util.WorldTypeInterface;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class FirCanyonSurfaceBuilder extends NoShorelineSurfaceBuilder {
	private static ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	private static int[] metaLocations;
	
	protected OpenSimplexOctaves grassNoiseGen;
	protected OpenSimplexOctaves coarseDirtNoiseGen;
	
	private final boolean generateTerracotta;
	
	public FirCanyonSurfaceBuilder(boolean generateTerracotta) {
		this.generateTerracotta = generateTerracotta;
	}

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random metaRand = new Random(seed + 100);

		//Init terracotta striping data - done statically in case multiple badlands variants use this surface builder
		if (allowedTerracottaMetadata.size() == 0) {
			allowedTerracottaMetadata.add(1);
			allowedTerracottaMetadata.add(3);
			allowedTerracottaMetadata.add(7);
			allowedTerracottaMetadata.add(11);
			allowedTerracottaMetadata.add(14);
			allowedTerracottaMetadata.add(15);
		}

		if (metaLocations == null) {
			metaLocations = new int[128];

			for (int i = 0; i < metaLocations.length; i++) {
				int meta = metaRand.nextInt(16);

				if (allowedTerracottaMetadata.contains(meta)) {
					metaLocations[i] = meta;
				}
				else {
					//Tries a second time to place a colored stripe
					meta = metaRand.nextInt(16);

					if (allowedTerracottaMetadata.contains(meta)) {
						metaLocations[i] = meta;
					}
					else {
						metaLocations[i] = -1;
					}
				}
			}
		}

		grassNoiseGen = new OpenSimplexOctaves(metaRand.nextLong(), 4);
		coarseDirtNoiseGen = new OpenSimplexOctaves(metaRand.nextLong(), 4);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double dirtNoiseScale = 1/96D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useCoarseDirt = coarseDirtNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), dirtNoiseScale) + rand.nextDouble() * 0.15D > -0.125;
		
		if (useCoarseDirt && BTAMod.isDecoInstalled() && ((WorldTypeInterface) worldType).isDeco()) {
			double grassNoiseScale = 1/48D;
			//k and i swapped because apparently I messed something up somewhere
			boolean useGrass = grassNoiseGen.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.15D > -0.125;
			
			switch (surfaceType) {
			case TOP:
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
			case FILLER:
				return new int[] {Block.dirt.blockID, 0};
			default:
				return new int[] {Block.stone.blockID, 0};
			}
		}
		else if (generateTerracotta) {
			int blockID = DecoBlocks.terracotta.blockID;
			int metadata = 0;
			
			if (blockID == DecoBlocks.terracotta.blockID && metaLocations[j & 15] != -1) {
				if (j < 95 || (j < 127 && j != surfaceJ)) {
					blockID = DecoBlocks.stainedTerracotta.blockID;
					metadata = metaLocations[j & 15];
				}
			}
			
			return new int[] {blockID, metadata};
		}
		else {
			return super.getSurfaceBlock(i, j, k, surfaceJ, soilDepth, surfaceType, seaLevel, rand, generatorInfo, worldType);
		}
	}
	
	@Override
	protected int getSubsurfaceDepth(Random rand) {
		if (this.generateTerracotta) {
			return 10;	
		}
		else {
			return super.getSubsurfaceDepth(rand);
		}
	}
}