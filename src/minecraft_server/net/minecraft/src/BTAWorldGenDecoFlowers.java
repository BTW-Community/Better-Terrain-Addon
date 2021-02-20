package net.minecraft.src;

import java.util.Random;

public class BTAWorldGenDecoFlowers extends WorldGenerator
{
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;
	private int plantMetadata;

	public BTAWorldGenDecoFlowers()
	{
		plantBlockId = -1;
		plantMetadata = -1;
	}

	public BTAWorldGenDecoFlowers(int par1)
	{
		this.plantBlockId = par1;
		plantMetadata = 0;
	}

	public boolean generate(World par1World, Random rand, int par3, int par4, int par5)
	{
		int plantID = plantBlockId;
		
		if (plantBlockId == -1) {
			int i = rand.nextInt(23);

			if (i < 16) {
				plantID = BTADecoIntegration.flower.blockID;
				plantMetadata = i;
			}
			else if (i < 18) {
				plantID = BTADecoIntegration.flower2.blockID;
				plantMetadata = i - 16;
			}
			else {
				plantID = BTADecoIntegration.tulip.blockID;
				plantMetadata = i - 18;
			}
		}

		for (int var6 = 0; var6 < 64; ++var6)
		{
			int var7 = par3 + rand.nextInt(8) - rand.nextInt(8);
			int var8 = par4 + rand.nextInt(4) - rand.nextInt(4);
			int var9 = par5 + rand.nextInt(8) - rand.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && Block.blocksList[plantID].CanBlockStayDuringGenerate(par1World, var7, var8, var9))
			{
				par1World.setBlock(var7, var8, var9, plantID, this.plantMetadata, 2);
			}
		}

		return true;
	}
}
