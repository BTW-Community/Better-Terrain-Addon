package betterterrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockDirectional;
import net.minecraft.src.BlockDoor;
import net.minecraft.src.BlockFenceGate;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.BlockTrapDoor;
import net.minecraft.src.Direction;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.FCBlockStairs;
import net.minecraft.src.Facing;
import net.minecraft.src.ItemDoor;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.World;

abstract class BTAStructureComponent extends StructureComponent {
	protected BTAStructureComponent(int par1) {
		super(par1);
	}

    /**
     * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int maxY, int
     * maxZ, int placeBlockId, int placeBlockMetadata, int replaceBlockId, int replaceBlockMetadata, boolean
     * alwaysreplace)
     */
    protected void fillWithMetadataBlocks(World world, StructureBoundingBox structBB, int minY, int minX, int minZ, int maxY, int maxX, int maxZ, int par9, int par10, int par11, int par12, boolean par13)
    {
        for (int i = minX; i <= maxX; ++i)
        {
            for (int j = minY; j <= maxY; ++j)
            {
                for (int k = minZ; k <= maxZ; ++k)
                {
                    if (!par13 || this.getBlockIdAtCurrentPosition(world, j, i, k, structBB) != 0)
                    {
                        if (i != minX && i != maxX && j != minY && j != maxY && k != minZ && k != maxZ)
                        {
                            this.placeBlockAtCurrentPosition(world, par11, par12, j, i, k, structBB);
                        }
                        else
                        {
                            this.placeBlockAtCurrentPosition(world, par9, par10, j, i, k, structBB);
                        }
                    }
                }
            }
        }
    }

    /**
     * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int maxY, int
     * maxZ, int placeBlockId, int placeBlockMetadata, int replaceBlockId, int replaceBlockMetadata, boolean
     * alwaysreplace)
     */
    protected void fillWithMetadataBlocksIfEmpty(World world, StructureBoundingBox structBB, int minY, int minX, int minZ, int maxY, int maxX, int maxZ, int par9, int par10)
    {
        for (int i = minX; i <= maxX; ++i)
        {
            for (int j = minY; j <= maxY; ++j)
            {
                for (int k = minZ; k <= maxZ; ++k)
                {
                    if (this.getBlockIdAtCurrentPosition(world, j, i, k, structBB) == 0 || Block.blocksList[this.getBlockIdAtCurrentPosition(world, j, i, k, structBB)].blockMaterial.isReplaceable() || Block.blocksList[this.getBlockIdAtCurrentPosition(world, j, i, k, structBB)] instanceof BlockFlower)
                    {
                        this.placeBlockAtCurrentPosition(world, par9, par10, j, i, k, structBB);
                    }
                }
            }
        }
    }

    /**
     * Overwrites air and liquids from selected position downwards, stops at hitting anything else.
     */
    protected void fillCurrentPositionBlocksDownwards(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        int var8 = this.getXWithOffset(par4, par6);
        int var9 = this.getYWithOffset(par5);
        int var10 = this.getZWithOffset(par4, par6);

        if (par7StructureBoundingBox.isVecInside(var8, var9, var10))
        {
            while ((par1World.isAirBlock(var8, var9, var10) || par1World.getBlockMaterial(var8, var9, var10).isLiquid() || Block.blocksList[par1World.getBlockId(var8, var9, var10)].blockMaterial.isReplaceable() || Block.blocksList[par1World.getBlockId(var8, var9, var10)] instanceof BlockFlower) && var9 > 1)
            {
                par1World.setBlock(var8, var9, var10, par2, par3, 2);
                --var9;
            }
        }
    }

    protected void placeDoorAtCurrentPosition(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, int par7, BlockDoor door)
    {
        int var8 = this.getXWithOffset(par4, par6);
        int var9 = this.getYWithOffset(par5);
        int var10 = this.getZWithOffset(par4, par6);

        if (par2StructureBoundingBox.isVecInside(var8, var9, var10))
        {
            ItemDoor.placeDoorBlock(par1World, var8, var9, var10, par7, door);
        }
    }

