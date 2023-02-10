package betterbiomes.biome;

import java.util.ArrayList;

import betterbiomes.biome.biomes.*;
import betterbiomes.biome.biomes.deprecated.*;
import betterbiomes.world.generate.surface.*;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BTABiomeConfiguration;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.biome.Climate;
import betterterrain.biome.biomes.BeachBiome;
import betterterrain.biome.biomes.WoodsBiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.NetherSurfaceBuilder;
import betterterrain.world.generate.surface.NoShorelineSurfaceBuilder;
import betterterrain.world.generate.surface.StonySurfaceBuilder;
import betterterrain.world.generate.surface.SwampSurfaceBuilder;
import btw.util.hardcorespawn.HardcoreSpawnUtils;
import net.minecraft.src.BiomeGenBase;

public class BetterBiomesConfiguration extends BiomeConfiguration {
	public static final int
	//Nether
			NETHER_WASTES_ID = 90,
			ASH_FIELDS_ID = 91,
			BASALT_DELTAS_ID = 92,
			SOUL_SAND_VALLEY_ID = 93,
			OBSIDIAN_GROVE_ID = 94,
			CRYSTAL_CAVERNS_ID = 95,
			PETRIFIED_FOREST_ID = 96,
	//Primary
			FIELD_ID = 98,
			BOWER_ID = 99,
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
			OLD_GROWTH_WOODLAND_ID = 111,
			TROPICS_ID = 112,
			JUNGLE_ID = 113,
			ALPINE_ID = 114,
			ASPEN_GROVE_ID = 115,
			FUNGAL_FOREST_ID = 116,
			CONIFEROUS_FOREST_ID = 117,
			CONIFEROUS_FOREST_CLEARING_ID = 118,
			SNOWY_CONIFEROUS_FOREST_ID = 119,
			SNOWY_CONIFEROUS_FOREST_CLEARING_ID = 120,
			MYSTIC_Valley_ID = 121,
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
			FLORAL_FOREST_ID = 146,
			REDWOOD_FOREST_ID = 147,
			ICE_MARSH_ID = 148,
			FORESTED_HIGHLANDS_ID = 149,
	//Sub biomes
			WOODS_HILLS_ID = 150,
			DESERT_HILLS_ID = 151,
			SAVANNA_HILLS_ID = 152,
			BIRCH_FOREST_HILLS_ID = 153,
			SNOWY_WOODS_HILLS = 154,
			CHAPPARAL_HILLS_ID = 155,
			OLD_GROWTH_WOODLAND_HILLS_ID = 156,
			JUNGLE_HILLS_ID = 157,
			FUNGAL_FOREST_FLAT_ID = 158,
			WETLANDS_HILLS_ID = 159,
			CHERRY_BLOSSOM_GROVE_HILLS_ID = 160,
			SEASONAL_FOREST_HILLS_ID = 161,
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
			FIR_CANYON_VALLEY_ID = 172,
			FLORAL_PLATEAU_ID = 173,
			MAPLE_WOODS_HILLS_ID = 174,
			SNOWY_MAPLE_WOODS_HILLS_ID = 175,
			ICE_MARSH_POND_ID = 176,
			
	//Deco only
			OUTBACK_ID = 180,
			CHERRY_BLOSSOM_GROVE_ID = 181,
			BADLANDS_ID = 182,
			BADLANDS_PLATEAU_ID = 183,
			SEASONAL_FOREST_ID = 184,
			IVORY_HILLS_ID = 185,
			HOT_SPRINGS_ID = 186,
			VOLCANIC_JUNGLE_ID = 187,
			FIR_CANYON_ID = 188,
			DARK_FOREST_ID = 189,
			MAPLE_WOODS_ID = 190,
			SNOWY_MAPLE_WOODS_ID = 191,
		
	//Rivers
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
			VOLCANIC_RIVER_ID = 213,
			MANGROVE_RIVER_ID = 214,
			DARK_FOREST_RIVER_BIOME = 215,
	
