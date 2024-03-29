package betterterrain.biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import betterterrain.BTAAddon;
import betterterrain.biome.layer.HillsLayer;
import betterterrain.biome.layer.RiverLayer;
import betterterrain.biome.layer.ShoreLayer;
import betterterrain.biome.layer.SporadicLayer;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.BiomeGenBase;

public abstract class BiomeConfiguration {
	public static void init() {
		BTABiomeConfiguration btaConfig = BTABiomeConfiguration.getInstance();
		btaConfig.addBiomesToList(biomeList);
		btaConfig.setBiomeVariants();
		
		for (BTWAddon mod : AddonHandler.modList.values()) {
			if (mod instanceof BTAAddon) {
				BTAAddon addon = (BTAAddon) mod;
				BiomeConfiguration biomeConfig = addon.getBiomeConfiguration();
				biomeConfig.addBiomesToList(biomeList);
				biomeConfig.setBiomeVariants();
			}
		}
		
		initClimateLists();
		initBiomeInfoList();
	}
	
	//------ API Funcionality ------//
	
	/**
	 * Done separate from biome constructor to allow for initialization order separate from definition order
	 * @param biomeList
	 */
	public abstract void addBiomesToList(ArrayList<BTABiome> biomeList);
	
	/**
	 * Used to define rivers, edges, hills, etc
	 * Must be done separately from biome definition to prevent definition order from affecting things
	 */
	public abstract void setBiomeVariants();
	
	//------ Biome Functionality ------//
	
	private static ArrayList<BTABiome> biomeList = new ArrayList();

	private static Map<Integer, BiomeInfo> biomeInfoMap = new HashMap();

	public static void initBiomeInfoList() {
		for (BTABiome b : biomeList) {
			if (b.isDecoOnly())
				biomeInfoMap.put(b.biomeID, new BiomeInfo(b.biomeID, true, true));
			else
				biomeInfoMap.put(b.biomeID, new BiomeInfo(b.biomeID, true));
		}
	}
	
	public static ArrayList<BTABiome> getBiomeList() {
		return biomeList;
	}

	public static Map<Integer, BiomeInfo> getBiomeInfoMap() {
		return biomeInfoMap;
	}

	public static boolean canBiomeSpawnStronghold(BiomeGenBase biome) {
		if (biome != BiomeGenBase.ocean && biome != BiomeGenBase.frozenOcean) {
			return false;
		}
		else if (biome instanceof BTABiome && !((BTABiome) biome).canSpawnStronghold()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static int getSubVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, HillsLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getSubVariant(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static int getSubVariantForBiomesCommon(int baseBiome, WorldConfigurationInfo generatorOptions, HillsLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getSubVariantCommon(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static int getSporadicVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, SporadicLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getSporadicVariant(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static int getSporadicChanceForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, SporadicLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getSporadicChance(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static int getBeachVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getBeachVariant(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static boolean shouldBiomeSpawnBeach(int baseBiome, WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.hasBeach(generatorOptions, layer);
		}
		else {
			return true;
		}
	}
	
	public static int getRiverVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, RiverLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getRiverVariant(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static int getEdgeVariantForBiomes(int baseBiome, WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.getEdgeVariant(generatorOptions, layer);
		}
		else {
			return baseBiome;
		}
	}
	
	public static boolean shouldBiomeConnectWithEdge(int baseBiome, WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		BiomeGenBase b = BiomeGenBase.biomeList[baseBiome];
		
		if (b instanceof BTABiome) {
			BTABiome biome = (BTABiome) b;
			
			return biome.shouldConnectWithEdge(generatorOptions, layer);
		}
		else {
			return true;
		}
	}
	
	//------ Climate functionality ------//

	public static ArrayList<BTABiome> snowyList = new ArrayList();
	public static ArrayList<BTABiome> coldList = new ArrayList();
	public static ArrayList<BTABiome> temperateList = new ArrayList();
	public static ArrayList<BTABiome> tropicalList = new ArrayList();
	public static ArrayList<BTABiome> aridList = new ArrayList();
	public static Map<Climate, ArrayList<BTABiome>> biomeCategoryMap = new HashMap();

	public static ArrayList<BTABiome> getClimateListForGenerator(Climate climate, ArrayList<BTABiome> biomesForGeneration) {
		ArrayList<BTABiome> newClimateList = new ArrayList<BTABiome>();

		for (BTABiome b : biomeCategoryMap.get(climate)) {
			if (biomesForGeneration.contains(b)) {
				newClimateList.add(b);
			}
		}

		return newClimateList;
	}

	public static void initClimateLists() {
		biomeCategoryMap.put(Climate.SNOWY, snowyList);
		biomeCategoryMap.put(Climate.COLD, coldList);
		biomeCategoryMap.put(Climate.TEMPERATE, temperateList);
		biomeCategoryMap.put(Climate.TROPICAL, tropicalList);
		biomeCategoryMap.put(Climate.ARID, aridList);

		for (BTABiome b : biomeList) {
			biomeCategoryMap.get(b.climate).add(b);
		}
	}
}
