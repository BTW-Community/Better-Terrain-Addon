package betterterrain.biome.biomes;

import betterterrain.BTADecoIntegration;
import betterterrain.BTAEnumClimate;
import net.minecraft.src.Block;

public class BTABiomeGenBeachOutback extends BTABiomeGenBase
{
    public BTABiomeGenBeachOutback(int id, BTAEnumClimate climate) {
        super(id, climate);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.btaBiomeDecorator.treesPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 0;
        this.btaBiomeDecorator.reedsPerChunk = 0;
        this.btaBiomeDecorator.cactiPerChunk = 0;
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
		}
    }

    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }
}
