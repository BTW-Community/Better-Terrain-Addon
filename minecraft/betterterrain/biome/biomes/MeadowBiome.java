package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.MeadowTreeGen1;
import betterterrain.feature.MeadowTreeGen2;
import net.minecraft.src.WorldGenerator;

public class MeadowBiome extends BTABiome {
	public MeadowBiome(int id, Climate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 1;
		this.btaBiomeDecorator.grassPerChunk = 15;
		this.btaBiomeDecorator.flowersPerChunk = 30;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new MeadowTreeGen2() : new MeadowTreeGen1();
	}
}