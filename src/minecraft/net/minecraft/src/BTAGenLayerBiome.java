package net.minecraft.src;

import java.util.ArrayList;

public class BTAGenLayerBiome extends BTAGenLayer {
	private ArrayList<BTABiomeGenBase> biomelist;

    public BTAGenLayerBiome(long par1, GenLayer par3GenLayer, WorldType par4WorldType)
    {
        super(par1);
		parent = par3GenLayer;
		
		if (par4WorldType == BTAMod.BTWGWorldType)
			biomelist = BTABiomeConfiguration.getBiomes();
		else
			biomelist = BTABiomeConfiguration.getBiomesDeco();
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
					var6[var8 + var7 * par3] = biomelist.get(this.nextInt(biomelist.size())).biomeID;
				}
            }
        }
        return var6;
    }
}