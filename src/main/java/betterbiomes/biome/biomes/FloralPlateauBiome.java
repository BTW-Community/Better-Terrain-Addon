package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.MeadowTreeGen1;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.feature.tree.TemperateBirchGen;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenerator;

public class FloralPlateauBiome extends BTABiome {
    public FloralPlateauBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.grassPerChunk = 15;
        this.btaBiomeDecorator.flowersPerChunk = 25;
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random rand) {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = new MeadowTreeGen1();
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new TemperateBirchGen();
    	}
    	else {
    		gen = this.worldGeneratorTrees;
    	}
    	
    	return gen;
    }

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}