	//Edges
			FORESTED_HIGHLANDS_EDGE_ID = 229,
			ALPINE_EDGE_ID = 230,
			MOUNTAIN_EDGE_ID = 231,
			BADLANDS_EDGE_ID = 232,
			ICY_PEAKS_EDGE_ID = 233,
			HIGHLANDS_EDGE_OLD_ID = 234,
			JUNGLE_EDGE_ID = 235,
			RAINFOREST_EDGE_ID = 236,
			TROPICS_EDGE_ID = 237,
			HOT_SPRINGS_EDGE_ID = 238,
			HIGHLANDS_EDGE_ID = 239,
	//Beaches
			RED_SAND_BEACH_ID = 240,
			BEACH_ID = 241,
			FROZEN_BEACH_ID = 242,
			VOLCANIC_BEACH_ID = 243,
			IVORY_BEACH_ID = 244,
		
			max_id = 256;

	// ------ Primary Biomes ------ //
	//Temperate
	public static final BTABiome cherryBlossomGrove = new CherryBlossomGroveBiome(CHERRY_BLOSSOM_GROVE_ID, "betterbiomes:cherry_forest", Climate.TEMPERATE)
			.setBiomeName("Cherry Blossom Grove")
			.setTemperatureRainfall(0.9F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setDecoOnly()
			.setLegacyCompatible();

	public static final BTABiome darkForest = new DarkForestBiome(DARK_FOREST_ID, "betterbiomes:dark_forest", Climate.TEMPERATE)
			.setBiomeName("Dark Forest")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.6F, 0.9F)
			.setMinMaxHeight(1.0F, 0.8F)
			.setDecoOnly();

