package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiomeConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SwampSurfaceBuilder;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.Material;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenVines;

public class SwampRiverBiome extends RiverBiome {
	public SwampRiverBiome(int id, String internalName) {
		super(id, internalName, BTABiomeConfiguration.swamp.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.swamp.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.swamp.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.swamp.getBiomeFoliageColor();
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