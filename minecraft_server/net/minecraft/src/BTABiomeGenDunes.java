package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenDunes extends BTABiomeGenBase
{
    public BTABiomeGenDunes(int par1, BTAEnumClimate climate)
    {
        super(par1, climate);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
        this.btaBiomeDecorator.treesPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 2;
        this.btaBiomeDecorator.cactiPerChunk = 10;
        this.btaBiomeDecorator.generateLakes = false;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4, BTAWorldConfigurationInfo generatorOptions)
    {
        super.decorate(par1World, par2Random, par3, par4, generatorOptions);

        if (par2Random.nextInt(1000) == 0)
        {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenDesertWells var7 = new WorldGenDesertWells();
            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
        }
        
        this.AddEmeralds(par1World, par2Random, par3, par4);
        this.AddSilverfishBlocks(par1World, par2Random, par3, par4);
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}
