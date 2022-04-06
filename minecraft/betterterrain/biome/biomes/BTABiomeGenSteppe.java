package betterterrain.biome.biomes;

import betterterrain.BTAEnumClimate;

public class BTABiomeGenSteppe extends BTABiomeGenBase {
	public BTABiomeGenSteppe(int id, BTAEnumClimate climate) {
		super(id, climate);
		btaBiomeDecorator.treesPerChunk = -999;
		btaBiomeDecorator.grassPerChunk = 25;
		btaBiomeDecorator.steppePerChunk = 20;
		btaBiomeDecorator.cactiPerChunk = 50;
	}
}