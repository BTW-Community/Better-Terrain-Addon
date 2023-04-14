package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TallSwampTreeGen;
import betterterrain.world.feature.tree.legacy.TemperateBirchGen;
import betterterrain.world.feature.tree.legacy.WillowGen;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.entity.mob.ChickenEntity;
import btw.entity.mob.PigEntity;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
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
		this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 2, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 2, 2));
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

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.HUGE_OAK_WILLOW_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.TALL_SWAMP_OAK_TREE, 5);
		this.treeGrowers.put(TreeGrowers.SWAMP_OAK_TREE, 10);
		this.treeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 2);

		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_WILLOW_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.WILLOW_TREE, 10);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_SWAMP_OAK_TREE, 5);
		this.decoTreeGrowers.put(TreeGrowers.SWAMP_OAK_TREE, 10);
		this.decoTreeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 2);
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