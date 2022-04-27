package betterterrain.biome;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.BTAVersion;
import betterterrain.feature.SkyClayGen;
import betterterrain.feature.ClayGen;
import betterterrain.feature.DecoFlowerGen;
import betterterrain.feature.FlowerGen;
import betterterrain.feature.MelonGen;
import betterterrain.feature.OreGen;
import betterterrain.feature.MyceliumGen;
import betterterrain.feature.OasisGen;
import betterterrain.feature.SteppeGen;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.BTADefaultChunkProvider;
import betterterrain.world.generate.surface.SurfaceBuilder;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkProviderServer;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenBigMushroom;
import net.minecraft.src.WorldGenCactus;
import net.minecraft.src.WorldGenDeadBush;
import net.minecraft.src.WorldGenLiquids;
import net.minecraft.src.WorldGenPumpkin;
import net.minecraft.src.WorldGenReed;
import net.minecraft.src.WorldGenSand;
import net.minecraft.src.WorldGenWaterlily;
import net.minecraft.src.WorldGenerator;

public class BiomeDecorator
{
	/** The world the BiomeDecorator is currently decorating */
	protected World currentWorld;

	/** The Biome Decorator's random number generator. */
	protected Random randomGenerator;

	/** The X-coordinate of the chunk currently being decorated */
	protected int chunk_X;

	/** The Z-coordinate of the chunk currently being decorated */
	protected int chunk_Z;

	/** The biome generator object. */
	protected BiomeGenBase biome;

	/** The clay generator. */
	protected WorldGenerator clayGen = new ClayGen(4);
	protected WorldGenerator clayGenSky = new SkyClayGen(4);

	/** The sand generator. */
	protected WorldGenerator sandGen;

	/** The gravel generator. */
	protected WorldGenerator gravelAsSandGen;

	/** The dirt generator. */
	protected WorldGenerator dirtGen;
	protected WorldGenerator gravelGen;
	protected WorldGenerator coalGen;
	protected WorldGenerator ironGen;

	/** Field that holds gold WorldGenMinable */
	protected WorldGenerator goldGen;

	/** Field that holds redstone WorldGenMinable */
	protected WorldGenerator redstoneGen;

	/** Field that holds diamond WorldGenMinable */
	protected WorldGenerator diamondGen;

	/** Field that holds Lapis WorldGenMinable */
	protected WorldGenerator lapisGen;

	/** Field that holds one of the plantYellow WorldGenFlowers */
	protected WorldGenerator plantYellowGen;

	/** Field that holds one of the plantRed WorldGenFlowers */
	protected WorldGenerator plantRedGen;

	/** Field that holds mushroomBrown WorldGenFlowers */
	protected WorldGenerator mushroomBrownGen;

	/** Field that holds mushroomRed WorldGenFlowers */
	protected WorldGenerator mushroomRedGen;

	/** Field that holds big mushroom generator */
	protected WorldGenerator bigMushroomGen;

	/** Field that holds WorldGenReed */
	protected WorldGenerator reedGen;

	/** Field that holds WorldGenCactus */
	protected WorldGenerator cactusGen;

	/** The water lily generation! */
	protected WorldGenerator waterlilyGen;

	/** Amount of waterlilys per chunk. */
	public int waterlilyPerChunk;

	/**
	 * The number of trees to attempt to generate per chunk. Up to 10 in forests, none in deserts.
	 */
	public int treesPerChunk;

	/**
	 * The number of yellow flower patches to generate per chunk. The game generates much less than this number, since
	 * it attempts to generate them at a random altitude.
	 */
	public int flowersPerChunk;

	/** The amount of tall grass to generate per chunk. */
	public int grassPerChunk;

	/**
	 * The number of dead bushes to generate per chunk. Used in deserts and swamps.
	 */
	public int deadBushPerChunk;

	/**
	 * The number of extra mushroom patches per chunk. It generates 1/4 this number in brown mushroom patches, and 1/8
	 * this number in red mushroom patches. These mushrooms go beyond the default base number of mushrooms.
	 */
	public int mushroomsPerChunk;

	/**
	 * The number of reeds to generate per chunk. Reeds won't generate if the randomly selected placement is unsuitable.
	 */
	public int reedsPerChunk;

	/**
	 * The number of cactus plants to generate per chunk. Cacti only work on sand.
	 */
	public int cactiPerChunk;

	/**
	 * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater.
	 */
	public int sandPerChunk;

	/**
	 * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater. There
	 * appear to be two separate fields for this.
	 */
	public int sandPerChunk2;

	/**
	 * The number of clay patches to generate per chunk. Only generates when part of it is underwater.
	 */
	public int clayPerChunk;

	/** Amount of big mushrooms per chunk */
	protected int bigMushroomsPerChunk;

