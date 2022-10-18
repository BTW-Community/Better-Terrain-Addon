package betterterrain.world.util;

import net.minecraft.src.BiomeGenBase;

import java.util.ArrayList;

public interface StructureScatteredFeatureStartInterface {
    ArrayList<BiomeGenBase> redDesertBiomeList = new ArrayList();

    static void addRedDesertBiome(BiomeGenBase biome) {
        redDesertBiomeList.add(biome);
    }
}
