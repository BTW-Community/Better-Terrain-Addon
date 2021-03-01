package net.minecraft.src;

public class FCItemStone extends Item
{
    public FCItemStone(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemStone");
        this.SetFilterableProperties(2);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 8;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return FCBetterThanWolves.fcBlockCobblestoneLoose.blockID;
    }
}
