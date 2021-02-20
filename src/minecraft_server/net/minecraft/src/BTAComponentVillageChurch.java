package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageChurch extends BTAComponentVillage
{
    private int averageGroundLevel = -1;

    public BTAComponentVillageChurch(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
    }

    public static BTAComponentVillageChurch func_74919_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 16, 28, 22, par6);
        return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new BTAComponentVillageChurch(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 28 - 1, 0);
        }
		
        //Clears space above
        for (int i = 2; i <= 10; i++)
        {
            for (int j = 3; j <= 18; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, 1, i, par3StructureBoundingBox);
            }
        }

        //Foundation
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 3, 10, 0, 18, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 0, 4, 9, 0, 17, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 1, 8, 0, 2, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 0, 3, 7, 0, 3, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 0, 13, 13, 0, 17, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 0, 14, 12, 0, 16, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
        
        //Walls
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 4, 10, 5, 18, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 5, 9, 5, 17, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 13, 13, 5, 17, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 14, 12, 5, 16, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 6, 13, 13, 16, 17, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 6, 14, 12, 16, 16, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 2, 8, 3, 3, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 3, 7, 3, 4, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 4, 2, 7, 4, 2, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        
        //Base trim
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 3, 0, 3, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 2, 0, 2, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 0, 3, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 0, 5, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 0, 9, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 0, 13, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 0, 17, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 0, 19, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 2, 0, 19, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 2, 0, 20, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 3, 0, 19, par3StructureBoundingBox);

		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 0, 3, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 10, 0, 2, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 0, 3, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 0, 5, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 0, 9, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 0, 12, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 14, 0, 13, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 14, 0, 17, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 0, 18, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 0, 19, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 10, 0, 19, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 10, 0, 20, par3StructureBoundingBox);
		this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 0, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 3, 1, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 2, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0), 1, 1, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0), 1, 1, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2), 2, 1, 20, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2), 3, 1, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 9, 1, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 10, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1), 11, 1, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1), 11, 1, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2), 10, 1, 20, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2), 9, 1, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 4, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 4, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 8, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 8, 0, 1, par3StructureBoundingBox);

        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 1, 0, 3, 1, 0, 18, BTADecoIntegration.stoneSlab2.blockID, 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 3, 11, 0, 18, BTADecoIntegration.stoneSlab2.blockID, 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 2, 0, 2, 10, 0, 2, BTADecoIntegration.stoneSlab2.blockID, 4);
        
        //Wall braces
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 3, 2, 7, 3, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 1, 3, 10, 7, 3, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 19, 2, 7, 19, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 1, 19, 10, 7, 19, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 4, 4, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 1, 8, 4, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 1, 5, 5, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 9, 1, 5, 9, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 13, 1, 5, 13, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 17, 1, 5, 17, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 5, 11, 5, 5, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 9, 11, 5, 9, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 13, 11, 5, 13, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 17, 11, 5, 17, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 1, 12, 13, 5, 12, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 14, 1, 13, 14, 5, 13, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 14, 1, 17, 14, 5, 17, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 1, 18, 13, 5, 18, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        
        //Wall arches
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 1, 5, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 1, 5, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 1, 5, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 1, 5, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 1, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 1, 5, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 1, 5, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 1, 5, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 1, 5, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 1, 5, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 11, 5, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 11, 5, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 11, 5, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 11, 5, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 11, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 11, 5, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 12, 5, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 14, 5, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 14, 5, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 12, 5, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 11, 5, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 11, 5, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 5, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 7, 4, 1, par3StructureBoundingBox);
		
		//Roof trim
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 1, 6, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 1, 6, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 11, 6, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 11, 6, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 11, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 11, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 12, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 6, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 14, 6, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 14, 6, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 14, 6, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 14, 6, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 14, 6, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 6, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 12, 6, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 6, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0), 4, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0), 4, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1), 8, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1), 8, 4, 3, par3StructureBoundingBox);
		
		//Roof face front
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 6, 4, 9, 6, 4, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 7, 4, 9, 7, 4, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 8, 4, 8, 9, 4, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 10, 4, 7, 11, 4, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 5, 4, 6, 13, 4, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 5, 4, 7, 5, 4, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 1, 4, 4, 4, 4, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 4, 8, 4, 4, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);

		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 2, 8, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 2, 9, 3, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 3, 6, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3, 7, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 3, 8, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 3, 9, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 3, 10, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 3, 11, 3, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 4, 8, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 4, 9, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 4, 10, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 4, 11, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 4, 12, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 4, 13, 3, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 5, 10, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 11, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 5, 12, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 5, 13, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 5, 14, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 5, 15, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 6, 11, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 6, 12, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 6, 13, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 6, 14, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothMouldingandDecorative.blockID, 12, 6, 15, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 6, 16, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 6, 17, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 10, 8, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 10, 9, 3, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 9, 6, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 9, 7, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 8, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 9, 9, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 9, 10, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 9, 11, 3, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 8, 8, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 8, 9, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 8, 10, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 8, 11, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 8, 12, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 8, 13, 3, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 7, 10, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 7, 11, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 7, 12, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 7, 13, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 7, 14, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 7, 15, 3, par3StructureBoundingBox);
		
		//Roof face back
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 6, 18, 9, 7, 18, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 8, 18, 8, 9, 18, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 10, 18, 7, 11, 18, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 1, 18, 8, 6, 18, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 7, 18, 7, 8, 18, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 9, 18, 6, 14, 18, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);

		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 2, 8, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 2, 9, 19, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 3, 6, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3, 7, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 3, 8, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 3, 9, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 3, 10, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 3, 11, 19, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 4, 8, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 4, 9, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 4, 10, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 4, 11, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 4, 12, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 4, 13, 19, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 5, 10, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 11, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 5, 12, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 5, 13, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 5, 14, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 5, 15, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 6, 11, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 6, 12, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 6, 13, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 6, 14, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothMouldingandDecorative.blockID, 12, 6, 15, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 6, 16, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 6, 17, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 10, 8, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 10, 9, 19, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 9, 6, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 9, 7, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 8, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 9, 9, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 9, 10, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 9, 11, 19, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 8, 8, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 8, 9, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 8, 10, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 8, 11, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 8, 12, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 8, 13, 19, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 7, 10, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 7, 11, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 7, 12, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 7, 13, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 7, 14, 19, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 7, 15, 19, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 2) + 4, 5, 1, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 2) + 4, 6, 1, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 2) + 4, 7, 1, 18, par3StructureBoundingBox);
		
		//Roof face small
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 4, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 4, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 5, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 5, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 5, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 5, 8, 1, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 6, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 6, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 6, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothMouldingandDecorative.blockID, 12, 6, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 6, 9, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 6, 10, 1, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 8, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 8, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 7, 5, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 7, 6, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 7, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 7, 8, 1, par3StructureBoundingBox);
		
		//Roof upper trim
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 13, 4, 6, 13, 18, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 14, 4, 6, 14, 18, Block.cobblestoneWall.blockID, Block.cobblestoneWall.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 6, 14, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 6, 15, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 6, 14, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 6, 15, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 6, 14, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneSlab2.blockID, 4, 6, 15, 15, par3StructureBoundingBox);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 6, 2, 6, 6, 3, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 7, 2, 6, 7, 3, Block.cobblestoneWall.blockID, Block.cobblestoneWall.blockID, false);
		
		//Roof
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 6, 4, 2, 6, 18, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 7, 4, 2, 7, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 8, 4, 2, 8, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 6, 4, 3, 6, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 7, 4, 3, 8, 18, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 9, 4, 3, 9, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 10, 4, 3, 10, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 4, 8, 4, 4, 8, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 4, 9, 4, 4, 10, 18, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 11, 4, 4, 11, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 12, 4, 4, 12, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 5, 10, 4, 5, 10, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 5, 11, 4, 5, 12, 18, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 13, 4, 5, 13, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 6, 4, 10, 6, 12, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 7, 4, 10, 7, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 8, 4, 10, 8, 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 9, 6, 4, 9, 6, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 9, 7, 4, 9, 8, 13, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 9, 4, 9, 9, 12, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 10, 4, 9, 10, 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 8, 4, 8, 8, 13, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 9, 4, 8, 10, 18, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 11, 4, 8, 11, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 12, 4, 8, 12, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 10, 4, 9, 10, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 6, 11, 4, 7, 12, 18, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 13, 4, 7, 13, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 6, 18, 10, 6, 18, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 7, 18, 10, 7, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 8, 18, 10, 8, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 9, 6, 18, 9, 6, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 9, 7, 18, 9, 8, 18, Block.planks.blockID, 1);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 9, 18, 9, 9, 18, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 10, 18, 9, 10, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, false);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 8, 9, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 9, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 8, 9, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 8, 8, 17, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 5, 2, 4, 5, 3, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 5, 2, 6, 5, 3, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 6, 2, 5, 6, 3, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 5, 2, 8, 5, 3, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 2, 7, 5, 3, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 6, 2, 7, 6, 3, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 5, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 7, 4, 3, par3StructureBoundingBox);
		
		//Tower
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 11, 7, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 13, 7, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 14, 7, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 14, 7, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 14, 7, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 11, 7, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 13, 7, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 11, 8, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 13, 8, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 14, 8, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 14, 8, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 14, 8, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 11, 8, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 13, 8, 18, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 9, 12, 13, 9, 12, BTADecoIntegration.stoneSlab2.blockID, 4, BTADecoIntegration.stoneSlab2.blockID, 4, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 9, 13, 14, 9, 17, BTADecoIntegration.stoneSlab2.blockID, 4, BTADecoIntegration.stoneSlab2.blockID, 4, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 9, 18, 13, 9, 18, BTADecoIntegration.stoneSlab2.blockID, 4, BTADecoIntegration.stoneSlab2.blockID, 4, false);
        
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 10, 13, 13, 16, 13, Block.cobblestoneWall.blockID, Block.cobblestoneWall.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 11, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 14, 13, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 10, 17, 13, 16, 17, Block.cobblestoneWall.blockID, Block.cobblestoneWall.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 11, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 14, 17, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 10, 13, 9, 16, 13, Block.cobblestoneWall.blockID, Block.cobblestoneWall.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 11, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 14, 13, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 10, 17, 9, 16, 17, Block.cobblestoneWall.blockID, Block.cobblestoneWall.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 11, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 14, 17, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 11, 11, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 11, 11, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 9, 11, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 13, 11, 15, par3StructureBoundingBox);
		
		//Tower roof
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 16, 12, 12, 16, 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 16, 18, 12, 16, 18, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 8, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 8, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 16, 14, 8, 16, 16, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 16, 14, 14, 16, 16, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 8, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 17, 13, 13, 17, 17, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 10, 17, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 11, 17, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 12, 17, 12, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 10, 17, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 11, 17, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 12, 17, 18, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 8, 17, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 8, 17, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 8, 17, 16, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 14, 17, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 14, 17, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 14, 17, 16, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 17, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 13, 17, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 9, 17, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 13, 17, 17, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 18, 14, 12, 21, 16, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 18, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 18, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 11, 19, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 12, 18, 13, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 18, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 18, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 11, 19, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 12, 18, 17, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 9, 18, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 18, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 9, 19, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 9, 18, 16, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 18, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 13, 18, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 19, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 13, 18, 16, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 20, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 12, 20, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 12, 20, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 20, 16, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 11, 18, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 10, 19, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 12, 19, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 11, 20, 13, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 11, 18, 18, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 10, 19, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 12, 19, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 11, 20, 17, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 8, 18, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 19, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 19, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 9, 20, 15, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 18, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 13, 19, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 13, 19, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 13, 20, 15, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 10, 21, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 12, 21, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 12, 21, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 1, 10, 21, 16, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 11, 22, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 11, 22, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 10, 22, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 12, 22, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 21, 15, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID, 13, 11, 23, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID, 12, 11, 24, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID, 12, 11, 25, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 12, 11, 26, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 11, 27, 15, par3StructureBoundingBox);
		
		//Lower stair trim
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 2, 1, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 2, 1, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0) + 4, 2, 1, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 0) + 4, 2, 1, 18, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 10, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 10, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 10, 1, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 10, 1, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 10, 1, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 10, 1, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 10, 1, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 11, 1, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 12, 1, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 13, 1, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1) + 4, 13, 1, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 13, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 12, 1, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 11, 1, 17, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 1) + 4, 10, 1, 18, par3StructureBoundingBox);
		
		//Windows
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 6, 2, 4, 8, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 2, 5, 7, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 10, 2, 4, 12, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 2, 5, 11, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 14, 2, 4, 16, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 2, 5, 15, par3StructureBoundingBox);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 2, 6, 10, 4, 8, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 10, 5, 7, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 2, 10, 10, 4, 12, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 10, 5, 11, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 2, 14, 13, 4, 16, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 5, 15, par3StructureBoundingBox);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 7, 13, 13, 8, 13, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 7, 13, 13, 8, 17, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 7, 17, 13, 8, 17, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 13, 13, 12, 15, 13, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 12, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 16, 13, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 13, 17, 12, 15, 17, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 12, 13, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 11, 16, 13, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 13, 14, 9, 15, 16, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 12, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 9, 16, 15, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 13, 14, 13, 15, 16, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 12, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 1, 13, 16, 15, par3StructureBoundingBox);
		
		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 7, 2, 4, 7, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 11, 2, 4, 11, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 15, 2, 4, 15, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);

	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 2, 7, 10, 4, 7, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 2, 11, 10, 4, 11, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 2, 15, 13, 4, 15, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);

	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 7, 13, 10, 8, 13, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 7, 13, 12, 8, 13, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 7, 14, 13, 8, 14, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 7, 16, 13, 8, 16, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 7, 17, 10, 8, 17, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 7, 17, 12, 8, 17, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);

	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 13, 13, 11, 15, 13, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 13, 17, 11, 15, 17, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 13, 15, 9, 15, 15, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 13, 15, 13, 15, 15, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);

	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 2, 18, 7, 6, 18, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
	        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 7, 18, 6, 8, 18, BTADecoIntegration.glassStained.blockID, 6, BTADecoIntegration.glassStained.blockID, 6, false);
		}
		else {
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 2, 7, 2, 4, 7, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 2, 11, 2, 4, 11, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 2, 15, 2, 4, 15, 0, 0, false);

	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 2, 7, 10, 4, 7, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 2, 11, 10, 4, 11, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 14, 2, 15, 14, 4, 15, 0, 0, false);

	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 7, 13, 10, 8, 13, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 12, 7, 13, 12, 8, 13, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 7, 14, 13, 8, 14, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 7, 16, 13, 8, 16, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 7, 17, 10, 8, 17, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 12, 7, 17, 12, 8, 17, 0, 0, false);

	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 13, 13, 11, 15, 13, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 13, 17, 11, 15, 17, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 13, 15, 9, 15, 15, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 13, 15, 13, 15, 15, 0, 0, false);

	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 18, 7, 6, 18, 0, 0, false);
	        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 7, 18, 6, 8, 18, 0, 0, false);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 5, 2, 18, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 5, 5, 18, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 5, 6, 18, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 7, 2, 18, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 7, 3, 18, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 7, 6, 18, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 6, 6, 8, 18, par3StructureBoundingBox);
		}
        
		//Entrance
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 5, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 6, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3), 7, 0, 0, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 6, 3, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 4, 1, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 4, 2, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneWall.blockID, 0, 8, 1, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherrySidingAndCorner.blockID, 14, 8, 2, 0, par3StructureBoundingBox);
		
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 6, 1, 2, 0, (BlockDoor) BTADecoIntegration.doorSpruce);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 6, 1, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 6, 2, 2, par3StructureBoundingBox);
		}

		this.placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 13, 6, 4, 4, par3StructureBoundingBox);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 1, 4, 5, 3, 4, FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner.blockID, 14, FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner.blockID, 14, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 1, 4, 7, 3, 4, FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner.blockID, 14, FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner.blockID, 14, false);
		
		//Inside
        this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 3, 1, 7, 9, 1, 13, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 12);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 6, 9, 1, 6, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 8, 9, 1, 8, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 10, 9, 1, 10, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 12, 9, 1, 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 6, 6, 1, 13, 0, 0, false);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 1, 17, 8, 1, 17, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 0), 4, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 3), 5, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 6, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 2, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 3), 7, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.andesiteSmoothStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.andesiteSmoothStairs.blockID, 1), 8, 1, 16, par3StructureBoundingBox);
        
		//Inner wall braces
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 5, 3, 7, 5, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 9, 3, 7, 9, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 13, 3, 7, 13, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 1, 17, 3, 7, 17, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);

        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 1, 5, 9, 7, 5, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 1, 9, 9, 7, 9, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 1, 13, 9, 7, 13, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 1, 17, 9, 7, 17, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
        
        //Spiral staircase
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 2) + 4, 9, 5, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsStoneBrick.blockID, this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4, 9, 5, 16, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 1, 15, 11, 16, 15, Block.wood.blockID, 0, false);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 11, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 12, 1, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 12, 1, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 12, 1, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 12, 2, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 12, 2, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 11, 2, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 11, 3, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 10, 3, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 3, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 4, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 4, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 11, 4, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 11, 5, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 12, 5, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 5, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 12, 6, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 6, 14, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 11, 6, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 11, 7, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 10, 7, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 7, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 8, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 8, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 11, 8, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 11, 9, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 12, 9, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 9, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 12, 10, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 10, 14, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 11, 10, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 11, 11, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 10, 11, 14, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 11, 15, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 10, 11, 16, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 11, 12, 16, par3StructureBoundingBox);
		
        this.spawnVillagers(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
        return true;
    }

    /**
     * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
     */
    protected int getVillagerType(int par1)
    {
        return 2;
    }
}
