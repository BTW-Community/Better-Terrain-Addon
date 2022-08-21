package betterbiomes.biome.biomes.deprecated;

import java.util.Random;

import betterbiomes.feature.tree.TaigaGen5;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class ForestedIcyPeaksBiome extends BTABiome {
	public ForestedIcyPeaksBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 3;
        this.btaBiomeDecorator.grassPerChunk = 2;
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new TaigaGen5(false) : new WorldGenTaiga2(false));
    }

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}