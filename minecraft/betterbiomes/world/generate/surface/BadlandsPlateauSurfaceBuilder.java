package betterbiomes.world.generate.surface;

import java.util.ArrayList;
import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.generate.surface.SurfaceBuilder.SurfaceType;
import net.minecraft.src.Block;
import net.minecraft.src.WorldType;

public class BadlandsPlateauSurfaceBuilder extends SurfaceBuilder {
	private static ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	private static int[] metaLocations;
	protected static OpenSimplexOctaves grassNoiseGenSimplex;

	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random metaRand = new Random(seed);

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
		
		grassNoiseGenSimplex = new OpenSimplexOctaves(metaRand.nextLong(), 2);
	}
	
	@Override
	protected int[] getSurfaceBlock(int i, int j, int k, int surfaceJ, int soilDepth, SurfaceType surfaceType, int seaLevel, Random rand, WorldConfigurationInfo generatorInfo, WorldType worldType) {
		double grassNoiseScale = 1/256D;
		//k and i swapped because apparently I messed something up somewhere
		boolean useGrass = grassNoiseGenSimplex.noise2((this.chunkX * 16 + k), (this.chunkZ * 16 + i), grassNoiseScale) + rand.nextDouble() * 0.15D - 0.125 > 0;
		
		if (useGrass && surfaceType == SurfaceType.TOP && j >= 95) {
			return new int[] {Block.grass.blockID, 0};
		}
		else {
			int blockID = DecoIntegration.terracotta.blockID;
			int metadata = 0;
			
			if (blockID == DecoIntegration.terracotta.blockID && metaLocations[j & 15] != -1) {
				if (j < 95 || (j < 127 && j != surfaceJ)) {
					blockID = DecoIntegration.stainedTerracotta.blockID;
					metadata = metaLocations[j & 15];
				}
			}
			
			return new int[] {blockID, metadata};
		}
	}
	
	@Override
	protected int getSubsurfaceDepth(Random rand) {
		return 10;
	}
}