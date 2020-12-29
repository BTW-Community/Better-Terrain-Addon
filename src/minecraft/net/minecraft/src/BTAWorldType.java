package net.minecraft.src;

public class BTAWorldType extends WorldType {
	public BTAWorldType() {
		super(4, "BTA");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Better Terrain";
    }
}