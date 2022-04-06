package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.feature.BTAWorldGenAcacia;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenSavannaBushes extends BTABiomeGenBase {

	public BTABiomeGenSavannaBushes(int id, BTAEnumClimate climate) {
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
		return rand.nextInt(8) == 0 ? new BTAWorldGenAcacia(false) : new WorldGenShrub(0,0);
	}
}