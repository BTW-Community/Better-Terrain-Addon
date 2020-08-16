package net.minecraft.src;

import java.util.ArrayList;

public class BTWGBiomeConfiguration {
	public static final BiomeGenBase forest = BiomeGenBase.forest;
	
	public static ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
	
	public static void init() {
		addBiomesToList();
	}
	
	public static void addBiomesToList() {
		biomeList.add(forest);
	}
	
	public static ArrayList<BiomeGenBase> getBiomes() {
		return biomeList;
	}
}