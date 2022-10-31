package betterterrain.feature.terrain;

import java.util.Random;

import betterterrain.BTAMod;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class NetherLavaGen extends WorldGenerator {
	private boolean isOpenToAir;
	
	public NetherLavaGen(boolean isOpenToAir) {
		this.isOpenToAir = isOpenToAir;
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (!this.isBlockValidForReplacement(world.getBlockId(x, y + 1, z))) {
            return false;
        }
        else if (world.getBlockId(x, y, z) != 0 && !this.isBlockValidForReplacement(world.getBlockId(x, y, z))) {
            return false;
        }
        else {
            int solidBlockCount = 0;

            if (world.getBlockId(x - 1, y, z) != 0)  {
                solidBlockCount++;
            }

            if (world.getBlockId(x + 1, y, z) != 0)  {
                solidBlockCount++;
            }

            if (world.getBlockId(x, y, z - 1) != 0)  {
                solidBlockCount++;
            }

            if (world.getBlockId(x, y, z + 1) != 0)  {
                solidBlockCount++;
            }

            if (world.getBlockId(x, y - 1, z) != 0) {
                solidBlockCount++;
            }

            int airCount = 0;

            if (world.isAirBlock(x - 1, y, z)) {
                airCount++;
            }

            if (world.isAirBlock(x + 1, y, z)) {
                airCount++;
            }

            if (world.isAirBlock(x, y, z - 1)) {
                airCount++;
            }

            if (world.isAirBlock(x, y, z + 1)) {
                airCount++;
            }

            if (world.isAirBlock(x, y - 1, z)) {
                airCount++;
            }

            if (!this.isOpenToAir && solidBlockCount == 4 && airCount == 1 || solidBlockCount == 5) {
                world.setBlock(x, y, z, Block.lavaMoving.blockID, 0, 2);
                world.scheduledUpdatesAreImmediate = true;
                Block.blocksList[Block.lavaMoving.blockID].updateTick(world, x, y, z, rand);
                world.scheduledUpdatesAreImmediate = false;
            }

            return true;
        }
    }
	
	private boolean isBlockValidForReplacement(int blockID) {
		return blockID == Block.netherrack.blockID || (BTAMod.isDecoInstalled() && blockID == DecoBlocks.basalt.blockID) || blockID == Block.blockNetherQuartz.blockID;
	}
}