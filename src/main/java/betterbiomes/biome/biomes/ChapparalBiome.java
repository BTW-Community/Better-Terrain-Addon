package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.SmallShrubGen;
import betterterrain.world.feature.tree.legacy.TinyShrubGen;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class ChapparalBiome extends BTABiome {
	public ChapparalBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		btaBiomeDecorator.treesPerChunk = 8;
		btaBiomeDecorator.grassPerChunk = 20;
		btaBiomeDecorator.generateStoneInGrass = true;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.SMALL_OAK_SHRUB, 2);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);
		this.treeGrowers.put(BTATreeGrowers.TINY_OAK_SHRUB, 2);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new SmallShrubGen();
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else {
    		gen = new TinyShrubGen();
    	}
    	
    	return gen;
    }
}