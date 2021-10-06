package net.minecraft.src;

public class BTAEntityCrystalGolem extends EntityMob {
	public BTAEntityCrystalGolem(World world) {
		super(world);
        this.texture = "/bta/crystalGolem.png";
        this.tasks.addTask(1, new FCEntityAIWanderSimple(this, this.moveSpeed));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
	}

	@Override
	public int getMaxHealth() {
		return 30;
	}
}