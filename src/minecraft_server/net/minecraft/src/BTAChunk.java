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
	public BTAChunk(World par1World, int[] blockArray, int[] metaArray, int par3, int par4) {
        super(par1World, par3, par4);
        int var5 = blockArray.length / 256;

        for (int i = 0; i < 16; ++i)
        {
            for (int k = 0; k < 16; ++k)
            {
                for (int j = 0; j < var5; ++j)
                {
                    int blockID = blockArray[i << 11 | k << 7 | j];
                    int meta = metaArray[i << 11 | k << 7 | j];

                    if (blockID != 0)
                    {
                        int var10 = j >> 4;

                        if (getBlockStorageArray()[var10] == null)
                        {
                        	getBlockStorageArray()[var10] = new ExtendedBlockStorage(var10 << 4, !par1World.provider.hasNoSky);
                        }

                        getBlockStorageArray()[var10].setExtBlockID(i, j & 15, k, blockID);
                        getBlockStorageArray()[var10].setExtBlockMetadata(i, j & 15, k, meta);
                    }
                }
            }
        }
	}
	
	public BTAChunk(World world, int[][][] blockArray, int chunkX, int chunkZ) {
        super(world, chunkX, chunkZ);

        for (int i = 0; i < 16; ++i)
        {
            for (int k = 0; k < 16; ++k)
            {
                for (int j = 0; j < 256; ++j)
                {
                    int blockID = blockArray[i][k][j];

                    if (blockID != 0)
                    {
                        int extIndex = j >> 4;

                        if (getBlockStorageArray()[extIndex] == null)
                        {
                        	getBlockStorageArray()[extIndex] = new ExtendedBlockStorage(extIndex << 4, !world.provider.hasNoSky);
                        }

                        getBlockStorageArray()[extIndex].setExtBlockID(i, j & 15, k, blockID);
                    }
                }
            }
        }
	}
}