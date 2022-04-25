package betterterrain.biome.biomes;

import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;

public class RiverBiome extends BTABiome
{
    public RiverBiome(int id, Climate climate)
    {
        super(id, climate);
        this.spawnableCreatureList.clear();
    }
}