package net.minecraft.src;

import java.util.Random;

public class BTWGMapGenCaveBeta extends MapGenBase
{
    protected void generateLargeCaveNode(int var1, int var2, byte[] var3, double var4, double var6, double var8)
    {
        this.generateCaveNode(var1, var2, var3, var4, var6, var8, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void generateCaveNode(int var1, int var2, byte[] var3, double var4, double var6, double var8, float var10, float var11, float var12, int var13, int var14, double var15)
    {
        double var17 = (double)(var1 * 16 + 8);
        double var19 = (double)(var2 * 16 + 8);
        float var21 = 0.0F;
        float var22 = 0.0F;
        Random var23 = new Random(this.rand.nextLong());

        if (var14 <= 0)
        {
            int var24 = this.range * 16 - 16;
            var14 = var24 - var23.nextInt(var24 / 4);
        }

        boolean var60 = false;

        if (var13 == -1)
        {
            var13 = var14 / 2;
            var60 = true;
        }

        int var25 = var23.nextInt(var14 / 2) + var14 / 4;

        for (boolean var26 = var23.nextInt(6) == 0; var13 < var14; ++var13)
        {
            double var27 = 1.5D + (double)(MathHelper.sin((float)var13 * (float)Math.PI / (float)var14) * var10 * 1.0F);
            double var29 = var27 * var15;
            float var31 = MathHelper.cos(var12);
            float var32 = MathHelper.sin(var12);
            var4 += (double)(MathHelper.cos(var11) * var31);
            var6 += (double)var32;
            var8 += (double)(MathHelper.sin(var11) * var31);

            if (var26)
            {
                var12 *= 0.92F;
            }
            else
            {
                var12 *= 0.7F;
            }

            var12 += var22 * 0.1F;
            var11 += var21 * 0.1F;
            var22 *= 0.9F;
            var21 *= 0.75F;
            var22 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 2.0F;
            var21 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 4.0F;

            if (!var60 && var13 == var25 && var10 > 1.0F)
            {
                this.generateCaveNode(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 - ((float)Math.PI / 2F), var12 / 3.0F, var13, var14, 1.0D);
                this.generateCaveNode(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 + ((float)Math.PI / 2F), var12 / 3.0F, var13, var14, 1.0D);
                return;
            }

            if (var60 || var23.nextInt(4) != 0)
            {
                double var33 = var4 - var17;
                double var35 = var8 - var19;
                double var37 = (double)(var14 - var13);
                double var39 = (double)(var10 + 2.0F + 16.0F);

                if (var33 * var33 + var35 * var35 - var37 * var37 > var39 * var39)
                {
                    return;
                }

                if (var4 >= var17 - 16.0D - var27 * 2.0D && var8 >= var19 - 16.0D - var27 * 2.0D && var4 <= var17 + 16.0D + var27 * 2.0D && var8 <= var19 + 16.0D + var27 * 2.0D)
                {
                    int var41 = MathHelper.floor_double(var4 - var27) - var1 * 16 - 1;
                    int var42 = MathHelper.floor_double(var4 + var27) - var1 * 16 + 1;
                    int var43 = MathHelper.floor_double(var6 - var29) - 1;
                    int var44 = MathHelper.floor_double(var6 + var29) + 1;
                    int var45 = MathHelper.floor_double(var8 - var27) - var2 * 16 - 1;
                    int var46 = MathHelper.floor_double(var8 + var27) - var2 * 16 + 1;

                    if (var41 < 0)
                    {
                        var41 = 0;
                    }

                    if (var42 > 16)
                    {
                        var42 = 16;
                    }

                    if (var43 < 1)
                    {
                        var43 = 1;
                    }

                    if (var44 > 120)
                    {
                        var44 = 120;
                    }

                    if (var45 < 0)
                    {
                        var45 = 0;
                    }

                    if (var46 > 16)
                    {
                        var46 = 16;
                    }

                    boolean var47 = false;
                    int var48;
                    int var49;

                    for (var49 = var41; !var47 && var49 < var42; ++var49)
                    {
                        for (int var50 = var45; !var47 && var50 < var46; ++var50)
                        {
                            for (int var51 = var44 + 1; !var47 && var51 >= var43 - 1; --var51)
                            {
                                var48 = (var49 * 16 + var50) * 128 + var51;

                                if (var51 >= 0 && var51 < 128)
                                {
                                    if (var3[var48] == Block.waterMoving.blockID || var3[var48] == Block.waterStill.blockID)
                                    {
                                        var47 = true;
                                    }

                                    if (var51 != var43 - 1 && var49 != var41 && var49 != var42 - 1 && var50 != var45 && var50 != var46 - 1)
                                    {
                                        var51 = var43;
                                    }
                                }
                            }
                        }
                    }

                    if (!var47)
                    {
                        for (var49 = var41; var49 < var42; ++var49)
                        {
                            double var61 = ((double)(var49 + var1 * 16) + 0.5D - var4) / var27;

                            for (var48 = var45; var48 < var46; ++var48)
                            {
                                double var52 = ((double)(var48 + var2 * 16) + 0.5D - var8) / var27;
                                int var54 = (var49 * 16 + var48) * 128 + var44;
                                boolean var55 = false;

                                if (var61 * var61 + var52 * var52 < 1.0D)
                                {
                                    for (int var56 = var44 - 1; var56 >= var43; --var56)
                                    {
                                        double var57 = ((double)var56 + 0.5D - var6) / var29;

                                        if (var57 > -0.7D && var61 * var61 + var57 * var57 + var52 * var52 < 1.0D)
                                        {
                                            byte var59 = var3[var54];

                                            if (var59 == Block.grass.blockID)
                                            {
                                                var55 = true;
                                            }

                                            if (var59 == Block.stone.blockID || var59 == Block.dirt.blockID || var59 == Block.grass.blockID)
                                            {
                                                if (var56 < 10)
                                                {
                                                    var3[var54] = (byte)Block.lavaMoving.blockID;
                                                }
                                                else
                                                {
                                                    var3[var54] = 0;

                                                    if (var55 && var3[var54 - 1] == Block.dirt.blockID)
                                                    {
                                                        var3[var54 - 1] = (byte)Block.grass.blockID;
                                                    }
                                                }
                                            }
                                        }

                                        --var54;
                                    }
                                }
                            }
                        }

                        if (var60)
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
    protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, byte[] var6)
    {
        int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);

        if (this.rand.nextInt(15) != 0)
        {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; ++var8)
        {
            double var9 = (double)(var2 * 16 + this.rand.nextInt(16));
            double var11 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
            double var13 = (double)(var3 * 16 + this.rand.nextInt(16));
            int var15 = 1;

            if (this.rand.nextInt(4) == 0)
            {
                this.generateLargeCaveNode(var4, var5, var6, var9, var11, var13);
                var15 += this.rand.nextInt(4);
            }

            for (int var16 = 0; var16 < var15; ++var16)
            {
                float var17 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
                this.generateCaveNode(var4, var5, var6, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
            }
        }
    }
}
