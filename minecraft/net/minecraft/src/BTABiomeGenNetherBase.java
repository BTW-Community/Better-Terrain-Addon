package net.minecraft.src;

import java.util.Random;

public abstract class BTABiomeGenNetherBase extends BTABiomeGenBase {
	protected int infusedStonePerChunk = 2;
	protected int maxInfusedStoneHeight = 32;
	
	protected int magmaPerChunk = 4;
	
	public BTABiomeGenNetherBase(int id) {
		super(id, BTAEnumClimate.HELL);
		
		this.topBlockExt = Block.netherrack.blockID;
		this.fillerBlockExt = Block.netherrack.blockID;
		
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityMagmaCube.class, 1, 4, 4));
	}
	
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.2, 0.03, 0.03);
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, BTAWorldConfigurationInfo generatorOptions) {
		WorldGenerator gen;
		
		//Random buried lava pockets
		gen = new BTAWorldGenNetherLava(false);
		
		for (int i = 0; i < 8; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(120) + 4;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Fire
		int numFire = rand.nextInt(rand.nextInt(10) + 1) + 1;
		//gen = new BTAWorldGenNetherFire();
		gen = new WorldGenFire();
		
		for (int i = 0; i < 8; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(120) + 4;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Glowstone
		int numGlowstone = rand.nextInt(rand.nextInt(10) + 1) + 11;
		gen = new BTAWorldGenGlowstone();
		
		for (int i = 0; i < numGlowstone; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(120) + 4;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Infused stone blobs
		gen = new BTAWorldGenMinable(BTADecoIntegration.infusedStone.blockID, 0, 32, 100, new int[] {Block.netherrack.blockID, BTADecoIntegration.basalt.blockID});
		this.genStandardOre1(world, rand, infusedStonePerChunk, gen, chunkX, chunkZ, 5, maxInfusedStoneHeight);
		
		//Magma
		gen = new BTAWorldGenMinable(BTADecoIntegration.magma.blockID, 0, 8, 24, new int[] {Block.netherrack.blockID, BTADecoIntegration.basalt.blockID, BTADecoIntegration.infusedStone.blockID});
		this.genStandardOre1(world, rand, magmaPerChunk, gen, chunkX, chunkZ, 27, 36);
		
		//Quartz
		gen = new BTAWorldGenMinable(Block.oreNetherQuartz.blockID, 0, 13, Block.netherrack.blockID);
		this.genStandardOre1(world, rand, 16, gen, chunkX, chunkZ, 10, 118);
		
		//Dried up lava pillars
		gen = new BTAWorldGenBasaltPillar();
		
		if (rand.nextInt(20) == 0) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(128);
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Lavafalls
		gen = new BTAWorldGenNetherLava(true);
		
		for (int i = 0; i < 16; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(108) + 10;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
	}
}