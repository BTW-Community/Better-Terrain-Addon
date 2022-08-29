package betterbiomes.biome.biomes;

import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import net.minecraft.src.Block;

public class RedSandBeachBiome extends BTABiome
{
    public RedSandBeachBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.btaBiomeDecorator.treesPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 0;
        this.btaBiomeDecorator.reedsPerChunk = 0;
        this.btaBiomeDecorator.cactiPerChunk = 0;
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.redSand.blockID;
			this.fillerBlockExt = DecoIntegration.redSand.blockID;
		}
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}
