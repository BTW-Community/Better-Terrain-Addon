package net.minecraft.src;

public class BTWGBiomeGenRiverDesert extends BTWGBiomeGenBase {
	public BTWGBiomeGenRiverDesert(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}