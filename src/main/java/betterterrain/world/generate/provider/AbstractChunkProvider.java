/**
 * 
 */
package betterterrain.world.generate.provider;

import java.util.List;
import java.util.Random;

import betterterrain.structure.mapgen.BTAMapGenNetherBridge;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.*;

/**
 * @author Abigail Moore
 *
 */
public abstract class AbstractChunkProvider implements IChunkProvider {
    protected World worldObj;
    protected Random rand;
    protected long seed;
    protected WorldConfigurationInfo generatorInfo;
    protected boolean mapFeaturesEnabled;

    public AbstractChunkProvider(World world, long seed, boolean mapFeaturesEnabled, WorldConfigurationInfo generatorInfo) {
        this.rand = new Random(seed);
        this.seed = seed;

        this.worldObj = world;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.generatorInfo = generatorInfo;
    }

    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    @Override
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    @Override
    public void func_104112_b() {}

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public String makeString()
    {
        return "RandomLevelSource";
    }

    public abstract List getPossibleCreaturesStructuresOnly(EnumCreatureType creatureType, int x, int y, int z);
    
    public abstract boolean doesStructureExistAtCoords(int x, int y, int z);

    public boolean isNether() {
        return false;
    }

    public BTAMapGenNetherBridge getNetherBridgeGenerator() {
        return null;
    }

    public int getIndex(int x, int y, int z) {
        return x << 11 | z << 7 | y;
    }
}