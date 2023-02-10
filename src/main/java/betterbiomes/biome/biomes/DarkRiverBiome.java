package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;

public class DarkRiverBiome extends RiverBiome {
    public DarkRiverBiome(int id, String internalName) {
        super(id, internalName, BetterBiomesConfiguration.orchard.climate);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return BetterBiomesConfiguration.darkForest.getBiomeGrassColor();
    }
}
