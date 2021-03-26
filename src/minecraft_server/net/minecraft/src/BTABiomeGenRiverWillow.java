package net.minecraft.src;

public class BTABiomeGenRiverWillow extends BTABiomeGenBase {
	public BTABiomeGenRiverWillow(int par1) {
		super(par1);
		this.waterColorMultiplier = BTABiomeConfiguration.willowGrove.waterColorMultiplier;
        this.btaiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}
}