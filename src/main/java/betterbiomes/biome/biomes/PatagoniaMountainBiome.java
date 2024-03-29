package betterbiomes.biome.biomes;

import java.util.Random;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class PatagoniaMountainBiome extends BTABiome {
	public PatagoniaMountainBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.topBlockExt = Block.stone.blockID;
		this.fillerBlockExt = Block.stone.blockID;
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 15064968;
    }

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.addEmeralds(var1, var2, var3, var4);
        this.addSilverfishBlocks(var1, var2, var3, var4);
    }
}