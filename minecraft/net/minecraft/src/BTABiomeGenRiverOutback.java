package net.minecraft.src;

public class BTABiomeGenRiverOutback extends BTABiomeGenRiver {
	public BTABiomeGenRiverOutback(int par1)
    {
        super(par1, BTABiomeConfiguration.outback.climate);
        this.spawnableCreatureList.clear();
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}