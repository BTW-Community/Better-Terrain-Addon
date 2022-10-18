package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.tree.HazelTreeGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;

import java.util.Random;

public class PatagoniaBiome extends BTABiome {
	public PatagoniaBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 30;
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 15064968;
    }

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType) {
		WorldGenerator gen;

		if (((WorldTypeInterface) worldType).isDeco()) {
			gen = new HazelTreeGen();
		}
		else {
			gen = this.worldGeneratorTrees;
		}

		return gen;
	}
}