package net.minecraft.src;

public class BTAWorldType extends WorldType {
    protected BTAWorldType(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Better Terrain";
    }
    
    public WorldChunkManager getChunkManager(World world) {
    	return new BTAWorldChunkManager(world);
    }
    
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled) {
    	return new BTAChunkProvider(world, seed, mapFeaturesEnabled);
    }
}