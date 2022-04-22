package betterterrain.world.type;

import betterterrain.world.BetaChunkManager;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.EndChunkProvider;
import betterterrain.world.generate.SkyChunkProvider;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;

public class SkyWorldType extends WorldType {
    public SkyWorldType(int par1, String par2Str) {
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
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
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
    		info = WorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new SkyChunkProvider(world, seed, mapFeaturesEnabled, info);
    }

    @Override
    public IChunkProvider getChunkProviderNether(World world, long seed, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new SkyChunkProvider(world, seed, true, info).setNether();
    }

    @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new EndChunkProvider(world, seed);
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