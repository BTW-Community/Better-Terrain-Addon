package betterterrain.biome;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import betterterrain.BTAVersion;
import betterterrain.biome.layer.HillsLayer;
import betterterrain.biome.layer.RiverLayer;
import betterterrain.biome.layer.ShoreLayer;
import betterterrain.biome.layer.SporadicLayer;
import betterterrain.feature.plant.MelonGen;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.feature.terrain.OreGen;
import betterterrain.structure.mapgen.BTAMapGenScatteredFeature;
import betterterrain.structure.mapgen.BTAMapGenVillage;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.config.WorldConfigurationInfoLegacy;
import betterterrain.world.generate.surface.SurfaceBuilder;
import betterterrain.world.util.WorldProviderInterface;
import betterterrain.world.util.WorldTypeInterface;
import btw.world.biome.BiomeDecoratorBase;
import net.minecraft.src.*;

public class BTABiome extends BiomeGenBase {
    public int topBlockExt;
    public int fillerBlockExt;
    
	private boolean enableSnow;
	private boolean enableRain;
	private boolean isSpawnable;
	
	public Climate climate;
	
	public BiomeDecorator btaBiomeDecorator;
    private SurfaceBuilder surfaceBuilder;
    
    private static WorldConfigurationInfo generatorInfoCache;
    
    private String internalName;
    
