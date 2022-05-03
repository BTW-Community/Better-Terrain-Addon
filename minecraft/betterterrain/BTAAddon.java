package betterterrain;

import betterterrain.biome.BiomeConfiguration;
import net.minecraft.src.FCAddOn;

public abstract class BTAAddon extends FCAddOn {
	public final BTAVersion currentVersion;
	
	public BTAAddon(String addonName, String version, String prefix) {
		super(addonName, version, prefix);
		this.currentVersion = BTAVersion.fromString(this.getVersionString());
	}
	
	public abstract BiomeConfiguration getBiomeConfiguration();
}