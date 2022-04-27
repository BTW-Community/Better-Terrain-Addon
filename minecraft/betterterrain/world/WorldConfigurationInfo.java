package betterterrain.world;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.BTAVersion;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeInfo;
import betterterrain.world.generate.TerrainGenerator;
import net.minecraft.src.BiomeGenBase;

public class WorldConfigurationInfo {
	private ArrayList<BiomeInfo> biomeInfoList = new ArrayList();
	private ArrayList<BTABiome> biomesForGeneration = new ArrayList();

	private BTAVersion btaVersion = BTAVersion.V1_1_3;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = false;
	private boolean climatized = false;
	private int biomeSize = 2;
	private TerrainGenerator generator = TerrainGenerator.CLASSIC;

	public static WorldConfigurationInfo createDefaultConfiguration(boolean isDeco) {
		WorldConfigurationInfo info = new WorldConfigurationInfo();

		info.setBTAVersion(BTAMod.getInstance().currentVersion);
		info.setOceanSize(5);
		info.setGeneratePerlinBeaches(true);
		info.setClimatized(true);
		info.setBiomeSize(1);
		info.setGenerator(TerrainGenerator.CLASSIC);

		info.generateBiomeInfoListFromBiomes(BetterBiomesConfiguration.biomeListDeco);

		if (!isDeco) {
			for (BiomeInfo b : info.getBiomeInfoList()) {
				if (b.isDecoOnly()) {
					b.setEnabled(false);
				}
			}
		}

		for (BiomeInfo b : info.biomeInfoList) {
			if (b.getEnabled()) {
				info.biomesForGeneration.add((BTABiome) BiomeGenBase.biomeList[b.getID()]);
			}
		}

		info.setBiomesForGenerationFromInfo(info.biomeInfoList);

		return info;
	}

	public static WorldConfigurationInfo createDefaultConfigurationLegacy(boolean isDeco) {
		WorldConfigurationInfo info = new WorldConfigurationInfo();

		info.setBTAVersion(BTAVersion.V1_1_3);
		info.setOceanSize(10);
		info.setGeneratePerlinBeaches(false);
		info.setClimatized(false);
		info.setBiomeSize(2);
		info.setGenerator(TerrainGenerator.CLASSIC);

		info.generateBiomeInfoListFromBiomes(BetterBiomesConfiguration.biomeListDecoCompat);

		if (!isDeco) {
			for (BiomeInfo b : info.getBiomeInfoList()) {
				if (b.isDecoOnly()) {
					b.setEnabled(false);
				}
			}
		}

		info.setBiomesForGenerationFromInfo(info.biomeInfoList);

		return info;
	}

	public static WorldConfigurationInfo createInfoFromString(String infoString) {
		WorldConfigurationInfo info = new WorldConfigurationInfo();
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
					this.btaVersion = BTAVersion.fromString(optionSplit[1]);
				}
				else if (optionSplit[0].equalsIgnoreCase("Biomes")){
					String[] biomeSplit = optionSplit[1].split(",");

					this.biomeInfoList.clear();

					for (String s : biomeSplit) {
						String[] biomeInfoSplit = s.split("=");
						
						BiomeInfo biomeInfo = BetterBiomesConfiguration.biomeInfoMap.get(Integer.parseInt(biomeInfoSplit[0]));
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
	
	private void setInfoFromStringLegacy(String infoString) {
		String[] infoSplit = infoString.split("; ");
		String[] biomeSplit = infoSplit[0].split(" ");

		for (String s : biomeSplit) {
			String[] biomeInfoSplit = s.split("=");

			BiomeInfo biomeInfo = BetterBiomesConfiguration.biomeInfoMap.get(Integer.parseInt(biomeInfoSplit[0]));
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

	public void generateBiomeInfoListFromBiomes(ArrayList<BTABiome> biomeList) {
		for (BTABiome b : biomeList) {
			this.biomeInfoList.add(BetterBiomesConfiguration.biomeInfoMap.get(b.biomeID).copy().setEnabled(true));
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

	public WorldConfigurationInfo setBiomesForGeneration(ArrayList<BTABiome> biomesForGeneration) {
		this.biomesForGeneration = biomesForGeneration;
		return this;
	}

	public WorldConfigurationInfo setBiomesForGenerationFromInfo(ArrayList<BiomeInfo> biomeInfoListToUse) {
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

	public WorldConfigurationInfo setOceanSize(int oceanSize) {
		this.oceanSize = oceanSize;
		return this;
	}

	public boolean generatePerlinBeaches() {
		return generatePerlinBeaches;
	}

	public WorldConfigurationInfo setGeneratePerlinBeaches(boolean generateBeaches) {
		this.generatePerlinBeaches = generateBeaches;
		return this;
	}

	public boolean isClimatized() {
		return climatized;
	}

	public WorldConfigurationInfo setClimatized(boolean climatized) {
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

	public WorldConfigurationInfo setGenerator(TerrainGenerator generator) {
		this.generator = generator;
		return this;
	}
}
