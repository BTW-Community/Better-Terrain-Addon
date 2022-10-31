package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.BTAMod;
import betterterrain.biome.biomes.RiverBiome;
import deco.block.DecoBlocks;

public class BadlandsRiverBiome extends RiverBiome {
	public BadlandsRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.badlands.climate);
		if (BTAMod.isDecoInstalled()) {
			this.topBlockExt = DecoBlocks.legacyRedSand.blockID;
			this.fillerBlockExt = DecoBlocks.legacyRedSand.blockID;
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