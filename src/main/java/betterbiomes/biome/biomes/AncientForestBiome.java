package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.OldOakGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.tree.HazelTreeGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.*;

public class AncientForestBiome extends BTABiome {
    public AncientForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 2;
    }

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType) {
    	WorldGenerator gen;

		if (((WorldTypeInterface) worldType).isDeco() && rand.nextInt(20) == 0) {
			gen = new HazelTreeGen();
		}
		else if (rand.nextInt(7) == 0) {
    		gen = new OldOakGen(false);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new WorldGenBigTree(false);
    	}
    	else {
    		gen = new WorldGenTrees(false, 6, 0, 0, false);
    	}
    	
    	return gen;
    }
}