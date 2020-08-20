package net.minecraft.src;

import java.util.concurrent.Callable;

class BTWGCallableChunkPosHash implements Callable
{
    final int field_85165_a;

    final int field_85163_b;

    final BTWGMapGenStructure theMapStructureGenerator;

    BTWGCallableChunkPosHash(BTWGMapGenStructure par1MapGenStructure, int par2, int par3)
    {
        this.theMapStructureGenerator = par1MapGenStructure;
        this.field_85165_a = par2;
        this.field_85163_b = par3;
    }

    public String callChunkPositionHash()
    {
        return String.valueOf(ChunkCoordIntPair.chunkXZ2Int(this.field_85165_a, this.field_85163_b));
    }

    public Object call()
    {
        return this.callChunkPositionHash();
    }
}
