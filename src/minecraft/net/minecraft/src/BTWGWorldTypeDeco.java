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
    
    public IChunkProvider getChunkProvider(World par1World, long par2, boolean par4) {
    	return new BTWGChunkProvider(par1World, par2, par4);
    }
}