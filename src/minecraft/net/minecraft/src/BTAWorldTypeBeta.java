package net.minecraft.src;

public class BTAWorldTypeBeta extends WorldType {
    protected BTAWorldTypeBeta(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Beta";
    }
    
    public WorldChunkManager getChunkManager(World world) {
    	return new BTABetaChunkManager(world);
    }
    
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled) {
    	return new BTABetaChunkProvider(world, seed, mapFeaturesEnabled);
    }
}