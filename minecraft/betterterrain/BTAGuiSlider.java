package betterterrain;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;

import org.lwjgl.opengl.GL11;

public class BTAGuiSlider extends GuiButton
{
    /** The value of this slider control. */
    public float sliderValue = 1.0F;

    /** Is this slider control being dragged. */
    public boolean dragging = false;
    
    private final BTAISliderSettingHandler handler;
    
    public final int numSettings;

    public BTAGuiSlider(int id, int x, int y, int sizeX, int sizeY, String displayString, float startingValue, BTAISliderSettingHandler handler, int numSettings)
    {
        super(id, x, y, sizeX, sizeY, displayString);
        this.sliderValue = startingValue;
        this.handler = handler;
        this.numSettings = numSettings;

        handler.handleSetting(this);
        handler.updateText(this);
    }

    public BTAGuiSlider(int id, int x, int y, int sizeX, int sizeY, String displayString, int startingValue, BTAISliderSettingHandler handler, int numSettings)
    {
        super(id, x, y, sizeX, sizeY, displayString);
        this.sliderValue = ((float) startingValue) / (numSettings - 1);
        this.handler = handler;
        this.numSettings = numSettings;

        handler.handleSetting(this);
        handler.updateText(this);
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean par1)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int x, int y)
    {
        if (this.drawButton)
        {
            if (this.dragging)
            {
                this.sliderValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);
                
                this.sliderValue = ((int) (this.sliderValue * this.numSettings)) / (float) (this.numSettings - 1);

                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }

                handler.handleSetting(this);
                handler.updateText(this);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int x, int y)
    {
        if (super.mousePressed(mc, x, y))
        {
            this.sliderValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);
            
            this.sliderValue = ((int) (this.sliderValue * this.numSettings)) / (float) (this.numSettings - 1);

            if (this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }

            handler.handleSetting(this);
            handler.updateText(this);
            
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int x, int y)
    {
        this.dragging = false;
    }

	public int getNumSettings() {
		return numSettings;
	}
}
