package betterterrain.feature.plant;

import java.util.Random;

import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class DecoFlowerGen extends WorldGenerator
{
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;
	private int plantMetadata;

	public DecoFlowerGen()
	{
		plantBlockId = -1;
		plantMetadata = -1;
	}

	public DecoFlowerGen(int blockID)
	{
		this.plantBlockId = blockID;
		plantMetadata = 0;
	}
	
	public DecoFlowerGen(int blockID, int metadata)
	{
		this.plantBlockId = blockID;
		plantMetadata = metadata;
	}

	public boolean generate(World par1World, Random rand, int par3, int par4, int par5)
	{
		int plantID = plantBlockId;
		
		if (plantBlockId == -1) {
			int i = rand.nextInt(23);

			if (i < 16) {
				plantID = DecoBlocks.flower.blockID;
				plantMetadata = i;
			}
			else if (i < 18) {
				plantID = DecoBlocks.flower2.blockID;
				plantMetadata = i - 16;
			}
			else {
				plantID = DecoBlocks.tulip.blockID;
				plantMetadata = i - 18;
			}
		}

		for (int var6 = 0; var6 < 64; ++var6)
		{
			int var7 = par3 + rand.nextInt(8) - rand.nextInt(8);
			int var8 = par4 + rand.nextInt(4) - rand.nextInt(4);
			int var9 = par5 + rand.nextInt(8) - rand.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && Block.blocksList[plantID].canBlockStayDuringGenerate(par1World, var7, var8, var9))
			{
				par1World.setBlock(var7, var8, var9, plantID, this.plantMetadata, 2);
                
                if (par1World.getBiomeGenForCoords(var7, var9).getEnableSnow() && par1World.isAirBlock(var7, var8 + 1, var9) && par1World.canBlockSeeTheSky(var7, var8, var9)) {
                	par1World.setBlock(var7, var8 + 1, var9, Block.snow.blockID);
                }
			}
		}

		return true;
	}
}