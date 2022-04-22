package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTASkyChunkProvider implements BTAIChunkProvider
{
	private Random rand;
	private BTABetaNoiseOctaves field_912_k;
	private BTABetaNoiseOctaves field_911_l;
	private BTABetaNoiseOctaves field_910_m;
	private BTABetaNoiseOctaves field_909_n;
	private BTABetaNoiseOctaves field_908_o;
	public BTABetaNoiseOctaves field_922_a;
	public BTABetaNoiseOctaves field_921_b;
	public BTABetaNoiseOctaves mobSpawnerNoise;
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private double[] field_4180_q;
	private double[] sandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private double[] stoneNoise = new double[256];
	private BTAMapGenBase mapGenCaves = new BTAMapGenCave();
	private BTAMapGenStronghold strongholdGenerator = new BTAMapGenStronghold();
	public BTAMapGenVillage villageGenerator = new BTAMapGenVillage();
	private BTAMapGenMineshaft mineshaftGenerator = new BTAMapGenMineshaft();
	private BTAMapGenScatteredFeature scatteredFeatureGenerator = new BTAMapGenScatteredFeature();
	private BTAMapGenBase ravineGenerator = new BTAMapGenRavine();
    public BTAMapGenNetherBridge genNetherBridge = new BTAMapGenNetherBridge();
	private BiomeGenBase[] biomesForGeneration;
	private int worldtype;
	double[] field_4185_d;
	double[] field_4184_e;
	double[] field_4183_f;
	double[] field_4182_g;
	double[] field_4181_h;
	int[][] field_914_i = new int[32][32];
	private Random m_structureRand;
	private boolean isNether = false;
	
	private BTAWorldConfigurationInfo generatorInfo;
	
	private long seed;

	public BTASkyChunkProvider(World var1, long var2, boolean var4, BTAWorldConfigurationInfo generatorInfo)
	{
		this.worldObj = var1;
		this.rand = new Random(var2);
		this.m_structureRand = new Random(var2);
		this.mapFeaturesEnabled = var4;
		this.generatorInfo = generatorInfo;
		this.field_912_k = new BTABetaNoiseOctaves(this.rand, 16);
		this.field_911_l = new BTABetaNoiseOctaves(this.rand, 16);
		this.field_910_m = new BTABetaNoiseOctaves(this.rand, 8);
		this.field_909_n = new BTABetaNoiseOctaves(this.rand, 4);
		this.field_908_o = new BTABetaNoiseOctaves(this.rand, 4);
		this.field_922_a = new BTABetaNoiseOctaves(this.rand, 10);
		this.field_921_b = new BTABetaNoiseOctaves(this.rand, 16);
		this.mobSpawnerNoise = new BTABetaNoiseOctaves(this.rand, 8);
		this.seed = var2;
		
		BTASurfaceBuilder.initForNoiseField(this.seed);
	}
	
	public BTASkyChunkProvider setNether() {
		this.isNether = true;
		return this;
	}

	public void generateTerrain(int var1, int var2, int[] var3, BiomeGenBase[] var4)
    {
        byte var6 = 2;
        int var7 = var6 + 1;
        byte var8 = 33;
        int var9 = var6 + 1;
        this.field_4180_q = this.initializeNoiseField(this.field_4180_q, var1 * var6, 0, var2 * var6, var7, var8, var9);

        for (int var10 = 0; var10 < var6; ++var10)
        {
            for (int var11 = 0; var11 < var6; ++var11)
            {
                for (int var12 = 0; var12 < 32; ++var12)
                {
                    double var13 = 0.25D;
                    double var15 = this.field_4180_q[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.field_4180_q[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.field_4180_q[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.field_4180_q[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.field_4180_q[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.field_4180_q[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.field_4180_q[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.field_4180_q[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for (int var31 = 0; var31 < 4; ++var31)
                    {
                        double var32 = 0.125D;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for (int var42 = 0; var42 < 8; ++var42)
                        {
                            int var43 = var42 + var10 * 8 << 11 | 0 + var11 * 8 << 7 | var12 * 4 + var31;
                            short var44 = 128;
                            double var45 = 0.125D;
                            double var47 = var34;
                            double var49 = (var36 - var34) * var45;

                            for (int var51 = 0; var51 < 8; ++var51)
                            {
                                int var52 = 0;
                                
                                double threshold = -20D;
                                
                                //if (this.isEnd)
                                //	threshold = 0D;
                                
                                if (var47 > threshold)
                                {
                                	if (this.isNether)
                                		var52 = Block.netherrack.blockID;
                                	else
                                		var52 = Block.stone.blockID;
                                }

                                var3[var43] = var52;
                                var43 += var44;
                                var47 += var49;
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

	public void replaceBlocksForBiome(int var1, int var2, int[] var3, BiomeGenBase[] var4)
    {
        byte var5 = 64;
        double var6 = 0.03125D;
        this.sandNoise = this.field_909_n.generateNoiseOctaves(this.sandNoise, (double)(var1 * 16), (double)(var2 * 16), 0.0D, 16, 16, 1, var6, var6, 1.0D);
        this.gravelNoise = this.field_909_n.generateNoiseOctaves(this.gravelNoise, (double)(var1 * 16), 109.0134D, (double)(var2 * 16), 16, 1, 16, var6, 1.0D, var6);
        this.stoneNoise = this.field_908_o.generateNoiseOctaves(this.stoneNoise, (double)(var1 * 16), (double)(var2 * 16), 0.0D, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                BiomeGenBase var10 = var4[var8 + var9 * 16];
                boolean var11 = this.sandNoise[var8 + var9 * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
                boolean var12 = this.gravelNoise[var8 + var9 * 16] + this.rand.nextDouble() * 0.2D > 3.0D;
                int var13 = (int)(this.stoneNoise[var8 + var9 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var14 = -1;
                int var15;
				int var16;

				if (var10 instanceof BTABiomeGenBase) {
					var15 = ((BTABiomeGenBase) var10).topBlockExt;
					var16 = ((BTABiomeGenBase) var10).fillerBlockExt;
				}
				else {
					var15 = var10.topBlock;
					var16 = var10.fillerBlock;
				}

                for (int var17 = 127; var17 >= 0; --var17)
                {
                    int var18 = (var8 * 16 + var9) * 128 + var17;
                    int var19 = var3[var18];

                    if (var19 == 0)
                    {
                        var14 = -1;
                    }
                    else if (var19 == Block.stone.blockID)
                    {
                        if (var14 == -1)
                        {
                            var3[var18] = var15;
                            var14 = var13;

							if (var10.biomeID == BTABiomeConfiguration.badlandsPlateau.biomeID)
								var14 += 10;
                        }
                        else if (var14 > 0)
                        {
                            --var14;
                            var3[var18] = var16;

                            if (var14 == 0 && var16 == Block.sand.blockID)
                            {
                                var14 = this.rand.nextInt(4);
                                var16 = Block.sandStone.blockID;
                            }
							else if (BTADecoIntegration.isDecoInstalled() && var14 == 0 && var16 == BTADecoIntegration.redSand.blockID)
							{
								var14 = this.rand.nextInt(4);
								var16 = BTADecoIntegration.redSandStone.blockID;
							}
						}
                    }
                }
            }
        }
    }

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int var1, int var2)
	{
		return this.provideChunk(var1, var2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	public Chunk provideChunk(int chunkX, int chunkZ)
	{
		this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
		int[] blockArray = new int[32768];
		int[] metaArray = new int[32768];
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);

		this.generateTerrain(chunkX, chunkZ, blockArray, this.biomesForGeneration);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		BTASurfaceBuilder.replaceSurface(this.rand, this.seed, chunkX, chunkZ, blockArray, metaArray, biomesForGeneration, generatorInfo, this.worldObj.provider.terrainType);
		
		this.mapGenCaves.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		this.ravineGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			//this.villageGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			//this.strongholdGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
			this.scatteredFeatureGenerator.generate(this, this.worldObj, chunkX, chunkZ, blockArray);
		}

		Chunk chunk = new BTAChunk(this.worldObj, blockArray, metaArray, chunkX, chunkZ);
		byte[] var6 = chunk.getBiomeArray();

		for (int var7 = 0; var7 < var6.length; ++var7)
		{
			var6[var7] = (byte)this.biomesForGeneration[var7].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}



    private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        if (var1 == null)
        {
            var1 = new double[var5 * var6 * var7];
        }

        double var8 = 684.412D;
        double var10 = 684.412D;
        this.field_4182_g = this.field_922_a.generateNoiseOctaves(this.field_4182_g, var2, var4, var5, var7, 1.121D, 1.121D, 0.5D);
        this.field_4181_h = this.field_921_b.generateNoiseOctaves(this.field_4181_h, var2, var4, var5, var7, 200.0D, 200.0D, 0.5D);
        var8 *= 2.0D;
        this.field_4185_d = this.field_910_m.generateNoiseOctaves(this.field_4185_d, (double)var2, (double)var3, (double)var4, var5, var6, var7, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
        this.field_4184_e = this.field_912_k.generateNoiseOctaves(this.field_4184_e, (double)var2, (double)var3, (double)var4, var5, var6, var7, var8, var10, var8);
        this.field_4183_f = this.field_911_l.generateNoiseOctaves(this.field_4183_f, (double)var2, (double)var3, (double)var4, var5, var6, var7, var8, var10, var8);
        int var14 = 0;
        int var15 = 0;
        int var16 = 16 / var5;

        for (int var17 = 0; var17 < var5; ++var17)
        {
            int var18 = var17 * var16 + var16 / 2;

            for (int var19 = 0; var19 < var7; ++var19)
            {
                int var20 = var19 * var16 + var16 / 2;
                double var21;
                double var23;
                double var25 = (this.field_4182_g[var15] + 256.0D) / 512.0D;

                if (var25 > 1.0D)
                {
                    var25 = 1.0D;
                }

                double var27 = this.field_4181_h[var15] / 8000.0D;

                if (var27 < 0.0D)
                {
                    var27 = -var27 * 0.3D;
                }

                var27 = var27 * 3.0D - 2.0D;

                if (var27 > 1.0D)
                {
                    var27 = 1.0D;
                }

                var27 /= 8.0D;
                var27 = 0.0D;

                if (var25 < 0.0D)
                {
                    var25 = 0.0D;
                }

                var25 += 0.5D;
                var27 = var27 * (double)var6 / 16.0D;
                ++var15;
                double var29 = (double)var6 / 2.0D;

                for (int var31 = 0; var31 < var6; ++var31)
                {
                    double var32 = 0.0D;
                    double var34 = ((double)var31 - var29) * 8.0D / var25;

                    if (var34 < 0.0D)
                    {
                        var34 *= -1.0D;
                    }

                    double var36 = this.field_4184_e[var14] / 512.0D;
                    double var38 = this.field_4183_f[var14] / 512.0D;
                    double var40 = (this.field_4185_d[var14] / 10.0D + 1.0D) / 2.0D;

                    if (var40 < 0.0D)
                    {
                        var32 = var36;
                    }
                    else if (var40 > 1.0D)
                    {
                        var32 = var38;
                    }
                    else
                    {
                        var32 = var36 + (var38 - var36) * var40;
                    }

                    var32 -= 8.0D;
                    byte var42 = 32;
                    double var43;

                    if (var31 > var6 - var42)
                    {
                        var43 = (double)((float)(var31 - (var6 - var42)) / ((float)var42 - 1.0F));
                        var32 = var32 * (1.0D - var43) + -30.0D * var43;
                    }

                    var42 = 8;

                    if (var31 < var42)
                    {
                        var43 = (double)((float)(var42 - var31) / ((float)var42 - 1.0F));
                        var32 = var32 * (1.0D - var43) + -30.0D * var43;
                    }

                    var1[var14] = var32;
                    ++var14;
                }
            }
        }

        return var1;
    }

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int var1, int var2)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
		if (this.isNether) {
			this.populateNether(par1IChunkProvider, par2, par3);
			return;
		}
		
		BlockSand.fallInstantly = true;
		int var4 = par2 * 16;
		int var5 = par3 * 16;
		BiomeGenBase b = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		long var11 = this.rand.nextLong() / 2L * 2L + 1L;
		long var13 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
		boolean var15 = false;

		if (this.mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
			this.m_structureRand.setSeed((long)par2 * var11 + (long)par3 * var13 ^ this.worldObj.getSeed());
			var15 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, par2, par3);
			this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, par2, par3);
			this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.m_structureRand, par2, par3);
		}

		int var16;
		int var17;
		int var18;

		if (!var15 && this.rand.nextInt(1) == 0)
		{
			var16 = var4 + this.rand.nextInt(16) + 8;
			var17 = this.rand.nextInt(128);
			var18 = var5 + this.rand.nextInt(16) + 8;
			(new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var16, var17, var18);
		}
		
		for (int i = 0; i < 20; i++)
		{
			var16 = var4 + this.rand.nextInt(16) + 8;
			var17 = var5 + this.rand.nextInt(16) + 8;
			var18 = this.rand.nextInt(128);
			new WorldGenReed().generate(this.worldObj, this.rand, var16, var18, var17);
		}

		if (!var15 && this.rand.nextInt(4) == 0)
		{
			var16 = var4 + this.rand.nextInt(16) + 8;
			var17 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			var18 = var5 + this.rand.nextInt(16) + 8;

			if (var17 < 63 || this.rand.nextInt(10) == 0)
			{
				(new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, var16, var17, var18);
			}
		}

		for (var16 = 0; var16 < 16; ++var16)
		{
			var17 = var4 + this.rand.nextInt(16) + 8;
			var18 = this.rand.nextInt(128);
			int var19 = var5 + this.rand.nextInt(16) + 8;

			if ((new WorldGenDungeons()).generate(this.worldObj, this.rand, var17, var18, var19))
			{
				;
			}
		}

		if (b instanceof BTABiomeGenBase)
			((BTABiomeGenBase) b).decorate(this.worldObj, this.rand, var4, var5, this.generatorInfo);
		else
			b.decorate(this.worldObj, this.rand, var4, var5);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, b, var4 + 8, var5 + 8, 16, 16, this.rand);

		var4 += 8;
		var5 += 8;

		for (var16 = 0; var16 < 16; ++var16)
		{
			for (var17 = 0; var17 < 16; ++var17)
			{
				var18 = this.worldObj.getPrecipitationHeight(var4 + var16, var5 + var17);

				if (this.worldObj.isBlockFreezable(var16 + var4, var18 - 1, var17 + var5))
				{
					this.worldObj.setBlock(var16 + var4, var18 - 1, var17 + var5, Block.ice.blockID, 0, 2);
				}

				if (this.worldObj.canSnowAt(var16 + var4, var18, var17 + var5))
				{
					this.worldObj.setBlock(var16 + var4, var18, var17 + var5, Block.snow.blockID, 0, 2);
				}
			}
		}

		BlockSand.fallInstantly = false;
		this.BTWPostProcessChunk(this.worldObj, var4 - 8, var5 - 8);
	}

    /**
     * Populates chunk with ores etc etc
     */
    public void populateNether(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        this.genNetherBridge.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        int var6;
        int var7;
        int var8;
        int var9;

        for (var6 = 0; var6 < 8; ++var6)
        {
            var7 = var4 + this.rand.nextInt(16) + 8;
            var8 = this.rand.nextInt(120) + 4;
            var9 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenHellLava(Block.lavaMoving.blockID, false)).generate(this.worldObj, this.rand, var7, var8, var9);
        }

        var6 = this.rand.nextInt(this.rand.nextInt(10) + 10) + 1;
        int var10;

        for (var7 = 0; var7 < var6; ++var7)
        {
            var8 = var4 + this.rand.nextInt(16) + 8;
            var9 = this.rand.nextInt(120) + 4;
            var10 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenFire()).generate(this.worldObj, this.rand, var8, var9, var10);
        }

        var6 = this.rand.nextInt(this.rand.nextInt(10) + 1);

        for (var7 = 0; var7 < var6; ++var7)
        {
            var8 = var4 + this.rand.nextInt(16) + 8;
            var9 = this.rand.nextInt(120) + 4;
            var10 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenGlowStone1()).generate(this.worldObj, this.rand, var8, var9, var10);
        }

        for (var7 = 0; var7 < 20; ++var7)
        {
            var8 = var4 + this.rand.nextInt(16) + 8;
            var9 = this.rand.nextInt(128);
            var10 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenGlowStone2()).generate(this.worldObj, this.rand, var8, var9, var10);
        }

        if (this.rand.nextInt(1) == 0)
        {
            var7 = var4 + this.rand.nextInt(16) + 8;
            var8 = this.rand.nextInt(128);
            var9 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, this.rand, var7, var8, var9);
        }

        if (this.rand.nextInt(1) == 0)
        {
            var7 = var4 + this.rand.nextInt(16) + 8;
            var8 = this.rand.nextInt(128);
            var9 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, this.rand, var7, var8, var9);
        }

        WorldGenMinable var11 = new WorldGenMinable(Block.oreNetherQuartz.blockID, 13, Block.netherrack.blockID);
        int var12;

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = var4 + this.rand.nextInt(16);
            var10 = this.rand.nextInt(108) + 10;
            var12 = var5 + this.rand.nextInt(16);
            var11.generate(this.worldObj, this.rand, var9, var10, var12);
        }

        for (var8 = 0; var8 < 16; ++var8)
        {
            var9 = var4 + this.rand.nextInt(16) + 8;
            var10 = this.rand.nextInt(108) + 10;
            var12 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenHellLava(Block.lavaMoving.blockID, true)).generate(this.worldObj, this.rand, var9, var10, var12);
        }

        BlockSand.fallInstantly = false;
    }

	private void BTWPostProcessChunk(World var1, int var2, int var3)
	{
		if (var1.provider.dimensionId == 0)
		{
			this.GenerateStrata(var1, var2, var3);
			this.GenerateAdditionalBrownMushrooms(var1, var2, var3);
		}
	}

	private void GenerateAdditionalBrownMushrooms(World var1, int var2, int var3)
	{
		if (var1.rand.nextInt(4) == 0)
		{
			WorldGenFlowers var4 = new WorldGenFlowers(Block.mushroomBrown.blockID);
			int var5 = var2 + var1.rand.nextInt(16) + 8;
			int var6 = var1.rand.nextInt(25);
			int var7 = var3 + var1.rand.nextInt(16) + 8;
			var4.generate(var1, var1.rand, var5, var6, var7);
		}
	}

	private void GenerateStrata(World var1, int var2, int var3)
	{
		Chunk var4 = var1.getChunkFromChunkCoords(var2 >> 4, var3 >> 4);

		for (int var5 = 0; var5 < 16; ++var5)
		{
			for (int var6 = 0; var6 < 16; ++var6)
			{
				int var7 = 0;
				int var8;
				int var9;

				for (var8 = 32 + var1.rand.nextInt(2); var7 <= var8; ++var7)
				{
					var9 = var4.getBlockID(var5, var7, var6);

					if (var9 == Block.stone.blockID)
					{
						var4.setBlockMetadata(var5, var7, var6, 2);
					}
				}

				for (var8 = 48 + var1.rand.nextInt(2); var7 <= var8; ++var7)
				{
					var9 = var4.getBlockID(var5, var7, var6);

					if (var9 == Block.stone.blockID)
					{
						var4.setBlockMetadata(var5, var7, var6, 1);
					}
				}
			}
		}
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean var1, IProgressUpdate var2)
	{
		return true;
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	 */
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	public boolean unload100OldestChunks()
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
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);

		if (biome != null) {
			if (!this.isNether && creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(x, y, z)) {
				List spawnList = this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();

				if (spawnList != null) {
					return spawnList;
				}
				else {
					return biome.getSpawnableList(creatureType);
				}
			}
			else if (this.isNether && creatureType == EnumCreatureType.monster && this.genNetherBridge.HasStructureAtLoose(x, y, z)) {
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
			if (!this.isNether && creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.hasStructureAt(x, y, z)) {
				List spawnList = this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();

				if (spawnList != null) {
					return spawnList;
				}
			}
			else if (this.isNether && creatureType == EnumCreatureType.monster && this.genNetherBridge.HasStructureAtLoose(x, y, z)) {
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
		if (!this.isNether) {
			return this.scatteredFeatureGenerator.hasStructureAt(x, y, z);
		}
		else {
			return this.genNetherBridge.HasStructureAtLoose(x, y, z);
		}
	}

	/**
	 * Returns the location of the closest structure of the specified type. If not found returns null.
	 */
	public ChunkPosition findClosestStructure(World var1, String var2, int var3, int var4, int var5)
	{
		return "Stronghold".equals(var2) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(var1, var3, var4, var5) : null;
	}

	public int getLoadedChunkCount()
	{
		return 0;
	}

	public void recreateStructures(int var1, int var2)
	{
		if (this.mapFeaturesEnabled)
		{
			if (this.isNether) {
				this.genNetherBridge.generate(this, this.worldObj, var1, var2, (int[])null);
			}
			else {
				this.strongholdGenerator.generate(this, this.worldObj, var1, var2, (int[])null);
			}
		}
	}

	public void func_104112_b() {}
}
