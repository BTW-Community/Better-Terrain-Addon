package net.minecraft.src;

import java.util.ArrayList;

public class BTWGBiomeConfiguration {
	public static final BTWGBiomeGenBase woods = new BTWGBiomeGenWoods(100).setColor(353825).setBiomeName("Woods").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase desert = new BTWGBiomeGenDesert(101).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTWGBiomeGenBase lushDesert = new BTWGBiomeGenLushDesert(102).setColor(16711935).setBiomeName("Lush Desert").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTWGBiomeGenBase oasis = new BTWGBiomeGenOasis(103).setColor(16711935).setBiomeName("Oasis").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.2F, 0.1F); //Spawns within lush deserts
	public static final BTWGBiomeGenBase savanna = new BTWGBiomeGenSavanna(104).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.6F);
    public static final BTWGBiomeGenBase mangroveSwamp = new BTWGBiomeGenMangroveSwamp(105).setColor(522674).setBiomeName("Mangrove Swamp").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTWGBiomeGenBase birchForest = new BTWGBiomeGenBirchForest(106).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase snowyWoods = new BTWGBiomeGenWoods(107).setColor(353825).setBiomeName("Snowy Woods").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase steppe = new BTWGBiomeGenSteppe(108).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTWGBiomeGenBase woodedSteppe = new BTWGBiomeGenWoodedSteppe(109).setColor(9286496).setBiomeName("Wooded Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.5F); //Spawns within steppe
	public static final BTWGBiomeGenBase chaparral = new BTWGBiomeGenChaparral(110).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTWGBiomeGenBase ancientForest = new BTWGBiomeGenAncientForest(111).setColor(353825).setBiomeName("Ancient Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase tropics = new BTWGBiomeGenTropics(112).setColor(16711935).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(-0.2F, 0.9F);
    public static final BTWGBiomeGenBase jungle = new BTWGBiomeGenJungle(113).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.0F, 0.4F);
    public static final BTWGBiomeGenBase alpine = new BTWGBiomeGenAlpine(114).setColor(747097).setBiomeName("Alpine").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 3.0F);
    public static final BTWGBiomeGenBase aspenGrove = new BTWGBiomeGenAspenGrove(115).setColor(747097).setBiomeName("Alpine").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 3.0F); //Spawns within Alpine
    public static final BTWGBiomeGenBase fungalForest = new BTWGBiomeGenFungalForest(116).setColor(522674).setBiomeName("Fungal Forest").func_76733_a(9154376).setMinMaxHeight(-0.2F, 1.2F).setTemperatureRainfall(0.4F, 0.9F);
	public static final BTWGBiomeGenBase coniferousForest = new BTWGBiomeGenConiferousForest(117).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.1F, 1.2F);
	public static final BTWGBiomeGenBase coniferousForestClearing = new BTWGBiomeGenConiferousForestClearing(118).setColor(747097).setBiomeName("Coniferous Forest Clearing").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.1F, 1.2F); // Spawns within coniferous forest
	public static final BTWGBiomeGenBase coniferousForestSnow = new BTWGBiomeGenConiferousForest(119).setColor(747097).setBiomeName("Snowy Coniferous Forest").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.1F, 1.2F);
	public static final BTWGBiomeGenBase coniferousForestClearingSnow = new BTWGBiomeGenConiferousForestClearing(120).setColor(747097).setBiomeName("Snowy Coniferous Forest Clearing").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.4F).setMinMaxHeight(0.1F, 1.2F); // Spawns within snowy coniferous forest
	public static final BTWGBiomeGenBase mysticForest = new BTWGBiomeGenMysticForest(121).setColor(10039961).setBiomeName("Mystic Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 3.0F);
	public static final BTWGBiomeGenBase rainforest = new BTWGBiomeGenRainforest(122).setColor(9286496).setBiomeName("Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTWGBiomeGenBase meadow = new BTWGBiomeGenMeadow(123).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 1.0F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase orchard = new BTWGBiomeGenOrchard(124).setColor(353825).setBiomeName("Orchard").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.5F);
	public static final BTWGBiomeGenBase mountain = new BTWGBiomeGenMountain(125).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.8F, 2.0F);

	//Hill variants - spawn within the normal variants
	public static final BTWGBiomeGenBase woodsHills = new BTWGBiomeGenWoods(150).setColor(353825).setBiomeName("Woods Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase desertHills = new BTWGBiomeGenDesert(151).setColor(16421912).setBiomeName("Desert Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase savannaHills = new BTWGBiomeGenSavanna(152).setColor(9286496).setBiomeName("Savanna Hills").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase birchForestHills = new BTWGBiomeGenBirchForest(153).setColor(353825).setBiomeName("Birch Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase snowyWoodsHills = new BTWGBiomeGenWoods(154).setColor(353825).setBiomeName("Snowy Woods Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.5F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase chaparralHills = new BTWGBiomeGenChaparral(155).setColor(9286496).setBiomeName("Chaparral Hills").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase ancientForestHills = new BTWGBiomeGenAncientForest(156).setColor(353825).setBiomeName("Ancient Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.0F);
    public static final BTWGBiomeGenBase jungleHills = new BTWGBiomeGenJungle(157).setColor(2900485).setBiomeName("Jungle Hills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.5F);
    public static final BTWGBiomeGenBase fungalForestHills = new BTWGBiomeGenFungalForest(158).setColor(522674).setBiomeName("Fungal Forest Hills").func_76733_a(9154376).setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.4F, 0.9F);
    public static final BTWGBiomeGenBase mangroveWetlands = new BTWGBiomeGenMangroveSwamp(159).setColor(522674).setBiomeName("Mangrove Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, -0.1F).setTemperatureRainfall(0.8F, 0.9F);
	
	//River variants
	public static final BTWGBiomeGenBase riverDesert = new BTWGBiomeGenRiverDesert(200).setColor(255).setBiomeName("Desert River").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTWGBiomeGenBase riverMystic = new BTWGBiomeGenRiverMystic(201).setColor(255).setBiomeName("Mystic River").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.5F, 0.0F);
	public static final BTWGBiomeGenBase riverRainforest = new BTWGBiomeGenRiverRainforest(202).setColor(255).setBiomeName("Rainforest River").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.5F, 0.0F);
	
	public static ArrayList<BTWGBiomeGenBase> biomeList = new ArrayList();
	
	private static ArrayList<BiomeGenBase> pumpkinBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> reedBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> villageBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> jungleTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> desertTempleBiomes = new ArrayList();
	private static ArrayList<BiomeGenBase> witchHutBiomes = new ArrayList();
	
	public static void init() {
		//addBiomesToList();
		filterSpawnBiomes();
		addBiomesToStructureGenerators();
		biomeList.add(mountain);
	}
	
	public static void addBiomesToList() {
		biomeList.add(woods);
		biomeList.add(desert);
		biomeList.add(lushDesert);
		biomeList.add(savanna);
		biomeList.add(mangroveSwamp);
		biomeList.add(birchForest);
		biomeList.add(snowyWoods);
		biomeList.add(steppe);
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
		biomeList.add(mountain);
	}
	
	public static void filterSpawnBiomes() {
		desert.setNotSpawnable();
		desertHills.setNotSpawnable();
		lushDesert.setNotSpawnable();
		steppe.setNotSpawnable();
		woodedSteppe.setNotSpawnable();
		tropics.setNotSpawnable();
		jungle.setNotSpawnable();
		rainforest.setNotSpawnable();
		riverRainforest.setNotSpawnable();
	}
	
	public static void addBiomesToStructureGenerators() {
		pumpkinBiomes.add(savanna);
		pumpkinBiomes.add(chaparral);
		pumpkinBiomes.add(meadow);
		
		reedBiomes.add(mangroveSwamp);
		reedBiomes.add(tropics);
		reedBiomes.add(jungle);
		reedBiomes.add(fungalForest);
		reedBiomes.add(mysticForest);
		reedBiomes.add(rainforest);
		
		villageBiomes.add(savanna);
		villageBiomes.add(desert);
		villageBiomes.add(lushDesert);
		villageBiomes.add(steppe);
		villageBiomes.add(chaparral);
		villageBiomes.add(meadow);
		
		jungleTempleBiomes.add(tropics);
		jungleTempleBiomes.add(jungle);
		jungleTempleBiomes.add(rainforest);
		
		desertTempleBiomes.add(desert);
		desertTempleBiomes.add(lushDesert);
		desertTempleBiomes.add(steppe);
		
		witchHutBiomes.add(mangroveSwamp);
		witchHutBiomes.add(fungalForest);
		witchHutBiomes.add(mysticForest);
		
		//Hill variants
		pumpkinBiomes.add(savannaHills);
		pumpkinBiomes.add(chaparralHills);
		
		reedBiomes.add(jungleHills);
		reedBiomes.add(fungalForestHills);
		
		villageBiomes.add(savannaHills);
		villageBiomes.add(desertHills);
		villageBiomes.add(woodedSteppe);
		villageBiomes.add(chaparralHills);

		jungleTempleBiomes.add(jungleHills);

		desertTempleBiomes.add(desertHills);
		desertTempleBiomes.add(woodedSteppe);

		witchHutBiomes.add(fungalForestHills);
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
        else  if (baseBiome == mangroveSwamp.biomeID) {
        	hillsBiome = mangroveWetlands.biomeID;
        }
		
		return hillsBiome;
	}
	
	public static int getRiverVariantForBiomes(int baseBiome) {
		int riverBiome = -1;
		
		if (BiomeGenBase.biomeList[baseBiome].getEnableSnow()) {
			riverBiome = BiomeGenBase.frozenRiver.biomeID;
		}
		else if (baseBiome == desert.biomeID || baseBiome == desertHills.biomeID) {
			riverBiome = riverDesert.biomeID;
		}
		else if (baseBiome == mysticForest.biomeID) {
			riverBiome = riverMystic.biomeID;
		}
		else if (baseBiome == rainforest.biomeID) {
			riverBiome = riverRainforest.biomeID;
		}
		
		return riverBiome;
	}
	
	public static ArrayList<BTWGBiomeGenBase> getBiomes() {
		return biomeList;
	}
	
	public static boolean canBiomeSpawnPumpkin(BiomeGenBase biome) {
		return pumpkinBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnReeds(BiomeGenBase biome) {
		return reedBiomes.contains(biome);
	}
	
	public static boolean canBiomeSpawnVillage(BiomeGenBase biome) {
		return villageBiomes.contains(biome);
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
}