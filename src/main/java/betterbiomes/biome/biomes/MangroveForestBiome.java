package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.MangroveGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import btw.entity.mob.*;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class MangroveForestBiome extends BTABiome {

	public MangroveForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.flowersPerChunk = -999;
        this.btaBiomeDecorator.mushroomsPerChunk = 8;
        this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.clayPerChunk = 1;
        this.btaBiomeDecorator.waterlilyPerChunk = 10;
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 4, 4));
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(JungleSpiderEntity.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(SpiderEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(ZombieEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(SkeletonEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(CreeperEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EndermanEntity.class, 1, 1, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(OcelotEntity.class, 2, 1, 1));

		this.waterColorMultiplier = 0x00FF74;
	}

	@Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new MangroveGen();
	}

	@Override
	public int getBiomeGrassColor() {
		return 0x6A7039;
	}

	@Override
	public int getBiomeFoliageColor() {
		return 0x8DB127;
	}
}