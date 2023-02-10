package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.feature.tree.legacy.PalmTreeSmallGen;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import net.minecraft.src.WorldGenerator;

public class LushDesertBiome extends BTABiome
{
    public LushDesertBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
        this.btaBiomeDecorator.treesPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 2;
        this.btaBiomeDecorator.reedsPerChunk = 50;
        this.btaBiomeDecorator.cactiPerChunk = 10;
        this.btaBiomeDecorator.oasesPerChunk = 100;
        this.btaBiomeDecorator.waterLakesPerChunk = 100;
		this.btaBiomeDecorator.treesPerChunk = 20;
		this.btaBiomeDecorator.grassPerChunk = 2;
	}

    public void initTreeGrowerMap() {
        this.treeGrowers.put(BTATreeGrowers.PALM_TREE, 1);
    }

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return new PalmTreeSmallGen(false, false);
	}

    public void decorate(World par1World, Random par2Random, int par3, int par4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(par1World, par2Random, par3, par4, generatorOptions);

        if (par2Random.nextInt(1000) == 0)
        {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenDesertWells var7 = new WorldGenDesertWells();
            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
        }
    }

    public boolean canLightningStrikeInBiome()
    {
        return true;
    }
}
