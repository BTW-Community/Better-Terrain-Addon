package betterterrain.biome;

import java.util.ArrayList;

import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.IcyPeaksSurfaceBuilder;
import betterterrain.BTAVersion;
import betterterrain.biome.biomes.AridForestBiome;
import betterterrain.biome.biomes.BeachBiome;
import betterterrain.biome.biomes.DesertBiome;
import betterterrain.biome.biomes.DesertRiverBiome;
import betterterrain.biome.biomes.IcyPeaksBiome;
import betterterrain.biome.biomes.JungleBiome;
import betterterrain.biome.biomes.JungleEdgeBiome;
import betterterrain.biome.biomes.JungleRiverBiome;
import betterterrain.biome.biomes.MountainBiome;
import betterterrain.biome.biomes.NetherWastesBiome;
import betterterrain.biome.biomes.PlainsBiome;
import betterterrain.biome.biomes.RiverBiome;
import betterterrain.biome.biomes.SiberiaBiome;
import betterterrain.biome.biomes.SwampBiome;
import betterterrain.biome.biomes.SwampRiverBiome;
import betterterrain.biome.biomes.TundraBiome;
import betterterrain.biome.biomes.WoodsBiome;
import betterterrain.biome.biomes.deprecated.ForestedIcyPeaksBiome;
import betterterrain.world.generate.surface.NetherSurfaceBuilder;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.generate.surface.StonySurfaceBuilder;
import betterterrain.world.generate.surface.SwampSurfaceBuilder;
import net.minecraft.src.FCAddOnHandler;

public class BTABiomeConfiguration extends BiomeConfiguration {
	public static final int
	SWAMP_ID = 50,
	SWAMP_RIVER_ID = 51,
	
	NETHER_WASTES_ID = 90,
	
	WOODS_ID = 100,
	DESERT_ID = 101,
	JUNGLE_ID = 113,
	MOUNTAINS_ID = 125,
	TUNDRA_ID = 132,
	ICY_PEAKS_ID = 134,
	SIBERIA_ID = 137,
	PLAINS_ID = 138,
	ARID_FOREST_ID = 142,
	
	WOODS_HILLS_ID = 150,
	DESERT_HILLS_ID = 151,
	JUNGLE_HILLS_ID = 157,
	ICY_PEAKS_FORESTED_ID = 165,

	DESERT_RIVER_ID = 200,
	JUNGLE_RIVER_ID = 207,
	RIVER_ID = 211,
	FROZEN_RIVER_ID = 212,

	MOUNTAIN_EDGE_ID = 231,
	ICY_PEAKS_EDGE_ID = 233,
	JUNGLE_EDGE_ID = 235,

	BEACH_ID = 241,
	FROZEN_BEACH_ID = 242,
	
	max_id = 4096;
	
	//------ Primary Biomes ------//
	
