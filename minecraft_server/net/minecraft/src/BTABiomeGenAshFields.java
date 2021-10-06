package net.minecraft.src;

public class BTABiomeGenAshFields extends BTABiomeGenNetherBase {
	public BTABiomeGenAshFields(int id) {
		super(id);
		
		this.topBlockExt = BTADecoIntegration.ash.blockID;
		this.fillerBlockExt = BTADecoIntegration.ash.blockID;
		
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySkeleton.class, 50, 4, 4));
	}
	
	@Override
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.3, 0.2, 0.1);
	}
}