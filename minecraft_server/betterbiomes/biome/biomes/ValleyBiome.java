package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.Climate;
import betterterrain.biome.biomes.WoodsBiome;
import net.minecraft.src.World;

public class ValleyBiome extends WoodsBiome {
	public ValleyBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
	}

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}