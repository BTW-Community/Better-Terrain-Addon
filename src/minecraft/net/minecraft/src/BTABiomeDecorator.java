package net.minecraft.src;

import java.util.Random;

public class BTABiomeDecorator
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
	protected WorldGenerator clayGen = new BTAWorldGenClay(4);

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
	protected int waterlilyPerChunk;

	/**
	 * The number of trees to attempt to generate per chunk. Up to 10 in forests, none in deserts.
	 */
	protected int treesPerChunk;

	/**
	 * The number of yellow flower patches to generate per chunk. The game generates much less than this number, since
	 * it attempts to generate them at a random altitude.
	 */
	protected int flowersPerChunk;

	/** The amount of tall grass to generate per chunk. */
	protected int grassPerChunk;

	/**
	 * The number of dead bushes to generate per chunk. Used in deserts and swamps.
	 */
	protected int deadBushPerChunk;

	/**
	 * The number of extra mushroom patches per chunk. It generates 1/4 this number in brown mushroom patches, and 1/8
	 * this number in red mushroom patches. These mushrooms go beyond the default base number of mushrooms.
	 */
	protected int mushroomsPerChunk;

	/**
	 * The number of reeds to generate per chunk. Reeds won't generate if the randomly selected placement is unsuitable.
	 */
	protected int reedsPerChunk;

	/**
	 * The number of cactus plants to generate per chunk. Cacti only work on sand.
	 */
	protected int cactiPerChunk;

	/**
	 * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater.
	 */
	protected int sandPerChunk;

	/**
	 * The number of sand patches to generate per chunk. Sand patches only generate when part of it is underwater. There
	 * appear to be two separate fields for this.
	 */
	protected int sandPerChunk2;

	/**
	 * The number of clay patches to generate per chunk. Only generates when part of it is underwater.
	 */
	protected int clayPerChunk;

	/** Amount of big mushrooms per chunk */
	protected int bigMushroomsPerChunk;

	/** True if decorator should generate surface lava & water */
	public boolean generateLakes;

	//BTWG
	protected WorldGenerator oasisGen;
	protected WorldGenerator pumpkinGen;
	protected WorldGenerator steppeGen;
	protected WorldGenerator steppeGenGravel;
	protected WorldGenerator stoneInGrassGen;
	protected WorldGenerator stoneInGrassGen2;
	protected WorldGenerator bigRedMushroomGen;
	protected WorldGenerator outbackGen;
	protected WorldGenerator decoFlowerGen;
	protected WorldGenerator andesiteGen;
	protected WorldGenerator dioriteGen;
	protected WorldGenerator graniteGen;

	public int oasesPerChunk;
	public int waterLakesPerChunk;
	public int lavaLakesPerChunk;
	public int steppePerChunk;
	public int steppeGravelPerChunk;
	public int bigRedMushroomsPerChunk;
	public boolean generateStoneInGrass;
	public boolean generateStoneInGrass2;
	public boolean generateOutback;

	public BTABiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		this.sandGen = new WorldGenSand(7, Block.sand.blockID);
		this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
		this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
		this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
		this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
		this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
		this.goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
		this.redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
		this.diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
		this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
		this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
		this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
		this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
		this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
		this.bigMushroomGen = new WorldGenBigMushroom();
		this.reedGen = new BTAWorldGenReed();
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

		//BTWG
		pumpkinGen = new BTAWorldGenPumpkin();
		oasisGen = new BTAWorldGenOasis(7, Block.grass.blockID);
		steppeGen = new BTAWorldGenSteppe(Block.sand.blockID, 0);
		stoneInGrassGen = new BTAWorldGenMycelium(Block.stone.blockID, 32);
		stoneInGrassGen2 = new BTAWorldGenShield(Block.stone.blockID, 48);
		bigRedMushroomGen = new WorldGenBigMushroom(1);
		decoFlowerGen = new BTAWorldGenDecoFlowers();

		if (BTADecoIntegration.isDecoInstalled()) {
			outbackGen = new BTAWorldGenMycelium(Block.grass.blockID, 48, BTADecoIntegration.redSand.blockID);

			andesiteGen = new BTAWorldGenMinable(BTADecoIntegration.stoneTypes.blockID, 0, 32);
			dioriteGen = new BTAWorldGenMinable(BTADecoIntegration.stoneTypes.blockID, 1, 32);
			graniteGen = new BTAWorldGenMinable(BTADecoIntegration.stoneTypes.blockID, 2, 32);
		}

		oasesPerChunk = 0;
		steppePerChunk = 0;
		waterLakesPerChunk = 50;
		lavaLakesPerChunk = 20;
		bigRedMushroomsPerChunk = 0;
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
			this.randomGenerator = par2Random;
			this.chunk_X = par3;
			this.chunk_Z = par4;
			this.decorate();
			this.currentWorld = null;
			this.randomGenerator = null;
		}
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

		for (var1 = 0; var1 < this.sandPerChunk2; ++var1)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		}

		for (var1 = 0; var1 < this.clayPerChunk; ++var1)
		{
			if (this.currentWorld.rand.nextInt(3) != 0) {
				var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.clayGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}
		}

		for (var1 = 0; var1 < this.sandPerChunk; ++var1)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.sandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		}

		var1 = this.treesPerChunk;

		if (this.randomGenerator.nextInt(10) == 0)
		{
			++var1;
		}

		int var4;

		for (var2 = 0; var2 < var1; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			WorldGenerator var5 = this.biome.getRandomWorldGenForTrees(this.randomGenerator);
			var5.setScale(1.0D, 1.0D, 1.0D);
			var5.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
		}

		for (var2 = 0; var2 < this.bigMushroomsPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
		}

		int var7;

		//BTWG modified
		for (var2 = 0; var2 < this.flowersPerChunk; ++var2)
		{
			if (BTADecoIntegration.isDecoInstalled() && this.currentWorld.provider.terrainType == BTAMod.BTWGWorldTypeDeco) {
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

					if (this.randomGenerator.nextInt(4) == 0)
					{
						var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
						var4 = this.randomGenerator.nextInt(128);
						var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
						this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
					}
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
		}

		for (var2 = 0; var2 < 10; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(128);
			var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
		}

		if (this.randomGenerator.nextInt(32) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(128);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.pumpkinGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		for (var2 = 0; var2 < this.cactiPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(128);
			var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.cactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
		}

		//BTWG modified
		if (this.generateLakes)
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

		//BTWG
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

		if (generateStoneInGrass)
		{
			this.genStandardOre1(15, stoneInGrassGen, 64, 128);
		}

		if (generateStoneInGrass2)
		{
			this.genStandardOre1(20, stoneInGrassGen2, 64, 128);
		}

		if (generateOutback)
		{
			this.genStandardOre1(30, outbackGen, 64, 128);
		}
	}

	/**
	 * Standard ore generation helper. Generates most ores.
	 */
	protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
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
	protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
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
		this.genStandardOre1(20, this.coalGen, 0, 128);
		this.genStandardOre1(20, this.ironGen, 0, 64);
		this.genStandardOre1(2, this.goldGen, 0, 32);
		this.genStandardOre1(8, this.redstoneGen, 0, 16);
		this.genStandardOre1(1, this.diamondGen, 0, 16);
		this.genStandardOre2(1, this.lapisGen, 16, 16);

		if (BTADecoIntegration.isDecoInstalled() && this.currentWorld.provider.terrainType == BTAMod.BTWGWorldTypeDeco) {
			this.genStandardOre1(20, this.andesiteGen, 0, 128);
			this.genStandardOre1(10, this.dioriteGen, 0, 128);
			this.genStandardOre1(10, this.graniteGen, 0, 128);
		}
	}
}
