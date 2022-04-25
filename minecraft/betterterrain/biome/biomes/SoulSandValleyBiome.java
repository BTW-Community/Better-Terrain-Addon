package betterterrain.biome.biomes;

import betterterrain.biome.BTANetherBiome;
import net.minecraft.src.Block;
import net.minecraft.src.FCEntityGhast;
import net.minecraft.src.FCEntitySkeleton;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public class SoulSandValleyBiome extends BTANetherBiome {
	public SoulSandValleyBiome(int id) {
		super(id);
		
		this.topBlockExt = Block.slowSand.blockID;
		this.fillerBlockExt = Block.slowSand.blockID;
		
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySkeleton.class, 50, 4, 4));
	}
	
	@Override
	public Vec3 getFogColor(World world) {
		return world.getWorldVec3Pool().getVecFromPool(0.1, 0.4, 0.5);
	}
}