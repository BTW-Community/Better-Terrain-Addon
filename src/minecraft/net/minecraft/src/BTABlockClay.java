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

	//CLIENT ONLY
	Icon sandyClayIcon;
	Icon redSandyClayIcon;
	Icon grassyClayOverlay;

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister var1)
	{
		super.registerIcons(var1);
		sandyClayIcon = var1.registerIcon("btaBlockSandyClay");
		redSandyClayIcon = var1.registerIcon("btaBlockRedSandyClay");
		grassyClayOverlay = var1.registerIcon("btaOverlayGrassyClay");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		if (meta == 1) {
			return sandyClayIcon;
		}
		else if (meta == 2) {
			return redSandyClayIcon;
		}
		else {
			return super.getIcon(side, meta);
		}
	}
	
	@Override
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
		int meta = render.blockAccess.getBlockMetadata(x, y, z);
		
		if (meta == 0)
			super.RenderBlock(render, x, y, z);
		else if (meta == 1)
			this.RenderBlockWithTexture(render, x, y, z, sandyClayIcon);
		else if (meta == 2)
			this.RenderBlockWithTexture(render, x, y, z, redSandyClayIcon);
		else
			Block.grass.RenderBlock(render, x, y, z);
		
		return true;
	}

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
	@Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		if (blockAccess.getBlockMetadata(x, y, z) == 3 && side == 1) {
			return false;
		}
		
		return true;
    }
	
	@Override
	public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
		if (var1.blockAccess.getBlockMetadata(var2, var3, var4) == 3) {
			var1.SetRenderAllFaces(false);
			this.RenderBlockWithTexture(var1, var2, var3, var4, grassyClayOverlay);
			var1.SetRenderAllFaces(true);
		}
    }
}