package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageBlacksmith extends BTAComponentVillage
{
    /** List of items that Village's Blacksmith chest can contain. */
    private static final WeightedRandomChestContent[] villageBlacksmithChestContents = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 10), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.pickaxeIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.swordIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.helmetIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.legsIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bootsIron.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Block.obsidian.blockID, 0, 3, 7, 5), new WeightedRandomChestContent(Block.sapling.blockID, 0, 3, 7, 5)};
    private int averageGroundLevel = -1;

    public BTAComponentVillageBlacksmith(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
    }

    public static BTAComponentVillageBlacksmith func_74915_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 15, 15, 13, par6);
        return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new BTAComponentVillageBlacksmith(par0ComponentVillageStartPiece, par7, par2Random, var8, par6) : null;
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

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 15 - 1, 0);
        }
		
        //Clears space above
        for (int i = 0; i <= 14; i++)
        {
            for (int j = 0; j <= 11; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, i, 0, j, par3StructureBoundingBox);
            }
        }
        
        //Foundation
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, -1, 1, 7, -1, 9, BTADecoIntegration.stoneTypesSmooth.blockID, 1, BTADecoIntegration.stoneTypesSmooth.blockID, 1, false);
		
        for (int i = 2; i <= 6; i++) {
        	for (int j = 0; j <= 8; j++) {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, i, -1, j, par3StructureBoundingBox);
                
                if (par2Random.nextInt(2) == 0) {
            		this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, i, -1, j, par3StructureBoundingBox);
                }
                else {
            		this.placeBlockAtCurrentPosition(par1World, Block.gravel.blockID, 0, i, -1, j, par3StructureBoundingBox);
                }
        	}
        }
        
        for (int i = 8; i <= 12; i++) {
        	for (int j = 2; j <= 8; j++) {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, i, 0, j, par3StructureBoundingBox);
        	}
        }
        
        //Log pillars
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, 3, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 7, 4, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 13, 4, 1, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, 3, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 7, 4, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 13, 4, 5, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 1, 3, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 4, 4, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 7, 4, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 10, 4, 9, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, 13, 4, 9, par3StructureBoundingBox);
        
        //Base supports
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 9, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 14, 1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 14, 1, 5, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 14, 0, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 14, 1, 9, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 1, 0, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 7, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 7, 1, 0, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 13, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 13, 1, 0, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 1, 10, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 4, 0, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 4, 1, 10, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 7, 0, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 7, 1, 10, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 10, 0, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 10, 1, 10, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 13, 0, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 13, 1, 10, par3StructureBoundingBox);
		
		//Log cross beams
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 0, 7, 5, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 5, 0, 13, 5, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 5, 1, 12, 5, 1, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 5, 9, 12, 5, 9, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.placeBlockAtCurrentPosition(par1World, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), 10, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), 10, 5, 10, par3StructureBoundingBox);
		
		//Upper log pillars
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 6, 0, 7, 10, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 6, 0, 10, 10, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 6, 0, 13, 10, 0, Block.wood.blockID, Block.wood.blockID, false);

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 6, 5, 7, 10, 5, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 6, 5, 13, 10, 5, Block.wood.blockID, Block.wood.blockID, false);

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 6, 10, 7, 10, 10, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 6, 10, 10, 10, 10, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 6, 10, 13, 10, 10, Block.wood.blockID, Block.wood.blockID, false);

		//Upper log cross beams
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 10, 1, 7, 10, 4, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 10, 6, 7, 10, 9, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 10, 1, 13, 10, 4, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 10, 6, 13, 10, 9, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 10, 0, 9, 10, 0, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 10, 0, 12, 10, 0, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 10, 10, 9, 10, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 10, 10, 12, 10, 10, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 10, 1, 12, 10, 1, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 10, 9, 12, 10, 9, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		
		//Walls
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 2, 12, 4, 8, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, 3, 11, 4, 7, 0, 0, false);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 6, 1, 13, 9, 9, BTADecoIntegration.barkLogStripped.blockID, 0);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 6, 2, 12, 9, 8, 0, 0, false);
		
		//Lower stair trim
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 7, 0, 2, 7, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4);

		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 13, 0, 2, 13, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 13, 0, 6, 13, 0, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4);

		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 8, 0, 9, 9, 0, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);
		this.fillWithMetadataBlocksIfEmpty(par1World, par3StructureBoundingBox, 11, 0, 9, 12, 0, 9, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4);

		//Leaves
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 1, 2, 13, 1, 4, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 1, 6, 13, 1, 8, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 1, 9, 9, 1, 9, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 1, 9, 12, 1, 9, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

		//Front facade
		//Door
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 10, 1, 3, 2, (BlockDoor) BTADecoIntegration.doorSpruce);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, 1, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, 2, 2, par3StructureBoundingBox);
		}
		//Steps
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 8, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 9, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 11, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 12, 0, 1, par3StructureBoundingBox);
		//Awning
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 9, 1, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 9, 2, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 11, 1, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 11, 2, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 9, 3, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 10, 3, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 11, 3, 1, par3StructureBoundingBox);
		
		//Side door
		//Door
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 8, 1, 7, 1, (BlockDoor) BTADecoIntegration.doorSpruce);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 1, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 2, 7, par3StructureBoundingBox);
		}
		//Steps
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 7, 0, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 7, 0, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 7, 0, 8, par3StructureBoundingBox);
		
		//Wall arches
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 7, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 7, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 7, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 7, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 7, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 7, 4, 10, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 13, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 13, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 13, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 13, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 13, 4, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 13, 4, 10, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 4, 10, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 8, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 12, 4, 1, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 8, 4, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 12, 4, 9, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 8, 9, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 12, 9, 0, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 8, 9, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 12, 9, 10, par3StructureBoundingBox);

		//Wall arch trapdoors
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 7, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 7, 3, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 7, 2, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 7, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 7, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 7, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 7, 1, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 7, 4, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 7, 3, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 7, 2, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 7, 1, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 7, 3, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 7, 2, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 7, 1, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 13, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 13, 3, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 13, 2, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 13, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 13, 2, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 8, 13, 4, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 13, 3, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 13, 2, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 13, 3, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 0) + 12, 13, 2, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 9, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 11, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 3, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 2, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 1, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 3, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 2, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 1, 1, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 9, 9, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 11, 9, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 8, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 7, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 8, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 7, 0, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 9, 4, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 11, 4, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 3, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 2, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 3, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 2, 9, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 9, 9, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 8, 11, 9, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 8, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 12, 7, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 8, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 8, 7, 10, par3StructureBoundingBox);
		
		//Outdoor covering
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 3, 9, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 1, 4, 9, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 1, 2, 4, 9, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 4, 1, 3, 4, 9, Block.woodSingleSlab.blockID, 9, Block.woodSingleSlab.blockID, 9, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 5, 1, 4, 5, 9, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 5, 1, 5, 5, 9, Block.woodSingleSlab.blockID, 9, Block.woodSingleSlab.blockID, 9, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 6, 1, 6, 6, 9, Block.woodSingleSlab.blockID, 1, Block.woodSingleSlab.blockID, 1, false);
		
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 0, 2, 1, 0, 4, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 0), Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 0), false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 6, 1, 0, 8, Block.cobblestoneWall.blockID, 0, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 9, 3, 0, 9, Block.cobblestoneWall.blockID, 0, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 9, 6, 0, 9, Block.cobblestoneWall.blockID, 0, false);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 1, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 2, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 2, 4, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 2, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 1, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 1, 2, 8, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 1, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 2, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 3, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 6, 1, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 6, 2, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 6, 3, 9, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 3, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 1, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 3, 4, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 3, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 1, 3, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 3, 8, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 5, 4, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 6, 4, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 5, 5, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 6, 5, 9, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 0, 3, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 3, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 1, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 2, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 3, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 3, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 4, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 5, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 5, 6, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 6, 6, 0, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 0, 3, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 1, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 2, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 3, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 3, 5, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 4, 5, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 5, 5, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 5, 6, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 6, 6, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 0, 3, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 1, 3, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 1, 4, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 2, 4, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 3, 4, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 3, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 4, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 5, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 0, 5, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 5, 6, 6, 10, par3StructureBoundingBox);
		
		//Forge
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 4, 3, 0, 7, BTADecoIntegration.stoneTypesStoneBrick.blockID, BTADecoIntegration.stoneTypesStoneBrick.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 4, 0, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 4, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 4, 0, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 4, 0, 7, par3StructureBoundingBox);
		
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 0, 3, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 0, 2, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 0, 2, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 0, 3, 1, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteSmoothSidingAndCorner.blockID, 14, 2, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteSmoothSidingAndCorner.blockID, 14, 2, 1, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 4, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 4, 1, 7, par3StructureBoundingBox);
		
		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
			this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 3, 1, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 3, 1, 6, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID, 0, 3, 1, 5, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID, 0, 3, 1, 6, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, Block.anvil.blockID, 0, 6, 0, 2, par3StructureBoundingBox);
		}

		this.placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 2, 2, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.fenceIron.blockID, 0, 2, 2, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 3, 2, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 3, 2, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 0), 2, 2, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 0), 2, 2, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 1) + 4, 3, 2, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 1) + 4, 3, 2, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 3), 3, 3, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 1) + 4, 3, 3, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 1) + 4, 3, 3, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.graniteBrickStairs.blockID, 2), 3, 3, 7, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 3, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesStoneBrick.blockID, 0, 3, 4, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 0, 3, 5, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.stoneTypesSmooth.blockID, 0, 3, 5, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 6, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 6, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 7, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 7, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 8, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 8, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 9, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.graniteBrickSidingAndCorner.blockID, 14, 3, 9, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 2, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 3, 0, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.waterMoving.blockID, 0, 2, 0, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.waterMoving.blockID, 0, 2, 0, 3, par3StructureBoundingBox);
		
		//Upper level details
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 8, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 9, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 11, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 12, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 8, 6, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 9, 6, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 11, 6, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 12, 6, 0, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 8, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 9, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 11, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.woodSingleSlab.blockID, 9, 12, 5, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 8, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 9, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 11, 6, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.leaves.blockID, 12, 12, 6, 10, par3StructureBoundingBox);

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 9, 1, 6, 9, 4, BTADecoIntegration.pergola.blockID, BTADecoIntegration.pergola.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 9, 6, 6, 9, 9, BTADecoIntegration.pergola.blockID, BTADecoIntegration.pergola.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 14, 9, 1, 14, 9, 4, BTADecoIntegration.pergola.blockID, BTADecoIntegration.pergola.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 14, 9, 6, 14, 9, 9, BTADecoIntegration.pergola.blockID, BTADecoIntegration.pergola.blockID, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 6, 1, 14, 6, 4, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 6, 6, 14, 6, 9, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 7, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 8, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 7, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 8, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 7, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 2) + 12, 6, 8, 9, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 7, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 8, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 7, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 8, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 7, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 8, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 7, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 14, 8, 9, par3StructureBoundingBox);
		
		//Roof faces
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 11, 0, 10, 13, 0, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 8, 11, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 9, 11, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 9, 12, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 11, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 11, 11, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 11, 12, 0, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 10, -1, 12, 10, -1, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 8, false);

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 11, 1, 10, 13, 1, Block.wood.blockID, Block.wood.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 8, 11, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 11, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 12, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 12, 11, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 11, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 12, 1, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 11, 10, 10, 13, 10, BTADecoIntegration.strippedLog.blockID, 1, BTADecoIntegration.strippedLog.blockID, 1, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 8, 11, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 9, 11, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 9, 12, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 12, 11, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 11, 11, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.barrelEmpty.blockID, 1, 11, 12, 10, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 10, 11, 12, 10, 11, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 8, false);

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 11, 9, 10, 13, 9, Block.wood.blockID, Block.wood.blockID, false);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 8, 11, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 11, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 12, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 12, 11, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 11, 9, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 11, 12, 9, par3StructureBoundingBox);
		
		//Ceiling
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 11, 2, 8, 11, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 12, 2, 9, 12, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 11, 2, 12, 11, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 12, 2, 11, 12, 8, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 13, 2, 10, 13, 8, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		
		//Roof
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 10, -1, 6, 10, 11, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 7, 10, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 7, 10, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 11, -1, 7, 11, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 7, 11, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 8, 11, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 7, 11, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 8, 11, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 12, -1, 8, 12, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 8, 12, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 9, 12, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 8, 12, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1) + 4, 9, 12, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 13, -1, 9, 13, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 9, 13, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0), 9, 13, 11, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 10, -1, 14, 10, 11, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 13, 10, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 13, 10, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 13, 11, -1, 13, 11, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 13, 11, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 12, 11, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 13, 11, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 12, 11, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 12, 12, -1, 12, 12, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 12, 12, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 11, 12, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 12, 12, 11, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 0) + 4, 11, 12, 11, par3StructureBoundingBox);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 13, -1, 11, 13, 11, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 11, 13, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 1), 11, 13, 11, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 14, -1, 10, 14, 11, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 10, 14, -2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 3) + 4, 10, 13, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 10, 14, 12, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(BTADecoIntegration.cherryStairs.blockID, 2) + 4, 10, 13, 11, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 6, 9, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 6, 9, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 6, 9, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 2) + 8, 6, 9, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 6, 9, 11, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 14, 9, -1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 14, 9, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 14, 9, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorCherry.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 8, 14, 9, 10, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.woodSlab.blockID, 8, 14, 9, 11, par3StructureBoundingBox);
		
		//Windows
		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 8, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 8, 3, 3, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 12, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 12, 3, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 12, 2, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 12, 3, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 9, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 9, 3, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 3, 8, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 9, 7, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 9, 8, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 7, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 8, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 9, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 9, 8, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 11, 8, 9, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 7, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 8, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 8, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 7, 8, 8, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 7, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 8, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 8, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 13, 8, 8, par3StructureBoundingBox);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 8, 3, 3, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, 3, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, 2, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 12, 3, 7, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, 3, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 2, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 3, 8, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, 7, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, 8, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 7, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 8, 1, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, 8, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 7, 9, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 11, 8, 9, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 7, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 8, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 8, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 7, 8, 8, par3StructureBoundingBox);

			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 7, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 8, 2, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 7, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 8, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 7, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 8, 7, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 7, 8, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 13, 8, 8, par3StructureBoundingBox);
		}
		
		//Inside
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 8, 5, 2, 12, 5, 8, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 5, 3, 10, 5, 6, 0, 0, false);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 11, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 2, 11, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 1) + 2, 11, 1, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 1, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 1, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 2, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 9, 3, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 9, 2, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 9, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 9, 2, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 9, 3, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 9, 4, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 9, 5, 2, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 8, 6, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 8, 6, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 8, 6, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 8, 6, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 10, 5, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 10, 5, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 10, 5, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 10, 5, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 9, 5, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 10, 6, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 10, 6, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 3) + 12, 10, 6, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.trapdoorSpruce.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorSpruce.blockID, 1) + 12, 9, 6, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2), 8, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2) + 8, 8, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2), 9, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2) + 8, 9, 6, 8, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 12, 6, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 12, 6, 7, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 12, 6, 8, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 3) + 2, 11, 6, 7, par3StructureBoundingBox);
		
        if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
                this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 9, 1, 3, villageBlacksmithChestContents, 3 + par2Random.nextInt(6));
        }
		
        this.spawnVillagers(par1World, par3StructureBoundingBox, 7, 1, 1, 1);
        return true;
    }

    /**
     * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
     */
    protected int getVillagerType(int par1)
    {
        return 3;
    }
}
