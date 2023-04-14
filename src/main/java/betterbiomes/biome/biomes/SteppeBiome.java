package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import net.minecraft.src.Block;

public class SteppeBiome extends BTABiome {
	public SteppeBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.topBlockExt = Block.sand.blockID;
		this.fillerBlockExt = Block.sand.blockID;

		btaBiomeDecorator.treesPerChunk = -999;
		btaBiomeDecorator.grassPerChunk = 10;
		btaBiomeDecorator.cactiPerChunk = 50;
		btaBiomeDecorator.steppePerChunk = 20;
		btaBiomeDecorator.deadBushPerChunk = 15;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.TINY_OAK_SHRUB, 12);
		this.treeGrowers.put(BTATreeGrowers.SMALL_OAK_SHRUB, 12);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 4);
		this.treeGrowers.put(BTATreeGrowers.STEPPE_TREE, 1);
	}
}