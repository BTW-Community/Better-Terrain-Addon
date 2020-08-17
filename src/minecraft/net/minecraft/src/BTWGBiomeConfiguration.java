package net.minecraft.src;

import java.util.ArrayList;

public class BTWGBiomeConfiguration {
	public static final BTWGBiomeGenBase woods = new BTWGBiomeGenWoods(100).setColor(353825).setBiomeName("Woods").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase desert = new BTWGBiomeGenDesert(101).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
	public static final BTWGBiomeGenBase lushDesert = new BTWGBiomeGenLushDesert(102).setColor(16711935).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.3F, 0.6F);
	public static final BTWGBiomeGenBase oasis = new BTWGBiomeGenOasis(103).setColor(16711935).setBiomeName("Oasis").setDisableRain().setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-0.2F, 0.1F); //Spawns within lush deserts
	public static final BTWGBiomeGenBase savanna = new BTWGBiomeGenSavanna(104).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.6F);
    public static final BTWGBiomeGenBase mangroveSwamp = new BTWGBiomeGenMangroveSwamp(105).setColor(522674).setBiomeName("Mangrove Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.2F).setTemperatureRainfall(0.8F, 0.9F);
	public static final BTWGBiomeGenBase birchForest = new BTWGBiomeGenBirchForest(106).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase snowyWoods = new BTWGBiomeGenWoods(107).setColor(353825).setBiomeName("Snowy Woods").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.1F, 0.5F);
	public static final BTWGBiomeGenBase steppe = new BTWGBiomeGenSteppe(108).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.5F);
	public static final BTWGBiomeGenBase woodedSteppe = new BTWGBiomeGenWoodedSteppe(109).setColor(9286496).setBiomeName("Wooded Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.5F); //Spawns within steppe

	//Hill variants - spawn within the normal variants
	public static final BTWGBiomeGenBase woodsHills = new BTWGBiomeGenWoods(150).setColor(353825).setBiomeName("Woods Hills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTWGBiomeGenBase desertHills = new BTWGBiomeGenDesert(151).setColor(16421912).setBiomeName("Desert Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase savannaHills = new BTWGBiomeGenSavanna(152).setColor(9286496).setBiomeName("Savanna Hills").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 1.0F);
	public static final BTWGBiomeGenBase birchForestHills = new BTWGBiomeGenBirchForest(153).setColor(353825).setBiomeName("Birch Forest Hills").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.3F, 1.5F);
	public static final BTWGBiomeGenBase snowyWoodsHills = new BTWGBiomeGenWoods(154).setColor(353825).setBiomeName("Snowy Woods Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.3F, 1.5F);
	
	//Mountain variants
	public static final BTWGBiomeGenBase woodsM = new BTWGBiomeGenWoods(200).setColor(353825).setBiomeName("Woods M").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 3.0F);
	public static final BTWGBiomeGenBase savannaM = new BTWGBiomeGenSavanna(201).setColor(9286496).setBiomeName("Savanna M").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.2F, 3.0F);
	public static final BTWGBiomeGenBase birchForestM = new BTWGBiomeGenBirchForest(202).setColor(353825).setBiomeName("Birch Forest M").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.4F).setMinMaxHeight(0.2F, 3.0F);
	public static final BTWGBiomeGenBase snowyWoodsM = new BTWGBiomeGenWoods(203).setColor(353825).setBiomeName("Snowy Woods M").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.1F, 0.1F).setMinMaxHeight(0.2F, 3.0F);
	
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
		biomeList.add(steppe);
	}
	
	public static void addBiomesToList() {
		biomeList.add(woods);
		biomeList.add(desert);
		biomeList.add(lushDesert);
		biomeList.add(savanna);
		biomeList.add(mangroveSwamp);
		biomeList.add(birchForest);
		biomeList.add(snowyWoods);
		
		//Mountain variants
		biomeList.add(woodsM);
		biomeList.add(savannaM);
		biomeList.add(birchForestM);
		biomeList.add(snowyWoodsM);
	}
	
	public static void filterSpawnBiomes() {
		desert.setNotSpawnable();
		desertHills.setNotSpawnable();
		lushDesert.setNotSpawnable();
		steppe.setNotSpawnable();
		woodedSteppe.setNotSpawnable();
	}
	
	public static void addBiomesToStructureGenerators() {
		pumpkinBiomes.add(savanna);
		pumpkinBiomes.add(savannaM);
		
		villageBiomes.add(savanna);
		villageBiomes.add(savannaM);
		villageBiomes.add(desert);
		villageBiomes.add(lushDesert);
		villageBiomes.add(steppe);
		
		desertTempleBiomes.add(desert);
		desertTempleBiomes.add(lushDesert);
		desertTempleBiomes.add(steppe);
		
		witchHutBiomes.add(mangroveSwamp);
		
		//Hill variants
		pumpkinBiomes.add(savannaHills);
		
		villageBiomes.add(savannaHills);
		villageBiomes.add(desertHills);
		villageBiomes.add(woodedSteppe);

		desertTempleBiomes.add(desertHills);
		desertTempleBiomes.add(woodedSteppe);
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
		//Mountain variants
        else if (baseBiome == woodsM.biomeID) {
        	hillsBiome = woods.biomeID;
        }
        else if (baseBiome == savannaM.biomeID) {
        	hillsBiome = savanna.biomeID;
        }
        else if (baseBiome == birchForestM.biomeID) {
        	hillsBiome = birchForest.biomeID;
        }
        else if (baseBiome == snowyWoodsM.biomeID) {
        	hillsBiome = snowyWoods.biomeID;
        }
		
		return hillsBiome;
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