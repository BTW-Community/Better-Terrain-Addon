package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.*;
import betterterrain.BTAMod;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.feature.plant.TallGrassGen;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenerator;

public class FirCanyonBiome extends BTABiome {
	public FirCanyonBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.btaBiomeDecorator.treesPerChunk = 10;
        this.btaBiomeDecorator.grassPerChunk = 20;
        
        if (BTAMod.isDecoInstalled()) {
        	this.topBlockExt = DecoBlocks.legacyRedSand.blockID;
        	this.fillerBlockExt = DecoBlocks.legacyRedSand.blockID;
        }
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.FIR_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.MEDIUM_FIR_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.TALL_FIR_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.BIG_FIR_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.HUGE_FIR_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.SMALL_FIR_SHRUB, 3);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(7) == 0) {
    		gen = new LargeFirGen();
    	}
    	else if (rand.nextInt(6) == 0) {
    		gen = new FirGen();
    	}
    	else if (rand.nextInt(5) == 0) {
    		gen = new FirGen2();
    	}
    	else if (rand.nextInt(4) == 0) {
    		gen = new FirGen3();
    	}
    	else {
    		gen = new SmallFirShrub();
    	}
    	
    	return gen;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 9341503;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return 9341503;
    }

	@Override
	public boolean shouldConnectWithEdge(WorldConfigurationInfo generatorOptions) {
		return true;
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return par1Random.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}
}