package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageHouseSmall extends BTAComponentVillage
{
    private int averageGroundLevel = -1;

    public BTAComponentVillageHouseSmall(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
    }

    public static BTAComponentVillageHouseSmall func_74912_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 9, 10, 9, par6);
        return StructureComponent.findIntersecting(par1List, var8) != null ? null : new BTAComponentVillageHouseSmall(par0ComponentVillageStartPiece, par7, par2Random, var8, par6);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(par1World, par3StructureBoundingBox);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 10, 0);
        }
		
        //Clears space above
        for (int i = 1; i <= 9; i++)
        {
            for (int j = 1; j <= 9; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, 1, i, par3StructureBoundingBox);
            }
        }
        
        for (int i = 3; i <= 7; i++)
        {
            for (int j = 3; j <= 7; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, 0, i, par3StructureBoundingBox);
            }
        }

		//Foundation
		for (int i = 3; i <= 7; i++)
        {
            for (int j = 3; j <= 7; j++)
            {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, j, 0, i, par3StructureBoundingBox);
            }
        }
        
        //Log pillars
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 8, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 5, 5, 8, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 8, 5, 8, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 8, 5, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 8, 5, 2, par3StructureBoundingBox);
        
        //Base supports
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 8, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 2, 1, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 5, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 5, 1, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 8, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 8, 1, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 9, 0, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 9, 1, 8, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 9, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 9, 1, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 9, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 9, 1, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 8, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 8, 1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 2, 1, 1, par3StructureBoundingBox);
		
		//Log cross beams
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 5, 2, 7, 5, 3, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 5, 7, 7, 5, 8, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 5, 3, 3, 5, 7, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 5, 3, 8, 5, 7, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8));
		
		//Walls
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 3, 7, 4, 7, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 4, 6, 4, 6, 0, 0, false);
		
		//Lower stair trim
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 3, 2, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 6, 2, 0, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);

		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 0, 8, 4, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 6, 0, 8, 7, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
		
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 0, 3, 8, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 0, 6, 8, 0, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
		
		//Leaves
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 1, 3, 2, 1, 4, Block.leaves.blockID, 12);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 1, 6, 2, 1, 7, Block.leaves.blockID, 12);
		
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 1, 8, 4, 1, 8, Block.leaves.blockID, 12);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 6, 1, 8, 7, 1, 8, Block.leaves.blockID, 12);
		
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 1, 3, 8, 1, 4, Block.leaves.blockID, 12);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 1, 6, 8, 1, 7, Block.leaves.blockID, 12);
		
		//Wall arches
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 2, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 2, 4, 7, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 8, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 8, 4, 7, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 7, 4, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 7, 4, 8, par3StructureBoundingBox);
		
		//Wall arch trapdoors
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 2, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 2, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 3, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 2, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 2, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 2, 2, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 8, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 8, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 8, 3, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 8, 2, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 8, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 8, 2, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 4, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 6, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 7, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 7, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 7, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 1, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 4, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 6, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 7, 3, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 7, 2, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 8, par3StructureBoundingBox);
		
		//Front facade
		//Door
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 5, 1, 3, 3, (BlockDoor) BTADecoIntegration.doorSpruce);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 1, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 2, 3, par3StructureBoundingBox);
		}
		//Steps
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 3, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 4, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 5, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 6, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 7, 0, 2, par3StructureBoundingBox);
		//Awning
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 4, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 4, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 6, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 6, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 4, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 5, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 3, 2, par3StructureBoundingBox);

		//Ceiling
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 6, 3, 7, 6, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 6, 4, 6, 6, 6, 0, 0, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 6, 4, 4, 6, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 6, 4, 6, 6, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 7, 3, 6, 7, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 8, 3, 5, 8, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		
		//Roof faces
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 6, 2, 5, 8, 2, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 3, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 4, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 4, 7, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 7, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 6, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 6, 7, 2, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 1, 7, 5, 1, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 6, 8, 5, 8, 8, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 3, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 4, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 4, 7, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 7, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 6, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 6, 7, 8, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 9, 7, 5, 9, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, false);
		
		//Roof
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 1, 5, 9, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 2, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 2, 5, 9, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 6, 1, 2, 6, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 2, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 3, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 2, 6, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 3, 6, 9, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 7, 1, 3, 7, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 3, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 4, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 3, 7, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 4, 7, 9, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 8, 1, 4, 8, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 4, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 4, 8, 9, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 5, 1, 9, 5, 9, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 8, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 8, 5, 9, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 6, 1, 8, 6, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 8, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 7, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 8, 6, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 7, 6, 9, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 7, 1, 7, 7, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 7, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 6, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 7, 7, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 6, 7, 9, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 8, 1, 6, 8, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 6, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 6, 8, 9, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 9, 1, 5, 9, 9, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 5, 9, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 5, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 5, 9, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 5, 8, 9, par3StructureBoundingBox);
		
		//Roof under rim
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 1, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 1, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 1, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 1, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 9, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 9, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 9, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 9, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 9, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 9, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 9, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 9, 4, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 9, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 9, 4, 9, par3StructureBoundingBox);

		//Inside
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 6, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 2, 6, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2), 4, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2) + 8, 4, 1, 6, par3StructureBoundingBox);
		
		//Windows
		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 2, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 3, 6, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 2, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 3, 6, par3StructureBoundingBox);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 2, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 3, 6, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 2, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 3, 6, par3StructureBoundingBox);
		}
		
        this.spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
        return true;
    }
}
