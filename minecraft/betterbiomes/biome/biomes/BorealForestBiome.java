package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.plant.TallGrassGen;
import betterbiomes.feature.tree.SmallShrubGen;
import betterbiomes.feature.tree.TaigaGen5;
import betterbiomes.feature.tree.TaigaGen6;
import betterbiomes.feature.tree.TaigaGen7;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class BorealForestBiome extends BTABiome {
	public BorealForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
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
    		gen = new TaigaGen5(false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new TaigaGen6(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen7(false);
    	}
    	else if (rand.nextInt(2) == 0){
    		gen = new WorldGenShrub(1, 1);
    	}
    	else {
    		gen = new SmallShrubGen(1, 1);
    	}
    	
    	return gen;
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}