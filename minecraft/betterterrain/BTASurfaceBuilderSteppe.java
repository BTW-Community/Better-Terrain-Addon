package betterterrain;

import java.util.Random;

import betterterrain.feature.BTAWorldGenShrubSmall;
import betterterrain.feature.BTAWorldGenShrubTiny;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class BTASurfaceBuilderSteppe extends BTASurfaceBuilderNoShorelines {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);
		
		Random treeRand = new Random(seed + 4000);
		
		this.treeNoiseGen = new BTAUtilsOpenSimplexOctaves(treeRand.nextLong(), 2);
			
		this.treeNoiseScale = 1/256D;
	}
	
	public void generateTreesForBiome(World world, Random rand, BTAWorldConfigurationInfo generatorInfo) {
		int numTrees = 15;

		for (int i = 0; i < numTrees; ++i)
		{
			int x = this.chunkX + rand.nextInt(16) + 8;
			int z = this.chunkZ + rand.nextInt(16) + 8;
			
			WorldGenerator gen;
			
			if (this.treeNoiseGen.noise2(x, z, this.treeNoiseScale) + .5 > 0) {
				continue;
			}
			else {
				if (rand.nextInt(3) == 0) {
		    		gen = new BTAWorldGenShrubSmall();
		    	}
		    	else if (rand.nextInt(10) == 0) {
		    		gen = new WorldGenShrub(0, 0);
		    	}
		    	else {
		    		gen = new BTAWorldGenShrubTiny();
		    	}
			}
			
			gen.setScale(1.0D, 1.0D, 1.0D);
			gen.generate(world, rand, x, world.getHeightValue(x, z), z);
		}
	}
}