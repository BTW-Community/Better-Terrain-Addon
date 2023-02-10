package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;

public class SteppeBiome extends BTABiome {
	public SteppeBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		btaBiomeDecorator.treesPerChunk = -999;
		btaBiomeDecorator.grassPerChunk = 25;
		btaBiomeDecorator.steppePerChunk = 20;
		btaBiomeDecorator.cactiPerChunk = 50;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.SMALL_OAK_SHRUB, 2);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);
		this.treeGrowers.put(BTATreeGrowers.TINY_OAK_SHRUB, 2);
	}
}