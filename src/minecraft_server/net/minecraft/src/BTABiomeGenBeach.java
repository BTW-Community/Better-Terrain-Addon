package net.minecraft.src;

public class BTABiomeGenBeach extends BTABiomeGenBase
{
    public BTABiomeGenBeach(int par1, BTAEnumClimate climate)
    {
        super(par1, climate);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
    }
}
