package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterbiomes.feature.tree.MangroveGen;
import betterterrain.biome.biomes.RiverBiome;
import btw.entity.mob.*;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class MangroveRiverBiome extends RiverBiome {
    public MangroveRiverBiome(int id, String internalName) {
        super(id, internalName, BetterBiomesConfiguration.mangroveForest.climate);
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.flowersPerChunk = -999;
        this.btaBiomeDecorator.mushroomsPerChunk = 8;
        this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.clayPerChunk = 1;
        this.btaBiomeDecorator.waterlilyPerChunk = 10;
        this.waterColorMultiplier = BetterBiomesConfiguration.mangroveForest.waterColorMultiplier;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(JungleSpiderEntity.class, 2, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(SpiderEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(ZombieEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(SkeletonEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(CreeperEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EndermanEntity.class, 1, 1, 4));
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return new MangroveGen();
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return BetterBiomesConfiguration.mangroveForest.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return BetterBiomesConfiguration.mangroveForest.getBiomeFoliageColor();
    }
}
