package betterterrain.gui;

import net.minecraft.src.GuiScreen;

public class AdvancedWorldConfigGui extends GuiScreen {
    private GeneratorOptionsGui optionsGui;

    public AdvancedWorldConfigGui(GeneratorOptionsGui optionsGui) {
        this.optionsGui = optionsGui;
    }

    public void initGui() {
        this.buttonList.clear();

    }
}
