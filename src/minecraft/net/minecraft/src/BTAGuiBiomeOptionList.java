package net.minecraft.src;

class BTAGuiBiomeOptionList extends GuiSlot
{
    final BTAGuiBiomeOptions guiGeneratorOptions;
    public int selected;

    public BTAGuiBiomeOptionList(BTAGuiBiomeOptions guiGeneratorOptions)
    {
        super(guiGeneratorOptions.mc, guiGeneratorOptions.width, guiGeneratorOptions.height, 43, guiGeneratorOptions.height - 60, 24);
        this.guiGeneratorOptions = guiGeneratorOptions;
        this.selected = -1;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return BTAGuiBiomeOptions.getBiomeArray(this.guiGeneratorOptions).getBiomeInfoList().size();
    }

    /**
     * the element in the slot that was clicked, boolean for whether it was double clicked or not
     */
    protected void elementClicked(int var1, boolean var2)
    {
        this.selected = var1;
        this.guiGeneratorOptions.setButtons();
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int var1)
    {
        return var1 == this.selected;
    }

    protected void drawBackground() {}

    protected void drawSlot(int index, int x, int y, int var4, Tessellator tesselator)
    {
    	BTABiomeInfo biomeInfo = (BTABiomeInfo)BTAGuiBiomeOptions.getBiomeArray(this.guiGeneratorOptions).getBiomeInfoList().get(BTAGuiBiomeOptions.getBiomeArray(this.guiGeneratorOptions).getBiomeInfoList().size() - index - 1);
        String biomeName = biomeInfo.getName();
        boolean biomeEnabled = biomeInfo.getEnabled();
        boolean decoOnly = biomeInfo.isDecoOnly();
        
        if (biomeName.startsWith("Better "))
        	biomeName = biomeName.substring(7);
        if (biomeName.equals("Valley Highlands"))
        	biomeName = "Valley";
        if (biomeName.equals("Badlands Plateau"))
        	biomeName = "Badlands";
        
        if (decoOnly && !guiGeneratorOptions.isDeco()) {
            this.guiGeneratorOptions.fontRenderer.drawString(biomeName + " (Deco)", x + 1, y + 7, 8526880);
            this.guiGeneratorOptions.fontRenderer.drawString("-", x + 185, y + 7, 8526880);
        }
        else if (biomeEnabled)
        {
            this.guiGeneratorOptions.fontRenderer.drawString(biomeName, x + 1, y + 7, 16777215);
            this.guiGeneratorOptions.fontRenderer.drawString("YES", x + 179, y + 7, 16777215);
        }
        else
        {
            this.guiGeneratorOptions.fontRenderer.drawString(biomeName, x + 1, y + 7, 10526880);
            this.guiGeneratorOptions.fontRenderer.drawString("NO", x + 182, y + 7, 10526880);
        }
    }

    protected int getScrollBarX()
    {
        return this.guiGeneratorOptions.width - 70;
    }
}
