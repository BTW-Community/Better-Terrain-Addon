package betterterrain.world.type;

import betterterrain.world.BTAWorldChunkManager;
import betterterrain.world.WorldConfigurationInfo;
import betterterrain.world.generate.BTADefaultChunkProvider;
import betterterrain.world.generate.EndChunkProvider;
import betterterrain.world.generate.NetherChunkProvider;
import betterterrain.world.generate.SimplexChunkProvider;
import betterterrain.world.generate.SimplexChunkProviderOld;
import betterterrain.world.generate.TerrainGenerator;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;

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

    @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	WorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = WorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = WorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BTAWorldChunkManager(world, info);
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
    	
    	switch (info.getGenerator()) {
    	default:
    	case CLASSIC:
    		return new BTADefaultChunkProvider(world, seed, mapFeaturesEnabled, info);
    	case SIMPLEX_OLD:
    		return new SimplexChunkProviderOld(world, seed, mapFeaturesEnabled, info);
    	case SIMPLEX:
    		return new SimplexChunkProvider(world, seed, mapFeaturesEnabled, info);
    	}
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
	
    @Override
	public int getColdBiomeSnowLevelModifier(WorldConfigurationInfo generatorInfo) {
		return generatorInfo.getGenerator().equals(TerrainGenerator.SIMPLEX) ? 70 : 0;
	}
}