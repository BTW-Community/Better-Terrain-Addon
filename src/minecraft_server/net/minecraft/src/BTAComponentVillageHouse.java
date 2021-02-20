package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageHouse extends BTAComponentVillage
{
	private int averageGroundLevel = -1;

	public BTAComponentVillageHouse(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
	{
		super(par1ComponentVillageStartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4StructureBoundingBox;
	}

	public static BTAComponentVillageHouse func_74921_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 15, 10, 15, par6);
		return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new BTAComponentVillageHouse(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 10 - 1, 0);
		}
		
        for (int i = 3; i <= 11; i++)
        {
            for (int j = 3; j <= 11; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, 0, i, par3StructureBoundingBox);
            }
        }
		
        //Clears space above
        for (int i = 1; i <= 13; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, 1, i, par3StructureBoundingBox);
            }
        }

		//Foundation
		for (int i = 3; i <= 11; i++)
        {
            for (int j = 3; j <= 11; j++)
            {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, j, 0, i, par3StructureBoundingBox);
            }
        }

		//Log pillars
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 6, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 12, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 5, 12, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 12, 5, 12, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 12, 5, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 12, 5, 6, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 12, 5, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 9, 5, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 5, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 5, 6, par3StructureBoundingBox);

		//Base supports
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 6, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 12, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 2, 1, 13, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 1, 13, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 12, 0, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 12, 1, 13, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 13, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 1, 2, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 13, 0, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 1, 6, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 13, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 1, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 13, 0, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 1, 12, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 2, 1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 9, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 9, 1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 12, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 12, 1, 1, par3StructureBoundingBox);

		//Spruce stair lower trim
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 3, 2, 0, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 7, 2, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 10, 2, 0, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 0, 12, 5, 0, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 0, 12, 7, 0, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 12, 11, 0, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 12, 0, 3, 12, 0, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 12, 0, 7, 12, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 12, 0, 10, 12, 0, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 0, 2, 8, 0, 2, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 10, 0, 2, 11, 0, 2, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4);

		//Leaf trim
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 7, 2, 1, 8, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 10, 2, 1, 11, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 12, 5, 1, 12, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 12, 7, 1, 12, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 1, 12, 11, 1, 12, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 3, 12, 1, 5, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 7, 12, 1, 8, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 1, 10, 12, 1, 11, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 2, 8, 1, 2, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 1, 2, 11, 1, 2, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 1, 3, 6, 1, 5, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

		//Log cross beams
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 5, 3, 6, 5, 5, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 5, 4, 2, 5, 5, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 5, 7, 2, 5, 8, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 5, 10, 2, 5, 11, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 12, 5, 5, 12, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 12, 11, 5, 12, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 5, 3, 12, 5, 5, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 5, 7, 12, 5, 8, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 5, 10, 12, 5, 11, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 6, 5, 5, 6, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 2, 8, 5, 2, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 5, 2, 11, 5, 2, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);

		//Inner cross beams
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 7, 3, 5, 11, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 5, 11, 11, 5, 11, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 5, 4, 11, 5, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 3, 11, 5, 3, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 4, 7, 5, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 5, 7, 6, 5, 7, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);

		//Walls
		//	Done in a dumb way because I had issues before that I know the cause of but I'm too lazy to simplify
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 1, 5, 7, 4, 5, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 1, 4, 7, 4, 4, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 1, 3, 7, 4, 3, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 3, 8, 4, 3, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, 3, 9, 4, 3, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 3, 10, 4, 3, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 3, 11, 4, 3, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 4, 11, 4, 4, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 5, 11, 4, 5, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 6, 11, 4, 6, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 7, 11, 4, 7, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 8, 11, 4, 8, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 9, 11, 4, 9, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 10, 11, 4, 10, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 11, 11, 4, 11, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 1, 11, 7, 4, 11, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 11, 6, 4, 11, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 11, 5, 4, 11, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 11, 4, 4, 11, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 11, 3, 4, 11, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 10, 3, 4, 10, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 9, 3, 4, 9, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 8, 3, 4, 8, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 7, 3, 4, 7, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 7, 4, 4, 7, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 7, 5, 4, 7, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);

		//Patio
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 1, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.gateSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.gateSpruce.blockID, 1), 2, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 3, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 4, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 5, 0, 2, par3StructureBoundingBox);

		//Wall arches
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 2, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 2, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 2, 4, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 2, 4, 11, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 6, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 6, 4, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 12, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 12, 4, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 4, 11, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 5, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 7, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 11, 4, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 5, 4, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 5, 4, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 7, 4, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 11, 4, 12, par3StructureBoundingBox);

		//Wall arch trapdoors
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 2, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 3, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 2, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 2, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 2, 2, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 6, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 6, 3, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 6, 2, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 6, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 6, 2, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 12, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 12, 3, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 12, 2, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 12, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 12, 2, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 2, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 2, 4, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 3, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 2, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 2, 3, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 2, 2, 7, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 12, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 12, 4, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 12, 3, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 12, 2, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 12, 3, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 12, 2, 7, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 4, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 1, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 4, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 3, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 2, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 1, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 4, 4, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 3, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 2, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 12, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 8, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 10, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 11, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 11, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 2, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 11, 3, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 11, 2, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 3, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 2, 12, par3StructureBoundingBox);

		//Ceiling
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 6, 7, 3, 6, 11, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 6, 11, 11, 6, 11, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 6, 4, 11, 6, 10, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 6, 3, 11, 6, 3, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 6, 4, 7, 6, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 6, 7, 6, 6, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 6, 4, 10, 6, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 6, 8, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 6, 4, 8, 6, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 6, 8, 8, 6, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 6, 10, 10, 6, 10, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 7, 8, 11, 7, 10, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 7, 3, 10, 7, 8, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 8, 9, 11, 8, 9, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 8, 3, 9, 8, 8, Block.planks.blockID, 1, Block.planks.blockID, 1, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 3, 7, 5, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 2, 5, 3, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 6, 5, 7, 6, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 2, 6, 5, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 7, 7, 7, 7, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 2, 7, 7, par3StructureBoundingBox);

		//Roof faces
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 6, 9, 2, 8, 9, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 2, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 2, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 2, 7, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 2, 6, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 2, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 2, 7, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLogStripped.blockID, 1, 2, 6, 6, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 5, 1, 5, 11, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 6, 9, 12, 8, 9, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 7, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 6, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 7, 10, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 5, 7, 13, 5, 11, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 6, 2, 9, 8, 2, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 7, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 8, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 8, 7, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 11, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 10, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 10, 7, 2, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 1, 11, 5, 1, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, false);

		//Roof
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 4, 5, 1, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1), BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 5, 2, 4, 5, 2, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 1, 5, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 1, 5, 3, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 6, 4, 5, 6, 4, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 1, 6, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 5, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 1, 6, 5, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 7, 6, 6, 7, 6, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 1, 7, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 6, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 1, 7, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 7, 8, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 8, 8, 8, 8, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3), 1, 8, 8, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3), 13, 5, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 13, 5, 6, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 6, 6, 12, 6, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3), 13, 6, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 13, 6, 7, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 7, 7, 12, 7, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3), 13, 7, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 13, 7, 8, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 8, 8, 12, 8, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3), 13, 8, 8, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 5, 2, 5, 5, 2, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 5, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 6, 5, 1, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 6, 2, 6, 6, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 6, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 7, 6, 1, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 7, 2, 7, 7, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 7, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 8, 7, 1, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 8, 2, 8, 8, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 8, 8, 1, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 5, 1, 13, 5, 4, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 13, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 12, 5, 1, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 6, 2, 12, 6, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 12, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 11, 6, 1, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 7, 2, 11, 7, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 11, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 10, 7, 1, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 8, 2, 10, 8, 7, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 10, 8, 1, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 13, 13, 5, 13, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 1, 5, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 13, 5, 12, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 6, 12, 12, 6, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), 1, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 1, 6, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), 13, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 13, 6, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 7, 11, 12, 7, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), 1, 7, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 1, 7, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), 13, 7, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 13, 7, 10, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 8, 10, 12, 8, 10, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), 1, 8, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2), 13, 8, 10, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 9, 9, 13, 9, 9, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 1, 8, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 0, 9, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 13, 8, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 14, 9, 9, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 9, 1, 9, 9, 8, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 9, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 9, 9, 0, par3StructureBoundingBox);

		//Roof under rim
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 1, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, 2, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 3, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, 4, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 5, 4, 1, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 13, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 13, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 13, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 13, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 13, 4, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, 2, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 3, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, 4, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 5, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, 6, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 7, 4, 13, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 11, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, 12, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 13, 4, 13, par3StructureBoundingBox);

		//Fireplace
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 0, 10, 10, 0, 13, BTADecoIntegration.stoneTypesSmooth.blockID, BTADecoIntegration.stoneTypesSmooth.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 12, 10, 2, 13, BTADecoIntegration.stoneTypesStoneBrick.blockID, BTADecoIntegration.stoneTypesStoneBrick.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 3, 11, 10, 3, 13, BTADecoIntegration.stoneTypesSmooth.blockID, BTADecoIntegration.stoneTypesSmooth.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 4, 12, 10, 4, 13, BTADecoIntegration.stoneTypesStoneBrick.blockID, BTADecoIntegration.stoneTypesStoneBrick.blockID, false);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteSmoothSidingAndCorner.blockID, 14, 8, 0, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteSmoothSidingAndCorner.blockID, 14, 10, 0, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 8, 1, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 10, 1, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 8, 2, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 10, 2, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteSmoothSidingAndCorner.blockID, 14, 8, 3, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteSmoothSidingAndCorner.blockID, 14, 10, 3, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 8, 4, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 10, 4, 13, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 8, -1, 12, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 9, -1, 12, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 10, -1, 12, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 8, -1, 13, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 9, -1, 13, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 10, -1, 13, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 9, 5, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 9, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickMouldingAndDecorative.blockID, 13, 9, 7, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickMouldingAndDecorative.blockID, 12, 9, 8, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickMouldingAndDecorative.blockID, 12, 9, 9, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 9, 10, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 9, 11, 12, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 8, 1, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 10, 1, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 8, 2, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 10, 2, 11, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 1) + 4, 8, 4, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 3) + 4, 9, 4, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 0) + 4, 10, 4, 11, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodCinders.blockID, 0, 9, 0, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, 0, 9, 1, 11, par3StructureBoundingBox);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 10, 10, 1, 10, Block.fenceIron.blockID, Block.fenceIron.blockID, false);

		//Inside
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 8, 9, 1, 9, BTADecoIntegration.carpet.blockID, 6, BTADecoIntegration.carpet.blockID, 6, false);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 0), 9, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 0) + 8, 9, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 0), 10, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 0) + 8, 10, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 7, 1, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 2, 7, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 2, 6, 1, 7, par3StructureBoundingBox);

		//Door
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 4, 1, 7, 3, (BlockDoor) BTADecoIntegration.doorSpruce);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 1, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 2, 7, par3StructureBoundingBox);
		}

		//Windows
		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 3, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 2, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 3, 3, 10, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 3, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 2, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 3, 10, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 8, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 8, 3, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 10, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 10, 3, 3, par3StructureBoundingBox);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 3, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 2, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 3, 10, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 3, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 3, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 2, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 3, 10, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 3, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, 3, 3, par3StructureBoundingBox);
		}

		this.spawnVillagers(par1World, par3StructureBoundingBox, 4, 1, 2, 2);
		return true;
	}
}
