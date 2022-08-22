package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.feature.tree.PineTreeGen;
import betterterrain.feature.tree.TaigaGen5;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class AspenGroveBiome extends AlpineBiome {
	public AspenGroveBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
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
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}