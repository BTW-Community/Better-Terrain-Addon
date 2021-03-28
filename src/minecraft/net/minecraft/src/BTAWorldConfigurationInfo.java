package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BTAWorldConfigurationInfo {
	private ArrayList<BTABiomeInfo> biomeInfoList = new ArrayList();
	private ArrayList<BTABiomeGenBase> biomesForGeneration = new ArrayList();

	private BTAEnumVersionCompat compatMode = BTAEnumVersionCompat.V1_1_3;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = true;
	private boolean climatized = false;

	public static BTAWorldConfigurationInfo createDefaultConfiguration(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();

		info.setCompatMode(BTAMod.getInstance().currentVersion);
		info.setOceanSize(10);
		info.setGeneratePerlinBeaches(true);
		info.setClimatized(true);

		info.generateBiomeInfoListFromBiomes(BTABiomeConfiguration.biomeListDeco);

		if (!isDeco) {
			for (BTABiomeInfo b : info.getBiomeInfoList()) {
				if (b.isDecoOnly()) {
					b.setEnabled(false);
				}
			}
		}

		for (BTABiomeInfo b : info.biomeInfoList) {
			if (b.getEnabled()) {
				info.biomesForGeneration.add((BTABiomeGenBase) BiomeGenBase.biomeList[b.getID()]);
			}
		}

		info.setBiomesForGenerationFromInfo(info.biomeInfoList);

		return info;
	}

	public static BTAWorldConfigurationInfo createDefaultConfigurationLegacy(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();

		info.setCompatMode(BTAEnumVersionCompat.V1_1_3);
		info.setOceanSize(10);
		info.setGeneratePerlinBeaches(false);
		info.setClimatized(false);

		info.generateBiomeInfoListFromBiomes(BTABiomeConfiguration.biomeListDecoCompat);

		if (!isDeco) {
			for (BTABiomeInfo b : info.getBiomeInfoList()) {
				if (b.isDecoOnly()) {
					b.setEnabled(false);
				}
			}
		}

		info.setBiomesForGenerationFromInfo(info.biomeInfoList);

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
			if (i == 1) {
				//Done this way for backwards compatibility with older standard
				if (infoSplit[1].equals("true")) {
					this.compatMode = BTAEnumVersionCompat.V1_1_3;
				}
				else if (infoSplit[1].equals("false")) {
					this.compatMode = BTAEnumVersionCompat.V1_2_0;
				}
				else {
					this.compatMode = BTAEnumVersionCompat.fromString(infoSplit[i]);
				}
			}
			if (i == 2) this.oceanSize = Integer.parseInt(infoSplit[i]);
			if (i == 3) this.generatePerlinBeaches = Boolean.parseBoolean(infoSplit[i]);
			if (i == 4) this.climatized = Boolean.parseBoolean(infoSplit[i]);
		}

		this.setBiomesForGenerationFromInfo(this.biomeInfoList);
	}

	public String toString() {
		String out = "";

		for (BTABiomeInfo b : this.biomeInfoList) {
			out += b.toString() + " ";
		}

		out += "; ";
		out += compatMode + "; ";
		out += oceanSize + "; ";
		out += generatePerlinBeaches + "; ";
		out += climatized;

		return out;
	}

	public void generateBiomeInfoListFromBiomes(ArrayList<BTABiomeGenBase> biomeList) {
		for (BTABiomeGenBase b : biomeList) {
			this.biomeInfoList.add(BTABiomeConfiguration.biomeInfoMap.get(b.biomeID).copy().setEnabled(true));
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

	public BTAWorldConfigurationInfo setBiomesForGenerationFromInfo(ArrayList<BTABiomeInfo> biomeInfoListToUse) {
		if (this.biomesForGeneration == null)
			this.biomesForGeneration = new ArrayList();
		
		for (BTABiomeInfo b : biomeInfoListToUse) {
			if (b.getEnabled()) {
				this.biomesForGeneration.add((BTABiomeGenBase) BiomeGenBase.biomeList[b.getID()]);
			}
		}
		
		return this;
	}

	public BTAEnumVersionCompat getCompatMode() {
		return compatMode;
	}

	public void setCompatMode(BTAEnumVersionCompat compatMode) {
		this.compatMode = compatMode;
	}

	public int getOceanSize() {
		return oceanSize;
	}

	public BTAWorldConfigurationInfo setOceanSize(int oceanSize) {
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

	public boolean isClimatized() {
		return climatized;
	}

	public BTAWorldConfigurationInfo setClimatized(boolean climatized) {
		this.climatized = climatized;
		return this;
	}
}
