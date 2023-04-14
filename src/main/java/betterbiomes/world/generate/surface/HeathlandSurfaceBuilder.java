package betterbiomes.world.generate.surface;

import java.util.Random;

import betterbiomes.biome.biomes.HeathlandBiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class HeathlandSurfaceBuilder extends SurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random treeRand = new Random(seed + 2000);
		
		this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);
			
		this.treeNoiseScale = 1/256D;
	}
	
	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		int numTrees = 12;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;

			HeathlandBiome heathlandBiome = (HeathlandBiome) this.biome;

			AbstractTreeGrower treeGrower;
			
			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .5 > 0) {
				treeGrower = heathlandBiome.getTreeGrowerFromList(rand, heathlandBiome.treeGrowers);
			}
			else if (rand.nextInt(12) < 7) {
				treeGrower = heathlandBiome.getTreeGrowerFromList(rand, heathlandBiome.spruceTreeGrowers);
			}
			else continue;

			treeGrower.growTree(world, rand, x, world.getHeightValue(x, z), z, true);
		}
	}
}