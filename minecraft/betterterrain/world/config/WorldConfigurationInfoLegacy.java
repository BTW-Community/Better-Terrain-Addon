package betterterrain.world.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import betterterrain.BTAVersion;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.biome.BiomeInfo;
import betterterrain.world.generate.TerrainGenerator;
import net.minecraft.src.BiomeGenBase;

public class WorldConfigurationInfoLegacy {
	private ArrayList<BiomeInfo> biomeInfoList = new ArrayList();
	private ArrayList<BTABiome> biomesForGeneration = new ArrayList();

	private BTAVersion btaVersion = BTAVersion.V1_1_3;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = false;
	private boolean climatized = false;
	private int biomeSize = 2;
	private TerrainGenerator generator = TerrainGenerator.CLASSIC;

	/**
	 * Generates default configuration for worlds generated in BTA in 1.1.3 or earlier
	 */
	public static WorldConfigurationInfo createDefaultConfigurationLegacy(boolean isDeco) {
		WorldConfigurationInfo info = new WorldConfigurationInfo();

		info.setBTAVersion(BTAVersion.V1_1_3);
		info.setOceanSize(10);
		info.setGeneratePerlinBeaches(false);
		info.setClimatized(false);
		info.setBiomeSize(2);
		info.setGenerator(TerrainGenerator.CLASSIC);

		info.generateBiomeInfoListFromBiomes(BiomeConfiguration.getBiomeList(), true, true);

		if (!isDeco) {
			for (BiomeInfo b : info.getBiomeInfoList()) {
				if (b.isDecoOnly()) {
					b.setEnabled(false);
				}
			}
		}

		info.setBiomesForGenerationFromInfo(info.getBiomeInfoList());

		return info;
	}

	public static WorldConfigurationInfo createInfoFromLegacy(WorldConfigurationInfoLegacy legacyInfo) {
		WorldConfigurationInfo info = new WorldConfigurationInfo();

		info.setBTAVersion(legacyInfo.btaVersion);
		info.setOceanSize(legacyInfo.oceanSize);
		info.setGeneratePerlinBeaches(legacyInfo.generatePerlinBeaches);
		info.setClimatized(legacyInfo.climatized);
		info.setBiomeSize(legacyInfo.biomeSize);
		info.setGenerator(legacyInfo.generator);
		info.setBiomesForGenerationFromInfo(legacyInfo.getBiomeInfoList());

		return info;
	}

	public static WorldConfigurationInfo createInfoFromString(String infoString) {
		WorldConfigurationInfoLegacy legacyInfo = new WorldConfigurationInfoLegacy();
		legacyInfo.setInfoFromString(infoString);

		WorldConfigurationInfo info = createInfoFromLegacy(legacyInfo);
		return info;
	}

