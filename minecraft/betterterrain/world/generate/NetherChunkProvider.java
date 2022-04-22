package betterterrain.world.generate;

import java.util.List;
import java.util.Random;

import betterterrain.biome.biomes.BTABiome;
import betterterrain.structure.mapgen.BTAMapGenBase;
import betterterrain.structure.mapgen.BTAMapGenCavesHell;
import betterterrain.structure.mapgen.BTAMapGenNetherBridge;
import betterterrain.world.BTAChunk;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SurfaceBuilder;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.NoiseGeneratorOctaves;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenFlowers;

public class NetherChunkProvider implements BTAChunkProvider
{
    private Random hellRNG;

    /** A NoiseGeneratorOctaves used in generating nether terrain */
    private NoiseGeneratorOctaves netherNoiseGen1;
    private NoiseGeneratorOctaves netherNoiseGen2;
    private NoiseGeneratorOctaves netherNoiseGen3;

    /** Determines whether slowsand or gravel can be generated at a location */
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;

    /**
     * Determines whether something other than nettherack can be generated at a location
     */
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves netherNoiseGen6;
    public NoiseGeneratorOctaves netherNoiseGen7;

    /** Is the world that the nether is getting generated. */
    private World worldObj;
    private double[] noiseArray;
    public BTAMapGenNetherBridge genNetherBridge = new BTAMapGenNetherBridge();

