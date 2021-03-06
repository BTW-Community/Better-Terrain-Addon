package net.minecraft.src;

public class BTAWorldTypeHorizons extends WorldType {
    protected BTAWorldTypeHorizons(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Horizons";
    }
    
    public WorldChunkManager getChunkManager(World world) {
    	return new BTAHorizonsWorldChunkManager(world);
    }
    
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled) {
    	return new BTAHorizonsChunkProvider(world, seed, mapFeaturesEnabled);
    }
    
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new BTAChunkProviderEnd(world, seed);
    }
    
    public float getCloudHeight() {
    	return 256F;
    }
    
    public int getAverageGroundLevel() {
    	return 128;
    }
    
    public double getHorizon() {
    	return 128F;
    }
    
    public int[] getStrataLevels() {
    	return new int[] {96, 48, 24};
    }
}