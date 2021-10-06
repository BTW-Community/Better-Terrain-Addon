package net.minecraft.src;

public class BTABlockClay extends FCBlockClay {
	public BTABlockClay(int id) {
		super(id);
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
	{
		if (!var1.isRemote)
		{
			this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(this.idDropped(var5, var1.rand, var7), 1, this.damageDropped(var5)));

			if (var5 == 1) {
				this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 6, 0, var6);
			}
			else if (var5 == 2 && BTADecoIntegration.isDecoInstalled()) {
				this.DropItemsIndividualy(var1, var2, var3, var4, BTADecoIntegration.pileRedSand.itemID, 6, 0, var6);
			}
			else {
				this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 6, 0, var6);
			}
		}
	}

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileClay.itemID, 1, 0, var6);
        
        if (var5 == 1) {
			this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 4, 0, var6);
		}
		else if (var5 == 2 && BTADecoIntegration.isDecoInstalled()) {
			this.DropItemsIndividualy(var1, var2, var3, var4, BTADecoIntegration.pileRedSand.itemID, 4, 0, var6);
		}
		else {
			this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 4, 0, var6);
		}
        return true;
    }
    
    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        int meta = var1.getBlockMetadata(var2, var3, var4);
        
        return (meta == 1 || meta == 2) ? 0.8F : 1.0F;
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) == 3;
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) == 3;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) == 3;
    }
}