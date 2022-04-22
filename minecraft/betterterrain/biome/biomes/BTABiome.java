package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.Version;
import betterterrain.biome.BiomeDecorator;
import betterterrain.biome.BiomeHeight;
import betterterrain.biome.Climate;
import betterterrain.feature.OreGen;
import betterterrain.feature.TallGrassGen;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SurfaceBuilder;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.StructureScatteredFeatureStart;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;

public class BTABiome extends BiomeGenBase {
    public int topBlockExt;
    public int fillerBlockExt;
    
	private boolean enableSnow;
	private boolean enableRain;
	private boolean isSpawnable;
	
	public Climate climate;
	
	public BiomeDecorator btaBiomeDecorator;
    private SurfaceBuilder surfaceBuilder;
    
    private BiomeHeight[] validHeights;
    
    private boolean isPlateau;
    private boolean isNether;
    
    private static WorldConfigurationInfo generatorInfoCache;

	protected BTABiome(int id, Climate climate) {
		super(id);
		this.enableRain = true;
		this.isSpawnable = true;
		this.btaBiomeDecorator = new BiomeDecorator(this);
        this.topBlockExt = Block.grass.blockID;
        this.fillerBlockExt = Block.dirt.blockID;
        this.climate = climate;
        this.isPlateau = false;
	}
	
	public BTABiome setValidHeights(BiomeHeight ... heights) {
		this.validHeights = heights;
		return this;
	}

    /**
     * Sets the temperature and rainfall of this biome.
     */
    public BTABiome setTemperatureRainfall(float par1, float par2)
    {
        if (par1 > 0.1F && par1 < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else
        {
            this.temperature = par1;
            this.rainfall = par2;
            return this;
        }
    }

    public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions)
    {
        this.btaBiomeDecorator.decorate(world, rand, chunkX, chunkZ);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random, WorldConfigurationInfo generatorOptions)
    {
        return this.getRandomWorldGenForTrees(par1Random);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random, WorldConfigurationInfo generatorOptions, WorldType worldType)
    {
        return this.getRandomWorldGenForTrees(par1Random, generatorOptions);
    }

    /**
     * Sets the minimum and maximum height of this biome. Seems to go from -2.0 to 2.0.
     */
    public BTABiome setMinMaxHeight(float par1, float par2)
    {
        this.minHeight = par1;
        this.maxHeight = par2;
        return this;
    }

    /**
     * Disable the rain for the biome.
     */
    public BTABiome setDisableRain()
    {
        this.enableRain = false;
        return this;
    }

    /**
     * sets enableSnow to true during biome initialization. returns BiomeGenBase.
     */
    public BTABiome setEnableSnow()
    {
        this.enableSnow = true;
        return this;
    }

    /**
     * Returns true if the biome have snowfall instead a normal rain.
     */
    public boolean getEnableSnow()
    {
        return this.enableSnow;
    }

    public BTABiome setBiomeName(String par1Str)
    {
        this.biomeName = par1Str;
        return this;
    }

    public BTABiome func_76733_a(int par1)
    {
        this.field_76754_C = par1;
        return this;
    }

    public BTABiome setColor(int par1)
    {
        this.color = par1;
        return this;
    }

    public boolean isSpawnable() {
		return isSpawnable;
	}

	public BTABiome setNotSpawnable() {
		this.isSpawnable = false;
		return this;
	}

	public boolean CanRainInBiome()
    {
        return this.enableSnow ? false : this.enableRain;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return this.CanRainInBiome();
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new TallGrassGen(Block.tallGrass.blockID, 1);
    }

    public SurfaceBuilder getSurfaceBuilder() {
		return surfaceBuilder;
	}
    
    public boolean canSlimesSpawnOnSurface() {
    	return StructureScatteredFeatureStart.swampBiomeList.contains(this);
    }

	public void setSurfaceBuilder(SurfaceBuilder surfaceBuilder) {
		this.surfaceBuilder = surfaceBuilder;
		if (this.surfaceBuilder.getBiome() == null) {
			this.surfaceBuilder.setBiome(this);
		}
	}

	public boolean isPlateau() {
		return isPlateau;
	}

	public BTABiome setPlateau() {
		this.isPlateau = true;
		return this;
	}

	public boolean isNether() {
		return isNether;
	}

	public BTABiome setNether() {
		this.isNether = true;
		this.setDisableRain();
		this.setTemperatureRainfall(2.0F, 0.0F);
		return this;
	}
    
	@Override
    public boolean canSnowAt(World world, int x, int y, int z) {
		if (generatorInfoCache == null || !generatorInfoCache.toString().equals(world.provider.generatorOptions)) {
			if (world.provider.generatorOptions.equals("")) {
				generatorInfoCache = WorldConfigurationInfo.createDefaultConfigurationLegacy(world.provider.terrainType.isDeco());
			}
			else {
				generatorInfoCache = WorldConfigurationInfo.createInfoFromString(world.provider.generatorOptions);
			}
		}
		
		if (generatorInfoCache.getCompatMode().isVersionAtLeast(Version.V1_3_0)) {
			int minHeightForSnow = this.climate.minHeightForSnow;
			
			SurfaceBuilder surfaceBuilder;
			
			if (this.surfaceBuilder == null) {
				surfaceBuilder = SurfaceBuilder.defaultBuilder;
				surfaceBuilder.setBiome(this);
			}
			else {
				surfaceBuilder = this.surfaceBuilder;
			}
			
			if (!surfaceBuilder.hasBeenInit()) {
				surfaceBuilder.init(world.rand, world.getSeed());
				surfaceBuilder.setHasBeenInit(true);
			}
			
			minHeightForSnow += surfaceBuilder.getSnowHeightOffset(x, z, world.rand);
			
			return y >= minHeightForSnow;
		}
		else {
			return this.getEnableSnow();
		}
    }

	public void AddEmeralds(World var1, Random var2, int var3, int var4)
    {
        int var5 = 3 + var2.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = var3 + var2.nextInt(16);
            int var8 = var2.nextInt(28) + 4;
            int var9 = var4 + var2.nextInt(16);

            if (var1.getBlockId(var7, var8, var9) == Block.stone.blockID)
            {
            	int var11 = 0;
                
                int[] stratas = var1.provider.terrainType.getStrataLevels();
                
                int strata1Height = stratas[0];
                int strata2Height = stratas[1];
                int strata3Height = -2;
                
                if (stratas.length > 2)
                	strata3Height = stratas[2];
            	
                if (var8 <= strata1Height + var1.rand.nextInt(2))
                {
                    var11 = 1;

                    if (var8 <= strata2Height + var1.rand.nextInt(2))
                    {
                        var11 = 2;
                    }

                    if (var8 <= strata3Height + var1.rand.nextInt(2))
                    {
                        var11 = 3;
                    }
                }

                var1.setBlock(var7, var8, var9, Block.oreEmerald.blockID, var11, 2);
            }
        }
    }

    public void AddSilverfishBlocks(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 7; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(64);
            int var8 = var4 + var2.nextInt(16);
            new OreGen(Block.silverfish.blockID, 0, 8, Block.stone.blockID).generate(var1, var2, var6, var7, var8);
        }
    }
    
    public void genStandardOre1(World world, Random rand, int count, WorldGenerator generator, int chunkX, int chunkZ, int minY, int maxY) {
		for (int i = 0; i < count; ++i) {
			int x = chunkX + rand.nextInt(16);
			int y = rand.nextInt(maxY - minY) + minY;
			int z = chunkZ + rand.nextInt(16);
			generator.generate(world, rand, x, y, z);
		}
	}
}