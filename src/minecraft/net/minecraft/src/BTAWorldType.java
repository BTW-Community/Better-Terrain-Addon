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

    @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	return new BTAWorldChunkManager(world);
    }

    @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
    	return new BTAChunkProvider(world, seed, mapFeaturesEnabled);
    }

    @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new BTAChunkProviderEnd(world, seed);
    }
    
    @Override
    public boolean hasDeco() {
    	return true;
    }

    @Override
    public boolean isBTA() {
    	return true;
    }
}