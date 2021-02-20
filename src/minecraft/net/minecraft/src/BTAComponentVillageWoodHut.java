package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageWoodHut extends BTAComponentVillage
{
	private int averageGroundLevel = -1;
	private final int tablePosition;

	public BTAComponentVillageWoodHut(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
	{
		super(par1ComponentVillageStartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4StructureBoundingBox;
		this.tablePosition = par3Random.nextInt(2);
	}

	public static BTAComponentVillageWoodHut func_74908_a(BTAComponentVillageStartPiece startPiece, List boundingBoxList, Random rand, int x, int y, int z, int par6, int par7)
	{
		StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 7, 7, 7, par6);
		return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(boundingBoxList, var8) == null ? new BTAComponentVillageWoodHut(startPiece, par7, rand, var8, par6) : null;
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

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6, 0);
		}
		
        //Clears space above
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, 1, i, par3StructureBoundingBox);
            }
        }
		
		//Generates basic box
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 5, 0, 5, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 5, 3, 5, BTADecoIntegration.barkLogStripped.blockID, BTADecoIntegration.barkLogStripped.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 2, 4, 6, 4, 0, 0, false);

		//Generates log supports in corners
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 1, 3, 1, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 5, 1, 3, 5, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 5, 5, 3, 5, Block.wood.blockID, Block.wood.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 1, 5, 3, 1, Block.wood.blockID, Block.wood.blockID, false);

		//Generates log crossbeams
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 2, 1, 4, 4, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 4, 2, 5, 4, 4, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 8), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 1, 4, 4, 1, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 5, 4, 4, 5, Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), Block.wood.blockID, this.getMetadataWithOffset(Block.wood.blockID, 4), false);

		//Base supports
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 0, 1, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, 0, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 6, 1, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 6, 1, 5, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 5, 0, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 1, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 5, 1, 6, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.strippedLog.blockID, 1, 5, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 1, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 5, 1, 0, par3StructureBoundingBox);

		//Spruce stair trim
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 0, 2, 0, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 0, 2, 6, 0, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 6, 4, 0, 6, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 1, 2, 0, 1, 4, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 1, 2, 6, 1, 4, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 6, 4, 1, 6, Block.leaves.blockID, 12, Block.leaves.blockID, 12, false);
		
		//Trapdoor rim
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 3, 5, BTADecoIntegration.trapdoorCherry.blockID, 9, BTADecoIntegration.trapdoorCherry.blockID, 9, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 3, 1, 6, 3, 5, BTADecoIntegration.trapdoorCherry.blockID, 9, BTADecoIntegration.trapdoorCherry.blockID, 9, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 3, 6, 5, 3, 6, BTADecoIntegration.trapdoorCherry.blockID, 9, BTADecoIntegration.trapdoorCherry.blockID, 9, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 3, 0, 5, 3, 0, BTADecoIntegration.trapdoorCherry.blockID, 9, BTADecoIntegration.trapdoorCherry.blockID, 9, false);

		//Front facade
		//Door
		if (this.startPiece.GetAbandonmentLevel(par1World) <= 1) {
			this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 3, 1, 1, 3, (BlockDoor) BTADecoIntegration.doorSpruce);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 1, 1, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 3, 2, 1, par3StructureBoundingBox);
		}
		//Steps
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 2, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 3, 0, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 4, 0, 0, par3StructureBoundingBox);
		//Awning
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 1, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 2, 2, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 4, 1, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 14, 4, 2, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 2, 3, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 3, 3, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.pergola.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 4, 3, 0, par3StructureBoundingBox);

		//Roof
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 2, 2, 4, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 4, 2, 4, 4, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, false);

		/*Face -Z*/ this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 2, 5, 5, 2, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), false);
		/*Face +Z*/ this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 4, 5, 5, 4, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), false);
		/*Face -X*/ this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 5, 1, 2, 5, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), false);
		/*Face +X*/ this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 5, 1, 4, 5, 5, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), false);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 0, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 1, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 0, 5, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 0, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 5, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3), 6, 5, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 6, 4, 2, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 3, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 3, 6, -1, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 0, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 1, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 0, 5, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 0, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 5, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2), 6, 5, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 3) + 4, 6, 4, 4, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 3, 5, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 2) + 4, 3, 6, 7, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 2, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 2, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 1, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0), 2, 5, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 2, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 0, 5, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, -1, 6, 3, par3StructureBoundingBox);

		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 5, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 5, 4, 1, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 4, 5, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 4, 4, 0, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 5, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodSpruce.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 5, 4, 5, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1), 4, 5, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 0) + 4, 4, 4, 6, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 6, 5, 3, par3StructureBoundingBox);
		this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.cherryStairs.blockID, this.getMetadataWithOffset(Block.stairsWoodSpruce.blockID, 1) + 4, 7, 6, 3, par3StructureBoundingBox);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 5, 3, 5, 5, 3, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 5, 1, 3, 5, 5, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 6, 3, 6, 6, 3, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 3, 6, 0, 3, 6, 6, Block.planks.blockID, 5, Block.planks.blockID, 5, false);
		
		//Windows
		if (this.startPiece.GetAbandonmentLevel(par1World) == 0) {
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 1, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.glassStained.blockID, 3, 5, 2, 3, par3StructureBoundingBox);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 1, 2, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 2, 3, par3StructureBoundingBox);
		}
		
		//Inside
		if (this.tablePosition == 0) {
			this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 4, 1, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 2, 4, 1, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2), 2, 1, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2) + 8, 2, 1, 4, par3StructureBoundingBox);
		}
		else {
			this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 15, 2, 1, 4, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, BTADecoIntegration.oakWoodChair.blockID, this.getMetadataWithOffset(BTADecoIntegration.trapdoorCherry.blockID, 0) + 2, 2, 1, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2), 4, 1, 3, par3StructureBoundingBox);
			this.placeBlockAtCurrentPosition(par1World, Block.bed.blockID, this.getMetadataWithOffset(Block.bed.blockID, 2) + 8, 4, 1, 4, par3StructureBoundingBox);
		}

		//Fills downward
        for (int i = 1; i < 6; i++)
        {
            for (int j = 1; j < 6; j++)
            {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.planks.blockID, 1, j, -1, i, par3StructureBoundingBox);
            }
        }
        
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, -1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 0, -1, 5, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, -1, 1, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 6, -1, 5, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, -1, 6, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 5, -1, 6, par3StructureBoundingBox);

        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 1, -1, 0, par3StructureBoundingBox);
        this.fillCurrentPositionBlocksDownwards(par1World, BTADecoIntegration.strippedLog.blockID, 1, 5, -1, 0, par3StructureBoundingBox);

		this.spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
		return true;
	}
}
