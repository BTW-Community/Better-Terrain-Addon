package betterterrain.biome.biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import betterterrain.BTADecoIntegration;
import betterterrain.BTAEnumClimate;
import betterterrain.BTAEnumVersionCompat;
import betterterrain.BTAWorldConfigurationInfo;
import net.minecraft.src.World;

public class BTABiomeGenBadlands extends BTABiomeGenBase {
	private ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	
	public BTABiomeGenBadlands(int id, BTAEnumClimate climate) {
		super(id, climate);
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTADecoIntegration.redSand.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
		this.spawnableCreatureList.clear();
        this.btaBiomeDecorator.generateLakes = false;
		
		this.initMetaList();
	}

	public void decorate(World world, Random rand, int startX, int startZ, BTAWorldConfigurationInfo generatorOptions)
	{
		super.decorate(world, rand, startX, startZ, generatorOptions);

		if (generatorOptions.getCompatMode().isVersionAtOrBelow(BTAEnumVersionCompat.V1_2_1)) {
			for (int i = startX; i < startX + 16; i++) {
				for (int j = 50; j < 127; j++) {
					int metaForY = Math.abs(Arrays.hashCode(new int[] {(int) world.getSeed() >> 32, j}) % 16);

					for (int k = startZ; k < startZ + 16; k++) {
						if (world.getBlockId(i, j, k) == BTADecoIntegration.terracotta.blockID) {
							if (allowedTerracottaMetadata.contains(metaForY)) {
								world.setBlockAndMetadata(i, j, k, BTADecoIntegration.stainedTerracotta.blockID, metaForY);
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