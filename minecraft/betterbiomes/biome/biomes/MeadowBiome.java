package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.MeadowTreeGen1;
import betterbiomes.feature.tree.MeadowTreeGen2;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenerator;

public class MeadowBiome extends BTABiome {
	public MeadowBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
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