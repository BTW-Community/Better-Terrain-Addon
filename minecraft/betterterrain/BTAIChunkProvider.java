/**
 * 
 */
package betterterrain;

import java.util.List;

import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;

/**
 * @author Abigail Moore
 *
 */
public interface BTAIChunkProvider extends IChunkProvider {
    List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z);
    
    boolean doesStructureExistAtCoords(int x, int y, int z);
}