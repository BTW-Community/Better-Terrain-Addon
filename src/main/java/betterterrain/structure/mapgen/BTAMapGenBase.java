package betterterrain.structure.mapgen;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MapGenBase;
import net.minecraft.src.World;

public class BTAMapGenBase extends MapGenBase {
    public void generate(IChunkProvider par1IChunkProvider, World par2World, int par3, int par4, int[] blockArray)
    {
        int var6 = this.range;
        this.worldObj = par2World;
        this.rand.setSeed(par2World.getSeed());
        long var7 = this.rand.nextLong();
        long var9 = this.rand.nextLong();

        for (int var11 = par3 - var6; var11 <= par3 + var6; ++var11)
        {
            for (int var12 = par4 - var6; var12 <= par4 + var6; ++var12)
            {
                long var13 = (long)var11 * var7;
                long var15 = (long)var12 * var9;
                this.rand.setSeed(var13 ^ var15 ^ par2World.getSeed());
                this.recursiveGenerate(par2World, var11, var12, par3, par4, blockArray);
            }
        }
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, int[] blockArray) {}
    
    public void generate(IChunkProvider chunkProvider, World world, int x, int z, int[][][] blockArray)
    {
        int range = this.range;
        this.worldObj = world;
        this.rand.setSeed(world.getSeed());
        long seedBase1 = this.rand.nextLong();
        long seedBase2 = this.rand.nextLong();

        for (int i = x - range; i <= x + range; ++i)
        {
            for (int k = z - range; k <= z + range; ++k)
            {
                long seed1 = (long)i * seedBase1;
                long seed2 = (long)k * seedBase2;
                this.rand.setSeed(seed1 ^ seed2 ^ world.getSeed());
                this.recursiveGenerate(world, i, k, x, z, blockArray);
            }
        }
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, int[][][] blockArray) {}
}