package netherexpanded.biome.biomes;

import betterterrain.BTAMod;
import betterterrain.biome.BTANetherBiome;
import btw.entity.mob.SkeletonEntity;
import deco.block.DecoBlocks;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public class AshFieldsBiome extends BTANetherBiome {
	public AshFieldsBiome(int id, String internalName) {
		super(id, internalName);
		
		if (BTAMod.isDecoInstalled()) {
			this.topBlockExt = DecoBlocks.ash.blockID;
			this.fillerBlockExt = DecoBlocks.ash.blockID;
		}
		
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(SkeletonEntity.class, 50, 4, 4));
	}
	
	@Override
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.3, 0.2, 0.1);
	}
}