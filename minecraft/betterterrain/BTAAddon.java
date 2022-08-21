package betterterrain;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonObject;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.world.config.AddonConfigurationInfo;
import net.minecraft.src.FCAddOn;

public abstract class BTAAddon extends FCAddOn {
	public final AddonVersion currentVersion;
	public final Set<AddonVersion> validVersions;
	
	public BTAAddon(String addonName, String version, String prefix) {
		super(addonName, version, prefix);
		validVersions = new HashSet();
		this.setValidVersions(validVersions);
		this.currentVersion = AddonVersion.fromString(this.getVersionString(), this);
	}
	
	//------ World Configuration Functionality ------//
	
	public abstract void setValidVersions(Set<AddonVersion> versions);
	
	public abstract BiomeConfiguration getBiomeConfiguration();
	
	public final AddonConfigurationInfo createDefaultConfigInfo() {
		AddonConfigurationInfo info = createConfigInfo();
		
		info.setName(this.getName());
		info.setVersion(this.currentVersion);
		
		this.setDefaultSettings(info);
		
		return info;
	}
	
	public AddonConfigurationInfo createConfigInfo() {
		return new AddonConfigurationInfo();
	}
	
	public void setDefaultSettings(AddonConfigurationInfo info) {}
}