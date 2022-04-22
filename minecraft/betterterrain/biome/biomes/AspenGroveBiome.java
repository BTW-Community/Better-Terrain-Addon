package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.PineTreeGen;
import betterterrain.feature.TaigaGen5;
import betterterrain.world.WorldConfigurationInfo;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class AspenGroveBiome extends AlpineBiome {
	public AspenGroveBiome(int id, Climate climate) {
		super(id, climate);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new TaigaGen5(false) : new PineTreeGen(false, 2, 2));
    }

    public void decorate(World var1, Random var2, int var3, int var4, WorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}