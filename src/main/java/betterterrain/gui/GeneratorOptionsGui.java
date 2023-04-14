package betterterrain.gui;

import betterterrain.BTAMod;
import betterterrain.biome.BiomeInfo;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.*;

public class GeneratorOptionsGui extends GuiScreen implements SliderSettingHandler
{
	private final GuiCreateWorld guiCreateWorld;
	public WorldConfigurationInfo worldGeneratorInfo;
	
	private GuiButton doneButton;
	private GuiButton decoButton;
	private Slider biomeSizeSlider;
	private Slider oceanSizeSlider;
	private GuiButton oceanSizeButtonDummy;
	private GuiButton betterBeachesButton;
	private GuiButton gravelBeachesButton;
	private GuiButton climatesButton;
	private GuiButton biomeConfigButton;
	private GuiButton riverButton;
	private GuiButton advancedConfigButton;
	
	private final int DONE_ID = 0;
	private final int DECO_ID = 1;
	private final int BIOME_SIZE_ID = 2;
	private final int OCEAN_SIZE_ID = 3;
	private final int BETTER_BEACHES_ID = 4;
	private final int CLIMATES_ID = 5;
	private final int BIOME_LIST_ID = 6;
	private final int RIVER_ID = 7;
	private final int GRAVEL_BEACHES_ID = 8;
	private final int ADVANCED_SETTINGS_ID = 9;
	
	private final int DUMMY_OCEAN_ID = 999;

	public GeneratorOptionsGui(GuiCreateWorld createWorldGui, String infoString) {
		this.guiCreateWorld = createWorldGui;
		if (infoString.equals(""))
			worldGeneratorInfo = WorldConfigurationInfo.createDefaultConfiguration(((GuiCreateWorldInterface) this.guiCreateWorld).isDeco());
		else
			worldGeneratorInfo = WorldConfigurationInfo.createInfoFromString(infoString);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(this.oceanSizeSlider = new Slider(OCEAN_SIZE_ID, this.width / 2 - 155, this.height / 6, 150, 20, "Ocean Size", this.worldGeneratorInfo.getOceanSize() - 1, this, 10));
		this.buttonList.add(this.oceanSizeButtonDummy = new GuiButton(DUMMY_OCEAN_ID, this.width / 2 - 155, this.height / 6, 150, 20, "Ocean Size: Off"));
		this.buttonList.add(this.biomeSizeSlider = new Slider(BIOME_SIZE_ID, this.width / 2 + 5, this.height / 6, 150, 20, "Biome Size", this.worldGeneratorInfo.getBiomeSize(), this, 4));
		
		this.buttonList.add(this.betterBeachesButton = new GuiButton(BETTER_BEACHES_ID, this.width / 2 - 155, this.height / 6 + 25, 150, 20, "Better Shores: On"));
		this.buttonList.add(this.gravelBeachesButton = new GuiButton(GRAVEL_BEACHES_ID, this.width / 2 + 5, this.height / 6 + 25, 150, 20, "Gravel Beaches: On"));

		this.buttonList.add(this.riverButton = new GuiButton(RIVER_ID, this.width / 2 - 155, this.height / 6 + 50, 150, 20, "Larger Rivers: On"));
		this.buttonList.add(this.climatesButton = new GuiButton(CLIMATES_ID, this.width / 2 + 5, this.height / 6 + 50, 150, 20, "Climate Zones: On"));
		
		this.buttonList.add(this.biomeConfigButton = new GuiButton(BIOME_LIST_ID, this.width / 2 - 155, this.height / 6 + 75, 150, 20, "Configure Biomes"));
		this.buttonList.add(this.decoButton = new GuiButton(DECO_ID, this.width / 2 + 5, this.height / 6 + 75, 150, 20, "Deco Enabled: Off"));

		//this.buttonList.add(this.advancedConfigButton = new GuiButton(ADVANCED_SETTINGS_ID, this.width / 2 - 75, this.height / 6 + 100, 150, 20, "Advanced Settings"));

		this.buttonList.add(this.doneButton = new GuiButton(DONE_ID, this.width / 2 - 75, this.height / 6 + 105, 150, 20, StatCollector.translateToLocal("gui.done")));
		this.setButtons();
	}

	public String getGeneratorInfo() {
		return this.worldGeneratorInfo.toString();
	}

	public void setGeneratorInfo(String infoString) {
		this.worldGeneratorInfo = WorldConfigurationInfo.createInfoFromString(infoString);
	}

