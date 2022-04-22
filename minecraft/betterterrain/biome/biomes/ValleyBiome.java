package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import net.minecraft.src.World;

public class ValleyBiome extends WoodsBiome {
	public ValleyBiome(int par1, Climate climate) {
		super(par1, climate);
	}

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}