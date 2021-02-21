package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenLushDesert extends BTABiomeGenBase
{
    public BTABiomeGenLushDesert(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
        this.btaiomeDecorator.treesPerChunk = -999;
        this.btaiomeDecorator.deadBushPerChunk = 2;
        this.btaiomeDecorator.reedsPerChunk = 50;
        this.btaiomeDecorator.cactiPerChunk = 10;
        this.btaiomeDecorator.oasesPerChunk = 100;
        this.btaiomeDecorator.waterLakesPerChunk = 100;
		this.btaiomeDecorator.treesPerChunk = 20;
		this.btaiomeDecorator.grassPerChunk = 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return new BTAWorldGenPalmTreeSmall(false, false);
	}

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);

        if (par2Random.nextInt(1000) == 0)
        {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenDesertWells var7 = new WorldGenDesertWells();
            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
        }
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}