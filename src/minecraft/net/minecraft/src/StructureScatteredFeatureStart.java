package net.minecraft.src;

import java.util.Random;

public class StructureScatteredFeatureStart extends StructureStart
{
	public StructureScatteredFeatureStart(World world, Random rand, int chunkX, int chunkZ)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);

		if (biome == BiomeGenBase.swampland || BTABiomeConfiguration.canBiomeSpawnWitchHut(biome))
		{
			ComponentScatteredFeatureSwampHut var7 = new ComponentScatteredFeatureSwampHut(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var7);
		}
		else if (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills || BTABiomeConfiguration.canBiomeSpawnDesertTemple(biome))
		{
			ComponentScatteredFeatureDesertPyramid var8 = new ComponentScatteredFeatureDesertPyramid(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var8);
		}
		else if(biome == BiomeGenBase.jungle || biome == BiomeGenBase.jungleHills || BTABiomeConfiguration.canBiomeSpawnJungleTemple(biome))
		{
			ComponentScatteredFeatureJunglePyramid var6 = new ComponentScatteredFeatureJunglePyramid(rand, chunkX * 16, chunkZ * 16);
			this.components.add(var6);
		}

		this.updateBoundingBox();
	}
}
