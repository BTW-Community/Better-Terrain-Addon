package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.CherryTreeGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.HazelTreeGen;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class CherryBlossomGroveBiome extends BTABiome {
    public CherryBlossomGroveBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 5;
        this.btaBiomeDecorator.flowersPerChunk = 25;
    }

	public void initTreeGrowerMap() {
		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_CHERRY_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_CHERRY_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.CHERRY_TREE, 25);

		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_WHITE_CHERRY_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_WHITE_CHERRY_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.WHITE_CHERRY_TREE, 25);

		this.decoTreeGrowers.put(BTATreeGrowers.BIG_BIRCH_TREE, 2);
		this.decoTreeGrowers.put(TreeGrowers.BIRCH_TREE, 13);

		this.decoTreeGrowers.put(TreeGrowers.OAK_TREE, 15);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;

		if (rand.nextInt(20) == 0) {
			gen = new HazelTreeGen();
		}
		else if (rand.nextInt(5) == 0) {
    		gen = this.worldGeneratorTrees;
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = this.worldGeneratorForest;
    	}
    	else {
    		gen = new CherryTreeGen();
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