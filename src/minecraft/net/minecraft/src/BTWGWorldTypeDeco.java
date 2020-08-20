package net.minecraft.src;

public class BTWGWorldTypeDeco extends WorldType {
	public BTWGWorldTypeDeco() {
		super(5, "BTWGDeco");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "BTWG Deco";
    }
}