package net.minecraft.src;

public class BTAWorldTypeSkyDeco extends WorldType {
	public BTAWorldTypeSkyDeco() {
		super(10, "BTASkyDeco");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Skylands Deco";
    }
}