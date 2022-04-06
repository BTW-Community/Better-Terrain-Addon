package betterterrain;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.WorldType;

public class BTAWorldTypeHorizons extends WorldType {
    protected BTAWorldTypeHorizons(int par1, String par2Str) {
		super(par1, par2Str);
	}

	/**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
        return "Horizons";
    }

    @Override
    public WorldChunkManager getChunkManager(World world, String generatorOptions) {
    	return new BTAHorizonsWorldChunkManager(world);
    }
    
    @Override
    public IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
    	return new BTAHorizonsChunkProvider(world, seed, mapFeaturesEnabled);
    }

    @Override
    public IChunkProvider getChunkProviderEnd(World world, long seed) {
    	return new BTAChunkProviderEnd(world, seed);
    }

    @Override
    public float getCloudHeight() {
    	return 256F;
    }

    @Override
    public int getAverageGroundLevel() {
    	return 128;
    }

    @Override
    public double getHorizon() {
    	return 128F;
    }

    @Override
    public int[] getStrataLevels() {
    	return new int[] {96, 48, 24};
    }

    @Override
    public boolean isBTA() {
    	return true;
    }
}