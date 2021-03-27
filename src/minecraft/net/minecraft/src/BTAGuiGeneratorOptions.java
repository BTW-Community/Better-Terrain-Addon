package net.minecraft.src;

public class BTAGuiGeneratorOptions extends GuiScreen
{
	private final GuiCreateWorld createWorldGui;
	private BTAGuiBiomeOptionList guiBiomeOptionList;
	private BTAWorldConfigurationInfo worldGeneratorInfo;
	private GuiButton buttonPerlinBeaches;
	private GuiButton buttonEnable;
	private GuiButton buttonAll;
	private GuiButton buttonOceans;
	private BTAGuiSlider sliderOceanSize;
	private GuiButton buttonBiomeSize;
	private boolean all = false;
	private BTABiomeInfo biome;

	private static final int id_done = 0;
	private static final int id_perlin = 1;
	private static final int id_enable = 2;
	private static final int id_enableAll = 3;
	private static final int id_oceans = 4;
	private static final int id_smallBiomes = 5;

	public BTAGuiGeneratorOptions(GuiCreateWorld createWorldGui, String infoString)
	{
		this.createWorldGui = createWorldGui;
		if (infoString.equals(""))
			worldGeneratorInfo = BTAWorldConfigurationInfo.createDefaultConfiguration(this.createWorldGui.isDeco());
		else
			worldGeneratorInfo = BTAWorldConfigurationInfo.createInfoFromString(infoString);
	}

	public String getGeneratorInfo()
	{
		return this.worldGeneratorInfo.toString();
	}

	public void setGeneratorInfo(String infoString)
	{
		this.worldGeneratorInfo = BTAWorldConfigurationInfo.createInfoFromString(infoString);
	}

