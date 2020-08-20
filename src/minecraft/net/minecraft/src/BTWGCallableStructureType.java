package net.minecraft.src;

import java.util.concurrent.Callable;

class BTWGCallableStructureType implements Callable
{
    final BTWGMapGenStructure theMapStructureGenerator;

    BTWGCallableStructureType(BTWGMapGenStructure par1MapGenStructure)
    {
        this.theMapStructureGenerator = par1MapGenStructure;
    }

    public String callStructureType()
    {
        return this.theMapStructureGenerator.getClass().getCanonicalName();
    }

    public Object call()
    {
        return this.callStructureType();
    }
}
