package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.world.generate.surface.HotSpringsSurfaceBuilder;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.feature.plant.TallGrassGen;
import betterterrain.feature.tree.PineTreeGen;
import betterterrain.feature.tree.TaigaGen5;
import betterterrain.feature.tree.TaigaGen6;
import betterterrain.feature.tree.TaigaGen7;
import betterterrain.world.config.WorldConfigurationInfo;
import btw.entity.mob.WolfEntity;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class HotSpringsBiome extends BTABiome {
	public HotSpringsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);

		spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 8, 4, 4));
		
        this.btaBiomeDecorator.treesPerChunk = 8;
        this.btaBiomeDecorator.grassPerChunk = 15;
        // Clay messes up the hot springs
        this.btaBiomeDecorator.clayPerChunk = 0;
		
		this.waterColorMultiplier =  0x00ffaa;
	}

	@Override
	public boolean shouldConnectWithEdge(WorldConfigurationInfo generatorOptions) {
		return true;
	}
	
	@Override
    public WorldGenerator getRandomWorldGenForTrees(Random rand) {
    	WorldGenerator gen;
    	
    	if (rand.nextInt(3) == 0) {
    		gen = new PineTreeGen(false, 2, 2);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen5(false);
    	}
    	else if (rand.nextInt(3) == 0) {
    		gen = new TaigaGen6(false);
    	}
    	else {
    		gen = new TaigaGen7(false);
    	}
    	
    	return gen;
    }
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(2) == 0 ? new TallGrassGen(Block.tallGrass.blockID, 2) : new TallGrassGen(Block.tallGrass.blockID, 1);
	}

	@Override
    public boolean canSnowAt(World world, int x, int y, int z) {
		return super.canSnowAt(world, x, y, z) && ((HotSpringsSurfaceBuilder) this.getSurfaceBuilder()).springsNoiseGen.noise2(x, z, 1/192D) <= 0.125;
	}
}