    /**
     * Holds the noise used to determine whether slowsand can be generated at a location
     */
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];

    /**
     * Holds the noise used to determine whether something other than netherrack can be generated at a location
     */
    private double[] netherrackExclusivityNoise = new double[256];
    private BTAMapGenBase netherCaveGenerator = new BTAMapGenCavesHell();
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;

	public WorldConfigurationInfo generatorInfo;
	private long seed;

	/** The biomes that are used to generate the chunk */
	private BiomeGenBase[] biomesForGeneration;

    public NetherChunkProvider(World world, long seed, WorldConfigurationInfo generatorInfo)
    {
        this.worldObj = world;
        this.hellRNG = new Random(seed);
        this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
        this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);

		this.generatorInfo = generatorInfo;
		this.seed = seed;
		
		SurfaceBuilder.initForNoiseField(this.seed);
    }

	/**
	 * Generates the shape of the terrain for the chunk though its all stone though the water is frozen if the
	 * temperature is low enough
	 */
	public void generateNetherTerrain(int chunkX, int chunkZ, int[] blockArray)
	{
		byte var4 = 4;
		byte var5 = 16;
		byte var6 = 32;
		int var7 = var4 + 1;
		byte var8 = 17;
		int var9 = var4 + 1;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, var7 + 5, var9 + 5);
		
		//if (this.generatorInfo.getCompatMode().isVersionAtLeast(BTAEnumVersionCompat.V1_4_0)) {
		//	this.noiseArray = BTASurfaceBuilder.initializeNoiseField(this.hellRNG, this.seed, this.noiseArray, chunkX * var4, 0, chunkZ * var4, var7, var8, var9, biomesForGeneration);
		//}
		//else {
			this.noiseArray = this.initializeNoiseField(this.noiseArray, chunkX * var4, 0, chunkZ * var4, var7, var8, var9);
		//}
		
		for (int var10 = 0; var10 < var4; ++var10)
		{
			for (int var11 = 0; var11 < var4; ++var11)
			{
				for (int var12 = 0; var12 < var5; ++var12)
				{
					double var13 = 0.125D;
					double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
					double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
					double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
					double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
					double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
					double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
					double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
					double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

					for (int var31 = 0; var31 < 8; ++var31)
					{
						double var32 = 0.25D;
						double var34 = var15;
						double var36 = var17;
						double var38 = (var19 - var15) * var32;
						double var40 = (var21 - var17) * var32;

						for (int var42 = 0; var42 < 4; ++var42)
						{
							int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
							short var44 = 128;
							var43 -= var44;
							double var45 = 0.25D;
							double var47 = (var36 - var34) * var45;
							double var49 = var34 - var47;

							for (int var51 = 0; var51 < 4; ++var51)
							{
								if ((var49 += var47) > 0.0D)
								{
									blockArray[var43 += var44] = (byte)Block.netherrack.blockID;
								}
								else if (var12 * 8 + var31 < var6)
								{
									blockArray[var43 += var44] = (byte)Block.lavaStill.blockID;
								}
								else
								{
									blockArray[var43 += var44] = 0;
								}
							}

							var34 += var38;
							var36 += var40;
						}

						var15 += var23;
						var17 += var25;
						var19 += var27;
						var21 += var29;
					}
				}
			}
		}
	}

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        this.hellRNG.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
		int[] blockArray = new int[32768];
		int[] metaArray = new int[32768];
		this.generateNetherTerrain(chunkX, chunkZ, blockArray);
		
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		SurfaceBuilder.replaceSurface(this.hellRNG, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);
		
        this.netherCaveGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
        this.genNetherBridge.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
        Chunk var4 = new BTAChunk(this.worldObj, blockArray, metaArray, chunkX, chunkZ);
        BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, chunkX * 16, chunkZ * 16, 16, 16);
        byte[] var6 = var4.getBiomeArray();

        for (int var7 = 0; var7 < var6.length; ++var7)
        {
            var6[var7] = (byte)var5[var7].biomeID;
        }

        var4.resetRelightChecks();
        return var4;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double var8 = 684.412D;
        double var10 = 2053.236D;
        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 60.0D, var8 / 80.0D);
        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;
        double[] var14 = new double[par6];
        int var15;

        for (var15 = 0; var15 < par6; ++var15)
        {
            var14[var15] = Math.cos((double)var15 * Math.PI * 6.0D / (double)par6) * 2.0D;
            double var16 = (double)var15;

            if (var15 > par6 / 2)
            {
                var16 = (double)(par6 - 1 - var15);
            }

            if (var16 < 4.0D)
            {
                var16 = 4.0D - var16;
                var14[var15] -= var16 * var16 * var16 * 10.0D;
            }
        }

        for (var15 = 0; var15 < par5; ++var15)
        {
            for (int var36 = 0; var36 < par7; ++var36)
            {
                double var17 = (this.noiseData4[var13] + 256.0D) / 512.0D;

                if (var17 > 1.0D)
                {
                    var17 = 1.0D;
                }

                double var19 = 0.0D;
                double var21 = this.noiseData5[var13] / 8000.0D;

                if (var21 < 0.0D)
                {
                    var21 = -var21;
                }

                var21 = var21 * 3.0D - 3.0D;

                if (var21 < 0.0D)
                {
                    var21 /= 2.0D;

                    if (var21 < -1.0D)
                    {
                        var21 = -1.0D;
                    }

                    var21 /= 1.4D;
                    var21 /= 2.0D;
                    var17 = 0.0D;
                }
                else
                {
                    if (var21 > 1.0D)
                    {
                        var21 = 1.0D;
                    }

                    var21 /= 6.0D;
                }

                var17 += 0.5D;
                var21 = var21 * (double)par6 / 16.0D;
                ++var13;

                for (int var23 = 0; var23 < par6; ++var23)
                {
                    double var24 = 0.0D;
                    double var26 = var14[var23];
                    double var28 = this.noiseData2[var12] / 512.0D;
                    double var30 = this.noiseData3[var12] / 512.0D;
                    double var32 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var32 < 0.0D)
                    {
                        var24 = var28;
                    }
                    else if (var32 > 1.0D)
                    {
                        var24 = var30;
                    }
                    else
                    {
                        var24 = var28 + (var30 - var28) * var32;
                    }

                    var24 -= var26;
                    double var34;

                    if (var23 > par6 - 4)
                    {
                        var34 = (double)((float)(var23 - (par6 - 4)) / 3.0F);
                        var24 = var24 * (1.0D - var34) + -10.0D * var34;
                    }

                    if ((double)var23 < var19)
                    {
                        var34 = (var19 - (double)var23) / 4.0D;

                        if (var34 < 0.0D)
                        {
                            var34 = 0.0D;
                        }

                        if (var34 > 1.0D)
                        {
                            var34 = 1.0D;
                        }

                        var24 = var24 * (1.0D - var34) + -10.0D * var34;
                    }

                    par1ArrayOfDouble[var12] = var24;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int chunkX, int chunkZ)
    {
        BlockSand.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;
		BiomeGenBase b = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);
        this.genNetherBridge.generateStructuresInChunk(this.worldObj, this.hellRNG, chunkX, chunkZ);
        
        int var7;
        int var8;
        int var9;

        if (this.hellRNG.nextInt(1) == 0)
        {
            var7 = x + this.hellRNG.nextInt(16) + 8;
            var8 = this.hellRNG.nextInt(128);
            var9 = z + this.hellRNG.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, this.hellRNG, var7, var8, var9);
        }
        
        if (b instanceof BTABiome)
			((BTABiome) b).decorate(this.worldObj, this.hellRNG, x, z, this.generatorInfo);
		else
			b.decorate(this.worldObj, this.hellRNG, x, z);

        BlockSand.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    public void func_104112_b() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "HellRandomLevelSource";
    }

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);

		if (biome != null) {
			if (creatureType == EnumCreatureType.monster && this.genNetherBridge.HasStructureAtLoose(x, y, z)) {
				List spawnList = this.genNetherBridge.getSpawnList();

				if (spawnList != null) {
					//spawnList.addAll(biome.getSpawnableList(creatureType));
					return spawnList;
				}
				else {
					return biome.getSpawnableList(creatureType);
				}
			}
			else {
				return biome.getSpawnableList(creatureType);
			}
		}
		else {
			return null;
		}
	}

	@Override
	public List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);

		if (biome != null) {
			if (creatureType == EnumCreatureType.monster && this.genNetherBridge.HasStructureAtLoose(x, y, z)) {
				List spawnList = this.genNetherBridge.getSpawnList();

				if (spawnList != null) {
					//spawnList.addAll(biome.getSpawnableList(creatureType));
					return spawnList;
				}
			}
		}
		
		return null;
	}
	
	public boolean doesStructureExistAtCoords(int x, int y, int z) {
		return this.genNetherBridge.HasStructureAtLoose(x, y, z);
	}

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2)
    {
        this.genNetherBridge.generate(this, this.worldObj, par1, par2, (int[])null);
    }
}
