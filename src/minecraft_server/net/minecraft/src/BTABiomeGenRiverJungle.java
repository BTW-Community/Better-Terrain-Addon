package net.minecraft.src;

public class BTABiomeGenRiverJungle extends BTABiomeGenBase {
	protected BTABiomeGenRiverJungle(int id) {
		super(id, BTABiomeConfiguration.jungle.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.jungle.waterColorMultiplier;
		this.btaBiomeDecorator.sandPerChunk = 100;
		this.btaBiomeDecorator.sandPerChunk2 = 100;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityJungleSpider.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySpider.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityZombie.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySkeleton.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityCreeper.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityEnderman.class, 1, 1, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityOcelot.class, 2, 1, 1));
	}
}