package betterterrain.world.generate.provider;

import betterterrain.world.BTAChunk;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.noise.FastNoiseOctaves;
import betterterrain.world.generate.noise.OpenSimplexOctavesFast;
import betterterrain.world.generate.surface.SurfaceBuilder;
import lecho.lib.hellocharts.utils.SplineInterpolator;
import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.List;

public class BTAChunkProvider extends AbstractChunkProvider {
    public final FastNoiseOctaves terrainOctavesHigh;
    public final FastNoiseOctaves terrainOctavesLow;
    public final FastNoiseOctaves terrainOctavesBlend;

    public final FastNoiseOctaves terrainOctavesDetail;

    public final OpenSimplexOctavesFast continentalnessOctaves;
    public final OpenSimplexOctavesFast erosionOctaves;
    public final OpenSimplexOctavesFast peakValleyOctaves;

    public SplineInterpolator continentalnessInterpolator;
    public SplineInterpolator erosionInterpolator;
    public SplineInterpolator peakValleyInterpolator;

    int seaLevel = 100;

    private BiomeGenBase[] biomesForGeneration;

    public BTAChunkProvider(World world, long seed, boolean mapFeaturesEnabled, WorldConfigurationInfo generatorInfo) {
        super(world, seed, mapFeaturesEnabled, generatorInfo);

        SurfaceBuilder.initForNoiseField(this.seed);

        this.terrainOctavesHigh = new FastNoiseOctaves(this.seed, 6);
        this.terrainOctavesLow = new FastNoiseOctaves(this.seed + 1000, 10);
        this.terrainOctavesBlend = new FastNoiseOctaves(this.seed + 2000, 4);
        this.terrainOctavesDetail = new FastNoiseOctaves(this.seed + 3000, 4);

        this.continentalnessOctaves = new OpenSimplexOctavesFast(this.rand.nextLong(), 4);
        this.erosionOctaves = new OpenSimplexOctavesFast(this.rand.nextLong(), 4);
        this.peakValleyOctaves = new OpenSimplexOctavesFast(this.rand.nextLong(), 4);

        ArrayList<float[]> continentalnessSpline = new ArrayList<>();
        continentalnessSpline.add(new float[] {-1, -1});
        continentalnessSpline.add(new float[] {-0.75F, -1});
        continentalnessSpline.add(new float[] {-0.5F, -0.125F});
        continentalnessSpline.add(new float[] {-0, -0F});
        continentalnessSpline.add(new float[] {0.125F, 0.5F});
        continentalnessSpline.add(new float[] {0.5F, 0.875F});
        continentalnessSpline.add(new float[] {1, 1});
        continentalnessInterpolator = SplineInterpolator.createMonotoneCubicSpline(continentalnessSpline);
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        int[] blockArray = new int[65536];
        int[] metaArray = new int[65536];

        this.generateTerrain(chunkX, chunkZ, blockArray);

        Chunk chunk = new BTAChunk(this.worldObj, blockArray, metaArray, chunkX, chunkZ);
        byte[] chunkBiomeArray = chunk.getBiomeArray();

        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 16, 16);

        for (int i = 0; i < chunkBiomeArray.length; ++i) {
            chunkBiomeArray[i] = (byte)this.biomesForGeneration[i].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    public void generateTerrain(int chunkX, int chunkZ, int[] blockArray) {
        int x = chunkX * 16;
        int z = chunkZ * 16;

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                for (int j = 0; j < 256; j++) {
                    double density = this.getTerrainNoise(x + i, j, z + k);

                    if (density > 0) {
                        blockArray[i << 12 | k << 8 | j] = Block.stone.blockID;
                    }
                    else if (j < seaLevel) {
                        blockArray[i << 12 | k << 8 | j] = Block.waterStill.blockID;
                    }
                }
            }
        }
    }

    public double getTerrainNoise(int x, int y, int z) {
        double terrainScale = 1/Math.pow(2, 14);

        double noiseHigh = this.terrainOctavesHigh.noise3(x, y, z, terrainScale * 8, terrainScale * 8 / 2);
        double noiseLow = this.terrainOctavesLow.noise3(x, y, z, terrainScale, terrainScale / 2);

        double noiseBlend = this.terrainOctavesBlend.noise3(x, y, z, terrainScale / 2);

        double blendedNoise = lerp(noiseBlend, noiseHigh, noiseLow);

        double detailNoise = this.terrainOctavesDetail.noise3(x, y, z, terrainScale * Math.pow(2, 8));

        int detailFactor = 1;
        blendedNoise += detailNoise / detailFactor;
        blendedNoise = fitToRange(blendedNoise, -1 - 1D/detailFactor, 1 + 1D/detailFactor, -1, 1);

        return modifyNoise(blendedNoise, x, y, z);
    }

    public double modifyNoise(double noise, int x, int y, int z) {
        double squashFactor = this.getSquashFactor(x, z);
        double liftFactor = this.getLiftFactor(x, z);

        int heightDivideLevel = seaLevel + 25 + (int) (25 * liftFactor);
        int maxHeight = heightDivideLevel + (int) ((224 - heightDivideLevel) * squashFactor);
        int minHeight = heightDivideLevel - (int) (50 * squashFactor * (1 - liftFactor));

        double newNoise;

        if (y > maxHeight) {
            newNoise = -1;
        }
        else if (y < minHeight) {
            newNoise = 1;
        }
        else if (y >= heightDivideLevel) {
            newNoise = lerp((y - heightDivideLevel) / (double) (maxHeight - heightDivideLevel), noise, -1);
        }
        else {
            newNoise = lerp((y - minHeight) / (double) (heightDivideLevel - minHeight), 1, noise);
        }

        return newNoise;
    }

    public double getSquashFactor(int x, int z) {
        return fitToRange(this.getErosion(x, z), -1, 1, 0.125, 1);
    }

    public double getLiftFactor(int x, int z) {
        //return fitToRange(this.getInterpolatedContinentalness(x, z), -1, 1, 0.125, 1);
        return 0;
    }

    public double getContinentalness(int x, int z) {
        return this.continentalnessOctaves.noise2(x, z, 1/256D);
    }

    public double getInterpolatedContinentalness(int x, int z) {
        return continentalnessInterpolator.interpolate((float) (this.getContinentalness(x, z)));
    }

    public double getErosion(int x, int z) {
        return this.erosionOctaves.noise2(x, z, 1/256D);
    }

    public double getPeakValley(int x, int z) {
        return this.peakValleyOctaves.noise2(x, z, 1/256D);
    }

    public double lerp(double factor, double start, double end) {
        return start + factor * (end - start);
    }

    public double fitToRange(double input, double inputMin, double inputMax, double outputMin, double outputMax) {
        double dIn = inputMax - inputMin;
        double dOut = outputMax - outputMin;

        return ((input - inputMin) / dIn) * dOut + outputMin;
    }

    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {

    }

    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        return null;
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String structureName, int x, int y, int z) {
        return null;
    }

    @Override
    public void recreateStructures(int chunkX, int chunkZ) {

    }

    @Override
    public List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean doesStructureExistAtCoords(int x, int y, int z) {
        return false;
    }
}
