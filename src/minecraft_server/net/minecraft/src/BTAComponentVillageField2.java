package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageField2 extends BTAComponentVillage
{
    private int averageGroundLevel = -1;

    private int cropPrimary;
    private int cropSecondary;

	private boolean isCovered;

    public BTAComponentVillageField2(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
		this.isCovered = par3Random.nextInt(2) == 0;
    }

    /**
     * Returns a crop type to be planted on this field.
     */
    private int pickRandomCrop(Random par1Random)
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

    public static BTAComponentVillageField2 func_74902_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 11, 5, 9, par6);
        return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new BTAComponentVillageField2(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 5 - 1, 0);
		}
        
		//Foundation
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 2, 8, 0, 6, Block.dirt.blockID, Block.dirt.blockID, false);

		for (int i = 2; i <= 8; i++)
		{
			for (int j = 2; j <= 6; j++)
			{
				this.fillCurrentPositionBlocksDownwards(par1World, Block.dirt.blockID, 0, i, -1, j, par3StructureBoundingBox);
			}
		}

		for (int i = 1; i <= 9; i++)
		{
			for (int j = 1; j <= 7; j++)
			{
				this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, i, -1, j, par3StructureBoundingBox);
			}
		}

		//Clears space above
		for (int i = 1; i <= 9; i++)
		{
			for (int j = 0; j <= 7; j++)
			{
				this.clearCurrentPositionBlocksUpwards(par1World, i, 1, j, par3StructureBoundingBox);
			}
		}
		
		//Log pillars
		int height = this.isCovered ? 3 : 1;

		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, height, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 3, height, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 7, height, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 9, height, 1, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, height, 7, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 5, height, 7, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 9, height, 7, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, height, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, height, 4, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, height, 7, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 9, height, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 9, height, 4, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 9, height, 7, par3StructureBoundingBox);
		
		//Base supports
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 1, 0, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 3, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 3, 1, 0, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 7, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 7, 1, 0, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 9, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 9, 1, 0, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 1, 8, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 5, 0, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 5, 1, 8, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 9, 0, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 9, 1, 8, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 4, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 7, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 10, 1, 1, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 10, 1, 4, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 10, 1, 7, par3StructureBoundingBox);

		//Lower trim
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 1, 2, 0, 1, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 0, 1, 6, 0, 1, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 0, 1, 8, 0, 1, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 7, 4, 0, 7, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 0, 7, 8, 0, 7, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 5), false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 0, 2, 1, 0, 3, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 0, 5, 1, 0, 6, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 0, 2, 9, 0, 3, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 0, 5, 9, 0, 6, BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), BTADecoIntegration.strippedLog.blockID, this.getMetadataWithOffset(BTADecoIntegration.strippedLog.blockID, 9), false);
		
		//Lower trim stairs
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 0, 2, 0, 0, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 4, 0, 0, 6, 0, 0, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 0, 0, 8, 0, 0, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3));

		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 8, 4, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 6, 0, 8, 8, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2));

		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 0, 0, 2, 0, 0, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 0, 0, 5, 0, 0, 3, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0));

		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 10, 0, 2, 10, 0, 3, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1));
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 10, 0, 5, 10, 0, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1));
		
		//Hedge/fence
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 1, 2, 1, 1, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 6, 1, 1, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 1, 8, 1, 1, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 7, 4, 1, 7, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 1, 7, 8, 1, 7, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 1, 2, 1, 1, 3, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 1, 1, 6, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 1, 2, 9, 1, 3, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 1, 5, 9, 1, 6, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, BTADecoIntegration.hedgeOakSidingAndCorner.blockID, 14, false);

		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.gateSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.gateSpruce.blockID, 0), 5, 1, 1, par3StructureBoundingBox);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 1, 1, par3StructureBoundingBox);
		}
		
		if (!this.isCovered) {
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 3, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 7, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 2, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 2, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 5, 2, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 2, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 1, 2, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 2, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 2, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 2, 7, par3StructureBoundingBox);
		}
		else {
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 9, 4, 7, BTADecoIntegration.pergola.blockID, 0, BTADecoIntegration.pergola.blockID, 14, false);
			this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 4, 3, 7, 4, 5, 0, 0, false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 0, 9, 4, 0, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 8, 9, 4, 8, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 1, 0, 4, 7, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), false);
			this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 4, 1, 10, 4, 7, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3), false);
			
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 1, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 3, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 7, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 9, 4, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 1, 4, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 5, 4, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 9, 4, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 1, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 1, 4, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 1, 4, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 9, 4, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 9, 4, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barkLog.blockID, 0, 9, 4, 7, par3StructureBoundingBox);
		}
		
		//Crops

		//Crops
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 2, 8, 0, 6, FCBetterThanWolves.fcBlockFarmland.blockID, FCBetterThanWolves.fcBlockFarmland.blockID, false);

			this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 4, 8, 0, 4, Block.waterMoving.blockID, 0, false);

			for (int i = 2; i <= 8; i++) {
				this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), i, 1, 2, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, this.cropPrimary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), i, 1, 3, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), i, 1, 5, par3StructureBoundingBox);
				this.placeBlockAtCurrentPosition(par1World, this.cropSecondary, MathHelper.getRandomIntegerInRange(par2Random, 2, 5), i, 1, 6, par3StructureBoundingBox);

				if (this.startPiece.GetAbandonmentLevel(par1World) == 1) {
					for (int j = 2; j <= 6; j += 3)
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
			this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 4, 8, 0, 4, 0, 0, false);
		}

		if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), 2, 1, 4, par3StructureBoundingBox);
		if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), 5, 1, 4, par3StructureBoundingBox);
		if (par2Random.nextInt(this.startPiece.GetAbandonmentLevel(par1World) + 2) <= 1)
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1), 8, 1, 4, par3StructureBoundingBox);

        return true;
    }
}
