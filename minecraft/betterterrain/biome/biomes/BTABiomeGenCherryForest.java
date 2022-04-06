package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenCherryTree;
import betterterrain.feature.BTAWorldGenTallGrass;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenCherryForest extends BTABiomeGenBase {
    public BTABiomeGenCherryForest(int par1, BTAEnumClimate climate) {
        super(par1, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 5;
        this.btaBiomeDecorator.flowersPerChunk = 25;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0) {
    		gen = this.worldGeneratorTrees;
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = this.worldGeneratorForest;
    	}
    	else {
    		gen = new BTAWorldGenCherryTree();
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