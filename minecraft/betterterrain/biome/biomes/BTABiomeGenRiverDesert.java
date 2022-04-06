package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;
import net.minecraft.src.Block;

public class BTABiomeGenRiverDesert extends BTABiomeGenRiver {
	public BTABiomeGenRiverDesert(int par1)
    {
        super(par1, BTABiomeConfiguration.desert.climate);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}