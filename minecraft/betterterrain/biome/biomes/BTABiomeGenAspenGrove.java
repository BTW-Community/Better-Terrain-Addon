package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import betterterrain.BTAWorldConfigurationInfo;
import betterterrain.feature.BTAWorldGenPineTree;
import betterterrain.feature.BTAWorldGenTaiga5;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BTABiomeGenAspenGrove extends BTABiomeGenAlpine {
	public BTABiomeGenAspenGrove(int id, BTAEnumClimate climate) {
		super(id, climate);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new BTAWorldGenTaiga5(false) : new BTAWorldGenPineTree(false, 2, 2));
    }

    public void decorate(World var1, Random var2, int var3, int var4, BTAWorldConfigurationInfo generatorOptions)
    {
        super.decorate(var1, var2, var3, var4, generatorOptions);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}