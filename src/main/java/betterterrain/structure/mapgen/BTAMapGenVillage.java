package betterterrain.structure.mapgen;

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.src.*;

public class BTAMapGenVillage extends BTAMapGenStructure
{
    /** World terrain type, 0 for normal, 1 for flat map */
    private int terrainType;
    private int field_82665_g;
    private int field_82666_h;

    public BTAMapGenVillage()
    {
        this.terrainType = 0;
        this.field_82665_g = 32;
        this.field_82666_h = 8;
    }

    public BTAMapGenVillage(Map par1Map)
    {
        this();
        Iterator var2 = par1Map.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();

            if (((String)var3.getKey()).equals("size"))
            {
                this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.terrainType, 0);
            }
            else if (((String)var3.getKey()).equals("distance"))
            {
                this.field_82665_g = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82665_g, this.field_82666_h + 1);
            }
        }
    }

    public boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int var3 = par1;
        int var4 = par2;

        if (par1 < 0)
        {
            par1 -= this.field_82665_g - 1;
        }

        if (par2 < 0)
        {
            par2 -= this.field_82665_g - 1;
        }

        int var5 = par1 / this.field_82665_g;
        int var6 = par2 / this.field_82665_g;
        Random var7 = this.worldObj.setRandomSeed(var5, var6, 10387312);
        var5 *= this.field_82665_g;
        var6 *= this.field_82665_g;
        var5 += var7.nextInt(this.field_82665_g - this.field_82666_h);
        var6 += var7.nextInt(this.field_82665_g - this.field_82666_h);

        if (var3 == var5 && var4 == var6)
        {
            boolean var8 = this.worldObj.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 0, MapGenVillage.villageSpawnBiomes);

            if (var8)
            {
                return true;
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
    	return new StructureVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
    }
}
