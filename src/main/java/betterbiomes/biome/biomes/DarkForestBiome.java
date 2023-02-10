package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class DarkForestBiome extends BTABiome {
    public DarkForestBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
        this.btaBiomeDecorator.treesPerChunk = 30;
        this.btaBiomeDecorator.bigRedMushroomsPerChunk = 7;
        this.btaBiomeDecorator.grassPerChunk = 40;
    }

    public void initTreeGrowerMap() {
        decoTreeGrowers.put(BTATreeGrowers.DARK_OAK_TREE, 3);
        decoTreeGrowers.put(BTATreeGrowers.HUGE_DARK_OAK_TREE, 6);
        decoTreeGrowers.put(BTATreeGrowers.TALL_OAK_TREE, 1);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
    }

    @Override
    public int getBiomeGrassColor() {
        return 0x507A32;
    }
}
