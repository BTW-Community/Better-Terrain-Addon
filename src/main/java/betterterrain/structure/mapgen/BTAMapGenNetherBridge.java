package betterterrain.structure.mapgen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import btw.entity.mob.BlazeEntity;
import btw.entity.mob.SkeletonEntity;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.StructureNetherBridgeStart;
import net.minecraft.src.StructureStart;

public class BTAMapGenNetherBridge extends BTAMapGenStructure
{
    protected List m_mobSpawnList = new ArrayList();

    public BTAMapGenNetherBridge()
    {
        this.m_mobSpawnList.add(new SpawnListEntry(BlazeEntity.class, 10, 2, 3));
        this.m_mobSpawnList.add(new SpawnListEntry(SkeletonEntity.class, 10, 4, 4));
    }

    public List getSpawnList()
    {
        return this.m_mobSpawnList;
    }

    public boolean HasStructureAtLoose(int var1, int var2, int var3)
    {
        Iterator var4 = this.structureMap.values().iterator();
        StructureStart var5;
        StructureBoundingBox var6;

        do
        {
            if (!var4.hasNext())
            {
                return false;
            }

            var5 = (StructureStart)var4.next();
            var6 = var5.getBoundingBox();
        }
        while (!var5.isSizeableStructure() || !var6.intersectsWith(var1, var3, var1, var3) || var2 < var6.minY || var2 > var6.maxY);

        return true;
    }

    public StructureStart GetClosestStructureWithinRangeSq(double var1, double var3, double var5)
    {
        StructureStart var7 = null;
        double var8 = var5;
        Iterator var10 = this.structureMap.values().iterator();

        while (var10.hasNext())
        {
            StructureStart var11 = (StructureStart)var10.next();
            StructureBoundingBox var12 = var11.getBoundingBox();
            double var13 = (double)var12.getCenterX();
            double var15 = (double)var12.getCenterZ();
            double var17 = var1 - var13;
            double var19 = var3 - var15;
            double var21 = var17 * var17 + var19 * var19;

            if (var21 < var8)
            {
                var7 = var11;
                var8 = var21;
            }
        }

        return var7;
    }

    public boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int var3 = par1 >> 4;
        int var4 = par2 >> 4;
        this.rand.setSeed((long)(var3 ^ var4 << 4) ^ this.worldObj.getSeed());
        this.rand.nextInt();
        return this.rand.nextInt(3) != 0 ? false : (par1 != (var3 << 4) + 4 + this.rand.nextInt(8) ? false : par2 == (var4 << 4) + 4 + this.rand.nextInt(8));
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureNetherBridgeStart(this.worldObj, this.rand, par1, par2);
    }
}
