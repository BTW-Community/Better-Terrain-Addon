package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BTABiomeGenBadlandsPlateau extends BTABiomeGenBase {
	private ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();

	public BTABiomeGenBadlandsPlateau(int id, BTAEnumClimate climate) {
		super(id, climate);
		if (BTADecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTADecoIntegration.terracotta.blockID;
			this.fillerBlockExt = BTADecoIntegration.terracotta.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
		this.spawnableCreatureList.clear();
		
		this.btaBiomeDecorator.treesPerChunk = 7;
		this.btaBiomeDecorator.flowersPerChunk = -999;

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

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorTrees;
    }

	@Override
	public boolean CanLightningStrikeInBiome()
	{
		return true;
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