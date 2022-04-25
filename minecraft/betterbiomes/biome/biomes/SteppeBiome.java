package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;

public class SteppeBiome extends BTABiome {
	public SteppeBiome(int id, Climate climate) {
		super(id, climate);
		btaBiomeDecorator.treesPerChunk = -999;
		btaBiomeDecorator.grassPerChunk = 25;
		btaBiomeDecorator.steppePerChunk = 20;
		btaBiomeDecorator.cactiPerChunk = 50;
	}
}