package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.BTAWorldConfigurationInfo;
import betterterrain.feature.BTAWorldGenRainforest1;
import betterterrain.feature.BTAWorldGenRainforest2;
import net.minecraft.src.FCEntityChicken;
import net.minecraft.src.FCEntityCreeper;
import net.minecraft.src.FCEntityEnderman;
import net.minecraft.src.FCEntityJungleSpider;
import net.minecraft.src.FCEntityOcelot;
import net.minecraft.src.FCEntityPig;
import net.minecraft.src.FCEntitySkeleton;
import net.minecraft.src.FCEntitySlime;
import net.minecraft.src.FCEntitySpider;
import net.minecraft.src.FCEntityZombie;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenRainforest extends BTABiomeGenBase {

	public BTABiomeGenRainforest(int id, BTAEnumClimate climate) {
		super(id, climate);
		this.btaBiomeDecorator.treesPerChunk = 25;
		this.btaBiomeDecorator.grassPerChunk = 7;
		this.btaBiomeDecorator.reedsPerChunk = 10;
        this.btaBiomeDecorator.waterlilyPerChunk = 4;
		this.waterColorMultiplier = 6160128;
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityJungleSpider.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySpider.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityZombie.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySkeleton.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityCreeper.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityEnderman.class, 1, 1, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(FCEntityOcelot.class, 2, 1, 1));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
    	WorldGenerator gen;
    	
    	if (rand.nextInt(10) == 0) {
    		gen = new BTAWorldGenRainforest2();
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
    	}
    	else if (rand.nextInt(2) == 0) {
    		gen = new BTAWorldGenRainforest1(true);
    	}
    	else {
    		gen = new WorldGenShrub(3, 3);
    	}
    	
    	return gen;
	}

    public void decorate(World par1World, Random par2Random, int par3, int par4, BTAWorldConfigurationInfo generatorOptions)
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