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
    	
    	return new BTASkyChunkProvider(world, seed, mapFeaturesEnabled, info);
    }

    @Override
    public IChunkProvider getChunkProviderNether(World world, long seed) {
    	return new BTASkyChunkProvider(world, seed, true, BTAWorldConfigurationInfo.createDefaultConfiguration(this.isDeco())).setNether();
    }

    @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new BTAChunkProviderEnd(world, seed);
    }

    @Override
    public float getCloudHeight() {
    	return -8F;
    }

    @Override
    public int getAverageGroundLevel() {
    	return 16;
    }

    @Override
    public double getHorizon() {
    	return 0F;
    }

    @Override
    public int[] getStrataLevels() {
    	return new int[] {48, 32};
    }

    @Override
    public boolean isSky() {
    	return true;
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
    	return false;
    }
}