package net.minecraft.src;

public abstract class BTAGenLayer extends GenLayer {

	public BTAGenLayer(long par1) {
		super(par1);
	}

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        GenLayerIsland var3 = new GenLayerIsland(1L);
        GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
        GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
        GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
        var10 = new GenLayerAddIsland(2L, var11);
        //GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
        var11 = new GenLayerZoom(2002L, var10);
        var10 = new GenLayerAddIsland(3L, var11);
        var11 = new GenLayerZoom(2003L, var10);
        var10 = new GenLayerAddIsland(4L, var11);
        GenLayerAddMushroomIsland var15 = new GenLayerAddMushroomIsland(5L, var10);
        byte var4 = 5;

        GenLayer var5 = GenLayerZoom.magnify(1000L, var15, 0);
        GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
        var5 = GenLayerZoom.magnify(1000L, var13, var4 + 2);
        GenLayerRiver var14 = new GenLayerRiver(1L, var5);
        GenLayerSmooth var16 = new GenLayerSmooth(1000L, var14);
        GenLayer var6 = GenLayerZoom.magnify(1000L, var15, 0);
        BTAGenLayerBiome var17 = new BTAGenLayerBiome(200L, var6, par2WorldType);
        var6 = GenLayerZoom.magnify(1000L, var17, 2);
        Object var18 = new BTAGenLayerHills(1000L, var6);

        for (int var7 = 0; var7 < var4; ++var7)
        {
            var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);

            if (var7 == 0)
            {
                var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new BTAGenLayerShore(1000L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
            }
        }

        GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
        BTAGenLayerRiverMix var20 = new BTAGenLayerRiverMix(100L, var19, var16);
        GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
        var20.initWorldGenSeed(par0);
        var8.initWorldGenSeed(par0);
        return new GenLayer[] {var20, var8, var20};
    }
}