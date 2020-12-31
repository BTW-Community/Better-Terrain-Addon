package net.minecraft.src;

public class BTABiomeGenSteppe extends BTABiomeGenBase {
	public BTABiomeGenSteppe(int id) {
		super(id);
		btwgBiomeDecorator.treesPerChunk = -999;
		btwgBiomeDecorator.grassPerChunk = 25;
		btwgBiomeDecorator.steppePerChunk = 20;
		btwgBiomeDecorator.cactiPerChunk = 50;
	}
}