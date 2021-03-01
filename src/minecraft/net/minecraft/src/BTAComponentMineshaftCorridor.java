package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BTAComponentMineshaftCorridor extends StructureComponent
{
    private final boolean hasRails;
    private final boolean hasSpiders;
    private boolean spawnerPlaced;

    /**
     * A count of the different sections of this mine. The space between ceiling supports.
     */
    private int sectionCount;

    public BTAComponentMineshaftCorridor(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
    {
        super(par1);
        this.coordBaseMode = par4;
        this.boundingBox = par3StructureBoundingBox;
        this.hasRails = par2Random.nextInt(3) == 0;
        this.hasSpiders = !this.hasRails && par2Random.nextInt(23) == 0;

        if (this.coordBaseMode != 2 && this.coordBaseMode != 0)
        {
            this.sectionCount = par3StructureBoundingBox.getXSize() / 5;
        }
        else
        {
            this.sectionCount = par3StructureBoundingBox.getZSize() / 5;
        }
    }

    public static StructureBoundingBox findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5)
    {
        StructureBoundingBox var6 = new StructureBoundingBox(par2, par3, par4, par2, par3 + 2, par4);
        int var7;

        for (var7 = par1Random.nextInt(3) + 2; var7 > 0; --var7)
        {
            int var8 = var7 * 5;

            switch (par5)
            {
                case 0:
                    var6.maxX = par2 + 2;
                    var6.maxZ = par4 + (var8 - 1);
                    break;

                case 1:
                    var6.minX = par2 - (var8 - 1);
                    var6.maxZ = par4 + 2;
                    break;

                case 2:
                    var6.maxX = par2 + 2;
                    var6.minZ = par4 - (var8 - 1);
                    break;

                case 3:
                    var6.maxX = par2 + (var8 - 1);
                    var6.maxZ = par4 + 2;
            }

            if (StructureComponent.findIntersecting(par0List, var6) == null)
            {
                break;
            }
        }

        return var7 > 0 ? var6 : null;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        int var4 = this.getComponentType();
        int var5 = par3Random.nextInt(4);

        switch (this.coordBaseMode)
        {
            case 0:
                if (var5 <= 1)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ + 1, this.coordBaseMode, var4);
                }
                else if (var5 == 2)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ - 3, 1, var4);
                }
                else
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ - 3, 3, var4);
                }

                break;

            case 1:
                if (var5 <= 1)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, var4);
                }
                else if (var5 == 2)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ - 1, 2, var4);
                }
                else
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ + 1, 0, var4);
                }

                break;

            case 2:
                if (var5 <= 1)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ - 1, this.coordBaseMode, var4);
                }
                else if (var5 == 2)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, 1, var4);
                }
                else
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, 3, var4);
                }

                break;

            case 3:
                if (var5 <= 1)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, var4);
                }
                else if (var5 == 2)
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.minZ - 1, 2, var4);
                }
                else
                {
                	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + par3Random.nextInt(3), this.boundingBox.maxZ + 1, 0, var4);
                }
        }

        if (var4 < 8)
        {
            int var6;
            int var7;

            if (this.coordBaseMode != 2 && this.coordBaseMode != 0)
            {
                for (var6 = this.boundingBox.minX + 3; var6 + 3 <= this.boundingBox.maxX; var6 += 5)
                {
                    var7 = par3Random.nextInt(5);

                    if (var7 == 0)
                    {
                    	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, var6, this.boundingBox.minY, this.boundingBox.minZ - 1, 2, var4 + 1);
                    }
                    else if (var7 == 1)
                    {
                    	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, var6, this.boundingBox.minY, this.boundingBox.maxZ + 1, 0, var4 + 1);
                    }
                }
            }
            else
            {
                for (var6 = this.boundingBox.minZ + 3; var6 + 3 <= this.boundingBox.maxZ; var6 += 5)
                {
                    var7 = par3Random.nextInt(5);

                    if (var7 == 0)
                    {
                    	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY, var6, 1, var4 + 1);
                    }
                    else if (var7 == 1)
                    {
                    	BTAStructureMineshaftPieces.getNextComponent(par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY, var6, 3, var4 + 1);
                    }
                }
            }
        }
    }

    /**
     * Used to generate chests with items in it. ex: Temple Chests, Village Blacksmith Chests, Mineshaft Chests.
     */
    protected boolean generateStructureChestContents(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, WeightedRandomChestContent[] par7ArrayOfWeightedRandomChestContent, int par8)
    {
        int var9 = this.getXWithOffset(par4, par6);
        int var10 = this.getYWithOffset(par5);
        int var11 = this.getZWithOffset(par4, par6);

        if (par2StructureBoundingBox.isVecInside(var9, var10, var11) && par1World.getBlockId(var9, var10, var11) == 0)
        {
            par1World.setBlock(var9, var10, var11, Block.rail.blockID, this.getMetadataWithOffset(Block.rail.blockID, par3Random.nextBoolean() ? 1 : 0), 2);
            EntityMinecartChest var12 = new EntityMinecartChest(par1World, (double)((float)var9 + 0.5F), (double)((float)var10 + 0.5F), (double)((float)var11 + 0.5F));
            WeightedRandomChestContent.generateChestContents(par3Random, par7ArrayOfWeightedRandomChestContent, var12, par8);
            this.FilterChestMinecartContents(var12);
            par1World.spawnEntityInWorld(var12);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (this.isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox) || this.isStructureBoundingBoxEmpty(par1World, par3StructureBoundingBox))
        {
            return false;
        }
        else
        {
            int var4 = this.sectionCount * 5 - 1;
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 2, 1, var4, 0, 0, false);
            this.randomlyFillWithBlocks(par1World, par3StructureBoundingBox, par2Random, 0.8F, 0, 2, 0, 2, 2, var4, 0, 0, false);

            if (this.hasSpiders)
            {
                this.randomlyFillWithBlocks(par1World, par3StructureBoundingBox, par2Random, 0.6F, 0, 0, 0, 2, 1, var4, FCBetterThanWolves.fcBlockWeb.blockID, 0, false);
            }

            int var5;
            int var6;
            int var7;

            for (var5 = 0; var5 < this.sectionCount; ++var5)
            {
                var6 = 2 + var5 * 5;
                
            	boolean supportShaft = false;
            	
            	if (this.getBlockIdAtCurrentPosition(par1World, 0, 3, var6, par3StructureBoundingBox) != 0 ||
            			this.getBlockIdAtCurrentPosition(par1World, 1, 3, var6, par3StructureBoundingBox) != 0 ||
            			this.getBlockIdAtCurrentPosition(par1World, 2, 3, var6, par3StructureBoundingBox) != 0)
            		supportShaft = true;
                
            	if (supportShaft) {
	            	this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 0, var6, 0, 1, var6, Block.wood.blockID, 0, 0, 0, false);
	                this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, var6, 2, 1, var6, Block.wood.blockID, 0, 0, 0, false);
	                int var8 = this.getMetadataWithOffset(Block.wood.blockID, 4);
	
	                if (par2Random.nextInt(4) == 0)
	                {
	                    this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 2, var6, 0, 2, var6, Block.wood.blockID, var8, 0, 0, false);
	                    this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, var6, 2, 2, var6, Block.wood.blockID, var8, 0, 0, false);
	                }
	                else
	                {
	                    this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 2, var6, 2, 2, var6, Block.wood.blockID, var8, 0, 0, false);
	                }
            	}
            	else if (var5 % 2 == 0){
            		
            	}

                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.1F, 0, 2, var6 - 1, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.1F, 2, 2, var6 - 1, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.1F, 0, 2, var6 + 1, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.1F, 2, 2, var6 + 1, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.05F, 0, 2, var6 - 2, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.05F, 2, 2, var6 - 2, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.05F, 0, 2, var6 + 2, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.05F, 2, 2, var6 + 2, FCBetterThanWolves.fcBlockWeb.blockID, 0);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.05F, 1, 2, var6 - 1, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8);
                this.randomlyPlaceBlockIfNeighbor(par1World, par3StructureBoundingBox, par2Random, 0.05F, 1, 2, var6 + 1, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, 8);

                if (par2Random.nextInt(100) == 0)
                {
                    this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 2, 0, var6 - 1, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.func_78816_a(), new WeightedRandomChestContent[] {Item.enchantedBook.func_92114_b(par2Random)}), 3 + par2Random.nextInt(4));
                }

                if (par2Random.nextInt(100) == 0)
                {
                    this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 0, 0, var6 + 1, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.func_78816_a(), new WeightedRandomChestContent[] {Item.enchantedBook.func_92114_b(par2Random)}), 3 + par2Random.nextInt(4));
                }

                if (this.hasSpiders && !this.spawnerPlaced)
                {
                    var7 = this.getYWithOffset(0);
                    int var9 = var6 - 1 + par2Random.nextInt(3);
                    int var10 = this.getXWithOffset(1, var9);
                    var9 = this.getZWithOffset(1, var9);

                    if (par3StructureBoundingBox.isVecInside(var10, var7, var9))
                    {
                        this.spawnerPlaced = true;
                        par1World.setBlock(var10, var7, var9, Block.mobSpawner.blockID, 0, 2);
                        TileEntityMobSpawner var11 = (TileEntityMobSpawner)par1World.getBlockTileEntity(var10, var7, var9);

                        if (var11 != null)
                        {
                            var11.func_98049_a().setMobID("CaveSpider");
                        }
                    }
                }
            }

            for (var5 = 0; var5 <= 2; ++var5)
            {
                for (var6 = 0; var6 <= var4; ++var6)
                {
                    var7 = this.getBlockIdAtCurrentPosition(par1World, var5, -1, var6, par3StructureBoundingBox);

                    if (var7 == 0)
                    {
                        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, var5, -1, var6, par3StructureBoundingBox);
                    }
                }
            }

            if (this.hasRails)
            {
                for (var5 = 0; var5 <= var4; ++var5)
                {
                    var6 = this.getBlockIdAtCurrentPosition(par1World, 1, -1, var5, par3StructureBoundingBox);

                    if (var6 > 0 && Block.opaqueCubeLookup[var6])
                    {
                        this.randomlyPlaceBlock(par1World, par3StructureBoundingBox, par2Random, 0.7F, 1, 0, var5, Block.rail.blockID, this.getMetadataWithOffset(Block.rail.blockID, 0));
                    }
                }
            }

            return true;
        }
    }

    private void FilterChestMinecartContents(EntityMinecartChest var1)
    {
        for (int var2 = 0; var2 < var1.getSizeInventory(); ++var2)
        {
            ItemStack var3 = var1.getStackInSlot(var2);

            if (var3 != null)
            {
                int var4 = var3.itemID;

                if (var4 == Item.ingotIron.itemID)
                {
                    if (var1.posY > 36.0D)
                    {
                        var1.setInventorySlotContents(var2, (ItemStack)null);
                    }
                    else
                    {
                        var3.stackSize = 1;
                    }
                }
                else if (var4 == Item.diamond.itemID)
                {
                    if (var1.posY > 24.0D)
                    {
                        var1.setInventorySlotContents(var2, (ItemStack)null);
                    }
                    else
                    {
                        var3.stackSize = 1;
                    }
                }
                else if (var4 == Item.pickaxeIron.itemID)
                {
                    var1.setInventorySlotContents(var2, (ItemStack)null);
                }
                else if (var4 == Item.redstone.itemID)
                {
                    if (var1.posY > 24.0D)
                    {
                        var1.setInventorySlotContents(var2, (ItemStack)null);
                    }
                }
                else if (var4 == Item.pumpkinSeeds.itemID)
                {
                    var1.setInventorySlotContents(var2, (ItemStack)null);
                }
            }
        }
    }

    /**
     * Returns the direction-shifted metadata for blocks that require orientation, e.g. doors, stairs, ladders.
     * Parameters: block ID, original metadata
     */
    protected int getMetadataWithOffset(int var1, int var2)
    {
        if (var1 != Block.wood.blockID)
        {
            return super.getMetadataWithOffset(var1, var2);
        }
        else
        {
            if (this.coordBaseMode == 1 || this.coordBaseMode == 3)
            {
                int var3 = var2 & 3;
                int var4 = var2 & 12;

                if (var4 == 4)
                {
                    var4 = 8;
                }
                else if (var4 == 8)
                {
                    var4 = 4;
                }

                var2 = var3 | var4;
            }

            return var2;
        }
    }
}
