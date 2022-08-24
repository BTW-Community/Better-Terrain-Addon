package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.generate.surface.HotSpringsSurfaceBuilder;
import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.feature.tree.PineTreeGen;
import betterterrain.feature.tree.TaigaGen5;
import betterterrain.feature.tree.TaigaGen6;
import betterterrain.feature.tree.TaigaGen7;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityWolf;
import net.minecraft.src.FCUtilsBlockPos;
import net.minecraft.src.FCUtilsColor;
import net.minecraft.src.Material;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class HotSpringsBiome extends BTABiome {
	public HotSpringsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);

		spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 8, 4, 4));
		
        this.btaBiomeDecorator.treesPerChunk = 8;
        this.btaBiomeDecorator.grassPerChunk = 15;
        // Clay messes up the hot springs
        this.btaBiomeDecorator.clayPerChunk = 0;
		
		this.waterColorMultiplier =  0x00ffaa;
	}
	
	@Override
    public WorldGenerator getRandomWorldGenForTrees(Random rand) {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new PineTreeGen(false, 2, 2);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen5(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen6(false);
    	}
    	else {
    		gen = new TaigaGen7(false);
    	}
    	
    	return gen;
    }
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}

	@Override
    public boolean canSnowAt(World world, int x, int y, int z) {
		return super.canSnowAt(world, x, y, z) && ((HotSpringsSurfaceBuilder) this.getSurfaceBuilder()).springsNoiseGen.noise2(x, z, 1/192D) <= 0.125;
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		HotSpringsSurfaceBuilder surfaceBuilder = (HotSpringsSurfaceBuilder) this.getSurfaceBuilder();

		for (int i = chunkX + 8; i < chunkX + 24; i++) {
			for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
				double waterNoiseScale = 1/192D;
				double waterNoise = surfaceBuilder.springsNoiseGen.noise2(i, k, waterNoiseScale);

				int previousBlockID = -1;
				int thisBlockID = -1;
				int nextBlockID = -1;
				
				for (int j = 32; j < 108; j++) {
					if (previousBlockID == -1) {
						previousBlockID = world.getBlockId(i, j - 1, k);
						thisBlockID = world.getBlockId(i, j, k);
						nextBlockID = world.getBlockId(i, j + 1, k);
					}
					else {
						previousBlockID = thisBlockID;
						thisBlockID = nextBlockID;
						nextBlockID = world.getBlockId(i, j + 1, k);
					}
					
					if (nextBlockID != 0 && Block.blocksList[nextBlockID].blockMaterial.isReplaceable() && Block.blocksList[nextBlockID].blockMaterial != Material.water) {
						world.setBlockToAir(i, j + 1, k);
						nextBlockID = 0;
					}
					
					if (previousBlockID != 0 && previousBlockID != Block.waterStill.blockID && 
							(thisBlockID == this.topBlockExt || thisBlockID == DecoIntegration.stainedTerracotta.blockID || thisBlockID == DecoIntegration.coarseDirt.blockID) && 
							nextBlockID == 0)
					{
						if (waterNoise > 0.825) {
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
								fillToDepth(world, i, j - 1, k, 3, DecoIntegration.stainedTerracotta.blockID, FCUtilsColor.YELLOW.colorID);
							}
							else {
								fillToDepth(world, i, j, k, 4, DecoIntegration.redSand.blockID, 0);
							}
							
							for (int offsetI = -1; offsetI <= 1; offsetI++) {
								for (int offsetK = -1; offsetK <= 1; offsetK++) {
									if (offsetI == 0 && offsetK == 0) {
										break;
									}
									
									if (surfaceBuilder.springsNoiseGen.noise2(i + offsetI, k + offsetK, waterNoiseScale) <= 0.825) {
										fillToDepth(world, i + offsetI, j, k + offsetK, 4, DecoIntegration.redSand.blockID, 0);
									}
								}
							}
						}
						else if (waterNoise > 0.75) {
							fillToDepth(world, i, j, k, 4, DecoIntegration.redSand.blockID, 0);
						}
						else if (waterNoise > 0.625) {
							fillToDepth(world, i, j, k, 4, DecoIntegration.infusedStone.blockID, 0);
						}
						else if (waterNoise > 0.5) {
							fillToDepth(world, i, j, k, 4, DecoIntegration.basalt.blockID, 0);
						}
						else if (waterNoise > 0.375) {
							fillToDepth(world, i, j, k, 4, Block.gravel.blockID, 0);
						}
						else if (waterNoise > 0.125) {
							fillToDepth(world, i, j, k, 4, DecoIntegration.stoneTypes.blockID, 2);
						}
					}
				}
			}
		}
		
		for (int i = chunkX + 8; i < chunkX + 24; i++) {
			for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
				for (int j = 32; j < 108; j++) {
					if (world.getBlockId(i, j, k) == DecoIntegration.redSand.blockID) {
						for (int offsetI = -1; offsetI <= 1; offsetI++) {
							for (int offsetK = -1; offsetK <= 1; offsetK++) {
								if (offsetI == 0 && offsetK == 0) {
									break;
								}
								
								int blockID = world.getBlockId(i + offsetI, j, k + offsetK);
								
								if (blockID != 0 && Block.blocksList[blockID].blockMaterial == Material.water) {
									fillToDepth(world, i, j, k, 1, DecoIntegration.stainedTerracotta.blockID, FCUtilsColor.YELLOW.colorID);
								}
							}
						}
					}
				}
			}
		}
		
		super.decorate(world, rand, chunkX, chunkZ, generatorOptions);
	}
	
	private void fillToDepth(World world, int x, int y, int z, int depth, int blockID, int metadata) {
		for (int i = 0; i < depth; i++) {
			if (world.getBlockId(x, y - i, z) != 0) {
				world.setBlockAndMetadata(x, y - i, z, blockID, metadata);
			}
		}
	}
}