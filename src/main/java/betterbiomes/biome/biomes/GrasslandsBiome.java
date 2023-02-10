package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.OldOakGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenerator;

public class GrasslandsBiome extends BTABiome {
	public GrasslandsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 20;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(TreeGrowers.BIG_OAK_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.HUGE_OAK_TREE, 1);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(2) == 0) {
    		gen = new OldOakGen(false);
    	}
    	else {
    		gen = new WorldGenBigTree(false);
    	}
    	
    	return gen;
    }
}