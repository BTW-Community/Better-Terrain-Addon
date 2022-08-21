package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class HeathlandBiome extends BTABiome {
	public HeathlandBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
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