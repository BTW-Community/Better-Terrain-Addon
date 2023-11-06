package betterterrain.mixins;

import betterterrain.structure.mapgen.BTAMapGenVillage;
import betterterrain.world.BTAWorldChunkManager;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(StructureVillagePieces.class)
public abstract class StructureVillagePiecesMixin {
    /**
     * @author Dawnraider00 / TheLadyDawn
     * @reason Changing of conditional statement which would otherwise require complicated mixin in order to preserve determinism in world generation
     */
    @Overwrite
    private static StructureComponent getNextVillageStructureComponent(ComponentVillageStartPiece componentVillageStartPiece, List list, Random rand, int par3, int par4, int par5, int par6, int par7) {
        if (par7 > 50) {
            return null;
        } else if (Math.abs(par3 - componentVillageStartPiece.getBoundingBox().minX) <= 112 && Math.abs(par5 - componentVillageStartPiece.getBoundingBox().minZ) <= 112) {
            ComponentVillage var8 = getNextVillageComponent(componentVillageStartPiece, list, rand, par3, par4, par5, par6, par7 + 1);
            
            if (var8 != null) {
                int var9 = (var8.getBoundingBox().minX + var8.getBoundingBox().maxX) / 2;
                int var10 = (var8.getBoundingBox().minZ + var8.getBoundingBox().maxZ) / 2;
                int var11 = var8.getBoundingBox().maxX - var8.getBoundingBox().minX;
                int var12 = var8.getBoundingBox().maxZ - var8.getBoundingBox().minZ;
                int var13 = var11 > var12 ? var11 : var12;
                
                List villageSpawnBiomesList;
                
                if (componentVillageStartPiece.getWorldChunkManager() instanceof BTAWorldChunkManager) {
                    System.out.println("BTAWorld");
                    villageSpawnBiomesList = MapGenVillage.villageSpawnBiomes;
                } else {
                    System.out.println("Regular World");
                    villageSpawnBiomesList = MapGenVillage.villageSpawnBiomes;
                }
                
                if (componentVillageStartPiece.getWorldChunkManager().areBiomesViable(var9, var10, var13 / 2 + 4, villageSpawnBiomesList)) {
                    list.add(var8);
                    componentVillageStartPiece.field_74932_i.add(var8);
                    return var8;
                }
            }
            
            return null;
        } else {
            return null;
        }
    }
    
    /**
     * @author Dawnraider00 / TheLadyDawn
     * @reason Changing of conditional statement which would otherwise require complicated mixin in order to preserve determinism in world generation
     */
    @Overwrite
    private static StructureComponent getNextComponentVillagePath(ComponentVillageStartPiece componentVillageStartPiece, List list, Random rand, int par3, int par4, int par5, int par6, int par7) {
        if (par7 > 3 + componentVillageStartPiece.terrainType) {
            return null;
        } else if (Math.abs(par3 - componentVillageStartPiece.getBoundingBox().minX) <= 112 && Math.abs(par5 - componentVillageStartPiece.getBoundingBox().minZ) <= 112) {
            StructureBoundingBox var8 = ComponentVillagePathGen.func_74933_a(componentVillageStartPiece, list, rand, par3, par4, par5, par6);
            
            if (var8 != null && var8.minY > 10) {
                ComponentVillagePathGen var9 = new ComponentVillagePathGen(componentVillageStartPiece, par7, rand, var8, par6);
                int var10 = (var9.getBoundingBox().minX + var9.getBoundingBox().maxX) / 2;
                int var11 = (var9.getBoundingBox().minZ + var9.getBoundingBox().maxZ) / 2;
                int var12 = var9.getBoundingBox().maxX - var9.getBoundingBox().minX;
                int var13 = var9.getBoundingBox().maxZ - var9.getBoundingBox().minZ;
                int var14 = var12 > var13 ? var12 : var13;
    
                List villageSpawnBiomesList;
    
                if (componentVillageStartPiece.getWorldChunkManager() instanceof BTAWorldChunkManager) {
                    System.out.println("BTAWorld");
                    villageSpawnBiomesList = MapGenVillage.villageSpawnBiomes;
                } else {
                    System.out.println("Regular World");
                    villageSpawnBiomesList = MapGenVillage.villageSpawnBiomes;
                }
                
                if (componentVillageStartPiece.getWorldChunkManager().areBiomesViable(var10, var11, var14 / 2 + 4, villageSpawnBiomesList)) {
                    list.add(var9);
                    componentVillageStartPiece.field_74930_j.add(var9);
                    return var9;
                }
            }
            
            return null;
        } else {
            return null;
        }
    }
    
    @Shadow
    private static ComponentVillage getNextVillageComponent(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        throw new IllegalStateException();
    }
}
