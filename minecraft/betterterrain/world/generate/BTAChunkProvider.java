/**
 * 
 */
package betterterrain.world.generate;

import java.util.List;

import betterterrain.structure.mapgen.BTAMapGenNetherBridge;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;

/**
 * @author Abigail Moore
 *
 */
public interface BTAChunkProvider extends IChunkProvider {
    List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z);
    
    boolean doesStructureExistAtCoords(int x, int y, int z);
    
    boolean isNether();
    
    BTAMapGenNetherBridge getNetherBridgeGenerator();
}