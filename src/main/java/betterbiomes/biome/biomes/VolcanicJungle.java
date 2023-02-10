package betterbiomes.biome.biomes;

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

public class VolcanicJungle extends BTABiome
{
    public VolcanicJungle(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 15;
        this.btaBiomeDecorator.grassPerChunk = 50;
        this.btaBiomeDecorator.flowersPerChunk = 10;
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
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
        this.waterColorMultiplier = 65396;
    }

    public void initTreeGrowerMap() {
        treeGrowers.put(TreeGrowers.BIG_OAK_TREE, 1);
        treeGrowers.put(TreeGrowers.JUNGLE_BUSH, 6);
        treeGrowers.put(TreeGrowers.JUNGLE_TREE, 4);
        treeGrowers.put(TreeGrowers.BIG_JUNGLE_TREE, 2);
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random rand) {
        WorldGenerator gen;
        
        if (rand.nextInt(7) == 0) {
        	gen = this.worldGeneratorBigTree;
        }
        else if (rand.nextInt(5) == 0) {
        	gen = new WorldGenHugeTrees(false, 10 + rand.nextInt(20), 3, 3);
        }
        else if (rand.nextInt(6) == 0) {
        	gen = new WorldGenShrub(3, 3);
        }
        else {
        	gen = new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
        }
        
        return gen;
    }

	@Override
	public int getBiomeGrassColor() {
		return 0x89dd4e;
	}

	@Override
	public int getBiomeFoliageColor() {
		return 0xb4ec4e;
	}

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
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
