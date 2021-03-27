package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenPlains extends BTABiomeGenBase {
	protected BTABiomeGenPlains(int id) {
		super(id);
		this.btaiomeDecorator.fractionalTreeChance = 5;
		this.btaiomeDecorator.grassPerChunk = 30;
		this.btaiomeDecorator.flowersPerChunk = 20;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenShrubSmall();
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new BTAWorldGenShrubTiny();
    	}
    	
    	return gen;
    }
}