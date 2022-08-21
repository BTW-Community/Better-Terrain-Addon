package betterterrain.biome;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterbiomes.feature.terrain.BasaltPillarGen;
import betterbiomes.feature.terrain.GlowstoneGen;
import betterbiomes.feature.terrain.NetherLavaGen;
import betterbiomes.feature.terrain.OreGen;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityGhast;
import net.minecraft.src.FCEntityMagmaCube;
import net.minecraft.src.FCEntityPigZombie;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenFire;
import net.minecraft.src.WorldGenerator;

public abstract class BTANetherBiome extends BTABiome {
	protected int infusedStonePerChunk = 2;
	protected int maxInfusedStoneHeight = 32;
	
	protected int magmaPerChunk = 4;
	
	public BTANetherBiome(int id, String internalName) {
		super(id, internalName, Climate.HELL);
		
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
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		WorldGenerator gen;
		
		//Random buried lava pockets
		gen = new NetherLavaGen(false);
		
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
		gen = new GlowstoneGen();
		
		for (int i = 0; i < numGlowstone; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(120) + 4;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Infused stone blobs
		gen = new OreGen(DecoIntegration.infusedStone.blockID, 0, 32, 100, new int[] {Block.netherrack.blockID, DecoIntegration.basalt.blockID});
		this.genStandardOre1(world, rand, infusedStonePerChunk, gen, chunkX, chunkZ, 5, maxInfusedStoneHeight);
		
		//Magma
		gen = new OreGen(DecoIntegration.magma.blockID, 0, 8, 24, new int[] {Block.netherrack.blockID, DecoIntegration.basalt.blockID, DecoIntegration.infusedStone.blockID});
		this.genStandardOre1(world, rand, magmaPerChunk, gen, chunkX, chunkZ, 27, 36);
		
		//Quartz
		gen = new OreGen(Block.oreNetherQuartz.blockID, 0, 13, Block.netherrack.blockID);
		this.genStandardOre1(world, rand, 16, gen, chunkX, chunkZ, 10, 118);
		
		//Dried up lava pillars
		gen = new BasaltPillarGen();
		
		if (rand.nextInt(20) == 0) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(128);
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Lavafalls
		gen = new NetherLavaGen(true);
		
		for (int i = 0; i < 16; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(108) + 10;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
	}
}