	/** True if decorator should generate surface lava & water */
	public boolean generateLakes;

	//BTA
	protected WorldGenerator oasisGen;
	protected WorldGenerator pumpkinGen;
	protected WorldGenerator steppeGen;
	protected WorldGenerator steppeGenGravel;
	protected WorldGenerator stoneInGrassGen;
	protected WorldGenerator stoneInGrassGen2;
	protected WorldGenerator bigRedMushroomGen;
	protected WorldGenerator outbackGen;
	protected WorldGenerator decoFlowerGen;
	protected WorldGenerator graniteGen;
	protected WorldGenerator andesiteGen;
	protected WorldGenerator dioriteGen;
	protected WorldGenerator slateGen;
	protected WorldGenerator melonGen;

	public int oasesPerChunk;
	public int waterLakesPerChunk;
	public int lavaLakesPerChunk;
	public int steppePerChunk;
	public int steppeGravelPerChunk;
	public int bigRedMushroomsPerChunk;
	public boolean generateStoneInGrass;
	public boolean generateStoneInGrass2;
	public boolean generateOutback;

	public int fractionalTreeChance;
	public int melonChancePerChunk;

	private WorldConfigurationInfo generatorInfo;

	public BiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		this.sandGen = new WorldGenSand(7, Block.sand.blockID);
		this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
		this.dirtGen = new OreGen(Block.dirt.blockID, 0, 32, Block.stone.blockID);
		this.gravelGen = new OreGen(Block.gravel.blockID, 0, 32, Block.stone.blockID);
		this.coalGen = new OreGen(Block.oreCoal.blockID, 0, 16, Block.stone.blockID);
		this.ironGen = new OreGen(Block.oreIron.blockID, 0, 8, Block.stone.blockID);
		this.goldGen = new OreGen(Block.oreGold.blockID, 0, 8, Block.stone.blockID);
		this.redstoneGen = new OreGen(Block.oreRedstone.blockID, 0, 7, Block.stone.blockID);
		this.diamondGen = new OreGen(Block.oreDiamond.blockID, 0, 7, Block.stone.blockID);
		this.lapisGen = new OreGen(Block.oreLapis.blockID, 0, 6, Block.stone.blockID);
		this.plantYellowGen = new FlowerGen(Block.plantYellow.blockID);
		this.plantRedGen = new FlowerGen(Block.plantRed.blockID);
		this.mushroomBrownGen = new FlowerGen(Block.mushroomBrown.blockID);
		this.mushroomRedGen = new FlowerGen(Block.mushroomRed.blockID);
		this.bigMushroomGen = new WorldGenBigMushroom();
		this.reedGen = new WorldGenReed();
		this.cactusGen = new WorldGenCactus();
		this.waterlilyGen = new WorldGenWaterlily();
		this.waterlilyPerChunk = 0;
		this.treesPerChunk = 0;
		this.flowersPerChunk = 2;
		this.grassPerChunk = 1;
		this.deadBushPerChunk = 0;
		this.mushroomsPerChunk = 0;
		this.reedsPerChunk = 0;
		this.cactiPerChunk = 0;
		this.sandPerChunk = 1;
		this.sandPerChunk2 = 3;
		this.clayPerChunk = 1;
		this.bigMushroomsPerChunk = 0;
		this.generateLakes = true;
		this.biome = par1BiomeGenBase;

		//BTA
		pumpkinGen = new WorldGenPumpkin();
		melonGen = new MelonGen();
		oasisGen = new OasisGen(7, Block.grass.blockID);
		steppeGen = new SteppeGen(Block.sand.blockID, 0);
		stoneInGrassGen = new MyceliumGen(Block.stone.blockID, 32);
		stoneInGrassGen2 = new OreGen(Block.stone.blockID, 48);
		bigRedMushroomGen = new WorldGenBigMushroom(1);
		decoFlowerGen = new DecoFlowerGen();

		if (DecoIntegration.isDecoInstalled()) {
			outbackGen = new MyceliumGen(Block.grass.blockID, 48, DecoIntegration.redSand.blockID);

			graniteGen = new OreGen(DecoIntegration.stoneTypes.blockID, 0, 32, Block.stone.blockID);
			andesiteGen = new OreGen(DecoIntegration.stoneTypes.blockID, 1, 32, Block.stone.blockID);
			dioriteGen = new OreGen(DecoIntegration.stoneTypes.blockID, 2, 32, Block.stone.blockID);
			slateGen = new OreGen(Block.stone.blockID, 3, 32, Block.stone.blockID);
		}

