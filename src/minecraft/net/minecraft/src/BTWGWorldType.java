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
        return "BTWG";
    }
    
    public IChunkProvider getDefaultChunkProvider(World par1World, long par2, boolean par4) {
    	return new BTWGChunkProvider(par1World, par2, par4);
    }
}