package net.minecraft.src;

import java.util.ArrayList;

public abstract class BTAGenLayer extends GenLayer {

	public BTAGenLayer(long par1) {
		super(par1);
	}

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, BTAWorldConfigurationInfo generatorInfo) {
    	if (generatorInfo.isClimatized() && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_0)) {
    		return initBiomesClimatized(seed, worldType, generatorInfo);
    	}
    	else {
    		return initBiomesRandom(seed, worldType, generatorInfo);
    	}
    }
    
    private static GenLayer[] initBiomesClimatized(long seed, WorldType worldType, BTAWorldConfigurationInfo generatorInfo) {
    	BTAGenLayerContinents layerContinents = new BTAGenLayerContinents(1L, generatorInfo.getOceanSize());
        GenLayerFuzzyZoom layerFuzzyZoom = new GenLayerFuzzyZoom(2000L, layerContinents);
        
        GenLayerAddIsland layerIsland = new GenLayerAddIsland(1L, layerFuzzyZoom);
        GenLayerZoom layerZoom = new GenLayerZoom(2001L, layerIsland);
        layerIsland = new GenLayerAddIsland(2L, layerZoom);
        
        BTAGenLayerClimates layerClimates = new BTAGenLayerClimates(2L, layerIsland, generatorInfo.getBiomesForGeneration());
        
        layerZoom = new GenLayerZoom(2002L, layerIsland);
        layerIsland = new GenLayerAddIsland(3L, layerZoom);
        layerZoom = new GenLayerZoom(2003L, layerIsland);
        layerIsland = new GenLayerAddIsland(4L, layerZoom);
        GenLayerAddMushroomIsland layerMushroomIsland = new GenLayerAddMushroomIsland(5L, layerIsland);
        
        byte scale = 5;
        
        if (worldType == BTAMod.BTAWorldTypeSmall || worldType == BTAMod.BTAWorldTypeSmallDeco)
        	scale = 4;

        GenLayer layerMagnifyRiver = GenLayerZoom.magnify(1000L, layerMushroomIsland, 0);
        GenLayerRiverInit layerRiverInit = new GenLayerRiverInit(100L, layerMagnifyRiver);
        layerMagnifyRiver = GenLayerZoom.magnify(1000L, layerRiverInit, scale + 2);
        GenLayerRiver layerRiver = new GenLayerRiver(1L, layerMagnifyRiver);
        GenLayerSmooth layerSmoothRivers = new GenLayerSmooth(1000L, layerRiver);
        GenLayer layerMangnifyBiome = GenLayerZoom.magnify(1000L, layerMushroomIsland, 0);
        
        GenLayer layerClimateZoom = GenLayerZoom.magnify(1000l, layerClimates, 3);
        BTAGenLayerBiomeClimatized layerBiome = new BTAGenLayerBiomeClimatized(200L, layerMangnifyBiome, layerClimateZoom, generatorInfo.getBiomesForGeneration());
        
        layerMangnifyBiome = GenLayerZoom.magnify(1000L, layerBiome, 2);
        Object layerExtras = new BTAGenLayerHills(1000L, layerMangnifyBiome);

        for (int passNum = 0; passNum < scale; passNum++) {
            layerExtras = new GenLayerZoom((long)(1000 + passNum), (GenLayer)layerExtras);

            if (passNum == 0) {
                layerExtras = new GenLayerAddIsland(3L, (GenLayer)layerExtras);
            }

            if (passNum == 1) {
                layerExtras = new BTAGenLayerShore(1000L, (GenLayer)layerExtras);
            }

            if (passNum == scale - 3) {
                layerExtras = new BTAGenLayerSporadic(1000L, (GenLayer)layerExtras, generatorInfo);
            }
        }

        GenLayerSmooth layerSmoothBiome = new GenLayerSmooth(1000L, (GenLayer)layerExtras);
        BTAGenLayerRiverMix layerRiverMix = new BTAGenLayerRiverMix(100L, layerSmoothBiome, layerSmoothRivers);
        GenLayerVoronoiZoom layerVoronoiZoom = new GenLayerVoronoiZoom(10L, layerRiverMix);
        layerRiverMix.initWorldGenSeed(seed);
        layerVoronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[] {layerRiverMix, layerVoronoiZoom, layerRiverMix};
    }
    
    private static GenLayer[] initBiomesRandom(long seed, WorldType worldType, BTAWorldConfigurationInfo generatorInfo) {
    	BTAGenLayerContinents layerContinents = new BTAGenLayerContinents(1L, generatorInfo.getOceanSize());
        GenLayerFuzzyZoom layerFuzzyZoom = new GenLayerFuzzyZoom(2000L, layerContinents);
        
        GenLayerAddIsland layerIsland = new GenLayerAddIsland(1L, layerFuzzyZoom);
        GenLayerZoom layerZoom = new GenLayerZoom(2001L, layerIsland);
        layerIsland = new GenLayerAddIsland(2L, layerZoom);
        layerZoom = new GenLayerZoom(2002L, layerIsland);
        layerIsland = new GenLayerAddIsland(3L, layerZoom);
        layerZoom = new GenLayerZoom(2003L, layerIsland);
        layerIsland = new GenLayerAddIsland(4L, layerZoom);
        GenLayerAddMushroomIsland layerMushroomIsland = new GenLayerAddMushroomIsland(5L, layerIsland);
        
        byte scale = 5;
        
        if (worldType == BTAMod.BTAWorldTypeSmall || worldType == BTAMod.BTAWorldTypeSmallDeco)
        	scale = 4;

        GenLayer layerMagnifyRiver = GenLayerZoom.magnify(1000L, layerMushroomIsland, 0);
        GenLayerRiverInit layerRiverInit = new GenLayerRiverInit(100L, layerMagnifyRiver);
        layerMagnifyRiver = GenLayerZoom.magnify(1000L, layerRiverInit, scale + 2);
        GenLayerRiver layerRiver = new GenLayerRiver(1L, layerMagnifyRiver);
        GenLayerSmooth layerSmoothRivers = new GenLayerSmooth(1000L, layerRiver);
        GenLayer layerMangnifyBiome = GenLayerZoom.magnify(1000L, layerMushroomIsland, 0);
        BTAGenLayerBiome layerBiome = new BTAGenLayerBiome(200L, layerMangnifyBiome, worldType, generatorInfo.getBiomesForGeneration());
        layerMangnifyBiome = GenLayerZoom.magnify(1000L, layerBiome, 2);
        Object layerExtras = new BTAGenLayerHills(1000L, layerMangnifyBiome);

        for (int passNum = 0; passNum < scale; passNum++) {
            layerExtras = new GenLayerZoom((long)(1000 + passNum), (GenLayer)layerExtras);

            if (passNum == 0) {
                layerExtras = new GenLayerAddIsland(3L, (GenLayer)layerExtras);
            }

            if (passNum == 1) {
                layerExtras = new BTAGenLayerShore(1000L, (GenLayer)layerExtras);
            }

            if (passNum == scale - 3) {
                layerExtras = new BTAGenLayerSporadic(1000L, (GenLayer)layerExtras, generatorInfo);
            }
        }

        GenLayerSmooth layerSmoothBiome = new GenLayerSmooth(1000L, (GenLayer)layerExtras);
        BTAGenLayerRiverMix layerRiverMix = new BTAGenLayerRiverMix(100L, layerSmoothBiome, layerSmoothRivers);
        GenLayerVoronoiZoom layerVoronoiZoom = new GenLayerVoronoiZoom(10L, layerRiverMix);
        layerRiverMix.initWorldGenSeed(seed);
        layerVoronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[] {layerRiverMix, layerVoronoiZoom, layerRiverMix};
    }
}