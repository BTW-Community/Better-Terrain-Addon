package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenPetrifiedForest extends BTABiomeGenNetherBase {
	public BTABiomeGenPetrifiedForest(int id) {
		super(id);
		this.btaBiomeDecorator.fractionalTreeChance = 10;
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return new BTAWorldGenPetrifiedTree(false);
	}
}