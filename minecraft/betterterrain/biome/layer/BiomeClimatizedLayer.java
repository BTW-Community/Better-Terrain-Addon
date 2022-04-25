package betterterrain.biome.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.biome.Climate;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class BiomeClimatizedLayer extends BTALayer {
	private ArrayList<BTABiome> biomesForGeneration;
	private GenLayer climateLayer;
	public static Map<Climate, ArrayList<BTABiome>> biomeCategoryMapCached = new HashMap();

    public BiomeClimatizedLayer(long baseSeed, GenLayer parent, GenLayer climates, ArrayList<BTABiome> biomesForGeneration)
    {
        super(baseSeed);
		this.parent = parent;
		this.climateLayer = climates;
		this.biomesForGeneration = biomesForGeneration;
		
		for (Climate c : Climate.values()) {
			if (c.isOverworld) {
				biomeCategoryMapCached.put(c, BiomeConfiguration.getClimateListForGenerator(c, this.biomesForGeneration));
			}
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
					Climate climate = Climate.fromID(climateID);
					ArrayList<BTABiome> biomesForClimate = biomeCategoryMapCached.get(climate);
					
					cache[k + i * sizeX] = biomesForClimate.get(this.nextInt(biomesForClimate.size())).biomeID;
				}
            }
        }
        return cache;
    }
}