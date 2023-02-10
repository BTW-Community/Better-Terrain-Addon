package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.PalmTreeSmallGen;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.entity.mob.*;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class TropicsBiome extends BTABiome {
	public TropicsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 10;
		this.btaBiomeDecorator.grassPerChunk = 2;
		this.btaBiomeDecorator.sandPerChunk = 100;
		this.btaBiomeDecorator.sandPerChunk2 = 100;
		this.btaBiomeDecorator.melonChancePerChunk = 32;
		this.waterColorMultiplier = 65396;
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

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.PALM_TREE_COCOA, 1);
		this.treeGrowers.put(BTATreeGrowers.JUNGLE_BUSH, 1);
	}
	
	@Override
	public boolean spawnSand(WorldConfigurationInfo info) {
		return info.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_1_3);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand)
	{
    	WorldGenerator gen;
    	
    	if (rand.nextInt(2) == 0) {
    		gen = new PalmTreeSmallGen(false, true);;
    	}
    	else {
    		gen = new WorldGenShrub(3, 3);
    	}
    	
    	return gen;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
    }
}