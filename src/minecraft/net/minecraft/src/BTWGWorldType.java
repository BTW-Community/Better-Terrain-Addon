package net.minecraft.src;

public class BTWGWorldType extends WorldType {
	public BTWGWorldType() {
		super(4, "BTWG");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Better Than World Gen";
    }
}