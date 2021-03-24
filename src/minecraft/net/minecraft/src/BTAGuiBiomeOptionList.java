package net.minecraft.src;

class BTAGuiBiomeOptionList extends GuiSlot
{
    final BTAGuiGeneratorOptions generatorOptionsGui;
    public int selected;

    public BTAGuiBiomeOptionList(BTAGuiGeneratorOptions var1)
    {
        super(var1.mc, var1.width, var1.height, 43, var1.height - 60, 24);
        this.generatorOptionsGui = var1;
        this.selected = -1;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return BTAGuiGeneratorOptions.getBiomeArray(this.generatorOptionsGui).theBiomesList().size();
    }

    /**
     * the element in the slot that was clicked, boolean for whether it was double clicked or not
     */
    protected void elementClicked(int var1, boolean var2)
    {
        this.selected = var1;
        this.generatorOptionsGui.setButtons();
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int var1)
    {
        return var1 == this.selected;
    }

    protected void drawBackground() {}

    protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5)
    {
    	BTABiomeInfo biomeInfo = (BTABiomeInfo)BTAGuiGeneratorOptions.getBiomeArray(this.generatorOptionsGui).theBiomesList().get(BTAGuiGeneratorOptions.getBiomeArray(this.generatorOptionsGui).theBiomesList().size() - var1 - 1);
        String var7 = biomeInfo.getName();
        boolean var8 = biomeInfo.getEnabled();

        if (var8)
        {
            this.generatorOptionsGui.fontRenderer.drawString(var7, var2 + 1, var3 + 7, 16777215);
            this.generatorOptionsGui.fontRenderer.drawString("YES", var2 + 179, var3 + 7, 16777215);
        }
        else
        {
            this.generatorOptionsGui.fontRenderer.drawString(var7, var2 + 1, var3 + 7, 10526880);
            this.generatorOptionsGui.fontRenderer.drawString("NO", var2 + 182, var3 + 7, 10526880);
        }
    }

    protected int getScrollBarX()
    {
        return this.generatorOptionsGui.width - 70;
    }
}
