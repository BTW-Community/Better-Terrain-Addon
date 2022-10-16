package betterterrain.biome.biomes;

import java.util.Random;

import betterbiomes.world.generate.surface.HotSpringsSurfaceBuilder;
import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.feature.tree.TallSwampTreeGen;
import betterterrain.feature.tree.TemperateBirchGen;
import betterterrain.feature.tree.WillowGen;
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
		this.btaBiomeDecorator.grassPerChunk = 5;
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.flowersPerChunk = -999;
		this.btaBiomeDecorator.deadBushPerChunk = 10;
		this.btaBiomeDecorator.mushroomsPerChunk = 8;
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.clayPerChunk = 1;
		this.btaBiomeDecorator.waterlilyPerChunk = 10;
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 2, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 2, 2));
		this.waterColorMultiplier = 14745518;
	}

	@Override
	public int getBiomeGrassColor() {
		return 0x5f9b76;
	}

	@Override
	public int getBiomeFoliageColor() {
		return 0x5C8E47;
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		WorldGenerator gen;

		if (rand.nextInt(20) == 0) {
			gen = new WillowGen();
		}
		else if (rand.nextInt(10) == 0) {
			gen = new TemperateBirchGen();
		}
		else if (rand.nextInt(4) == 0) {
			gen = new TallSwampTreeGen();
		}
		else {
			gen = this.worldGeneratorSwamp;
		}

		return gen;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(6) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 0) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ, WorldConfigurationInfo generatorOptions) {
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