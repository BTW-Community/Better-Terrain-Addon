package betterterrain.biome.layer.nether;

import betterterrain.biome.layer.BTALayer;
import net.minecraft.src.IntCache;

import java.util.Arrays;

public class NetherBaseLayer extends BTALayer {
    @Override
    public int[] getInts(int xOffset, int zOffset, int xSize, int zSize) {
        int[] intCache = IntCache.getIntCache(xSize * zSize);
        Arrays.fill(intCache, 1);
        return intCache;
    }
}
