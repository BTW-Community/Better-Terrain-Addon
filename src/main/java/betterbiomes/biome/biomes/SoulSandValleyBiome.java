package betterbiomes.biome.biomes;

import betterterrain.biome.BTANetherBiome;
import btw.entity.mob.GhastEntity;
import btw.entity.mob.SkeletonEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public class SoulSandValleyBiome extends BTANetherBiome {
	public SoulSandValleyBiome(int id, String internalName) {
		super(id, internalName);
		
		this.topBlockExt = Block.slowSand.blockID;
		this.fillerBlockExt = Block.slowSand.blockID;
		
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(GhastEntity.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(SkeletonEntity.class, 50, 4, 4));
	}
	
	@Override
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.1, 0.4, 0.5);
	}
}