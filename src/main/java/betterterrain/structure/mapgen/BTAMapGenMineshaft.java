package betterterrain.structure.mapgen;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.src.MathHelper;
import net.minecraft.src.StructureMineshaftStart;
import net.minecraft.src.StructureStart;

public class BTAMapGenMineshaft extends BTAMapGenStructure
{
    private double field_82673_e = 0.01D;

    public BTAMapGenMineshaft() {}

    public BTAMapGenMineshaft(Map par1Map)
    {
        Iterator var2 = par1Map.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();

            if (((String)var3.getKey()).equals("chance"))
            {
                this.field_82673_e = MathHelper.parseDoubleWithDefault((String)var3.getValue(), this.field_82673_e);
            }
        }
    }

    public boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        return this.rand.nextDouble() < this.field_82673_e && this.rand.nextInt(80) < Math.max(Math.abs(par1), Math.abs(par2));
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureMineshaftStart(this.worldObj, this.rand, par1, par2);
    }
}