	public static WorldConfigurationInfo getBiomeArray(GeneratorOptionsGui guiGeneratorOptions) {
		return guiGeneratorOptions.worldGeneratorInfo;
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	 */
	protected void actionPerformed(GuiButton button) {
		if (button.id == DONE_ID) {
			if (!isDeco()) {
				for (BiomeInfo b : this.worldGeneratorInfo.getBiomeInfoList()) {
					if (b.isDecoOnly()) {
						b.setEnabled(false);
					}
				}
			}
			
			this.guiCreateWorld.generatorOptionsToUse = this.getGeneratorInfo();
			this.mc.displayGuiScreen(this.guiCreateWorld);
		}
		else if (button.id == ADVANCED_SETTINGS_ID) {
			this.mc.displayGuiScreen(new AdvancedWorldConfigGui(this));
		}
		else if (button.id == DECO_ID) {
			((GuiCreateWorldInterface) this.guiCreateWorld).setDeco(!this.isDeco());
			
			for (BiomeInfo b : this.worldGeneratorInfo.getBiomeInfoList()) {
				if (b.isDecoOnly()) {
					b.setEnabled(true);
				}
			}
			
			this.updateButtons();
		}
		else if (button.id == BETTER_BEACHES_ID) {
			this.worldGeneratorInfo.setGeneratePerlinBeaches(!this.worldGeneratorInfo.generatePerlinBeaches());
			this.gravelBeachesButton.enabled = this.worldGeneratorInfo.generatePerlinBeaches();
			this.updateButtons();
		}
		else if (button.id == GRAVEL_BEACHES_ID) {
			this.worldGeneratorInfo.setGenerateGravelBeaches(!this.worldGeneratorInfo.generateGravelBeaches());
			this.updateButtons();
		}
		else if (button.id == RIVER_ID) {
			this.worldGeneratorInfo.setWideRivers(!this.worldGeneratorInfo.hasWideRivers());
			this.updateButtons();
		}
		else if (button.id == CLIMATES_ID) {
			this.worldGeneratorInfo.setClimatized(!this.worldGeneratorInfo.isClimatized());
			this.updateButtons();
		}
		else if (button.id == BIOME_LIST_ID) {
			this.mc.displayGuiScreen(new BiomeOptionsGui(this.guiCreateWorld, this, this.getGeneratorInfo()));
		}
	}


	public void updateButtons() {
        if (this.isDeco()) {
        	this.decoButton.displayString = "Deco Enabled: On";
        }
        else {
        	this.decoButton.displayString = "Deco Enabled: Off";
        }
        
		if (this.worldGeneratorInfo.generatePerlinBeaches()) {
			this.betterBeachesButton.displayString = "Better Shores: On";
		}
		else {
			this.betterBeachesButton.displayString = "Better Shores: Off";
		}

		if (this.worldGeneratorInfo.generateGravelBeaches() && this.worldGeneratorInfo.generatePerlinBeaches()) {
			this.gravelBeachesButton.displayString = "Gravel Beaches: On";
		}
		else {
			this.gravelBeachesButton.displayString = "Gravel Beaches: Off";
		}
		
		if (this.worldGeneratorInfo.hasWideRivers()) {
			this.riverButton.displayString = "Larger Rivers: On";
		}
		else {
			this.riverButton.displayString = "Larger Rivers: Off";
		}
		
		if (this.worldGeneratorInfo.isClimatized()) {
			this.climatesButton.displayString = "Climate Zones: On";
		}
		else {
			this.climatesButton.displayString = "Climate Zones: Off";
		}
	}

	public void setButtons() {
        this.decoButton.enabled = BTAMod.isDecoInstalled();
		
		if (!((WorldTypeInterface) WorldType.worldTypes[((GuiCreateWorldInterface) this.guiCreateWorld).getWorldTypeId()]).canPerlinBeachesBeToggled()) {
			this.betterBeachesButton.enabled = false;
			this.worldGeneratorInfo.setGeneratePerlinBeaches(((WorldTypeInterface) WorldType.worldTypes[((GuiCreateWorldInterface) this.guiCreateWorld).getWorldTypeId()]).getDefaultPerlinBeachState());
		}
		
		if (!((WorldTypeInterface) WorldType.worldTypes[((GuiCreateWorldInterface) this.guiCreateWorld).getWorldTypeId()]).hasOceans()) {
			this.oceanSizeButtonDummy.enabled = false;
			this.oceanSizeButtonDummy.drawButton = true;
			this.oceanSizeSlider.drawButton = false;
			this.worldGeneratorInfo.setOceanSize(1);
		}
		else {
			this.oceanSizeButtonDummy.drawButton = false;
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
	public void handleSetting(Slider slider) {
		if (slider.id == OCEAN_SIZE_ID) {
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
		else if (slider.id == BIOME_SIZE_ID) {
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
	public void updateText(Slider slider) {
		if (slider.id == OCEAN_SIZE_ID) {
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
		else if (slider.id == BIOME_SIZE_ID) {
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
		return ((GuiCreateWorldInterface) this.guiCreateWorld).isDeco();
	}
}