	public static final BTABiome field = new FieldBiome(FIELD_ID, "betterbiomes:field", Climate.TEMPERATE)
			.setBiomeName("Field")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.6F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F);
	
	public static final BTABiome floralForest = new FloralForestBiome(FLORAL_FOREST_ID, "betterbiomes:floral_forest", Climate.TEMPERATE)
			.setBiomeName("Floral Forest")
			.setTemperatureRainfall(0.9F, 0.8F)
			.setMinMaxHeight(0.2F, 0.5F);

	public static final BTABiome forestedHighlands = new ForestedHighlandsBiome(FORESTED_HIGHLANDS_ID, "betterbiomes:forested_highlands", Climate.TEMPERATE)
			.setBiomeName("Forested Highlands")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.8F, 2.5F);

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
			.setBiomeName("Mangrove Forest")
			.setSurfaceBuilder(new MangroveForestSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(0.8F, 0.9F)
			.setMinMaxHeight(-0.3F, 0.2F);

	public static final BTABiome mysticValley = new MysticValleyBiome(MYSTIC_Valley_ID, "betterbiomes:mystic_valley", Climate.TEMPERATE)
			.setBiomeName("Mystic Valley")
			.setSpawnsSugarCane()
			.setSpawnsWitchHuts()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(0.3F, 1.5F)
			.setLegacyCompatible();

	public static final BTABiome oldGrowthWoodland = new OldGrowthWoodlandBiome(OLD_GROWTH_WOODLAND_ID, "betterbiomes:ancient_forest", Climate.TEMPERATE)
			.setBiomeName("Old Growth Woodland")
			.setSurfaceBuilder(new AncientForestSurfaceBuilder())
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setLegacyCompatible();

	public static final BTABiome orchard = new OrchardBiome(ORCHARD_ID, "betterbiomes:orchard", Climate.TEMPERATE)
			.setBiomeName("Orchard")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(0.7F, 0.5F)
			.setLegacyCompatible();

	public static final BTABiome redwoodForest = new RedwoodForestBiome(REDWOOD_FOREST_ID, "betterbiomes:redwood_forest", Climate.TEMPERATE)
			.setBiomeName("Redwood Forest")
			.setTemperatureRainfall(0.6F, 0.9F)
			.setMinMaxHeight(1.0F, 0.8F);

	public static final BTABiome seasonalForest = new SeasonalForestBiome(SEASONAL_FOREST_ID, "betterbiomes:autumn_forest", Climate.TEMPERATE)
			.setBiomeName("Seasonal Forest")
			.setSpawnsPumpkins()
			.setTemperatureRainfall(0.9F, 0.2F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setDecoOnly()
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

	//Arid
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

	public static final BTABiome dunes = new DunesBiome(DUNES_ID, "betterbiomes:dunes", Climate.ARID)
			.setBiomeName("Dunes")
			.setDisableRain()
			.setSpawnsDesertTemples()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.5F, 1.5F)
			.setNotSpawnable()
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setLegacyCompatible();
	
	public static final BTABiome firCanyon = new FirCanyonBiome(FIR_CANYON_ID, "betterbiomes:fir_canyon", Climate.ARID)
			.setBiomeName("Fir Canyon")
			.setSurfaceBuilder(new FirCanyonSurfaceBuilder(true))
			.setTemperatureRainfall(1.0F, 0.1F)
			.setMinMaxHeight(0.8F, 2.0F)
			.setDecoOnly();
	
	public static final BTABiome ivoryHills = new IvoryHillsBiome(IVORY_HILLS_ID, "betterbiomes:ivory_hills", Climate.ARID)
			.setBiomeName("Ivory Hills")
			.setSurfaceBuilder(new IvoryHillsSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.8F, 2.0F)
			.setPlateau()
			.setDecoOnly();

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

	public static final BTABiome savanna = new SavannaBiome(SAVANNA_ID, "betterbiomes:savanna", Climate.ARID)
			.setBiomeName("Savanna")
			.setSpawnsPumpkins()
			.setSpawnsVillages(false)
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.1F, 0.3F)
			.setLegacyCompatible();

	//Tropical
	public static final BTABiome lushDesert = new LushDesertBiome(LUSH_DESERT_ID, "betterbiomes:lush_desert", Climate.TROPICAL)
			.setBiomeName("Lush Desert")
			.setSpawnsVillages(true)
			.setSpawnsDesertTemples()
			.setDisableRain()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(0.1F, 0.6F)
			.setLegacyCompatible()
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
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

	public static final BTABiome volcanicJungle = new VolcanicJungle(VOLCANIC_JUNGLE_ID, "betterbiomes:volcanic_jungle", Climate.TROPICAL)
			.setBiomeName("Volcanic Jungle")
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.2F, 1.0F)
			.setSurfaceBuilder(new VolcanicJungleSurfaceBuilder())
			.setDecoOnly()
			.setNotSpawnable();

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

	public static final BTABiome bower = new BowerBiome(BOWER_ID, "betterbiomes:bower", Climate.COLD)
			.setBiomeName("Bower")
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.5F, 1.5F);

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
	
	public static final BTABiome hotSprings = new HotSpringsBiome(HOT_SPRINGS_ID, "betterbiomes:hot_springs", Climate.COLD)
			.setBiomeName("Hot Springs")
			.setSurfaceBuilder(new HotSpringsSurfaceBuilder(HOT_SPRINGS_ID, true))
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.8F, 1.2F)
			.setDecoOnly();

	public static final BTABiome mapleWoods = new MapleWoodsBiome(MAPLE_WOODS_ID, "betterbiomes:maple_woods", Climate.COLD)
			.setBiomeName("Maple Woods")
			.setTemperatureRainfall(0.4F, 0.6F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setDecoOnly();

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
			.setSurfaceBuilder(new ShieldSurfaceBuilder())
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.2F, 0.6F);

	//Snowy
	public static final BTABiome frozenSprings = new FrozenSpringsBiome(FROZEN_SPRINGS_ID, "betterbiomes:frozen_springs", Climate.SNOWY)
			.setBiomeName("Frozen Springs")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.1F, 0.1F)
			.setMinMaxHeight(0.2F, 0.6F)
			.setEnableSnow();

	public static final BTABiome iceMarsh = new IceMarshBiome(ICE_MARSH_ID, "betterbiomes:ice_marsh", Climate.SNOWY)
			.setBiomeName("Ice Marsh")
			.setSpawnsWitchHuts()
			.setSurfaceBuilder(new SwampSurfaceBuilder(false))
			.setTemperatureRainfall(0.1F, 0.3F)
			.setMinMaxHeight(-0.1F, 0.3F)
			.setEnableSnow();

	public static final BTABiome snowyConiferousForest = new ConiferousForestBiome(SNOWY_CONIFEROUS_FOREST_ID, "betterbiomes:snowy_coniferous_forest", Climate.SNOWY)
			.setBiomeName("Snowy Coniferous Forest")
			.setSurfaceBuilder(new ConiferousForestSurfaceBuilder())
			.setTemperatureRainfall(0.1F, 0.4F)
			.setMinMaxHeight(0.3F, 1.2F)
			.setEnableSnow()
			.setLegacyCompatible();

	public static final BTABiome snowyMapleWoods = new MapleWoodsBiome(SNOWY_MAPLE_WOODS_ID, "betterbiomes:snowy_maple_woods", Climate.SNOWY)
			.setBiomeName("Snowy Maple Woods")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.6F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setDecoOnly();

	public static final BTABiome snowyWoods = new WoodsBiome(SNOWY_WOODS_ID, "betterbiomes:snowy_woods", Climate.SNOWY)
			.setBiomeName("Snowy Woods")
			.setTemperatureRainfall(0.1F, 0.5F)
			.setMinMaxHeight(0.1F, 0.5F)
			.setEnableSnow()
			.setLegacyCompatible();

	// ------ Secondary Biomes ------ //
	//Variants
	public static final BTABiome badlands = new BadlandsBiome(BADLANDS_ID, "betterbiomes:badlands", Climate.ARID)
			.setBiomeName("Badlands")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
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

	public static final BTABiome cherryBlossomGroveHills = new CherryBlossomGroveBiome(CHERRY_BLOSSOM_GROVE_HILLS_ID, "betterbiomes:cherry_forest_hills", Climate.TEMPERATE)
			.setBiomeName("Cherry Blossom Grove Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);
	
	public static final BTABiome firCanyonValley = new FirCanyonBiome(FIR_CANYON_VALLEY_ID, "betterbiomes:fir_canyon_valley", Climate.ARID)
			.setBiomeName("Fir Canyon Valley")
			.setSurfaceBuilder(new FirCanyonSurfaceBuilder(false))
			.setDisableRain()
			.setTemperatureRainfall(1.0F, 0.1F)
			.setMinMaxHeight(0.1F, 0.7F)
			.setDecoOnly();
	
	public static final BTABiome floralPlateau = new FloralPlateauBiome(FLORAL_PLATEAU_ID, "betterbiomes:floral_plateau", Climate.ARID)
			.setBiomeName("Floral Plateau")
			.setTemperatureRainfall(0.9F, 0.8F)
			.setMinMaxHeight(0.8F, 2.0F)
			.setPlateau();

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

	public static final BTABiome iceMarshPond = new IceMarshBiome(ICE_MARSH_POND_ID, "betterbiomes:ice_marsh_pond", Climate.SNOWY)
			.setBiomeName("Ice Marsh Pond")
			.setSurfaceBuilder(new SwampSurfaceBuilder(false))
			.setSpawnsWitchHuts()
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.3F, 0.3F)
			.setMinMaxHeight(-0.3F, 0.0F);
	
	public static final BTABiome ivoryPlains = new SavannaBiome(IVORY_BEACH_ID, "betterbiomes:ivory_plains", Climate.ARID)
			.setBiomeName("Ivory Plains")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setTemperatureRainfall(1.5F, 0.1F)
			.setMinMaxHeight(0.1F, 0.3F)
			.setBeach();

	public static final BTABiome mangroveForestIsland = new MangroveForestBiome(MANGROVE_FOREST_ISLAND_ID, "betterbiomes:mangrove_forest_island", Climate.TEMPERATE)
			.setBiomeName("Mangrove Forest Island")
			.setSurfaceBuilder(new MangroveForestSurfaceBuilder())
			.setSpawnsSugarCane()
			.setSpawnsJungleTemples()
			.setMinMaxHeight(0.0F, 0.3F)
			.setTemperatureRainfall(0.8F, 0.9F)
			.setNotSpawnable();

	public static final BTABiome mapleWoodsHills = new MapleWoodsBiome(MAPLE_WOODS_HILLS_ID, "betterbiomes:maple_woods_hills", Climate.COLD)
			.setBiomeName("Maple Woods Hills")
			.setTemperatureRainfall(0.4F, 0.6F)
			.setMinMaxHeight(0.3F, 1.0F)
			.setDecoOnly();

	public static final BTABiome oasis = new OasisBiome(OASIS_ID, "betterbiomes:oasis", Climate.TROPICAL)
			.setBiomeName("Oasis")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setSpawnsVillages(true)
			.setDisableRain()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(-0.2F, 0.1F)
			.setNotSpawnable();

	public static final BTABiome oldGrowthWoodlandHills = new OldGrowthWoodlandBiome(OLD_GROWTH_WOODLAND_HILLS_ID, "betterbiomes:ancient_forest_hills", Climate.TEMPERATE)
			.setBiomeName("Old Growth Woodland Hills")
			.setTemperatureRainfall(0.7F, 0.8F)
			.setMinMaxHeight(0.3F, 1.0F);

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

	public static final BTABiome seasonalForestHills = new SeasonalForestBiome(SEASONAL_FOREST_HILLS_ID, "betterbiomes:autumn_forest_hills", Climate.TEMPERATE)
			.setBiomeName("Autumn Forest Hills")
			.setSpawnsPumpkins()
			.setTemperatureRainfall(0.9F, 0.2F)
			.setMinMaxHeight(0.3F, 1.0F)
			.setDecoOnly();

	public static final BTABiome snowyMapleWoodsHills = new MapleWoodsBiome(SNOWY_MAPLE_WOODS_HILLS_ID, "betterbiomes:snowy_maple_woods_hills", Climate.SNOWY)
			.setBiomeName("Snowy Maple Woods Hills")
			.setEnableSnow()
			.setTemperatureRainfall(0.1F, 0.6F)
			.setMinMaxHeight(0.3F, 1.0F)
			.setDecoOnly();

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

	//Rivers
	public static final BTABiome badlandsRiver = new BadlandsRiverBiome(BADLANDS_RIVER_ID, "betterbiomes:badlands_river")
			.setBiomeName("Badlands River")
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome darkForestRiver = new OrchardRiverBiome(DARK_FOREST_RIVER_BIOME, "betterbiomes:dark_forest_river")
			.setBiomeName("Dark Forest River")
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.6F, 0.9F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

	public static final BTABiome mangroveRiver = new MangroveRiverBiome(MANGROVE_RIVER_ID, "betterbiomes:mangrove_river")
			.setBiomeName("Mangrove River")
			.setSurfaceBuilder(new MangroveForestSurfaceBuilder())
			.setSpawnsSugarCane()
			.setTemperatureRainfall(0.9F, 1.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver();

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

	public static final BTABiome tropicsRiver = new TropicsRiverBiome(TROPICS_RIVER_ID, "betterbiomes:tropics_river")
			.setBiomeName("Tropics River")
			.setSpawnsSugarCane()
			.setSurfaceBuilder(new TropicsSurfaceBuilder())
			.setTemperatureRainfall(2.0F, 2.0F)
			.setMinMaxHeight(-0.5F, 0.0F)
			.setRiver()
			.setNotSpawnable();

	public static final BTABiome volcanicRiver = new TropicsRiverBiome(VOLCANIC_RIVER_ID, "betterbiomes:volcanic_river")
			.setBiomeName("Volcanic River")
			.setSpawnsSugarCane()
			.setSurfaceBuilder(new VolcanicBeachSurfaceBuilder())
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

	public static final BTABiome forestedHighlandsEdge = new ForestedHighlandsBiome(FORESTED_HIGHLANDS_EDGE_ID, "betterbiomes:forested_highlands_edge", Climate.TEMPERATE)
			.setBiomeName("Forested Highlands Edge")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.2F, 0.5F)
			.setMinMaxHeight(0.8F, 2.5F);

	public static final BTABiome highlandsEdge = new HighlandsBiome(HIGHLANDS_EDGE_ID, "betterbiomes:highlands_edge", Climate.TEMPERATE)
			.setBiomeName("Highlands Edge")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.2F, 0.5F)
			.setEdge();
	
	public static final BTABiome hotSpringsEdge = new HotSpringsBiome(HOT_SPRINGS_EDGE_ID, "betterbiomes:hot_springs_edge", Climate.COLD)
			.setBiomeName("Hot Springs Edge")
			.setSurfaceBuilder(new HotSpringsSurfaceBuilder(HOT_SPRINGS_EDGE_ID, false))
			.setTemperatureRainfall(0.5F, 0.4F)
			.setMinMaxHeight(0.8F, 1.2F)
			.setDecoOnly();

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
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
			.setDisableRain()
			.setTemperatureRainfall(2.0F, 0.0F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setBeach()
			.setNotSpawnable();
	
	public static final BTABiome volcanicBeach = new BeachBiome(VOLCANIC_BEACH_ID, "betterbiomes:volcanic_beach", Climate.TROPICAL)
			.setBiomeName("Volcanic Beach")
			.setSurfaceBuilder(new VolcanicBeachSurfaceBuilder())
			.setTemperatureRainfall(1.2F, 0.9F)
			.setMinMaxHeight(0.0F, 0.1F)
			.setBeach()
			.setNotSpawnable();

	// ------ Nether Biomes ------ //
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
			.setSurfaceBuilder(new NoShorelineSurfaceBuilder())
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

	public static final BTABiome highlandsEdgeOld = new HighlandsBiome(HIGHLANDS_EDGE_OLD_ID, "betterbiomes:highlands_edge_old", Climate.TEMPERATE)
			.setBiomeName("Highlands Edge")
			.setTemperatureRainfall(0.7F, 0.5F)
			.setMinMaxHeight(0.8F, 2.5F)
			.setEdge();

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
	
	private static ArrayList<BTABiome> betterBiomes = new ArrayList();
	
	private BetterBiomesConfiguration() {}

	public void addBiomesToList(ArrayList<BTABiome> biomeList) {
		betterBiomes.add(BTABiomeConfiguration.woods);
		betterBiomes.add(BTABiomeConfiguration.desert);
		betterBiomes.add(lushDesert);
		betterBiomes.add(savanna);
		betterBiomes.add(wetlands);
		betterBiomes.add(birchForest);
		betterBiomes.add(snowyWoods);
		betterBiomes.add(chaparral);
		betterBiomes.add(oldGrowthWoodland);
		betterBiomes.add(tropics);
		betterBiomes.add(BTABiomeConfiguration.jungle);
		betterBiomes.add(alpine);
		betterBiomes.add(fungalForest);
		betterBiomes.add(coniferousForest);
		betterBiomes.add(snowyConiferousForest);
		betterBiomes.add(mysticValley);
		betterBiomes.add(rainforest);
		betterBiomes.add(meadow);
		betterBiomes.add(BTABiomeConfiguration.mountains);
		betterBiomes.add(dunes);
		betterBiomes.add(heathland);
		betterBiomes.add(temperateForest);
		betterBiomes.add(valleyMountains);
		betterBiomes.add(BTABiomeConfiguration.tundra);
		betterBiomes.add(orchard);
		betterBiomes.add(steppe);
		
		betterBiomes.add(willowGrove);
		betterBiomes.add(BTABiomeConfiguration.icyPeaks);
		betterBiomes.add(patagonia);
		betterBiomes.add(grasslands);
		betterBiomes.add(BTABiomeConfiguration.siberia);
		betterBiomes.add(BTABiomeConfiguration.plains);
		betterBiomes.add(frozenSprings);
		betterBiomes.add(mangroveForest);
		betterBiomes.add(BTABiomeConfiguration.aridForest);
		betterBiomes.add(borealForest);
		betterBiomes.add(shield);
		betterBiomes.add(brushland);
		betterBiomes.add(highlands);
		betterBiomes.add(floralForest);
		betterBiomes.add(redwoodForest);
		betterBiomes.add(iceMarsh);
		betterBiomes.add(forestedHighlands);
		betterBiomes.add(bower);
		betterBiomes.add(field);

		betterBiomes.add(outback);
		betterBiomes.add(cherryBlossomGrove);
		betterBiomes.add(badlandsPlateau);
		betterBiomes.add(seasonalForest);
		betterBiomes.add(hotSprings);
		betterBiomes.add(volcanicJungle);
		betterBiomes.add(firCanyon);
		betterBiomes.add(ivoryHills);
		betterBiomes.add(darkForest);
		betterBiomes.add(mapleWoods);
		betterBiomes.add(snowyMapleWoods);
		
		biomeList.addAll(betterBiomes);
		
		//BTA vanilla biomes added in to keep backwards compatible order
		for (int i = 0; i < betterBiomes.size(); i++) {
			if (betterBiomes.get(i).getInternalName().startsWith("betterterrain")) {
				betterBiomes.remove(i);
				i--;
			}
		}

		HardcoreSpawnUtils.blacklistedBiomes.add(tropics);
		HardcoreSpawnUtils.blacklistedBiomes.add(tropicsRiver);
		HardcoreSpawnUtils.blacklistedBiomes.add(tropicsEdge);

		HardcoreSpawnUtils.blacklistedBiomes.add(rainforest);
		HardcoreSpawnUtils.blacklistedBiomes.add(rainforestRiver);
		HardcoreSpawnUtils.blacklistedBiomes.add(rainforestEdge);

		HardcoreSpawnUtils.blacklistedBiomes.add(volcanicJungle);
		HardcoreSpawnUtils.blacklistedBiomes.add(volcanicBeach);
	}
	
	public void setBiomeVariants() {
		//Sub biomes
		WorldConfigurationInfo.Condition pre140 = info -> info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4);
		
		alpine.addSubVariant(aspenGrove, pre140);
		oldGrowthWoodland.addSubVariant(oldGrowthWoodlandHills);
		seasonalForest.addSubVariant(seasonalForestHills);
		badlandsPlateau.addSubVariant(badlands);
		birchForest.addSubVariant(birchForestHills);
		borealForest.addSubVariant(borealForestHills);
		chaparral.addSubVariant(chaparralHills);
		cherryBlossomGrove.addSubVariant(cherryBlossomGroveHills);
		coniferousForest.addSubVariant(coniferousForestClearing, pre140);
		firCanyon.addSubVariant(firCanyonValley);
		fungalForest.addSubVariant(fungalForestFlat);
		grasslands.addSubVariant(grasslandsLake);
		heathland.addSubVariant(heathlandWoods, pre140);
		ivoryHills.addSubVariant(ivoryPlains);
		mapleWoods.addSubVariant(mapleWoodsHills);
		orchard.addSubVariant(orchardClearing, pre140);
		patagonia.addSubVariant(patagoniaMountains);
		savanna.addSubVariant(savannaHills, info -> info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V2_0_2));
		savanna.addSubVariant(savannaPlateau, info -> info.getBTAVersion().isVersionAtLeast(BTAVersion.V2_0_3));
		snowyConiferousForest.addSubVariant(snowyConiferousForestClearing, pre140);
		snowyMapleWoods.addSubVariant(snowyMapleWoodsHills);
		snowyWoods.addSubVariant(snowyWoodsHills);
		steppe.addSubVariant(woodedSteppe, pre140);
		valleyMountains.addSubVariant(valley);
		
		//Sub biomes (Common)
		lushDesert.addSubVariantCommon(oasis);
		floralForest.addSubVariantCommon(floralPlateau);
		wetlands.addSubVariantCommon(wetlandsHills);
		willowGrove.addSubVariantCommon(willowHills);
		
		//Sporadic
		frozenSprings.addSporadicVariant(frozenSpringsPond);
		frozenSprings.addSporadicChance(3);

		iceMarsh.addSporadicVariant(iceMarshPond);
		iceMarsh.addSporadicChance(3);
		
		wetlands.addSporadicVariant(wetlandsRiver);
		wetlandsHills.addSporadicVariant(wetlandsRiver);
		wetlands.addSporadicChance(5);
		wetlandsHills.addSporadicChance(5);
		
		willowGrove.addSporadicVariant(willowRiver);
		willowHills.addSporadicVariant(willowRiver);
		willowGrove.addSporadicChance(5);
		willowHills.addSporadicChance(5);
		
		rainforest.addSporadicVariant(rainforestRiver);
		rainforest.addSporadicChance(8);
		
		lushDesert.addSporadicVariant(oasis);
		lushDesert.addSporadicChance(8);
		
		mangroveForest.addSporadicVariant(mangroveForestIsland);
		mangroveForest.addSporadicChance(3);
		
		//Beaches
		WorldConfigurationInfo.Condition pre132 = info -> info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_1);
		
		alpine.setHasBeach(false);
		alpineEdge.setHasBeach(false);
		fungalForest.setHasBeach(false);
		fungalForestFlat.setHasBeach(false);
		coniferousForest.setHasBeach(false);
		snowyConiferousForest.setHasBeach(false);
		mysticValley.setHasBeach(false);
		temperateForest.setHasBeach(false);
		oldValley.setHasBeach(false);
		valleyMountains.setHasBeach(false);
		snowyWoods.setHasBeach(false, pre132);
		snowyWoodsHills.setHasBeach(false);
		snowyMapleWoodsHills.setHasBeach(false);
		badlandsPlateau.setHasBeach(false);
		rainforest.setHasBeach(false);
		tropics.setHasBeach(false);
		patagoniaMountains.setHasBeach(false);
		frozenSprings.setHasBeach(false, pre132);
		mangroveForest.setHasBeach(false);
		mangroveForestIsland.setHasBeach(false);
		highlands.setHasBeach(false);
		highlandsEdge.setHasBeach(false);
		iceMarsh.setHasBeach(false);
		iceMarshPond.setHasBeach(false);
		forestedHighlands.setHasBeach(false);
		forestedHighlandsEdge.setHasBeach(false);
		bower.setHasBeach(false);
		
		outback.addBeachVariant(redSandBeach);
		badlands.addBeachVariant(redSandBeach);
		firCanyon.addBeachVariant(redSandBeach);
		firCanyonValley.addBeachVariant(redSandBeach);
		
		ivoryHills.addBeachVariant(ivoryPlains);
		
		volcanicJungle.addBeachVariant(volcanicBeach);

		WorldConfigurationInfo.Condition post132 = info -> info.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_2);

		mapleWoods.addBeachVariant(BTABiomeConfiguration.frozenBeach);
		snowyWoods.addBeachVariant(BTABiomeConfiguration.frozenBeach, post132);
		frozenSprings.addBeachVariant(BTABiomeConfiguration.frozenBeach, post132);
		
		//Rivers
		dunes.addRiverVariant(BTABiomeConfiguration.desertRiver);

		mysticValley.addRiverVariant(mysticRiver);

		rainforest.addRiverVariant(rainforestRiver);
		rainforestEdge.addRiverVariant(rainforestRiver);
		
		outback.addRiverVariant(outbackRiver);
		
		badlands.addRiverVariant(badlandsRiver);
		badlandsPlateau.addRiverVariant(badlandsRiver);
		badlandsEdge.addRiverVariant(badlandsRiver);
		firCanyon.addRiverVariant(badlandsRiver);
		firCanyonValley.addRiverVariant(badlandsRiver);
		
		tropics.addRiverVariant(tropicsRiver);
		tropicsEdge.addRiverVariant(tropicsRiver);

		mangroveForest.addRiverVariant(mangroveRiver);
		mangroveForestIsland.addRiverVariant(mangroveRiver);
		
		orchard.addRiverVariant(orchardRiver);
		orchardClearing.addRiverVariant(orchardRiver);
		
		wetlands.addRiverVariant(wetlandsRiver);
		wetlandsHills.addRiverVariant(wetlandsRiver);
		
		willowGrove.addRiverVariant(willowRiver);
		willowHills.addRiverVariant(willowRiver);
		
		patagonia.addRiverVariant(patagoniaRiver);
		patagoniaMountains.addRiverVariant(patagoniaRiver);
		
		volcanicJungle.addRiverVariant(volcanicRiver);
		volcanicBeach.addRiverVariant(volcanicRiver);

		darkForest.addRiverVariant(darkForestRiver);
		
		//Edges
		alpine.addEdgeVariant(alpineEdge);
		valleyMountains.addEdgeVariant(valley);
		dunes.addEdgeVariant(BTABiomeConfiguration.desert);
		badlandsPlateau.addEdgeVariant(badlands, info -> info.getBTAVersion().isVersionAtLeast(BTAVersion.V3_0_0));
		badlands.addEdgeVariant(badlandsEdge, pre140);
		badlandsPlateau.addEdgeVariant(badlandsEdge, pre140);
		firCanyon.addEdgeVariant(firCanyonValley);
		forestedHighlands.addEdgeVariant(forestedHighlandsEdge);
		highlands.addEdgeVariant(highlandsEdgeOld, info -> info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V2_0_8));
		highlands.addEdgeVariant(highlandsEdge, info -> info.getBTAVersion().isVersionAtLeast(BTAVersion.V3_0_0));
		//hotSprings.addEdgeVariant(hotSpringsEdge);
		rainforest.addEdgeVariant(rainforestEdge, post132);
		tropics.addEdgeVariant(tropicsEdge, post132);
	}
	
	private static BetterBiomesConfiguration instance;
	
	public static BetterBiomesConfiguration getInstance() {
		if (instance == null) {
			instance = new BetterBiomesConfiguration();
		}
		
		return instance;
	}
}