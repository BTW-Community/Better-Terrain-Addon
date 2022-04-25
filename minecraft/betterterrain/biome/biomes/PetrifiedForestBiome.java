package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTANetherBiome;
import betterterrain.feature.PetrifiedTreeGen;
import net.minecraft.src.WorldGenerator;

public class PetrifiedForestBiome extends BTANetherBiome {
	public PetrifiedForestBiome(int id) {
		super(id);
		this.btaBiomeDecorator.fractionalTreeChance = 10;
	}
	
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return new PetrifiedTreeGen(false);
	}
}