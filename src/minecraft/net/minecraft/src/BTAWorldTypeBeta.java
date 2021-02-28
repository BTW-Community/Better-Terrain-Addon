package net.minecraft.src;

public class BTAWorldTypeBeta extends WorldType {
	public BTAWorldTypeBeta() {
		super(5, "BTABeta");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Beta";
    }
}