	public static final BTABiome desert = new DesertBiome(DESERT_ID, "betterterrain:desert", Climate.ARID)
			.setBiomeName("Better Desert")
			.setSpawnsVillages(true)
			.setDisableRain()
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.2F, 0.4F)
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setNotSpawnable()
			.setLegacyCompatible();

	public static final BTABiome icyPeaks = new IcyPeaksBiome(ICY_PEAKS_ID, "betterterrain:icy_peaks", Climate.SNOWY)
			.setBiomeName("Icy Peaks")
			.setSurfaceBuilder(new IcyPeaksSurfaceBuilder())
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.5F, 1.5F)
			.setEnableSnow()
			.setNotSpawnable();

	public static final BTABiome jungle = new JungleBiome(JUNGLE_ID, "betterterrain:jungle", Climate.TROPICAL)
			.setBiomeName("Better Jungle")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.4F)
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome mountains = new MountainBiome(MOUNTAINS_ID, "betterterrain:mountains", Climate.COLD)
			.setBiomeName("Mountains")
			.setTemperatureRainfall(0.5F, 0.1F)
			.setMinMaxHeight(0.8F, 2.5F)
			.setLegacyCompatible();

	public static final BTABiome plains = new PlainsBiome(PLAINS_ID, "betterterrain:plains", Climate.ARID)
			.setBiomeName("Better Plains")
			.setSpawnsPumpkins()
			.setSpawnsSugarCane()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.8F, 0.3F)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome siberia = new SiberiaBiome(SIBERIA_ID, "betterterrain:siberia", Climate.SNOWY)
			.setBiomeName("Siberia")
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 0.7F)
			.setEnableSnow();

	public static final BTABiome tundra = new TundraBiome(TUNDRA_ID, "betterbiomes:tundra", Climate.SNOWY)
			.setBiomeName("Tundra")
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.1F, 0.4F)
			.setEnableSnow()
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome swamp = new SwampBiome(SWAMP_ID, "betterterrain:swamp", Climate.TROPICAL)
			.setBiomeName("Better Swamp")
			.setSurfaceBuilder(new SwampSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setMinMaxHeight(-0.1F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.9F);
	
	public static final BTABiome woods = new WoodsBiome(WOODS_ID, "betterterrain:woods", Climate.TEMPERATE)
			.setBiomeName("Woods")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setLegacyCompatible();
	
	public static final BTABiome netherWastes = new NetherWastesBiome(NETHER_WASTES_ID, "betterterrain:nether_wastes")
			.setBiomeName("Nether Wastes")
			.setSurfaceBuilder(new NetherSurfaceBuilder())
			.setNether();
	
	//------ Variant Biomes ------//

	public static final BTABiome mountainEdge = new MountainBiome(MOUNTAIN_EDGE_ID, "betterterrain:mountain_edge", Climate.COLD)
			.setBiomeName("Mountains Edge")
			.setTemperatureRainfall(0.5F, 0.1F)
			.setMinMaxHeight(0.2F, 0.5F)
			.setEdge();

	public static final BTABiome icyPeaksEdge = new IcyPeaksBiome(ICY_PEAKS_EDGE_ID, "betterterrain:icy_peaks_edge", Climate.SNOWY)
			.setBiomeName("Icy Peaks Edge")
			.setSurfaceBuilder(new IcyPeaksSurfaceBuilder())
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setEdge();

	public static final BTABiome jungleEdge = new JungleEdgeBiome(JUNGLE_EDGE_ID, "betterterrain:jungle_edge", Climate.TROPICAL)
			.setBiomeName("Better Jungle Edge")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.4F)
			.setEdge()
			.setNotSpawnable();

	public static final BTABiome beach = new BeachBiome(BEACH_ID, "betterterrain:beach", Climate.TEMPERATE)
			.setBiomeName("Better Beach")
			.setTemperatureRainfall(0.8F, 0.4F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setBeach()
			.setNotSpawnable();

	public static final BTABiome frozenBeach = new BeachBiome(FROZEN_BEACH_ID, "betterterrain:frozen_beach", Climate.SNOWY)
			.setBiomeName("Frozen Beach")
			.setTemperatureRainfall(0.0F, 0.4F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setEnableSnow()
			.setBeach()
			.setNotSpawnable();

	public static final BTABiome river = new RiverBiome(RIVER_ID, "betterterrain:river", Climate.TEMPERATE)
			.setBiomeName("Better River")
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome jungleRiver = new JungleRiverBiome(JUNGLE_RIVER_ID, "betterterrain:jungle_river")
			.setBiomeName("Jungle River")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome desertRiver = new DesertRiverBiome(DESERT_RIVER_ID, "betterterrain:desert_river")
			.setBiomeName("Desert River")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome swampRiver = new SwampRiverBiome(SWAMP_RIVER_ID, "betterterrain:swamp_river")
			.setBiomeName("Swamp River")
			.setSpawnsSugarCane()
			.setSurfaceBuilder(new SwampSurfaceBuilder())
			.setTemperatureRainfall(0.8F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome frozenRiver = new RiverBiome(FROZEN_RIVER_ID, "betterterrain:frozen_river", Climate.SNOWY)
			.setBiomeName("Better Frozen River")
			.setEnableSnow()
			.setMinMaxHeight(-0.5F, 0.0F)
			.setTemperatureRainfall(0.0F, 0.5F)
			.setRiver();

	public static final BTABiome desertHills = new DesertBiome(DESERT_HILLS_ID, "betterterrain:desert_hills", Climate.ARID)
			.setBiomeName("Better Desert Hills")
			.setDisableRain()
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.3F, 1.0F)
			.setNotSpawnable();

	public static final BTABiome woodsHills = new WoodsBiome(WOODS_HILLS_ID, "betterterrain:woods_hills", Climate.TEMPERATE)
			.setBiomeName("Wooded Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);
	
	public static final BTABiome aridForest = new AridForestBiome(ARID_FOREST_ID, "betterterrain:arid_forest", Climate.ARID)
			.setBiomeName("Arid Forest")
			.setSurfaceBuilder(new StonySurfaceBuilder())
			.setTemperatureRainfall(0.8F, 0.3F)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome jungleHills = new JungleBiome(JUNGLE_HILLS_ID, "betterterrain:jungle_hills", Climate.TROPICAL)
			.setBiomeName("Better Jungle Hills")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(1.8F, 0.5F)
			.setNotSpawnable();
	
	//------ Deprecated Biomes ------//
	
	public static final BTABiome icyPeaksForested = new ForestedIcyPeaksBiome(ICY_PEAKS_FORESTED_ID, "betterterrain:icy_peaks_forested", Climate.SNOWY)
			.setBiomeName("Forested Icy Peaks")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.5F, 1.5F);

	@Override
	public void addBiomesToList(ArrayList<BTABiome> biomeList) {
		//Done to preserve backwards compatible biome order
		if (!FCAddOnHandler.isModInstalled("Better Biomes")) {
			biomeList.add(woods);
			biomeList.add(desert);
			biomeList.add(jungle);
			biomeList.add(mountains);
			biomeList.add(tundra);
			biomeList.add(icyPeaks);
			biomeList.add(siberia);
			biomeList.add(plains);
		}
		
		biomeList.add(swamp);
	}

	@Override
	public void setBiomeVariants() {
		desert.addSubVariant(desertHills);
		icyPeaks.addSubVariant(icyPeaksForested, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4);
			}
		});
		jungle.addSubVariant(jungleHills);
		plains.addSubVariant(woods, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_2_1);
			}
		});
		plains.addSubVariant(aridForest, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_0);
			}
		});
		woods.addSubVariant(woodsHills);
		
		jungle.addSporadicVariant(jungleRiver);
		jungle.addSporadicChance(8);
		swamp.addSporadicVariant(swampRiver);
		swamp.addSporadicChance(5);

		mountains.setHasBeach(false);
		tundra.setHasBeach(false, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_1);
			}
		});
		icyPeaks.setHasBeach(false);
		siberia.setHasBeach(false, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_1);
			}
		});
		tundra.addBeachVariant(frozenBeach, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2);
			}
		});
		siberia.addBeachVariant(frozenBeach, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2);
			}
		});

		desert.addRiverVariant(desertRiver);
		desertHills.addRiverVariant(desertRiver);
		jungle.addRiverVariant(jungleRiver);
		jungleHills.addRiverVariant(jungleRiver);
		jungleEdge.addRiverVariant(jungleRiver);
		swamp.addRiverVariant(swampRiver);
		tundra.addRiverVariant(frozenRiver);
		icyPeaks.addRiverVariant(frozenRiver);
		siberia.addRiverVariant(frozenRiver);

		mountains.addEdgeVariant(mountainEdge);
		icyPeaks.addEdgeVariant(icyPeaksEdge);
		jungle.addEdgeVariant(jungleEdge, new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2);
			}
		});
		jungleHills.setConnectToEdge(false);
	}
	
	private static BTABiomeConfiguration instance;
	
	public static BTABiomeConfiguration getInstance() {
		if (instance == null) {
			instance = new BTABiomeConfiguration();
		}
		
		return instance;
	}
}
