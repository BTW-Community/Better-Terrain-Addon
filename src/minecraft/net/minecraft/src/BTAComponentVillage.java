package net.minecraft.src;

import java.util.List;
import java.util.Random;

abstract class BTAComponentVillage extends StructureComponent
{
    /** The number of villagers that have been spawned in this component. */
    private int villagersSpawned;

    /** The starting piece of the village. */
    protected BTAComponentVillageStartPiece startPiece;

    protected BTAComponentVillage(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2)
    {
        super(par2);
        this.startPiece = par1ComponentVillageStartPiece;
    }

    /**
     * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
     */
    protected StructureComponent getNextComponentNN(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType());

            case 1:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType());

            case 2:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType());

            case 3:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType());

            default:
                return null;
        }
    }

    /**
     * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
     */
    protected StructureComponent getNextComponentPP(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType());

            case 1:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType());

            case 2:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType());

            case 3:
                return BTAStructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType());

            default:
                return null;
        }
    }

    /**
     * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of all the
     * levels in the BB's horizontal rectangle).
     */
    protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox)
    {
        int var3 = 0;
        int var4 = 0;

        for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; ++var5)
        {
            for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; ++var6)
            {
                if (par2StructureBoundingBox.isVecInside(var6, 64, var5))
                {
                    var3 += Math.max(par1World.getTopSolidOrLiquidBlock(var6, var5), par1World.provider.getAverageGroundLevel());
                    ++var4;
                }
            }
        }

        if (var4 == 0)
        {
            return -1;
        }
        else
        {
            return var3 / var4;
        }
    }

    protected static boolean canVillageGoDeeper(StructureBoundingBox par0StructureBoundingBox)
    {
        return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
    }

    /**
     * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y offset, z
     * offset, number of villagers
     */
    protected void spawnVillagers(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6)
    {
        int var7 = this.startPiece.GetAbandonmentLevel(par1World);
        boolean var8 = false;

        if (var7 > 1)
        {
            if (par1World.rand.nextInt(20) != 0)
            {
                return;
            }

            if (par6 > 1)
            {
                par6 = 1;
            }

            var8 = true;
        }

        if (this.villagersSpawned < par6)
        {
            for (int var9 = this.villagersSpawned; var9 < par6; ++var9)
            {
                int var10 = this.getXWithOffset(par3 + var9, par5);
                int var11 = this.getYWithOffset(par4);
                int var12 = this.getZWithOffset(par3 + var9, par5);

                if (!par2StructureBoundingBox.isVecInside(var10, var11, var12))
                {
                    break;
                }

                ++this.villagersSpawned;
                int var14 = this.getVillagerType(var9);

                if (var14 == 0 || var7 <= 0 || var7 <= 1 && (var14 == 3 || var14 == 4))
                {
                    Object var13;

                    if (par1World.getWorldInfo().getGameType() != EnumGameType.CREATIVE)
                    {
                        FCEntityZombie var15 = new FCEntityZombie(par1World);
                        var15.m_iVillagerClass = var14;
                        var15.SetPersistent(true);
                        var15.setVillager(true);
                        var13 = var15;
                    }
                    else
                    {
                        FCEntityVillager var16 = new FCEntityVillager(par1World, var14);

                        if (var8)
                        {
                            var16.SetDirtyPeasant(1);
                        }

                        var13 = var16;
                    }

                    ((EntityLiving)var13).setLocationAndAngles((double)var10 + 0.5D, (double)var11, (double)var12 + 0.5D, 0.0F, 0.0F);
                    par1World.spawnEntityInWorld((Entity)var13);
                }
            }
        }
    }

    /**
     * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
     */
    protected int getVillagerType(int par1)
    {
        return 0;
    }

    /**
     * Gets the replacement block for the current biome
     */
    protected int getBiomeSpecificBlock(int par1, int par2)
    {
        if (this.startPiece.inDesert)
        {
            if (par1 == Block.wood.blockID)
            {
                return Block.sandStone.blockID;
            }

            if (par1 == Block.cobblestone.blockID)
            {
                return Block.sandStone.blockID;
            }

            if (par1 == Block.planks.blockID)
            {
                return Block.sandStone.blockID;
            }

            if (par1 == Block.stairsWoodOak.blockID)
            {
                return Block.stairsSandStone.blockID;
            }

            if (par1 == Block.stairsCobblestone.blockID)
            {
                return Block.stairsSandStone.blockID;
            }

            if (par1 == Block.gravel.blockID)
            {
                return Block.sandStone.blockID;
            }
        }

        return par1;
    }

    /**
     * Gets the replacement block metadata for the current biome
     */
    protected int getBiomeSpecificBlockMetadata(int par1, int par2)
    {
        if (this.startPiece.inDesert)
        {
            if (par1 == Block.wood.blockID)
            {
                return 0;
            }

            if (par1 == Block.cobblestone.blockID)
            {
                return 0;
            }

            if (par1 == Block.planks.blockID)
            {
                return 2;
            }
        }

        return par2;
    }

    /**
     * current Position depends on currently set Coordinates mode, is computed here
     */
    protected void placeBlockAtCurrentPosition(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        int var8 = this.getBiomeSpecificBlock(par2, par3);
        int var9 = this.getBiomeSpecificBlockMetadata(par2, par3);
        super.placeBlockAtCurrentPosition(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
    }

    /**
     * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int maxY, int
     * maxZ, int placeBlockId, int replaceBlockId, boolean alwaysreplace)
     */
    protected void fillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, boolean par11)
    {
        int var12 = this.getBiomeSpecificBlock(par9, 0);
        int var13 = this.getBiomeSpecificBlockMetadata(par9, 0);
        int var14 = this.getBiomeSpecificBlock(par10, 0);
        int var15 = this.getBiomeSpecificBlockMetadata(par10, 0);
        super.fillWithMetadataBlocks(par1World, par2StructureBoundingBox, par3, par4, par5, par6, par7, par8, var12, var13, var14, var15, par11);
    }

    /**
     * Overwrites air and liquids from selected position downwards, stops at hitting anything else.
     */
    protected void fillCurrentPositionBlocksDownwards(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        int var8 = this.getBiomeSpecificBlock(par2, par3);
        int var9 = this.getBiomeSpecificBlockMetadata(par2, par3);
        super.fillCurrentPositionBlocksDownwards(par1World, var8, var9, par4, par5, par6, par7StructureBoundingBox);
    }
}
