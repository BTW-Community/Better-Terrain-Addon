package betterbiomes.biome.biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import betterterrain.BTAVersion;
import betterterrain.DecoIntegration;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import net.minecraft.src.World;

public class BadlandsBiome extends BTABiome {
	private ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	
	public BadlandsBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		if (DecoIntegration.isDecoInstalled()) {
			this.topBlockExt = DecoIntegration.redSand.blockID;
			this.fillerBlockExt = DecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
		this.spawnableCreatureList.clear();
        this.btaBiomeDecorator.generateLakes = false;
		
		this.initMetaList();
	}

	public void decorate(World world, Random rand, int startX, int startZ, WorldConfigurationInfo generatorOptions)
	{
		super.decorate(world, rand, startX, startZ, generatorOptions);

		if (generatorOptions.getBTAVersion().isVersionAtOrBelow(BTAVersion.V1_2_1)) {
			for (int i = startX; i < startX + 16; i++) {
				for (int j = 50; j < 127; j++) {
					int metaForY = Math.abs(Arrays.hashCode(new int[] {(int) world.getSeed() >> 32, j}) % 16);

					for (int k = startZ; k < startZ + 16; k++) {
						if (world.getBlockId(i, j, k) == DecoIntegration.terracotta.blockID) {
							if (allowedTerracottaMetadata.contains(metaForY)) {
								world.setBlockAndMetadata(i, j, k, DecoIntegration.stainedTerracotta.blockID, metaForY);
							}
						}
					}
				}
			}
		}
	}

	@Override
    public boolean CanLightningStrikeInBiome()
    {
        return true;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return 9341503;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return 9341503;
    }
	
	private void initMetaList() {
		allowedTerracottaMetadata.add(1);
		allowedTerracottaMetadata.add(3);
		allowedTerracottaMetadata.add(7);
		allowedTerracottaMetadata.add(11);
		allowedTerracottaMetadata.add(14);
		allowedTerracottaMetadata.add(15);
	}
}