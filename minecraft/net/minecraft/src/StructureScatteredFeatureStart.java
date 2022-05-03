package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.structure.component.BTAComponentScatteredFeatureRedDesertPyramid;

public class StructureScatteredFeatureStart extends StructureStart
{
	public static ArrayList<BiomeGenBase> desertBiomeList = new ArrayList();
	public static ArrayList<BiomeGenBase> redDesertBiomeList = new ArrayList();
	public static ArrayList<BiomeGenBase> jungleBiomeList = new ArrayList();
	public static ArrayList<BiomeGenBase> swampBiomeList = new ArrayList();
	
	public static void addDesertBiome(BiomeGenBase biome) {
		desertBiomeList.add(biome);
	}
	
	public static void addRedDesertBiome(BiomeGenBase biome) {
		redDesertBiomeList.add(biome);
	}
	
	public static void addJungleBiome(BiomeGenBase biome) {
		jungleBiomeList.add(biome);
	}
	
	public static void addSwampBiome(BiomeGenBase biome) {
		swampBiomeList.add(biome);
	}
	
	public StructureScatteredFeatureStart(World world, Random rand, int chunkX, int chunkZ)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);

		if (swampBiomeList.contains(biome))
		{
			ComponentScatteredFeatureSwampHut var7 = new ComponentScatteredFeatureSwampHut(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var7);
		}
		else if (desertBiomeList.contains(biome))
		{
			ComponentScatteredFeatureDesertPyramid var8 = new ComponentScatteredFeatureDesertPyramid(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var8);
		}
		else if (redDesertBiomeList.contains(biome))
		{
			BTAComponentScatteredFeatureRedDesertPyramid var8 = new BTAComponentScatteredFeatureRedDesertPyramid(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var8);
		}
		else if(jungleBiomeList.contains(biome))
		{
			ComponentScatteredFeatureJunglePyramid var6 = new ComponentScatteredFeatureJunglePyramid(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var6);
		}

		this.updateBoundingBox();
	}
}

