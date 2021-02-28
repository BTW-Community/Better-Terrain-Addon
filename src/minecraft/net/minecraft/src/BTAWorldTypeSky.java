package net.minecraft.src;

public class BTAWorldTypeSky extends WorldType {
	public BTAWorldTypeSky() {
		super(9, "BTASky");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Skylands";
    }
}