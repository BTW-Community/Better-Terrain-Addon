package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentVillageTorch extends BTAComponentVillage
{
    private int averageGroundLevel = -1;

    public BTAComponentVillageTorch(BTAComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
    }

    public static StructureBoundingBox func_74904_a(BTAComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 3, 4, 2, par6);
        return StructureComponent.findIntersecting(par1List, var7) != null ? null : var7;
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

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 2, 3, 1, 0, 0, false);
        this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 0, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 1, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 2, 0, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 1, 3, 0, par3StructureBoundingBox);

        if (this.startPiece.GetAbandonmentLevel(par1World) <= 1)
        {
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 0, 3, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 1, 3, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 2, 3, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8, 1, 3, -1, par3StructureBoundingBox);
        }

        return true;
    }
}
