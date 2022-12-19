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
    public final FastNoiseOctaves terrainOctaves;

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

        this.terrainOctaves = new FastNoiseOctaves(this.seed, 10);

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

        double terrainScale = 1/8192D;

        int chunkIndex = 0;

        double[] terrainArray = new double[1];

        for (int i = 0; i < 16; i++) {
            for (int k = 0; k < 16; k++) {
                for (int j = 0; j < 256; j++) {
                    double density = this.getTerrainNoise(x + i, j, z + k, terrainScale);

                    double modifiedNoise = this.modifyNoise(density, x + i, j, z + k);

                    if (modifiedNoise > 0) {
                        blockArray[i << 12 | k << 8 | j] = Block.stone.blockID;
                    }
                    else if (j < seaLevel) {
                        blockArray[i << 12 | k << 8 | j] = Block.waterStill.blockID;
                    }

                    chunkIndex++;
                }
            }
        }
    }

    public double getTerrainNoise(int x, int y, int z, double terrainScale) {
        return this.terrainOctaves.noise3(x, y, z, terrainScale, terrainScale / 2);
    }

    public double modifyNoise(double noise, int x, int y, int z) {
        double squashFactor = this.getSquashFactor(x, z);
        double liftFactor = this.getLiftFactor(x, z);

        int heightDivideLevel = seaLevel + 25 + (int) (25 * liftFactor);
        int maxHeight = heightDivideLevel + (int) ((200 - heightDivideLevel) * squashFactor);
        int minHeight = heightDivideLevel - (int) (50 * squashFactor * liftFactor);

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
        return fitToRange(this.getInterpolatedContinentalness(x, z), -1, 1, 0.125, 1);
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
