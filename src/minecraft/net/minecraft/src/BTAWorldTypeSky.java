package net.minecraft.src;

public class BTAWorldTypeSky extends WorldType {
    protected BTAWorldTypeSky(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Skylands";
    }
    
    public WorldChunkManager getChunkManager(World world) {
    	return new BTABetaChunkManager(world);
    }
    
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled) {
    	return new BTASkyChunkProvider(world, seed, mapFeaturesEnabled);
    }
    
    public IChunkProvider getChunkProviderNether(World world, long seed) {
    	return new BTASkyChunkProvider(world, seed, true).setNether();
    }
    
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new BTAChunkProviderEnd(world, seed);
    }
    
    public float getCloudHeight() {
    	return 8F;
    }
    
    public int getAverageGroundLevel() {
    	return 16;
    }
    
    public double getHorizon() {
    	return 0F;
    }
    
    public int[] getStrataLevels() {
    	return new int[] {48, 32};
    }
    
    public boolean isSky() {
    	return true;
    }
    
    public boolean hasDeco() {
    	return true;
    }
}