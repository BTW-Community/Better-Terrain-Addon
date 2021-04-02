package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BTABiomeConfiguration {
	public static final BTABiomeGenBase woods = new BTABiomeGenWoods(100, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Woods").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase desert = new BTABiomeGenDesert(101, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Better Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.2F, 0.4F);
	public static final BTABiomeGenBase lushDesert = new BTABiomeGenLushDesert(102, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Lush Desert").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.1F, 0.6F);
	public static final BTABiomeGenBase oasis = new BTABiomeGenOasis(103, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Oasis").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.2F, 0.1F); //Spawns within lush deserts
	public static final BTABiomeGenBase savanna = new BTABiomeGenSavanna(104, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.3F);
    public static final BTABiomeGenBase wetlands = new BTABiomeGenWetlands(105, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Wetlands").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase birchForest = new BTABiomeGenBirchForest(106, BTAEnumClimate.COLD).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase snowyWoods = new BTABiomeGenWoods(107, BTAEnumClimate.SNOWY).setColor(353825).setBiomeName("Snowy Woods").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase steppe = new BTABiomeGenSteppe(108, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTABiomeGenBase woodedSteppe = new BTABiomeGenWoodedSteppe(109, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Wooded Steppe").setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.3F, 0.5F); //Spawns within steppe
	public static final BTABiomeGenBase chaparral = new BTABiomeGenChaparral(110, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTABiomeGenBase ancientForest = new BTABiomeGenAncientForest(111, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Ancient Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase tropics = new BTABiomeGenTropics(112, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Tropics").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.2F, 0.9F);
    public static final BTABiomeGenBase jungle = new BTABiomeGenJungle(113, BTAEnumClimate.TROPICAL).setColor(5470985).setBiomeName("Better Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.0F, 0.4F);
    public static final BTABiomeGenBase alpine = new BTABiomeGenAlpine(114, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Alpine").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.5F, 3.0F);
    public static final BTABiomeGenBase aspenGrove = new BTABiomeGenAspenGrove(115, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Aspen Grove").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.5F, 3.0F); //Spawns within Alpine
    public static final BTABiomeGenBase fungalForest = new BTABiomeGenFungalForest(116, BTAEnumClimate.COLD).setColor(522674).setBiomeName("Fungal Forest").func_76733_a(9154376).setMinMaxHeight(-0.1F, 1.2F).setTemperatureRainfall(0.4F, 1.0F);
	public static final BTABiomeGenBase coniferousForest = new BTABiomeGenConiferousForest(117, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase coniferousForestClearing = new BTABiomeGenConiferousForestClearing(118, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Coniferous Forest Clearing").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.2F); // Spawns within coniferous forest
	public static final BTABiomeGenBase coniferousForestSnow = new BTABiomeGenConiferousForest(119, BTAEnumClimate.SNOWY).setColor(747097).setBiomeName("Snowy Coniferous Forest").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase coniferousForestClearingSnow = new BTABiomeGenConiferousForestClearing(120, BTAEnumClimate.SNOWY).setColor(747097).setBiomeName("Snowy Coniferous Forest Clearing").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 1.2F); // Spawns within snowy coniferous forest
	public static final BTABiomeGenBase mysticForest = new BTABiomeGenMysticForest(121, BTAEnumClimate.TEMPERATE).setColor(10039961).setBiomeName("Mystic Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase rainforest = new BTABiomeGenRainforest(122, BTAEnumClimate.TROPICAL).setColor(9286496).setBiomeName("Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase meadow = new BTABiomeGenMeadow(123, BTAEnumClimate.TROPICAL).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 1.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase orchard = new BTABiomeGenOrchard(124, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Orchard").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.5F);
	public static final BTABiomeGenBase mountains = new BTABiomeGenMountain(125, BTAEnumClimate.COLD).setColor(14090235).setBiomeName("Mountains").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.8F, 2.5F);
	public static final BTABiomeGenBase dunes = new BTABiomeGenDunes(126, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase heathland = new BTABiomeGenHeathland(127, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Heathland").setTemperatureRainfall(0.7F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase heathlandWoods = new BTABiomeGenHeathlandWoods(128, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Heathland Woods").setTemperatureRainfall(0.7F, 0.4F).setMinMaxHeight(0.1F, 0.5F); //Spawns within heathland
	public static final BTABiomeGenBase temperateForest = new BTABiomeGenTemperateForest(129, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Temperate Forest").func_76733_a(5159473).setMinMaxHeight(0.2F, 1.0F).setTemperatureRainfall(0.6F, 0.7F);
	public static final BTABiomeGenBase valleyMountains = new BTABiomeGenValley(130, BTAEnumClimate.TROPICAL).setColor(353825).setBiomeName("Valley Highlands").func_76733_a(5159473).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.5F, 3.0F);
	public static final BTABiomeGenBase oldValley = new BTABiomeGenOldValley(131, BTAEnumClimate.TROPICAL).setColor(353825).setBiomeName("Old Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.8F);
	public static final BTABiomeGenBase tundra = new BTABiomeGenTundra(132, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase willowGrove = new BTABiomeGenWillowGrove(133, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Willow Grove").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.8F);
	public static final BTABiomeGenBase icyPeaks = new BTABiomeGenIcyPeaks(134, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Icy Peaks").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase patagonia = new BTABiomeGenPatagonia(135, BTAEnumClimate.COLD).setColor(9286496).setBiomeName("Patagonia").setTemperatureRainfall(0.3F, 0.6F).setMinMaxHeight(0.0F, 0.5F);
	public static final BTABiomeGenBase grasslands = new BTABiomeGenGrasslands(136, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Grasslands").setTemperatureRainfall(0.5F, 0.6F).setMinMaxHeight(0.2F, 0.3F);
	public static final BTABiomeGenBase siberia = new BTABiomeGenSiberia(137, BTAEnumClimate.SNOWY).setColor(747097).setBiomeName("Siberia").func_76733_a(5159473).setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 0.7F).setEnableSnow();
	public static final BTABiomeGenBase plains = new BTABiomeGenPlains(138, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Better Plains").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.4F);
	public static final BTABiomeGenBase frozenSprings = new BTABiomeGenFrozenSprings(139, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Frozen Springs").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.2F, 0.6F);
    public static final BTABiomeGenBase mangroveForest = new BTABiomeGenMangroveForest(140, BTAEnumClimate.TEMPERATE).setColor(522674).setBiomeName("Mangal").func_76733_a(9154376).setMinMaxHeight(-0.3F, 0.2F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase borealForest = new BTABiomeGenBorealForest(141, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Boreal Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase aridForest = new BTABiomeGenAridForest(142, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Arid Forest").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.4F);
	public static final BTABiomeGenBase shield = new BTABiomeGenShield(143, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Shield").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.2F, 0.6F);
	public static final BTABiomeGenBase brushland = new BTABiomeGenBrushland(144, BTAEnumClimate.COLD).setColor(9286496).setBiomeName("Brushland").setTemperatureRainfall(0.4F, 0.2F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTABiomeGenBase highlands = new BTABiomeGenHighlands(145, BTAEnumClimate.TEMPERATE).setColor(14090235).setBiomeName("Highlands").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(0.8F, 2.5F);

	//Hill variants - spawn within the normal variants
	public static final BTABiomeGenBase woodsHills = new BTABiomeGenWoods(150, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Woods Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase desertHills = new BTABiomeGenDesert(151, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Desert Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase savannaHills = new BTABiomeGenSavanna(152, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Savanna Hills").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 0.8F);
	public static final BTABiomeGenBase birchForestHills = new BTABiomeGenBirchForest(153, BTAEnumClimate.COLD).setColor(353825).setBiomeName("Birch Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase snowyWoodsHills = new BTABiomeGenWoods(154, BTAEnumClimate.SNOWY).setColor(353825).setBiomeName("Snowy Woods Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase chaparralHills = new BTABiomeGenChaparral(155, BTAEnumClimate.ARID).setColor(9286496).setBiomeName("Chaparral Hills").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase ancientForestHills = new BTABiomeGenAncientForest(156, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Ancient Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
    public static final BTABiomeGenBase jungleHills = new BTABiomeGenJungle(157, BTAEnumClimate.TROPICAL).setColor(2900485).setBiomeName("Jungle Hills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.5F);
    public static final BTABiomeGenBase fungalForestFlat = new BTABiomeGenFungalForest(158, BTAEnumClimate.COLD).setColor(522674).setBiomeName("Fungal Forest Lowlands").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.5F).setTemperatureRainfall(0.4F, 1.0F);
    public static final BTABiomeGenBase wetlandsHills = new BTABiomeGenWetlands(159, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Wetlands Hills").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.6F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase cherryForestHills = new BTABiomeGenCherryForest(160, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Cherry Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase autumnForestHills = new BTABiomeGenAutumnForest(161, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Autumn Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.2F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase valley = new BTABiomeGenValley(162, BTAEnumClimate.TROPICAL).setColor(353825).setBiomeName("Valley").func_76733_a(5159473).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.1F, 0.6F);
	public static final BTABiomeGenBase orchardClearing = new BTABiomeGenOrchardClearing(163, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Orchard Clearing").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.5F);
	public static final BTABiomeGenBase willowHills = new BTABiomeGenWillowGrove(164, BTAEnumClimate.TROPICAL).setColor(522674).setBiomeName("Willow Hills").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.6F).setTemperatureRainfall(0.8F, 0.8F);
	public static final BTABiomeGenBase icyPeaksForested = new BTABiomeGenIcyPeaksForested(165, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Forested Icy Peaks").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase patagoniaMountains = new BTABiomeGenPatagoniaMountains(166, BTAEnumClimate.COLD).setColor(9286496).setBiomeName("Patagonia Mountains").setTemperatureRainfall(0.1F, 0.6F).setMinMaxHeight(2.0F, 4.0F).setEnableSnow();
	public static final BTABiomeGenBase grasslandsLake = new BTABiomeGenGrasslandsLake(167, BTAEnumClimate.TEMPERATE).setColor(9286496).setBiomeName("Grasslands Lake").setTemperatureRainfall(0.5F, 0.6F).setMinMaxHeight(-0.3F, 0.0F);
	public static final BTABiomeGenBase frozenSpringsPond = new BTABiomeGenFrozenSpringPond(168, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Frozen Springs Pond").setTemperatureRainfall(0.2F, 0.1F).setMinMaxHeight(-0.3F, 0.0F);
    public static final BTABiomeGenBase mangroveForestIsland = new BTABiomeGenMangroveForest(169, BTAEnumClimate.TEMPERATE).setColor(522674).setBiomeName("Mangal Island").func_76733_a(9154376).setMinMaxHeight(0.0F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase borealForestHills = new BTABiomeGenBorealForest(170, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Boreal Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
    
    //Deco only biomes
    public static final BTABiomeGenBase outback = new BTABiomeGenOutback(180, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Outback").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase cherryForest = new BTABiomeGenCherryForest(181, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Cherry Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase badlands = new BTABiomeGenBadlands(182, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Badlands").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
	public static final BTABiomeGenBase badlandsPlateau = new BTABiomeGenBadlandsPlateau(183, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Badlands Plateau").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.8F, 2.0F); //Spawns within badlands
	public static final BTABiomeGenBase autumnForest = new BTABiomeGenAutumnForest(184, BTAEnumClimate.TEMPERATE).setColor(353825).setBiomeName("Autumn Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.2F).setMinMaxHeight(0.1F, 0.5F);
	
	//River variants
	public static final BTABiomeGenBase riverDesert = new BTABiomeGenRiverDesert(200).setColor(255).setBiomeName("Desert River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverMystic = new BTABiomeGenRiverMystic(201).setColor(255).setBiomeName("Mystic River").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverRainforest = new BTABiomeGenRiverRainforest(202).setColor(255).setBiomeName("Rainforest River").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverOutback = new BTABiomeGenRiverOutback(203).setColor(255).setBiomeName("Outback River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverBadlands = new BTABiomeGenRiverOutback(204).setColor(255).setBiomeName("Badlands River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverTropics = new BTABiomeGenRiverTropics(205).setColor(255).setBiomeName("Tropics River").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverOrchard = new BTABiomeGenRiverOrchard(206).setColor(255).setBiomeName("Orchard River").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverJungle = new BTABiomeGenRiverJungle(207).setColor(5470985).setBiomeName("Jungle River").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverWetlands = new BTABiomeGenRiverWetlands(208).setColor(522674).setBiomeName("Wetlands River").func_76733_a(9154376).setTemperatureRainfall(0.8F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverWillow = new BTABiomeGenRiverWillow(209).setColor(522674).setBiomeName("Willow Grove River").func_76733_a(9154376).setTemperatureRainfall(0.6F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTABiomeGenBase riverPatagonia = new BTABiomeGenRiverPatagonia(210).setColor(9286496).setBiomeName("Patagonia River").setTemperatureRainfall(0.3F, 0.6F).setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase river = new BTABiomeGenRiver(211, BTAEnumClimate.TEMPERATE).setColor(255).setBiomeName("Better River").setMinMaxHeight(-0.5F, 0.0F);
    public static final BTABiomeGenBase riverFrozen = new BTABiomeGenRiver(212, BTAEnumClimate.SNOWY).setColor(255).setBiomeName("Better Frozen River").setEnableSnow().setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F);
    
    //Edge variants
    public static final BTABiomeGenBase alpineEdge = new BTABiomeGenAlpine(230, BTAEnumClimate.COLD).setColor(747097).setBiomeName("Alpine Edge").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.2F, 0.5F);
    public static final BTABiomeGenBase mountainEdge = new BTABiomeGenMountain(231, BTAEnumClimate.COLD).setColor(14090235).setBiomeName("Mountains Edge").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.2F, 0.5F);
	public static final BTABiomeGenBase badlandsEdge = new BTABiomeGenBadlands(232, BTAEnumClimate.ARID).setColor(16421912).setBiomeName("Badlands Edge").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
	public static final BTABiomeGenBase icyPeaksEdge = new BTABiomeGenIcyPeaks(233, BTAEnumClimate.SNOWY).setColor(16421912).setBiomeName("Icy Peaks Edge").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase highlandsEdge = new BTABiomeGenHighlands(234, BTAEnumClimate.TEMPERATE).setColor(14090235).setBiomeName("Highlands Edge").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(0.8F, 2.5F);
    public static final BTABiomeGenBase jungleEdge = new BTABiomeGenJungleEdge(235, BTAEnumClimate.TROPICAL).setColor(5470985).setBiomeName("Better Jungle Edge").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.0F, 0.4F);
	public static final BTABiomeGenBase rainforestEdge = new BTABiomeGenRainforestEdge(236, BTAEnumClimate.TROPICAL).setColor(9286496).setBiomeName("Rainforest Edge").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase tropicsEdge = new BTABiomeGenTropics(237, BTAEnumClimate.TROPICAL).setColor(16711935).setBiomeName("Tropics Edge").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.3F, 0.1F);
	
	//Beach variants
    public static final BTABiomeGenBase beachOutback = new BTABiomeGenBeachOutback(240, BTAEnumClimate.ARID).setColor(16440917).setBiomeName("Red Sand Beach").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.1F);
    public static final BTABiomeGenBase beach = new BTABiomeGenBeach(241, BTAEnumClimate.TEMPERATE).setColor(16440917).setBiomeName("Better Beach").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
    public static final BTABiomeGenBase beachFrozen = new BTABiomeGenBeach(241, BTAEnumClimate.SNOWY).setColor(16440917).setBiomeName("Frozen Beach").setTemperatureRainfall(0.0F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
	
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
		mangroveForest.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		mangroveForestIsland.setSurfaceBuilder(new BTASurfaceBuilderNoShorelines());
		
		valley.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		valleyMountains.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		tropics.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		riverTropics.setSurfaceBuilder(new BTASurfaceBuilderTropics());
		
		badlands.setSurfaceBuilder(new BTASurfaceBuilderBadlands());
		riverBadlands.setSurfaceBuilder(new BTASurfaceBuilderBadlands());
		badlandsPlateau.setSurfaceBuilder(new BTASurfaceBuilderBadlandsPlateau());
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
        else if (baseBiome == steppe.biomeID) {
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
        else if (baseBiome == alpine.biomeID) {
        	hillsBiome = aspenGrove.biomeID;
        }
        else if (baseBiome == coniferousForest.biomeID) {
        	hillsBiome = coniferousForestClearing.biomeID;
        }
        else if (baseBiome == coniferousForestSnow.biomeID) {
        	hillsBiome = coniferousForestClearingSnow.biomeID;
        }
        else if (baseBiome == heathland.biomeID) {
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
        else if (baseBiome == orchard.biomeID) {
        	hillsBiome = orchardClearing.biomeID;
        }
        else if (baseBiome == fungalForest.biomeID) {
        	hillsBiome = fungalForestFlat.biomeID;
        }
        else if (baseBiome == badlandsPlateau.biomeID){
            hillsBiome = badlands.biomeID;
        }
        else if (baseBiome == icyPeaks.biomeID) {
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
		
		if (baseBiome == outback.biomeID || baseBiome == badlands.biomeID) {
			beachBiome = beachOutback.biomeID;
		}
		else if ((baseBiome == snowyWoods.biomeID || baseBiome == tundra.biomeID || baseBiome == siberia.biomeID || baseBiome == frozenSprings.biomeID) && generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_3_2)) {
			beachBiome = beachFrozen.biomeID;
		}
		else if (shouldBiomeSpawnBeach(baseBiome, generatorInfo)) {
			beachBiome = beach.biomeID;
		}
		
		return beachBiome;
	}
	
	public static int getEdgeVariantForBiome(int baseBiome, BTAWorldConfigurationInfo generatorInfo) {
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
		else if (baseBiome == badlands.biomeID || baseBiome == badlandsPlateau.biomeID) {
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
	}
	
	public static boolean shouldBiomeConnectWithEdge(int biome, BTAWorldConfigurationInfo generatorInfo) {
		if (generatorInfo.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_3_1)) {
			return !noEdgeBiomes.contains(BiomeGenBase.biomeList[biome]);
		}
		else {
			return !noEdgeBiomes132.contains(BiomeGenBase.biomeList[biome]);
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