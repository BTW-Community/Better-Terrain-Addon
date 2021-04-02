package net.minecraft.src;

class BTAGuiBiomeOptionList extends GuiSlot
{
    final BTAGuiBiomeOptions guiBiomeOptions;
    public int selected;

    public BTAGuiBiomeOptionList(BTAGuiBiomeOptions guiBiomeOptions)
    {
        super(guiBiomeOptions.mc, guiBiomeOptions.width, guiBiomeOptions.height, 43, guiBiomeOptions.height - 60, 24);
        this.guiBiomeOptions = guiBiomeOptions;
        this.selected = -1;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return BTAGuiBiomeOptions.getBiomeArray(this.guiBiomeOptions.guiGeneratorOptions).getBiomeInfoList().size();
    }

    /**
     * the element in the slot that was clicked, boolean for whether it was double clicked or not
     */
    protected void elementClicked(int var1, boolean var2)
    {
        this.selected = var1;
        
        if (var2) {
        	BTABiomeInfo selectedBiome = this.guiBiomeOptions.guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().get(this.guiBiomeOptions.guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().size() - this.selected - 1);

			if (selectedBiome.getEnabled()) {
				selectedBiome.setEnabled(false);
			}
			else {
				selectedBiome.setEnabled(true);
			}
        }
        
        this.guiBiomeOptions.setButtons();
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
    	BTABiomeInfo biomeInfo = (BTABiomeInfo)BTAGuiBiomeOptions.getBiomeArray(this.guiBiomeOptions.guiGeneratorOptions).getBiomeInfoList().get(BTAGuiBiomeOptions.getBiomeArray(this.guiBiomeOptions.guiGeneratorOptions).getBiomeInfoList().size() - index - 1);
        String biomeName = biomeInfo.getName();
        boolean biomeEnabled = biomeInfo.getEnabled();
        boolean decoOnly = biomeInfo.isDecoOnly();
        
        if (biomeName.startsWith("Better "))
        	biomeName = biomeName.substring(7);
        if (biomeName.equals("Valley Highlands"))
        	biomeName = "Valley";
        if (biomeName.equals("Badlands Plateau"))
        	biomeName = "Badlands";
        
        if (decoOnly && !guiBiomeOptions.isDeco()) {
            this.guiBiomeOptions.fontRenderer.drawString(biomeName + " (Deco)", x + 1, y + 7, 8526880);
            this.guiBiomeOptions.fontRenderer.drawString("-", x + 185, y + 7, 8526880);
        }
        else if (biomeEnabled)
        {
            this.guiBiomeOptions.fontRenderer.drawString(biomeName, x + 1, y + 7, 16777215);
            this.guiBiomeOptions.fontRenderer.drawString("YES", x + 179, y + 7, 16777215);
        }
        else
        {
            this.guiBiomeOptions.fontRenderer.drawString(biomeName, x + 1, y + 7, 10526880);
            this.guiBiomeOptions.fontRenderer.drawString("NO", x + 182, y + 7, 10526880);
        }
    }

    protected int getScrollBarX()
    {
        return this.guiBiomeOptions.width - 70;
    }
}
