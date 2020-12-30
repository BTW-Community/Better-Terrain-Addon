package net.minecraft.src;

public class BTABlockClay extends FCBlockClay {
	public BTABlockClay(int id) {
		super(id);
	}

	//CLIENT ONLY
	Icon sandyClayIcon;
	Icon redSandyClayIcon;
	
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        sandyClayIcon = var1.registerIcon("btaBlockSandyClay");
        redSandyClayIcon = var1.registerIcon("btaBlockRedSandyClay");
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
    	if (!var1.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(var7, var1.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (var1.rand.nextFloat() <= var6)
                {
                    int var10 = this.idDropped(var5, var1.rand, var7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
                    }
                }
            }
        }

        if (!var1.isRemote)
        {
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

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int meta = var1.getBlockMetadata(var2, var3, var4);
        
        if (meta == 1) {
        	return sandyClayIcon;
        }
        else if (meta == 2) {
        	return redSandyClayIcon;
        }
        else {
        	return super.getBlockTexture(var1, var2, var3, var4, var5);
        }
    }
}