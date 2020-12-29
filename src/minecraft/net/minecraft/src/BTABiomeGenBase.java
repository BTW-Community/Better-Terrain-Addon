package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;

public class BTABiomeGenBase extends BiomeGenBase {
    public int topBlockExt;
    public int fillerBlockExt;
    
	private boolean enableSnow;
	private boolean enableRain;
	private boolean isSpawnable;
	
    public BTABiomeDecorator btwgBiomeDecorator;

	protected BTABiomeGenBase(int id) {
		super(id);
		this.enableRain = true;
		this.isSpawnable = true;
		this.btwgBiomeDecorator = new BTABiomeDecorator(this);
        this.topBlockExt = Block.grass.blockID;
        this.fillerBlockExt = Block.dirt.blockID;
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
        this.btwgBiomeDecorator.decorate(par1World, par2Random, par3, par4);
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
}