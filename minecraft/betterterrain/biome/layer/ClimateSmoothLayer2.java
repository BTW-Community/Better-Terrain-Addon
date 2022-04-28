package betterterrain.biome.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.biome.Climate;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class ClimateSmoothLayer2 extends BTALayer {
	private ArrayList<BTABiome> biomesForGeneration;
	public static Map<Climate, ArrayList<BTABiome>> biomeCategoryMapCached = new HashMap();

	public ClimateSmoothLayer2(long baseSeed, GenLayer parent, ArrayList<BTABiome> biomesForGeneration) {
		super(baseSeed);
		this.parent = parent;
		this.biomesForGeneration = biomesForGeneration;

		for (Climate c : Climate.values()) {
			if (c.isOverworld) {
				biomeCategoryMapCached.put(c, BiomeConfiguration.getClimateListForGenerator(c, this.biomesForGeneration));
			}
		}
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer subclass.
	 */
	public int[] getInts(int xOffset, int zOffset, int sizeX, int sizeZ) {
		int parentXOffset = xOffset - 1;
		int parentZOffset = zOffset - 1;
		int parentSizeX = sizeX + 2;
		int parentSizeZ = sizeZ + 2;
		int[] parentArray = this.parent.getInts(parentXOffset, parentZOffset, parentSizeX, parentSizeZ);
		int[] cache = IntCache.getIntCache(sizeX * sizeZ);

		for (int i = 0; i < sizeZ; ++i) {
			for (int k = 0; k < sizeX; ++k) {
				int climateID = parentArray[k + 1 + (i + 1) * parentSizeX];
				this.initChunkSeed((long)(k + xOffset), (long)(i + zOffset));

				int neighborInt1 = parentArray[k + 0 + (i + 1) * parentSizeX];
				int neighborInt2 = parentArray[k + 1 + (i + 2) * parentSizeX];
				int neighborInt3 = parentArray[k + 1 + (i + 0) * parentSizeX];
				int neighborInt4 = parentArray[k + 2 + (i + 1) * parentSizeX];

				if (climateID == Climate.TROPICAL.id && biomeCategoryMapCached.get(Climate.TEMPERATE).size() > 0) {
					if (neighborInt1 == Climate.COLD.id || neighborInt1 == Climate.ARID.id)
						climateID = Climate.TEMPERATE.id;
					if (neighborInt2 == Climate.COLD.id || neighborInt2 == Climate.ARID.id)
						climateID = Climate.TEMPERATE.id;
					if (neighborInt3 == Climate.COLD.id || neighborInt3 == Climate.ARID.id)
						climateID = Climate.TEMPERATE.id;
					if (neighborInt4 == Climate.COLD.id || neighborInt4 == Climate.ARID.id)
						climateID = Climate.TEMPERATE.id;
				}
				else if (climateID == Climate.ARID.id && biomeCategoryMapCached.get(Climate.TEMPERATE).size() > 0) {
					if (neighborInt1 == Climate.COLD.id || neighborInt1 == Climate.TROPICAL.id)
						climateID = Climate.TEMPERATE.id;
					if (neighborInt2 == Climate.COLD.id || neighborInt2 == Climate.TROPICAL.id)
						climateID = Climate.TEMPERATE.id;
					if (neighborInt3 == Climate.COLD.id || neighborInt3 == Climate.TROPICAL.id)
						climateID = Climate.TEMPERATE.id;
					if (neighborInt4 == Climate.COLD.id || neighborInt4 == Climate.TROPICAL.id)
						climateID = Climate.TEMPERATE.id;
				}

				cache[k + i * sizeX] = climateID;
			}
		}

		return cache;
	}
}