	static BTAWorldConfigurationInfo getBiomeArray(BTAGuiGeneratorOptions guiGeneratorOptions)
	{
		return guiGeneratorOptions.worldGeneratorInfo;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui()
	{
		this.buttonList.clear();
		this.guiBiomeOptionList = new BTAGuiBiomeOptionList(this);
		this.buttonList.add(new GuiButton(id_done, this.width / 2 - 155, this.height - 28, 100, 20, StatCollector.translateToLocal("gui.done")));
		this.buttonList.add(this.buttonPerlinBeaches = new GuiButton(id_perlin, this.width / 2 + 55, this.height - 28, 100, 20, "Better Shores: On"));
		this.buttonList.add(this.buttonEnable = new GuiButton(id_enable, this.width / 2 - 155, this.height - 52, 100, 20, "Enable/Disable"));
		this.buttonList.add(this.buttonAll = new GuiButton(id_enableAll, this.width / 2 - 50, this.height - 52, 100, 20, "Disable All"));
		//this.buttonList.add(this.sliderOceanSize = new BTAGuiSlider(4, this.width / 2 + 55, this.height - 52, 100, 20, "Ocean Size", 1.0F));
		this.buttonList.add(this.buttonOceans = new GuiButton(4, this.width / 2 + 55, this.height - 52, 100, 20, "Oceans: On"));
		this.buttonList.add(this.buttonBiomeSize = new GuiButton(5, this.width / 2 - 50, this.height - 28, 100, 20, "Biome Size: BTA"));
		this.setButtons();
		this.updateButtons();
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	 */
	protected void actionPerformed(GuiButton button) {
		int var2 = this.worldGeneratorInfo.getBiomeInfoList().size() - this.guiBiomeOptionList.selected - 1;
		BTABiomeInfo selectedBiome;

		if (button.id == id_done) {
			if (!isDeco()) {
				for (BTABiomeInfo b : this.worldGeneratorInfo.getBiomeInfoList()) {
					if (b.isDecoOnly()) {
						b.setEnabled(false);
					}
				}
			}
			
			this.createWorldGui.generatorOptionsToUse = this.getGeneratorInfo();
			this.mc.displayGuiScreen(this.createWorldGui);
		}
		else if (button.id == id_perlin) {
			this.worldGeneratorInfo.setGeneratePerlinBeaches(!this.worldGeneratorInfo.generatePerlinBeaches());
			this.updateButtons();
		}
		else if (button.id == id_enable) {
			selectedBiome = (BTABiomeInfo)this.worldGeneratorInfo.getBiomeInfoList().get(this.worldGeneratorInfo.getBiomeInfoList().size() - this.guiBiomeOptionList.selected - 1);

			if (selectedBiome.getEnabled()) {
				selectedBiome.setEnabled(false);
			}
			else {
				selectedBiome.setEnabled(true);
			}
		}
		else if (button.id == id_enableAll) {
			if (!this.all) {
				for (int i = 0; i < this.worldGeneratorInfo.getBiomeInfoList().size(); ++i) {
					selectedBiome = (BTABiomeInfo)this.worldGeneratorInfo.getBiomeInfoList().get(i);
					selectedBiome.setEnabled(false);
				}

				this.all = true;
			}
			else {
				for (int i = 0; i < this.worldGeneratorInfo.getBiomeInfoList().size(); ++i) {
					selectedBiome = (BTABiomeInfo)this.worldGeneratorInfo.getBiomeInfoList().get(i);
					selectedBiome.setEnabled(true);
				}

				this.all = false;
			}

			this.updateButtons();
		}
		else if (button.id == id_oceans) {
			int oceanSize = this.worldGeneratorInfo.getOceanSize();
			this.worldGeneratorInfo.setOceanSize(oceanSize == 10 ? 1 : 10);
			this.updateButtons();
		}
		else if (button.id == id_smallBiomes) {
			createWorldGui.setSmall(!createWorldGui.isSmall());
			this.updateButtons();
		}
	}


	public void updateButtons() {
		StringTranslate var1 = StringTranslate.getInstance();

		if (this.all) {
			this.buttonAll.displayString = "Enable All";
		}
		else {
			this.buttonAll.displayString = "Disable All";
		}

		if (this.worldGeneratorInfo.generatePerlinBeaches()) {
			this.buttonPerlinBeaches.displayString = "Better Shores: On";
		}
		else {
			this.buttonPerlinBeaches.displayString = "Better Shores: Off";
		}

		if (createWorldGui.isSmall()) {
			this.buttonBiomeSize.displayString = "Biome Size: Vanilla";
		}
		else {
			this.buttonBiomeSize.displayString = "Biome Size: BTA";
		}
		
		if (this.worldGeneratorInfo.getOceanSize() == 10 && WorldType.worldTypes[this.createWorldGui.getWorldTypeId()].hasOceans()) {
			this.buttonOceans.displayString = "Oceans: On";
		}
		else {
			this.buttonOceans.displayString = "Oceans: Off";
		}
	}

	public void setButtons() {
		this.buttonEnable.enabled = this.allowBiomeToggle();
		
		if (!WorldType.worldTypes[this.createWorldGui.getWorldTypeId()].hasOceans()) {
			this.buttonOceans.enabled = false;
			this.worldGeneratorInfo.setOceanSize(1);
		}
		
		if (!WorldType.worldTypes[this.createWorldGui.getWorldTypeId()].canPerlinBeachesBeToggled()) {
			this.buttonPerlinBeaches.enabled = false;
			this.worldGeneratorInfo.setGeneratePerlinBeaches(WorldType.worldTypes[this.createWorldGui.getWorldTypeId()].getDefaultPerlinBeachState());
		}
		
		this.updateButtons();
	}

	private boolean allowBiomeToggle() {
		return this.guiBiomeOptionList.selected > -1 && 
				this.guiBiomeOptionList.selected < this.worldGeneratorInfo.getBiomeInfoList().size() &&
				!(this.worldGeneratorInfo.getBiomeInfoList().get(this.worldGeneratorInfo.getBiomeInfoList().size() - this.guiBiomeOptionList.selected - 1).isDecoOnly() && !this.isDeco());
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();

		this.guiBiomeOptionList.drawScreen(var1, var2, var3);
		this.drawCenteredString(this.fontRenderer, "Biome List", this.width / 2, 8, 16777215);
		this.drawCenteredString(this.fontRenderer, "Biome Name", this.width / 2 - 80, 32, 16777215);
		this.drawCenteredString(this.fontRenderer, "Enabled", this.width / 2 + 80, 32, 16777215);

		super.drawScreen(var1, var2, var3);
	}

	public boolean isDeco() {
		return this.createWorldGui.isDeco();
	}
}
