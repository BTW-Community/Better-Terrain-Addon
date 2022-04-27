package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import net.minecraft.src.Block;

public class DesertRiverBiome extends RiverBiome {
	public DesertRiverBiome(int par1)
    {
        super(par1, BetterBiomesConfiguration.desert.climate);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}