package betterterrain;

import btw.AddonHandler;
import btw.BTWAddon;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.world.config.AddonConfigurationInfo;

public abstract class BTAAddon extends BTWAddon {
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
		for (BTWAddon mod : AddonHandler.modList.values()) {
			if (mod instanceof BTAAddon && ((BTAAddon) mod).internalName.equals(name)) {
				return (BTAAddon) mod;
			}
		}
		
		return null;
	}
}