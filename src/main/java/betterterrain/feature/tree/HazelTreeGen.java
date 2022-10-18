package betterterrain.feature.tree;

import deco.block.DecoBlocks;
import deco.block.blocks.DecoSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class HazelTreeGen extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return ((DecoSaplingBlock) DecoBlocks.hazelSapling).generateTree(world, rand, x, y, z, 0);
    }
}
