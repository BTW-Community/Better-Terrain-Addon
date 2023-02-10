package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class TundraBiome extends BTABiome {
	public TundraBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.flowersPerChunk = -999;
		this.btaBiomeDecorator.treesPerChunk = 2;
	}

	public void initTreeGrowerMap() {
		treeGrowers.put(TreeGrowers.OAK_TREE, 1);
		treeGrowers.put(BTATreeGrowers.SPRUCE_BUSH, 9);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = this.worldGeneratorTrees;
    	}
    	else {
    		gen = new WorldGenShrub(1, 1);
    	}
    	
    	return gen;
    }
}