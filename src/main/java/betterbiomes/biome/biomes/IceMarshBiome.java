package betterbiomes.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.ChickenEntity;
import btw.entity.mob.PigEntity;
import btw.entity.mob.SlimeEntity;
import btw.entity.mob.WitchEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class IceMarshBiome extends BTABiome {
    public IceMarshBiome(int id, String internalName, Climate climate) {
        super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 3;
        this.btaBiomeDecorator.grassPerChunk = 5;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(WitchEntity.class, 1, 1, 1));
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 2, 2));
    }

    public void initTreeGrowerMap() {
        treeGrowers.put(BTATreeGrowers.HUGE_DEAD_OAK_TREE, 1);
        treeGrowers.put(BTATreeGrowers.BIG_DEAD_OAK_TREE, 10);
        treeGrowers.put(BTATreeGrowers.FALLEN_OAK_TREE, 10);
        treeGrowers.put(BTATreeGrowers.SMALL_OAK_SHRUB, 15);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
    }
}
