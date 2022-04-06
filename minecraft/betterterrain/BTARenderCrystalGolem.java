package betterterrain;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.RenderLiving;

public class BTARenderCrystalGolem extends RenderLiving
{
    /** Iron Golem's Model. */
    private BTAModelCrystalGolem ironGolemModel;

    public BTARenderCrystalGolem()
    {
        super(new BTAModelCrystalGolem(), 0.5F);
        this.ironGolemModel = (BTAModelCrystalGolem)this.mainModel;
    }

    /**
     * Renders the Iron Golem.
     */
    public void doRenderIronGolem(BTAEntityCrystalGolem par1EntityIronGolem, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityIronGolem, par2, par4, par6, par8, par9);
    }

    /**
     * Rotates Iron Golem corpse.
     */
    protected void rotateIronGolemCorpse(BTAEntityCrystalGolem par1EntityIronGolem, float par2, float par3, float par4)
    {
        super.rotateCorpse(par1EntityIronGolem, par2, par3, par4);

        if ((double)par1EntityIronGolem.limbYaw >= 0.01D)
        {
            float var5 = 13.0F;
            float var6 = par1EntityIronGolem.limbSwing - par1EntityIronGolem.limbYaw * (1.0F - par4) + 6.0F;
            float var7 = (Math.abs(var6 % var5 - var5 * 0.5F) - var5 * 0.25F) / (var5 * 0.25F);
            GL11.glRotatef(6.5F * var7, 0.0F, 0.0F, 1.0F);
        }
    }

    protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        this.rotateIronGolemCorpse((BTAEntityCrystalGolem)par1EntityLiving, par2, par3, par4);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderIronGolem((BTAEntityCrystalGolem)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderIronGolem((BTAEntityCrystalGolem)par1Entity, par2, par4, par6, par8, par9);
    }
}
