package betterbiomes;

import java.util.Set;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.AddonVersion;
import betterterrain.BTAAddon;
import betterterrain.biome.BiomeConfiguration;
import btw.AddonHandler;

public class BetterBiomesMod extends BTAAddon {
	private static BetterBiomesMod instance;
	
	public static final AddonVersion V3_0_0 = new AddonVersion(3, 0, 0, getInstance());
	public static final AddonVersion V3_1_0 = new AddonVersion(3, 1, 0, getInstance());
	
	private BetterBiomesMod() {
		super("Better Biomes", "betterbiomes", "3.1.0", "BB");
	}
	
	@Override
	public void initialize() {
		AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		AddonHandler.logMessage(this.getName() + " Initialized");
	}
	
	public static BetterBiomesMod getInstance() {
		if (instance == null) {
			instance = new BetterBiomesMod();
		}
		
		return instance;
	}

	@Override
	public BiomeConfiguration getBiomeConfiguration() {
		return BetterBiomesConfiguration.getInstance();
	}
}