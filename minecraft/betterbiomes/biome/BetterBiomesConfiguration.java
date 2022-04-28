package betterbiomes.biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import betterbiomes.world.generate.surface.AlpineSurfaceBuilder;
import betterbiomes.world.generate.surface.AncientForestSurfaceBuilder;
import betterbiomes.world.generate.surface.AshFieldsSurfaceBuilder;
import betterbiomes.world.generate.surface.BadlandsPlateauSurfaceBuilder;
import betterbiomes.world.generate.surface.BadlandsSurfaceBuilder;
import betterbiomes.world.generate.surface.BasaltDeltasSurfaceBuilder;
import betterbiomes.world.generate.surface.ConiferousForestSurfaceBuilder;
import betterbiomes.world.generate.surface.CrystalCavernsSurfaceBuilder;
import betterbiomes.world.generate.surface.HeathlandSurfaceBuilder;
import betterbiomes.world.generate.surface.IcyPeaksSurfaceBuilder;
import betterbiomes.world.generate.surface.OrchardSurfaceBuilder;
import betterbiomes.world.generate.surface.OutbackSurfaceBuilder;
import betterbiomes.world.generate.surface.SoulSandValleySurfaceBuilder;
import betterbiomes.world.generate.surface.SteppeSurfaceBuilder;
import betterbiomes.world.generate.surface.TropicsSurfaceBuilder;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.biome.BiomeHeight;
import betterterrain.biome.BiomeInfo;
import betterterrain.biome.Climate;
import betterbiomes.biome.biomes.AlpineBiome;
import betterbiomes.biome.biomes.AncientForestBiome;
import betterbiomes.biome.biomes.AridForestBiome;
import betterbiomes.biome.biomes.AshFieldsBiome;
import betterbiomes.biome.biomes.AspenGroveBiome;
import betterbiomes.biome.biomes.AutmnForestBiome;
import betterbiomes.biome.biomes.BadlandsBiome;
import betterbiomes.biome.biomes.BadlandsPlateauBiome;
import betterbiomes.biome.biomes.BasaltDeltasBiome;
import betterbiomes.biome.biomes.BeachBiome;
import betterbiomes.biome.biomes.OutbackBeachBiome;
import betterbiomes.biome.biomes.BirchForestBiome;
import betterbiomes.biome.biomes.BorealForestBiome;
import betterbiomes.biome.biomes.BrushlandBiome;
import betterbiomes.biome.biomes.ChapparalBiome;
import betterbiomes.biome.biomes.CherryForestBiome;
import betterbiomes.biome.biomes.ConiferousForestBiome;
import betterbiomes.biome.biomes.deprecated.ConiferousForestClearingBiome;
import betterbiomes.biome.biomes.CrystalCavernsBiome;
import betterbiomes.biome.biomes.DesertBiome;
import betterbiomes.biome.biomes.DunesBiome;
import betterbiomes.biome.biomes.FrozenSpringsPondBiome;
import betterbiomes.biome.biomes.FrozenSpringsBiome;
import betterbiomes.biome.biomes.FungalForestBiome;
import betterbiomes.biome.biomes.GrasslandsBiome;
import betterbiomes.biome.biomes.GrasslandsLakeBiome;
import betterbiomes.biome.biomes.HeathlandBiome;
import betterbiomes.biome.biomes.deprecated.HeathlandWoodsBiome;
import betterbiomes.biome.biomes.HighlandsBiome;
import betterbiomes.biome.biomes.IcyPeaksBiome;
import betterbiomes.biome.biomes.deprecated.ForestedIcyPeaksBiome;
import betterbiomes.biome.biomes.JungleBiome;
import betterbiomes.biome.biomes.JungleEdgeBiome;
import betterbiomes.biome.biomes.LushDesertBiome;
import betterbiomes.biome.biomes.MangroveForestBiome;
import betterbiomes.biome.biomes.MeadowBiome;
import betterbiomes.biome.biomes.MountainBiome;
import betterbiomes.biome.biomes.MysticForestBiome;
import betterbiomes.biome.biomes.NetherWastesBiome;
import betterbiomes.biome.biomes.OasisBiome;
import betterbiomes.biome.biomes.deprecated.OldValleyBiome;
import betterbiomes.biome.biomes.OrchardBiome;
import betterbiomes.biome.biomes.deprecated.OrchardClearingBiome;
import betterbiomes.biome.biomes.OutbackBiome;
import betterbiomes.biome.biomes.PatagoniaBiome;
import betterbiomes.biome.biomes.PatagoniaMountainBiome;
import betterbiomes.biome.biomes.PetrifiedForestBiome;
import betterbiomes.biome.biomes.PlainsBiome;
import betterbiomes.biome.biomes.RainforestBiome;
import betterbiomes.biome.biomes.RainforestEdgeBiome;
import betterbiomes.biome.biomes.RiverBiome;
import betterbiomes.biome.biomes.BadlandsRiverBiome;
import betterbiomes.biome.biomes.DesertRiverBiome;
import betterbiomes.biome.biomes.JungleRiverBiome;
import betterbiomes.biome.biomes.MysticRiverBiome;
import betterbiomes.biome.biomes.OrchardRiverBiome;
import betterbiomes.biome.biomes.OutbackRiverBiome;
import betterbiomes.biome.biomes.PatagoniaRiverBiome;
import betterbiomes.biome.biomes.RainforestRiverBiome;
import betterbiomes.biome.biomes.TropicsRiverBiome;
import betterbiomes.biome.biomes.WetlandsRiverBiome;
import betterbiomes.biome.biomes.WillowRiverBiome;
import betterbiomes.biome.biomes.SavannaBiome;
import betterbiomes.biome.biomes.ShieldBiome;
import betterbiomes.biome.biomes.SiberiaBiome;
import betterbiomes.biome.biomes.SoulSandValleyBiome;
import betterbiomes.biome.biomes.SteppeBiome;
import betterbiomes.biome.biomes.TemperateForestBiome;
import betterbiomes.biome.biomes.TropicsBiome;
import betterbiomes.biome.biomes.TundraBiome;
import betterbiomes.biome.biomes.ValleyBiome;
import betterbiomes.biome.biomes.WetlandsBiome;
import betterbiomes.biome.biomes.WillowGroveBiome;
import betterbiomes.biome.biomes.deprecated.WoodedSteppeBiome;
import betterbiomes.biome.biomes.WoodsBiome;
import betterterrain.biome.layer.HillsLayer;
import betterterrain.structure.mapgen.BTAMapGenScatteredFeature;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.surface.NetherSurfaceBuilder;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.generate.surface.StonySurfaceBuilder;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ComponentVillageStartPiece;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.StructureScatteredFeatureStart;
import net.minecraft.src.WorldGenPumpkin;
import net.minecraft.src.WorldGenReed;

public class BetterBiomesConfiguration extends BiomeConfiguration {
	public static final int
	NETHER_WASTES_ID = 90,
	ASH_FIELDS_ID = 91,
	BASALT_DELTAS_ID = 92,
	SOUL_SAND_VALLEY_ID = 93,
	OBSIDIAN_GROVE_ID = 94,
	CRYSTAL_CAVERNS_ID = 95,
	PETRIFIED_FOREST_ID = 96,

	WOODS_ID = 100,
	DESERT_ID = 101,
	LUSH_DESERT_ID = 102,
	OASIS_ID = 103,
	SAVANNA_ID = 104,
	WETLANDS_ID = 105,
	BIRCH_FOREST_ID = 106,
	SNOWY_WOODS_ID = 107,
	STEPPE_ID = 108,
	WOODED_STEPPE_ID = 109,
	CHAPPARAL_ID = 110,
	ANCIENT_FOREST_ID = 111,
	TROPICS_ID = 112,
	JUNGLE_ID = 113,
	ALPINE_ID = 114,
	ASPEN_GROVE_ID = 115,
	FUNGAL_FOREST_ID = 116,
	CONIFEROUS_FOREST_ID = 117,
	CONIFEROUS_FOREST_CLEARING_ID = 118,
	SNOWY_CONIFEROUS_FOREST_ID = 119,
	SNOWY_CONIFEROUS_FOREST_CLEARING_ID = 120,
	MYSTIC_FOREST_ID = 121,
	RAINFOREST_ID = 122,
	MEADOW_ID = 123,
	ORCHARD_ID = 124,
	MOUNTAINS_ID = 125,
	DUNES_ID = 126,
	HEATHLAND_ID = 127,
	HEATHLAND_WOODS_ID = 128,
	TEMPERATE_FOREST_ID = 129,
	VALLEY_MOUNTAINS_ID = 130,
	OLD_VALLEY_ID = 131,
	TUNDRA_ID = 132,
	WILLOW_GROVE_ID = 133,
	ICY_PEAKS_ID = 134,
	PATAGONIA_ID = 135,
	GRASSLANDS_ID = 136,
	SIBERIA_ID = 137,
	PLAINS_ID = 138,
	FROZEN_SPRINGS_ID = 139,
	MANGROVE_FOREST_ID = 140,
	BOREAL_FOREST_ID = 141,
	ARID_FOREST_ID = 142,
	SHIELD_ID = 143,
	BRUSHLAND_ID = 144,
	HIGHLANDS_ID = 145,

