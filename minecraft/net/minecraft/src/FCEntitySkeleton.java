package net.minecraft.src;

import betterterrain.BTAIChunkProvider;

public class FCEntitySkeleton extends EntitySkeleton
{
	private FCEntityAISkeletonArrowAttack m_aiRangedAttack;
	private EntityAIAttackOnCollide m_aiMeleeAttack;
	private static final float m_fEnchantmentProbability = 0.0125F;
	private static final float m_fArmorEnchantmentProbability = 0.05F;
	private static final float m_fPickUpLootProability = 0.15F;

	public FCEntitySkeleton(World var1)
	{
		super(var1);
		this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
		this.tasks.addTask(5, new FCEntityAIWanderSimple(this, this.moveSpeed));
	}

	protected void entityInit()
	{
		super.entityInit();
		this.m_aiRangedAttack = new FCEntityAISkeletonArrowAttack(this, 0.25F, 60, 15.0F);
		this.m_aiMeleeAttack = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.31F, false);
	}

	public void PreInitCreature() {
		if (this.worldObj.provider.dimensionId == -1) {
			/*if (this.worldObj.provider.terrainType.isBTA()) {
				if (!(this.worldObj.chunkProvider instanceof ChunkProviderServer)) {
					if (((BTAIChunkProvider) this.worldObj.chunkProvider).doesStructureExistAtCoords((int) this.posX, (int) this.posY, (int) this.posZ) && 
							((BTAIChunkProvider) this.worldObj.chunkProvider).getPossibleCreaturesStructuresOnly(EnumCreatureType.monster, (int) this.posX, (int) this.posY, (int) this.posZ).contains(this.getClass())) {
						this.setSkeletonType(1);
					}
				}
			}
			else*/ if (this.getRNG().nextInt(5) > 0) {
				this.setSkeletonType(1);
			}
		}
	}

	/**
	 * Initialize this creature.
	 */
	 public void initCreature()
	{
		if (this.getSkeletonType() == 1)
		{
			this.setCurrentItemOrArmor(0, new ItemStack(Item.swordStone));
		}
		else
		{
			this.addRandomArmor();
			this.func_82162_bC();
		}

		this.setCombatTask();
		this.setCanPickUpLoot(this.rand.nextFloat() < 0.15F);
	}

	 public void SpawnerInitCreature()
	 {
		 this.setCombatTask();
		 this.setCanPickUpLoot(this.rand.nextFloat() < 0.15F);
	 }

	 /**
	  * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	  * use this to react to sunlight and start to burn.
	  */
	 public void onLivingUpdate()
	 {
		 this.CheckForCatchFireInSun();

		 if (this.worldObj.isRemote && this.getSkeletonType() == 1)
		 {
			 this.setSize(0.72F, 2.34F);
		 }

		 this.EntityMobOnLivingUpdate();
	 }

	 /**
	  * Returns the item ID for the item the mob drops on death.
	  */
	 protected int getDropItemId()
	 {
		 return 0;
	 }

	 /**
	  * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	  * par2 - Level of Looting used to kill this mob.
	  */
	 protected void dropFewItems(boolean var1, int var2)
	 {
		 int var3;
		 int var4;

		 if (this.getSkeletonType() == 0 && this.worldObj.provider.dimensionId != -1 && this.getHeldItem() != null && this.getHeldItem().itemID == Item.bow.itemID)
		 {
			 var3 = this.rand.nextInt(3 + var2);

			 for (var4 = 0; var4 < var3; ++var4)
			 {
				 this.dropItem(FCBetterThanWolves.fcItemRottenArrow.itemID, 1);
			 }
		 }

		 var3 = this.rand.nextInt(3 + var2);

		 for (var4 = 0; var4 < var3; ++var4)
		 {
			 this.dropItem(Item.bone.itemID, 1);
		 }
	 }

	 protected void dropRareDrop(int var1) {}

	 /**
	  * Attack the specified entity using a ranged attack.
	  */
	 public void attackEntityWithRangedAttack(EntityLiving var1, float var2)
	 {
		 Object var3 = null;

		 if (this.worldObj.provider.dimensionId == -1)
		 {
			 var3 = new FCEntityInfiniteArrow(this.worldObj, this, var1, 1.6F, 12.0F);
		 }
		 else
		 {
			 var3 = new FCEntityRottenArrow(this.worldObj, this, var1, 1.6F, 12.0F);
		 }

		 int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());

		 if (var4 > 0)
		 {
			 ((EntityArrow)var3).setDamage(((EntityArrow)var3).getDamage() + (double)var4 * 0.5D + 0.5D);
		 }

		 int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());

		 if (var5 > 0)
		 {
			 ((EntityArrow)var3).setKnockbackStrength(var5);
		 }

		 int var6 = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem());

		 if (var6 > 0 || this.getSkeletonType() == 1 || this.isBurning() && this.rand.nextFloat() < 0.3F)
		 {
			 ((EntityArrow)var3).setFire(100);
		 }

		 this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		 this.worldObj.spawnEntityInWorld((Entity)var3);
	 }

	 /**
	  * sets this entity's combat AI.
	  */
	 public void setCombatTask()
	 {
		 this.tasks.removeTask(this.m_aiMeleeAttack);
		 this.tasks.removeTask(this.m_aiRangedAttack);
		 ItemStack var1 = this.getHeldItem();

		 if (var1 != null && var1.itemID == Item.bow.itemID)
		 {
			 this.tasks.addTask(4, this.m_aiRangedAttack);
		 }
		 else
		 {
			 this.tasks.addTask(4, this.m_aiMeleeAttack);
		 }
	 }

	 /**
	  * Checks to make sure the light is not too bright where the mob is spawning
	  */
	 protected boolean isValidLightLevel()
	 {
		 return this.worldObj.provider.dimensionId == -1 ? true : super.isValidLightLevel();
	 }

	 public void CheckForScrollDrop()
	 {
		 if (this.getSkeletonType() == 0 && this.rand.nextInt(1000) == 0)
		 {
			 ItemStack var1 = null;

			 if (this.worldObj.provider.dimensionId == -1)
			 {
				 var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.infinity.effectId);
			 }
			 else
			 {
				 var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.projectileProtection.effectId);
			 }

			 this.entityDropItem(var1, 0.0F);
		 }
	 }

	 protected void dropHead()
	 {
		 if (this.getSkeletonType() == 1)
		 {
			 this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
		 }
		 else
		 {
			 this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 0), 0.0F);
		 }
	 }

	 protected void func_82162_bC()
	 {
		 if (this.getHeldItem() != null && this.rand.nextFloat() < 0.0125F)
		 {
			 EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItem(), 7 * this.rand.nextInt(6));
			 this.equipmentDropChances[0] = 0.99F;
		 }

		 for (int var1 = 0; var1 < 4; ++var1)
		 {
			 ItemStack var2 = this.getCurrentArmor(var1);

			 if (var2 != null && this.rand.nextFloat() < 0.05F)
			 {
				 EnchantmentHelper.addRandomEnchantment(this.rand, var2, 7 * this.rand.nextInt(6));
			 }
		 }
	 }

	 /**
	  * Called when the entity is attacked.
	  */
	 public boolean attackEntityFrom(DamageSource var1, int var2)
	 {
		 return var1 != DamageSource.cactus ? super.attackEntityFrom(var1, var2) : false;
	 }
}
