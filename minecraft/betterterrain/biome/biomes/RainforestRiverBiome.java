package betterterrain.biome.biomes;

import betterterrain.biome.BiomeConfiguration;
import net.minecraft.src.FCEntityCreeper;
import net.minecraft.src.FCEntityEnderman;
import net.minecraft.src.FCEntityJungleSpider;
import net.minecraft.src.FCEntityOcelot;
import net.minecraft.src.FCEntitySkeleton;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntitySpider;
import net.minecraft.src.FCEntityZombie;
import net.minecraft.src.SpawnListEntry;

public class RainforestRiverBiome extends RiverBiome {
	public RainforestRiverBiome(int id) {
		super(id, BiomeConfiguration.rainforest.climate);
		this.waterColorMultiplier = BiomeConfiguration.rainforest.waterColorMultiplier;
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

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BiomeConfiguration.rainforest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BiomeConfiguration.rainforest.getBiomeFoliageColor();
    }
}