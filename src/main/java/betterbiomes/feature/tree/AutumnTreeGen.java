package betterbiomes.feature.tree;

import java.util.Random;

import deco.block.DecoBlocks;
import deco.block.blocks.DecoSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class AutumnTreeGen extends WorldGenerator {
	private int leafMeta = 0;
	
	public AutumnTreeGen(int leafMeta) {
		this.leafMeta = leafMeta;
	}
	
	public boolean generate(World var0, Random var1, int var2, int var3, int var4) {
		return ((DecoSaplingBlock) DecoBlocks.autumnSapling).generateTree(var0, var1, var2, var3, var4, leafMeta);
	}
}
