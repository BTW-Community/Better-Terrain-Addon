package betterterrain.biome.biomes;

import java.util.Random;

import betterterrain.BTAEnumClimate;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class BTABiomeGenPatagoniaMountains extends BTABiomeGenBase {
	public BTABiomeGenPatagoniaMountains(int id, BTAEnumClimate climate) {
		super(id, climate);
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
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}