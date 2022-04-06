package betterterrain;

import java.util.Random;

import net.minecraft.src.NoiseGenerator;

public class BTAHorizonsNoisePerlin extends NoiseGenerator
{
    private int[] permutations;
    public double xCoord;
    public double yCoord;
    public double zCoord;

    public BTAHorizonsNoisePerlin()
    {
        this(new Random());
    }

    public BTAHorizonsNoisePerlin(Random rand) {
        this.permutations = new int[512];
        this.xCoord = rand.nextDouble() * 256.0D;
        this.yCoord = rand.nextDouble() * 256.0D;
        this.zCoord = rand.nextDouble() * 256.0D;

        for (int i = 0; i < 256; this.permutations[i] = i++) {
            ;
        }

        for (int i = 0; i < 256; ++i) {
            int randIndex = rand.nextInt(256 - i) + i;
            int oldValue = this.permutations[i];
            this.permutations[i] = this.permutations[randIndex];
            this.permutations[randIndex] = oldValue;
            this.permutations[i + 256] = this.permutations[i];
        }
    }

    public final double lerp(double par1, double par3, double par5) {
        return par3 + par1 * (par5 - par3);
    }

    public final double grad2d(int hash, double x, double z) {
        int h = hash & 15;
        double u = (double)(1 - ((h & 8) >> 3)) * x;
        double v = h < 4 ? 0.0D : (h != 12 && h != 14 ? z : x);
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    public final double grad(int hash, double x, double y, double z) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : (h != 12 && h != 14 ? z : x);
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    /**
     * pars: noiseArray , xOffset , yOffset , zOffset , xSize , ySize , zSize , xScale, yScale , zScale , noiseScale.
     * noiseArray should be xSize*ySize*zSize in size
     */
    public void populateNoiseArray(double[] noiseArray, double xOffset, double yOffset, double zOffset,
    		int xSize, int ySize, int zSize, double xScale, double yScale, double zScale, double noiseScale) {
        int noiseIndex = 0;

        if (ySize == 1) {
        	int c = 0;
            double noiseScaleInverse = 1.0D / noiseScale;
            
            double x1 = 0.0D;
            double x2 = 0.0D;

            for (int i = 0; i < xSize; ++i) {
                double xDouble = xOffset + (double) i * xScale + this.xCoord;
                int x = (int) xDouble;

                if (xDouble < (double) x) {
                    --x;
                }

                int xLimited = x & 255;
                xDouble -= (double) x;
                double xFaded = xDouble * xDouble * xDouble * (xDouble * (xDouble * 6.0D - 15.0D) + 10.0D);

                for (int k = 0; k < zSize; ++k) {
                    double zDouble = zOffset + (double) k * zScale + this.zCoord;
                    int z = (int)zDouble;

                    if (zDouble < (double) z) {
                        --z;
                    }

                    int zLimited = z & 255;
                    zDouble -= (double) z;
                    double zFaded = zDouble * zDouble * zDouble * (zDouble * (zDouble * 6.0D - 15.0D) + 10.0D);
                    
                    int a = this.permutations[this.permutations[xLimited + 0]] + zLimited;
                    int b = this.permutations[this.permutations[xLimited + 1]] + zLimited;
                    x1 = this.lerp(xFaded, this.grad2d(this.permutations[a], xDouble, zDouble), this.grad(this.permutations[b], xDouble - 1.0D, 0.0D, zDouble));
                    x2 = this.lerp(xFaded, this.grad(this.permutations[a + 1], xDouble, 0.0D, zDouble - 1.0D), this.grad(this.permutations[b + 1], xDouble - 1.0D, 0.0D, zDouble - 1.0D));
                    double xz = this.lerp(zFaded, x1, x2);
                    noiseIndex = c++;
                    noiseArray[noiseIndex] += xz * noiseScaleInverse;
                }
            }
        }
        else {
            double noiseScaleInverse = 1.0D / noiseScale;
            int var22 = -1;
            int c = 0;
            
            double x1 = 0.0D;
            double x2 = 0.0D;
            double x3 = 0.0D;
            double x4 = 0.0D;

            for (int i = 0; i < xSize; ++i) {
                double xDouble = xOffset + (double)i * xScale + this.xCoord;
                int x = (int) xDouble;

                if (xDouble < (double) x) {
                    --x;
                }

                int xLimited = x & 255;
                xDouble -= (double)x;
                double xFaded = xDouble * xDouble * xDouble * (xDouble * (xDouble * 6.0D - 15.0D) + 10.0D);

                for (int k = 0; k < zSize; ++k) {
                    double zDouble = zOffset + (double) k * zScale + this.zCoord;
                    int z = (int) zDouble;

                    if (zDouble < (double) z) {
                        --z;
                    }

                    int zLimited = z & 255;
                    zDouble -= (double)z;
                    double zFaded = zDouble * zDouble * zDouble * (zDouble * (zDouble * 6.0D - 15.0D) + 10.0D);

                    for (int j = 0; j < ySize; ++j) {
                        double yDouble = yOffset + (double)j * yScale + this.yCoord;
                        int y = (int)yDouble;

                        if (yDouble < (double)y) {
                            --y;
                        }

                        int yLimited = y & 255;
                        yDouble -= (double)y;
                        double yFaded = yDouble * yDouble * yDouble * (yDouble * (yDouble * 6.0D - 15.0D) + 10.0D);

                        if (j == 0 || yLimited != var22) {
                            var22 = yLimited;
                            int aa = this.permutations[this.permutations[xLimited + 0] + yLimited + 0] + zLimited;
                            int ab = this.permutations[this.permutations[xLimited + 0] + yLimited + 1] + zLimited;
                            int ba = this.permutations[this.permutations[xLimited + 1] + yLimited + 0] + zLimited;
                            int bb = this.permutations[this.permutations[xLimited + 1] + yLimited + 1] + zLimited;
                            x1 = this.lerp(xFaded, this.grad(this.permutations[aa], xDouble, yDouble, zDouble), this.grad(this.permutations[ba], xDouble - 1.0D, yDouble, zDouble));
                            x2 = this.lerp(xFaded, this.grad(this.permutations[ab], xDouble, yDouble - 1.0D, zDouble), this.grad(this.permutations[bb], xDouble - 1.0D, yDouble - 1.0D, zDouble));
                            x3 = this.lerp(xFaded, this.grad(this.permutations[aa + 1], xDouble, yDouble, zDouble - 1.0D), this.grad(this.permutations[ba + 1], xDouble - 1.0D, yDouble, zDouble - 1.0D));
                            x4 = this.lerp(xFaded, this.grad(this.permutations[ab + 1], xDouble, yDouble - 1.0D, zDouble - 1.0D), this.grad(this.permutations[bb + 1], xDouble - 1.0D, yDouble - 1.0D, zDouble - 1.0D));
                        }

                        double xy1 = this.lerp(yFaded, x1, x2);
                        double xy2 = this.lerp(yFaded, x3, x4);
                        double xyz = this.lerp(zFaded, xy1, xy2);
                        noiseIndex = c++;
                        noiseArray[noiseIndex] += xyz * noiseScaleInverse;
                    }
                }
            }
        }
    }
}
