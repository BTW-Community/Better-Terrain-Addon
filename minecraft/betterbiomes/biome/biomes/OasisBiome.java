package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.PalmTreeSmallGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.WorldGenerator;

public class OasisBiome extends BTABiome {
	public OasisBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 2;
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return new PalmTreeSmallGen(false, false);
	}

	@Override
    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}