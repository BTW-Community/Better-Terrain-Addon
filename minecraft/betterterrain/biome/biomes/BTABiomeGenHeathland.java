package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenHeathland extends BTABiomeGenBase {
	public BTABiomeGenHeathland(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 7;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.flowersPerChunk = 15;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(3) == 0 ? this.worldGeneratorTrees : new WorldGenShrub(0,0);
	}
}