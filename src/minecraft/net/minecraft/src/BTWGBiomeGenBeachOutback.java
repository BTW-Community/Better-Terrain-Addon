package net.minecraft.src;

public class BTWGBiomeGenBeachOutback extends BTWGBiomeGenBase
{
    public BTWGBiomeGenBeachOutback(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
		if (BTWGDecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTWGDecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTWGDecoIntegration.redSand.blockID;
		}
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}
