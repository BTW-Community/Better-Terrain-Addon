package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BTABiomeConfiguration {
	public static final int
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
			BOREAL_FOREST_HILLS = 170,
			
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
	public static final BTABiomeGenBase woods = new BTABiomeGenWoods(WOODS_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Woods").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase steppe = new BTABiomeGenSteppe(STEPPE_ID, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTABiomeGenBase ancientForest = new BTABiomeGenAncientForest(ANCIENT_FOREST_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Ancient Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase mysticForest = new BTABiomeGenMysticForest(MYSTIC_FOREST_ID, BTAEnumClimate.TEMPERATE).setColor(10039961).setBiomeName("Mystic Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase orchard = new BTABiomeGenOrchard(ORCHARD_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Orchard").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.5F);
	public static final BTABiomeGenBase heathland = new BTABiomeGenHeathland(HEATHLAND_ID, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Heathland").setTemperatureRainfall(0.7F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase temperateForest = new BTABiomeGenTemperateForest(TEMPERATE_FOREST_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Temperate Forest").func_76733_a(5159473).setMinMaxHeight(0.2F, 1.0F).setTemperatureRainfall(0.6F, 0.7F);
	public static final BTABiomeGenBase grasslands = new BTABiomeGenGrasslands(GRASSLANDS_ID, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Grasslands").setTemperatureRainfall(0.5F, 0.6F).setMinMaxHeight(0.2F, 0.3F);
    public static final BTABiomeGenBase mangroveForest = new BTABiomeGenMangroveForest(MANGROVE_FOREST_ID, BTAEnumClimate.TEMPERATE).setColor(522674).setBiomeName("Mangal").func_76733_a(9154376).setMinMaxHeight(-0.3F, 0.2F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase highlands = new BTABiomeGenHighlands(HIGHLANDS_ID, BTAEnumClimate.TEMPERATE).setColor(14090235).setBiomeName("Highlands").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(0.8F, 2.5F);
	public static final BTABiomeGenBase cherryForest = new BTABiomeGenCherryForest(CHERRY_FOREST_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Cherry Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase autumnForest = new BTABiomeGenAutumnForest(AUTUMN_FOREST_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Autumn Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.2F).setMinMaxHeight(0.1F, 0.5F);
	
	//Arid
	public static final BTABiomeGenBase desert = new BTABiomeGenDesert(DESERT_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Better Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.2F, 0.4F);
	public static final BTABiomeGenBase savanna = new BTABiomeGenSavanna(SAVANNA_ID, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.3F);
	public static final BTABiomeGenBase chaparral = new BTABiomeGenChaparral(CHAPPARAL_ID, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTABiomeGenBase dunes = new BTABiomeGenDunes(DUNES_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase plains = new BTABiomeGenPlains(PLAINS_ID, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Better Plains").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.4F);
	public static final BTABiomeGenBase aridForest = new BTABiomeGenAridForest(ARID_FOREST_ID, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Arid Forest").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.4F);
    public static final BTABiomeGenBase outback = new BTABiomeGenOutback(OUTBACK_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Outback").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase badlandsPlateau = new BTABiomeGenBadlandsPlateau(BADLANDS_PLATEAU_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Badlands Plateau").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.8F, 2.0F);
	
	//Tropical
	public static final BTABiomeGenBase lushDesert = new BTABiomeGenLushDesert(LUSH_DESERT_ID, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Lush Desert").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.1F, 0.6F);
    public static final BTABiomeGenBase wetlands = new BTABiomeGenWetlands(WETLANDS_ID, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Wetlands").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase tropics = new BTABiomeGenTropics(TROPICS_ID, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Tropics").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.2F, 0.9F);
    public static final BTABiomeGenBase jungle = new BTABiomeGenJungle(JUNGLE_ID, BTAEnumClimate.TROPICAL).setColor(5470985).setBiomeName("Better Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.0F, 0.4F);
	public static final BTABiomeGenBase rainforest = new BTABiomeGenRainforest(RAINFOREST_ID, BTAEnumClimate.TROPICAL).setColor(9286496).setBiomeName("Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase meadow = new BTABiomeGenMeadow(MEADOW_ID, BTAEnumClimate.TROPICAL).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 1.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase valleyMountains = new BTABiomeGenValley(VALLEY_MOUNTAINS_ID, BTAEnumClimate.TROPICAL).setColor(353825).setBiomeName("Valley Highlands").func_76733_a(5159473).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.5F, 3.0F);
	public static final BTABiomeGenBase willowGrove = new BTABiomeGenWillowGrove(WILLOW_GROVE_ID, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Willow Grove").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.8F);
    
	//Cold
	public static final BTABiomeGenBase birchForest = new BTABiomeGenBirchForest(BIRCH_FOREST_ID, BTAEnumClimate.COLD).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
    public static final BTABiomeGenBase alpine = new BTABiomeGenAlpine(ALPINE_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Alpine").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.5F, 3.0F);
    public static final BTABiomeGenBase fungalForest = new BTABiomeGenFungalForest(FUNGAL_FOREST_ID, BTAEnumClimate.COLD).setColor(522674).setBiomeName("Fungal Forest").func_76733_a(9154376).setMinMaxHeight(-0.1F, 1.2F).setTemperatureRainfall(0.4F, 1.0F);
	public static final BTABiomeGenBase coniferousForest = new BTABiomeGenConiferousForest(CONIFEROUS_FOREST_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase mountains = new BTABiomeGenMountain(MOUNTAINS_ID, BTAEnumClimate.COLD).setColor(14090235).setBiomeName("Mountains").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.8F, 2.5F);
	public static final BTABiomeGenBase patagonia = new BTABiomeGenPatagonia(PATAGONIA_ID, BTAEnumClimate.COLD).setColor(9286496).setBiomeName("Patagonia").setTemperatureRainfall(0.3F, 0.6F).setMinMaxHeight(0.0F, 0.5F);
	public static final BTABiomeGenBase borealForest = new BTABiomeGenBorealForest(BOREAL_FOREST_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Boreal Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase shield = new BTABiomeGenShield(SHIELD_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Shield").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.2F, 0.6F);
	public static final BTABiomeGenBase brushland = new BTABiomeGenBrushland(BRUSHLAND_ID, BTAEnumClimate.COLD).setColor(9286496).setBiomeName("Brushland").setTemperatureRainfall(0.4F, 0.2F).setMinMaxHeight(0.3F, 0.5F);
	
	//Snowy
	public static final BTABiomeGenBase snowyWoods = new BTABiomeGenWoods(SNOWY_WOODS_ID, BTAEnumClimate.SNOWY).setColor(353825).setBiomeName("Snowy Woods").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase coniferousForestSnow = new BTABiomeGenConiferousForest(SNOWY_CONIFEROUS_FOREST_ID, BTAEnumClimate.SNOWY).setColor(747097).setBiomeName("Snowy Coniferous Forest").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase tundra = new BTABiomeGenTundra(TUNDRA_ID, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase icyPeaks = new BTABiomeGenIcyPeaks(ICY_PEAKS_EDGE_ID, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Icy Peaks").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase siberia = new BTABiomeGenSiberia(SIBERIA_ID, BTAEnumClimate.SNOWY).setColor(747097).setBiomeName("Siberia").func_76733_a(5159473).setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 0.7F).setEnableSnow();
	public static final BTABiomeGenBase frozenSprings = new BTABiomeGenFrozenSprings(FROZEN_SPRINGS_ID, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Frozen Springs").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.2F, 0.6F);
	
	//Nether
	/*
	 * Nether waste
	 * Ash field
	 * Basalt delta
	 * Soul sand valley
	 * Obsidian grove
	 * Crystal cavern
	 */

	// ------ Secondary Biomes ------ //
	//Variants
	public static final BTABiomeGenBase woodsHills = new BTABiomeGenWoods(WOODS_HILLS_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Woods Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase desertHills = new BTABiomeGenDesert(DESERT_HILLS_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Desert Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase savannaHills = new BTABiomeGenSavanna(SAVANNA_HILLS_ID, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Savanna Hills").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 0.8F);
	public static final BTABiomeGenBase birchForestHills = new BTABiomeGenBirchForest(BIRCH_FOREST_HILLS_ID, BTAEnumClimate.COLD).setColor(353825).setBiomeName("Birch Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase snowyWoodsHills = new BTABiomeGenWoods(SNOWY_WOODS_HILLS, BTAEnumClimate.SNOWY).setColor(353825).setBiomeName("Snowy Woods Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase chaparralHills = new BTABiomeGenChaparral(CHAPPARAL_HILLS_ID, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Chaparral Hills").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase ancientForestHills = new BTABiomeGenAncientForest(ANCIENT_FOREST_HILLS_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Ancient Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
    public static final BTABiomeGenBase jungleHills = new BTABiomeGenJungle(JUNGLE_HILLS_ID, BTAEnumClimate.TROPICAL).setColor(2900485).setBiomeName("Jungle Hills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.5F);
    public static final BTABiomeGenBase fungalForestFlat = new BTABiomeGenFungalForest(FUNGAL_FOREST_FLAT_ID, BTAEnumClimate.COLD).setColor(522674).setBiomeName("Fungal Forest Lowlands").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.5F).setTemperatureRainfall(0.4F, 1.0F);
    public static final BTABiomeGenBase wetlandsHills = new BTABiomeGenWetlands(WETLANDS_HILLS_ID, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Wetlands Hills").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.6F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase cherryForestHills = new BTABiomeGenCherryForest(CHERRY_FOREST_HILLS_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Cherry Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase autumnForestHills = new BTABiomeGenAutumnForest(AUTUMN_FOREST_HILLS_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Autumn Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.2F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase valley = new BTABiomeGenValley(VALLEY_ID, BTAEnumClimate.TROPICAL).setColor(353825).setBiomeName("Valley").func_76733_a(5159473).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.1F, 0.6F);
	public static final BTABiomeGenBase willowHills = new BTABiomeGenWillowGrove(WILLOW_HILLS_ID, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Willow Hills").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.6F).setTemperatureRainfall(0.8F, 0.8F);
	public static final BTABiomeGenBase patagoniaMountains = new BTABiomeGenPatagoniaMountains(PATAGONIA_MOUNTAINS_ID, BTAEnumClimate.COLD).setColor(9286496).setBiomeName("Patagonia Mountains").setTemperatureRainfall(0.1F, 0.6F).setMinMaxHeight(2.0F, 4.0F).setEnableSnow();
	public static final BTABiomeGenBase grasslandsLake = new BTABiomeGenGrasslandsLake(GRASSLANDS_LAKE_ID, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Grasslands Lake").setTemperatureRainfall(0.5F, 0.6F).setMinMaxHeight(-0.3F, 0.0F);
	public static final BTABiomeGenBase frozenSpringsPond = new BTABiomeGenFrozenSpringPond(FROZEN_SPRINGS_POND_ID, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Frozen Springs Pond").setTemperatureRainfall(0.2F, 0.1F).setMinMaxHeight(-0.3F, 0.0F);
    public static final BTABiomeGenBase mangroveForestIsland = new BTABiomeGenMangroveForest(MANGROVE_FOREST_ISLAND_ID, BTAEnumClimate.TEMPERATE).setColor(522674).setBiomeName("Mangal Island").func_76733_a(9154376).setMinMaxHeight(0.0F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase borealForestHills = new BTABiomeGenBorealForest(BOREAL_FOREST_HILLS, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Boreal Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase badlands = new BTABiomeGenBadlands(BADLANDS_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Badlands").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
	public static final BTABiomeGenBase oasis = new BTABiomeGenOasis(OASIS_ID, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Oasis").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.2F, 0.1F);
	
	//Rivers
	public static final BTABiomeGenBase riverDesert = new BTABiomeGenRiverDesert(DESERT_RIVER_ID).setColor(255).setBiomeName("Desert River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverMystic = new BTABiomeGenRiverMystic(MYSTIC_RIVER_ID).setColor(255).setBiomeName("Mystic River").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverRainforest = new BTABiomeGenRiverRainforest(RAINFOREST_RIVER_ID).setColor(255).setBiomeName("Rainforest River").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverOutback = new BTABiomeGenRiverOutback(OUTBACK_RIVER_ID).setColor(255).setBiomeName("Outback River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverBadlands = new BTABiomeGenRiverBadlands(BADLANDS_RIVER_ID).setColor(255).setBiomeName("Badlands River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverTropics = new BTABiomeGenRiverTropics(TROPICS_RIVER_ID).setColor(255).setBiomeName("Tropics River").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverOrchard = new BTABiomeGenRiverOrchard(ORCHARD_RIVER_ID).setColor(255).setBiomeName("Orchard River").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverJungle = new BTABiomeGenRiverJungle(JUNGLE_RIVER_ID).setColor(5470985).setBiomeName("Jungle River").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverWetlands = new BTABiomeGenRiverWetlands(WETLANDS_RIVER_ID).setColor(522674).setBiomeName("Wetlands River").func_76733_a(9154376).setTemperatureRainfall(0.8F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverWillow = new BTABiomeGenRiverWillow(WILLOW_GROVE_RIVER_ID).setColor(522674).setBiomeName("Willow Grove River").func_76733_a(9154376).setTemperatureRainfall(0.6F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverPatagonia = new BTABiomeGenRiverPatagonia(PATAGONIA_RIVER_ID).setColor(9286496).setBiomeName("Patagonia River").setTemperatureRainfall(0.3F, 0.6F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase river = new BTABiomeGenRiver(RIVER_ID, BTAEnumClimate.TEMPERATE).setColor(255).setBiomeName("Better River").setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverFrozen = new BTABiomeGenRiver(FROZEN_RIVER_ID, BTAEnumClimate.SNOWY).setColor(255).setBiomeName("Better Frozen River").setEnableSnow().setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F);
    
    //Edges
    public static final BTABiomeGenBase alpineEdge = new BTABiomeGenAlpine(ALPINE_EDGE_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Alpine Edge").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.2F, 0.5F);
    public static final BTABiomeGenBase mountainEdge = new BTABiomeGenMountain(MOUNTAIN_EDGE_ID, BTAEnumClimate.COLD).setColor(14090235).setBiomeName("Mountains Edge").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.2F, 0.5F);
	public static final BTABiomeGenBase icyPeaksEdge = new BTABiomeGenIcyPeaks(ICY_PEAKS_EDGE_ID, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Icy Peaks Edge").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase highlandsEdge = new BTABiomeGenHighlands(HIGHLANDS_EDGE_ID, BTAEnumClimate.TEMPERATE).setColor(14090235).setBiomeName("Highlands Edge").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(0.8F, 2.5F);
    public static final BTABiomeGenBase jungleEdge = new BTABiomeGenJungleEdge(JUNGLE_EDGE_ID, BTAEnumClimate.TROPICAL).setColor(5470985).setBiomeName("Better Jungle Edge").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.0F, 0.4F);
	public static final BTABiomeGenBase rainforestEdge = new BTABiomeGenRainforestEdge(RAINFOREST_EDGE_ID, BTAEnumClimate.TROPICAL).setColor(9286496).setBiomeName("Rainforest Edge").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase tropicsEdge = new BTABiomeGenTropics(TROPICS_EDGE_ID, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Tropics Edge").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.3F, 0.1F);
	
	//Beaches
    public static final BTABiomeGenBase beachOutback = new BTABiomeGenBeachOutback(RED_SAND_BEACH_ID, BTAEnumClimate.ARID).setColor(16440917).setBiomeName("Red Sand Beach").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.1F);
    public static final BTABiomeGenBase beach = new BTABiomeGenBeach(BEACH_ID, BTAEnumClimate.TEMPERATE).setColor(16440917).setBiomeName("Better Beach").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
    public static final BTABiomeGenBase beachFrozen = new BTABiomeGenBeach(FROZEN_BEACH_ID, BTAEnumClimate.SNOWY).setColor(16440917).setBiomeName("Frozen Beach").setTemperatureRainfall(0.0F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
    
    // ------ Nether Biomes ------ //
    
    // ------ Deprecated ------ //
	public static final BTABiomeGenBase orchardClearing = new BTABiomeGenOrchardClearing(ORCHARD_CLEARING_ID, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Orchard Clearing").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.5F);
	public static final BTABiomeGenBase icyPeaksForested = new BTABiomeGenIcyPeaksForested(ICY_PEAKS_FORESTED_ID, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Forested Icy Peaks").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase coniferousForestClearing = new BTABiomeGenConiferousForestClearing(CONIFEROUS_FOREST_CLEARING_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Coniferous Forest Clearing").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase coniferousForestClearingSnow = new BTABiomeGenConiferousForestClearing(SNOWY_CONIFEROUS_FOREST_CLEARING_ID, BTAEnumClimate.SNOWY).setColor(747097).setBiomeName("Snowy Coniferous Forest Clearing").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
    public static final BTABiomeGenBase aspenGrove = new BTABiomeGenAspenGrove(ASPEN_GROVE_ID, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Aspen Grove").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.5F, 3.0F);
	public static final BTABiomeGenBase heathlandWoods = new BTABiomeGenHeathlandWoods(HEATHLAND_WOODS_ID, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Heathland Woods").setTemperatureRainfall(0.7F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase woodedSteppe = new BTABiomeGenWoodedSteppe(WOODED_STEPPE_ID, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Wooded Steppe").setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTABiomeGenBase badlandsEdge = new BTABiomeGenBadlands(BADLANDS_EDGE_ID, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Badlands Edge").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
	public static final BTABiomeGenBase oldValley = new BTABiomeGenOldValley(OLD_VALLEY_ID, BTAEnumClimate.TROPICAL).setColor(353825).setBiomeName("Old Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.8F);
	
    //Various biome lists
	public static ArrayList<BTABiomeGenBase> biomeList = new ArrayList();
	public static ArrayList<BTABiomeGenBase> biomeListDeco = new ArrayList();
	public static ArrayList<BTABiomeGenBase> biomeListCompat = new ArrayList();
	public static ArrayList<BTABiomeGenBase> biomeListDecoCompat = new ArrayList();

	public static ArrayList<BTABiomeGenBase> snowyList = new ArrayList();
	public static ArrayList<BTABiomeGenBase> coldList = new ArrayList();
	public static ArrayList<BTABiomeGenBase> temperateList = new ArrayList();
	public static ArrayList<BTABiomeGenBase> tropicalList = new ArrayList();
	public static ArrayList<BTABiomeGenBase> aridList = new ArrayList();
	public static Map<BTAEnumClimate, ArrayList<BTABiomeGenBase>> biomeCategoryMap = new HashMap();
	
	public static Map<Integer, BTABiomeInfo> biomeInfoMap = new HashMap();

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
		for (BTABiomeGenBase b : biomeListDecoCompat) {
			if (!biomeListDeco.contains(b)) {
				biomeListDeco.add(b);
			}
		}
	}
	
	public static void addExternalBiome(BTABiomeGenBase biome, boolean decoOnly) {
		biomeListDeco.add(biome);
		
		if (!decoOnly)
			biomeList.add(biome);
		
		biomeCategoryMap.get(biome.climate).add(biome);
		biomeInfoMap.put(biome.biomeID, new BTABiomeInfo(biome.biomeID, true, decoOnly));
	}
	
	public static void initClimateLists() {
		biomeCategoryMap.put(BTAEnumClimate.SNOWY, snowyList);
		biomeCategoryMap.put(BTAEnumClimate.COLD, coldList);
		biomeCategoryMap.put(BTAEnumClimate.TEMPERATE, temperateList);
		biomeCategoryMap.put(BTAEnumClimate.TROPICAL, tropicalList);
		biomeCategoryMap.put(BTAEnumClimate.ARID, aridList);
		
		for (BTABiomeGenBase b : biomeListDeco) {
			biomeCategoryMap.get(b.climate).add(b);
		}
	}
	
	public static void initBiomeInfoList() {
		for (BTABiomeGenBase b : biomeListDeco) {
			if (biomeList.contains(b))
				biomeInfoMap.put(b.biomeID, new BTABiomeInfo(b.biomeID, true));
			else
				biomeInfoMap.put(b.biomeID, new BTABiomeInfo(b.biomeID, true, true));
		}
	}
	
	public static void initSurfaceBuilders() {
		patagonia.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		patagoniaMountains.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		willowGrove.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		wetlands.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		oasis.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		
		valley.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		valleyMountains.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		tropics.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		riverTropics.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		
		badlands.setSurfaceBuilder(new BTASurfaceBuilderBadlands());
		badlandsEdge.setSurfaceBuilder(new BTASurfaceBuilderBadlands());
		riverBadlands.setSurfaceBuilder(new BTASurfaceBuilderBadlands());
		badlandsPlateau.setSurfaceBuilder(new BTASurfaceBuilderBadlandsPlateau());
		beachOutback.setSurfaceBuilder(new BTASurfaceBuilderBadlands());
		
		outback.setSurfaceBuilder(new BTASurfaceBuilderOutback());
		riverOutback.setSurfaceBuilder(new BTASurfaceBuilderOutback());
		
		alpine.setSurfaceBuilder(new BTASurfaceBuilderAlpine());
		alpineEdge.setSurfaceBuilder(new BTASurfaceBuilderAlpine());
		
		heathland.setSurfaceBuilder(new BTASurfaceBuilderHeathland());
		
		icyPeaks.setSurfaceBuilder(new BTASurfaceBuilderIcyPeaks());
		icyPeaksEdge.setSurfaceBuilder(new BTASurfaceBuilderIcyPeaks());
		
		steppe.setSurfaceBuilder(new BTASurfaceBuilderSteppe());
		
		coniferousForest.setSurfaceBuilder(new BTASurfaceBuilderConiferousForest());
		coniferousForestSnow.setSurfaceBuilder(new BTASurfaceBuilderConiferousForest());
		
		orchard.setSurfaceBuilder(new BTASurfaceBuilderOrchard());
		
		chaparral.setSurfaceBuilder(new BTASurfaceBuilderStony());
		chaparralHills.setSurfaceBuilder(new BTASurfaceBuilderStony());
		aridForest.setSurfaceBuilder(new BTASurfaceBuilderStony());
		shield.setSurfaceBuilder(new BTASurfaceBuilderStony());
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
		pumpkinBiomes.add(chaparral);
		pumpkinBiomes.add(meadow);
		pumpkinBiomes.add(heathland);
		pumpkinBiomes.add(orchard);
		pumpkinBiomes.add(patagonia);
		pumpkinBiomes.add(grasslands);
		pumpkinBiomes.add(plains);
		pumpkinBiomes.add(brushland);
		
		reedBiomes.add(wetlands);
		reedBiomes.add(tropics);
		reedBiomes.add(jungle);
		reedBiomes.add(fungalForest);
		reedBiomes.add(mysticForest);
		reedBiomes.add(rainforest);
		reedBiomes.add(willowGrove);
		reedBiomes.add(patagonia);
		reedBiomes.add(plains);
		reedBiomes.add(mangroveForest);
		reedBiomes.add(temperateForest);
		reedBiomes.add(frozenSprings);
		
		villageBiomes.add(savanna);
		villageBiomes.add(desert);
		villageBiomes.add(lushDesert);
		villageBiomes.add(chaparral);
		villageBiomes.add(meadow);
		villageBiomes.add(heathland);
		villageBiomes.add(steppe);
		villageBiomes.add(orchard);
		villageBiomes.add(outback);
		villageBiomes.add(patagonia);
		villageBiomes.add(grasslands);
		villageBiomes.add(plains);
		villageBiomes.add(brushland);
		
		MapGenVillage.villageSpawnBiomes.addAll(villageBiomes);
		
		jungleTempleBiomes.add(tropics);
		jungleTempleBiomes.add(jungle);
		jungleTempleBiomes.add(rainforest);
		jungleTempleBiomes.add(mangroveForest);
		
		desertTempleBiomes.add(desert);
		desertTempleBiomes.add(lushDesert);
		desertTempleBiomes.add(dunes);
		desertTempleBiomes.add(steppe);
		
		redDesertTempleBiomes.add(outback);
		redDesertTempleBiomes.add(badlands);
		
		witchHutBiomes.add(wetlands);
		witchHutBiomes.add(fungalForest);
		witchHutBiomes.add(mysticForest);
		witchHutBiomes.add(willowGrove);
		
		BTAMapGenScatteredFeature.biomelist.addAll(jungleTempleBiomes);
		BTAMapGenScatteredFeature.biomelist.addAll(desertTempleBiomes);
		BTAMapGenScatteredFeature.biomelist.addAll(witchHutBiomes);
		
		//Hill variants
		pumpkinBiomes.add(savannaHills);
		pumpkinBiomes.add(chaparralHills);
		pumpkinBiomes.add(heathlandWoods);
		pumpkinBiomes.add(orchardClearing);
		
		reedBiomes.add(jungleHills);
		reedBiomes.add(fungalForestFlat);
		reedBiomes.add(wetlandsHills);
		reedBiomes.add(willowHills);
		reedBiomes.add(frozenSpringsPond);
		reedBiomes.add(mangroveForestIsland);

		villageBiomes.add(oasis);
		villageBiomes.add(woodedSteppe);
		villageBiomes.add(heathlandWoods);
		villageBiomes.add(orchardClearing);

		jungleTempleBiomes.add(jungleHills);
		jungleTempleBiomes.add(mangroveForestIsland);

		desertTempleBiomes.add(desertHills);
		desertTempleBiomes.add(woodedSteppe);

		witchHutBiomes.add(fungalForestFlat);
		witchHutBiomes.add(wetlandsHills);
		witchHutBiomes.add(willowHills);
		
		//Edges
		reedBiomes.add(jungleEdge);
		reedBiomes.add(rainforestEdge);
		
		jungleTempleBiomes.add(jungleEdge);
		jungleTempleBiomes.add(rainforestEdge);

		redDesertTempleBiomes.add(badlandsEdge);
		
		//Rivers
		reedBiomes.add(riverRainforest);
		reedBiomes.add(riverTropics);
		reedBiomes.add(riverJungle);
		reedBiomes.add(riverMystic);
		reedBiomes.add(riverWetlands);
		reedBiomes.add(riverWillow);
	}
	
	public static int getHillsVariantForBiomes(int baseBiome, BTAWorldConfigurationInfo generatorOptions) {
		int hillsBiome = baseBiome;
		
		if (baseBiome == woods.biomeID){
            hillsBiome = woodsHills.biomeID;
        }
        else if (baseBiome == desert.biomeID){
            hillsBiome = desertHills.biomeID;
        }
        else if (baseBiome == savanna.biomeID){
            hillsBiome = savannaHills.biomeID;
        }
        else if (baseBiome == birchForest.biomeID) {
        	hillsBiome = birchForestHills.biomeID;
        }
        else if (baseBiome == snowyWoods.biomeID) {
        	hillsBiome = snowyWoodsHills.biomeID; 
        }
        else if (baseBiome == steppe.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
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
        else if (baseBiome == alpine.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
        	hillsBiome = aspenGrove.biomeID;
        }
        else if (baseBiome == coniferousForest.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
        	hillsBiome = coniferousForestClearing.biomeID;
        }
        else if (baseBiome == coniferousForestSnow.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
        	hillsBiome = coniferousForestClearingSnow.biomeID;
        }
        else if (baseBiome == heathland.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
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
        else if (baseBiome == orchard.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
        	hillsBiome = orchardClearing.biomeID;
        }
        else if (baseBiome == fungalForest.biomeID) {
        	hillsBiome = fungalForestFlat.biomeID;
        }
        else if (baseBiome == badlandsPlateau.biomeID){
            hillsBiome = badlands.biomeID;
        }
        else if (baseBiome == icyPeaks.biomeID && generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
        	hillsBiome = icyPeaksForested.biomeID;
        }
        else if (baseBiome == grasslands.biomeID) {
        	hillsBiome = grasslandsLake.biomeID;
        }
        else if (baseBiome == patagonia.biomeID) {
        	hillsBiome = patagoniaMountains.biomeID;
        }
        else if (baseBiome == plains.biomeID) {
        	if (generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_2_1))
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
		
		if (BiomeGenBase.biomeList[baseBiome] instanceof BTABiomeGenRiver) {
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
	
	public static int getBeachVariantForBiomes(int baseBiome, BTAWorldConfigurationInfo generatorInfo) {
		int beachBiome = -1;
		
		if (baseBiome == outback.biomeID || baseBiome == badlands.biomeID || baseBiome == beachOutback.biomeID) {
			beachBiome = beachOutback.biomeID;
		}
		else if ((baseBiome == snowyWoods.biomeID || baseBiome == tundra.biomeID || baseBiome == siberia.biomeID || baseBiome == frozenSprings.biomeID || baseBiome == beachFrozen.biomeID) && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_2)) {
			beachBiome = beachFrozen.biomeID;
		}
		else if (shouldBiomeSpawnBeach(baseBiome, generatorInfo)) {
			beachBiome = beach.biomeID;
		}
		
		return beachBiome;
	}
	
	public static int getEdgeVariantForBiome(int baseBiome, BTAWorldConfigurationInfo generatorInfo, int passNum) {
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
		else if ((baseBiome == badlands.biomeID || baseBiome == badlandsPlateau.biomeID) && generatorInfo.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_4)) {
			edgeBiome = badlandsEdge.biomeID;
		}
		else if (baseBiome == icyPeaks.biomeID) {
			edgeBiome = icyPeaksEdge.biomeID;
		}
		else if (baseBiome == highlands.biomeID) {
			edgeBiome = highlandsEdge.biomeID;
		}
		else if (baseBiome == jungle.biomeID && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_2)) {
			edgeBiome = jungleEdge.biomeID;
		}
		else if (baseBiome == rainforest.biomeID && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_2)) {
			edgeBiome = rainforestEdge.biomeID;
		}
		else if (baseBiome == tropics.biomeID && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_2)) {
			edgeBiome = tropicsEdge.biomeID;
		}
		
		return edgeBiome;
	}
	
	public static int getEdgeVariantForBiomeGuaranteed(int baseBiome, BTAWorldConfigurationInfo generatorInfo) {
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
	
	public static boolean shouldBiomeConnectWithEdge(int biome, BTAWorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_4_0)) {
			return !noEdgeBiomes140.contains(BiomeGenBase.biomeList[biome]);
		}
		if (generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_2)) {
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
	
	public static boolean shouldBiomeSpawnBeach(int biome, BTAWorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_1)) {
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
	
	public static ArrayList<BTABiomeGenBase> getBiomes() {
		return biomeList;
	}
	
	public static ArrayList<BTABiomeGenBase> getBiomesDeco() {
		return biomeListDeco;
	}
	
	public static boolean canBiomeSpawnPumpkin(BiomeGenBase biome) {
		return pumpkinBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnMelon(BiomeGenBase biome) {
		return jungleTempleBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnReeds(BiomeGenBase biome) {
		return reedBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnVillage(BiomeGenBase biome) {
		return getVillageBiomes().contains(biome);
	}
	
	public static boolean canBiomeSpawnJungleTemple(BiomeGenBase biome) {
		return jungleTempleBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnDesertTemple(BiomeGenBase biome) {
		return desertTempleBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnRedDesertTemple(BiomeGenBase biome) {
		return redDesertTempleBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnWitchHut(BiomeGenBase biome) {
		return witchHutBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnStronghold(BiomeGenBase biome) {
		return biome != BiomeGenBase.ocean && biome != BiomeGenBase.frozenOcean;
	}

	public static ArrayList<BiomeGenBase> getVillageBiomes() {
		return villageBiomes;
	}
	
	public static ArrayList<BiomeGenBase> getEdgeBiomes(BTAWorldConfigurationInfo generatorInfo) {
		return edgeBiomes;
	}
	
	public static ArrayList<BTABiomeGenBase> getClimateListForGenerator(BTAEnumClimate climate, ArrayList<BTABiomeGenBase> biomesForGeneration) {
		ArrayList<BTABiomeGenBase> newClimateList = new ArrayList<BTABiomeGenBase>();
		
		for (BTABiomeGenBase b : biomeCategoryMap.get(climate)) {
			if (biomesForGeneration.contains(b)) {
				newClimateList.add(b);
			}
		}
		
		return newClimateList;
	}
}