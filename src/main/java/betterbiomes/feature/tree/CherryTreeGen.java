package betterbiomes.feature.tree;

import java.util.Random;

import deco.block.DecoBlocks;
import deco.block.blocks.DecoSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class CherryTreeGen extends WorldGenerator {
	public boolean generate(World var0, Random var1, int var2, int var3, int var4) {
		return ((DecoSaplingBlock) DecoBlocks.cherrySapling).generateTree(var0, var1, var2, var3, var4, 0);
	}
}
