package betterbiomes.feature.tree;

import deco.block.DecoBlocks;
import deco.block.blocks.FirSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class FirGen extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return ((FirSaplingBlock) DecoBlocks.firSapling).generateFir1(world, rand, x, y, z);
    }
}
