package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiomeConfiguration;
import betterterrain.biome.biomes.RiverBiome;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.generate.surface.SwampSurfaceBuilder;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntityWitch;
import net.minecraft.src.Material;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenVines;

public class SwampRiverBiome extends RiverBiome {
	public SwampRiverBiome(int id, String internalName) {
		super(id, internalName, BTABiomeConfiguration.swamp.climate);
		this.waterColorMultiplier = BTABiomeConfiguration.swamp.waterColorMultiplier;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityWitch.class, 1, 1, 1));
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BTABiomeConfiguration.swamp.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BTABiomeConfiguration.swamp.getBiomeFoliageColor();
    }
}