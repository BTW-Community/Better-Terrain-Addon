package betterterrain;

import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;

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
    	BTAWorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = BTAWorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = BTAWorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	return new BTAWorldChunkManager(world, info);
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
    	
    	switch (info.getGenerator()) {
    	default:
    	case CLASSIC:
    		return new BTAChunkProvider(world, seed, mapFeaturesEnabled, info);
    	case SIMPLEX_OLD:
    		return new BTAChunkProviderSimplexOld(world, seed, mapFeaturesEnabled, info);
    	case SIMPLEX:
    		return new BTAChunkProviderSimplex(world, seed, mapFeaturesEnabled, info);
    	}
    }
    
    @Override
    public IChunkProvider getChunkProviderNether(World world, long seed, String generatorOptions) {
    	BTAWorldConfigurationInfo info;
    	
    	if (generatorOptions.equals("")) {
    		info = BTAWorldConfigurationInfo.createDefaultConfigurationLegacy(this.isDeco());
    	}
    	else {
    		info = BTAWorldConfigurationInfo.createInfoFromString(generatorOptions);
    	}
    	
    	if (this.isDeco()) {
    		return new BTAChunkProviderNether(world, seed, info);
    	}
    	else {
    		return new ChunkProviderHell(world, seed);
    	}
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
	
    @Override
	public int getColdBiomeSnowLevelModifier(BTAWorldConfigurationInfo generatorInfo) {
		return generatorInfo.getGenerator().equals(BTAEnumTerrainGenerator.SIMPLEX) ? 70 : 0;
	}
}