package net.minecraft.src;

public class BTABiomeGenRiver extends BTABiomeGenBase
{
    public BTABiomeGenRiver(int id, BTAEnumClimate climate)
    {
        super(id, climate);
        this.spawnableCreatureList.clear();
    }
}