package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageField extends BTAComponentVillage
{
	private int averageGroundLevel = -1;

	private int cropPrimary;
	private int cropSecondary;

	private boolean isGreenhouse;
	private boolean isCovered;

	public BTAComponentVillageField(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
	{
		super(par1ComponentVillageStartPiece, par2);
		this.coordBaseMode = par5;
		this.isGreenhouse = par3Random.nextInt(5) == 0;
		this.isCovered = par3Random.nextInt(2) == 0;
		this.boundingBox = par4StructureBoundingBox;
	}

	/**
	 * Returns a crop type to be planted on this field.
	 */
	private int getRandomCrop(Random par1Random)
	{
		switch (par1Random.nextInt(5))
		{
		case 0:
			return Block.carrot.blockID;

		case 1:
			return Block.potato.blockID;

		default:
			return FCBetterThanWolves.fcBlockWheatCrop.blockID;
		}
	}

	public static BTAComponentVillageField func_74900_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 17, 4, 13, par6);
		return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new BTAComponentVillageField(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
	 * the end, it adds Fences...
	 */
	public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
	{
		this.cropPrimary = this.startPiece.GetPrimaryCropBlockID(par1World);
		this.cropSecondary = this.startPiece.GetSecondaryCropBlockID(par1World);

		if (this.averageGroundLevel < 0)
		{
			this.averageGroundLevel = this.getAverageGroundLevel(par1World, par3StructureBoundingBox);

			if (this.averageGroundLevel < 0)
			{
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
		}

		if (this.isGreenhouse) {
			//Foundation
			this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 3, 13, 0, 9, Block.dirt.blockID, Block.dirt.blockID, false);

			for (int i = 3; i <= 13; i++)
			{
				for (int j = 3; j <= 9; j++)
				{
					this.fillCurrentPositionBlocksDownwards(par1World, Block.dirt.blockID, 0, i, -1, j, par3StructureBoundingBox);
				}
			}

			for (int i = 2; i <= 14; i++)
			{
				for (int j = 2; j <= 10; j++)
				{
					this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, i, -1, j, par3StructureBoundingBox);
				}
			}

			//Clears space above
			for (int i = 1; i <= 15; i++)
			{
				for (int j = 1; j <= 11; j++)
				{
					this.clearCurrentPositionBlocksUpwards(par1World, i, 1, j, par3StructureBoundingBox);
				}
			}

			//Log pillars
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 4, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 4, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, 4, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, 4, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 4, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, 4, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, 5, 2, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 4, 11, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 4, 11, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, 4, 11, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, 4, 11, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 5, 10, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, 4, 10, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, 4, 10, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, 5, 10, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, 4, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, 8, 6, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, 7, 6, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, 4, 10, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 15, 4, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 15, 8, 6, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, 7, 6, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 15, 4, 10, par3StructureBoundingBox);

			//Base supports
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 6, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 10, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 16, 0, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 16, 1, 2, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 16, 0, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 16, 1, 6, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 16, 0, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 16, 1, 10, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 2, 1, 0, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 1, 0, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 1, 0, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 14, 1, 0, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 2, 1, 12, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 1, 12, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 1, 12, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 14, 1, 12, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 1, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 1, 11, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 0, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 15, 1, 11, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 0, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 15, 1, 1, par3StructureBoundingBox);

			//Log cross beams
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 2, 13, 5, 2, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 8, 6, 14, 8, 6, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 10, 13, 5, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);

			//Lower trim
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 0, 2, 5, 0, 2, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 0, 2, 9, 0, 2, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 0, 2, 13, 0, 2, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 0, 10, 5, 0, 10, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 0, 10, 9, 0, 10, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 0, 10, 13, 0, 10, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 3, 2, 0, 5, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 7, 2, 0, 9, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 0, 3, 14, 0, 5, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 0, 7, 14, 0, 9, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);

			//Lower trim stairs
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 0, 1, 5, 0, 1, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4);
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 1, 13, 0, 1, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4);

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 0, 11, 5, 0, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 0, 11, 9, 0, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 11, 13, 0, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 1, 0, 3, 1, 0, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 1, 0, 7, 1, 0, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 15, 0, 3, 15, 0, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 15, 0, 7, 15, 0, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);

			//Leaf trim
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 1, 5, 1, 1, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 1, 1, 13, 1, 1, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 11, 5, 1, 11, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 11, 9, 1, 11, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 1, 11, 13, 1, 11, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 1, 3, 1, 1, 5, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 1, 7, 1, 1, 9, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 15, 1, 3, 15, 1, 5, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 15, 1, 7, 15, 1, 9, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
			
			//Side roofs
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 4, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 3, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), 3, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 5, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 5, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 8, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 7, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), 7, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 9, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 9, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 12, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 11, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), 11, 5, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 13, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 13, 5, 0, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 4, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 3, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), 3, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 5, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 5, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 8, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 7, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), 7, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 9, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 9, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 12, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 11, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), 11, 5, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 13, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 13, 5, 12, par3StructureBoundingBox);

			//Wall arches
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 5, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 7, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 9, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 7, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 9, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 11, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 13, 4, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 1, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 2, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 6, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 10, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 14, 4, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 15, 4, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 3, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 5, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 7, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 9, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 11, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 13, 4, 11, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 1, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 2, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 6, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 14, 4, 12, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 15, 4, 11, par3StructureBoundingBox);

			//Wall arch trapdoors
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 4, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 2, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 8, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 8, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 1, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 1, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 12, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 11, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 11, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 13, 3, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 13, 2, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 4, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 3, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 2, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 3, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 5, 2, 11, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 8, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 3, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 7, 2, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 3, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 2, 11, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, 12, 4, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 11, 3, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 11, 2, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 13, 3, 11, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 13, 2, 11, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 3, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 4, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 2, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 3, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 4, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 5, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 6, 5, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 2, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 3, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 1, 4, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 2, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 3, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 4, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 5, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 1, 6, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 3, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 4, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 2, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 3, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 4, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 5, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 6, 5, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 2, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 3, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 15, 4, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 2, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 3, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 4, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 5, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 15, 6, 7, par3StructureBoundingBox);

			//Roof
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 15, 5, 1, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 6, 2, 15, 6, 2, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 5, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 6, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 1, 5, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 6, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 6, 5, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 6, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 5, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 5, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 15, 6, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 15, 5, 3, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 6, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 1, 6, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 6, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 6, 6, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 6, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 6, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 6, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 15, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 15, 6, 4, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 7, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 8, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 7, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 7, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 8, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 6, 7, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 7, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 8, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 7, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 7, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 15, 8, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 15, 7, 5, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 8, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 8, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 8, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 8, 5, par3StructureBoundingBox);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 11, 15, 5, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 6, 10, 15, 6, 10, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 5, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 6, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 1, 5, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 5, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 6, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 6, 5, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 5, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 6, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 10, 5, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 5, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 15, 6, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 15, 5, 9, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 6, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 1, 6, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 6, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 6, 6, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 6, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 10, 6, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 6, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 15, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 15, 6, 8, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 8, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 8, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 6, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 8, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 10, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 15, 8, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 15, 7, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 8, 7, par3StructureBoundingBox);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 9, 5, 1, 9, 7, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 9, 5, 6, 9, 7, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 6, 7, 6, par3StructureBoundingBox);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 9, 5, 10, 9, 7, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 10, 7, 6, par3StructureBoundingBox);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 15, 9, 5, 15, 9, 7, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 9, 6, 15, 9, 6, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 7, 6, 15, 7, 6, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8);

			//Glass
			if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 1, 2, 14, 4, 2, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 6, 3, 14, 6, 3, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 7, 4, 14, 7, 4, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 8, 5, 14, 8, 5, Block.glass.blockID, 0);

				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 1, 10, 14, 4, 10, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 6, 9, 14, 6, 9, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 7, 8, 14, 7, 8, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 8, 7, 14, 8, 7, Block.glass.blockID, 0);

				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 1, 3, 2, 6, 9, Block.glass.blockID, 0);
				this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 14, 1, 3, 14, 6, 9, Block.glass.blockID, 0);

				this.placeBlockAtCurrentPosition(par1World, Block.glass.blockID, 0, 2, 7, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.glass.blockID, 0, 2, 7, 7, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.glass.blockID, 0, 14, 7, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.glass.blockID, 0, 14, 7, 7, par3StructureBoundingBox);
			}

			//Front door
			this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 1, 2, 9, 4, 2, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
			if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
				this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 8, 1, 2, 3, (BlockDoor) BTADecoIntegration.doorSpruce);
			}
			else {
				this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 1, 2, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 2, 2, par3StructureBoundingBox);
			}

			//Crops
			if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 3, 13, 0, 9, FCBetterThanWolves.fcBlockFarmland.blockID, FCBetterThanWolves.fcBlockFarmland.blockID, false);

				this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 3, 6, 0, 9, Block.waterMoving.blockID, 0, false);
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 3, 10, 0, 9, Block.waterMoving.blockID, 0, false);

				for (int i = 3; i <= 9; i++) {
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 3, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 4, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 5, 1, i, par3StructureBoundingBox);

					this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 7, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 8, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 9, 1, i, par3StructureBoundingBox);

					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 11, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 12, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 13, 1, i, par3StructureBoundingBox);

					if (this.startPiece.GetAbandonmentLevel(par1World) == 1) {
						for (int j = 3; j <= 13; j += 3)
						{
							if (par1World.rand.nextInt(3) != 0)
							{
								this.placeBlockAtCurrentPosition(par1World, 0, 0, i, 1, j, par3StructureBoundingBox);
							}
						}
					}
				}
			}
			else {
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 3, 6, 0, 9, 0, 0, false);
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 3, 10, 0, 9, 0, 0, false);
			}
		}
		else {
			//Foundation
			this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 1, 13, 0, 8, Block.dirt.blockID, Block.dirt.blockID, false);

			for (int i = 3; i <= 13; i++)
			{
				for (int j = 2; j <= 8; j++)
				{
					this.fillCurrentPositionBlocksDownwards(par1World, Block.dirt.blockID, 0, i, -1, j, par3StructureBoundingBox);
				}
			}

			for (int i = 2; i <= 14; i++)
			{
				for (int j = 1; j <= 9; j++)
				{
					this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, i, -1, j, par3StructureBoundingBox);
				}
			}

			//Clears space above
			for (int i = 1; i <= 15; i++)
			{
				for (int j = 0; j <= 10; j++)
				{
					this.clearCurrentPositionBlocksUpwards(par1World, i, 1, j, par3StructureBoundingBox);
				}
			}

			//Log pillars
			int height = this.isCovered ? 3 : 1;

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, height, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, height, 5, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 2, height, 9, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, height, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, height, 5, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 14, height, 9, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, height, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, height, 1, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 6, height, 9, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, height, 9, par3StructureBoundingBox);

			//Base supports
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 5, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 1, 9, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 0, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 15, 1, 1, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 0, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 15, 1, 5, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 15, 0, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 15, 1, 9, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 2, 1, 0, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 1, 0, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 1, 0, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 0, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 14, 1, 0, par3StructureBoundingBox);

			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 2, 0, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 2, 1, 10, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 1, 10, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 1, 10, par3StructureBoundingBox);
			this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 10, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 14, 1, 10, par3StructureBoundingBox);

			//Lower trim
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 0, 1, 5, 0, 1, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 0, 1, 9, 0, 1, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 0, 1, 13, 0, 1, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 0, 9, 5, 0, 9, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 0, 9, 9, 0, 9, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 0, 9, 13, 0, 9, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 2, 2, 0, 4, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 6, 2, 0, 8, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 0, 2, 14, 0, 4, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 0, 6, 14, 0, 8, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);

			//Lower trim stairs
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 0, 0, 5, 0, 0, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 0, 0, 9, 0, 0, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 0, 13, 0, 0, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3));

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 0, 10, 5, 0, 10, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 0, 10, 9, 0, 10, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 10, 13, 0, 10, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2));

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 1, 0, 2, 1, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 1, 0, 6, 1, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0));

			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 15, 0, 2, 15, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1));
			this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 15, 0, 6, 15, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1));

			//Hedge/fence
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 1, 5, 1, 1, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 1, 9, 1, 1, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 1, 1, 13, 1, 1, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 9, 5, 1, 9, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 9, 9, 1, 9, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 1, 9, 13, 1, 9, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 2, 2, 1, 4, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 6, 2, 1, 8, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 1, 2, 14, 1, 4, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 1, 6, 14, 1, 8, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

			if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.gateSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.gateSpruce.blockID, 0), 8, 1, 1, par3StructureBoundingBox);
			}
			else {
				this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 1, 1, par3StructureBoundingBox);
			}

			//Top frame
			if (!this.isCovered) {
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 2, 2, 1, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 2, 2, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 2, 2, 9, par3StructureBoundingBox);

				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 14, 2, 1, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 14, 2, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 14, 2, 9, par3StructureBoundingBox);

				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 6, 2, 1, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 10, 2, 1, par3StructureBoundingBox);

				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 6, 2, 9, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 10, 2, 9, par3StructureBoundingBox);
			}
			else {
				this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 1, 14, 4, 9, BTADecoIntegration.pergola.blockID, 0, BTADecoIntegration.pergola.blockID, 14, false);
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 4, 3, 12, 4, 7, 0, 0, false);
				this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 0, 14, 4, 0, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), false);
				this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 10, 14, 4, 10, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0), false);
				this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 1, 4, 9, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), false);
				this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 15, 4, 1, 15, 4, 9, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), false);

				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 2, 4, 1, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 2, 4, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 2, 4, 9, par3StructureBoundingBox);

				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 14, 4, 1, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 14, 4, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 14, 4, 9, par3StructureBoundingBox);

				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 6, 4, 1, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 10, 4, 1, par3StructureBoundingBox);

				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 6, 4, 9, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 10, 4, 9, par3StructureBoundingBox);
			}

			//Crops
			if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 2, 13, 0, 8, FCBetterThanWolves.fcBlockFarmland.blockID, FCBetterThanWolves.fcBlockFarmland.blockID, false);

				this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 2, 6, 0, 8, Block.waterMoving.blockID, 0, false);
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 2, 10, 0, 8, Block.waterMoving.blockID, 0, false);

				for (int i = 2; i <= 8; i++) {
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 3, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 4, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 5, 1, i, par3StructureBoundingBox);

					this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 7, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 8, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 9, 1, i, par3StructureBoundingBox);

					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 11, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 12, 1, i, par3StructureBoundingBox);
					this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), 13, 1, i, par3StructureBoundingBox);

					if (this.startPiece.GetAbandonmentLevel(par1World) == 1) {
						for (int j = 3; j <= 13; j += 3)
						{
							if (par1World.rand.nextInt(3) != 0)
							{
								this.placeBlockAtCurrentPosition(par1World, 0, 0, i, 1, j, par3StructureBoundingBox);
							}
						}
					}
				}
			}
			else {
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 2, 6, 0, 8, 0, 0, false);
				this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 2, 10, 0, 8, 0, 0, false);
			}

			if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 6, 1, 2, par3StructureBoundingBox);
			if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 6, 1, 5, par3StructureBoundingBox);
			if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 6, 1, 8, par3StructureBoundingBox);

			if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 10, 1, 2, par3StructureBoundingBox);
			if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 10, 1, 5, par3StructureBoundingBox);
			if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
				this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), 10, 1, 8, par3StructureBoundingBox);
		}

		return true;
	}
}
