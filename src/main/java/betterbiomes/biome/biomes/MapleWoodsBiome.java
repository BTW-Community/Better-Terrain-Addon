package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class MapleWoodsBiome extends BTABiome {
    public MapleWoodsBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 5;
    }

    public void initTreeGrowerMap() {
        decoTreeGrowers.put(BTATreeGrowers.RED_AUTUMN_TREE, 25);
        decoTreeGrowers.put(BTATreeGrowers.BIG_RED_AUTUMN_TREE, 4);
        decoTreeGrowers.put(BTATreeGrowers.HUGE_RED_AUTUMN_TREE, 1);
        decoTreeGrowers.put(BTATreeGrowers.MEDIUM_SPRUCE_TREE, 3);
        decoTreeGrowers.put(BTATreeGrowers.BIG_SPRUCE_TREE, 2);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
    }
}
