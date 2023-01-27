package netherexpanded.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.PetrifiedTreeGen;
import betterterrain.biome.BTANetherBiome;
import net.minecraft.src.WorldGenerator;

public class PetrifiedForestBiome extends BTANetherBiome {
	public PetrifiedForestBiome(int id, String internalName) {
		super(id, internalName);
		this.btaBiomeDecorator.fractionalTreeChance = 10;
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return new PetrifiedTreeGen(false);
	}
}