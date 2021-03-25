package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BTAWorldConfigurationInfo {
	private ArrayList<BTABiomeInfo> biomeInfoList = new ArrayList();
	private ArrayList<BTABiomeGenBase> biomesForGeneration = new ArrayList();
	
	private boolean compatMode = false;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = true;

	public static BTAWorldConfigurationInfo createDefaultConfiguration(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();
		
		if (isDeco)
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeList);
		else
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeListDeco);
		info.setCompatMode(false);
		info.setGenerateOceans(10);
		info.setGeneratePerlinBeaches(true);

		info.generateBiomeInfoListFromBiomes(BTABiomeConfiguration.biomeListDeco);
		
		return info;
	}

	public static BTAWorldConfigurationInfo createDefaultConfigurationLegacy(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();
		
		if (isDeco)
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeListCompat);
		else
			info.setBiomesForGeneration(BTABiomeConfiguration.biomeListDecoCompat);
		info.setCompatMode(true);
		info.setGenerateOceans(10);
		info.setGeneratePerlinBeaches(false);
		
		info.generateBiomeInfoListFromBiomes(BTABiomeConfiguration.biomeListDecoCompat);
		
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
			String[] biomeInfoSplit = s.split("=");
			
			BTABiomeInfo biomeInfo = BTABiomeConfiguration.biomeInfoMap.get(Integer.parseInt(biomeInfoSplit[0]));
			biomeInfo.setEnabled(Boolean.parseBoolean(biomeInfoSplit[1]));
			
			this.biomeInfoList.add(biomeInfo);
		}
		
		for (int i = 1; i < infoSplit.length; i++) {
			if (i == 1) this.compatMode = Boolean.parseBoolean(infoSplit[i]);
			if (i == 2) this.oceanSize = Integer.parseInt(infoSplit[i]);
			if (i == 3) this.generatePerlinBeaches = Boolean.parseBoolean(infoSplit[i]);
		}
		
		for (BTABiomeInfo b : this.biomeInfoList) {
			if (b.getEnabled()) {
				this.biomesForGeneration.add((BTABiomeGenBase) BiomeGenBase.biomeList[b.getID()]);
			}
		}
	}
	
	public String toString() {
		String out = "";
		
		for (BTABiomeInfo b : this.biomeInfoList) {
			out += b.toString() + " ";
		}
		
		out += "; ";
		out += compatMode + "; ";
		out += oceanSize + "; ";
		out += generatePerlinBeaches;
		
		return out;
	}
	
	public void generateBiomeInfoListFromBiomes(ArrayList<BTABiomeGenBase> biomeList) {
		for (BTABiomeGenBase b : biomeList) {
			this.biomeInfoList.add(BTABiomeConfiguration.biomeInfoMap.get(b.biomeID));
		}
	}

	public ArrayList<BTABiomeInfo> getBiomeInfoList() {
		return biomeInfoList;
	}

	public void setBiomeInfoList(ArrayList<BTABiomeInfo> biomeInfoList) {
		this.biomeInfoList = biomeInfoList;
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
}
