package betterbiomes.biome.biomes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import betterbiomes.world.feature.tree.legacy.TaigaGen3;
import betterbiomes.world.feature.tree.legacy.TaigaGen4;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TaigaGen7;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class ConiferousForestBiome extends BTABiome {
	public ConiferousForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 8, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 8;
        this.btaBiomeDecorator.grassPerChunk = 10;
	}

	public Map<AbstractTreeGrower, Integer> clearingTreeGrowers;
	public Map<AbstractTreeGrower, Integer> decoClearingTreeGrowers;

	public void initTreeGrowerMap() {
		clearingTreeGrowers = new HashMap<>();
		decoClearingTreeGrowers = new HashMap<>();

		this.treeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 3);
		this.treeGrowers.put(BTATreeGrowers.TALL_SPRUCE_TREE, 2);
		this.treeGrowers.put(BTATreeGrowers.HUGE_SPRUCE_TREE, 1);

		this.clearingTreeGrowers.put(TreeGrowers.SPRUCE_TREE, 3);
		this.clearingTreeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 2);
		this.clearingTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 1);

		this.decoTreeGrowers.put(BTATreeGrowers.BIG_FIR_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_FIR_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_FIR_TREE, 1);

		this.decoClearingTreeGrowers.put(BTATreeGrowers.FIR_TREE, 3);
		this.decoClearingTreeGrowers.put(BTATreeGrowers.MEDIUM_FIR_TREE, 2);
		this.decoClearingTreeGrowers.put(BTATreeGrowers.BIG_FIR_TREE, 1);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0) {
    		gen = new TaigaGen3(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen4(false);
    	}
    	else {
    		gen = new TaigaGen7(false);
    	}
    	
    	return gen;
    }

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}