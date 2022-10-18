package betterbiomes.biome.biomes;

import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class SmallFirShrub extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (world.getBlockId(x, y - 1, z) != Block.grass.blockID)
            return false;

        world.setBlock(x, y, z, DecoBlocks.firLog.blockID);
        world.setBlock(x, y + 1, z, DecoBlocks.firLog.blockID);

        world.setBlock(x + 1, y + 1, z, DecoBlocks.firLeaves.blockID);
        world.setBlock(x, y + 1, z + 1, DecoBlocks.firLeaves.blockID);
        world.setBlock(x - 1, y + 1, z, DecoBlocks.firLeaves.blockID);
        world.setBlock(x, y + 1, z - 1, DecoBlocks.firLeaves.blockID);
        world.setBlock(x, y + 2, z, DecoBlocks.firLeaves.blockID);

        return true;
    }
}
