package net.minecraft.src;

public class BTWGBiomeGenSteppe extends BTWGBiomeGenBase {
	public BTWGBiomeGenSteppe(int id) {
		super(id);
		btwgBiomeDecorator.treesPerChunk = -999;
		btwgBiomeDecorator.grassPerChunk = 25;
		btwgBiomeDecorator.steppePerChunk = 10;
		btwgBiomeDecorator.cactiPerChunk = 15;
	}
}