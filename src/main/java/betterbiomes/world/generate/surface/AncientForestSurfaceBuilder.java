package betterbiomes.world.generate.surface;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.OldOakGen;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.OpenSimplexOctaves;
import betterterrain.world.generate.surface.SurfaceBuilder;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

public class AncientForestSurfaceBuilder extends SurfaceBuilder {
	@Override
	public void init(Random rand, long seed) {
		super.init(rand, seed);

		Random treeRand = new Random(seed + 2000);

		this.treeNoiseGen = new OpenSimplexOctaves(treeRand.nextLong(), 2);

		this.treeNoiseScale = 1/256D;
	}

	public void generateTreesForBiome(World world, Random rand, WorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V2_0_0)) {
			int numTrees = (int) (8 + 2.9 * treeNoiseGen.noise2(this.chunkX + 16, this.chunkZ + 16, treeNoiseScale));

			for (int i = 0; i < numTrees; ++i)
			{
				int x = this.chunkX + rand.nextInt(16) + 8;
				int z = this.chunkZ + rand.nextInt(16) + 8;

				AbstractTreeGrower treeGrower = ((BTABiome) biome).getTreeGrower(rand, generatorInfo, world.provider.terrainType);
				treeGrower.growTree(world, rand, x, world.getHeightValue(x, z), z, true);
			}
		}
		else {
			super.generateTreesForBiome(world, rand, generatorInfo);
		}
	}
}