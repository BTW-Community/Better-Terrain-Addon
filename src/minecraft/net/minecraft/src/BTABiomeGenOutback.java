package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOutback extends BTABiomeGenBase {
	protected BTABiomeGenOutback(int id) {
		super(id);
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
		}
		this.btwgBiomeDecorator.generateOutback = true;
		this.btwgBiomeDecorator.deadBushPerChunk = 10;
		this.btwgBiomeDecorator.treesPerChunk = 100;
		this.btwgBiomeDecorator.grassPerChunk = 10;
		this.btwgBiomeDecorator.cactiPerChunk = 15;
		this.btwgBiomeDecorator.sandPerChunk = 0;
		this.btwgBiomeDecorator.sandPerChunk2 = 0;
		this.btwgBiomeDecorator.flowersPerChunk = -999;
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		WorldGenerator gen;

		if (rand.nextInt(7) == 0) {
			gen = new BTAWorldGenShrubSmall();
		}
		else if (rand.nextInt(2) == 0) {
			gen = new WorldGenShrub(0, 0);
		}
		else {
			gen = new BTAWorldGenShrubTiny();
		}

		return gen;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	public boolean CanLightningStrikeInBiome()
	{
		return true;
	}
}