	protected int getMetadataWithOffset(int blockId, int meta)
	{
		/*try {*/
			if (blockId == Block.rail.blockID)
			{
				if (this.coordBaseMode == 1 || this.coordBaseMode == 3)
				{
					if (meta == 1)
					{
						return 0;
					}

					return 1;
				}
			}
			else if (!(Block.blocksList[blockId] instanceof BlockDoor))
			{
				if (!(Block.blocksList[blockId] instanceof FCBlockStairs))
				{
					if (blockId == Block.ladder.blockID)
					{
						if (this.coordBaseMode == 0)
						{
							if (meta == 2)
							{
								return 3;
							}

							if (meta == 3)
							{
								return 2;
							}
						}
						else if (this.coordBaseMode == 1)
						{
							if (meta == 2)
							{
								return 4;
							}

							if (meta == 3)
							{
								return 5;
							}

							if (meta == 4)
							{
								return 2;
							}

							if (meta == 5)
							{
								return 3;
							}
						}
						else if (this.coordBaseMode == 3)
						{
							if (meta == 2)
							{
								return 5;
							}

							if (meta == 3)
							{
								return 4;
							}

							if (meta == 4)
							{
								return 2;
							}

							if (meta == 5)
							{
								return 3;
							}
						}
					}
					else if (blockId == Block.stoneButton.blockID)
					{
						if (this.coordBaseMode == 0)
						{
							if (meta == 3)
							{
								return 4;
							}

							if (meta == 4)
							{
								return 3;
							}
						}
						else if (this.coordBaseMode == 1)
						{
							if (meta == 3)
							{
								return 1;
							}

							if (meta == 4)
							{
								return 2;
							}

							if (meta == 2)
							{
								return 3;
							}

							if (meta == 1)
							{
								return 4;
							}
						}
						else if (this.coordBaseMode == 3)
						{
							if (meta == 3)
							{
								return 2;
							}

							if (meta == 4)
							{
								return 1;
							}

							if (meta == 2)
							{
								return 3;
							}

							if (meta == 1)
							{
								return 4;
							}
						}
					}
					else if (blockId != Block.tripWireSource.blockID && (Block.blocksList[blockId] == null || !(Block.blocksList[blockId] instanceof BlockDirectional)))
					{
						if (blockId == Block.pistonBase.blockID || blockId == Block.pistonStickyBase.blockID || blockId == Block.lever.blockID || blockId == Block.dispenser.blockID)
						{
							if (this.coordBaseMode == 0)
							{
								if (meta == 2 || meta == 3)
								{
									return Facing.oppositeSide[meta];
								}
							}
							else if (this.coordBaseMode == 1)
							{
								if (meta == 2)
								{
									return 4;
								}

								if (meta == 3)
								{
									return 5;
								}

								if (meta == 4)
								{
									return 2;
								}

								if (meta == 5)
								{
									return 3;
								}
							}
							else if (this.coordBaseMode == 3)
							{
								if (meta == 2)
								{
									return 5;
								}

								if (meta == 3)
								{
									return 4;
								}

								if (meta == 4)
								{
									return 2;
								}

								if (meta == 5)
								{
									return 3;
								}
							}
						}
					}
					else if (this.coordBaseMode == 0)
					{
						if (meta == 0 || meta == 2)
						{
							return Direction.rotateOpposite[meta];
						}
					}
					else if (this.coordBaseMode == 1)
					{
						if (meta == 2)
						{
							return 1;
						}

						if (meta == 0)
						{
							return 3;
						}

						if (meta == 1)
						{
							return 2;
						}

						if (meta == 3)
						{
							return 0;
						}
					}
					else if (this.coordBaseMode == 3)
					{
						if (meta == 2)
						{
							return 3;
						}

						if (meta == 0)
						{
							return 1;
						}

						if (meta == 1)
						{
							return 2;
						}

						if (meta == 3)
						{
							return 0;
						}
					}
				}
				else if (this.coordBaseMode == 0)
				{
					if (meta == 2)
					{
						return 3;
					}

					if (meta == 3)
					{
						return 2;
					}
				}
				else if (this.coordBaseMode == 1)
				{
					if (meta == 0)
					{
						return 2;
					}

					if (meta == 1)
					{
						return 3;
					}

					if (meta == 2)
					{
						return 0;
					}

					if (meta == 3)
					{
						return 1;
					}
				}
				else if (this.coordBaseMode == 3)
				{
					if (meta == 0)
					{
						return 2;
					}

					if (meta == 1)
					{
						return 3;
					}

					if (meta == 2)
					{
						return 1;
					}

					if (meta == 3)
					{
						return 0;
					}
				}
			}
			else if (this.coordBaseMode == 0)
			{
				if (meta == 0)
				{
					return 2;
				}

				if (meta == 2)
				{
					return 0;
				}
			}
			else
			{
				if (this.coordBaseMode == 1)
				{
					return meta + 1 & 3;
				}

				if (this.coordBaseMode == 3)
				{
					return meta + 3 & 3;
				}
			}

			if (blockId == Block.wood.blockID) {// || (BTADecoIntegration.isDecoInstalled() && (blockId == BTADecoIntegration.barkLogStripped.blockID || blockId == BTADecoIntegration.strippedLog.blockID))) {
				if (this.coordBaseMode == 1 || this.coordBaseMode == 3) {
					if ((meta & 8) != 0) {
						return meta - 4;
					}
					else {
						return meta + 4;
					}
				}
			}

			if (blockId == Block.bed.blockID) {
				if (this.coordBaseMode == 1) {
					meta += 1;
				}
				if (this.coordBaseMode == 0) {
					meta += 2;
				}
				if (this.coordBaseMode == 3) {
					meta += 3;
				}

				return meta % 4;
			}

			if (Block.blocksList[blockId] instanceof BlockFenceGate) {
				if (this.coordBaseMode == 1 || this.coordBaseMode == 3) {
					return meta % 2 + 1;
				}
			}

			if (Block.blocksList[blockId] instanceof BlockTrapDoor /*|| (BTADecoIntegration.isDecoInstalled() && Class.forName("DecoBlockChair").isInstance(Block.blocksList[blockId]))*/) {
				if (this.coordBaseMode == 0)
				{
					if (meta == 0)
					{
						return 1;
					}

					if (meta == 1)
					{
						return 0;
					}
				}
				else if (this.coordBaseMode == 1)
				{
					if (meta == 0)
					{
						return 2;
					}

					if (meta == 1)
					{
						return 3;
					}

					if (meta == 2)
					{
						return 0;
					}

					if (meta == 3)
					{
						return 1;
					}
				}
				else if (this.coordBaseMode == 3)
				{
					if (meta == 0)
					{
						return 3;
					}

					if (meta == 1)
					{
						return 2;
					}

					if (meta == 2)
					{
						return 0;
					}

					if (meta == 3)
					{
						return 1;
					}
				}
				
				if (blockId == FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID) {
					if (this.coordBaseMode == 0)
					{
						if (meta == 0)
						{
							return 5;
						}

						if (meta == 1)
						{
							return 4;
						}
					}
					else if (this.coordBaseMode == 1)
					{
						if (meta == 0)
						{
							return 2;
						}

						if (meta == 1)
						{
							return 3;
						}

						if (meta == 2)
						{
							return 4;
						}

						if (meta == 3)
						{
							return 5;
						}
					}
					else if (this.coordBaseMode == 3)
					{
						if (meta == 0)
						{
							return 3;
						}

						if (meta == 1)
						{
							return 2;
						}

						if (meta == 2)
						{
							return 4;
						}

						if (meta == 3)
						{
							return 5;
						}
					}
				}
			}
		/*} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

		return meta;
	}
}