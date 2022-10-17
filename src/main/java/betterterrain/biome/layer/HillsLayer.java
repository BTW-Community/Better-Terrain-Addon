package betterterrain.biome.layer;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class HillsLayer extends GenLayer
{
	private WorldConfigurationInfo generatorInfo;
	
    public HillsLayer(long par1, GenLayer par3GenLayer, WorldConfigurationInfo generatorInfo)
    {
        super(par1);
        this.parent = par3GenLayer;
        this.generatorInfo = generatorInfo;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int baseBiome = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];

                if (this.nextInt(3) == 0)
                {
                    int hillsBiome = BiomeConfiguration.getSubVariantForBiomes(baseBiome, generatorInfo, this);

                    if (hillsBiome == baseBiome)
                    {
                        var6[var8 + var7 * par3] = baseBiome;
                    }
                    else
                    {
                        int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
                        int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
                        int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
                        int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

                        if (var11 == baseBiome && var12 == baseBiome && var13 == baseBiome && var14 == baseBiome)
                        {
                            var6[var8 + var7 * par3] = hillsBiome;
                        }
                        else
                        {
                            var6[var8 + var7 * par3] = baseBiome;
                        }

                        continue;
                    }
                }
                else {
                    var6[var8 + var7 * par3] = baseBiome;
                }
                
                if (this.nextInt(5) != 0)
                {
                    int hillsBiome = BiomeConfiguration.getSubVariantForBiomesCommon(baseBiome, generatorInfo, this);

                    if (hillsBiome == baseBiome)
                    {
                        var6[var8 + var7 * par3] = baseBiome;
                    }
                    else
                    {
                        int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
                        int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
                        int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
                        int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

                        if (var11 == baseBiome && var12 == baseBiome && var13 == baseBiome && var14 == baseBiome)
                        {
                            var6[var8 + var7 * par3] = hillsBiome;
                        }
                        else
                        {
                            var6[var8 + var7 * par3] = baseBiome;
                        }
                    }
                }
                else {
                    var6[var8 + var7 * par3] = baseBiome;
                }
            }
        }

        return var6;
    }
}