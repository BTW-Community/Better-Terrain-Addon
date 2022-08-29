package betterterrain.structure.callable;

import java.util.concurrent.Callable;

import betterterrain.structure.mapgen.BTAMapGenStructure;

public class BTACallableStructureType implements Callable
{
    final BTAMapGenStructure theMapStructureGenerator;

    public BTACallableStructureType(BTAMapGenStructure par1MapGenStructure)
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
