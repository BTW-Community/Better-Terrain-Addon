package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.DecoIntegration;
import betterterrain.biome.biomes.RiverBiome;

public class BadlandsRiverBiome extends RiverBiome {
	public BadlandsRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.badlands.climate);
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.redSand.blockID;
			this.fillerBlockExt = DecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
        this.spawnableCreatureList.clear();
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.badlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.badlands.getBiomeFoliageColor();
    }
}