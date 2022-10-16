package betterterrain.biome.biomes;

import betterterrain.biome.BTABiomeConfiguration;
import net.minecraft.src.Block;

public class DesertRiverBiome extends RiverBiome {
	public DesertRiverBiome(int id, String internalName) {
		super(id, internalName, BTABiomeConfiguration.desert.climate);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}