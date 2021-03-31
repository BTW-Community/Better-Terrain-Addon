package net.minecraft.src;

public class BTAGuiGeneratorOptions extends GuiScreen implements BTAISliderSettingHandler
{
	private final GuiCreateWorld guiCreateWorld;
	public BTAWorldConfigurationInfo worldGeneratorInfo;
	
	private GuiButton buttonDone;
	private GuiButton buttonDeco;
	private BTAGuiSlider sliderBiomeSize;
	private BTAGuiSlider sliderOceanSize;
	private GuiButton buttonOceanSizeDummy;
	private GuiButton buttonPerlinBeaches;
	private GuiButton buttonClimates;
	private GuiButton buttonBiomeConfig;
	
	private final int id_done = 0;
	private final int id_deco = 1;
	private final int id_biomeSize = 2;
	private final int id_oceanSize = 3;
	private final int id_perlinBeaches = 4;
	private final int id_climates = 5;
	private final int id_biomeConfig = 6;
	
	private final int id_oceanDummy = 999;

	public BTAGuiGeneratorOptions(GuiCreateWorld createWorldGui, String infoString) {
		this.guiCreateWorld = createWorldGui;
		if (infoString.equals(""))
			worldGeneratorInfo = BTAWorldConfigurationInfo.createDefaultConfiguration(this.guiCreateWorld.isDeco());
		else
			worldGeneratorInfo = BTAWorldConfigurationInfo.createInfoFromString(infoString);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(this.sliderOceanSize = new BTAGuiSlider(id_oceanSize, this.width / 2 - 155, this.height / 6, 150, 20, "Ocean Size", 4, this, 10));
		this.buttonList.add(this.buttonOceanSizeDummy = new GuiButton(id_oceanSize, this.width / 2 - 155, this.height / 6, 150, 20, "Ocean Size: Off"));
		this.buttonList.add(this.sliderBiomeSize = new BTAGuiSlider(id_biomeSize, this.width / 2 + 5, this.height / 6, 150, 20, "Biome Size", 1, this, 4));
		this.buttonList.add(this.buttonPerlinBeaches = new GuiButton(id_perlinBeaches, this.width / 2 - 155, this.height / 6 + 25, 150, 20, "Better Shores: On"));
		this.buttonList.add(this.buttonClimates = new GuiButton(id_climates, this.width / 2 + 5, this.height / 6 + 25, 150, 20, "Climate Zones: On"));
		this.buttonList.add(this.buttonDeco = new GuiButton(id_deco, this.width / 2 - 155, this.height / 6 + 50, 150, 20, "Deco Enabled: Off"));
		this.buttonList.add(this.buttonBiomeConfig = new GuiButton(id_biomeConfig, this.width / 2 + 5, this.height / 6 + 50, 150, 20, "Configure Biomes"));
		this.buttonList.add(this.buttonDone = new GuiButton(id_done, this.width / 2 - 75, this.height / 6 + 80, 150, 20, StatCollector.translateToLocal("gui.done")));
		this.setButtons();
	}

	public String getGeneratorInfo() {
		return this.worldGeneratorInfo.toString();
	}

	public void setGeneratorInfo(String infoString) {
		this.worldGeneratorInfo = BTAWorldConfigurationInfo.createInfoFromString(infoString);
	}

