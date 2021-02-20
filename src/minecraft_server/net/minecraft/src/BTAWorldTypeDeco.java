package net.minecraft.src;

public class BTAWorldTypeDeco extends WorldType {
	public BTAWorldTypeDeco() {
		super(5, "BTADeco");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Deco Terrain";
    }
}