	WOODS_HILLS_ID = 150,
	DESERT_HILLS_ID = 151,
	SAVANNA_HILLS_ID = 152,
	BIRCH_FOREST_HILLS_ID = 153,
	SNOWY_WOODS_HILLS = 154,
	CHAPPARAL_HILLS_ID = 155,
	ANCIENT_FOREST_HILLS_ID = 156,
	JUNGLE_HILLS_ID = 157,
	FUNGAL_FOREST_FLAT_ID = 158,
	WETLANDS_HILLS_ID = 159,
	CHERRY_FOREST_HILLS_ID = 160,
	AUTUMN_FOREST_HILLS_ID = 161,
	VALLEY_ID = 162,
	ORCHARD_CLEARING_ID = 163,
	WILLOW_HILLS_ID = 164,
	ICY_PEAKS_FORESTED_ID = 165,
	PATAGONIA_MOUNTAINS_ID = 166,
	GRASSLANDS_LAKE_ID = 167,
	FROZEN_SPRINGS_POND_ID = 168,
	MANGROVE_FOREST_ISLAND_ID = 169,
	BOREAL_FOREST_HILLS_ID = 170,
	SAVANNA_PLATEAU_ID = 171,

	OUTBACK_ID = 180,
	CHERRY_FOREST_ID = 181,
	BADLANDS_ID = 182,
	BADLANDS_PLATEAU_ID = 183,
	AUTUMN_FOREST_ID = 184,

	DESERT_RIVER_ID = 200,
	MYSTIC_RIVER_ID = 201,
	RAINFOREST_RIVER_ID = 202,
	OUTBACK_RIVER_ID = 203,
	BADLANDS_RIVER_ID = 204,
	TROPICS_RIVER_ID = 205,
	ORCHARD_RIVER_ID = 206,
	JUNGLE_RIVER_ID = 207,
	WETLANDS_RIVER_ID = 208,
	WILLOW_GROVE_RIVER_ID = 209,
	PATAGONIA_RIVER_ID = 210,
	RIVER_ID = 211,
	FROZEN_RIVER_ID = 212,

	ALPINE_EDGE_ID = 230,
	MOUNTAIN_EDGE_ID = 231,
	BADLANDS_EDGE_ID = 232,
	ICY_PEAKS_EDGE_ID = 233,
	HIGHLANDS_EDGE_ID = 234,
	JUNGLE_EDGE_ID = 235,
	RAINFOREST_EDGE_ID = 236,
	TROPICS_EDGE_ID = 237,

	RED_SAND_BEACH_ID = 240,
	BEACH_ID = 241,
	FROZEN_BEACH_ID = 242,

	max_id = 256;

	// ------ Primary Biomes ------ //
	//Temperate
	public static final BTABiome woods = new WoodsBiome(WOODS_ID, Climate.TEMPERATE)
	.setBiomeName("Woods")
	.setTemperatureRainfall(0.7F, 0.8F)
	.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
	.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome steppe = new SteppeBiome(STEPPE_ID, Climate.TEMPERATE)
			.setBiomeName("Steppe")
			.setTemperatureRainfall(0.8F, 0.1F)
			.setValidHeights(BiomeHeight.FLAT)
			.setMinMaxHeight(0.3F, 0.5F);

	public static final BTABiome ancientForest = new AncientForestBiome(ANCIENT_FOREST_ID, Climate.TEMPERATE)
			.setBiomeName("Ancient Forest")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome mysticForest = new MysticForestBiome(MYSTIC_FOREST_ID, Climate.TEMPERATE)
			.setBiomeName("Mystic Forest")
			.setTemperatureRainfall(0.9F, 1.0F)
			.setValidHeights(BiomeHeight.HILLY, BiomeHeight.MOUNTAINS)
			.setMinMaxHeight(0.3F, 1.5F);

