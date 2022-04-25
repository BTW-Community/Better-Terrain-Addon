package betterterrain.biome.biomes;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterterrain.biome.BTANetherBiome;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.surface.BasaltDeltasSurfaceBuilder;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityMagmaCube;
import net.minecraft.src.FCUtilsBlockPos;
import net.minecraft.src.Material;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public class BasaltDeltasBiome extends BTANetherBiome {
	public BasaltDeltasBiome(int id) {
		super(id);

		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.basalt.blockID;
			this.fillerBlockExt = DecoIntegration.basalt.blockID;
		}
		else {
			this.topBlockExt = Block.stone.blockID;
			this.fillerBlockExt = Block.stone.blockID;
		}

		this.infusedStonePerChunk = 6;
		this.maxInfusedStoneHeight = 124;
		this.magmaPerChunk = 8;
		
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityMagmaCube.class, 1, 4, 4));
	}

	@Override
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.3, 0.3, 0.3);
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		BasaltDeltasSurfaceBuilder surfaceBuilder = (BasaltDeltasSurfaceBuilder) this.getSurfaceBuilder();

		for (int i = chunkX + 8; i < chunkX + 24; i++) {
			for (int k = chunkZ + 8; k < chunkZ + 24; k++) {
				double lavaNoiseScale = 1/32D;
				double lavaNoise = surfaceBuilder.lavaNoiseGen.noise2(i, k, lavaNoiseScale);

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
					
					boolean useLava = lavaNoise + rand.nextDouble() * 0.3 > 0.25;
					
					if (previousBlockID != 0 && previousBlockID != Block.lavaStill.blockID && 
							(thisBlockID == this.topBlockExt || thisBlockID == DecoIntegration.infusedStone.blockID || thisBlockID == DecoIntegration.ash.blockID) && 
							nextBlockID == 0) {
						if (useLava) {
							int numBlockNeighbors = 4;
							int numLavaNeighbors = 0;

							int offset = rand.nextInt(10) == 0 ? 1 : 0;

							for (int facing = 2; facing <= 5; facing++) {
								FCUtilsBlockPos pos = new FCUtilsBlockPos(i, j, k, facing);

								int neighborID = world.getBlockId(pos.i, pos.j, pos.k);
								if (neighborID == 0) {
									numBlockNeighbors--;

									if (numBlockNeighbors < 3)
										break;
								}
								else if (Block.blocksList[neighborID].blockMaterial == Material.lava){
									numLavaNeighbors++;
								}
							}

							if (numBlockNeighbors == 4 - offset) {
								if (offset == 1) {
									if (numLavaNeighbors > 0) {
										world.setBlock(i, j, k, Block.lavaMoving.blockID);
										world.scheduledUpdatesAreImmediate = true;
										Block.blocksList[Block.lavaMoving.blockID].updateTick(world, i, j, k, rand);
										world.scheduledUpdatesAreImmediate = false;
									}
								}
								else {
									world.setBlock(i, j, k, Block.lavaStill.blockID);
								}
							}
						}
					}
				}
			}
		}
		
		super.decorate(world, rand, chunkX, chunkZ, generatorOptions);
	}
}