package net.minecraft.src;

public class BTWGBiomeGenRiverOutback extends BTWGBiomeGenBase {
	public BTWGBiomeGenRiverOutback(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
		if (BTWGDecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTWGDecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTWGDecoIntegration.redSand.blockID;
		}
		this.btwgBiomeDecorator.sandPerChunk = 0;
		this.btwgBiomeDecorator.sandPerChunk2 = 0;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}