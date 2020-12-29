package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.Map;

public class BTAChunk extends Chunk {
	public BTAChunk(World par1World, int[] blockArray, int par3, int par4) {
        super(par1World, par3, par4);
        int var5 = blockArray.length / 256;

        for (int var6 = 0; var6 < 16; ++var6)
        {
            for (int var7 = 0; var7 < 16; ++var7)
            {
                for (int var8 = 0; var8 < var5; ++var8)
                {
                    int var9 = blockArray[var6 << 11 | var7 << 7 | var8];

                    if (var9 != 0)
                    {
                        int var10 = var8 >> 4;

                        if (getBlockStorageArray()[var10] == null)
                        {
                        	getBlockStorageArray()[var10] = new ExtendedBlockStorage(var10 << 4, !par1World.provider.hasNoSky);
                        }

                        getBlockStorageArray()[var10].setExtBlockID(var6, var8 & 15, var7, var9);
                    }
                }
            }
        }
	}
}