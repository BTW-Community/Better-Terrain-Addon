package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.tree.AcaciaGen;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class SavannaBiome extends BTABiome {

	public SavannaBiome(int id, Climate climate) {
		super(id, climate);
        this.btaBiomeDecorator.treesPerChunk = 2;
        this.btaBiomeDecorator.grassPerChunk = 25;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(3) == 0 ? new AcaciaGen(false) : new WorldGenShrub(0,0);
	}
}