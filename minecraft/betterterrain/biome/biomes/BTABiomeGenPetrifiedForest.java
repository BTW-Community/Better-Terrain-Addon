package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.feature.BTAWorldGenPetrifiedTree;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenPetrifiedForest extends BTABiomeGenNetherBase {
	public BTABiomeGenPetrifiedForest(int id) {
		super(id);
		this.btaBiomeDecorator.fractionalTreeChance = 10;
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return new BTAWorldGenPetrifiedTree(false);
	}
}