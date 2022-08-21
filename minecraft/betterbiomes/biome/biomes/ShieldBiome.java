package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.plant.TallGrassGen;
import betterbiomes.feature.tree.TaigaGen4;
import betterbiomes.feature.tree.TaigaGen6;
import betterbiomes.feature.tree.TaigaGen7;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class ShieldBiome extends BTABiome {
	public ShieldBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 7;
        this.btaBiomeDecorator.grassPerChunk = 2;
        this.btaBiomeDecorator.generateStoneInGrass = true;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(4) == 0) {
    		gen = new TaigaGen4(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen7(false);
    	}
    	else {
    		gen = new TaigaGen6(false);
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