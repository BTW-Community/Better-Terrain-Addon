package betterterrain.world.feature.plant;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class MelonGen extends WorldGenerator {
	private static ArrayList<BiomeGenBase> validBiomeList = new ArrayList();
	
	public static boolean isBiomeValid(BiomeGenBase biome) {
		return validBiomeList.contains(biome);
	}
	
	public static void addBiomeToGenerator(BiomeGenBase biome) {
		validBiomeList.add(biome);
	}
	
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        boolean var6 = isBiomeValid(par1World.getBiomeGenForCoords(par3, par5));
        int var7 = 0;

        for (int var9 = 0; var9 < 64; ++var9)
        {
            int var10 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var11 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var12 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var10, var11, var12) && par1World.getBlockId(var10, var11 - 1, var12) == Block.grass.blockID && Block.pumpkin.canPlaceBlockAt(par1World, var10, var11, var12))
            {
                int var13 = par2Random.nextInt(4);

                if (var6 && var7 < 3)
                {
                    par1World.setBlock(var10, var11, var12, Block.melon.blockID, var13, 2);

                    ++var7;
                }
            }
        }

        return true;
    }
}