package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.feature.tree.RainforestGen1;
import betterbiomes.feature.tree.RainforestGen2;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.entity.mob.*;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class RainforestBiome extends BTABiome {

	public RainforestBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 25;
		this.btaBiomeDecorator.grassPerChunk = 7;
		this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
		this.waterColorMultiplier = 6160128;
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
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = new RainforestGen2();
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new RainforestGen1(true);
    	}
    	else {
    		gen = new WorldGenShrub(3, 3);
    	}
    	
    	return gen;
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