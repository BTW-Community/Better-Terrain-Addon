package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.tree.HazelTreeGen;
import betterterrain.feature.tree.TaigaGen6;
import betterterrain.feature.tree.TemperateBirchGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.*;

public class TemperateForestBiome extends BTABiome {
	public TemperateForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
		this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.treesPerChunk = 30;
        this.btaBiomeDecorator.grassPerChunk = 5;
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType) {
    	WorldGenerator gen;

		if (((WorldTypeInterface) worldType).isDeco() && rand.nextInt(20) == 0) {
			gen = new HazelTreeGen();
		}
		else if (rand.nextInt(4) == 0) {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TemperateBirchGen();
    	}
    	else {
    		gen = new TaigaGen6(false);
    	}
    	
    	return gen;
    }
}