		oasesPerChunk = 0;
		steppePerChunk = 0;
		waterLakesPerChunk = 50;
		lavaLakesPerChunk = 20;
		bigRedMushroomsPerChunk = 0;
		fractionalTreeChance = 10;
		melonChancePerChunk = 24;
	}

	/**
	 * Decorates the world. Calls code that was formerly (pre-1.8) in ChunkProviderGenerate.populate
	 */
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		if (this.currentWorld != null)
		{
			//throw new RuntimeException("Already decorating!!");
			FCAddOnHandler.LogWarning("WARNING: Already decorating!");
		}
		else
		{
			this.currentWorld = par1World;
			if (this.currentWorld.provider.generatorOptions.equals("")) {
				this.generatorInfo = WorldConfigurationInfo.createDefaultConfigurationLegacy(this.currentWorld.provider.terrainType.isDeco());
			}
			else {
				this.generatorInfo = WorldConfigurationInfo.createInfoFromString(this.currentWorld.provider.generatorOptions);
			}
			this.randomGenerator = par2Random;
			this.chunk_X = par3;
			this.chunk_Z = par4;
			this.decorate();
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}

	public void init(World par1World, Random par2Random, int par3, int par4) {
		this.currentWorld = par1World;
		if (this.currentWorld.provider.generatorOptions.equals("")) {
			this.generatorInfo = WorldConfigurationInfo.createDefaultConfigurationLegacy(this.currentWorld.provider.terrainType.isDeco());
		}
		else {
			this.generatorInfo = WorldConfigurationInfo.createInfoFromString(this.currentWorld.provider.generatorOptions);
		}
		this.randomGenerator = par2Random;
		this.chunk_X = par3;
		this.chunk_Z = par4;
		this.currentWorld = null;
		this.randomGenerator = null;
	}

	/**
	 * The method that does the work of actually decorating chunks
	 */
	protected void decorate()
	{
		this.generateOres();
		int var1;
		int var2;
		int var3;

		for (var1 = 0; var1 < oasesPerChunk; ++var1)
		{
			try
			{
				var2 = chunk_X + randomGenerator.nextInt(16) + 8;
				var3 = chunk_Z + randomGenerator.nextInt(16) + 8;
				oasisGen.generate(currentWorld, randomGenerator, var2, currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}
			catch (Exception e)
			{

			}
		}

		for (var1 = 0; var1 < this.sandPerChunk2; ++var1)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			if (this.currentWorld.provider.terrainType.canPerlinBeachesBeToggled() && !(this.currentWorld.getChunkProvider() instanceof ChunkProviderServer))
				if (!this.generatorInfo.generatePerlinBeaches())
					if (this.biome != BetterBiomesConfiguration.tropics || ((BTADefaultChunkProvider) this.currentWorld.getChunkProvider()).generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_1_3))
						this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		}

		for (var1 = 0; var1 < this.clayPerChunk; ++var1)
		{
			if (this.currentWorld.rand.nextInt(3) != 0) {
				var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				if (this.currentWorld.provider.terrainType.isSky()) {
					if (this.randomGenerator.nextInt(5) == 0)
						this.clayGenSky.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
				}
				else {
					this.clayGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
				}
			}
		}

		for (var1 = 0; var1 < this.sandPerChunk; ++var1)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			if (this.currentWorld.provider.terrainType.canPerlinBeachesBeToggled() && !(this.currentWorld.getChunkProvider() instanceof ChunkProviderServer))
				if (!this.generatorInfo.generatePerlinBeaches())
					if (this.biome != BetterBiomesConfiguration.tropics || ((BTADefaultChunkProvider) this.currentWorld.getChunkProvider()).generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_1_3))
						this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		}

		if (generateOutback && this.generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_2_1))
		{
			this.genStandardOre1(30, outbackGen, 64, 128);
		}

		var1 = this.treesPerChunk;

		if (this.randomGenerator.nextInt(fractionalTreeChance) == 0)
		{
			++var1;
		}

		SurfaceBuilder.generateTrees(this.currentWorld, this.randomGenerator, this.currentWorld.getSeed(), this.generatorInfo, this.chunk_X, this.chunk_Z, (BTABiome) this.biome);

		int var4;

		for (var2 = 0; var2 < this.bigMushroomsPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
		}

		int var7;

		for (var2 = 0; var2 < this.flowersPerChunk; ++var2)
		{
			if (DecoIntegration.isDecoInstalled() && (this.currentWorld.provider.terrainType.isDeco())) {
				if (this.randomGenerator.nextInt(24) > 1) {
					var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
					var4 = this.randomGenerator.nextInt(128);
					var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
					this.decoFlowerGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
				}
				else {
					var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
					var4 = this.randomGenerator.nextInt(128);
					var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
					this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);

					var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
					var4 = this.randomGenerator.nextInt(128);
					var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
					this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
				}
			}
			else {
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(128);
				var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);

				if (this.randomGenerator.nextInt(4) == 0)
				{
					var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
					var4 = this.randomGenerator.nextInt(128);
					var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
					this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
				}
			}
		}

		for (var2 = 0; var2 < this.grassPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(128);
			var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			WorldGenerator var6 = this.biome.getRandomWorldGenForGrass(this.randomGenerator);
			var6.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
		}

		for (var2 = 0; var2 < this.deadBushPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(128);
			var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			(new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
		}

		for (var2 = 0; var2 < this.waterlilyPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

			for (var7 = this.randomGenerator.nextInt(128); var7 > 0 && this.currentWorld.getBlockId(var3, var7 - 1, var4) == 0; --var7)
			{
				;
			}

			this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
		}

		for (var2 = 0; var2 < this.mushroomsPerChunk; ++var2)
		{
			if (this.randomGenerator.nextInt(4) == 0)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				var7 = this.currentWorld.getHeightValue(var3, var4);
				this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
			}

			if (this.randomGenerator.nextInt(8) == 0)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				var7 = this.randomGenerator.nextInt(128);
				this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
			}
		}

		if (this.randomGenerator.nextInt(4) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(128);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		if (this.randomGenerator.nextInt(8) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(128);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		for (var2 = 0; var2 < this.reedsPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			var7 = this.randomGenerator.nextInt(128);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);

			if (this.currentWorld.provider.terrainType.isSky()) {
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				var7 = this.randomGenerator.nextInt(128);
				this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
			}
		}

		if (this.randomGenerator.nextInt(32) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(128);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.pumpkinGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		if (this.randomGenerator.nextInt(melonChancePerChunk) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(128);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.melonGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		//BTA modified
		if (this.generateLakes || generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V2_0_2))
		{
			for (var2 = 0; var2 < waterLakesPerChunk; ++var2)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
				var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
			}

			for (var2 = 0; var2 < lavaLakesPerChunk; ++var2)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
				var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
			}
		}

		for (var2 = 0; var2 < steppePerChunk; ++var2)
		{
			try
			{
				var3 = chunk_X + randomGenerator.nextInt(16) + 8;
				var4 = randomGenerator.nextInt(128);
				var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
				steppeGen.generate(currentWorld, randomGenerator, var3, var4, var7);
			}
			catch (Exception e)
			{

			}
		}

		for (var2 = 0; var2 < this.bigRedMushroomsPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.bigRedMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
		}

		if (generateStoneInGrass && this.generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4))
		{
			this.genStandardOre1(15, stoneInGrassGen, 64, 128);
		}

		if (generateStoneInGrass2 && this.generatorInfo.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_3_4))
		{
			this.genStandardOre1(20, stoneInGrassGen2, 64, 128);
		}

		for (var2 = 0; var2 < this.cactiPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(128);
			var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.cactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
		}
	}

	/**
	 * Standard ore generation helper. Generates most ores.
	 */
	public void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	{
		for (int var5 = 0; var5 < par1; ++var5)
		{
			int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
			int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
			int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
			par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
		}
	}

	/**
	 * Standard ore generation helper. Generates Lapis Lazuli.
	 */
	public void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	{
		for (int var5 = 0; var5 < par1; ++var5)
		{
			int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
			int var7 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
			int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
			par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
		}
	}

	/**
	 * Generates ores in the current chunk
	 */
	protected void generateOres()
	{
		this.genStandardOre1(20, this.dirtGen, 0, 128);
		this.genStandardOre1(10, this.gravelGen, 0, 128);

		if (this.currentWorld.provider.terrainType.isSky()) {
			this.genStandardOre1(20, this.coalGen, 0, 128);
			this.genStandardOre1(24, this.ironGen, 0, 80);
			this.genStandardOre1(3, this.goldGen, 0, 48);
			this.genStandardOre1(12, this.redstoneGen, 0, 32);
			this.genStandardOre1(2, this.diamondGen, 16, 32);
			this.genStandardOre2(1, this.lapisGen, 32, 16);
		}
		else {
			this.genStandardOre1(20, this.coalGen, 0, 128);
			this.genStandardOre1(20, this.ironGen, 0, 64);
			this.genStandardOre1(2, this.goldGen, 0, 32);
			this.genStandardOre1(8, this.redstoneGen, 0, 16);
			this.genStandardOre1(1, this.diamondGen, 0, 16);
			this.genStandardOre2(1, this.lapisGen, 16, 16);
		}

		if (DecoIntegration.isDecoInstalled() && (this.currentWorld.provider.terrainType.isDeco())) {
			this.genStandardOre1(12, this.graniteGen, 0, 128);
			this.genStandardOre1(12, this.andesiteGen, 0, 128);
			this.genStandardOre1(12, this.dioriteGen, 0, 128);
			this.genStandardOre1(12, this.slateGen, 0, this.currentWorld.provider.terrainType.getStrataLevels()[1]);
		}
	}
}