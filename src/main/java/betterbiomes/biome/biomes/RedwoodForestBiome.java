package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.*;

import java.util.Random;

public class RedwoodForestBiome extends BTABiome {
    public RedwoodForestBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 5;
    }

    public void initTreeGrowerMap() {
        treeGrowers.put(BTATreeGrowers.OAK_REDWOOD_TREE, 2);
        treeGrowers.put(BTATreeGrowers.HUGE_OAK_REDWOOD_TREE, 2);
        treeGrowers.put(BTATreeGrowers.SMALL_OAK_SHRUB, 4);
        treeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);

        decoTreeGrowers.put(BTATreeGrowers.REDWOOD_TREE, 2);
        decoTreeGrowers.put(BTATreeGrowers.HUGE_REDWOOD_TREE, 2);
        decoTreeGrowers.put(BTATreeGrowers.SMALL_REDWOOD_SHRUB, 4);
        decoTreeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
    }
}
