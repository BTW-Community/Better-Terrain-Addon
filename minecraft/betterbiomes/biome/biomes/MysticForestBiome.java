package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.terrain.MysticShardsGen;
import betterbiomes.feature.tree.MassiveOakGen;
import betterbiomes.feature.tree.MysticTreeGen;
import betterbiomes.feature.tree.TemperateBirchGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.feature.tree.TaigaGen6;
import betterterrain.feature.tree.TallSwampTreeGen;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class MysticForestBiome extends BTABiome {
	public MysticForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		waterColorMultiplier = 0xa78cff;
		this.btaBiomeDecorator.treesPerChunk = 15;
		this.btaBiomeDecorator.grassPerChunk = 20;
		this.btaBiomeDecorator.flowersPerChunk = 30;
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		WorldGenerator gen;

		if (rand.nextInt(10) == 0) {
			gen = new MassiveOakGen(false);
		}
		else if (rand.nextInt(8) == 0) {
			gen = new TallSwampTreeGen();
		}
		else if (rand.nextInt(6) == 0) {
			gen = new TemperateBirchGen();
		}
		else if (rand.nextInt(6) == 0) {
			gen = new TaigaGen6(false);
		}
		else {
			gen = new MysticTreeGen(false);
		}

		return gen;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}

	@Override
	public int getBiomeGrassColor() {
		return 0x34ebc6;
	}

	@Override
	public int getBiomeFoliageColor() {
		return 0xba8cff;
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
		super.decorate(world, rand, chunkX, chunkZ, generatorOptions);

		WorldGenerator gen;

		//Small amethyst shards
		gen = new MysticShardsGen();

		if (rand.nextInt(3) == 0) {
			int x = chunkX + rand.nextInt(16) + 8;
			int z = chunkZ + rand.nextInt(16) + 8;
			int y = world.getTopSolidOrLiquidBlock(x, z);

			gen.generate(world, rand, x, y, z);
		}
	}
}