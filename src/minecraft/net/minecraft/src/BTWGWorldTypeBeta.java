package net.minecraft.src;

public class BTWGWorldTypeBeta extends WorldType {
	public BTWGWorldTypeBeta() {
		super(6, "Beta");
	}

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Beta";
    }
    
    public IChunkProvider getChunkProvider(World par1World, long par2, boolean par4) {
    	return new BTWGChunkProviderBeta(par1World, par2, par4);
    }
}