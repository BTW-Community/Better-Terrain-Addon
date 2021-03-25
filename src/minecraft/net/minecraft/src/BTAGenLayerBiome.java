package net.minecraft.src;

import java.util.ArrayList;

public class BTAGenLayerBiome extends BTAGenLayer {
	private ArrayList<BTABiomeGenBase> biomesForGeneration;

    public BTAGenLayerBiome(long par1, GenLayer par3GenLayer, WorldType par4WorldType, ArrayList<BTABiomeGenBase> biomesForGeneration)
    {
        super(par1);
		parent = par3GenLayer;
		this.biomesForGeneration = biomesForGeneration;
		
		/*
		 * if (!par4WorldType.isDeco())
			biomesForGeneration = BTABiomeConfiguration.getBiomes();
		else
			biomesForGeneration = BTABiomeConfiguration.getBiomesDeco();
		*/
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