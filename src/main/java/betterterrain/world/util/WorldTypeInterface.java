package betterterrain.world.util;

import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.*;

public interface WorldTypeInterface {
    //============//
    // IMPORTANT! //
    //============//

    // Methods in subclasses cannot use override
    // Double check subclasses before changing signatures!

    default WorldChunkManager getChunkManager(World world, String generatorOptions) {
        return new WorldChunkManager(world);
    }

    default IChunkProvider getChunkProviderOverworld(World world, long seed, boolean mapFeaturesEnabled, String generatorOptions) {
        return new ChunkProviderGenerate(world, seed, mapFeaturesEnabled);
    }

    default IChunkProvider getChunkProviderNether(World world, long seed, String generatorOptions) {
        return new ChunkProviderHell(world, seed);
    }

    default IChunkProvider getChunkProviderEnd(World world, long seed) {
        return new ChunkProviderEnd(world, seed);
    }

    default float getCloudHeight() {
        return 128F;
    }

    default int getAverageGroundLevel() {
        return 64;
    }

    default double getHorizon() {
        return 63D;
    }

    default int[] getStrataLevels() {
        return new int[]{48, 24};
    }

    WorldTypeInterface setIsDeco();

    boolean isDeco();

    default boolean hasDeco() {
        return false;
    }

    default boolean isSky() {
        return false;
    }

    default boolean isBTA() {
        return false;
    }

    default boolean hasOceans() {
        return true;
    }

    default boolean canPerlinBeachesBeToggled() {
        return true;
    }

    default boolean getDefaultPerlinBeachState() {
        return true;
    }

    WorldTypeInterface getParent();

    WorldTypeInterface setParent(WorldTypeInterface parent);

    default int getColdBiomeSnowLevelModifier(WorldConfigurationInfo generatorInfo) {
        return 0;
    }
}