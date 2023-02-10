package betterterrain.world.feature.plant;

import java.util.ArrayList;
import java.util.Random;

import deco.block.DecoBlocks;
import deco.block.blocks.DecoFlowerBlock;
import deco.block.blocks.TallPlantBlock;
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
		int plantMeta = plantMetadata;
		
		if (plantBlockId == -1) {
			ArrayList<Integer> spawnableFlowers = ((DecoFlowerBlock) DecoBlocks.flower).getSpawnableList();
			ArrayList<Integer> spawnableFlowers2 = ((DecoFlowerBlock) DecoBlocks.flower2).getSpawnableList();
			ArrayList<Integer> spawnableTulips = ((DecoFlowerBlock) DecoBlocks.tulip).getSpawnableList();

			// +2 is for vanilla flowers
			int totalSpawnableFlowerCount = spawnableFlowers.size() + spawnableFlowers2.size() + spawnableTulips.size() + 2;

			int flowerIndex = rand.nextInt(totalSpawnableFlowerCount);

			if (flowerIndex < spawnableFlowers.size()) {
				plantID = DecoBlocks.flower.blockID;
				plantMeta = spawnableFlowers.get(flowerIndex);
			}
			else {
				flowerIndex -= spawnableFlowers.size();

				if (flowerIndex == 0) {
					plantID = Block.plantRed.blockID;
					plantMeta = 0;
				}
				else if (flowerIndex == 1) {
					plantID = Block.plantYellow.blockID;
					plantMeta = 0;
				}
				else {
					flowerIndex -= 2;

					if (flowerIndex < spawnableFlowers2.size()) {
						plantID = DecoBlocks.flower2.blockID;
						plantMeta = spawnableFlowers2.get(flowerIndex);
					}
					else {
						flowerIndex -= spawnableFlowers2.size();

						if (flowerIndex < spawnableTulips.size()) {
							plantID = DecoBlocks.tulip.blockID;
							plantMeta = spawnableTulips.get(flowerIndex);
						}
					}
				}
			}
		}

		for (int var6 = 0; var6 < 64; ++var6)
		{
			int var7 = par3 + rand.nextInt(8) - rand.nextInt(8);
			int var8 = par4 + rand.nextInt(4) - rand.nextInt(4);
			int var9 = par5 + rand.nextInt(8) - rand.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && Block.blocksList[plantID].canBlockStayDuringGenerate(par1World, var7, var8, var9))
			{
				par1World.setBlock(var7, var8, var9, plantID, plantMeta, 2);
                
                if (par1World.getBiomeGenForCoords(var7, var9).getEnableSnow() && par1World.isAirBlock(var7, var8 + 1, var9) && par1World.canBlockSeeTheSky(var7, var8, var9)) {
                	par1World.setBlock(var7, var8 + 1, var9, Block.snow.blockID);
                }
			}
		}

		return true;
	}
}
