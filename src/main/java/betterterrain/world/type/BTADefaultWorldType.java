package betterterrain.world.type;

import betterterrain.world.BTAWorldChunkManager;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.config.WorldConfigurationInfoLegacy;
import betterterrain.world.generate.BTADefaultChunkProvider;
import betterterrain.world.generate.EndChunkProvider;
import betterterrain.world.generate.NetherChunkProvider;
import betterterrain.world.generate.SimplexChunkProvider;
import betterterrain.world.generate.TerrainGenerator;
import net.minecraft.src.*;

public class BTADefaultWorldType extends WorldType {
    public BTADefaultWorldType(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Better Terrain";
    }

    // @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy((this).isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BTAWorldChunkManager(world, info);
    }

    // @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy((this).isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	switch (info.getGenerator()) {
    	default:
    	case CLASSIC:
    		return new BTADefaultChunkProvider(world, seed, mapFeaturesEnabled, info);
    	case SIMPLEX:
    		return new SimplexChunkProvider(world, seed, mapFeaturesEnabled, info);
    	}
    }

    // @Override
    public IChunkProvider getChunkProviderNether(World world, long seed, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfoLegacy.createDefaultConfigurationLegacy((this).isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	if ((this).isDeco()) {
    		return new NetherChunkProvider(world, seed, info);
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
	public int getColdBiomeSnowLevelModifier(WorldConfigurationInfo generatorInfo) {
		return generatorInfo.getGenerator().equals(TerrainGenerator.SIMPLEX) ? 70 : 0;
	}
}