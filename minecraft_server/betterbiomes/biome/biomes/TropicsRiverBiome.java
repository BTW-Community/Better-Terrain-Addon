package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import net.minecraft.src.FCEntityCreeper;
import net.minecraft.src.FCEntityEnderman;
import net.minecraft.src.FCEntityJungleSpider;
import net.minecraft.src.FCEntityOcelot;
import net.minecraft.src.FCEntitySkeleton;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntitySpider;
import net.minecraft.src.FCEntityZombie;
import net.minecraft.src.SpawnListEntry;

public class TropicsRiverBiome extends RiverBiome {
	public TropicsRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.tropics.climate);
		this.waterColorMultiplier = BetterBiomesConfiguration.tropics.waterColorMultiplier;
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