package net.minecraft.src;

public class BTABiomeGenSteppe extends BTABiomeGenBase {
	public BTABiomeGenSteppe(int id) {
		super(id);
		btaiomeDecorator.treesPerChunk = -999;
		btaiomeDecorator.grassPerChunk = 25;
		btaiomeDecorator.steppePerChunk = 20;
		btaiomeDecorator.cactiPerChunk = 50;
	}
}