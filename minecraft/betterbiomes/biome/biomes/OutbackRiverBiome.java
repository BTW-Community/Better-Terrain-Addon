package betterbiomes.biome.biomes;

import betterbiomes.DecoIntegration;
import betterbiomes.biome.BetterBiomesConfiguration;

public class OutbackRiverBiome extends RiverBiome {
	public OutbackRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.outback.climate);
        this.spawnableCreatureList.clear();
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.redSand.blockID;
			this.fillerBlockExt = DecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}