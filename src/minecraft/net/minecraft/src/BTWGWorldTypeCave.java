package net.minecraft.src;

public class BTWGWorldTypeCave extends WorldType {
	public BTWGWorldTypeCave() {
		super(5, "BTWGCave");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "BTWG Cave";
    }
    
    public IChunkProvider getChunkProvider(World par1World, long par2, boolean par4) {
    	return new BTWGChunkProviderCave(par1World, par2, par4);
    }
}