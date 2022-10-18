package betterterrain.feature.plant;

import deco.block.DecoBlocks;
import deco.block.blocks.DecoFlowerBlock;
import deco.block.blocks.TallPlantBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class TallFlowerGen extends WorldGenerator {
    /** The ID of the plant block used in this plant generator. */
    private int plantBlockId;
    private int plantMetadata;

    public TallFlowerGen()
    {
        plantBlockId = -1;
        plantMetadata = -1;
    }

    public TallFlowerGen(int blockID)
    {
        this.plantBlockId = blockID;
        plantMetadata = 0;
    }

    public TallFlowerGen(int blockID, int metadata)
    {
        this.plantBlockId = blockID;
        plantMetadata = metadata;
    }

    public boolean generate(World par1World, Random rand, int par3, int par4, int par5)
    {
        int plantID = plantBlockId;
        int plantMeta = 0;

        if (plantBlockId == -1) {
            int flowerIndex = rand.nextInt(((TallPlantBlock) DecoBlocks.tallFlower).getSpawnableList().size());

            plantID = DecoBlocks.tallFlower.blockID;
            plantMeta = flowerIndex;
        }

        for (int var6 = 0; var6 < 64; ++var6)
        {
            int var7 = par3 + rand.nextInt(8) - rand.nextInt(8);
            int var8 = par4 + rand.nextInt(4) - rand.nextInt(4);
            int var9 = par5 + rand.nextInt(8) - rand.nextInt(8);

            if (par1World.isAirBlock(var7, var8, var9) && par1World.isAirBlock(var7, var8 + 1, var9) && (!par1World.provider.hasNoSky || var8 < 127)) {
                System.out.println("Success?");
                if (DecoBlocks.flower.canBlockStay(par1World, var7, var8, var9)) {
                    System.out.println("Success!");
                    par1World.setBlock(var7, var8, var9, plantID, plantMeta, 2);
                    par1World.setBlock(var7, var8 + 1, var9, plantID, plantMeta | 8, 2);
                }
            }
        }

        return true;
    }
}
