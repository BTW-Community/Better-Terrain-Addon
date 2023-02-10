package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.entity.mob.*;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenHugeTrees;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class JungleBiome extends BTABiome
{
    public JungleBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 50;
        this.btaBiomeDecorator.grassPerChunk = 25;
        this.btaBiomeDecorator.flowersPerChunk = 4;
		this.btaBiomeDecorator.sandPerChunk = 100;
		this.btaBiomeDecorator.sandPerChunk2 = 100;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(PigEntity.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(ChickenEntity.class, 10, 4, 4));
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(JungleSpiderEntity.class, 2, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(SpiderEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(ZombieEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(SkeletonEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(CreeperEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(SlimeEntity.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EndermanEntity.class, 1, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(OcelotEntity.class, 2, 1, 1));
        this.waterColorMultiplier = 10083127;
    }

    public void initTreeGrowerMap() {
        treeGrowers.put(TreeGrowers.BIG_OAK_TREE, 1);
        treeGrowers.put(TreeGrowers.JUNGLE_BUSH, 6);
        treeGrowers.put(TreeGrowers.JUNGLE_TREE, 4);
        treeGrowers.put(TreeGrowers.BIG_JUNGLE_TREE, 2);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : (par1Random.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (par1Random.nextInt(3) == 0 ? new WorldGenHugeTrees(false, 10 + par1Random.nextInt(20), 3, 3) : new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true))));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(par1World, par2Random, par3, par4, generatorOptions);
        WorldGenVines var5 = new WorldGenVines();

        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 64;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }
}
