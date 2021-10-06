/**
 * 
 */
package net.minecraft.src;

import java.util.List;

/**
 * @author Abigail Moore
 *
 */
public interface BTAIChunkProvider extends IChunkProvider {
    List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z);
    
    boolean doesStructureExistAtCoords(int x, int y, int z);
}