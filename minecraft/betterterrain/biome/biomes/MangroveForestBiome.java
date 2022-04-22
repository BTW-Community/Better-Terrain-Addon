package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.MangroveGen;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityCreeper;
import net.minecraft.src.FCEntityEnderman;
import net.minecraft.src.FCEntityJungleSpider;
import net.minecraft.src.FCEntityOcelot;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySkeleton;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntitySpider;
import net.minecraft.src.FCEntityZombie;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class MangroveForestBiome extends BTABiome {

	public MangroveForestBiome(int id, Climate climate) {
		super(id, climate);
        this.btaBiomeDecorator.treesPerChunk = 3;
        this.btaBiomeDecorator.flowersPerChunk = -999;
        this.btaBiomeDecorator.mushroomsPerChunk = 8;
        this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.clayPerChunk = 1;
        this.btaBiomeDecorator.waterlilyPerChunk = 10;
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
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

    /**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new MangroveGen();
		//return this.worldGeneratorSwamp;
	}
}