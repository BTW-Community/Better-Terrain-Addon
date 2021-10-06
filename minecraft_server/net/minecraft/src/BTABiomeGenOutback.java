package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenOutback extends BTABiomeGenBase {
	protected BTABiomeGenOutback(int id, BTAEnumClimate climate) {
		super(id, climate);
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.generateOutback = true;
		this.btaBiomeDecorator.deadBushPerChunk = 10;
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 20;
		this.btaBiomeDecorator.cactiPerChunk = 15;
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
		this.btaBiomeDecorator.flowersPerChunk = -999;
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
		return par1Random.nextInt(2) == 0 ? new BTAWorldGenTallGrass(Block.tallGrass.blockID, 0) : new BTAWorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	public boolean CanLightningStrikeInBiome()
	{
		return true;
	}
}