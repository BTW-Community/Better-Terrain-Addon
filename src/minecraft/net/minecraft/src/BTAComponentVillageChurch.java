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
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 5, 12, 9, par6);
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

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 12 - 1, 0);
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 3, 7, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 3, 9, 3, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 3, 0, 8, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 0, 3, 10, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 10, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 4, 10, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 4, 0, 4, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 4, 4, 4, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 8, 3, 4, 8, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 4, 3, 10, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 5, 3, 5, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 9, 0, 4, 9, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, 4, 4, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 0, 11, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 11, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 2, 11, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 2, 11, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 1, 6, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 1, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 2, 1, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 3, 1, 6, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 3, 1, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 1, 1, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 1, 6, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 3, 1, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 1), 1, 2, 7, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 0), 3, 2, 7, par3StructureBoundingBox);
        int var4 = this.startPiece.GetAbandonmentLevel(par1World);

        if (var4 == 0)
        {
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 2, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 6, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 7, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 6, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 7, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 6, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 7, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 6, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 7, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 3, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 4, 3, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 2, 3, 8, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 2, 4, 7, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 1, 4, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 3, 4, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 2, 4, 5, par3StructureBoundingBox);
        }
        else
        {
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 0, 2, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 0, 3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 2, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 0, 6, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 0, 7, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 6, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 7, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 6, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 7, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 6, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 7, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 0, 3, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 4, 3, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 3, 8, par3StructureBoundingBox);
        }

        int var5 = this.getMetadataWithOffset(Block.ladder.blockID, 4);
        int var6;
        int var7;

        for (var6 = 1; var6 <= 9; ++var6)
        {
            var7 = FCBetterThanWolves.fcBlockLadder.SetFacing(0, var5);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockLadder.blockID, var7, 3, var6, 3, par3StructureBoundingBox);
        }

        this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 1, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, 2, 2, 0, par3StructureBoundingBox);

        if (var4 == 0)
        {
            this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 2, 1, 0, this.getMetadataWithOffset(FCBetterThanWolves.fcBlockDoorWood.blockID, 1));
        }

        if (this.getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && this.getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0)
        {
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), 2, 0, -1, par3StructureBoundingBox);
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            for (var7 = 0; var7 < 5; ++var7)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, var7, 12, var6, par3StructureBoundingBox);
                this.fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, var7, -1, var6, par3StructureBoundingBox);
            }
        }

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
