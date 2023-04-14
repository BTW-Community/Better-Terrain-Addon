package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.terrain.MysticShardsGen;
import betterbiomes.world.feature.tree.legacy.CherryTreeGen;
import betterbiomes.world.feature.tree.legacy.MassiveOakGen;
import betterbiomes.world.feature.tree.legacy.MysticTreeGen;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.TaigaGen6;
import betterterrain.world.feature.tree.legacy.TallSwampTreeGen;
import betterterrain.world.feature.tree.legacy.TemperateBirchGen;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.entity.mob.ChickenEntity;
import btw.entity.mob.PigEntity;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class MysticValleyBiome extends BTABiome {
	public MysticValleyBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		waterColorMultiplier = 0xa78cff;
		this.btaBiomeDecorator.treesPerChunk = 15;
		this.btaBiomeDecorator.grassPerChunk = 20;
		this.btaBiomeDecorator.flowersPerChunk = 30;
		this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 2, 2));
		this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 2, 2));
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.HUGE_OAK_REDWOOD_TREE, 2);
		this.treeGrowers.put(BTATreeGrowers.TALL_SWAMP_OAK_TREE, 3);
		this.treeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 5);
		this.treeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 5);
		this.treeGrowers.put(BTATreeGrowers.OAK_REDWOOD_TREE, 5);

		this.decoTreeGrowers.put(BTATreeGrowers.HUGE_OAK_REDWOOD_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.CHERRY_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.WHITE_CHERRY_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_SWAMP_OAK_TREE, 3);
		this.decoTreeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 5);
		this.decoTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 5);
		this.decoTreeGrowers.put(BTATreeGrowers.OAK_REDWOOD_TREE, 5);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions) {
		WorldGenerator gen;

		if (rand.nextInt(10) == 0) {
			gen = new MassiveOakGen(false);
		}
		else if (BTAMod.isDecoInstalled() && rand.nextInt(8) == 0) {
			gen = new CherryTreeGen();
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

		if (BTAMod.isDecoInstalled()) {
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
}