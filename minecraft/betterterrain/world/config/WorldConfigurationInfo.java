package betterterrain.world.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import betterterrain.BTAVersion;
import betterterrain.BTAAddon;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.biome.BiomeInfo;
import betterterrain.world.generate.TerrainGenerator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.FCAddOn;
import net.minecraft.src.FCAddOnHandler;

public class WorldConfigurationInfo {
	private ArrayList<BiomeInfo> biomeInfoList = new ArrayList();
	private ArrayList<BTABiome> biomesForGeneration = new ArrayList();

	private BTAVersion btaVersion = BTAVersion.V1_1_3;
	private int oceanSize = 10;
	private boolean generatePerlinBeaches = false;
	private boolean climatized = false;
	private int biomeSize = 2;
	private TerrainGenerator generator = TerrainGenerator.CLASSIC;
	
	private Map<BTAAddon, AddonConfigurationInfo> addonInfoList = new HashMap();

	public static WorldConfigurationInfo createDefaultConfiguration(boolean isDeco) {
		WorldConfigurationInfo info = new WorldConfigurationInfo();

		info.setBTAVersion(BTAMod.getInstance().currentVersion);
		info.setOceanSize(5);
		info.setGeneratePerlinBeaches(true);
		info.setClimatized(true);
		info.setBiomeSize(1);
		info.setGenerator(TerrainGenerator.CLASSIC);

		info.generateBiomeInfoListFromBiomes(BiomeConfiguration.getBiomeList(), true, false);

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
		
		for (FCAddOn mod : FCAddOnHandler.m_ModList) {
			if (mod instanceof BTAAddon) {
				BTAAddon addon = (BTAAddon) mod;
				AddonConfigurationInfo addonInfo = addon.createDefaultConfigInfo();
				info.addonInfoList.put(addon, addonInfo);
			}
		}

		return info;
	}

	public static WorldConfigurationInfo createInfoFromString(String infoString) {
		WorldConfigurationInfo info;
		
		if (!infoString.startsWith("{")) {
			info = WorldConfigurationInfoLegacy.createInfoFromString(infoString);
		}
		else {
			info = new WorldConfigurationInfo();
			info.setInfoFromString(infoString);
		}
		
		return info;
	}

	private void setInfoFromString(String infoString) {
		JsonObject root = JsonParser.parseString(infoString).getAsJsonObject();
		btaVersion = BTAVersion.fromString(root.get("version").getAsString());
		
		JsonObject globalSettings = root.get("global_settings").getAsJsonObject();
		oceanSize = globalSettings.get("ocean_size").getAsInt();
		generatePerlinBeaches = globalSettings.get("better_beaches").getAsBoolean();
		climatized = globalSettings.get("climates").getAsBoolean();
		biomeSize = globalSettings.get("biome_size").getAsInt();
		generator = TerrainGenerator.fromName(globalSettings.get("generator").getAsString());
		
		JsonArray biomes = globalSettings.get("biomes").getAsJsonArray();
		
		for (JsonElement biome : biomes) {
			BiomeInfo biomeInfo = BiomeConfiguration.getBiomeInfoMap().get(BTABiome.getIDFromInternalName(biome.getAsString()));
			biomeInfo.setEnabled(true);

			this.biomeInfoList.add(biomeInfo);
		}
		
		this.setBiomesForGenerationFromInfo(this.biomeInfoList);
		
		JsonArray addons = root.get("addons").getAsJsonArray();
		
		for (JsonElement element : addons) {
			JsonObject addonObject = element.getAsJsonObject();
			
			BTAAddon addon = (BTAAddon) FCAddOnHandler.getModByName(addonObject.get("name").getAsString());
			AddonConfigurationInfo addonInfo = addon.createConfigInfo();
			addonInfo.parseJsonObject(addonObject);
			
			addonInfoList.put(addon, addonInfo);
		}
	}

	public String toString() {
		JsonObject root = new JsonObject();
		root.addProperty("version", btaVersion.toString());
		
		JsonObject globalSettings = new JsonObject();
		globalSettings.addProperty("ocean_size", oceanSize);
		globalSettings.addProperty("better_beaches", generatePerlinBeaches);
		globalSettings.addProperty("climates", climatized);
		globalSettings.addProperty("biome_size", biomeSize);
		globalSettings.addProperty("generator", generator.name);
		
		JsonArray biomes = new JsonArray();
		
		for (BiomeInfo biomeInfo : this.biomeInfoList) {
			if (biomeInfo.getEnabled()) {
				biomes.add(((BTABiome) BiomeGenBase.biomeList[biomeInfo.getID()]).getInternalName());
			}
		}
		
		globalSettings.add("biomes", biomes);
		
		root.add("global_settings", globalSettings);
		
		JsonArray addons = new JsonArray();
		
		for (AddonConfigurationInfo addonInfo : addonInfoList.values()) {
			addons.add(addonInfo.toJsonObject());
		}
		
		root.add("addons", addons);
		
		return root.toString();
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
	
	public static interface Condition {
		public boolean satisfiesContraints(WorldConfigurationInfo info);
	}
}