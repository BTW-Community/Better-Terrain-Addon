package betterbiomes.biome.biomes;

import betterterrain.DecoIntegration;
import betterterrain.biome.BTANetherBiome;
import net.minecraft.src.FCEntitySkeleton;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public class AshFieldsBiome extends BTANetherBiome {
	public AshFieldsBiome(int id, String internalName) {
		super(id, internalName);
		
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.ash.blockID;
			this.fillerBlockExt = DecoIntegration.ash.blockID;
		}
		
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