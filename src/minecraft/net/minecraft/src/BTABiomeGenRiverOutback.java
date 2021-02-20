package net.minecraft.src;

public class BTABiomeGenRiverOutback extends BTABiomeGenBase {
	public BTABiomeGenRiverOutback(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
		}
		this.btaiomeDecorator.sandPerChunk = 0;
		this.btaiomeDecorator.sandPerChunk2 = 0;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}