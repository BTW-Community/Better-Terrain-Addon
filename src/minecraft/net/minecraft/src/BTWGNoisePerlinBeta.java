package net.minecraft.src;

import java.util.Random;

public class BTWGNoisePerlinBeta extends NoiseGenerator
{
    private int[] permutations;
    public double xCoord;
    public double yCoord;
    public double zCoord;

    public BTWGNoisePerlinBeta()
    {
        this(new Random());
    }

    public BTWGNoisePerlinBeta(Random var1)
    {
        this.permutations = new int[512];
        this.xCoord = var1.nextDouble() * 256.0D;
        this.yCoord = var1.nextDouble() * 256.0D;
        this.zCoord = var1.nextDouble() * 256.0D;
        int var2;

        for (var2 = 0; var2 < 256; this.permutations[var2] = var2++)
        {
            ;
        }

        for (var2 = 0; var2 < 256; ++var2)
        {
            int var3 = var1.nextInt(256 - var2) + var2;
            int var4 = this.permutations[var2];
            this.permutations[var2] = this.permutations[var3];
            this.permutations[var3] = var4;
            this.permutations[var2 + 256] = this.permutations[var2];
        }
    }

    public double generateNoise(double var1, double var3, double var5)
    {
        double var7 = var1 + this.xCoord;
        double var9 = var3 + this.yCoord;
        double var11 = var5 + this.zCoord;
        int var13 = (int)var7;
        int var14 = (int)var9;
        int var15 = (int)var11;

        if (var7 < (double)var13)
        {
            --var13;
        }

        if (var9 < (double)var14)
        {
            --var14;
        }

        if (var11 < (double)var15)
        {
            --var15;
        }

        int var16 = var13 & 255;
        int var17 = var14 & 255;
        int var18 = var15 & 255;
        var7 -= (double)var13;
        var9 -= (double)var14;
        var11 -= (double)var15;
        double var19 = var7 * var7 * var7 * (var7 * (var7 * 6.0D - 15.0D) + 10.0D);
        double var21 = var9 * var9 * var9 * (var9 * (var9 * 6.0D - 15.0D) + 10.0D);
        double var23 = var11 * var11 * var11 * (var11 * (var11 * 6.0D - 15.0D) + 10.0D);
        int var25 = this.permutations[var16] + var17;
        int var26 = this.permutations[var25] + var18;
        int var27 = this.permutations[var25 + 1] + var18;
        int var28 = this.permutations[var16 + 1] + var17;
        int var29 = this.permutations[var28] + var18;
        int var30 = this.permutations[var28 + 1] + var18;
        return this.lerp(var23, this.lerp(var21, this.lerp(var19, this.grad(this.permutations[var26], var7, var9, var11), this.grad(this.permutations[var29], var7 - 1.0D, var9, var11)), this.lerp(var19, this.grad(this.permutations[var27], var7, var9 - 1.0D, var11), this.grad(this.permutations[var30], var7 - 1.0D, var9 - 1.0D, var11))), this.lerp(var21, this.lerp(var19, this.grad(this.permutations[var26 + 1], var7, var9, var11 - 1.0D), this.grad(this.permutations[var29 + 1], var7 - 1.0D, var9, var11 - 1.0D)), this.lerp(var19, this.grad(this.permutations[var27 + 1], var7, var9 - 1.0D, var11 - 1.0D), this.grad(this.permutations[var30 + 1], var7 - 1.0D, var9 - 1.0D, var11 - 1.0D))));
    }

    public final double lerp(double var1, double var3, double var5)
    {
        return var3 + var1 * (var5 - var3);
    }

    public final double func_4110_a(int var1, double var2, double var4)
    {
        int var6 = var1 & 15;
        double var7 = (double)(1 - ((var6 & 8) >> 3)) * var2;
        double var9 = var6 >= 4 ? (var6 != 12 && var6 != 14 ? var4 : var2) : 0.0D;
        return ((var6 & 1) != 0 ? -var7 : var7) + ((var6 & 2) != 0 ? -var9 : var9);
    }

    public final double grad(int var1, double var2, double var4, double var6)
    {
        int var8 = var1 & 15;
        double var9 = var8 >= 8 ? var4 : var2;
        double var11 = var8 >= 4 ? (var8 != 12 && var8 != 14 ? var6 : var2) : var4;
        return ((var8 & 1) != 0 ? -var9 : var9) + ((var8 & 2) != 0 ? -var11 : var11);
    }

    public double func_801_a(double var1, double var3)
    {
        return this.generateNoise(var1, var3, 0.0D);
    }

