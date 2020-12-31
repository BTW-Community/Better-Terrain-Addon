package net.minecraft.src;

import java.util.ArrayList;

public class BTABiomeConfiguration {
	public static final BTABiomeGenBase woods = new BTABiomeGenWoods(100).setColor(353825).setBiomeName("Woods").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase desert = new BTABiomeGenDesert(101).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.2F, 0.4F);
	public static final BTABiomeGenBase lushDesert = new BTABiomeGenLushDesert(102).setColor(16711935).setBiomeName("Lush Desert").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTABiomeGenBase oasis = new BTABiomeGenOasis(103).setColor(16711935).setBiomeName("Oasis").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.2F, 0.1F); //Spawns within lush deserts
	public static final BTABiomeGenBase savanna = new BTABiomeGenSavanna(104).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.6F);
    public static final BTABiomeGenBase wetlands = new BTABiomeGenWetlands(105).setColor(522674).setBiomeName("Wetlands").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase birchForest = new BTABiomeGenBirchForest(106).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase snowyWoods = new BTABiomeGenWoods(107).setColor(353825).setBiomeName("Snowy Woods").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase steppe = new BTABiomeGenSteppe(108).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTABiomeGenBase woodedSteppe = new BTABiomeGenWoodedSteppe(109).setColor(9286496).setBiomeName("Wooded Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.5F); //Spawns within steppe
	public static final BTABiomeGenBase chaparral = new BTABiomeGenChaparral(110).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTABiomeGenBase ancientForest = new BTABiomeGenAncientForest(111).setColor(353825).setBiomeName("Ancient Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase tropics = new BTABiomeGenTropics(112).setColor(16711935).setBiomeName("Tropics").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.2F, 0.9F);
    public static final BTABiomeGenBase jungle = new BTABiomeGenJungle(113).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.0F, 0.4F);
    public static final BTABiomeGenBase alpine = new BTABiomeGenAlpine(114).setColor(747097).setBiomeName("Alpine").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.5F, 3.0F);
    public static final BTABiomeGenBase aspenGrove = new BTABiomeGenAspenGrove(115).setColor(747097).setBiomeName("Aspen Grove").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.5F, 3.0F); //Spawns within Alpine
    public static final BTABiomeGenBase fungalForest = new BTABiomeGenFungalForest(116).setColor(522674).setBiomeName("Fungal Forest").func_76733_a(9154376).setMinMaxHeight(-0.2F, 1.2F).setTemperatureRainfall(0.4F, 0.9F);
	public static final BTABiomeGenBase coniferousForest = new BTABiomeGenConiferousForest(117).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase coniferousForestClearing = new BTABiomeGenConiferousForestClearing(118).setColor(747097).setBiomeName("Coniferous Forest Clearing").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.2F); // Spawns within coniferous forest
	public static final BTABiomeGenBase coniferousForestSnow = new BTABiomeGenConiferousForest(119).setColor(747097).setBiomeName("Snowy Coniferous Forest").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 1.2F);
	public static final BTABiomeGenBase coniferousForestClearingSnow = new BTABiomeGenConiferousForestClearing(120).setColor(747097).setBiomeName("Snowy Coniferous Forest Clearing").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.3F, 1.2F); // Spawns within snowy coniferous forest
	public static final BTABiomeGenBase mysticForest = new BTABiomeGenMysticForest(121).setColor(10039961).setBiomeName("Mystic Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase rainforest = new BTABiomeGenRainforest(122).setColor(9286496).setBiomeName("Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTABiomeGenBase meadow = new BTABiomeGenMeadow(123).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 1.0F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase orchard = new BTABiomeGenOrchard(124).setColor(353825).setBiomeName("Orchard").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.5F);
	public static final BTABiomeGenBase mountains = new BTABiomeGenMountain(125).setColor(14090235).setBiomeName("Mountains").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.8F, 2.5F);
	public static final BTABiomeGenBase dunes = new BTABiomeGenDesert(126).setColor(16421912).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.5F);
	public static final BTABiomeGenBase heathland = new BTABiomeGenHeathland(127).setColor(9286496).setBiomeName("Heathland").setTemperatureRainfall(0.7F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase heathlandWoods = new BTABiomeGenHeathlandWoods(128).setColor(9286496).setBiomeName("Heathland Woods").setTemperatureRainfall(0.7F, 0.4F).setMinMaxHeight(0.1F, 0.5F); //Spawns within heathland
	public static final BTABiomeGenBase borealForest = new BTABiomeGenBorealForest(129).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.2F, 1.0F).setTemperatureRainfall(0.6F, 0.7F);
	public static final BTABiomeGenBase valleyMountains = new BTABiomeGenValley(130).setColor(353825).setBiomeName("Valley").func_76733_a(5159473).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.5F, 3.0F);
	public static final BTABiomeGenBase oldValley = new BTABiomeGenOldValley(131).setColor(353825).setBiomeName("Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.8F);
	public static final BTABiomeGenBase tundra = new BTABiomeGenTundra(132).setColor(16421912).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.1F, 0.4F);

	//Hill variants - spawn within the normal variants
	public static final BTABiomeGenBase woodsHills = new BTABiomeGenWoods(150).setColor(353825).setBiomeName("Woods Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase desertHills = new BTABiomeGenDesert(151).setColor(16421912).setBiomeName("Desert Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase savannaHills = new BTABiomeGenSavanna(152).setColor(9286496).setBiomeName("Savanna Hills").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase birchForestHills = new BTABiomeGenBirchForest(153).setColor(353825).setBiomeName("Birch Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase snowyWoodsHills = new BTABiomeGenWoods(154).setColor(353825).setBiomeName("Snowy Woods Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase chaparralHills = new BTABiomeGenChaparral(155).setColor(9286496).setBiomeName("Chaparral Hills").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase ancientForestHills = new BTABiomeGenAncientForest(156).setColor(353825).setBiomeName("Ancient Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
    public static final BTABiomeGenBase jungleHills = new BTABiomeGenJungle(157).setColor(2900485).setBiomeName("Jungle Hills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.5F);
    public static final BTABiomeGenBase fungalForestHills = new BTABiomeGenFungalForest(158).setColor(522674).setBiomeName("Fungal Forest Hills").func_76733_a(9154376).setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.4F, 0.9F);
    public static final BTABiomeGenBase wetlandsHills = new BTABiomeGenWetlands(159).setColor(522674).setBiomeName("Wetlands Hills").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.6F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTABiomeGenBase cherryForestHills = new BTABiomeGenCherryForest(160).setColor(353825).setBiomeName("Cherry Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase autumnForestHills = new BTABiomeGenAutumnForest(161).setColor(353825).setBiomeName("Autumn Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.2F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTABiomeGenBase valley = new BTABiomeGenValley(162).setColor(353825).setBiomeName("Valley").func_76733_a(5159473).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.1F, 0.6F);
    
    //Deco only biomes
    public static final BTABiomeGenBase outback = new BTABiomeGenOutback(180).setColor(16421912).setBiomeName("Outback").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTABiomeGenBase cherryForest = new BTABiomeGenCherryForest(181).setColor(353825).setBiomeName("Cherry Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTABiomeGenBase badlands = new BTABiomeGenBadlands(182).setColor(16421912).setBiomeName("Badlands").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
	public static final BTABiomeGenBase badlandsPlateau = new BTABiomeGenBadlandsPlateau(183).setColor(16421912).setBiomeName("Badlands Plateau").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(1.0F, 1.0F); //Spawns within badlands
	public static final BTABiomeGenBase autumnForest = new BTABiomeGenAutumnForest(184).setColor(353825).setBiomeName("Autumn Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.2F).setMinMaxHeight(0.1F, 0.5F);
	
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
    
    //Edge variants
    public static final BTABiomeGenBase alpineEdge = new BTABiomeGenAlpine(230).setColor(747097).setBiomeName("Alpine Edge").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.2F, 0.5F);
    public static final BTABiomeGenBase mountainEdge = new BTABiomeGenMountain(231).setColor(14090235).setBiomeName("Mountains Edge").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.2F, 0.5F);
	public static final BTABiomeGenBase badlandsEdge = new BTABiomeGenBadlands(232).setColor(16421912).setBiomeName("Badlands").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
	
	//Beach variants
    public static final BTABiomeGenBase beachOutback = new BTABiomeGenBeachOutback(240).setColor(16440917).setBiomeName("Red Sand Beach").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.1F);
	
	public static ArrayList<BTABiomeGenBase> biomeList = new ArrayList();
	public static ArrayList<BTABiomeGenBase> biomeListDeco = new ArrayList();

	private static ArrayList<BiomeGenBase> beachlessBiomes = new ArrayList();
	
	private static ArrayList<BiomeGenBase> edgeBiomes = new ArrayList<BiomeGenBase>();
	private static ArrayList<BiomeGenBase> noEdgeBiomes = new ArrayList<BiomeGenBase>();
	
	private static ArrayList<BiomeGenBase> pumpkinBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> reedBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> villageBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> jungleTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> desertTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> witchHutBiomes = new ArrayList();
	
	public static void init() {
		filterSpawnBiomes();
		filterBeachBiomes();
		addBiomesWithEdge();
		filterEdgeBiomes();
		addBiomesToStructureGenerators();
		//addBiomesToList();
		addSingleBiome(orchard);
	}
	
	public static void addBiomesToList() {
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
		biomeList.add(coniferousForestSnow);
		biomeList.add(mysticForest);
		biomeList.add(rainforest);
		biomeList.add(meadow);
		biomeList.add(mountains);
		biomeList.add(dunes);
		biomeList.add(heathland);
		biomeList.add(borealForest);
		biomeList.add(valleyMountains);
		biomeList.add(tundra);
		//biomeList.add(valley);
		biomeList.add(orchard);
		biomeList.add(steppe);
		
		biomeListDeco.addAll(biomeList);
		biomeListDeco.add(outback);
		biomeListDeco.add(cherryForest);
		biomeListDeco.add(badlands);
		biomeListDeco.add(autumnForest);
	}
	
	public static void addSingleBiome(BTABiomeGenBase biome) {
		biomeList.add(biome);
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
		beachlessBiomes.add(fungalForestHills);
		beachlessBiomes.add(coniferousForest);
		beachlessBiomes.add(coniferousForestSnow);
		beachlessBiomes.add(mysticForest);
		beachlessBiomes.add(mountains);
		beachlessBiomes.add(borealForest);
		beachlessBiomes.add(oldValley);
		beachlessBiomes.add(valleyMountains);
		beachlessBiomes.add(tundra);
		beachlessBiomes.add(snowyWoods);
		beachlessBiomes.add(snowyWoodsHills);
		beachlessBiomes.add(badlandsPlateau);
		beachlessBiomes.add(rainforest);
		beachlessBiomes.add(tropics);
	}
	
	public static void addBiomesToStructureGenerators() {
		//14.8%/12.9%
		pumpkinBiomes.add(chaparral);
		pumpkinBiomes.add(meadow);
		pumpkinBiomes.add(heathland);
		pumpkinBiomes.add(orchard);
		
		//22.2%/19.4%
		reedBiomes.add(wetlands);
		reedBiomes.add(tropics);
		reedBiomes.add(jungle);
		reedBiomes.add(fungalForest);
		reedBiomes.add(mysticForest);
		reedBiomes.add(rainforest);
		
		//25.9%/25.8%
		villageBiomes.add(savanna);
		villageBiomes.add(desert);
		villageBiomes.add(lushDesert);
		villageBiomes.add(chaparral);
		villageBiomes.add(meadow);
		villageBiomes.add(heathland);
		villageBiomes.add(steppe);
		villageBiomes.add(orchard);
		villageBiomes.add(outback);
		
		MapGenVillage.villageSpawnBiomes.addAll(villageBiomes);
		
		//11.1%/9.7%
		jungleTempleBiomes.add(tropics);
		jungleTempleBiomes.add(jungle);
		jungleTempleBiomes.add(rainforest);
		
		//11.1%/9.7%
		desertTempleBiomes.add(desert);
		desertTempleBiomes.add(lushDesert);
		desertTempleBiomes.add(dunes);
		
		//11.1%/9.7%
		witchHutBiomes.add(wetlands);
		witchHutBiomes.add(fungalForest);
		witchHutBiomes.add(mysticForest);
		
		BTAMapGenScatteredFeature.biomelist.addAll(jungleTempleBiomes);
		BTAMapGenScatteredFeature.biomelist.addAll(desertTempleBiomes);
		BTAMapGenScatteredFeature.biomelist.addAll(witchHutBiomes);
		
		//Hill variants
		pumpkinBiomes.add(savannaHills);
		pumpkinBiomes.add(chaparralHills);
		pumpkinBiomes.add(heathlandWoods);
		
		reedBiomes.add(jungleHills);
		reedBiomes.add(fungalForestHills);
		reedBiomes.add(wetlandsHills);
		
		villageBiomes.add(savannaHills);
		villageBiomes.add(desertHills);
		villageBiomes.add(woodedSteppe);
		villageBiomes.add(chaparralHills);

		jungleTempleBiomes.add(jungleHills);

		desertTempleBiomes.add(desertHills);
		desertTempleBiomes.add(woodedSteppe);

		witchHutBiomes.add(fungalForestHills);
		
		//Rivers
		reedBiomes.add(riverRainforest);
		reedBiomes.add(riverTropics);
		reedBiomes.add(riverMystic);
	}
	
	public static int getHillsVariantForBiomes(int baseBiome) {
		int hillsBiome = baseBiome;
		
		if (baseBiome == woods.biomeID){
            hillsBiome = woodsHills.biomeID;
        }
        else if (baseBiome == desert.biomeID){
            hillsBiome = desertHills.biomeID;
        }
        else if (baseBiome == lushDesert.biomeID){
            hillsBiome = oasis.biomeID;
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
        else if (baseBiome == fungalForest.biomeID) {
        	hillsBiome = fungalForestHills.biomeID;
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
		
		return hillsBiome;
	}
	
	//Appear more commonly than normal hills
	public static int getHillsVariantForBiomes2(int baseBiome) {
		int hillsBiome = baseBiome;
		
		if (baseBiome == badlands.biomeID){
            hillsBiome = badlandsPlateau.biomeID;
        }
        else  if (baseBiome == wetlands.biomeID) {
        	hillsBiome = wetlandsHills.biomeID;
        }
		
		return hillsBiome;
	}
	
	public static int getRiverVariantForBiomes(int baseBiome) {
		int riverBiome = -1;
		
		if (BiomeGenBase.biomeList[baseBiome].getEnableSnow()) {
			riverBiome = BiomeGenBase.frozenRiver.biomeID;
		}
		else if (baseBiome == desert.biomeID || baseBiome == desertHills.biomeID || baseBiome == dunes.biomeID) {
			riverBiome = riverDesert.biomeID;
		}
		else if (baseBiome == mysticForest.biomeID) {
			riverBiome = riverMystic.biomeID;
		}
		else if (baseBiome == rainforest.biomeID) {
			riverBiome = riverRainforest.biomeID;
		}
		else if (baseBiome == outback.biomeID) {
			riverBiome = riverOutback.biomeID;
		}
		else if (baseBiome == badlands.biomeID || baseBiome == badlandsPlateau.biomeID) {
			riverBiome = riverBadlands.biomeID;
		}
		else if (baseBiome == tropics.biomeID) {
			riverBiome = riverTropics.biomeID;
		}
		else if (baseBiome == orchard.biomeID) {
			riverBiome = riverOrchard.biomeID;
		}
		else if (baseBiome == jungle.biomeID) {
			riverBiome = riverJungle.biomeID;
		}
		else if (baseBiome == wetlands.biomeID) {
			riverBiome = riverWetlands.biomeID;
		}
		
		return riverBiome;
	}
	
	public static int getBeachVariantForBiomes(int baseBiome) {
		int beachBiome = -1;
		
		if (baseBiome == outback.biomeID || baseBiome == badlands.biomeID) {
			beachBiome = beachOutback.biomeID;
		}
		
		return beachBiome;
	}
	
	public static int getEdgeVariantForBiome(int baseBiome) {
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
		else if (baseBiome == badlands.biomeID) {
			edgeBiome = badlandsEdge.biomeID;
		}
		
		return edgeBiome;
	}
	
	public static void addBiomesWithEdge() {
		edgeBiomes.add(alpine);
		edgeBiomes.add(mountains);
		edgeBiomes.add(valleyMountains);
		edgeBiomes.add(dunes);
		edgeBiomes.add(badlands);
	}
	
	public static void filterEdgeBiomes() {
		noEdgeBiomes.addAll(edgeBiomes);
		noEdgeBiomes.add(mysticForest);
		noEdgeBiomes.add(rainforest);
		noEdgeBiomes.add(borealForest);
		noEdgeBiomes.add(coniferousForest);
		noEdgeBiomes.add(coniferousForestSnow);
		noEdgeBiomes.remove(badlands);
	}
	
	public static boolean shouldBiomeConnectWithEdge(int biome) {
		return !noEdgeBiomes.contains(BiomeGenBase.biomeList[biome]);
	}
	
	public static boolean doesBiomeIgnoreEdgeRestrictions(int biome) {
		return biome == badlands.biomeID;
	}
	
	public static boolean shouldBiomeSpawnBeach(int biomeID) {
		BiomeGenBase biome = BiomeGenBase.biomeList[biomeID];
		return !beachlessBiomes.contains(biome);
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
	
	public static boolean canBiomeSpawnWitchHut(BiomeGenBase biome) {
		return witchHutBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnStronghold(BiomeGenBase biome) {
		return biome != BiomeGenBase.ocean && biome != BiomeGenBase.frozenOcean;
	}

	public static ArrayList<BiomeGenBase> getVillageBiomes() {
		return villageBiomes;
	}
	
	public static ArrayList<BiomeGenBase> getEdgeBiomes() {
		return edgeBiomes;
	}
}