    public BTABiome(int id, String internalName, Climate climate) {
		super(id);
		
        this.setInternalName(internalName);
        
		this.enableRain = true;
		this.isSpawnable = true;
		this.btaBiomeDecorator = new BiomeDecorator(this);
		
        this.topBlockExt = Block.grass.blockID;
        this.fillerBlockExt = Block.dirt.blockID;
        
        this.climate = climate;
        
        this.isPlateau = false;
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

	public BTABiome setSurfaceBuilder(SurfaceBuilder surfaceBuilder) {
		this.surfaceBuilder = surfaceBuilder;
		
		if (this.surfaceBuilder.getBiome() == null) {
			this.surfaceBuilder.setBiome(this);
		}
		
		return this;
	}
    
	@Override
    public boolean canSnowAt(World world, int x, int y, int z) {
		if (generatorInfoCache == null || !generatorInfoCache.toString().equals(((WorldProviderInterface)world.provider).getGeneratorOptions())) {
			if (((WorldProviderInterface)world.provider).getGeneratorOptions().equals("")) {
				generatorInfoCache = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(((WorldTypeInterface) world.provider.terrainType).isDeco());
			}
			else {
				generatorInfoCache = WorldConfigurationInfo.createInfoFromString(((WorldProviderInterface)world.provider).getGeneratorOptions());
			}
		}
		
		if (generatorInfoCache.getBTAVersion().isVersionAtLeast(BTAVersion.V1_3_0)) {
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
	
	//------ Compatibility Functionality ------//
	
	boolean isDecoOnly;
	boolean isLegacyCompatible;
	
	public boolean isDecoOnly() {
		return isDecoOnly;
	}

	public BTABiome setDecoOnly() {
		this.isDecoOnly = true;
		return this;
	}

	public boolean isLegacyCompatible() {
		return isLegacyCompatible;
	}

	/**
	 * Only used for biomes in BTA 1.1.3 or before
	 * DO NOT USE except for those biomes as it will break backwards compatibility if you do
	 */
	public BTABiome setLegacyCompatible() {
		this.isLegacyCompatible = true;
		return this;
	}
	
	//------ Biome Variant Functionality ------//
	
	//--- Sub Biomes ---//

	//Different biome variants which spawn within the larger biome
	protected Map<BTABiome, WorldConfigurationInfo.Condition> subBiomeVariants = new LinkedHashMap();
	protected Map<BTABiome, WorldConfigurationInfo.Condition> subBiomeVariantsCommon = new LinkedHashMap();
	
	public BTABiome addSubVariant(BTABiome biome) {
		return this.addSubVariant(biome, null);
	}
	
	public BTABiome addSubVariant(BTABiome biome, WorldConfigurationInfo.Condition condition) {
		this.subBiomeVariants.put(biome, condition);
		return this;
	}
	
	public int getSubVariant(WorldConfigurationInfo generatorOptions, HillsLayer layer) {
		return this.getSubVariant(generatorOptions);
	}

	public int getSubVariant(WorldConfigurationInfo generatorOptions) {
		if (!this.subBiomeVariants.isEmpty()) {
			for (BTABiome biome : this.subBiomeVariants.keySet()) {
				if (this.subBiomeVariants.get(biome) == null || this.subBiomeVariants.get(biome).satisfiesContraints(generatorOptions)) {
					return biome.biomeID;
				}
			}
		}
		
		return this.biomeID;
	}
	
	public BTABiome addSubVariantCommon(BTABiome biome) {
		return this.addSubVariant(biome, null);
	}
	
	public BTABiome addSubVariantCommon(BTABiome biome, WorldConfigurationInfo.Condition condition) {
		this.subBiomeVariantsCommon.put(biome, condition);
		return this;
	}
	
	public int getSubVariantCommon(WorldConfigurationInfo generatorOptions, HillsLayer layer) {
		return this.getSubVariantCommon(generatorOptions);
	}

	public int getSubVariantCommon(WorldConfigurationInfo generatorOptions) {
		if (!this.subBiomeVariantsCommon.isEmpty()) {
			for (BTABiome biome : this.subBiomeVariantsCommon.keySet()) {
				if (this.subBiomeVariantsCommon.get(biome) == null || this.subBiomeVariantsCommon.get(biome).satisfiesContraints(generatorOptions)) {
					return biome.biomeID;
				}
			}
		}
		
		return this.biomeID;
	}
	
	//--- Sporadic Biomes ---//
	
	//Rare small sub biomes
	protected Map<BTABiome, WorldConfigurationInfo.Condition> sporadicVariants = new LinkedHashMap();
	//Chance the sporadic variant will generate
	protected Map<Integer, WorldConfigurationInfo.Condition> sporadicChances = new LinkedHashMap();
	
	public BTABiome addSporadicVariant(BTABiome biome) {
		return this.addSporadicVariant(biome, null);
	}
	
	public BTABiome addSporadicVariant(BTABiome biome, WorldConfigurationInfo.Condition condition) {
		this.sporadicVariants.put(biome, condition);
		return this;
	}
	
	public int getSporadicVariant(WorldConfigurationInfo generatorOptions, SporadicLayer layer) {
		return this.getSporadicVariant(generatorOptions);
	}

	public int getSporadicVariant(WorldConfigurationInfo generatorOptions) {
		if (!this.sporadicVariants.isEmpty()) {
			for (BTABiome biome : this.sporadicVariants.keySet()) {
				if (this.sporadicVariants.get(biome) == null || this.sporadicVariants.get(biome).satisfiesContraints(generatorOptions)) {
					return biome.biomeID;
				}
			}
		}
		
		return this.biomeID;
	}
	
	public BTABiome addSporadicChance(int chance) {
		return this.addSporadicChance(chance, null);
	}
	
	public BTABiome addSporadicChance(int chance, WorldConfigurationInfo.Condition condition) {
		this.sporadicChances.put(chance, condition);
		return this;
	}
	
	public int getSporadicChance(WorldConfigurationInfo generatorOptions, SporadicLayer layer) {
		return this.getSporadicChance(generatorOptions);
	}

	public int getSporadicChance(WorldConfigurationInfo generatorOptions) {
		if (!this.sporadicChances.isEmpty()) {
			for (int chance : this.sporadicChances.keySet()) {
				if (this.sporadicChances.get(chance) == null || this.sporadicChances.get(chance).satisfiesContraints(generatorOptions)) {
					return chance;
				}
			}
		}
		
		return 0;
	}
	
	//--- Beaches ---//
	
	//Beach variants
	protected Map<BTABiome, WorldConfigurationInfo.Condition> beachVariants = new LinkedHashMap();
	//Keeps track of whether the biome should spawn a beach
	protected Map<WorldConfigurationInfo.Condition, Boolean> hasBeach = new LinkedHashMap();
	
	public BTABiome addBeachVariant(BTABiome biome) {
		return this.addBeachVariant(biome, null);
	}
	
	public BTABiome addBeachVariant(BTABiome biome, WorldConfigurationInfo.Condition condition) {
		this.beachVariants.put(biome, condition);
		return this;
	}
	
	public int getBeachVariant(WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		return this.getBeachVariant(generatorOptions);
	}

	public int getBeachVariant(WorldConfigurationInfo generatorOptions) {
		if (!this.beachVariants.isEmpty()) {
			for (BTABiome biome : this.beachVariants.keySet()) {
				if (this.beachVariants.get(biome) == null || this.beachVariants.get(biome).satisfiesContraints(generatorOptions)) {
					return biome.biomeID;
				}
			}
		}
		
		return this.biomeID;
	}
	
	public BTABiome setHasBeach(boolean connect) {
		return this.setHasBeach(connect, null);
	}
	
	public BTABiome setHasBeach(boolean connect, WorldConfigurationInfo.Condition condition) {
		this.hasBeach.put(condition, connect);
		return this;
	}
	
	public boolean hasBeach(WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		return this.hasBeach(generatorOptions);
	}

	public boolean hasBeach(WorldConfigurationInfo generatorOptions) {
		if (!this.hasBeach.isEmpty()) {
			for (WorldConfigurationInfo.Condition condition : this.hasBeach.keySet()) {
				if (condition == null || condition.satisfiesContraints(generatorOptions)) {
					return this.hasBeach.get(condition);
				}
			}
		}
		
		return true;
	}
	
	//--- Rivers ---//
	
	//River biome to use when a river cuts through
	protected Map<BTABiome, WorldConfigurationInfo.Condition> riverVariants = new LinkedHashMap();
	
	public BTABiome addRiverVariant(BTABiome biome) {
		return this.addRiverVariant(biome, null);
	}
	
	public BTABiome addRiverVariant(BTABiome biome, WorldConfigurationInfo.Condition condition) {
		this.riverVariants.put(biome, condition);
		return this;
	}
	
	public int getRiverVariant(WorldConfigurationInfo generatorOptions, RiverLayer layer) {
		return this.getRiverVariant(generatorOptions);
	}

	public int getRiverVariant(WorldConfigurationInfo generatorOptions) {
		if (!this.riverVariants.isEmpty()) {
			for (BTABiome biome : this.riverVariants.keySet()) {
				if (this.riverVariants.get(biome) == null || this.riverVariants.get(biome).satisfiesContraints(generatorOptions)) {
					return biome.biomeID;
				}
			}
		}
		
		return this.biomeID;
	}
	
	//--- Edges ---//
	
	//Edge variants, used when transitioning disparate biomes
	protected Map<BTABiome, WorldConfigurationInfo.Condition> edgeVariants = new LinkedHashMap();
	//Controls whether an edge should be formed with this biome
	protected Map<WorldConfigurationInfo.Condition, Boolean> shouldConnectWithEdge = new LinkedHashMap();
	
	public BTABiome addEdgeVariant(BTABiome biome) {
		return this.addEdgeVariant(biome, null);
	}
	
	public BTABiome addEdgeVariant(BTABiome biome, WorldConfigurationInfo.Condition condition) {
		this.edgeVariants.put(biome, condition);
		return this;
	}
	
	public int getEdgeVariant(WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		return this.getEdgeVariant(generatorOptions);
	}

	public int getEdgeVariant(WorldConfigurationInfo generatorOptions) {
		if (!this.edgeVariants.isEmpty()) {
			for (BTABiome biome : this.edgeVariants.keySet()) {
				if (this.edgeVariants.get(biome) == null || this.edgeVariants.get(biome).satisfiesContraints(generatorOptions)) {
					return biome.biomeID;
				}
			}
		}
		
		return this.biomeID;
	}
	
	public BTABiome setConnectToEdge(boolean connect) {
		return this.setConnectToEdge(connect, null);
	}
	
	public BTABiome setConnectToEdge(boolean connect, WorldConfigurationInfo.Condition condition) {
		this.shouldConnectWithEdge.put(condition, connect);
		return this;
	}
	
	public boolean shouldConnectWithEdge(WorldConfigurationInfo generatorOptions, ShoreLayer layer) {
		return this.shouldConnectWithEdge(generatorOptions);
	}

	public boolean shouldConnectWithEdge(WorldConfigurationInfo generatorOptions) {
		if (!this.shouldConnectWithEdge.isEmpty()) {
			for (WorldConfigurationInfo.Condition condition : this.shouldConnectWithEdge.keySet()) {
				if (condition == null || condition.satisfiesContraints(generatorOptions)) {
					return this.shouldConnectWithEdge.get(condition) && this.getEdgeVariant(generatorOptions) == this.biomeID;
				}
			}
		}
		
		return getEdgeVariant(generatorOptions) == this.biomeID;
	}
	
	//------ Biome type Functionality ------//

    private boolean isPlateau;
    private boolean isNether;
	private boolean isEdge;
	private boolean isRiver;
	private boolean isBeach;

	public BTABiome setPlateau() {
		this.isPlateau = true;
		return this;
	}

	public boolean isPlateau() {
		return isPlateau;
	}

	public BTABiome setNether() {
		this.isNether = true;
		this.setDisableRain();
		this.setTemperatureRainfall(2.0F, 0.0F);
		return this;
	}

	public boolean isNether() {
		return isNether;
	}
	
	public BTABiome setEdge() {
		this.isEdge = true;
		return this;
	}
	
	public boolean isEdge() {
		return this.isEdge;
	}
	
	public BTABiome setRiver() {
		this.isRiver = true;
		this.addRiverVariant(this);
		return this;
	}
	
	public boolean isRiver() {
		return this.isRiver;
	}
	
	public BTABiome setBeach() {
		this.isBeach = true;
		this.addBeachVariant(this);
		return this;
	}
	
	public boolean isBeach() {
		return this.isBeach;
	}
	
	//------ Structure Functionality ------//
	
	private boolean canSpawnStronghold = true;
	
	public BTABiome setSpawnsVillages(boolean isDesert) {
		BTAMapGenVillage.villageSpawnBiomes.add(this);
		
		if (isDesert) {
			ComponentVillageStartPiece.addDesertBiome(this);
		}
		
		return this;
	}
	
	public BTABiome setSpawnsDesertTemples() {
		BTAMapGenScatteredFeature.biomelist.add(this);
		StructureScatteredFeatureStart.addDesertBiome(this);
		return this;
	}
	
	public BTABiome setSpawnsRedDesertTemples() {
		BTAMapGenScatteredFeature.biomelist.add(this);
		StructureScatteredFeatureStart.addRedDesertBiome(this);
		return this;
	}
	
	public BTABiome setSpawnsJungleTemples() {
		BTAMapGenScatteredFeature.biomelist.add(this);
		StructureScatteredFeatureStart.addJungleBiome(this);
		return this;
	}
	
	public BTABiome setSpawnsWitchHuts() {
		BTAMapGenScatteredFeature.biomelist.add(this);
		StructureScatteredFeatureStart.addSwampBiome(this);
		return this;
	}
	
	public BTABiome setSpawnsPumpkins() {
		WorldGenPumpkin.addBiomeToGenerator(this);
		return this;
	}
	
	public BTABiome setSpawnsMelons() {
		MelonGen.addBiomeToGenerator(this);
		return this;
	}
	
	public BTABiome setSpawnsSugarCane() {
		WorldGenReed.addBiomeToGenerator(this);
		return this;
	}
	
	public BTABiome setCannotSpawnStronghold() {
		this.canSpawnStronghold = false;
		return this;
	}
	
	public boolean canSpawnStronghold() {
		return this.canSpawnStronghold;
	}
	
	//------ Generator Functionality ------//
	
	/**
	 * Only used if better beaches is turned off
	 */
	public boolean spawnSand(WorldConfigurationInfo info) {
		return true;
	}

	public void addEmeralds(World world, Random rand, int startX, int startZ) {
        int count = 3 + rand.nextInt(6);

        for (int i = 0; i < count; ++i) {
            int x = startX + rand.nextInt(16);
            int y = rand.nextInt(28) + 4;
            int z = startZ + rand.nextInt(16);

            if (world.getBlockId(x, y, z) == Block.stone.blockID) {
            	int oreMeta = 0;
                
                int[] stratas = ((WorldTypeInterface) world.provider.terrainType).getStrataLevels();
                
                int strata1Height = stratas[0];
                int strata2Height = stratas[1];
                int strata3Height = -2;
                
                if (stratas.length > 2) {
                	strata3Height = stratas[2];
                }
            	
                if (y <= strata1Height + world.rand.nextInt(2)) {
                    oreMeta = 1;

                    if (y <= strata2Height + world.rand.nextInt(2))
                    {
                        oreMeta = 2;
                    }

                    if (y <= strata3Height + world.rand.nextInt(2))
                    {
                        oreMeta = 3;
                    }
                }

                world.setBlock(x, y, z, Block.oreEmerald.blockID, oreMeta, 2);
            }
        }
    }

    public void addSilverfishBlocks(World world, Random rand, int startX, int startZ) {
        for (int i = 0; i < 7; ++i) {
            int x = startX + rand.nextInt(16);
            int y = rand.nextInt(64);
            int z = startZ + rand.nextInt(16);
            new OreGen(Block.silverfish.blockID, 0, 8, Block.stone.blockID).generate(world, rand, x, y, z);
        }
    }
    
    public void genStandardOre1(World world, Random rand, int count, WorldGenerator generator, int startX, int startZ, int minY, int maxY) {
		for (int i = 0; i < count; ++i) {
			int x = startX + rand.nextInt(16);
			int y = rand.nextInt(maxY - minY) + minY;
			int z = startZ + rand.nextInt(16);
			generator.generate(world, rand, x, y, z);
		}
	}
    
    /**
     * Allows for intercepting other mods' custom decorators in case you want to use your own custom implementation
     * @return true if the decorator was intercepted, false otherwise
     */
    public boolean interceptCustomDecorator(BiomeDecoratorBase decorator, World world, Random rand, int x, int y) {
    	return false;
    }
    
    public static int getIDFromInternalName(String internalName) {
    	for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
    		BiomeGenBase b = BiomeGenBase.biomeList[i];
    		
    		if (b != null && b instanceof BTABiome && ((BTABiome) b).getInternalName().equals(internalName)) {
    			return b.biomeID;
    		}
    	}
    	
    	return -1;
    }

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
}