package net.minecraft.src;

public class BTABiomeGenRiverWillow extends BTABiomeGenRiver {
	public BTABiomeGenRiverWillow(int par1) {
		super(par1, BTABiomeConfiguration.willowGrove.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.willowGrove.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}
}