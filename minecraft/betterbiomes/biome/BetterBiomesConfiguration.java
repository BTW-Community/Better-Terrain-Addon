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
import betterbiomes.biome.biomes.RedSandBeachBiome;
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
import betterterrain.world.config.WorldConfigurationInfo;
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
	public static final BTABiome ancientForest = new AncientForestBiome(ANCIENT_FOREST_ID, "betterbiomes:ancient_forest", Climate.TEMPERATE)
			.setBiomeName("Ancient Forest")
			.setSurfaceBuilder(new AncientForestSurfaceBuilder())
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setLegacyCompatible();

	public static final BTABiome autumnForest = new AutmnForestBiome(AUTUMN_FOREST_ID, "betterbiomes:autumn_forest", Climate.TEMPERATE)
			.setBiomeName("Autumn Forest")
			.setTemperatureRainfall(0.9F, 0.2F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setDecoOnly()
			.setLegacyCompatible();

	public static final BTABiome cherryForest = new CherryForestBiome(CHERRY_FOREST_ID, "betterbiomes:cherry_forest", Climate.TEMPERATE)
			.setBiomeName("Cherry Forest")
			.setTemperatureRainfall(0.9F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setDecoOnly()
			.setLegacyCompatible();

	public static final BTABiome grasslands = new GrasslandsBiome(GRASSLANDS_ID, "betterbiomes:grasslands", Climate.TEMPERATE)
			.setBiomeName("Grasslands")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.5F, 0.6F);

	public static final BTABiome heathland = new HeathlandBiome(HEATHLAND_ID, "betterbiomes:heathland", Climate.TEMPERATE)
			.setBiomeName("Heathland")
			.setSurfaceBuilder(new HeathlandSurfaceBuilder())
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.7F, 0.4F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setLegacyCompatible();

	public static final BTABiome highlands = new HighlandsBiome(HIGHLANDS_ID, "betterbiomes:highlands", Climate.TEMPERATE)
			.setBiomeName("Highlands")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.8F, 2.5F);

	public static final BTABiome mangroveForest = new MangroveForestBiome(MANGROVE_FOREST_ID, "betterbiomes:mangrove_forest", Climate.TEMPERATE)
			.setBiomeName("Mangal")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(0.8F, 0.9F)
			.setMinMaxHeight(-0.3F, 0.2F);

	public static final BTABiome mysticForest = new MysticForestBiome(MYSTIC_FOREST_ID, "betterbiomes:mystic_forest", Climate.TEMPERATE)
			.setBiomeName("Mystic Forest")
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(0.3F, 1.5F)
			.setLegacyCompatible();

	public static final BTABiome orchard = new OrchardBiome(ORCHARD_ID, "betterbiomes:orchard", Climate.TEMPERATE)
			.setBiomeName("Orchard")
			.setSurfaceBuilder(new OrchardSurfaceBuilder())
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.7F, 0.5F)
			.setLegacyCompatible();

	public static final BTABiome steppe = new SteppeBiome(STEPPE_ID, "betterbiomes:steppe", Climate.TEMPERATE)
			.setBiomeName("Steppe")
			.setSurfaceBuilder(new SteppeSurfaceBuilder())
			.setSpawnsVillages(true)
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(0.8F, 0.1F)
			.setMinMaxHeight(0.3F, 0.5F)
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome temperateForest = new TemperateForestBiome(TEMPERATE_FOREST_ID, "betterbiomes:temperate_forest", Climate.TEMPERATE)
			.setBiomeName("Temperate Forest")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.6F, 0.7F)
			.setMinMaxHeight(0.2F, 1.0F)
			.setLegacyCompatible();
	
	public static final BTABiome woods = new WoodsBiome(WOODS_ID, "betterbiomes:woods", Climate.TEMPERATE)
			.setBiomeName("Woods")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setLegacyCompatible();

	//Arid
	public static final BTABiome aridForest = new AridForestBiome(ARID_FOREST_ID, "betterbiomes:arid_forest", Climate.ARID)
			.setBiomeName("Arid Forest")
			.setSurfaceBuilder(new StonySurfaceBuilder())
			.setTemperatureRainfall(0.8F, 0.3F)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome badlandsPlateau = new BadlandsPlateauBiome(BADLANDS_PLATEAU_ID, "betterbiomes:badlands_plateau", Climate.ARID)
			.setBiomeName("Badlands Plateau")
			.setSurfaceBuilder(new BadlandsPlateauSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.8F, 2.0F)
			.setPlateau()
			.setDecoOnly()
			.setLegacyCompatible();
	
	public static final BTABiome chaparral = new ChapparalBiome(CHAPPARAL_ID, "betterbiomes:chapparal", Climate.ARID)
			.setBiomeName("Chaparral")
			.setSurfaceBuilder(new StonySurfaceBuilder())
			.setSpawnsPumpkins()
			.setSpawnsVillages(true)
			.setTemperatureRainfall(0.8F, 0.4F)
			.setMinMaxHeight(0.3F, 0.6F)
			.setLegacyCompatible();
	
	public static final BTABiome desert = new DesertBiome(DESERT_ID, "betterbiomes:desert", Climate.ARID)
			.setBiomeName("Better Desert")
			.setSpawnsVillages(true)
			.setDisableRain()
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.2F, 0.4F)
			.setNotSpawnable()
			.setLegacyCompatible();

	public static final BTABiome dunes = new DunesBiome(DUNES_ID, "betterbiomes:dunes", Climate.ARID)
			.setBiomeName("Dunes")
			.setDisableRain()
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.5F, 1.5F)
			.setNotSpawnable()
			.setLegacyCompatible();

	public static final BTABiome outback = new OutbackBiome(OUTBACK_ID, "betterbiomes:outback", Climate.ARID)
			.setBiomeName("Outback")
			.setSurfaceBuilder(new OutbackSurfaceBuilder())
			.setSpawnsVillages(false)
			.setSpawnsRedDesertTemples()
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.1F, 0.4F)
			.setDecoOnly()
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome plains = new PlainsBiome(PLAINS_ID, "betterbiomes:plains", Climate.ARID)
			.setBiomeName("Better Plains")
			.setSpawnsPumpkins()
			.setSpawnsSugarCane()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.8F, 0.3F)
			.setMinMaxHeight(0.2F, 0.4F);

	public static final BTABiome savanna = new SavannaBiome(SAVANNA_ID, "betterbiomes:savanna", Climate.ARID)
			.setBiomeName("Savanna")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.1F, 0.3F)
			.setLegacyCompatible();

	//Tropical
	public static final BTABiome jungle = new JungleBiome(JUNGLE_ID, "betterbiomes:jungle", Climate.TROPICAL)
			.setBiomeName("Better Jungle")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.4F)
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome lushDesert = new LushDesertBiome(LUSH_DESERT_ID, "betterbiomes:lush_desert", Climate.TROPICAL)
			.setBiomeName("Lush Desert")
			.setSpawnsVillages(true)
			.setSpawnsDesertTemples()
			.setDisableRain()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(0.1F, 0.6F)
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome meadow = new MeadowBiome(MEADOW_ID, "betterbiomes:meadow", Climate.TROPICAL)
			.setBiomeName("Meadow")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.7F, 1.0F)
			.setMinMaxHeight(0.1F, 0.4F)
			.setLegacyCompatible();

	public static final BTABiome rainforest = new RainforestBiome(RAINFOREST_ID, "betterbiomes:rainforest", Climate.TROPICAL)
			.setBiomeName("Rainforest")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.3F, 1.5F)
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome tropics = new TropicsBiome(TROPICS_ID, "betterbiomes:tropics", Climate.TROPICAL)
			.setBiomeName("Tropics")
			.setSurfaceBuilder(new TropicsSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.2F, 0.9F)
			.setLegacyCompatible()
			.setNotSpawnable();

	public static final BTABiome valleyMountains = new ValleyBiome(VALLEY_MOUNTAINS_ID, "betterbiomes:valley_highlands", Climate.TROPICAL)
			.setBiomeName("Valley Highlands")
			.setSurfaceBuilder(new TropicsSurfaceBuilder())
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.5F, 3.0F)
			.setLegacyCompatible();

	public static final BTABiome wetlands = new WetlandsBiome(WETLANDS_ID, "betterbiomes:wetlands", Climate.TROPICAL)
			.setBiomeName("Wetlands")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setMinMaxHeight(-0.1F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.9F)
			.setLegacyCompatible();

	public static final BTABiome willowGrove = new WillowGroveBiome(WILLOW_GROVE_ID, "betterbiomes:willow_grove", Climate.TROPICAL)
			.setBiomeName("Willow Grove")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setMinMaxHeight(-0.1F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.8F);

	//Cold
	public static final BTABiome alpine = new AlpineBiome(ALPINE_ID, "betterbiomes:alpine", Climate.COLD)
			.setBiomeName("Alpine")
			.setSurfaceBuilder(new AlpineSurfaceBuilder())
			.setTemperatureRainfall(0.2F, 0.8F)
			.setMinMaxHeight(0.5F, 3.0F)
			.setLegacyCompatible();

	public static final BTABiome birchForest = new BirchForestBiome(BIRCH_FOREST_ID, "betterbiomes:birch_forest", Climate.COLD)
			.setBiomeName("Birch Forest")
			.setTemperatureRainfall(0.4F, 0.4F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setLegacyCompatible();

	public static final BTABiome borealForest = new BorealForestBiome(BOREAL_FOREST_ID, "betterbiomes:boreal_forest", Climate.COLD)
			.setBiomeName("Boreal Forest")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.1F, 0.4F);

	public static final BTABiome brushland = new BrushlandBiome(BRUSHLAND_ID, "betterbiomes:brushland", Climate.COLD)
			.setBiomeName("Brushland")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.4F, 0.2F)
			.setMinMaxHeight(0.3F, 0.5F);

	public static final BTABiome coniferousForest = new ConiferousForestBiome(CONIFEROUS_FOREST_ID, "betterbiomes:coniferous_forest", Climate.COLD)
			.setBiomeName("Coniferous Forest")
			.setSurfaceBuilder(new ConiferousForestSurfaceBuilder())
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F)
			.setLegacyCompatible();

	public static final BTABiome fungalForest = new FungalForestBiome(FUNGAL_FOREST_ID, "betterbiomes:fungal_forest", Climate.COLD)
			.setBiomeName("Fungal Forest")
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setTemperatureRainfall(0.4F, 1.0F)
			.setMinMaxHeight(-0.1F, 1.2F)
			.setLegacyCompatible();

	public static final BTABiome mountains = new MountainBiome(MOUNTAINS_ID, "betterbiomes:mountains", Climate.COLD)
			.setBiomeName("Mountains")
			.setTemperatureRainfall(0.5F, 0.1F)
			.setMinMaxHeight(0.8F, 2.5F)
			.setLegacyCompatible();

	public static final BTABiome patagonia = new PatagoniaBiome(PATAGONIA_ID, "betterbiomes:patagonia", Climate.COLD)
			.setBiomeName("Patagonia")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsPumpkins()
			.setSpawnsSugarCane()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.3F, 0.6F)
			.setMinMaxHeight(0.0F, 0.5F);

	public static final BTABiome shield = new ShieldBiome(SHIELD_ID, "betterbiomes:shield", Climate.COLD)
			.setBiomeName("Shield")
			.setSurfaceBuilder(new StonySurfaceBuilder())
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.2F, 0.6F);

	//Snowy
	public static final BTABiome frozenSprings = new FrozenSpringsBiome(FROZEN_SPRINGS_ID, "betterbiomes:frozen_springs", Climate.SNOWY)
			.setBiomeName("Frozen Springs")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.2F, 0.6F)
			.setEnableSnow();

	public static final BTABiome icyPeaks = new IcyPeaksBiome(ICY_PEAKS_ID, "betterbiomes:icy_peaks", Climate.SNOWY)
			.setBiomeName("Icy Peaks")
			.setSurfaceBuilder(new IcyPeaksSurfaceBuilder())
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.5F, 1.5F)
			.setEnableSnow()
			.setNotSpawnable();

	public static final BTABiome siberia = new SiberiaBiome(SIBERIA_ID, "betterbiomes:siberia", Climate.SNOWY)
			.setBiomeName("Siberia")
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 0.7F)
			.setEnableSnow();

	public static final BTABiome snowyConiferousForest = new ConiferousForestBiome(SNOWY_CONIFEROUS_FOREST_ID, "betterbiomes:snowy_coniferous_forest", Climate.SNOWY)
			.setBiomeName("Snowy Coniferous Forest")
			.setSurfaceBuilder(new ConiferousForestSurfaceBuilder())
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F)
			.setEnableSnow()
			.setLegacyCompatible();

	public static final BTABiome snowyWoods = new WoodsBiome(SNOWY_WOODS_ID, "betterbiomes:snowy_woods", Climate.SNOWY)
			.setBiomeName("Snowy Woods")
			.setTemperatureRainfall(0.1F, 0.5F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setEnableSnow()
			.setLegacyCompatible();

	public static final BTABiome tundra = new TundraBiome(TUNDRA_ID, "betterbiomes:tundra", Climate.SNOWY)
			.setBiomeName("Tundra")
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.1F, 0.4F)
			.setEnableSnow()
			.setLegacyCompatible()
			.setNotSpawnable();

	// ------ Secondary Biomes ------ //
	//Variants
	public static final BTABiome ancientForestHills = new AncientForestBiome(ANCIENT_FOREST_HILLS_ID, "betterbiomes:ancient_forest_hills", Climate.TEMPERATE)
			.setBiomeName("Ancient Forest Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome autumnForestHills = new AutmnForestBiome(AUTUMN_FOREST_HILLS_ID, "betterbiomes:autumn_forest_hills", Climate.TEMPERATE)
			.setBiomeName("Autumn Forest Hills")
			.setTemperatureRainfall(0.9F, 0.2F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome badlands = new BadlandsBiome(BADLANDS_ID, "betterbiomes:badlands", Climate.ARID)
			.setBiomeName("Badlands")
			.setSurfaceBuilder(new BadlandsSurfaceBuilder())
			.setSpawnsRedDesertTemples()
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.1F, 0.1F)
			.setNotSpawnable();

	public static final BTABiome birchForestHills = new BirchForestBiome(BIRCH_FOREST_HILLS_ID, "betterbiomes:birch_forest_hills", Climate.COLD)
			.setBiomeName("Birch Forest Hills")
			.setTemperatureRainfall(0.4F, 0.4F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome borealForestHills = new BorealForestBiome(BOREAL_FOREST_HILLS_ID, "betterbiomes:boreal_forest_hills", Climate.COLD)
			.setBiomeName("Boreal Forest Hills")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome chaparralHills = new ChapparalBiome(CHAPPARAL_HILLS_ID, "betterbiomes:chapparal_hills", Climate.ARID)
			.setBiomeName("Chaparral Hills")
			.setSurfaceBuilder(new StonySurfaceBuilder())
			.setSpawnsPumpkins()
			.setTemperatureRainfall(0.8F, 0.4F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome cherryForestHills = new CherryForestBiome(CHERRY_FOREST_HILLS_ID, "betterbiomes:cherry_forest_hills", Climate.TEMPERATE)
			.setBiomeName("Cherry Forest Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome desertHills = new DesertBiome(DESERT_HILLS_ID, "betterbiomes:desert_hills", Climate.ARID)
			.setBiomeName("Better Desert Hills")
			.setDisableRain()
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.3F, 1.0F)
			.setNotSpawnable();

	public static final BTABiome frozenSpringsPond = new FrozenSpringsPondBiome(FROZEN_SPRINGS_POND_ID, "betterbiomes:frozen_springs", Climate.SNOWY)
			.setBiomeName("Frozen Springs Pond")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.2F, 0.1F)
			.setMinMaxHeight(-0.3F, 0.0F);

	public static final BTABiome fungalForestFlat = new FungalForestBiome(FUNGAL_FOREST_FLAT_ID, "betterbiomes:fungal_forest", Climate.COLD)
			.setBiomeName("Fungal Forest Lowlands")
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setMinMaxHeight(-0.1F, 0.5F)
			.setTemperatureRainfall(0.4F, 1.0F);

	public static final BTABiome grasslandsLake = new GrasslandsLakeBiome(GRASSLANDS_LAKE_ID, "betterbiomes:grasslands_lake", Climate.TEMPERATE)
			.setBiomeName("Grasslands Lake")
			.setTemperatureRainfall(0.5F, 0.6F)
			.setMinMaxHeight(-0.3F, 0.0F);

	public static final BTABiome jungleHills = new JungleBiome(JUNGLE_HILLS_ID, "betterbiomes:jungle_hills", Climate.TROPICAL)
			.setBiomeName("Better Jungle Hills")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(1.8F, 0.5F)
			.setNotSpawnable();

	public static final BTABiome mangroveForestIsland = new MangroveForestBiome(MANGROVE_FOREST_ISLAND_ID, "betterbiomes:mangrove_forest", Climate.TEMPERATE)
			.setBiomeName("Mangal Island")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setMinMaxHeight(0.0F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.9F)
			.setNotSpawnable();

	public static final BTABiome oasis = new OasisBiome(OASIS_ID, "betterbiomes:oasis", Climate.TROPICAL)
			.setBiomeName("Oasis")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsVillages(true)
			.setDisableRain()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(-0.2F, 0.1F)
			.setNotSpawnable();

	public static final BTABiome patagoniaMountains = new PatagoniaMountainBiome(PATAGONIA_MOUNTAINS_ID, "betterbiomes:patagonia_mountains", Climate.COLD)
			.setBiomeName("Patagonia Mountains")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setTemperatureRainfall(0.1F, 0.6F)
			.setMinMaxHeight(2.0F, 4.0F)
			.setEnableSnow();

	public static final BTABiome savannaHills = new SavannaBiome(SAVANNA_HILLS_ID, "betterbiomes:savanna_hills", Climate.ARID)
			.setBiomeName("Savanna Hills")
			.setSpawnsPumpkins()
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.3F, 0.8F);

	public static final BTABiome savannaPlateau = new SavannaBiome(SAVANNA_PLATEAU_ID, "betterbiomes:savanna_plateau", Climate.ARID)
			.setBiomeName("Savanna Plateau")
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.3F, 0.8F)
			.setPlateau();

	public static final BTABiome snowyWoodsHills = new WoodsBiome(SNOWY_WOODS_HILLS, "betterbiomes:snowy_woods_hills", Climate.SNOWY)
			.setBiomeName("Snowy Woods Hills")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.5F)
			.setMinMaxHeight(0.3F, 1.0F);

	public static final BTABiome valley = new ValleyBiome(VALLEY_ID, "betterbiomes:valley", Climate.TROPICAL)
			.setBiomeName("Valley")
			.setSurfaceBuilder(new TropicsSurfaceBuilder())
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.1F, 0.6F);

	public static final BTABiome wetlandsHills = new WetlandsBiome(WETLANDS_HILLS_ID, "betterbiomes:wetlands_hills", Climate.TROPICAL)
			.setBiomeName("Wetlands Hills")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setMinMaxHeight(-0.2F, 0.6F)
			.setTemperatureRainfall(0.8F, 0.9F);

	public static final BTABiome willowHills = new WillowGroveBiome(WILLOW_HILLS_ID, "betterbiomes:willow_hills", Climate.TROPICAL)
			.setBiomeName("Willow Hills")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setMinMaxHeight(0.2F, 0.6F)
			.setTemperatureRainfall(0.8F, 0.8F);

	public static final BTABiome woodsHills = new WoodsBiome(WOODS_HILLS_ID, "betterbiomes:woods_hills", Climate.TEMPERATE)
			.setBiomeName("Wooded Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

	//Rivers
	public static final BTABiome badlandsRiver = new BadlandsRiverBiome(BADLANDS_RIVER_ID, "betterbiomes:badlands_river")
			.setBiomeName("Badlands River")
			.setSurfaceBuilder(new BadlandsSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome desertRiver = new DesertRiverBiome(DESERT_RIVER_ID, "betterbiomes:desert_river")
			.setBiomeName("Desert River")
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome frozenRiver = new RiverBiome(FROZEN_RIVER_ID, "betterbiomes:frozen_river", Climate.SNOWY)
			.setBiomeName("Better Frozen River")
			.setEnableSnow()
			.setMinMaxHeight(-0.5F, 0.0F)
			.setTemperatureRainfall(0.0F, 0.5F)
			.setRiver();

	public static final BTABiome jungleRiver = new JungleRiverBiome(JUNGLE_RIVER_ID, "betterbiomes:jungle_river")
			.setBiomeName("Jungle River")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome mysticRiver = new MysticRiverBiome(MYSTIC_RIVER_ID, "betterbiomes:mystic_river")
			.setBiomeName("Mystic River")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome orchardRiver = new OrchardRiverBiome(ORCHARD_RIVER_ID, "betterbiomes:orchard_river")
			.setBiomeName("Orchard River")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome outbackRiver = new OutbackRiverBiome(OUTBACK_RIVER_ID, "betterbiomes:outback_river")
			.setBiomeName("Outback River")
			.setSurfaceBuilder(new OutbackSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome patagoniaRiver = new PatagoniaRiverBiome(PATAGONIA_RIVER_ID, "betterbiomes:patagonia_river")
			.setBiomeName("Patagonia River")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.3F, 0.6F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome rainforestRiver = new RainforestRiverBiome(RAINFOREST_RIVER_ID, "betterbiomes:rainforest_river")
			.setBiomeName("Rainforest River")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome river = new RiverBiome(RIVER_ID, "betterbiomes:river", Climate.TEMPERATE)
			.setBiomeName("Better River")
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome tropicsRiver = new TropicsRiverBiome(TROPICS_RIVER_ID, "betterbiomes:tropics_river")
			.setBiomeName("Tropics River")
			.setSpawnsSugarCane()
			.setSurfaceBuilder(new TropicsSurfaceBuilder())
			.setTemperatureRainfall(2.0F, 2.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome wetlandsRiver = new WetlandsRiverBiome(WETLANDS_RIVER_ID, "betterbiomes:wetlands_river")
			.setBiomeName("Wetlands River")
			.setSpawnsSugarCane()
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setTemperatureRainfall(0.8F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome willowRiver = new WillowRiverBiome(WILLOW_GROVE_RIVER_ID, "betterbiomes:willow_grove_river")
			.setBiomeName("Willow Grove River")
			.setSpawnsSugarCane()
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setTemperatureRainfall(0.6F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	//Edges
	public static final BTABiome alpineEdge = new AlpineBiome(ALPINE_EDGE_ID, "betterbiomes:alpine_edge", Climate.COLD)
			.setBiomeName("Alpine Edge")
			.setSurfaceBuilder(new AlpineSurfaceBuilder())
			.setTemperatureRainfall(0.2F, 0.8F)
			.setMinMaxHeight(0.2F, 0.5F)
			.setEdge();

	public static final BTABiome highlandsEdge = new HighlandsBiome(HIGHLANDS_EDGE_ID, "betterbiomes:highlands_edge", Climate.TEMPERATE)
			.setBiomeName("Highlands Edge")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.8F, 2.5F)
			.setEdge();

	public static final BTABiome icyPeaksEdge = new IcyPeaksBiome(ICY_PEAKS_EDGE_ID, "betterbiomes:icy_peaks_edge", Climate.SNOWY)
			.setBiomeName("Icy Peaks Edge")
			.setSurfaceBuilder(new IcyPeaksSurfaceBuilder())
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setEdge();

	public static final BTABiome jungleEdge = new JungleEdgeBiome(JUNGLE_EDGE_ID, "betterbiomes:jungle_edge", Climate.TROPICAL)
			.setBiomeName("Better Jungle Edge")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.4F)
			.setEdge()
			.setNotSpawnable();

	public static final BTABiome mountainEdge = new MountainBiome(MOUNTAIN_EDGE_ID, "betterbiomes:mountain_edge", Climate.COLD)
			.setBiomeName("Mountains Edge")
			.setTemperatureRainfall(0.5F, 0.1F)
			.setMinMaxHeight(0.2F, 0.5F)
			.setEdge();

	public static final BTABiome rainforestEdge = new RainforestEdgeBiome(RAINFOREST_EDGE_ID, "betterbiomes:rainforest_edge", Climate.TROPICAL)
			.setBiomeName("Rainforest Edge")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.3F, 1.5F)
			.setEdge()
			.setNotSpawnable();

	public static final BTABiome tropicsEdge = new TropicsBiome(TROPICS_EDGE_ID, "betterbiomes:tropics_edge", Climate.TROPICAL)
			.setBiomeName("Tropics Edge")
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(-0.3F, 0.1F)
			.setEdge()
			.setNotSpawnable();

	//Beaches
	public static final BTABiome redSandBeach = new RedSandBeachBiome(RED_SAND_BEACH_ID, "betterbiomes:red_sand_beach", Climate.ARID)
			.setBiomeName("Red Sand Beach")
			.setSurfaceBuilder(new BadlandsSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setBeach()
			.setNotSpawnable();

	public static final BTABiome beach = new BeachBiome(BEACH_ID, "betterbiomes:beach", Climate.TEMPERATE)
			.setBiomeName("Better Beach")
			.setTemperatureRainfall(0.8F, 0.4F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setBeach()
			.setNotSpawnable();

	public static final BTABiome frozenBeach = new BeachBiome(FROZEN_BEACH_ID, "betterbiomes:frozen_beach", Climate.SNOWY)
			.setBiomeName("Frozen Beach")
			.setTemperatureRainfall(0.0F, 0.4F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setEnableSnow()
			.setBeach()
			.setNotSpawnable();

	// ------ Nether Biomes ------ //
	public static final BTABiome netherWastes = new NetherWastesBiome(NETHER_WASTES_ID, "betterbiomes:nether_wastes")
			.setBiomeName("Nether Wastes")
			.setSurfaceBuilder(new NetherSurfaceBuilder())
			.setNether();

	public static final BTABiome ashFields = new AshFieldsBiome(ASH_FIELDS_ID, "betterbiomes:ash_fields")
			.setBiomeName("Ash Fields")
			.setSurfaceBuilder(new AshFieldsSurfaceBuilder())
			.setNether();

	public static final BTABiome basaltDeltas = new BasaltDeltasBiome(BASALT_DELTAS_ID, "betterbiomes:basalt_deltas")
			.setBiomeName("Basalt Deltas")
			.setSurfaceBuilder(new BasaltDeltasSurfaceBuilder())
			.setNether();

	public static final BTABiome soulSandValley = new SoulSandValleyBiome(SOUL_SAND_VALLEY_ID, "betterbiomes:soul_sand_valley")
			.setBiomeName("Soul Sand Valley")
			.setSurfaceBuilder(new SoulSandValleySurfaceBuilder())
			.setNether();

	public static final BTABiome obsidianGrove = null;

	public static final BTABiome crystalCaverns = new CrystalCavernsBiome(CRYSTAL_CAVERNS_ID, "betterbiomes:crystal_caverns")
			.setBiomeName("Crystal Caverns")
			.setSurfaceBuilder(new CrystalCavernsSurfaceBuilder())
			.setNether();

	public static final BTABiome petrifiedForest = new PetrifiedForestBiome(PETRIFIED_FOREST_ID, "betterbiomes:petrified_forest")
			.setBiomeName("Petrified Forest")
			.setSurfaceBuilder(new NetherSurfaceBuilder())
			.setNether();

	// ------ Deprecated ------ //
	public static final BTABiome aspenGrove = new AspenGroveBiome(ASPEN_GROVE_ID, "betterbiomes:aspen_grove", Climate.COLD)
			.setBiomeName("Aspen Grove")
			.setTemperatureRainfall(0.2F, 0.8F)
			.setMinMaxHeight(0.5F, 3.0F);

	public static final BTABiome badlandsEdge = new BadlandsBiome(BADLANDS_EDGE_ID, "betterbiomes:badlands_edge", Climate.ARID)
			.setBiomeName("Badlands Edge")
			.setSurfaceBuilder(new BadlandsSurfaceBuilder())
			.setSpawnsRedDesertTemples()
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.1F, 0.1F)
			.setEdge();

	public static final BTABiome coniferousForestClearing = new ConiferousForestClearingBiome(CONIFEROUS_FOREST_CLEARING_ID, "betterbiomes:coniferous_forest_clearing", Climate.COLD)
			.setBiomeName("Coniferous Forest Clearing")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F);

	public static final BTABiome heathlandWoods = new HeathlandWoodsBiome(HEATHLAND_WOODS_ID, "betterbiomes:heathland_woods", Climate.TEMPERATE)
			.setBiomeName("Heathland Woods")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.7F, 0.4F)
			.setMinMaxHeight(0.1F, 0.5F);

	public static final BTABiome icyPeaksForested = new ForestedIcyPeaksBiome(ICY_PEAKS_FORESTED_ID, "betterbiomes:icy_peaks_forested", Climate.SNOWY)
			.setBiomeName("Forested Icy Peaks")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.5F, 1.5F);

	public static final BTABiome oldValley = new OldValleyBiome(OLD_VALLEY_ID, "betterbiomes:old_valley", Climate.TROPICAL)
			.setBiomeName("Old Valley")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.1F, 0.8F);
	
	public static final BTABiome orchardClearing = new OrchardClearingBiome(ORCHARD_CLEARING_ID, "betterbiomes:orchard_clearing", Climate.TEMPERATE)
			.setBiomeName("Orchard Clearing")
			.setSpawnsVillages(false)
			.setSpawnsPumpkins()
			.setTemperatureRainfall(0.7F, 0.5F);

	public static final BTABiome snowyConiferousForestClearing = new ConiferousForestClearingBiome(SNOWY_CONIFEROUS_FOREST_CLEARING_ID, "betterbiomes:snowy_coniferous_forest_clearing", Climate.SNOWY)
			.setBiomeName("Snowy Coniferous Forest Clearing")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F);

	public static final BTABiome woodedSteppe = new WoodedSteppeBiome(WOODED_STEPPE_ID, "betterbiomes:wooded_steppe", Climate.TEMPERATE)
			.setBiomeName("Wooded Steppe")
			.setSpawnsVillages(true)
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(0.8F, 0.1F)
			.setMinMaxHeight(0.3F, 0.5F)
			.setNotSpawnable();

	private static ArrayList<BiomeGenBase> beachlessBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> beachlessBiomes132 = new ArrayList();

	private static ArrayList<BiomeGenBase> edgeBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> noEdgeBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> noEdgeBiomes132 = new ArrayList();
	private static ArrayList<BiomeGenBase> noEdgeBiomes140 = new ArrayList();

	private static ArrayList<BiomeGenBase> pumpkinBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> reedBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> villageBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> jungleTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> desertTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> redDesertTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> witchHutBiomes = new ArrayList();

	private static Random rand = new Random();
	private static long lastRandSeed = 0;
	
	private BetterBiomesConfiguration() {}

	public static void init() {
		filterBeachBiomes();
		addBiomesWithEdge();
		filterEdgeBiomes();
	}

	public void addBiomesToList(ArrayList<BTABiome> biomeList) {
		biomeList.add(woods);
		biomeList.add(desert);
		biomeList.add(lushDesert);
		biomeList.add(savanna);
		biomeList.add(wetlands);
		biomeList.add(birchForest);
		biomeList.add(snowyWoods);
		biomeList.add(chaparral);
		biomeList.add(ancientForest);
		biomeList.add(tropics);
		biomeList.add(jungle);
		biomeList.add(alpine);
		biomeList.add(fungalForest);
		biomeList.add(coniferousForest);
		biomeList.add(snowyConiferousForest);
		biomeList.add(mysticForest);
		biomeList.add(rainforest);
		biomeList.add(meadow);
		biomeList.add(mountains);
		biomeList.add(dunes);
		biomeList.add(heathland);
		biomeList.add(temperateForest);
		biomeList.add(valleyMountains);
		biomeList.add(tundra);
		biomeList.add(orchard);
		biomeList.add(steppe);
		
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

		biomeList.add(outback);
		biomeList.add(cherryForest);
		biomeList.add(badlandsPlateau);
		biomeList.add(autumnForest);
	}
	
	public void setBiomeVariants() {
		//Sub biomes
		WorldConfigurationInfo.Condition pre140 = new WorldConfigurationInfo.Condition() {
			@Override
			public boolean satisfiesContraints(WorldConfigurationInfo info) {
				return info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4);
			}
		};
		
		alpine.addSubVariant(aspenGrove, pre140);
		ancientForest.addSubVariant(ancientForestHills);
		autumnForest.addSubVariant(autumnForestHills);
		badlandsPlateau.addSubVariant(badlands);
		birchForest.addSubVariant(birchForestHills);
		borealForest.addSubVariant(borealForestHills);
		chaparral.addSubVariant(chaparralHills);
		cherryForest.addSubVariant(cherryForestHills);
		coniferousForest.addSubVariant(coniferousForestClearing, pre140);
		desert.addSubVariant(desertHills);
		fungalForest.addSubVariant(fungalForestFlat);
		grasslands.addSubVariant(grasslandsLake);
		heathland.addSubVariant(heathlandWoods, pre140);
		icyPeaks.addSubVariant(icyPeaksForested, pre140);
		jungle.addSubVariant(jungleHills);
		orchard.addSubVariant(orchardClearing, pre140);
		patagonia.addSubVariant(patagoniaMountains);
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
		snowyConiferousForest.addSubVariant(snowyConiferousForestClearing, pre140);
		snowyWoods.addSubVariant(snowyWoodsHills);
		steppe.addSubVariant(woodedSteppe, pre140);
		valleyMountains.addSubVariant(valley);
		woods.addSubVariant(woodsHills);
		
		//Sub biomes (Common)
		lushDesert.addSubVariantCommon(oasis);
		wetlands.addSubVariantCommon(wetlandsHills);
		willowGrove.addSubVariantCommon(willowHills);
		
		//Sporadic
		
		//Rivers
		
		//Edges
	}

	public static void filterBeachBiomes() {
		beachlessBiomes.add(alpine);
		beachlessBiomes.add(fungalForest);
		beachlessBiomes.add(fungalForestFlat);
		beachlessBiomes.add(coniferousForest);
		beachlessBiomes.add(snowyConiferousForest);
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

	public static int getRiverVariantForBiomes(int baseBiome) {
		int riverBiome = -1;

		if (BiomeGenBase.biomeList[baseBiome] instanceof RiverBiome) {
			riverBiome = baseBiome;
		}
		else if (baseBiome == desert.biomeID || baseBiome == desertHills.biomeID || baseBiome == dunes.biomeID) {
			riverBiome = desertRiver.biomeID;
		}
		else if (baseBiome == mysticForest.biomeID) {
			riverBiome = mysticRiver.biomeID;
		}
		else if (baseBiome == rainforest.biomeID || baseBiome == rainforestEdge.biomeID) {
			riverBiome = rainforestRiver.biomeID;
		}
		else if (baseBiome == outback.biomeID) {
			riverBiome = outbackRiver.biomeID;
		}
		else if (baseBiome == badlands.biomeID || baseBiome == badlandsPlateau.biomeID || baseBiome == badlandsEdge.biomeID) {
			riverBiome = badlandsRiver.biomeID;
		}
		else if (baseBiome == tropics.biomeID || baseBiome == tropicsEdge.biomeID) {
			riverBiome = tropicsRiver.biomeID;
		}
		else if (baseBiome == orchard.biomeID) {
			riverBiome = orchardRiver.biomeID;
		}
		else if (baseBiome == jungle.biomeID || baseBiome == jungleHills.biomeID || baseBiome == jungleEdge.biomeID) {
			riverBiome = jungleRiver.biomeID;
		}
		else if (baseBiome == wetlands.biomeID || baseBiome == wetlandsHills.biomeID) {
			riverBiome = wetlandsRiver.biomeID;
		}
		else if (baseBiome == willowGrove.biomeID || baseBiome == willowHills.biomeID) {
			riverBiome = willowRiver.biomeID;
		}
		else if (baseBiome == patagonia.biomeID || baseBiome == patagoniaMountains.biomeID) {
			riverBiome = patagoniaRiver.biomeID;
		}
		else if (BiomeGenBase.biomeList[baseBiome].getEnableSnow()) {
			riverBiome = frozenRiver.biomeID;
		}
		else {
			riverBiome = river.biomeID;
		}

		return riverBiome;
	}

	public static int getBeachVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorInfo) {
		int beachBiome = -1;

		if (baseBiome == outback.biomeID || baseBiome == badlands.biomeID || baseBiome == redSandBeach.biomeID) {
			beachBiome = redSandBeach.biomeID;
		}
		else if ((baseBiome == snowyWoods.biomeID || baseBiome == tundra.biomeID || baseBiome == siberia.biomeID || baseBiome == frozenSprings.biomeID || baseBiome == frozenBeach.biomeID) && generatorInfo.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2)) {
			beachBiome = frozenBeach.biomeID;
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
			sporadicBiome = wetlandsRiver.biomeID;
		}
		else if (baseBiome == willowGrove.biomeID || baseBiome == willowHills.biomeID) {
			sporadicBiome = willowRiver.biomeID;
		}
		else if (baseBiome == jungle.biomeID) {
			sporadicBiome = jungleRiver.biomeID;
		}
		else if (baseBiome == rainforest.biomeID) {
			sporadicBiome = rainforestRiver.biomeID;
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
		noEdgeBiomes.add(snowyConiferousForest);
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

	public static ArrayList<BiomeGenBase> getEdgeBiomes(WorldConfigurationInfo generatorInfo) {
		return edgeBiomes;
	}
	
	private static BetterBiomesConfiguration instance;
	
	public static BetterBiomesConfiguration getInstance() {
		if (instance == null) {
			instance = new BetterBiomesConfiguration();
		}
		
		return instance;
	}
}