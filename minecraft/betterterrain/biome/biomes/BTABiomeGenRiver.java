package betterterrain.biome.biomes;

import betterterrain.BTAEnumClimate;

public class BTABiomeGenRiver extends BTABiomeGenBase
{
    public BTABiomeGenRiver(int id, BTAEnumClimate climate)
    {
        super(id, climate);
        this.spawnableCreatureList.clear();
    }
}