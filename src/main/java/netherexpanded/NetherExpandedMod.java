package netherexpanded;

import betterterrain.AddonVersion;
import betterterrain.BTAAddon;
import betterterrain.biome.BiomeConfiguration;
import btw.AddonHandler;

public class NetherExpandedMod extends BTAAddon {
    private static NetherExpandedMod instance;

    public static final AddonVersion V1_0_0 = new AddonVersion(1, 0, 0, getInstance());

    public NetherExpandedMod() {
        super("Nether Expanded", "netherexpanded", "1.0.0", "NE");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        AddonHandler.logMessage(this.getName() + " Initialized");
    }

    public static NetherExpandedMod getInstance() {
        if (instance == null) {
            instance = new NetherExpandedMod();
        }

        return instance;
    }

    @Override
    public BiomeConfiguration getBiomeConfiguration() {
        return null;
    }
}
