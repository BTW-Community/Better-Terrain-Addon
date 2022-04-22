package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.feature.AmethystShardsGen;
import betterterrain.feature.CrystalGen;
import betterterrain.world.WorldConfigurationInfo;
import net.minecraft.src.Block;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class CrystalCavernsBiome extends BTANetherBiome {
	public CrystalCavernsBiome(int id) {
		super(id);
		
		this.topBlockExt = Block.blockNetherQuartz.blockID;
		this.fillerBlockExt = Block.blockNetherQuartz.blockID;
		
		this.spawnableMonsterList.clear();
		//this.spawnableMonsterList.add(new SpawnListEntry(BTAEntityCrystalGolem.class, 1, 1, 4));
	}
	
	@Override
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.3, 0.2, 0.4);
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		super.decorate(world, rand, chunkX, chunkZ, generatorOptions);
		
		WorldGenerator gen;
		
		//Small amethyst shards
		gen= new AmethystShardsGen();
		
		for (int i = 0; i < 64; i++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(120) + 4;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
		
		//Large amethyst crystals
		for (int i = 0; i < 6; i++) {
			gen = new CrystalGen(rand.nextInt(4) == 0);
			
			int x = chunkX + rand.nextInt(16) + 8;
			int y = rand.nextInt(100) + 4;
			int z = chunkZ + rand.nextInt(16) + 8;
			
			gen.generate(world, rand, x, y, z);
		}
	}
}