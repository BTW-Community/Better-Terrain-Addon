package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.DecoIntegration;
import betterbiomes.feature.tree.CherryTreeGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;

public class FrozenSpringsBiome extends BTABiome {
	public FrozenSpringsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.reedsPerChunk = 10;
		this.btaBiomeDecorator.treesPerChunk = 2;
		this.btaBiomeDecorator.grassPerChunk = 5;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(5) == 0 && DecoIntegration.isDecoInstalled() && worldType.isDeco()) {
    		gen = new CherryTreeGen();
    	}
    	else {
    		gen = this.worldGeneratorForest;
    	}
    	
    	return gen;
    }
}