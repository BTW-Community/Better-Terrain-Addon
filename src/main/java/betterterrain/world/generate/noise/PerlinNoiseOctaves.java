package betterterrain.world.generate.noise;

import java.util.Random;

import net.minecraft.src.MathHelper;
import net.minecraft.src.NoiseGenerator;

public class PerlinNoiseOctaves extends NoiseGenerator {
    /**
     * Collection of noise generation functions.  Output is combined to produce different octaves of noise.
     */
    private PerlinNoiseGenerator[] generatorCollection;
    private int octaves;

    public PerlinNoiseOctaves(Random rand, int numOctaves) {
        this.octaves = numOctaves;
        this.generatorCollection = new PerlinNoiseGenerator[numOctaves];

        for (int i = 0; i < numOctaves; ++i) {
            this.generatorCollection[i] = new PerlinNoiseGenerator(rand);
        }
    }

    /**
     * pars:(par2,3,4=noiseOffset ; so that adjacent noise segments connect) (pars5,6,7=x,y,zArraySize),(pars8,10,12 =
     * x,y,z noiseScale)
     */
    public double[] generateNoiseOctaves3D(double[] noiseArray, int xOffset, int yOffset, int zOffset, int xSize, int ySize, int zSize, double xScale, double yScale, double zScale) {
        //Resets noise array
        if (noiseArray == null) {
            noiseArray = new double[xSize * ySize * zSize];
        }
        else {
            for (int i = 0; i < noiseArray.length; ++i) {
                noiseArray[i] = 0.0D;
            }
        }

        double octaveScale = 1.0D;

        for (int i = 0; i < this.octaves; ++i) {
            double xOffsetOctave = (double) xOffset * octaveScale * xScale;
            double yOffsetOctave = (double) yOffset * octaveScale * yScale;
            double zOffsetOctave = (double) zOffset * octaveScale * zScale;

            //Limits the integer part of the offset to be within 0 - 16777216 (Limit for 32-bit float)
            long xOffsetIPart = MathHelper.floor_double_long(xOffsetOctave);
            long zOffsetIPart = MathHelper.floor_double_long(zOffsetOctave);
            xOffsetOctave -= (double) xOffsetIPart;
            zOffsetOctave -= (double) zOffsetIPart;
            xOffsetIPart %= 16777216L;
            zOffsetIPart %= 16777216L;
            xOffsetOctave += (double) xOffsetIPart;
            zOffsetOctave += (double) zOffsetIPart;

            this.generatorCollection[i].populateNoiseArray3D(noiseArray, xOffsetOctave, yOffsetOctave, zOffsetOctave, xSize, ySize, zSize, xScale * octaveScale, yScale * octaveScale, zScale * octaveScale, octaveScale);
            octaveScale /= 2.0D;
        }

        return noiseArray;
    }

    /**
     * pars:(par2,3,4=noiseOffset ; so that adjacent noise segments connect) (pars5,6,7=x,y,zArraySize),(pars8,10,12 =
     * x,y,z noiseScale)
     */
    public double[] generateNoiseOctaves2D(double[] noiseArray, int xOffset, int zOffset, int xSize, int zSize, double xScale, double zScale) {
        //Resets noise array
        if (noiseArray == null) {
            noiseArray = new double[xSize * zSize];
        }
        else {
            for (int i = 0; i < noiseArray.length; ++i) {
                noiseArray[i] = 0.0D;
            }
        }

        double octaveScale = 1.0D;

        for (int i = 0; i < this.octaves; ++i) {
            double xOffsetOctave = (double) xOffset * octaveScale * xScale;
            double zOffsetOctave = (double) zOffset * octaveScale * zScale;

            //Limits the integer part of the offset to be within 0 - 16777216 (Limit for 32-bit float)
            long xOffsetIPart = MathHelper.floor_double_long(xOffsetOctave);
            long zOffsetIPart = MathHelper.floor_double_long(zOffsetOctave);
            xOffsetOctave -= (double) xOffsetIPart;
            zOffsetOctave -= (double) zOffsetIPart;
            xOffsetIPart %= 16777216L;
            zOffsetIPart %= 16777216L;
            xOffsetOctave += (double) xOffsetIPart;
            zOffsetOctave += (double) zOffsetIPart;

            this.generatorCollection[i].populateNoiseArray2D(noiseArray, xOffsetOctave, zOffsetOctave, xSize, zSize, xScale * octaveScale, zScale * octaveScale, octaveScale);
            octaveScale /= 2.0D;
        }

        return noiseArray;
    }
}
