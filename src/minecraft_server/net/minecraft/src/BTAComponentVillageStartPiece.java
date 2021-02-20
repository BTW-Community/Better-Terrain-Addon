package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class BTAComponentVillageStartPiece extends BTAComponentVillageWell
{
    public final WorldChunkManager worldChunkMngr;

    /** Boolean that determines if the village is in a desert or not. */
    public final boolean inDesert;

    /** World terrain type, 0 for normal, 1 for flap map */
    public final int terrainType;
    public StructureVillagePieceWeight structVillagePieceWeight;

    /**
     * Contains List of all spawnable Structure Piece Weights. If no more Pieces of a type can be spawned, they are
     * removed from this list
     */
    public ArrayList structureVillageWeightedPieceList;
    public ArrayList field_74932_i = new ArrayList();
    public ArrayList field_74930_j = new ArrayList();
    private int m_iAbandonmentLevel;
    private int m_iPrimaryCropBlockID;
    private int m_iSecondaryCropBlockID;
    private boolean m_bModSpecificDataInitialized = false;

    public BTAComponentVillageStartPiece(WorldChunkManager par1WorldChunkManager, int par2, Random par3Random, int par4, int par5, ArrayList par6ArrayList, int par7)
    {
        super((BTAComponentVillageStartPiece)null, 0, par3Random, par4, par5);
        this.worldChunkMngr = par1WorldChunkManager;
        this.structureVillageWeightedPieceList = par6ArrayList;
        this.terrainType = par7;
        BiomeGenBase var8 = par1WorldChunkManager.getBiomeGenAt(par4, par5);
        this.inDesert = var8 == BiomeGenBase.desert || var8 == BiomeGenBase.desertHills;
        this.startPiece = this;
    }

    public WorldChunkManager getWorldChunkManager()
    {
        return this.worldChunkMngr;
    }

    public int GetAbandonmentLevel(World var1)
    {
        this.CheckIfModSpecificDataRequiresInit(var1);
        return this.m_iAbandonmentLevel;
    }

    public int GetPrimaryCropBlockID(World var1)
    {
        this.CheckIfModSpecificDataRequiresInit(var1);
        return this.m_iPrimaryCropBlockID;
    }

    public int GetSecondaryCropBlockID(World var1)
    {
        this.CheckIfModSpecificDataRequiresInit(var1);
        return this.m_iSecondaryCropBlockID;
    }

    private void CheckIfModSpecificDataRequiresInit(World var1)
    {
        if (!this.m_bModSpecificDataInitialized)
        {
            this.InitializeModSpecificData(var1);
        }
    }

    private void InitializeModSpecificData(World var1)
    {
        this.m_bModSpecificDataInitialized = true;
        this.m_iAbandonmentLevel = 0;
        int var2 = var1.getWorldInfo().getSpawnX();
        int var3 = var1.getWorldInfo().getSpawnZ();
        int var4 = this.boundingBox.getCenterX();
        int var5 = this.boundingBox.getCenterZ();
        double var6 = (double)(var2 - var4);
        double var8 = (double)(var3 - var5);
        double var10 = var6 * var6 + var8 * var8;
        double var12 = FCUtilsHardcoreSpawn.GetAbandonedVillageRadius();

        if (var10 < var12 * var12)
        {
            this.m_iAbandonmentLevel = 2;
        }
        else
        {
            double var14 = FCUtilsHardcoreSpawn.GetPartiallyAbandonedVillageRadius();

            if (var10 < var14 * var14)
            {
                this.m_iAbandonmentLevel = 1;
                this.m_iPrimaryCropBlockID = FCBetterThanWolves.fcBlockWheatCrop.blockID;
                this.m_iSecondaryCropBlockID = FCBetterThanWolves.fcBlockWheatCrop.blockID;
            }
            else
            {
                this.m_iPrimaryCropBlockID = FCBetterThanWolves.fcBlockWheatCrop.blockID;
                int var16 = var1.rand.nextInt(6);

                if (var16 == 5)
                {
                    this.m_iSecondaryCropBlockID = Block.potato.blockID;
                }
                else if (var16 == 4)
                {
                    this.m_iSecondaryCropBlockID = Block.carrot.blockID;
                }
                else
                {
                    this.m_iSecondaryCropBlockID = FCBetterThanWolves.fcBlockWheatCrop.blockID;
                }
            }
        }
    }
}
