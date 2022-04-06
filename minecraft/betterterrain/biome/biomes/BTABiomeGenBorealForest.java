package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenShrubSmall;
import betterterrain.feature.BTAWorldGenTaiga5;
import betterterrain.feature.BTAWorldGenTaiga6;
import betterterrain.feature.BTAWorldGenTaiga7;
import betterterrain.feature.BTAWorldGenTallGrass;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenBorealForest extends BTABiomeGenBase {
	public BTABiomeGenBorealForest(int id, BTAEnumClimate climate) {
		super(id, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 20;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(6) == 0) {
    		gen = new WorldGenTaiga2(false);
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new BTAWorldGenTaiga5(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new BTAWorldGenTaiga6(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new BTAWorldGenTaiga7(false);
    	}
    	else if (rand.nextInt(2) == 0){
    		gen = new WorldGenShrub(1, 1);
    	}
    	else {
    		gen = new BTAWorldGenShrubSmall(1, 1);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new BTAWorldGenTallGrass(Block.tallGrass.blockID, 2) : new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
	}
}