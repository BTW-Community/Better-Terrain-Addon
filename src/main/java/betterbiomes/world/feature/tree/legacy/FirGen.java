package betterbiomes.world.feature.tree.legacy;

import deco.block.DecoBlocks;
import deco.block.blocks.legacy.LegacyFirSaplingBlock;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class FirGen extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        return ((LegacyFirSaplingBlock) DecoBlocks.legacyFirSapling).generateFir1(world, rand, x, y, z);
    }
}
