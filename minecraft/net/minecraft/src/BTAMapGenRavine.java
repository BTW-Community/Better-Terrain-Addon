package net.minecraft.src;

import java.util.Random;

public class BTAMapGenRavine extends BTAMapGenBase
{
    private float[] field_75046_d = new float[1024];

    protected void generateRavine(long par1, int par3, int par4, int[] blockArray, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17)
    {
        Random var19 = new Random(par1);
        double var20 = (double)(par3 * 16 + 8);
        double var22 = (double)(par4 * 16 + 8);
        float var24 = 0.0F;
        float var25 = 0.0F;

        if (par16 <= 0)
        {
            int var26 = this.range * 16 - 16;
            par16 = var26 - var19.nextInt(var26 / 4);
        }

        boolean var53 = false;

        if (par15 == -1)
        {
            par15 = par16 / 2;
            var53 = true;
        }

        float var27 = 1.0F;

        for (int var28 = 0; var28 < 128; ++var28)
        {
            if (var28 == 0 || var19.nextInt(3) == 0)
            {
                var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
            }

            this.field_75046_d[var28] = var27 * var27;
        }

        for (; par15 < par16; ++par15)
        {
            double var54 = 1.5D + (double)(MathHelper.sin((float)par15 * (float)Math.PI / (float)par16) * par12 * 1.0F);
            double var30 = var54 * par17;
            var54 *= (double)var19.nextFloat() * 0.25D + 0.75D;
            var30 *= (double)var19.nextFloat() * 0.25D + 0.75D;
            float var32 = MathHelper.cos(par14);
            float var33 = MathHelper.sin(par14);
            par6 += (double)(MathHelper.cos(par13) * var32);
            par8 += (double)var33;
            par10 += (double)(MathHelper.sin(par13) * var32);
            par14 *= 0.7F;
            par14 += var25 * 0.05F;
            par13 += var24 * 0.05F;
            var25 *= 0.8F;
            var24 *= 0.5F;
            var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
            var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;

            if (var53 || var19.nextInt(4) != 0)
            {
                double var34 = par6 - var20;
                double var36 = par10 - var22;
                double var38 = (double)(par16 - par15);
                double var40 = (double)(par12 + 2.0F + 16.0F);

                if (var34 * var34 + var36 * var36 - var38 * var38 > var40 * var40)
                {
                    return;
                }

                if (par6 >= var20 - 16.0D - var54 * 2.0D && par10 >= var22 - 16.0D - var54 * 2.0D && par6 <= var20 + 16.0D + var54 * 2.0D && par10 <= var22 + 16.0D + var54 * 2.0D)
                {
                    int var55 = MathHelper.floor_double(par6 - var54) - par3 * 16 - 1;
                    int var35 = MathHelper.floor_double(par6 + var54) - par3 * 16 + 1;
                    int var56 = MathHelper.floor_double(par8 - var30) - 1;
                    int var37 = MathHelper.floor_double(par8 + var30) + 1;
                    int var57 = MathHelper.floor_double(par10 - var54) - par4 * 16 - 1;
                    int var39 = MathHelper.floor_double(par10 + var54) - par4 * 16 + 1;

                    if (var55 < 0)
                    {
                        var55 = 0;
                    }

                    if (var35 > 16)
                    {
                        var35 = 16;
                    }

                    if (var56 < 1)
                    {
                        var56 = 1;
                    }

                    if (var37 > 120)
                    {
                        var37 = 120;
                    }

                    if (var57 < 0)
                    {
                        var57 = 0;
                    }

                    if (var39 > 16)
                    {
                        var39 = 16;
                    }

                    boolean var58 = false;
                    int var41;
                    int var44;

                    for (var41 = var55; !var58 && var41 < var35; ++var41)
                    {
                        for (int var42 = var57; !var58 && var42 < var39; ++var42)
                        {
                            for (int var43 = var37 + 1; !var58 && var43 >= var56 - 1; --var43)
                            {
                                var44 = (var41 * 16 + var42) * 128 + var43;

                                if (var43 >= 0 && var43 < 128)
                                {
                                    if (blockArray[var44] == Block.waterMoving.blockID || blockArray[var44] == Block.waterStill.blockID)
                                    {
                                        var58 = true;
                                    }

                                    if (var43 != var56 - 1 && var41 != var55 && var41 != var35 - 1 && var42 != var57 && var42 != var39 - 1)
                                    {
                                        var43 = var56;
                                    }
                                }
                            }
                        }
                    }

                    if (!var58)
                    {
                        for (var41 = var55; var41 < var35; ++var41)
                        {
                            double var59 = ((double)(var41 + par3 * 16) + 0.5D - par6) / var54;

                            for (var44 = var57; var44 < var39; ++var44)
                            {
                                double var45 = ((double)(var44 + par4 * 16) + 0.5D - par10) / var54;
                                int var47 = (var41 * 16 + var44) * 128 + var37;
                                boolean var48 = false;

                                if (var59 * var59 + var45 * var45 < 1.0D)
                                {
                                    for (int var49 = var37 - 1; var49 >= var56; --var49)
                                    {
                                        double var50 = ((double)var49 + 0.5D - par8) / var30;

                                        if ((var59 * var59 + var45 * var45) * (double)this.field_75046_d[var49] + var50 * var50 / 6.0D < 1.0D)
                                        {
                                            int var52 = blockArray[var47];

                                            if (var52 == Block.grass.blockID)
                                            {
                                                var48 = true;
                                            }

                                            if (var52 == Block.stone.blockID || var52 == Block.dirt.blockID || var52 == Block.grass.blockID)
                                            {
                                                if (var49 < 10 && !this.worldObj.provider.terrainType.isSky())
                                                {
                                                    blockArray[var47] = (byte)Block.lavaMoving.blockID;
                                                }
                                                else
                                                {
                                                    blockArray[var47] = 0;

                                                    if (var48 && blockArray[var47 - 1] == Block.dirt.blockID)
                                                    {
                                                        blockArray[var47 - 1] = this.worldObj.getBiomeGenForCoords(var41 + par3 * 16, var44 + par4 * 16).topBlock;
                                                    }
                                                }
                                            }
                                        }

                                        --var47;
                                    }
                                }
                            }
                        }

                        if (var53)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, int[] blockArray)
    {
        if (this.rand.nextInt(50) == 0)
        {
            double var7 = (double)(par2 * 16 + this.rand.nextInt(16));
            double var9 = (double)(this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
            double var11 = (double)(par3 * 16 + this.rand.nextInt(16));
            byte var13 = 1;

            for (int var14 = 0; var14 < var13; ++var14)
            {
                float var15 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float var16 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var17 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
                this.generateRavine(this.rand.nextLong(), par4, par5, blockArray, var7, var9, var11, var17, var15, var16, 0, 0, 3.0D);
            }
        }
    }
}
