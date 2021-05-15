package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BTAWorldConfigurationInfo {
	private ArrayList<BTABiomeInfo> biomeInfoList = new ArrayList();
	private ArrayList<BTABiomeGenBase> biomesForGeneration = new ArrayList();

	private BTAEnumVersionCompat compatMode = BTAEnumVersionCompat.V1_1_3;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = false;
	private boolean climatized = false;
	private int biomeSize = 2;
	private BTAEnumTerrainGenerator generator = BTAEnumTerrainGenerator.CLASSIC;

	public static BTAWorldConfigurationInfo createDefaultConfiguration(boolean isDeco) {
		BTAWorldConfigurationInfo info = new BTAWorldConfigurationInfo();

		info.setCompatMode(BTAMod.getInstance().currentVersion);
		info.setOceanSize(5);
		info.setGeneratePerlinBeaches(true);
		info.setClimatized(true);
		info.setBiomeSize(1);
		info.setGenerator(BTAEnumTerrainGenerator.CLASSIC);

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
		info.setBiomeSize(2);
		info.setGenerator(BTAEnumTerrainGenerator.CLASSIC);

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
		if (!infoString.contains(":")) {
			this.setInfoFromStringLegacy(infoString);
		}
		else {
			//Ignores whitespace
			infoString = infoString.replace(" ", "");
			
			String[] infoSplit = infoString.split(";");
			
			for (String option : infoSplit) {
				String[] optionSplit = option.split(":");
				
				if (optionSplit[0].equalsIgnoreCase("Version")) {
					this.compatMode = BTAEnumVersionCompat.fromString(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("Biomes")){
					String[] biomeSplit = optionSplit[1].split(",");

					this.biomeInfoList.clear();

					for (String s : biomeSplit) {
						String[] biomeInfoSplit = s.split("=");
						
						BTABiomeInfo biomeInfo = BTABiomeConfiguration.biomeInfoMap.get(Integer.parseInt(biomeInfoSplit[0]));
						biomeInfo.setEnabled(Boolean.parseBoolean(biomeInfoSplit[1]));

						this.biomeInfoList.add(biomeInfo);
					}

					this.setBiomesForGenerationFromInfo(this.biomeInfoList);
				}
				else if (optionSplit[0].equalsIgnoreCase("OceanSize")) {
					this.oceanSize = Integer.parseInt(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("Shorelines")) {
					this.generatePerlinBeaches = Boolean.parseBoolean(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("BiomeSize")) {
					this.biomeSize = Integer.parseInt(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("Climates")) {
					this.climatized = Boolean.parseBoolean(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("Generator")) {
					this.generator = BTAEnumTerrainGenerator.fromId(Integer.parseInt(optionSplit[1]));
				}
				else {
					throw new IllegalArgumentException("Invalid format for generator options");
				}
			}
		}
	}
	
	private void setInfoFromStringLegacy(String infoString) {
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
				if (infoSplit[i].equals("true")) {
					this.compatMode = BTAEnumVersionCompat.V1_1_3;
				}
				else if (infoSplit[i].equals("false")) {
					this.compatMode = BTAEnumVersionCompat.V1_2_0;
				}
				else {
					this.compatMode = BTAEnumVersionCompat.fromString(infoSplit[i]);
				}
			}
			if (i == 2) this.oceanSize = Integer.parseInt(infoSplit[i]);
			if (i == 3) this.generatePerlinBeaches = Boolean.parseBoolean(infoSplit[i]);
		}
		
		this.climatized = false;
		this.biomeSize = 2;

		this.setBiomesForGenerationFromInfo(this.biomeInfoList);
	}

	public String toString() {
		String out = "Version:";
		out += this.compatMode.toString() + ";";

		out += "Biomes:";
		for (BTABiomeInfo b : this.biomeInfoList) {
			out += b.toString() + ",";
		}
		out += ";";
		
		out += "OceanSize:" + oceanSize + ";";
		out += "Shorelines:" + generatePerlinBeaches + ";";
		out += "BiomeSize:" + biomeSize + ";";
		out += "Climates:" + climatized + ";";
		out += "Generator:" + generator.id;

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

	public int getBiomeSize() {
		return biomeSize;
	}

	public void setBiomeSize(int biomeSize) {
		this.biomeSize = biomeSize;
	}

	public BTAEnumTerrainGenerator getGenerator() {
		return generator;
	}

	public BTAWorldConfigurationInfo setGenerator(BTAEnumTerrainGenerator generator) {
		this.generator = generator;
		return this;
	}
}
