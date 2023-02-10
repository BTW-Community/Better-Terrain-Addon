package betterbiomes.biome.biomes.deprecated;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.AcaciaGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class SavannaBushesBiome extends BTABiome {

	public SavannaBushesBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
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