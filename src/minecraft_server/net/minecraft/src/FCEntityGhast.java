package net.minecraft.src;

public class FCEntityGhast extends EntityGhast
{
    private static final long m_iPlayerSwitchDimensionsGracePeriod = 600L;
    private static final double m_dMaxFireballLaunchRange = 64.0D;
    private static final double m_dMaxFireballLaunchRangeSq = 4096.0D;
    private static final double m_dFireballSpawnDistFromGhast = 4.0D;
    private static final int m_iFireballExplosionPower = 1;
    private Entity m_entityTargeted = null;
    private int m_iRetargetCountdown = 0;

    public FCEntityGhast(World var1)
    {
        super(var1);
    }

    public int getMaxHealth()
    {
        return 20;
    }

    protected void updateEntityActionState()
    {
        ++this.entityAge;
        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - this.posX;
        double var3 = this.waypointY - this.posY;
        double var5 = this.waypointZ - this.posZ;
        double var7 = var1 * var1 + var3 * var3 + var5 * var5;

        if (var7 < 1.0D || var7 > 3600.0D)
        {
            this.waypointX = this.posX + (this.rand.nextDouble() * 2.0D - 1.0D) * 16.0D;
            this.waypointY = this.posY + (this.rand.nextDouble() * 2.0D - 1.0D) * 16.0D;
            this.waypointZ = this.posZ + (this.rand.nextDouble() * 2.0D - 1.0D) * 16.0D;
        }

        double var9;

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            var9 = (double)MathHelper.sqrt_double(var7);

            if (this.IsCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var9))
            {
                this.motionX += var1 / var9 * 0.1D;
                this.motionY += var3 / var9 * 0.1D;
                this.motionZ += var5 / var9 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        if (this.m_entityTargeted == null || !this.m_entityTargeted.isEntityAlive() || this.m_iRetargetCountdown-- <= 0)
        {
            this.m_entityTargeted = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

            if (this.m_entityTargeted != null && this.m_entityTargeted instanceof EntityPlayer)
            {
                EntityPlayer var15 = (EntityPlayer)this.m_entityTargeted;
                long var10 = var15.m_lTimeOfLastDimensionSwitch;
                long var12 = this.worldObj.getWorldTime();

                if (var12 > var10 && var12 - var10 <= 600L && var15 != this.m_entityTargeted)
                {
                    this.m_entityTargeted = null;
                }
            }

            if (this.m_entityTargeted != null)
            {
                this.m_iRetargetCountdown = 20;
            }
        }

        if (this.m_entityTargeted != null && this.m_entityTargeted.getDistanceSqToEntity(this) < 4096.0D)
        {
            var9 = this.m_entityTargeted.posX - this.posX;
            double var11 = this.m_entityTargeted.posZ - this.posZ;
            double var13 = this.m_entityTargeted.boundingBox.minY + (double)(this.m_entityTargeted.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            this.renderYawOffset = this.rotationYaw = -((float)(Math.atan2(var9, var11) * 180.0D / Math.PI));

            if (this.canEntityBeSeen(this.m_entityTargeted))
            {
                if (this.attackCounter == 10)
                {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }

                ++this.attackCounter;

                if (this.attackCounter == 20)
                {
                    this.FireAtTarget();
                }
            }
            else if (this.attackCounter > 0)
            {
                --this.attackCounter;
            }
        }
        else
        {
            this.renderYawOffset = this.rotationYaw = -((float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI));

            if (this.attackCounter > 0)
            {
                --this.attackCounter;
            }
        }

        if (!this.worldObj.isRemote)
        {
            boolean var16 = this.dataWatcher.getWatchableObjectByte(16) != 0;
            boolean var17 = this.attackCounter > 10;

            if (var16 != var17)
            {
                this.SetMouthOpen(var17);
            }
        }
    }

    protected double MinDistFromPlayerForDespawn()
    {
        return 144.0D;
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(500) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.punch.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 80 + this.rand.nextInt(480);
    }

    /**
     * returns true if the entity provided in the argument can be seen. (Raytrace)
     */
    public boolean canEntityBeSeen(Entity var1)
    {
        return this.worldObj.rayTraceBlocks_do_do(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY + (double)this.height / 2.0D, this.posZ), this.worldObj.getWorldVec3Pool().getVecFromPool(var1.posX, var1.posY + (double)var1.getEyeHeight(), var1.posZ), false, true) == null;
    }

    public boolean DoesEntityApplyToSquidPossessionCap()
    {
        return this.isEntityAlive() && this.GetIsPersistent();
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return Item.fireballCharge.itemID;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + var2);
        int var4;

        for (var4 = 0; var4 < var3; ++var4)
        {
            this.dropItem(Item.ghastTear.itemID, 1);
        }

        var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + var2);

        for (var4 = 0; var4 < var3; ++var4)
        {
            this.dropItem(Item.fireballCharge.itemID, 1);
        }
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return this.worldObj.provider.dimensionId == -1 ? 10.0F : 3.0F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox) && this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, 64.0D) == null;
    }

    public boolean AttractsLightning()
    {
        return false;
    }

    private boolean IsCourseTraversable(double var1, double var3, double var5, double var7)
    {
        if (var3 >= 0.0D && var3 <= (double)this.worldObj.getHeight())
        {
            double var9 = (this.waypointX - this.posX) / var7;
            double var11 = (this.waypointY - this.posY) / var7;
            double var13 = (this.waypointZ - this.posZ) / var7;
            AxisAlignedBB var15 = this.boundingBox.copy();

            for (double var16 = 1.0D; var16 < var7; ++var16)
            {
                var15.offset(var9, var11, var13);

                if (!this.worldObj.getCollidingBoundingBoxes(this, var15).isEmpty())
                {
                    return false;
                }
            }

            if (this.worldObj.isAnyLiquid(var15))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean GetCanSpawnHereNoPlayerDistanceRestrictions()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty();
    }

    private void SetMouthOpen(boolean var1)
    {
        Byte var2 = Byte.valueOf((byte)0);

        if (var1)
        {
            var2 = Byte.valueOf((byte)1);
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(var2.byteValue()));
    }

    private void FireAtTarget()
    {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        Vec3 var1 = this.getLook(1.0F);
        double var2 = this.posX + var1.xCoord;
        double var4 = this.posY + (double)(this.height / 2.0F);
        double var6 = this.posZ + var1.zCoord;
        double var8 = this.m_entityTargeted.posX - var2;
        double var10 = this.m_entityTargeted.posY + (double)this.m_entityTargeted.getEyeHeight() - var4;
        double var12 = this.m_entityTargeted.posZ - var6;
        EntityLargeFireball var14 = new EntityLargeFireball(this.worldObj, this, var8, var10, var12);
        var14.field_92057_e = 1;
        double var15 = (double)MathHelper.sqrt_double(var8 * var8 + var10 * var10 + var12 * var12);
        double var17 = var8 / var15;
        double var19 = var10 / var15;
        double var21 = var12 / var15;
        var14.posX = var2 + var17 * 4.0D;
        var14.posY = var4 + var19 * 4.0D - (double)var14.height / 2.0D;
        var14.posZ = var6 + var21 * 4.0D;
        this.worldObj.spawnEntityInWorld(var14);
        this.attackCounter = -40;
    }
}
