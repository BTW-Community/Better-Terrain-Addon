package betterterrain.world.config;

import java.util.Map;

import com.google.gson.JsonObject;

import betterterrain.AddonVersion;
import betterterrain.BTAAddon;

public class AddonConfigurationInfo {
	private String name;
	private AddonVersion version;
	
	public final JsonObject toJsonObject() {
		JsonObject root = new JsonObject();
		
		root.addProperty("name", name);
		root.addProperty("version", version.toString());
		root.add("settings", exportSettings());
		
		return root;
	}
	
	public final void parseJsonObject(JsonObject json) {
		this.name = json.get("name").getAsString();
		this.version = AddonVersion.fromString(json.get("version").getAsString(), BTAAddon.getAddonByInternalName(this.name));
		
		this.parseConfigSettings(json.get("settings").getAsJsonObject());
	}
	
	/**
	 * Should be overriden to store additional addon-specific world generation settings
	 */
	public JsonObject exportSettings() {
		return new JsonObject();
	}
	
	/**
	 * Should be overriden to parse additional addon-specific world generation settings
	 */
	public void parseConfigSettings(JsonObject settings) {}
	
	public AddonVersion getVersion() {
		return version;
	}
	
	public void setVersion(AddonVersion version) {
		this.version = version;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
