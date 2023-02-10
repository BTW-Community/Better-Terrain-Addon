package betterbiomes.world.generate.surface;

import java.util.Random;

import betterbiomes.biome.biomes.AlpineBiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.util.WorldTypeInterface;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import net.minecraft.src.World;

public class AlpineSurfaceBuilder extends SurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random treeRand = new Random(seed + 2000);
		
		this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);
	}
	
	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		int numTrees = 20;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;

			AbstractTreeGrower treeGrower;

			if (((WorldTypeInterface) world.provider.terrainType).isDeco()) {
				treeGrower = this.getDecoTree(rand, x, z);
			}
			else {
				treeGrower = this.getVanillaTree(rand, x, z);
			}
			
			if (world.getHeightValue(x, z) > 85 && rand.nextInt(3) == 0)
				continue;
			if (world.getHeightValue(x, z) > 100 && rand.nextInt(2) == 0)
				continue;

			treeGrower.growTree(world, rand, x, world.getHeightValue(x, z), z, true);
		}
	}

	private AbstractTreeGrower getDecoTree(Random rand, int x, int z) {
		AlpineBiome alpineBiome = (AlpineBiome) this.biome;

		if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .25 > 0) {
			return alpineBiome.getTreeGrowerFromList(rand, alpineBiome.decoAspenTreeGrowers);
		}
		else {
			return alpineBiome.getTreeGrowerFromList(rand, alpineBiome.decoTreeGrowers);
		}
	}

	private AbstractTreeGrower getVanillaTree(Random rand, int x, int z) {
		AlpineBiome alpineBiome = (AlpineBiome) this.biome;

		if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .25 > 0) {
			return alpineBiome.getTreeGrowerFromList(rand, alpineBiome.aspenTreeGrowers);
		}
		else {
			return alpineBiome.getTreeGrowerFromList(rand, alpineBiome.treeGrowers);
		}
	}
}