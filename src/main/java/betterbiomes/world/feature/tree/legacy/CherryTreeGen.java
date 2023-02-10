package betterbiomes.world.feature.tree.legacy;

import java.util.Random;

import deco.block.DecoBlocks;
import deco.block.blocks.legacy.LegacyDecoSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class CherryTreeGen extends WorldGenerator {
	public boolean generate(World var0, Random var1, int var2, int var3, int var4) {
		return ((LegacyDecoSaplingBlock) DecoBlocks.legacyCherrySapling).generateTree(var0, var1, var2, var3, var4, 0);
	}
}
