package betterterrain.world.feature.tree.legacy;

import deco.block.DecoBlocks;
import deco.block.blocks.legacy.LegacyDecoSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class HazelTreeGen extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return ((LegacyDecoSaplingBlock) DecoBlocks.legacyHazelSapling).generateTree(world, rand, x, y, z, 0);
    }
}
