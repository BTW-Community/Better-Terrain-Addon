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
    
    @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	BTAWorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = BTAWorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = BTAWorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BTABetaChunkManager(world, info);
    }

    @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
    	BTAWorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = BTAWorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = BTAWorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BTABetaChunkProvider(world, seed, mapFeaturesEnabled, info);
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
    
    public boolean hasOceans() {
    	return false;
    }
    
    public boolean canPerlinBeachesBeToggled() {
    	return false;
    }
    
    public boolean getDefaultPerlinBeachState() {
    	return true;
    }
}