	private void setInfoFromString(String infoString) {
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
					this.btaVersion = BTAVersion.fromString(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("Biomes")){
					String[] biomeSplit = optionSplit[1].split(",");

					this.biomeInfoList.clear();

					for (String s : biomeSplit) {
						String[] biomeInfoSplit = s.split("=");

						BiomeInfo biomeInfo = BiomeConfiguration.getBiomeInfoMap().get(Integer.parseInt(biomeInfoSplit[0]));
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
					this.generator = TerrainGenerator.fromId(Integer.parseInt(optionSplit[1]));
				}
				else {
					throw new IllegalArgumentException("Invalid format for generator options");
				}
			}
		}
	}

	/**
	 * Reads info string from BTA version 1.2.0
	 */
	private void setInfoFromStringLegacy(String infoString) {
		String[] infoSplit = infoString.split("; ");
		String[] biomeSplit = infoSplit[0].split(" ");

		for (String s : biomeSplit) {
			String[] biomeInfoSplit = s.split("=");

			BiomeInfo biomeInfo = BiomeConfiguration.getBiomeInfoMap().get(Integer.parseInt(biomeInfoSplit[0]));
			biomeInfo.setEnabled(Boolean.parseBoolean(biomeInfoSplit[1]));

			this.biomeInfoList.add(biomeInfo);
		}

		for (int i = 1; i < infoSplit.length; i++) {
			if (i == 1) {
				//Done this way for backwards compatibility with older standard
				if (infoSplit[i].equals("true")) {
					this.btaVersion = BTAVersion.V1_1_3;
				}
				else if (infoSplit[i].equals("false")) {
					this.btaVersion = BTAVersion.V1_2_0;
				}
				else {
					this.btaVersion = BTAVersion.fromString(infoSplit[i]);
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
		out += this.btaVersion.toString() + ";";

		out += "Biomes:";
		for (BiomeInfo b : this.biomeInfoList) {
			out += b.toString() + ",";
		}
		out += ";";

		out += "OceanSize:" + oceanSize + ";";
		out += "Shorelines:" + generatePerlinBeaches + ";";
		out += "BiomeSize:" + biomeSize + ";";
		out += "Climates:" + climatized + ";";
		out += "Generator:" + generator.id + ";";

		return out;
	}

	public void generateBiomeInfoListFromBiomes(ArrayList<BTABiome> biomeList, boolean allowDeco, boolean legacyCompatibility) {
		for (BTABiome b : biomeList) {
			boolean addBiome = false;

			if (b.isDecoOnly()) {
				if (allowDeco) {
					addBiome = true;
				}
			}
			else if (b.isLegacyCompatible() || !legacyCompatibility) {
				addBiome = true;
			}

			if (addBiome) {
				this.biomeInfoList.add(BiomeConfiguration.getBiomeInfoMap().get(b.biomeID).copy().setEnabled(true));
			}
		}
	}

	public ArrayList<BiomeInfo> getBiomeInfoList() {
		return biomeInfoList;
	}

	public void setBiomeInfoList(ArrayList<BiomeInfo> biomeInfoList) {
		this.biomeInfoList = biomeInfoList;
	}

	public ArrayList<BTABiome> getBiomesForGeneration() {
		return biomesForGeneration;
	}

	public WorldConfigurationInfoLegacy setBiomesForGeneration(ArrayList<BTABiome> biomesForGeneration) {
		this.biomesForGeneration = biomesForGeneration;
		return this;
	}

	public WorldConfigurationInfoLegacy setBiomesForGenerationFromInfo(ArrayList<BiomeInfo> biomeInfoListToUse) {
		if (this.biomesForGeneration == null)
			this.biomesForGeneration = new ArrayList();

		for (BiomeInfo b : biomeInfoListToUse) {
			if (b.getEnabled()) {
				this.biomesForGeneration.add((BTABiome) BiomeGenBase.biomeList[b.getID()]);
			}
		}

		return this;
	}

	public BTAVersion getBTAVersion() {
		return btaVersion;
	}

	public void setBTAVersion(BTAVersion btaVersion) {
		this.btaVersion = btaVersion;
	}

	public int getOceanSize() {
		return oceanSize;
	}

	public WorldConfigurationInfoLegacy setOceanSize(int oceanSize) {
		this.oceanSize = oceanSize;
		return this;
	}

	public boolean generatePerlinBeaches() {
		return generatePerlinBeaches;
	}

	public WorldConfigurationInfoLegacy setGeneratePerlinBeaches(boolean generateBeaches) {
		this.generatePerlinBeaches = generateBeaches;
		return this;
	}

	public boolean isClimatized() {
		return climatized;
	}

	public WorldConfigurationInfoLegacy setClimatized(boolean climatized) {
		this.climatized = climatized;
		return this;
	}

	public int getBiomeSize() {
		return biomeSize;
	}

	public void setBiomeSize(int biomeSize) {
		this.biomeSize = biomeSize;
	}

	public TerrainGenerator getGenerator() {
		return generator;
	}

	public WorldConfigurationInfoLegacy setGenerator(TerrainGenerator generator) {
		this.generator = generator;
		return this;
	}

	public static interface Condition {
		public boolean satisfiesContraints(WorldConfigurationInfoLegacy info);
	}
}
