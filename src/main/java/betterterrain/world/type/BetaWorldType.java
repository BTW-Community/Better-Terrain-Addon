package betterterrain.world.type;

import betterterrain.world.BetaChunkManager;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.config.WorldConfigurationInfoLegacy;
import betterterrain.world.generate.provider.BetaChunkProvider;
import betterterrain.world.generate.provider.EndChunkProvider;
import betterterrain.world.generate.provider.NetherChunkProvider;
import betterterrain.world.util.WorldTypeInterface;
import net.minecraft.src.*;

public class BetaWorldType extends WorldType {
    public BetaWorldType(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Beta";
    }

	// @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(((WorldTypeInterface) this).isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BetaChunkManager(world, info);
    }

	// @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(((WorldTypeInterface) this).isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BetaChunkProvider(world, seed, mapFeaturesEnabled, info);
    }

	// @Override
    public IChunkProvider getChunkProviderNether(World world, long seed, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(((WorldTypeInterface) this).isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	if (((WorldTypeInterface) this).isDeco()) {
    		return new NetherChunkProvider(world, seed, true, info);
    	}
    	else {
    		return new ChunkProviderHell(world, seed);
    	}
    }

	// @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new EndChunkProvider(world, seed);
    }

	// @Override
    public boolean hasDeco() {
    	return true;
    }

    // @Override
    public boolean isBTA() {
    	return true;
    }

	// @Override
    public boolean hasOceans() {
    	return false;
    }

	// @Override
    public boolean canPerlinBeachesBeToggled() {
    	return false;
    }

	// @Override
    public boolean getDefaultPerlinBeachState() {
    	return true;
    }
}