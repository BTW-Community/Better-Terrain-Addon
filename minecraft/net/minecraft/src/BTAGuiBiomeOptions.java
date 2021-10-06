package net.minecraft.src;

public class BTAGuiBiomeOptions extends GuiScreen
{
	public final BTAGuiGeneratorOptions guiGeneratorOptions;
	private final GuiCreateWorld guiCreateWorld;
	private BTAGuiBiomeOptionList guiBiomeOptionList;
	private GuiButton buttonPerlinBeaches;
	private GuiButton buttonEnable;
	private GuiButton buttonAll;
	private GuiButton buttonOceans;
	private BTAGuiSlider sliderOceanSize;
	private GuiButton buttonBiomeSize;
	private boolean all = false;
	private BTABiomeInfo biome;

	private static final int id_done = 0;
	private static final int id_enable = 1;
	private static final int id_enableAll = 2;

	public BTAGuiBiomeOptions(GuiCreateWorld guiCreateWorld, BTAGuiGeneratorOptions guiGeneratorOptions, String infoString) {
		this.guiGeneratorOptions = guiGeneratorOptions;
		this.guiCreateWorld = guiCreateWorld;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		this.buttonList.clear();
		this.guiBiomeOptionList = new BTAGuiBiomeOptionList(this);
		this.buttonList.add(new GuiButton(id_done, this.width / 2 - 75, this.height - 28, 150, 20, StatCollector.translateToLocal("gui.done")));
		this.buttonList.add(this.buttonEnable = new GuiButton(id_enable, this.width / 2 - 155, this.height - 52, 150, 20, "Enable/Disable"));
		this.buttonList.add(this.buttonAll = new GuiButton(id_enableAll, this.width / 2 + 5, this.height - 52, 150, 20, "Disable All"));
		this.setButtons();
		this.updateButtons();
	}

	public String getGeneratorInfo() {
		return guiGeneratorOptions.worldGeneratorInfo.toString();
	}

	public void setGeneratorInfo(String infoString) {
		guiGeneratorOptions.worldGeneratorInfo = BTAWorldConfigurationInfo.createInfoFromString(infoString);
	}

	static BTAWorldConfigurationInfo getBiomeArray(BTAGuiGeneratorOptions guiGeneratorOptions) {
		return guiGeneratorOptions.worldGeneratorInfo;
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	 */
	protected void actionPerformed(GuiButton button) {
		BTABiomeInfo selectedBiome;

		if (button.id == id_done) {
			this.mc.displayGuiScreen(this.guiGeneratorOptions);
		}
		else if (button.id == id_enable) {
			selectedBiome = guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().get(guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().size() - this.guiBiomeOptionList.selected - 1);

			if (selectedBiome.getEnabled()) {
				selectedBiome.setEnabled(false);
			}
			else {
				selectedBiome.setEnabled(true);
			}
		}
		else if (button.id == id_enableAll) {
			if (!this.all) {
				for (int i = 0; i < guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().size(); ++i) {
					selectedBiome = guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().get(i);
					selectedBiome.setEnabled(false);
				}

				this.all = true;
			}
			else {
				for (int i = 0; i < guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().size(); ++i) {
					selectedBiome = guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().get(i);
					selectedBiome.setEnabled(true);
				}

				this.all = false;
			}

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
	}

	public void setButtons() {
		this.buttonEnable.enabled = this.allowBiomeToggle();
		
		this.updateButtons();
	}

	private boolean allowBiomeToggle() {
		return this.guiBiomeOptionList.selected > -1 && 
				this.guiBiomeOptionList.selected < guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().size() &&
				!(guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().get(guiGeneratorOptions.worldGeneratorInfo.getBiomeInfoList().size() - this.guiBiomeOptionList.selected - 1).isDecoOnly() && !this.isDeco());
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
		return this.guiCreateWorld.isDeco();
	}
}
