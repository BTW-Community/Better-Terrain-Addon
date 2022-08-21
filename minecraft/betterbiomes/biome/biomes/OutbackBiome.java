package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterbiomes.feature.plant.TallGrassGen;
import betterbiomes.feature.tree.SmallShrubGen;
import betterbiomes.feature.tree.TinyShrubGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class OutbackBiome extends BTABiome {
	public OutbackBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.redSand.blockID;
			this.fillerBlockExt = DecoIntegration.redSand.blockID;
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
			gen = new SmallShrubGen();
		}
		else if (rand.nextInt(2) == 0) {
			gen = new WorldGenShrub(0, 0);
		}
		else {
			gen = new TinyShrubGen();
		}

		return gen;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 0) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}

	public boolean CanLightningStrikeInBiome()
	{
		return true;
	}
}