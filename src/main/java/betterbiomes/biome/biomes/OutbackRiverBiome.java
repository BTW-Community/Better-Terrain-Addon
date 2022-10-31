package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.BTAMod;
import betterterrain.biome.biomes.RiverBiome;
import deco.block.DecoBlocks;

public class OutbackRiverBiome extends RiverBiome {
	public OutbackRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.outback.climate);
        this.spawnableCreatureList.clear();
		if (BTAMod.isDecoInstalled()) {
			this.topBlockExt = DecoBlocks.legacyRedSand.blockID;
			this.fillerBlockExt = DecoBlocks.legacyRedSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}