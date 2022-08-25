package betterterrain.biome.biomes;

import java.util.Random;

import betterbiomes.world.generate.surface.HotSpringsSurfaceBuilder;
import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.tree.TallSwampTreeGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SwampSurfaceBuilder;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.FCUtilsColor;
import net.minecraft.src.Material;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class SwampBiome extends BTABiome {

	public SwampBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.flowersPerChunk = -999;
		this.btaBiomeDecorator.deadBushPerChunk = 1;
		this.btaBiomeDecorator.mushroomsPerChunk = 8;
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.clayPerChunk = 1;
		this.btaBiomeDecorator.waterlilyPerChunk = 4;
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
		this.waterColorMultiplier = 14745518;
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	public int getBiomeGrassColor()
	{
		double var1 = (double)this.getFloatTemperature();
		double var3 = (double)this.getFloatRainfall();
		return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	public int getBiomeFoliageColor()
	{
		double var1 = (double)this.getFloatTemperature();
		double var3 = (double)this.getFloatRainfall();
		return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
		WorldGenerator gen;

		if (rand.nextInt(4) == 0) {
			gen = new TallSwampTreeGen();
		}
		else {
			gen = this.worldGeneratorSwamp;
		}

		return gen;
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		SwampSurfaceBuilder surfaceBuilder = (SwampSurfaceBuilder) this.getSurfaceBuilder();

		for (int i = chunkX + 8; i < chunkX + 24; i++) {
			for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
				double waterNoiseScale = 1/96D;
				double waterNoise = surfaceBuilder.waterNoiseGen.noise2(i, k, waterNoiseScale) + rand.nextDouble();
				
				int j = 63;
				
				int previousBlockID = world.getBlockId(i, j - 1, k);
				int thisBlockID = world.getBlockId(i, j, k);
				int nextBlockID = world.getBlockId(i, j + 1, k);

				if (nextBlockID != 0 && Block.blocksList[nextBlockID].blockMaterial.isReplaceable() && Block.blocksList[nextBlockID].blockMaterial != Material.water) {
					world.setBlockToAir(i, j + 1, k);
					nextBlockID = 0;
				}

				if (previousBlockID != 0 && previousBlockID != Block.waterStill.blockID && 
						(thisBlockID == this.topBlockExt || thisBlockID == DecoIntegration.stainedTerracotta.blockID || thisBlockID == DecoIntegration.coarseDirt.blockID) && 
						nextBlockID == 0)
				{
					if (waterNoise > 0.25) {
						int numBlockNeighbors = 8;

						for (int offsetI = -1; offsetI <= 1; offsetI++) {
							for (int offsetK = -1; offsetK <= 1; offsetK++) {
								if (offsetI == 0 && offsetK == 0) {
									continue;
								}

								int neighborID = world.getBlockId(i + offsetI, j, k + offsetK);
								int neighborAboveID = world.getBlockId(i + offsetI, j + 1, k + offsetK);

								if (neighborID == 0 || neighborAboveID != 0) {
									numBlockNeighbors--;
								}
							}
						}

						if (numBlockNeighbors == 8) {
							world.setBlock(i, j, k, Block.waterStill.blockID);
						}
					}
				}
			}
		}

		super.decorate(world, rand, chunkX, chunkZ, generatorOptions);

		WorldGenVines var5 = new WorldGenVines();

		for (int var6 = 0; var6 < 50; ++var6)
		{
			int x = chunkX + rand.nextInt(16) + 8;
			byte y = 64;
			int z = chunkZ + rand.nextInt(16) + 8;
			var5.generate(world, rand, x, y, z);
		}
	}
}