package betterterrain.biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BiomeConfiguration {
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
}
