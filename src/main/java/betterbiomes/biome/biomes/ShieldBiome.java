package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.TaigaGen4;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TaigaGen6;
import betterterrain.world.feature.tree.legacy.TaigaGen7;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class ShieldBiome extends BTABiome {
	public ShieldBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 7;
        this.btaBiomeDecorator.grassPerChunk = 2;
        this.btaBiomeDecorator.generateStoneInGrass = true;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.TALL_SPRUCE_TREE, 6);
		this.treeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 6);
		this.treeGrowers.put(BTATreeGrowers.OAK_REDWOOD_TREE, 6);
		this.treeGrowers.put(BTATreeGrowers.BIRCH_ASPEN_TREE, 2);

		this.decoTreeGrowers.put(BTATreeGrowers.TALL_SPRUCE_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_FIR_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_FIR_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_DARK_OAK_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.OAK_REDWOOD_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.BIRCH_ASPEN_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.ASPEN_TREE, 1);
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