	public static BTAWorldConfigurationInfo getBiomeArray(BTAGuiGeneratorOptions guiGeneratorOptions) {
		return guiGeneratorOptions.worldGeneratorInfo;
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	 */
	protected void actionPerformed(GuiButton button) {
		if (button.id == id_done) {
			if (!isDeco()) {
				for (BTABiomeInfo b : this.worldGeneratorInfo.getBiomeInfoList()) {
					if (b.isDecoOnly()) {
						b.setEnabled(false);
					}
				}
			}
			
			this.guiCreateWorld.generatorOptionsToUse = this.getGeneratorInfo();
			this.mc.displayGuiScreen(this.guiCreateWorld);
		}
		else if (button.id == id_deco) {
			this.guiCreateWorld.setDeco(!this.isDeco());
			this.updateButtons();
		}
		else if (button.id == id_perlinBeaches) {
			this.worldGeneratorInfo.setGeneratePerlinBeaches(!this.worldGeneratorInfo.generatePerlinBeaches());
			this.updateButtons();
		}
		else if (button.id == id_climates) {
			this.worldGeneratorInfo.setClimatized(!this.worldGeneratorInfo.isClimatized());
			this.updateButtons();
		}
		else if (button.id == id_biomeConfig) {
			this.mc.displayGuiScreen(new BTAGuiBiomeOptions(this.guiCreateWorld, this, this.getGeneratorInfo()));
		}
	}


	public void updateButtons() {
        if (this.isDeco()) {
        	this.buttonDeco.displayString = "Deco Enabled: On";
        }
        else {
        	this.buttonDeco.displayString = "Deco Enabled: Off";
        }
        
		if (this.worldGeneratorInfo.generatePerlinBeaches()) {
			this.buttonPerlinBeaches.displayString = "Better Shores: On";
		}
		else {
			this.buttonPerlinBeaches.displayString = "Better Shores: Off";
		}
		
		if (this.worldGeneratorInfo.isClimatized()) {
			this.buttonClimates.displayString = "Climate Zones: On";
		}
		else {
			this.buttonClimates.displayString = "Climate Zones: Off";
		}
	}

	public void setButtons() {
        this.buttonDeco.enabled = BTADecoIntegration.isDecoInstalled();
		
		if (!WorldType.worldTypes[this.guiCreateWorld.getWorldTypeId()].canPerlinBeachesBeToggled()) {
			this.buttonPerlinBeaches.enabled = false;
			this.worldGeneratorInfo.setGeneratePerlinBeaches(WorldType.worldTypes[this.guiCreateWorld.getWorldTypeId()].getDefaultPerlinBeachState());
		}
		
		if (!WorldType.worldTypes[this.guiCreateWorld.getWorldTypeId()].hasOceans()) {
			this.buttonOceanSizeDummy.enabled = false;
			this.buttonOceanSizeDummy.drawButton = true;
			this.sliderOceanSize.drawButton = false;
			this.worldGeneratorInfo.setOceanSize(1);
		}
		else {
			this.buttonOceanSizeDummy.drawButton = false;
		}
        
		this.updateButtons();
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int var1, int var2, float var3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Better Terrain Customization", this.width / 2, 20, 16777215);

		super.drawScreen(var1, var2, var3);
	}

	@Override
	public void handleSetting(BTAGuiSlider slider) {
		if (slider.id == id_oceanSize) {
			int setting;
			
			if (slider.sliderValue == 0) {
				setting = 0;
			}
			else if (slider.sliderValue == 1) {
				setting = slider.numSettings - 1;
			}
			else {
				setting = (int) (slider.sliderValue * (slider.numSettings - 2) + 1); 
			}
			
			this.worldGeneratorInfo.setOceanSize(setting + 1);
		}
		else if (slider.id == id_biomeSize) {
			int setting;
			
			if (slider.sliderValue == 0) {
				setting = 0;
			}
			else if (slider.sliderValue == 1) {
				setting = slider.numSettings - 1;
			}
			else {
				setting = (int) (slider.sliderValue * (slider.numSettings - 2) + 1); 
			}
			
			this.worldGeneratorInfo.setBiomeSize(setting);
		}
	}

	@Override
	public void updateText(BTAGuiSlider slider) {
		if (slider.id == id_oceanSize) {
			int setting;
			
			if (slider.sliderValue == 0) {
				setting = 0;
			}
			else if (slider.sliderValue == 1) {
				setting = slider.numSettings - 1;
			}
			else {
				setting = (int) (slider.sliderValue * (slider.numSettings - 2) + 1); 
			}
			
			String displaySetting = Integer.toString(setting + 1);
			
			switch (setting) {
			case 0:
				displaySetting = "Off";
				break;
			case 9:
				displaySetting = "Vanilla";
				break;
			}
			
			slider.displayString = "Ocean Size: " + displaySetting;
		}
		else if (slider.id == id_biomeSize) {
			int setting;
			
			if (slider.sliderValue == 0) {
				setting = 0;
			}
			else if (slider.sliderValue == 1) {
				setting = slider.numSettings - 1;
			}
			else {
				setting = (int) (slider.sliderValue * (slider.numSettings - 2) + 1); 
			}
			
			String displaySetting = "";
			
			switch (setting) {
			case 0:
				displaySetting = "0.5x";
				break;
			case 1:
				displaySetting = "Vanilla";
				break;
			case 2:
				displaySetting = "2x";
				break;
			case 3:
				displaySetting = "4x";
				break;
			}
			
			slider.displayString = "Biome Size: " + displaySetting;
		}
	}

	public boolean isDeco() {
		return this.guiCreateWorld.isDeco();
	}
}
