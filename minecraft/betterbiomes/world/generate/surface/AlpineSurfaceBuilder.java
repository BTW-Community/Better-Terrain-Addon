package betterbiomes.world.generate.surface;

import java.util.Random;

import betterbiomes.feature.tree.PineTreeGen;
import betterbiomes.feature.tree.TaigaGen5;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

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
			
			WorldGenerator gen;
			
			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) - .25 > 0) {
				if (rand.nextInt(5) == 0) {
					gen = new TaigaGen5(false);
				}
				else {
					gen = new PineTreeGen(false, 2, 2);
				}
			}
			else {
				if (rand.nextInt(3) == 0) {
					gen = new TaigaGen5(false);
				}
				else {
					gen = new WorldGenTaiga2(false);
				}
			}
			
			if (world.getHeightValue(x, z) > 85 && rand.nextInt(3) == 0)
				continue;
			if (world.getHeightValue(x, z) > 100 && rand.nextInt(2) == 0)
				continue;
			
			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}