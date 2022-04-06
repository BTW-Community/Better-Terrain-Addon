package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenHeathlandWoods extends BTABiomeGenBase {
	public BTABiomeGenHeathlandWoods(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 12;
		this.btaBiomeDecorator.grassPerChunk = 15;
		this.btaBiomeDecorator.flowersPerChunk = 30;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return new WorldGenTaiga2(false);
	}
}