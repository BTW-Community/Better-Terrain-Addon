package net.minecraft.src;

public class BTAGuiGeneratorOptions extends GuiScreen
{
    private final GuiCreateWorld createWorldGui;
    private BTAGuiBiomeOptionList guiBiomeOptionList;
    private BTAWorldConfigurationInfo defaultGeneratorInfo;
    private GuiButton buttonEnable;
    private GuiButton buttonAll;
    private GuiButton buttonBiome;
    private GuiButton buttonSetting;
    private boolean all = false;
    private boolean customize;
    private boolean biomescreen;
    private BTABiomeInfo biome;

    public BTAGuiGeneratorOptions(GuiCreateWorld var1, String var2)
    {
        this.createWorldGui = var1;
        this.setGeneratorInfo(var2);
        this.customize = false;
        defaultGeneratorInfo = BTAWorldConfigurationInfo.createDefaultConfiguration(createWorldGui.isDeco());
    }

    public String getGeneratorInfo()
    {
        return this.defaultGeneratorInfo.toString();
    }

    public void setGeneratorInfo(String var1)
    {
        this.defaultGeneratorInfo = BTAWorldConfigurationInfo.createInfoFromString(var1);
    }

    static BTAWorldConfigurationInfo getBiomeArray(BTAGuiGeneratorOptions var0)
    {
        return var0.defaultGeneratorInfo;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        this.guiBiomeOptionList = new BTAGuiBiomeOptionList(this);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.done")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.cancel")));
        this.buttonList.add(this.buttonEnable = new GuiButton(2, this.width / 2 - 155, this.height - 52, 100, 20, "Enable/Disable"));
        this.buttonList.add(this.buttonAll = new GuiButton(3, this.width / 2 - 50, this.height - 52, 100, 20, "Disable All"));
        this.buttonList.add(this.buttonBiome = new GuiButton(4, this.width / 2 + 55, this.height - 52, 100, 20, "-"));
        this.buttonList.add(this.buttonSetting = new GuiButton(5, this.width / 2 + 55, this.height - 28, 100, 20, "-"));
        this.setButtons();
        this.switchScreen();
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton var1)
    {
        int var2 = this.defaultGeneratorInfo.theBiomesList().size() - this.guiBiomeOptionList.selected - 1;

        if (var1.id == 0)
        {
            this.createWorldGui.generatorOptionsToUse = this.getGeneratorInfo();
            this.mc.displayGuiScreen(this.createWorldGui);
        }
        else if (var1.id == 1)
        {
            this.createWorldGui.generatorOptionsToUse = this.getGeneratorInfo();
            this.mc.displayGuiScreen(this.createWorldGui);
        }
        else
        {
        	BTABiomeInfo var3;

            if (var1.id == 2)
            {
                var3 = (BTABiomeInfo)this.defaultGeneratorInfo.theBiomesList().get(this.defaultGeneratorInfo.theBiomesList().size() - this.guiBiomeOptionList.selected - 1);

                if (var3.getEnabled())
                {
                    var3.setEnabled(false);
                }
                else
                {
                    var3.setEnabled(true);
                }
            }
            else if (var1.id == 3)
            {
                int var4;

                if (!this.all)
                {
                    for (var4 = 0; var4 < this.defaultGeneratorInfo.theBiomesList().size(); ++var4)
                    {
                        var3 = (BTABiomeInfo)this.defaultGeneratorInfo.theBiomesList().get(var4);
                        var3.setEnabled(false);
                    }

                    this.all = true;
                }
                else
                {
                    for (var4 = 0; var4 < this.defaultGeneratorInfo.theBiomesList().size(); ++var4)
                    {
                        var3 = (BTABiomeInfo)this.defaultGeneratorInfo.theBiomesList().get(var4);
                        var3.setEnabled(true);
                    }

                    this.all = false;
                }

                this.updateButtons();
            }
            else if (var1.id == 4)
            {
                this.biome = (BTABiomeInfo)this.defaultGeneratorInfo.theBiomesList().get(this.defaultGeneratorInfo.theBiomesList().size() - this.guiBiomeOptionList.selected - 1);
                this.customize = false;
                this.biomescreen = true;
                this.switchScreen();
            }
            else if (var1.id == 5)
            {
                if (this.biomescreen)
                {
                    this.biomescreen = false;
                    this.customize = false;
                }
                else if (this.customize)
                {
                    this.customize = false;
                }
                else
                {
                    this.customize = true;
                }

                this.switchScreen();
            }
        }
    }

    public void updateButtons()
    {
        if (this.all)
        {
            this.buttonAll.displayString = "Enable All";
        }
        else
        {
            this.buttonAll.displayString = "Disable All";
        }
    }

    public void setButtons()
    {
        boolean var1 = this.checkPossible();
        this.buttonEnable.enabled = var1;
        this.buttonBiome.enabled = false;
        this.buttonSetting.enabled = false;
    }

    private boolean checkPossible()
    {
        return this.guiBiomeOptionList.selected > -1 && this.guiBiomeOptionList.selected < this.defaultGeneratorInfo.theBiomesList().size();
    }

    public void switchScreen()
    {
        if (this.customize)
        {
            this.buttonEnable.drawButton = false;
            this.buttonAll.drawButton = false;
            this.buttonBiome.drawButton = false;
            this.buttonSetting.displayString = "-";
        }
        else if (this.biomescreen)
        {
            this.buttonEnable.drawButton = false;
            this.buttonAll.drawButton = false;
            this.buttonBiome.drawButton = false;
            this.buttonSetting.displayString = "-";
        }
        else
        {
            this.buttonEnable.drawButton = true;
            this.buttonAll.drawButton = true;
            this.buttonBiome.drawButton = true;
            this.buttonSetting.displayString = "-";
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        this.drawDefaultBackground();

        if (this.customize)
        {
            this.drawCenteredString(this.fontRenderer, "World Settings", this.width / 2, 8, 16777215);
        }
        else if (this.biomescreen)
        {
            this.drawCenteredString(this.fontRenderer, "Biome: " + this.biome.getName(), this.width / 2, 8, 16777215);
        }
        else
        {
            this.guiBiomeOptionList.drawScreen(var1, var2, var3);
            this.drawCenteredString(this.fontRenderer, "Biome List", this.width / 2, 8, 16777215);
            this.drawCenteredString(this.fontRenderer, "Biome Name", this.width / 2 - 80, 32, 16777215);
            this.drawCenteredString(this.fontRenderer, "Enabled", this.width / 2 + 80, 32, 16777215);
        }

        super.drawScreen(var1, var2, var3);
    }
}
