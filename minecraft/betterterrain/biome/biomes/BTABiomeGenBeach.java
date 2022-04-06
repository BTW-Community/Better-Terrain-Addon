package betterterrain.biome.biomes;

import betterterrain.BTAEnumClimate;
import net.minecraft.src.Block;

public class BTABiomeGenBeach extends BTABiomeGenBase
{
    public BTABiomeGenBeach(int id, BTAEnumClimate climate) {
        super(id, climate);
        this.spawnableCreatureList.clear();
        this.topBlockExt = (byte)Block.sand.blockID;
        this.fillerBlockExt = (byte)Block.sand.blockID;
        this.btaBiomeDecorator.treesPerChunk = -999;
        this.btaBiomeDecorator.deadBushPerChunk = 0;
        this.btaBiomeDecorator.reedsPerChunk = 0;
        this.btaBiomeDecorator.cactiPerChunk = 0;
    }
}