    public void func_805_a(double[] var1, double var2, double var4, double var6, int var8, int var9, int var10, double var11, double var13, double var15, double var17)
    {
        double var19;
        double var21;
        double var23;
        int var25;
        double var26;
        int var28;
        int var29;
        int var30;
        int var31;
        int var32;
        double var33;

        if (var9 == 1)
        {
            boolean var35 = false;
            boolean var36 = false;
            boolean var37 = false;
            boolean var38 = false;
            double var39 = 0.0D;
            double var41 = 0.0D;
            int var43 = 0;
            double var44 = 1.0D / var17;

            for (int var46 = 0; var46 < var8; ++var46)
            {
                var19 = (var2 + (double)var46) * var11 + this.xCoord;
                int var47 = (int)var19;

                if (var19 < (double)var47)
                {
                    --var47;
                }

                int var48 = var47 & 255;
                var19 -= (double)var47;
                var21 = var19 * var19 * var19 * (var19 * (var19 * 6.0D - 15.0D) + 10.0D);

                for (var25 = 0; var25 < var10; ++var25)
                {
                    var23 = (var6 + (double)var25) * var15 + this.zCoord;
                    var28 = (int)var23;

                    if (var23 < (double)var28)
                    {
                        --var28;
                    }

                    var29 = var28 & 255;
                    var23 -= (double)var28;
                    var26 = var23 * var23 * var23 * (var23 * (var23 * 6.0D - 15.0D) + 10.0D);
                    var32 = this.permutations[var48] + 0;
                    int var49 = this.permutations[var32] + var29;
                    int var50 = this.permutations[var48 + 1] + 0;
                    var31 = this.permutations[var50] + var29;
                    double var51 = this.lerp(var21, this.func_4110_a(this.permutations[var49], var19, var23), this.grad(this.permutations[var31], var19 - 1.0D, 0.0D, var23));
                    double var53 = this.lerp(var21, this.grad(this.permutations[var49 + 1], var19, 0.0D, var23 - 1.0D), this.grad(this.permutations[var31 + 1], var19 - 1.0D, 0.0D, var23 - 1.0D));
                    var33 = this.lerp(var26, var51, var53);
                    var30 = var43++;
                    var1[var30] += var33 * var44;
                }
            }
        }
        else
        {
            int var65 = 0;
            double var66 = 1.0D / var17;
            int var67 = -1;
            boolean var68 = false;
            boolean var40 = false;
            boolean var69 = false;
            boolean var42 = false;
            boolean var70 = false;
            boolean var71 = false;
            double var45 = 0.0D;
            var19 = 0.0D;
            double var72 = 0.0D;
            var21 = 0.0D;

            for (var25 = 0; var25 < var8; ++var25)
            {
                var23 = (var2 + (double)var25) * var11 + this.xCoord;
                var28 = (int)var23;

                if (var23 < (double)var28)
                {
                    --var28;
                }

                var29 = var28 & 255;
                var23 -= (double)var28;
                var26 = var23 * var23 * var23 * (var23 * (var23 * 6.0D - 15.0D) + 10.0D);

                for (var32 = 0; var32 < var10; ++var32)
                {
                    double var73 = (var6 + (double)var32) * var15 + this.zCoord;
                    var31 = (int)var73;

                    if (var73 < (double)var31)
                    {
                        --var31;
                    }

                    int var74 = var31 & 255;
                    var73 -= (double)var31;
                    double var52 = var73 * var73 * var73 * (var73 * (var73 * 6.0D - 15.0D) + 10.0D);

                    for (int var54 = 0; var54 < var9; ++var54)
                    {
                        var33 = (var4 + (double)var54) * var13 + this.yCoord;
                        int var55 = (int)var33;

                        if (var33 < (double)var55)
                        {
                            --var55;
                        }

                        int var56 = var55 & 255;
                        var33 -= (double)var55;
                        double var57 = var33 * var33 * var33 * (var33 * (var33 * 6.0D - 15.0D) + 10.0D);

                        if (var54 == 0 || var56 != var67)
                        {
                            var67 = var56;
                            int var59 = this.permutations[var29] + var56;
                            int var60 = this.permutations[var59] + var74;
                            int var61 = this.permutations[var59 + 1] + var74;
                            int var62 = this.permutations[var29 + 1] + var56;
                            int var63 = this.permutations[var62] + var74;
                            int var64 = this.permutations[var62 + 1] + var74;
                            var45 = this.lerp(var26, this.grad(this.permutations[var60], var23, var33, var73), this.grad(this.permutations[var63], var23 - 1.0D, var33, var73));
                            var19 = this.lerp(var26, this.grad(this.permutations[var61], var23, var33 - 1.0D, var73), this.grad(this.permutations[var64], var23 - 1.0D, var33 - 1.0D, var73));
                            var72 = this.lerp(var26, this.grad(this.permutations[var60 + 1], var23, var33, var73 - 1.0D), this.grad(this.permutations[var63 + 1], var23 - 1.0D, var33, var73 - 1.0D));
                            var21 = this.lerp(var26, this.grad(this.permutations[var61 + 1], var23, var33 - 1.0D, var73 - 1.0D), this.grad(this.permutations[var64 + 1], var23 - 1.0D, var33 - 1.0D, var73 - 1.0D));
                        }

                        double var75 = this.lerp(var57, var45, var19);
                        double var76 = this.lerp(var57, var72, var21);
                        double var77 = this.lerp(var52, var75, var76);
                        var30 = var65++;
                        var1[var30] += var77 * var66;
                    }
                }
            }
        }
    }
}
