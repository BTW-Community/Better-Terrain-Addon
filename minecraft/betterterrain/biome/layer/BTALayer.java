package betterterrain.biome.layer;

import java.util.ArrayList;

import betterterrain.BTAVersion;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.BTAMod;
import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerAddIsland;
import net.minecraft.src.GenLayerAddMushroomIsland;
import net.minecraft.src.GenLayerFuzzyZoom;
import net.minecraft.src.GenLayerRiver;
import net.minecraft.src.GenLayerRiverInit;
import net.minecraft.src.GenLayerSmooth;
import net.minecraft.src.GenLayerVoronoiZoom;
import net.minecraft.src.GenLayerZoom;
import net.minecraft.src.WorldType;

public abstract class BTALayer extends GenLayer {

	public BTALayer(long par1) {
		super(par1);
	}

	/**
	 * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
	 * first.
	 */
	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, WorldConfigurationInfo generatorInfo) {
		//if (generatorInfo.getGenerator() == BTAEnumTerrainGenerator.SIMPLEX) {
		if (1 == 2) {
			return null;
		}
		else {
			ContinentsLayer layerContinents = new ContinentsLayer(1L, generatorInfo.getOceanSize());
			GenLayerFuzzyZoom layerFuzzyZoom = new GenLayerFuzzyZoom(2000L, layerContinents);

			GenLayerAddIsland layerIsland = new GenLayerAddIsland(1L, layerFuzzyZoom);
			GenLayerZoom layerZoom = new GenLayerZoom(2001L, layerIsland);
			layerIsland = new GenLayerAddIsland(2L, layerZoom);

			ClimatesLayer layerClimates = new ClimatesLayer(2L, layerIsland, generatorInfo.getBiomesForGeneration());

			layerZoom = new GenLayerZoom(2002L, layerIsland);
			layerIsland = new GenLayerAddIsland(3L, layerZoom);
			layerZoom = new GenLayerZoom(2003L, layerIsland);
			layerIsland = new GenLayerAddIsland(4L, layerZoom);
			GenLayerAddMushroomIsland layerMushroomIsland = new GenLayerAddMushroomIsland(5L, layerIsland);

			int scale = 3 + generatorInfo.getBiomeSize();

			if (worldType == BTAMod.BTAWorldTypeSmall || worldType == BTAMod.BTAWorldTypeSmallDeco)
				scale = 4;

			GenLayer layerMagnifyRiver = GenLayerZoom.magnify(1000L, layerMushroomIsland, 0);
			GenLayerRiverInit layerRiverInit = new GenLayerRiverInit(100L, layerMagnifyRiver);
			layerMagnifyRiver = GenLayerZoom.magnify(1000L, layerRiverInit, scale + 2);
			GenLayerRiver layerRiver = new GenLayerRiver(1L, layerMagnifyRiver);
			GenLayerSmooth layerSmoothRivers = new GenLayerSmooth(1000L, layerRiver);

			GenLayer layerMangnifyBiome = GenLayerZoom.magnify(1000L, layerMushroomIsland, 0);
			GenLayer layerBiome;
			if (generatorInfo.isClimatized() && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_0)) {
				GenLayer layerClimateZoom = GenLayerZoom.magnify(1000l, layerClimates, 2);
				GenLayer layerClimateSmooth = new ClimateSmoothLayer(2000L, layerClimateZoom, generatorInfo.getBiomesForGeneration());
				layerClimateSmooth = new ClimateSmoothLayer2(2000L, layerClimateSmooth, generatorInfo.getBiomesForGeneration());
				layerClimateSmooth.initWorldGenSeed(seed);

				layerBiome = new BiomeClimatizedLayer(200L, layerMangnifyBiome, layerClimateSmooth, generatorInfo.getBiomesForGeneration());
			}
			else {
				layerBiome = new BiomeLayer(200L, layerMangnifyBiome, worldType, generatorInfo.getBiomesForGeneration());
			}

			layerMangnifyBiome = GenLayerZoom.magnify(1000L, layerBiome, 2);
			GenLayer layerExtras = new HillsLayer(1000L, layerMangnifyBiome, generatorInfo);

			for (int passNum = 0; passNum < scale; passNum++) {
				layerExtras = new GenLayerZoom((long)(1000 + passNum), layerExtras);

				if (passNum == 0) {
					layerExtras = new GenLayerAddIsland(3L, layerExtras);
				}

				if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
					if (passNum == 0) {
						layerExtras = new ShoreLayer(1000L, layerExtras, generatorInfo, 0);
					}
				}

				if (passNum == 1) {
					layerExtras = new ShoreLayer(1000L, layerExtras, generatorInfo, 1);

					if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_4_0))
						layerExtras = new GuaranteedShoreLayer(1000L, layerExtras, generatorInfo);
				}

				if (passNum == scale - 3) {
					layerExtras = new SporadicLayer(1000L, layerExtras, generatorInfo);
				}
			}

			GenLayerSmooth layerSmoothBiome = new GenLayerSmooth(1000L, layerExtras);
			RiverLayer layerRiverMix = new RiverLayer(100L, layerSmoothBiome, layerSmoothRivers);
			GenLayerVoronoiZoom layerVoronoiZoom = new GenLayerVoronoiZoom(10L, layerRiverMix);

			layerRiverMix.initWorldGenSeed(seed);
			layerVoronoiZoom.initWorldGenSeed(seed);
			return new GenLayer[] {layerRiverMix, layerVoronoiZoom, layerRiverMix};
		}
	}
}