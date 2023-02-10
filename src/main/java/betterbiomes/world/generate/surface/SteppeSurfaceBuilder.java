package betterbiomes.world.generate.surface;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.world.feature.tree.legacy.SmallShrubGen;
import betterterrain.world.feature.tree.legacy.TinyShrubGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class SteppeSurfaceBuilder extends NoShorelineSurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random treeRand = new Random(seed + 4000);
		
		this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);
			
		this.treeNoiseScale = 1/256D;
	}
	
	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		int numTrees = 15;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;
			
			AbstractTreeGrower treeGrower;
			
			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) + .5 > 0) {
				continue;
			}
			else {
				treeGrower = ((BTABiome) biome).getTreeGrower(rand, generatorInfo, world.provider.terrainType);
			}

			treeGrower.growTree(world, rand, x, world.getHeightValue(x, z), z, true);
		}
	}
}