	public static final BTABiome orchard = new OrchardBiome(ORCHARD_ID, Climate.TEMPERATE)
			.setBiomeName("Orchard")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY);

	public static final BTABiome heathland = new HeathlandBiome(HEATHLAND_ID, Climate.TEMPERATE)
			.setBiomeName("Heathland")
			.setTemperatureRainfall(0.7F, 0.4F)
			.setValidHeights(BiomeHeight.FLAT)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome temperateForest = new TemperateForestBiome(TEMPERATE_FOREST_ID, Climate.TEMPERATE)
			.setBiomeName("Temperate Forest")
			.setMinMaxHeight(0.2F, 1.0F)
			.setTemperatureRainfall(0.6F, 0.7F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY, BiomeHeight.MOUNTAINS);

	public static final BTABiome grasslands = new GrasslandsBiome(GRASSLANDS_ID, Climate.TEMPERATE)
			.setBiomeName("Grasslands")
			.setTemperatureRainfall(0.5F, 0.6F)
			.setValidHeights(BiomeHeight.FLAT)
			.setMinMaxHeight(0.2F, 0.3F);

	public static final BTABiome mangroveForest = new MangroveForestBiome(MANGROVE_FOREST_ID, Climate.TEMPERATE)
			.setBiomeName("Mangal")
			.setTemperatureRainfall(0.8F, 0.9F)
			.setValidHeights(BiomeHeight.SHALLOWS)
			.setMinMaxHeight(-0.3F, 0.2F);

	public static final BTABiome highlands = new HighlandsBiome(HIGHLANDS_ID, Climate.TEMPERATE)
			.setBiomeName("Highlands")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setValidHeights(BiomeHeight.MOUNTAINS)
			.setMinMaxHeight(0.8F, 2.5F);

	public static final BTABiome cherryForest = new CherryForestBiome(CHERRY_FOREST_ID, Climate.TEMPERATE)
			.setBiomeName("Cherry Forest")
			.setTemperatureRainfall(0.9F, 0.8F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome autumnForest = new AutmnForestBiome(AUTUMN_FOREST_ID, Climate.TEMPERATE)
			.setBiomeName("Autumn Forest")
			.setTemperatureRainfall(0.9F, 0.2F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(0.1F, 0.5F);

	//Arid
	public static final BTABiome desert = new DesertBiome(DESERT_ID, Climate.ARID)
			.setBiomeName("Better Desert")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY, BiomeHeight.MOUNTAINS)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome savanna = new SavannaBiome(SAVANNA_ID, Climate.ARID)
			.setBiomeName("Savanna")
			.setTemperatureRainfall(1.5F, 0.1F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(0.1F, 0.3F);

	public static final BTABiome chaparral = new ChapparalBiome(CHAPPARAL_ID, Climate.ARID)
			.setBiomeName("Chaparral")
			.setTemperatureRainfall(0.8F, 0.4F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(0.3F, 0.6F);

	public static final BTABiome dunes = new DunesBiome(DUNES_ID, Climate.ARID)
			.setBiomeName("Dunes")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setValidHeights(BiomeHeight.HILLY, BiomeHeight.MOUNTAINS)
			.setMinMaxHeight(0.5F, 1.5F);

	public static final BTABiome plains = new PlainsBiome(PLAINS_ID, Climate.ARID)
			.setBiomeName("Better Plains")
			.setTemperatureRainfall(0.8F, 0.3F)
			.setValidHeights(BiomeHeight.FLAT)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome aridForest = new AridForestBiome(ARID_FOREST_ID, Climate.ARID)
			.setBiomeName("Arid Forest")
			.setTemperatureRainfall(0.8F, 0.3F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome outback = new OutbackBiome(OUTBACK_ID, Climate.ARID)
			.setBiomeName("Outback")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setValidHeights(BiomeHeight.FLAT)
			.setMinMaxHeight(0.1F, 0.4F);

	public static final BTABiome badlandsPlateau = new BadlandsPlateauBiome(BADLANDS_PLATEAU_ID, Climate.ARID)
			.setBiomeName("Badlands Plateau")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setValidHeights(BiomeHeight.HILLY, BiomeHeight.MOUNTAINS)
			.setMinMaxHeight(0.8F, 2.0F)
			.setPlateau();

	//Tropical
	public static final BTABiome lushDesert = new LushDesertBiome(LUSH_DESERT_ID, Climate.TROPICAL)
			.setBiomeName("Lush Desert")
			.setDisableRain()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setValidHeights(BiomeHeight.FLAT, BiomeHeight.HILLY, BiomeHeight.MOUNTAINS)
			.setMinMaxHeight(0.1F, 0.6F);

	public static final BTABiome wetlands = new WetlandsBiome(WETLANDS_ID, Climate.TROPICAL)
			.setBiomeName("Wetlands")
			.func_76733_a(9154376)
			.setMinMaxHeight(-0.1F, 0.3F)
			.setValidHeights(BiomeHeight.SHALLOWS, BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setTemperatureRainfall(0.8F, 0.9F);

	public static final BTABiome tropics = new TropicsBiome(TROPICS_ID, Climate.TROPICAL)
			.setBiomeName("Tropics")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setValidHeights(BiomeHeight.SHALLOWS, BiomeHeight.FLAT, BiomeHeight.HILLY)
			.setMinMaxHeight(-0.2F, 0.9F);

	public static final BTABiome jungle = new JungleBiome(JUNGLE_ID, Climate.TROPICAL)
			.setBiomeName("Better Jungle")
			.func_76733_a(5470985)
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.4F);

	public static final BTABiome rainforest = new RainforestBiome(RAINFOREST_ID, Climate.TROPICAL)
			.setBiomeName("Rainforest")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.3F, 1.5F);

	public static final BTABiome meadow = new MeadowBiome(MEADOW_ID, Climate.TROPICAL)
			.setBiomeName("Meadow")
			.setTemperatureRainfall(0.7F, 1.0F)
			.setMinMaxHeight(0.1F, 0.4F);

	public static final BTABiome valleyMountains = new ValleyBiome(VALLEY_MOUNTAINS_ID, Climate.TROPICAL)
			.setBiomeName("Valley Highlands")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.5F, 3.0F);

	public static final BTABiome willowGrove = new WillowGroveBiome(WILLOW_GROVE_ID, Climate.TROPICAL)
			.setBiomeName("Willow Grove")
			.setMinMaxHeight(-0.1F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.8F);

	//Cold
	public static final BTABiome birchForest = new BirchForestBiome(BIRCH_FOREST_ID, Climate.COLD)
			.setBiomeName("Birch Forest")
			.setTemperatureRainfall(0.4F, 0.4F)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome alpine = new AlpineBiome(ALPINE_ID, Climate.COLD)
			.setBiomeName("Alpine")
			.setTemperatureRainfall(0.2F, 0.8F)
			.setMinMaxHeight(0.5F, 3.0F);

	public static final BTABiome fungalForest = new FungalForestBiome(FUNGAL_FOREST_ID, Climate.COLD)
			.setBiomeName("Fungal Forest")
			.setMinMaxHeight(-0.1F, 1.2F)
			.setTemperatureRainfall(0.4F, 1.0F);

	public static final BTABiome coniferousForest = new ConiferousForestBiome(CONIFEROUS_FOREST_ID, Climate.COLD)
			.setBiomeName("Coniferous Forest")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F);

	public static final BTABiome mountains = new MountainBiome(MOUNTAINS_ID, Climate.COLD)
			.setBiomeName("Mountains")
			.setTemperatureRainfall(0.5F, 0.1F)
			.setMinMaxHeight(0.8F, 2.5F);

	public static final BTABiome patagonia = new PatagoniaBiome(PATAGONIA_ID, Climate.COLD)
			.setBiomeName("Patagonia")
			.setTemperatureRainfall(0.3F, 0.6F)
			.setMinMaxHeight(0.0F, 0.5F);

	public static final BTABiome borealForest = new BorealForestBiome(BOREAL_FOREST_ID, Climate.COLD)
			.setBiomeName("Boreal Forest")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.1F, 0.4F);

	public static final BTABiome shield = new ShieldBiome(SHIELD_ID, Climate.COLD)
			.setBiomeName("Shield")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.2F, 0.6F);

	public static final BTABiome brushland = new BrushlandBiome(BRUSHLAND_ID, Climate.COLD)
			.setBiomeName("Brushland")
			.setTemperatureRainfall(0.4F, 0.2F)
			.setMinMaxHeight(0.3F, 0.5F);

	//Snowy
	public static final BTABiome snowyWoods = new WoodsBiome(SNOWY_WOODS_ID, Climate.SNOWY)
			.setBiomeName("Snowy Woods")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.5F)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome coniferousForestSnow = new ConiferousForestBiome(SNOWY_CONIFEROUS_FOREST_ID, Climate.SNOWY)
			.setBiomeName("Snowy Coniferous Forest")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F);

	public static final BTABiome tundra = new TundraBiome(TUNDRA_ID, Climate.SNOWY)
			.setBiomeName("Tundra")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.1F, 0.4F);

	public static final BTABiome icyPeaks = new IcyPeaksBiome(ICY_PEAKS_ID, Climate.SNOWY)
			.setBiomeName("Icy Peaks")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.5F, 1.5F);

	public static final BTABiome siberia = new SiberiaBiome(SIBERIA_ID, Climate.SNOWY)
			.setBiomeName("Siberia")
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 0.7F)
			.setEnableSnow();

	public static final BTABiome frozenSprings = new FrozenSpringsBiome(FROZEN_SPRINGS_ID, Climate.SNOWY)
			.setBiomeName("Frozen Springs")
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.2F, 0.6F)
			.setEnableSnow();

	// ------ Secondary Biomes ------ //
	//Variants
	public static final BTABiome woodsHills = new WoodsBiome(WOODS_HILLS_ID, Climate.TEMPERATE)
			.setBiomeName("Wooded Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome desertHills = new DesertBiome(DESERT_HILLS_ID, Climate.ARID)
			.setBiomeName("Better Desert Hills")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome savannaHills = new SavannaBiome(SAVANNA_HILLS_ID, Climate.ARID)
			.setBiomeName("Savanna Hills")
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.3F, 0.8F);

	public static final BTABiome savannaPlateau = new SavannaBiome(SAVANNA_PLATEAU_ID, Climate.ARID)
			.setBiomeName("Savanna Plateau")
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.3F, 0.8F)
			.setPlateau();

	public static final BTABiome birchForestHills = new BirchForestBiome(BIRCH_FOREST_HILLS_ID, Climate.COLD)
			.setBiomeName("Birch Forest Hills")
			.setTemperatureRainfall(0.4F, 0.4F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome snowyWoodsHills = new WoodsBiome(SNOWY_WOODS_HILLS, Climate.SNOWY)
			.setBiomeName("Snowy Woods Hills")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.5F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome chaparralHills = new ChapparalBiome(CHAPPARAL_HILLS_ID, Climate.ARID)
			.setBiomeName("Chaparral Hills")
			.setTemperatureRainfall(0.8F, 0.4F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome ancientForestHills = new AncientForestBiome(ANCIENT_FOREST_HILLS_ID, Climate.TEMPERATE)
			.setBiomeName("Ancient Forest Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome jungleHills = new JungleBiome(JUNGLE_HILLS_ID, Climate.TROPICAL)
			.setBiomeName("Better Jungle Hills")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(1.8F, 0.5F);

	public static final BTABiome fungalForestFlat = new FungalForestBiome(FUNGAL_FOREST_FLAT_ID, Climate.COLD)
			.setBiomeName("Fungal Forest Lowlands")
			.setMinMaxHeight(-0.1F, 0.5F)
			.setTemperatureRainfall(0.4F, 1.0F);

	public static final BTABiome wetlandsHills = new WetlandsBiome(WETLANDS_HILLS_ID, Climate.TROPICAL)
			.setBiomeName("Wetlands Hills")
			.setMinMaxHeight(-0.2F, 0.6F)
			.setTemperatureRainfall(0.8F, 0.9F);

	public static final BTABiome cherryForestHills = new CherryForestBiome(CHERRY_FOREST_HILLS_ID, Climate.TEMPERATE)
			.setBiomeName("Cherry Forest Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome autumnForestHills = new AutmnForestBiome(AUTUMN_FOREST_HILLS_ID, Climate.TEMPERATE)
			.setBiomeName("Autumn Forest Hills")
			.setTemperatureRainfall(0.9F, 0.2F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome valley = new ValleyBiome(VALLEY_ID, Climate.TROPICAL)
			.setBiomeName("Valley")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.1F, 0.6F);

	public static final BTABiome willowHills = new WillowGroveBiome(WILLOW_HILLS_ID, Climate.TROPICAL)
			.setBiomeName("Willow Hills")
			.setMinMaxHeight(0.2F, 0.6F)
			.setTemperatureRainfall(0.8F, 0.8F);

	public static final BTABiome patagoniaMountains = new PatagoniaMountainBiome(PATAGONIA_MOUNTAINS_ID, Climate.COLD)
			.setBiomeName("Patagonia Mountains")
			.setTemperatureRainfall(0.1F, 0.6F)
			.setMinMaxHeight(2.0F, 4.0F)
			.setEnableSnow();

	public static final BTABiome grasslandsLake = new GrasslandsLakeBiome(GRASSLANDS_LAKE_ID, Climate.TEMPERATE)
			.setBiomeName("Grasslands Lake")
			.setTemperatureRainfall(0.5F, 0.6F)
			.setMinMaxHeight(-0.3F, 0.0F);

	public static final BTABiome frozenSpringsPond = new FrozenSpringsPondBiome(FROZEN_SPRINGS_POND_ID, Climate.SNOWY)
			.setBiomeName("Frozen Springs Pond")
			.setTemperatureRainfall(0.2F, 0.1F)
			.setMinMaxHeight(-0.3F, 0.0F);

	public static final BTABiome mangroveForestIsland = new MangroveForestBiome(MANGROVE_FOREST_ISLAND_ID, Climate.TEMPERATE)
			.setBiomeName("Mangal Island")
			.setMinMaxHeight(0.0F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.9F);

	public static final BTABiome borealForestHills = new BorealForestBiome(BOREAL_FOREST_HILLS_ID, Climate.COLD)
			.setBiomeName("Boreal Forest Hills")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome badlands = new BadlandsBiome(BADLANDS_ID, Climate.ARID)
			.setBiomeName("Badlands")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.1F, 0.1F);

	public static final BTABiome oasis = new OasisBiome(OASIS_ID, Climate.TROPICAL)
			.setBiomeName("Oasis")
			.setDisableRain()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(-0.2F, 0.1F);

	//Rivers
	public static final BTABiome riverDesert = new DesertRiverBiome(DESERT_RIVER_ID)
			.setBiomeName("Desert River")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverMystic = new MysticRiverBiome(MYSTIC_RIVER_ID)
			.setBiomeName("Mystic River")
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverRainforest = new RainforestRiverBiome(RAINFOREST_RIVER_ID)
			.setBiomeName("Rainforest River")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverOutback = new OutbackRiverBiome(OUTBACK_RIVER_ID)
			.setBiomeName("Outback River")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverBadlands = new BadlandsRiverBiome(BADLANDS_RIVER_ID)
			.setBiomeName("Badlands River")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverTropics = new TropicsRiverBiome(TROPICS_RIVER_ID)
			.setBiomeName("Tropics River")
			.setTemperatureRainfall(2.0F, 2.0F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverOrchard = new OrchardRiverBiome(ORCHARD_RIVER_ID)
			.setBiomeName("Orchard River")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverJungle = new JungleRiverBiome(JUNGLE_RIVER_ID)
			.setBiomeName("Jungle River")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverWetlands = new WetlandsRiverBiome(WETLANDS_RIVER_ID)
			.setBiomeName("Wetlands River")
			.setTemperatureRainfall(0.8F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverWillow = new WillowRiverBiome(WILLOW_GROVE_RIVER_ID)
			.setBiomeName("Willow Grove River")
			.setTemperatureRainfall(0.6F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverPatagonia = new PatagoniaRiverBiome(PATAGONIA_RIVER_ID)
			.setBiomeName("Patagonia River")
			.setTemperatureRainfall(0.3F, 0.6F)
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome river = new RiverBiome(RIVER_ID, Climate.TEMPERATE)
			.setBiomeName("Better River")
			.setMinMaxHeight(-0.5F, 0.0F);

	public static final BTABiome riverFrozen = new RiverBiome(FROZEN_RIVER_ID, Climate.SNOWY)
			.setBiomeName("Better Frozen River")
			.setEnableSnow()
			.setMinMaxHeight(-0.5F, 0.0F)
			.setTemperatureRainfall(0.0F, 0.5F);

	//Edges
	public static final BTABiome alpineEdge = new AlpineBiome(ALPINE_EDGE_ID, Climate.COLD)
			.setBiomeName("Alpine Edge")
			.setTemperatureRainfall(0.2F, 0.8F)
			.setMinMaxHeight(0.2F, 0.5F);

	public static final BTABiome mountainEdge = new MountainBiome(MOUNTAIN_EDGE_ID, Climate.COLD)
			.setBiomeName("Mountains Edge")
			.setTemperatureRainfall(0.5F, 0.1F)
			.setMinMaxHeight(0.2F, 0.5F);

	public static final BTABiome icyPeaksEdge = new IcyPeaksBiome(ICY_PEAKS_EDGE_ID, Climate.SNOWY)
			.setBiomeName("Icy Peaks Edge")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome highlandsEdge = new HighlandsBiome(HIGHLANDS_EDGE_ID, Climate.TEMPERATE)
			.setBiomeName("Highlands Edge")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.8F, 2.5F);

	public static final BTABiome jungleEdge = new JungleEdgeBiome(JUNGLE_EDGE_ID, Climate.TROPICAL)
			.setBiomeName("Better Jungle Edge")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.4F);

	public static final BTABiome rainforestEdge = new RainforestEdgeBiome(RAINFOREST_EDGE_ID, Climate.TROPICAL)
			.setBiomeName("Rainforest Edge")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.3F, 1.5F);

	public static final BTABiome tropicsEdge = new TropicsBiome(TROPICS_EDGE_ID, Climate.TROPICAL)
			.setBiomeName("Tropics Edge")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.3F, 0.1F);

	//Beaches
	public static final BTABiome beachOutback = new OutbackBeachBiome(RED_SAND_BEACH_ID, Climate.ARID)
			.setBiomeName("Red Sand Beach")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.0F, 0.1F);

	public static final BTABiome beach = new BeachBiome(BEACH_ID, Climate.TEMPERATE)
			.setBiomeName("Better Beach")
			.setTemperatureRainfall(0.8F, 0.4F)
			.setMinMaxHeight(0.0F, 0.1F);

	public static final BTABiome beachFrozen = new BeachBiome(FROZEN_BEACH_ID, Climate.SNOWY)
			.setBiomeName("Frozen Beach")
			.setTemperatureRainfall(0.0F, 0.4F)
			.setMinMaxHeight(0.0F, 0.1F);

	// ------ Nether Biomes ------ //
	public static final BTABiome netherWastes = new NetherWastesBiome(NETHER_WASTES_ID)
			.setBiomeName("Nether Wastes")
			.setNether();

	public static final BTABiome ashFields = new AshFieldsBiome(ASH_FIELDS_ID)
			.setBiomeName("Ash Fields")
			.setNether();

	public static final BTABiome basaltDeltas = new BasaltDeltasBiome(BASALT_DELTAS_ID)
			.setBiomeName("Basalt Deltas")
			.setNether();

	public static final BTABiome soulSandValley = new SoulSandValleyBiome(SOUL_SAND_VALLEY_ID)
			.setBiomeName("Soul Sand Valley")
			.setNether();

	public static final BTABiome obsidianGrove = null;

	public static final BTABiome crystalCaverns = new CrystalCavernsBiome(CRYSTAL_CAVERNS_ID)
			.setBiomeName("Crystal Caverns")
			.setNether();

	public static final BTABiome petrifiedForest = new PetrifiedForestBiome(PETRIFIED_FOREST_ID)
			.setBiomeName("Petrified Forest")
			.setNether();

	// ------ Deprecated ------ //
	public static final BTABiome orchardClearing = new OrchardClearingBiome(ORCHARD_CLEARING_ID, Climate.TEMPERATE)
			.setBiomeName("Orchard Clearing")
			.setTemperatureRainfall(0.7F, 0.5F);

	public static final BTABiome icyPeaksForested = new ForestedIcyPeaksBiome(ICY_PEAKS_FORESTED_ID, Climate.SNOWY)
			.setBiomeName("Forested Icy Peaks")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.5F, 1.5F);

	public static final BTABiome coniferousForestClearing = new ConiferousForestClearingBiome(CONIFEROUS_FOREST_CLEARING_ID, Climate.COLD)
			.setBiomeName("Coniferous Forest Clearing")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F);

	public static final BTABiome coniferousForestClearingSnow = new ConiferousForestClearingBiome(SNOWY_CONIFEROUS_FOREST_CLEARING_ID, Climate.SNOWY)
			.setBiomeName("Snowy Coniferous Forest Clearing")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F);

	public static final BTABiome aspenGrove = new AspenGroveBiome(ASPEN_GROVE_ID, Climate.COLD)
			.setBiomeName("Aspen Grove")
			.setTemperatureRainfall(0.2F, 0.8F)
			.setMinMaxHeight(0.5F, 3.0F);

	public static final BTABiome heathlandWoods = new HeathlandWoodsBiome(HEATHLAND_WOODS_ID, Climate.TEMPERATE)
			.setBiomeName("Heathland Woods")
			.setTemperatureRainfall(0.7F, 0.4F)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome woodedSteppe = new WoodedSteppeBiome(WOODED_STEPPE_ID, Climate.TEMPERATE)
			.setBiomeName("Wooded Steppe")
			.setTemperatureRainfall(0.8F, 0.1F)
			.setMinMaxHeight(0.3F, 0.5F);

	public static final BTABiome badlandsEdge = new BadlandsBiome(BADLANDS_EDGE_ID, Climate.ARID)
			.setBiomeName("Badlands Edge")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.1F, 0.1F);

	public static final BTABiome oldValley = new OldValleyBiome(OLD_VALLEY_ID, Climate.TROPICAL)
			.setBiomeName("Old Valley")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.1F, 0.8F);

	//Various biome lists
	public static ArrayList<BTABiome> biomeList = new ArrayList();
	public static ArrayList<BTABiome> biomeListDeco = new ArrayList();
	public static ArrayList<BTABiome> biomeListCompat = new ArrayList();
	public static ArrayList<BTABiome> biomeListDecoCompat = new ArrayList();

	public static Map<Integer, BiomeInfo> biomeInfoMap = new HashMap();

	private static ArrayList<BiomeGenBase> beachlessBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> beachlessBiomes132 = new ArrayList();

	private static ArrayList<BiomeGenBase> edgeBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> noEdgeBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> noEdgeBiomes132 = new ArrayList();
	private static ArrayList<BiomeGenBase> noEdgeBiomes140 = new ArrayList();
	private static ArrayList<BiomeGenBase> noPerlinBeachBiomes = new ArrayList();

	private static ArrayList<BiomeGenBase> pumpkinBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> reedBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> villageBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> jungleTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> desertTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> redDesertTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> witchHutBiomes = new ArrayList();

	private static Random rand = new Random();
	private static long lastRandSeed = 0;

	public static void init() {
		filterSpawnBiomes();
		filterBeachBiomes();
		filterPerlinBeachBiomes();
		addBiomesWithEdge();
		filterEdgeBiomes();
		addBiomesToStructureGenerators();
		addBiomesToList();
		initClimateLists();
		initBiomeInfoList();
		initSurfaceBuilders();
	}

	public static void addBiomesToList() {
		biomeListCompat.add(woods);
		biomeListCompat.add(desert);
		biomeListCompat.add(lushDesert);
		biomeListCompat.add(savanna);
		biomeListCompat.add(wetlands);
		biomeListCompat.add(birchForest);
		biomeListCompat.add(snowyWoods);
		biomeListCompat.add(chaparral);
		biomeListCompat.add(ancientForest);
		biomeListCompat.add(tropics);
		biomeListCompat.add(jungle);
		biomeListCompat.add(alpine);
		biomeListCompat.add(fungalForest);
		biomeListCompat.add(coniferousForest);
		biomeListCompat.add(coniferousForestSnow);
		biomeListCompat.add(mysticForest);
		biomeListCompat.add(rainforest);
		biomeListCompat.add(meadow);
		biomeListCompat.add(mountains);
		biomeListCompat.add(dunes);
		biomeListCompat.add(heathland);
		biomeListCompat.add(temperateForest);
		biomeListCompat.add(valleyMountains);
		biomeListCompat.add(tundra);
		biomeListCompat.add(orchard);
		biomeListCompat.add(steppe);

		biomeListDecoCompat.addAll(biomeListCompat);
		biomeListDecoCompat.add(outback);
		biomeListDecoCompat.add(cherryForest);
		biomeListDecoCompat.add(badlandsPlateau);
		biomeListDecoCompat.add(autumnForest);

		biomeList.addAll(biomeListCompat);
		biomeList.add(willowGrove);
		biomeList.add(icyPeaks);
		biomeList.add(patagonia);
		biomeList.add(grasslands);
		biomeList.add(siberia);
		biomeList.add(plains);
		biomeList.add(frozenSprings);
		biomeList.add(mangroveForest);
		biomeList.add(aridForest);
		biomeList.add(borealForest);
		biomeList.add(shield);
		biomeList.add(brushland);
		biomeList.add(highlands);

		biomeListDeco.addAll(biomeList);
		for (BTABiome b : biomeListDecoCompat) {
			if (!biomeListDeco.contains(b)) {
				biomeListDeco.add(b);
			}
		}
	}

	public static void addExternalBiome(BTABiome biome, boolean decoOnly) {
		biomeListDeco.add(biome);

		if (!decoOnly)
			biomeList.add(biome);

		biomeCategoryMap.get(biome.climate).add(biome);
		biomeInfoMap.put(biome.biomeID, new BiomeInfo(biome.biomeID, true, decoOnly));
	}

	public static void initClimateLists() {
		biomeCategoryMap.put(Climate.SNOWY, snowyList);
		biomeCategoryMap.put(Climate.COLD, coldList);
		biomeCategoryMap.put(Climate.TEMPERATE, temperateList);
		biomeCategoryMap.put(Climate.TROPICAL, tropicalList);
		biomeCategoryMap.put(Climate.ARID, aridList);

		for (BTABiome b : biomeListDeco) {
			biomeCategoryMap.get(b.climate).add(b);
		}
	}

	public static void initBiomeInfoList() {
		for (BTABiome b : biomeListDeco) {
			if (biomeList.contains(b))
				biomeInfoMap.put(b.biomeID, new BiomeInfo(b.biomeID, true));
			else
				biomeInfoMap.put(b.biomeID, new BiomeInfo(b.biomeID, true, true));
		}
	}

	public static void initSurfaceBuilders() {
		patagonia.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		patagoniaMountains.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		willowGrove.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		willowHills.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		riverWillow.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		wetlands.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		wetlandsHills.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		riverWetlands.setSurfaceBuilder(new NoShorelineSurfaceBuilder());
		oasis.setSurfaceBuilder(new NoShorelineSurfaceBuilder());

		valley.setSurfaceBuilder(new TropicsSurfaceBuilder());
		valleyMountains.setSurfaceBuilder(new TropicsSurfaceBuilder());
		tropics.setSurfaceBuilder(new TropicsSurfaceBuilder());
		riverTropics.setSurfaceBuilder(new TropicsSurfaceBuilder());

		badlands.setSurfaceBuilder(new BadlandsSurfaceBuilder());
		badlandsEdge.setSurfaceBuilder(new BadlandsSurfaceBuilder());
		riverBadlands.setSurfaceBuilder(new BadlandsSurfaceBuilder());
		badlandsPlateau.setSurfaceBuilder(new BadlandsPlateauSurfaceBuilder());
		beachOutback.setSurfaceBuilder(new BadlandsSurfaceBuilder());

		outback.setSurfaceBuilder(new OutbackSurfaceBuilder());
		riverOutback.setSurfaceBuilder(new OutbackSurfaceBuilder());

		alpine.setSurfaceBuilder(new AlpineSurfaceBuilder());
		alpineEdge.setSurfaceBuilder(new AlpineSurfaceBuilder());

		heathland.setSurfaceBuilder(new HeathlandSurfaceBuilder());

		icyPeaks.setSurfaceBuilder(new IcyPeaksSurfaceBuilder());
		icyPeaksEdge.setSurfaceBuilder(new IcyPeaksSurfaceBuilder());

		steppe.setSurfaceBuilder(new SteppeSurfaceBuilder());

		coniferousForest.setSurfaceBuilder(new ConiferousForestSurfaceBuilder());
		coniferousForestSnow.setSurfaceBuilder(new ConiferousForestSurfaceBuilder());

		orchard.setSurfaceBuilder(new OrchardSurfaceBuilder());

		chaparral.setSurfaceBuilder(new StonySurfaceBuilder());
		chaparralHills.setSurfaceBuilder(new StonySurfaceBuilder());
		aridForest.setSurfaceBuilder(new StonySurfaceBuilder());
		shield.setSurfaceBuilder(new StonySurfaceBuilder());

		ancientForest.setSurfaceBuilder(new AncientForestSurfaceBuilder());

		//Nether biomes
		netherWastes.setSurfaceBuilder(new NetherSurfaceBuilder());
		ashFields.setSurfaceBuilder(new AshFieldsSurfaceBuilder());
		soulSandValley.setSurfaceBuilder(new SoulSandValleySurfaceBuilder());
		basaltDeltas.setSurfaceBuilder(new BasaltDeltasSurfaceBuilder());
		crystalCaverns.setSurfaceBuilder(new CrystalCavernsSurfaceBuilder());
		//petrifiedForest.setSurfaceBuilder(new BTASurfaceBuilderPetrifiedForest());
	}

	public static void filterSpawnBiomes() {
		desert.setNotSpawnable();
		desertHills.setNotSpawnable();
		riverDesert.setNotSpawnable();
		lushDesert.setNotSpawnable();
		oasis.setNotSpawnable();
		dunes.setNotSpawnable();
		tropics.setNotSpawnable();
		riverTropics.setNotSpawnable();
		jungle.setNotSpawnable();
		jungleHills.setNotSpawnable();
		riverJungle.setNotSpawnable();
		rainforest.setNotSpawnable();
		riverRainforest.setNotSpawnable();
		steppe.setNotSpawnable();
		woodedSteppe.setNotSpawnable();
		icyPeaks.setNotSpawnable();
		icyPeaksForested.setNotSpawnable();
		icyPeaksEdge.setNotSpawnable();
		mangroveForest.setNotSpawnable();
		mangroveForestIsland.setNotSpawnable();
		tundra.setNotSpawnable();
		//DECO
		outback.setNotSpawnable();
		riverOutback.setNotSpawnable();
		beachOutback.setNotSpawnable();
		badlands.setNotSpawnable();
		badlandsPlateau.setNotSpawnable();
		riverBadlands.setNotSpawnable();
	}

	public static void filterBeachBiomes() {
		beachlessBiomes.add(alpine);
		beachlessBiomes.add(fungalForest);
		beachlessBiomes.add(fungalForestFlat);
		beachlessBiomes.add(coniferousForest);
		beachlessBiomes.add(coniferousForestSnow);
		beachlessBiomes.add(mysticForest);
		beachlessBiomes.add(mountains);
		beachlessBiomes.add(temperateForest);
		beachlessBiomes.add(oldValley);
		beachlessBiomes.add(valleyMountains);
		beachlessBiomes.add(tundra);
		beachlessBiomes.add(snowyWoods);
		beachlessBiomes.add(snowyWoodsHills);
		beachlessBiomes.add(badlandsPlateau);
		beachlessBiomes.add(rainforest);
		beachlessBiomes.add(tropics);
		beachlessBiomes.add(icyPeaks);
		beachlessBiomes.add(patagoniaMountains);
		beachlessBiomes.add(siberia);
		beachlessBiomes.add(frozenSprings);
		beachlessBiomes.add(mangroveForest);
		beachlessBiomes.add(mangroveForestIsland);

		beachlessBiomes132.addAll(beachlessBiomes);
		beachlessBiomes132.remove(snowyWoods);
		beachlessBiomes132.remove(tundra);
		beachlessBiomes132.remove(siberia);
		beachlessBiomes132.remove(frozenSprings);
	}

	public static void filterPerlinBeachBiomes() {
		noPerlinBeachBiomes.add(wetlands);
		noPerlinBeachBiomes.add(willowGrove);
		noPerlinBeachBiomes.add(patagonia);
	}

	public static void addBiomesToStructureGenerators() {
		WorldGenPumpkin.addBiomeToGenerator(chaparral);
		WorldGenPumpkin.addBiomeToGenerator(meadow);
		WorldGenPumpkin.addBiomeToGenerator(heathland);
		WorldGenPumpkin.addBiomeToGenerator(orchard);
		WorldGenPumpkin.addBiomeToGenerator(patagonia);
		WorldGenPumpkin.addBiomeToGenerator(grasslands);
		WorldGenPumpkin.addBiomeToGenerator(plains);
		WorldGenPumpkin.addBiomeToGenerator(brushland);

		WorldGenReed.addBiomeToGenerator(wetlands);
		WorldGenReed.addBiomeToGenerator(tropics);
		WorldGenReed.addBiomeToGenerator(jungle);
		WorldGenReed.addBiomeToGenerator(fungalForest);
		WorldGenReed.addBiomeToGenerator(mysticForest);
		WorldGenReed.addBiomeToGenerator(rainforest);
		WorldGenReed.addBiomeToGenerator(willowGrove);
		WorldGenReed.addBiomeToGenerator(patagonia);
		WorldGenReed.addBiomeToGenerator(plains);
		WorldGenReed.addBiomeToGenerator(mangroveForest);
		WorldGenReed.addBiomeToGenerator(temperateForest);
		WorldGenReed.addBiomeToGenerator(frozenSprings);

		MapGenVillage.villageSpawnBiomes.add(savanna);
		MapGenVillage.villageSpawnBiomes.add(desert);
		MapGenVillage.villageSpawnBiomes.add(lushDesert);
		MapGenVillage.villageSpawnBiomes.add(chaparral);
		MapGenVillage.villageSpawnBiomes.add(meadow);
		MapGenVillage.villageSpawnBiomes.add(heathland);
		MapGenVillage.villageSpawnBiomes.add(steppe);
		MapGenVillage.villageSpawnBiomes.add(orchard);
		MapGenVillage.villageSpawnBiomes.add(outback);
		MapGenVillage.villageSpawnBiomes.add(patagonia);
		MapGenVillage.villageSpawnBiomes.add(grasslands);
		MapGenVillage.villageSpawnBiomes.add(plains);
		MapGenVillage.villageSpawnBiomes.add(brushland);

		ComponentVillageStartPiece.addDesertBiome(desert);
		ComponentVillageStartPiece.addDesertBiome(lushDesert);
		ComponentVillageStartPiece.addDesertBiome(steppe);

		StructureScatteredFeatureStart.addJungleBiome(tropics);
		StructureScatteredFeatureStart.addJungleBiome(jungle);
		StructureScatteredFeatureStart.addJungleBiome(rainforest);
		StructureScatteredFeatureStart.addJungleBiome(mangroveForest);

		StructureScatteredFeatureStart.addDesertBiome(desert);
		StructureScatteredFeatureStart.addDesertBiome(lushDesert);
		StructureScatteredFeatureStart.addDesertBiome(dunes);
		StructureScatteredFeatureStart.addDesertBiome(steppe);

		redDesertTempleBiomes.add(outback);
		redDesertTempleBiomes.add(badlands);

		StructureScatteredFeatureStart.addSwampBiome(wetlands);
		StructureScatteredFeatureStart.addSwampBiome(fungalForest);
		StructureScatteredFeatureStart.addSwampBiome(mysticForest);
		StructureScatteredFeatureStart.addSwampBiome(willowGrove);

		BTAMapGenScatteredFeature.biomelist.addAll(jungleTempleBiomes);
		BTAMapGenScatteredFeature.biomelist.addAll(desertTempleBiomes);
		BTAMapGenScatteredFeature.biomelist.addAll(witchHutBiomes);

		//Hill variants
		WorldGenPumpkin.addBiomeToGenerator(savannaHills);
		WorldGenPumpkin.addBiomeToGenerator(chaparralHills);
		WorldGenPumpkin.addBiomeToGenerator(heathlandWoods);
		WorldGenPumpkin.addBiomeToGenerator(orchardClearing);

		WorldGenReed.addBiomeToGenerator(jungleHills);
		WorldGenReed.addBiomeToGenerator(fungalForestFlat);
		WorldGenReed.addBiomeToGenerator(wetlandsHills);
		WorldGenReed.addBiomeToGenerator(willowHills);
		WorldGenReed.addBiomeToGenerator(frozenSpringsPond);
		WorldGenReed.addBiomeToGenerator(mangroveForestIsland);

		MapGenVillage.villageSpawnBiomes.add(oasis);
		MapGenVillage.villageSpawnBiomes.add(woodedSteppe);
		MapGenVillage.villageSpawnBiomes.add(heathlandWoods);
		MapGenVillage.villageSpawnBiomes.add(orchardClearing);

		StructureScatteredFeatureStart.addJungleBiome(jungleHills);
		StructureScatteredFeatureStart.addJungleBiome(mangroveForestIsland);

		StructureScatteredFeatureStart.addDesertBiome(desertHills);
		StructureScatteredFeatureStart.addDesertBiome(woodedSteppe);

		StructureScatteredFeatureStart.addSwampBiome(fungalForestFlat);
		StructureScatteredFeatureStart.addSwampBiome(wetlandsHills);
		StructureScatteredFeatureStart.addSwampBiome(willowHills);

		//Edges
		WorldGenReed.addBiomeToGenerator(jungleEdge);
		WorldGenReed.addBiomeToGenerator(rainforestEdge);

		StructureScatteredFeatureStart.addJungleBiome(jungleEdge);
		StructureScatteredFeatureStart.addJungleBiome(rainforestEdge);

		redDesertTempleBiomes.add(badlandsEdge);

		//Rivers
		WorldGenReed.addBiomeToGenerator(riverRainforest);
		WorldGenReed.addBiomeToGenerator(riverTropics);
		WorldGenReed.addBiomeToGenerator(riverJungle);
		WorldGenReed.addBiomeToGenerator(riverMystic);
		WorldGenReed.addBiomeToGenerator(riverWetlands);
		WorldGenReed.addBiomeToGenerator(riverWillow);

		//Extra processing
		BTAMapGenScatteredFeature.biomelist.clear();
		BTAMapGenScatteredFeature.biomelist.addAll(StructureScatteredFeatureStart.desertBiomeList);
		BTAMapGenScatteredFeature.biomelist.addAll(StructureScatteredFeatureStart.jungleBiomeList);
		BTAMapGenScatteredFeature.biomelist.addAll(StructureScatteredFeatureStart.swampBiomeList);
	}

	public static int getHillsVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, HillsLayer layer) {
		int hillsBiome = baseBiome;

		if (layer.getChunkSeed() != lastRandSeed) {
			rand.setSeed(layer.getChunkSeed());
			lastRandSeed = layer.getChunkSeed();
		}

		if (baseBiome == woods.biomeID){
			hillsBiome = woodsHills.biomeID;
		}
		else if (baseBiome == desert.biomeID){
			hillsBiome = desertHills.biomeID;
		}
		else if (baseBiome == savanna.biomeID){
			if (generatorOptions.getBTAVersion().isVersionAtLeast(BTAVersion.V2_0_3)) {
				if (rand.nextInt(3) == 0) {
					hillsBiome = savannaPlateau.biomeID;
				}
			}
			else {
				hillsBiome = savannaHills.biomeID;
			}
		}
		else if (baseBiome == birchForest.biomeID) {
			hillsBiome = birchForestHills.biomeID;
		}
		else if (baseBiome == snowyWoods.biomeID) {
			hillsBiome = snowyWoodsHills.biomeID; 
		}
		else if (baseBiome == steppe.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = woodedSteppe.biomeID;
		}
		else if (baseBiome == chaparral.biomeID) {
			hillsBiome = chaparralHills.biomeID;
		}
		else if (baseBiome == ancientForest.biomeID) {
			hillsBiome = ancientForestHills.biomeID;
		}
		else if (baseBiome == jungle.biomeID) {
			hillsBiome = jungleHills.biomeID;
		}
		else if (baseBiome == alpine.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = aspenGrove.biomeID;
		}
		else if (baseBiome == coniferousForest.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = coniferousForestClearing.biomeID;
		}
		else if (baseBiome == coniferousForestSnow.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = coniferousForestClearingSnow.biomeID;
		}
		else if (baseBiome == heathland.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = heathlandWoods.biomeID;
		}
		else if (baseBiome == cherryForest.biomeID) {
			hillsBiome = cherryForestHills.biomeID;
		}
		else if (baseBiome == autumnForest.biomeID) {
			hillsBiome = autumnForestHills.biomeID;
		}
		else if (baseBiome == valleyMountains.biomeID) {
			hillsBiome = valley.biomeID;
		}
		else if (baseBiome == orchard.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = orchardClearing.biomeID;
		}
		else if (baseBiome == fungalForest.biomeID) {
			hillsBiome = fungalForestFlat.biomeID;
		}
		else if (baseBiome == badlandsPlateau.biomeID){
			hillsBiome = badlands.biomeID;
		}
		else if (baseBiome == icyPeaks.biomeID && generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			hillsBiome = icyPeaksForested.biomeID;
		}
		else if (baseBiome == grasslands.biomeID) {
			hillsBiome = grasslandsLake.biomeID;
		}
		else if (baseBiome == patagonia.biomeID) {
			hillsBiome = patagoniaMountains.biomeID;
		}
		else if (baseBiome == plains.biomeID) {
			if (generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_2_1))
				hillsBiome = woods.biomeID;
			else
				hillsBiome = aridForest.biomeID;
		}
		else if (baseBiome == borealForest.biomeID) {
			hillsBiome = borealForestHills.biomeID;
		}

		return hillsBiome;
	}

	//Appear more commonly than normal hills
	public static int getHillsVariantForBiomes2(int baseBiome) {
		int hillsBiome = baseBiome;

		if (baseBiome == wetlands.biomeID) {
			hillsBiome = wetlandsHills.biomeID;
		}
		else if (baseBiome == lushDesert.biomeID){
			hillsBiome = oasis.biomeID;
		}
		else if (baseBiome == willowGrove.biomeID) {
			hillsBiome = willowHills.biomeID;
		}

		return hillsBiome;
	}

	public static int getRiverVariantForBiomes(int baseBiome) {
		int riverBiome = -1;

		if (BiomeGenBase.biomeList[baseBiome] instanceof RiverBiome) {
			riverBiome = baseBiome;
		}
		else if (baseBiome == desert.biomeID || baseBiome == desertHills.biomeID || baseBiome == dunes.biomeID) {
			riverBiome = riverDesert.biomeID;
		}
		else if (baseBiome == mysticForest.biomeID) {
			riverBiome = riverMystic.biomeID;
		}
		else if (baseBiome == rainforest.biomeID || baseBiome == rainforestEdge.biomeID) {
			riverBiome = riverRainforest.biomeID;
		}
		else if (baseBiome == outback.biomeID) {
			riverBiome = riverOutback.biomeID;
		}
		else if (baseBiome == badlands.biomeID || baseBiome == badlandsPlateau.biomeID || baseBiome == badlandsEdge.biomeID) {
			riverBiome = riverBadlands.biomeID;
		}
		else if (baseBiome == tropics.biomeID || baseBiome == tropicsEdge.biomeID) {
			riverBiome = riverTropics.biomeID;
		}
		else if (baseBiome == orchard.biomeID) {
			riverBiome = riverOrchard.biomeID;
		}
		else if (baseBiome == jungle.biomeID || baseBiome == jungleHills.biomeID || baseBiome == jungleEdge.biomeID) {
			riverBiome = riverJungle.biomeID;
		}
		else if (baseBiome == wetlands.biomeID || baseBiome == wetlandsHills.biomeID) {
			riverBiome = riverWetlands.biomeID;
		}
		else if (baseBiome == willowGrove.biomeID || baseBiome == willowHills.biomeID) {
			riverBiome = riverWillow.biomeID;
		}
		else if (baseBiome == patagonia.biomeID || baseBiome == patagoniaMountains.biomeID) {
			riverBiome = riverPatagonia.biomeID;
		}
		else if (BiomeGenBase.biomeList[baseBiome].getEnableSnow()) {
			riverBiome = riverFrozen.biomeID;
		}
		else {
			riverBiome = river.biomeID;
		}

		return riverBiome;
	}

	public static int getBeachVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorInfo) {
		int beachBiome = -1;

		if (baseBiome == outback.biomeID || baseBiome == badlands.biomeID || baseBiome == beachOutback.biomeID) {
			beachBiome = beachOutback.biomeID;
		}
		else if ((baseBiome == snowyWoods.biomeID || baseBiome == tundra.biomeID || baseBiome == siberia.biomeID || baseBiome == frozenSprings.biomeID || baseBiome == beachFrozen.biomeID) && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
			beachBiome = beachFrozen.biomeID;
		}
		else if (shouldBiomeSpawnBeach(baseBiome, generatorInfo)) {
			beachBiome = beach.biomeID;
		}

		return beachBiome;
	}

	public static int getEdgeVariantForBiome(int baseBiome, WorldConfigurationInfo generatorInfo, int passNum) {
		int edgeBiome = -1;

		if (baseBiome == alpine.biomeID) {
			edgeBiome = alpineEdge.biomeID;
		}
		else if (baseBiome == mountains.biomeID) {
			edgeBiome = mountainEdge.biomeID;
		}
		else if (baseBiome == valleyMountains.biomeID) {
			edgeBiome = valley.biomeID;
		}
		else if (baseBiome == dunes.biomeID) {
			edgeBiome = desert.biomeID;
		}
		else if ((baseBiome == badlands.biomeID || baseBiome == badlandsPlateau.biomeID) && generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4)) {
			edgeBiome = badlandsEdge.biomeID;
		}
		else if (baseBiome == icyPeaks.biomeID) {
			edgeBiome = icyPeaksEdge.biomeID;
		}
		else if (baseBiome == highlands.biomeID) {
			edgeBiome = highlandsEdge.biomeID;
		}
		else if (baseBiome == jungle.biomeID && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
			edgeBiome = jungleEdge.biomeID;
		}
		else if (baseBiome == rainforest.biomeID && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
			edgeBiome = rainforestEdge.biomeID;
		}
		else if (baseBiome == tropics.biomeID && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
			edgeBiome = tropicsEdge.biomeID;
		}

		return edgeBiome;
	}

	public static int getEdgeVariantForBiomeGuaranteed(int baseBiome, WorldConfigurationInfo generatorInfo) {
		int edgeBiome = -1;

		if (baseBiome == badlandsPlateau.biomeID) {
			edgeBiome = badlands.biomeID;
		}

		return edgeBiome;
	}

	public static int getSporadicVariantForBiome(int baseBiome) {
		int sporadicBiome = -1;

		if (baseBiome == frozenSprings.biomeID) {
			sporadicBiome = frozenSpringsPond.biomeID;
		}
		else if (baseBiome == wetlands.biomeID || baseBiome == wetlandsHills.biomeID) {
			sporadicBiome = riverWetlands.biomeID;
		}
		else if (baseBiome == willowGrove.biomeID || baseBiome == willowHills.biomeID) {
			sporadicBiome = riverWillow.biomeID;
		}
		else if (baseBiome == jungle.biomeID) {
			sporadicBiome = riverJungle.biomeID;
		}
		else if (baseBiome == rainforest.biomeID) {
			sporadicBiome = riverRainforest.biomeID;
		}
		else if (baseBiome == lushDesert.biomeID) {
			sporadicBiome = oasis.biomeID;
		}
		else if (baseBiome == mangroveForest.biomeID) {
			sporadicBiome = mangroveForestIsland.biomeID;
		}

		return sporadicBiome;
	}

	public static int getSporadicChanceForBiome(int baseBiome) {
		if (baseBiome == frozenSprings.biomeID)
			return 3;
		if (baseBiome == wetlands.biomeID || baseBiome == wetlandsHills.biomeID)
			return 5;
		if (baseBiome == willowGrove.biomeID || baseBiome == willowHills.biomeID)
			return 5;
		if (baseBiome == jungle.biomeID)
			return 8;
		if (baseBiome == rainforest.biomeID)
			return 8;
		if (baseBiome == lushDesert.biomeID)
			return 8;
		if (baseBiome == mangroveForest.biomeID)
			return 3;
		return 0;
	}

	public static void addBiomesWithEdge() {
		edgeBiomes.add(alpine);
		edgeBiomes.add(mountains);
		edgeBiomes.add(valleyMountains);
		edgeBiomes.add(dunes);
		edgeBiomes.add(badlands);
		edgeBiomes.add(icyPeaks);
		edgeBiomes.add(highlands);
	}

	public static void filterEdgeBiomes() {
		noEdgeBiomes.addAll(edgeBiomes);
		noEdgeBiomes.add(mysticForest);
		noEdgeBiomes.add(temperateForest);
		noEdgeBiomes.add(coniferousForest);
		noEdgeBiomes.add(coniferousForestSnow);
		noEdgeBiomes.add(patagoniaMountains);
		noEdgeBiomes.add(badlandsPlateau);
		noEdgeBiomes.remove(badlands);

		noEdgeBiomes132.addAll(noEdgeBiomes);
		noEdgeBiomes132.add(jungleHills);

		noEdgeBiomes140.addAll(noEdgeBiomes132);
		noEdgeBiomes140.remove(badlandsPlateau);
	}

	public static boolean shouldBiomeConnectWithEdge(int biome, WorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_4_0)) {
			return !noEdgeBiomes140.contains(BiomeGenBase.biomeList[biome]);
		}
		if (generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
			return !noEdgeBiomes132.contains(BiomeGenBase.biomeList[biome]);
		}
		else {
			return !noEdgeBiomes.contains(BiomeGenBase.biomeList[biome]);
		}
	}

	public static boolean doesBiomeIgnoreEdgeRestrictions(int currentBiome, int biome1, int biome2, int biome3, int biome4) {
		return (currentBiome == badlands.biomeID &&
				biome1 != badlandsPlateau.biomeID &&
				biome2 != badlandsPlateau.biomeID &&
				biome3 != badlandsPlateau.biomeID &&
				biome4 != badlandsPlateau.biomeID);
	}

	public static boolean shouldBiomeSpawnBeach(int biome, WorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_1)) {
			return !beachlessBiomes.contains(BiomeGenBase.biomeList[biome]);
		}
		else {
			return !beachlessBiomes132.contains(BiomeGenBase.biomeList[biome]);
		}
	}

	public static boolean shouldBiomeSpawnPerlinBeach(int biomeID) {
		BiomeGenBase biome = BiomeGenBase.biomeList[biomeID];
		return !noPerlinBeachBiomes.contains(biome);
	}

	public static ArrayList<BTABiome> getBiomes() {
		return biomeList;
	}

	public static ArrayList<BTABiome> getBiomesDeco() {
		return biomeListDeco;
	}

	public static boolean canBiomeSpawnMelon(BiomeGenBase biome) {
		return jungleTempleBiomes.contains(biome);
	}

	public static boolean canBiomeSpawnRedDesertTemple(BiomeGenBase biome) {
		return redDesertTempleBiomes.contains(biome);
	}

	public static boolean canBiomeSpawnStronghold(BiomeGenBase biome) {
		return biome != BiomeGenBase.ocean && biome != BiomeGenBase.frozenOcean;
	}

	public static ArrayList<BiomeGenBase> getEdgeBiomes(WorldConfigurationInfo generatorInfo) {
		return edgeBiomes;
	}
}