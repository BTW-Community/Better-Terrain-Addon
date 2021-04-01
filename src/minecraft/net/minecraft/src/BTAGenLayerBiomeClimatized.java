package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BTAGenLayerBiomeClimatized extends BTAGenLayer {
	private ArrayList<BTABiomeGenBase> biomesForGeneration;
	private GenLayer climateLayer;
	public static Map<BTAEnumClimate, ArrayList<BTABiomeGenBase>> biomeCategoryMapCached = new HashMap();

    public BTAGenLayerBiomeClimatized(long baseSeed, GenLayer parent, GenLayer climates, ArrayList<BTABiomeGenBase> biomesForGeneration)
    {
        super(baseSeed);
		this.parent = parent;
		this.climateLayer = climates;
		this.biomesForGeneration = biomesForGeneration;
		
		for (BTAEnumClimate c : BTAEnumClimate.values()) {
			biomeCategoryMapCached.put(c, BTABiomeConfiguration.getClimateListForGenerator(c, this.biomesForGeneration));
		}
	} 

    @Override
	public int[] getInts(int xOffset, int zOffset, int sizeX, int sizeZ)
    {
        int[] parentInts = this.parent.getInts(xOffset, zOffset, sizeX, sizeZ);
        int[] climateInts = this.climateLayer.getInts(xOffset, zOffset, sizeX, sizeZ);
        int[] cache = IntCache.getIntCache(sizeX * sizeZ);
        
        for (int i = 0; i < sizeZ; ++i)
        {
            for (int k = 0; k < sizeX; ++k)
            {
                this.initChunkSeed((long)(k + xOffset), (long)(i + zOffset));
                
                int parentInt = parentInts[k + i * sizeX];
                
				if (parentInt == 0) {
					cache[k + i * sizeX] = 0;
				}
				else {
					int climateID = climateInts[k + i * sizeX];
					BTAEnumClimate climate = BTAEnumClimate.fromID(climateID);
					ArrayList<BTABiomeGenBase> biomesForClimate = biomeCategoryMapCached.get(climate);
					
					cache[k + i * sizeX] = biomesForClimate.get(this.nextInt(biomesForClimate.size())).biomeID;
				}
            }
        }
        return cache;
    }
}