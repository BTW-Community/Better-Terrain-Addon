package betterterrain.biome.layer;

import java.util.ArrayList;

import betterterrain.biome.BTABiome;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.WorldType;

public class BiomeLayer extends BTALayer {
	private ArrayList<BTABiome> biomesForGeneration;

    public BiomeLayer(long par1, GenLayer par3GenLayer, WorldType par4WorldType, ArrayList<BTABiome> biomesForGeneration)
    {
        super(par1);
		parent = par3GenLayer;
		this.biomesForGeneration = biomesForGeneration;
	} 

    @Override
	public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1, par2, par3, par4);
        int[] var6 = IntCache.getIntCache(par3 * par4);
        
        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + var7 * par3];
				if (var9 == 0)
				{
					var6[var8 + var7 * par3] = 0;
				}
				else 
				{
					var6[var8 + var7 * par3] = biomesForGeneration.get(this.nextInt(biomesForGeneration.size())).biomeID;
				}
            }
        }
        return var6;
    }
}