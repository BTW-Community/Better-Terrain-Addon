package betterterrain.biome.biomes;

import betterbiomes.DecoIntegration;
import betterterrain.biome.BiomeConfiguration;

public class OutbackRiverBiome extends RiverBiome {
	public OutbackRiverBiome(int par1)
    {
        super(par1, BiomeConfiguration.outback.climate);
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