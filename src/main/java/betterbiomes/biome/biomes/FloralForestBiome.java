package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.MeadowTreeGen1;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.HazelTreeGen;
import betterterrain.world.feature.tree.legacy.TemperateBirchGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import btw.entity.mob.WolfEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.WorldType;

public class FloralForestBiome extends BTABiome {
	WorldGenerator meadowGen = new MeadowTreeGen1();
	WorldGenerator birchGen = new TemperateBirchGen();
	
    public FloralForestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 15;
        this.btaBiomeDecorator.flowersPerChunk = 25;
    }

	public void initTreeGrowerMap() {
		this.treeGrowers.put(TreeGrowers.OAK_TREE, 12);
		this.treeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 6);
		this.treeGrowers.put(BTATreeGrowers.MEADOW_SPRUCE_TREE, 4);

		this.decoTreeGrowers.put(TreeGrowers.OAK_TREE, 12);
		this.decoTreeGrowers.put(BTATreeGrowers.TEMPERATE_BIRCH_TREE, 6);
		this.decoTreeGrowers.put(BTATreeGrowers.MEADOW_SPRUCE_TREE, 4);
		this.decoTreeGrowers.put(BTATreeGrowers.HAZEL_TREE, 1);
	}

    @Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand, WorldConfigurationInfo generatorOptions, WorldType worldType) {
    	WorldGenerator gen;

		if (((WorldTypeInterface) worldType).isDeco() && rand.nextInt(20) == 0) {
			gen = new HazelTreeGen();
		}
		else if (rand.nextInt(10) == 0) {
    		gen = this.meadowGen;
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = this.birchGen;
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