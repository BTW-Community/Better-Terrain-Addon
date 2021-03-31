package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;

public class BTABiomeGenBase extends BiomeGenBase {
    public int topBlockExt;
    public int fillerBlockExt;
    
	private boolean enableSnow;
	private boolean enableRain;
	private boolean isSpawnable;
	
	protected BTAEnumClimate climate;
	
	protected BTABiomeDecorator btaBiomeDecorator;
    private BTASurfaceBuilder surfaceBuilder;

	protected BTABiomeGenBase(int id, BTAEnumClimate climate) {
		super(id);
		this.enableRain = true;
		this.isSpawnable = true;
		this.btaBiomeDecorator = new BTABiomeDecorator(this);
        this.topBlockExt = Block.grass.blockID;
        this.fillerBlockExt = Block.dirt.blockID;
        this.climate = climate;
	}

    /**
     * Sets the temperature and rainfall of this biome.
     */
    public BTABiomeGenBase setTemperatureRainfall(float par1, float par2)
    {
        if (par1 > 0.1F && par1 < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else
        {
            this.temperature = par1;
            this.rainfall = par2;
            return this;
        }
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        this.btaBiomeDecorator.decorate(par1World, par2Random, par3, par4);
    }

    /**
     * Sets the minimum and maximum height of this biome. Seems to go from -2.0 to 2.0.
     */
    public BTABiomeGenBase setMinMaxHeight(float par1, float par2)
    {
        this.minHeight = par1;
        this.maxHeight = par2;
        return this;
    }

    /**
     * Disable the rain for the biome.
     */
    public BTABiomeGenBase setDisableRain()
    {
        this.enableRain = false;
        return this;
    }

    /**
     * sets enableSnow to true during biome initialization. returns BiomeGenBase.
     */
    public BTABiomeGenBase setEnableSnow()
    {
        this.enableSnow = true;
        return this;
    }

    /**
     * Returns true if the biome have snowfall instead a normal rain.
     */
    public boolean getEnableSnow()
    {
        return this.enableSnow;
    }

    public BTABiomeGenBase setBiomeName(String par1Str)
    {
        this.biomeName = par1Str;
        return this;
    }

    public BTABiomeGenBase func_76733_a(int par1)
    {
        this.field_76754_C = par1;
        return this;
    }

    public BTABiomeGenBase setColor(int par1)
    {
        this.color = par1;
        return this;
    }

    public boolean isSpawnable() {
		return isSpawnable;
	}

	public BTABiomeGenBase setNotSpawnable() {
		this.isSpawnable = false;
		return this;
	}

	public boolean CanRainInBiome()
    {
        return this.enableSnow ? false : this.enableRain;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return this.CanRainInBiome();
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    public BTASurfaceBuilder getSurfaceBuilder() {
		return surfaceBuilder;
	}

	public void setSurfaceBuilder(BTASurfaceBuilder surfaceBuilder) {
		this.surfaceBuilder = surfaceBuilder;
	}

	public void AddEmeralds(World var1, Random var2, int var3, int var4)
    {
        int var5 = 3 + var2.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = var3 + var2.nextInt(16);
            int var8 = var2.nextInt(28) + 4;
            int var9 = var4 + var2.nextInt(16);

            if (var1.getBlockId(var7, var8, var9) == Block.stone.blockID)
            {
            	int var11 = 0;
                
                int[] stratas = var1.provider.terrainType.getStrataLevels();
                
                int strata1Height = stratas[0];
                int strata2Height = stratas[1];
                int strata3Height = -2;
                
                if (stratas.length > 2)
                	strata3Height = stratas[2];
            	
                if (var8 <= strata1Height + var1.rand.nextInt(2))
                {
                    var11 = 1;

                    if (var8 <= strata2Height + var1.rand.nextInt(2))
                    {
                        var11 = 2;
                    }

                    if (var8 <= strata3Height + var1.rand.nextInt(2))
                    {
                        var11 = 3;
                    }
                }

                var1.setBlock(var7, var8, var9, Block.oreEmerald.blockID, var11, 2);
            }
        }
    }

    public void AddSilverfishBlocks(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 7; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(64);
            int var8 = var4 + var2.nextInt(16);
            new BTAWorldGenMinable(Block.silverfish.blockID, 0, 8).generate(var1, var2, var6, var7, var8);
        }
    }
    
    
}