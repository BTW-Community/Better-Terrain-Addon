package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.CherryTreeGen;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.util.WorldTypeInterface;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;

public class FrozenSpringsBiome extends BTABiome {
	public FrozenSpringsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.treesPerChunk = 2;
		this.btaBiomeDecorator.grassPerChunk = 5;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(TreeGrowers.BIRCH_TREE, 9);
		this.treeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);

		this.decoTreeGrowers.put(TreeGrowers.BIRCH_TREE, 9);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.CHERRY_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.WHITE_CHERRY_TREE, 2);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0 && BTAMod.isDecoInstalled() && ((WorldTypeInterface) worldType).isDeco()) {
    		gen = new CherryTreeGen();
    	}
    	else {
    		gen = this.worldGeneratorForest;
    	}
    	
    	return gen;
    }
}