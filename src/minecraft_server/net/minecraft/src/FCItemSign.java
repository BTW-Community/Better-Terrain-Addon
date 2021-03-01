package net.minecraft.src;

public class FCItemSign extends ItemSign
{
    public FCItemSign(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.setUnlocalizedName("sign");
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var7 != 0 && var1.stackSize != 0 && (var2 == null || var2.canPlayerEdit(var4, var5, var6, var7, var1)) && var3.getBlockMaterial(var4, var5, var6).isSolid())
        {
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var4, var5, var6);
            var11.AddFacingAsOffset(var7);

            if (Block.signPost.canPlaceBlockAt(var3, var11.i, var11.j, var11.k))
            {
                if (var7 == 1)
                {
                    int var12 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                    var3.setBlock(var11.i, var11.j, var11.k, Block.signPost.blockID, var12, 3);
                }
                else
                {
                    var3.setBlock(var11.i, var11.j, var11.k, Block.signWall.blockID, var7, 3);
                }

                --var1.stackSize;
                TileEntitySign var13 = (TileEntitySign)var3.getBlockTileEntity(var11.i, var11.j, var11.k);

                if (var13 != null)
                {
                    var2.displayGUIEditSign(var13);
                }

                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
