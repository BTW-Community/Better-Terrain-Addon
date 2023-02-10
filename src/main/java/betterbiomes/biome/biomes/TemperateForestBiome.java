package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.FirGen2;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.HazelTreeGen;
import betterterrain.world.feature.tree.legacy.TaigaGen6;
import betterterrain.world.feature.tree.legacy.TemperateBirchGen;
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

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.TALL_SPRUCE_TREE, 2);
		this.treeGrowers.put(BTATreeGrowers.OAK_REDWOOD_TREE, 2);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 1);
		this.treeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 4);
		this.treeGrowers.put(BTATreeGrowers.TALL_OAK_TREE, 6);

		this.decoTreeGrowers.put(BTATreeGrowers.TALL_SPRUCE_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_FIR_TREE, 2);
		this.decoTreeGrowers.put(BTATreeGrowers.REDWOOD_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.OAK_BUSH, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.TALL_OAK_TREE, 6);
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
    	else if (rand.nextInt(8) == 0) {
    		gen = new WorldGenShrub(0, 0);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TemperateBirchGen();
    	}
    	else {
			if (((WorldTypeInterface) worldType).isDeco() && rand.nextInt(2) == 0) {
				gen = new FirGen2();
			}
			else {
				gen = new TaigaGen6(false);
			}
    	}
    	
    	return gen;
    }
}