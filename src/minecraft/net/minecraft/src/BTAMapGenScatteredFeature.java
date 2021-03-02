package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class BTAMapGenScatteredFeature extends BTAMapGenStructure
{
	public static final ArrayList<BiomeGenBase> biomelist = new ArrayList<BiomeGenBase>(Arrays.asList(new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland}));

    /** contains possible spawns for scattered features */
    private List scatteredFeatureSpawnList;

    /** the maximum distance between scattered features */
    private int maxDistanceBetweenScatteredFeatures = 32;

    /** the minimum distance between scattered features */
    private int minDistanceBetweenScatteredFeatures = 8;

    public BTAMapGenScatteredFeature()
    {
        this.scatteredFeatureSpawnList = new ArrayList();
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, int[] blockArray)
    {
        if (!this.structureMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(par2, par3))))
        {
            this.rand.nextInt();

            try
            {
                if (this.canSpawnStructureAtCoords(par2, par3))
                {
                    StructureStart var7 = this.getStructureStart(par2, par3);
                    this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(par2, par3)), var7);
                }
            }
            catch (Throwable var10)
            {
                CrashReport var8 = CrashReport.makeCrashReport(var10, "Exception preparing structure feature");
                CrashReportCategory var9 = var8.makeCategory("Feature being prepared");
                var9.addCrashSectionCallable("Is feature chunk", new BTACallableIsFeatureChunk(this, par2, par3));
                var9.addCrashSection("Chunk location", String.format("%d,%d", new Object[] {Integer.valueOf(par2), Integer.valueOf(par3)}));
                var9.addCrashSectionCallable("Chunk pos hash", new BTACallableChunkPosHash(this, par2, par3));
                var9.addCrashSectionCallable("Structure type", new BTACallableStructureType(this));
                throw new ReportedException(var8);
            }
        }
    }

    public BTAMapGenScatteredFeature(Map par1Map)
    {
        this();
        Iterator var2 = par1Map.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();

            if (((String)var3.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
            }
        }
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int var3 = par1;
        int var4 = par2;

        if (par1 < 0)
        {
            par1 -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int var5 = par1 / this.maxDistanceBetweenScatteredFeatures;
        int var6 = par2 / this.maxDistanceBetweenScatteredFeatures;
        Random var7 = this.worldObj.setRandomSeed(var5, var6, 14357617);
        var5 *= this.maxDistanceBetweenScatteredFeatures;
        var6 *= this.maxDistanceBetweenScatteredFeatures;
        var5 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
        var6 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);

        boolean isValidBiome = false;
        
        if (var3 == var5 && var4 == var6)
        {
            BiomeGenBase var8 = this.worldObj.getWorldChunkManager().getBiomeGenAt(var3 * 16 + 8, var4 * 16 + 8);
            Iterator var9 = biomelist.iterator();

            while (var9.hasNext())
            {
                BiomeGenBase var10 = (BiomeGenBase)var9.next();

                if (var8 == var10)
                {
                	return true;
                }
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureScatteredFeatureStart(this.worldObj, this.rand, par1, par2);
    }

    /**
     * returns possible spawns for scattered features
     */
    public List getScatteredFeatureSpawnList()
    {
        return this.scatteredFeatureSpawnList;
    }
}
