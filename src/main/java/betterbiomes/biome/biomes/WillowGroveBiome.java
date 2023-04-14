package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import betterterrain.world.feature.tree.legacy.WillowGen;
import btw.entity.mob.ChickenEntity;
import btw.entity.mob.PigEntity;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class WillowGroveBiome extends BTABiome {

	public WillowGroveBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 7;
        this.btaBiomeDecorator.flowersPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 1;
        this.btaBiomeDecorator.mushroomsPerChunk = 8;
        this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.clayPerChunk = 1;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 2, 2));
        this.waterColorMultiplier = 3653595;
	}

    public void initTreeGrowerMap() {
        treeGrowers.put(BTATreeGrowers.HUGE_OAK_WILLOW_TREE, 1);
        treeGrowers.put(TreeGrowers.SWAMP_OAK_TREE, 1);

        decoTreeGrowers.put(BTATreeGrowers.HUGE_WILLOW_TREE, 1);
        decoTreeGrowers.put(BTATreeGrowers.WILLOW_TREE, 1);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return new WillowGen();
    }
}