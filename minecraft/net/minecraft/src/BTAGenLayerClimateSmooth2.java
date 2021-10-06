package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BTAGenLayerClimateSmooth2 extends BTAGenLayer {
	private ArrayList<BTABiomeGenBase> biomesForGeneration;
	public static Map<BTAEnumClimate, ArrayList<BTABiomeGenBase>> biomeCategoryMapCached = new HashMap();

	public BTAGenLayerClimateSmooth2(long baseSeed, GenLayer parent, ArrayList<BTABiomeGenBase> biomesForGeneration) {
		super(baseSeed);
		this.parent = parent;
		this.biomesForGeneration = biomesForGeneration;

		for (BTAEnumClimate c : BTAEnumClimate.values()) {
			if (c.isOverworld) {
				biomeCategoryMapCached.put(c, BTABiomeConfiguration.getClimateListForGenerator(c, this.biomesForGeneration));
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

				if (climateID == BTAEnumClimate.TROPICAL.id && biomeCategoryMapCached.get(BTAEnumClimate.TEMPERATE).size() > 0) {
					if (neighborInt1 == BTAEnumClimate.COLD.id || neighborInt1 == BTAEnumClimate.ARID.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
					if (neighborInt2 == BTAEnumClimate.COLD.id || neighborInt2 == BTAEnumClimate.ARID.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
					if (neighborInt3 == BTAEnumClimate.COLD.id || neighborInt3 == BTAEnumClimate.ARID.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
					if (neighborInt4 == BTAEnumClimate.COLD.id || neighborInt4 == BTAEnumClimate.ARID.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
				}
				else if (climateID == BTAEnumClimate.ARID.id && biomeCategoryMapCached.get(BTAEnumClimate.TEMPERATE).size() > 0) {
					if (neighborInt1 == BTAEnumClimate.COLD.id || neighborInt1 == BTAEnumClimate.TROPICAL.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
					if (neighborInt2 == BTAEnumClimate.COLD.id || neighborInt2 == BTAEnumClimate.TROPICAL.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
					if (neighborInt3 == BTAEnumClimate.COLD.id || neighborInt3 == BTAEnumClimate.TROPICAL.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
					if (neighborInt4 == BTAEnumClimate.COLD.id || neighborInt4 == BTAEnumClimate.TROPICAL.id)
						climateID = BTAEnumClimate.TEMPERATE.id;
				}

				cache[k + i * sizeX] = climateID;
			}
		}

		return cache;
	}
}
