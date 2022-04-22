package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.AcaciaGen;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class SavannaBushesBiome extends BTABiome {

	public SavannaBushesBiome(int id, Climate climate) {
		super(id, climate);
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 25;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		return rand.nextInt(8) == 0 ? new AcaciaGen(false) : new WorldGenShrub(0,0);
	}
}