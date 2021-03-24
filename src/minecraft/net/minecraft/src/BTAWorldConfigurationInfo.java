package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BTAWorldConfigurationInfo {
	private ArrayList<BTABiomeGenBase> biomesForGeneration;
	
	private boolean compatMode = false;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = true;

	public static BTAWorldConfigurationInfo createDefaultConfiguration(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();
		
		if (isDeco)
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeList);
		else
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeListDeco);
		info.setGenerateOceans(10);
		info.setGeneratePerlinBeaches(true);
		
		return info;
	}

	public static BTAWorldConfigurationInfo createDefaultConfigurationLegacy(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();
		
		if (isDeco)
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeListCompat);
		else
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeListDecoCompat);
		info.setGenerateOceans(10);
		info.setGeneratePerlinBeaches(false);
		
		return info;
	}
	
	public static BTAWorldConfigurationInfo createInfoFromString(String infoString) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();
		info.setInfoFromString(infoString);
		return info;
	}
	
	public void setInfoFromString(String infoString) {
		String[] infoSplit = infoString.split("; ");
		String[] biomeSplit = infoSplit[0].split(" ");
		
		for (String s : biomeSplit) {
			this.biomesForGeneration.add((BTABiomeGenBase) BiomeGenBase.biomeList[Integer.parseInt(s)]);
		}
		
		for (int i = 1; i < infoSplit.length; i++) {
			if (i == 1) this.compatMode = false;
			if (i == 2) this.oceanSize = Integer.parseInt(infoSplit[i]);
			if (i == 3) this.generatePerlinBeaches = Boolean.parseBoolean(infoSplit[i]);
		}
	}
	
	public String toString() {
		String out = "";
		
		for (BiomeGenBase b : this.biomesForGeneration) {
			out += b.biomeID + " ";
		}
		
		out += "; ";
		out += compatMode + "; ";
		out += oceanSize + "; ";
		out += generatePerlinBeaches;
		
		return out;
	}

	public ArrayList<BTABiomeGenBase> getBiomesForGeneration() {
		return biomesForGeneration;
	}
	
	public BTAWorldConfigurationInfo setBiomesForGeneration(ArrayList<BTABiomeGenBase> biomesForGeneration) {
		this.biomesForGeneration = biomesForGeneration;
		return this;
	}
	
	public boolean isCompatMode() {
		return compatMode;
	}

	public void setCompatMode(boolean compatMode) {
		this.compatMode = compatMode;
	}

	public int generateOceans() {
		return oceanSize;
	}

	public BTAWorldConfigurationInfo setGenerateOceans(int oceanSize) {
		this.oceanSize = oceanSize;
		return this;
	}

	public boolean generatePerlinBeaches() {
		return generatePerlinBeaches;
	}

	public BTAWorldConfigurationInfo setGeneratePerlinBeaches(boolean generateBeaches) {
		this.generatePerlinBeaches = generateBeaches;
		return this;
	}

	public List theBiomesList() {
		// TODO Auto-generated method stub
		return null;
	}
}
