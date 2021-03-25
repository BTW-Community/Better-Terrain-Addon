package net.minecraft.src;

public class BTAGenLayerIsland extends GenLayerIsland
{
	private int oceanSize;
	
    public BTAGenLayerIsland(long par1, int oceanSize)
    {
        super(par1);
        this.oceanSize = oceanSize;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = IntCache.getIntCache(par3 * par4);

        for (int var6 = 0; var6 < par4; ++var6)
        {
            for (int var7 = 0; var7 < par3; ++var7)
            {
                this.initChunkSeed((long)(par1 + var7), (long)(par2 + var6));
                var5[var7 + var6 * par3] = this.nextInt(10) < oceanSize ? 1 : 0;
            }
        }

        if (par1 > -par3 && par1 <= 0 && par2 > -par4 && par2 <= 0)
        {
            var5[-par1 + -par2 * par3] = 1;
        }

        return var5;
    }
}
