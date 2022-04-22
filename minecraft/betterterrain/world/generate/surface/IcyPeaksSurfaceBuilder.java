package betterterrain.world.generate.surface;

import java.util.Random;

import betterterrain.feature.TaigaGen5;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class IcyPeaksSurfaceBuilder extends SurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random treeRand = new Random(seed + 3000);
		
		this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);
			
		this.treeNoiseScale = 1/256D;
	}
	
	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		int numTrees = 3;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;
			
			WorldGenerator gen;
			
			if (rand.nextInt(3) == 0) {
				gen = new TaigaGen5(false);
			}
			else {
				gen = new WorldGenTaiga2(false);
			}
			
			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) + .5 > 0 && rand.nextInt(30) > 0) {
				continue;
			}
			
			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}