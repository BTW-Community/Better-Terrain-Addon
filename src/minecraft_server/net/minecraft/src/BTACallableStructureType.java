package net.minecraft.src;

import java.util.concurrent.Callable;

class BTACallableStructureType implements Callable
{
    final BTAMapGenStructure theMapStructureGenerator;

    BTACallableStructureType(BTAMapGenStructure par1MapGenStructure)
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
