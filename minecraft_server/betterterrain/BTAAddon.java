package betterterrain;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonObject;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.world.config.AddonConfigurationInfo;
import net.minecraft.src.FCAddOn;
import net.minecraft.src.FCAddOnHandler;

public abstract class BTAAddon extends FCAddOn {
	public final String internalName;
	
	public AddonVersion currentVersion;
	
	public BTAAddon(String addonName, String internalName, String version, String prefix) {
		super(addonName, version, prefix);
		this.internalName = internalName;
	}
	
	//------ World Configuration Functionality ------//
	
	public abstract BiomeConfiguration getBiomeConfiguration();
	
	public final AddonConfigurationInfo createDefaultConfigInfo() {
		AddonConfigurationInfo info = createConfigInfo();
		
		info.setName(this.internalName);
		info.setVersion(this.currentVersion);
		
		this.setDefaultSettings(info);
		
		return info;
	}
	
	public AddonConfigurationInfo createConfigInfo() {
		return new AddonConfigurationInfo();
	}
	
	public void setDefaultSettings(AddonConfigurationInfo info) {}
	
	//------ Other Functionality ------//
	
	public static BTAAddon getAddonByInternalName(String name) {
		for (FCAddOn mod : FCAddOnHandler.m_ModList.values()) {
			if (mod instanceof BTAAddon && ((BTAAddon) mod).internalName.equals(name)) {
				return (BTAAddon) mod;
			}
		}
		
		return null;
	}
}