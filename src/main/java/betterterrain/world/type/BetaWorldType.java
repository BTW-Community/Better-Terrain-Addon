package betterterrain.world.type;

import betterterrain.world.BetaChunkManager;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.config.WorldConfigurationInfoLegacy;
import betterterrain.world.generate.BetaChunkProvider;
import betterterrain.world.generate.EndChunkProvider;
import betterterrain.world.generate.NetherChunkProvider;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;

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
    
    @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BetaChunkManager(world, info);
    }

    @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BetaChunkProvider(world, seed, mapFeaturesEnabled, info);
    }
    
    @Override
    public IChunkProvider getChunkProviderNether(World world, long seed, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	if (this.isDeco()) {
    		return new NetherChunkProvider(world, seed, info);
    	}
    	else {
    		return new ChunkProviderHell(world, seed);
    	}
    }

    @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new EndChunkProvider(world, seed);
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