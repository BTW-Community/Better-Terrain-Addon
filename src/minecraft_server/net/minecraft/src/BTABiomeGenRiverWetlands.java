package net.minecraft.src;

public class BTABiomeGenRiverWetlands extends BTABiomeGenBase {
	public BTABiomeGenRiverWetlands(int par1) {
		super(par1, BTABiomeConfiguration.wetlands.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.wetlands.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}
}