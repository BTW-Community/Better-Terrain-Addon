package net.minecraft.src;

public abstract class BTAHorizonsGenLayer extends GenLayer {

	public BTAHorizonsGenLayer(long par1) {
		super(par1);
	}

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType)
    {
    	BTAHorizonsGenLayerContinents var3 = new BTAHorizonsGenLayerContinents(1L);
        GenLayerFuzzyZoom layerFuzzyZoom = new GenLayerFuzzyZoom(2000L, var3);
        GenLayerAddIsland layerAddIsland = new GenLayerAddIsland(1L, layerFuzzyZoom);
        GenLayerZoom layerZoom = new GenLayerZoom(2001L, layerAddIsland);
        layerAddIsland = new GenLayerAddIsland(2L, layerZoom);
        layerZoom = new GenLayerZoom(2002L, layerAddIsland);
        layerAddIsland = new GenLayerAddIsland(3L, layerZoom);
        layerZoom = new GenLayerZoom(2003L, layerAddIsland);
        layerAddIsland = new GenLayerAddIsland(4L, layerZoom);
        GenLayerAddMushroomIsland var15 = new GenLayerAddMushroomIsland(5L, layerAddIsland);
        byte zoomFactor = 5;

        GenLayer var5 = GenLayerZoom.magnify(1000L, var15, 0);
        GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
        var5 = GenLayerZoom.magnify(1000L, var13, zoomFactor + 2);
        GenLayerRiver var14 = new GenLayerRiver(1L, var5);
        GenLayerSmooth var16 = new GenLayerSmooth(1000L, var14);
        GenLayer var6 = GenLayerZoom.magnify(1000L, var15, 0);
        BTAGenLayerBiome var17 = new BTAGenLayerBiome(200L, var6, worldType, BTABiomeConfiguration.biomeListDeco);
        var6 = GenLayerZoom.magnify(1000L, var17, 2);
        Object var18 = new GenLayerHills(1000L, var6);

        for (int var7 = 0; var7 < zoomFactor; ++var7)
        {
            var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);

            if (var7 == 0)
            {
                var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                //var18 = new BTAGenLayerShore(1000L, (GenLayer)var18);
            }

            if (var7 == 1)
            {
                var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
            }
        }

        GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
        BTAGenLayerRiverMix var20 = new BTAGenLayerRiverMix(100L, var19, var16);
        GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
        var20.initWorldGenSeed(seed);
        var8.initWorldGenSeed(seed);
        return new GenLayer[] {var20, var8, var20};
    }
}