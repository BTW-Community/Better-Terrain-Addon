package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BTWGBiomeGenBadlands extends BTWGBiomeGenBase {
	private ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();
	
	public BTWGBiomeGenBadlands(int id) {
		super(id);
		if (BTWGDecoIntegration.isDecoInstalled()) {
			this.topBlockExt = BTWGDecoIntegration.redSand.blockID;
			this.fillerBlockExt = BTWGDecoIntegration.redSand.blockID;
		}
		this.btwgBiomeDecorator.sandPerChunk = 0;
		this.btwgBiomeDecorator.sandPerChunk2 = 0;
		this.spawnableCreatureList.clear();
		
		this.initMetaList();
	}

	public void decorate(World world, Random rand, int startX, int startZ)
	{
		super.decorate(world, rand, startX, startZ);

		for (int i = startX; i < startX + 16; i++) {
			for (int j = 50; j < 127; j++) {
				int metaForY = Math.abs(Arrays.hashCode(new int[] {(int) world.getSeed() >> 32, j}) % 16);
				
				for (int k = startZ; k < startZ + 16; k++) {
					if (world.getBlockId(i, j, k) == BTWGDecoIntegration.terracotta.blockID) {
						if (allowedTerracottaMetadata.contains(metaForY)) {
							world.setBlockAndMetadata(i, j, k, BTWGDecoIntegration.stainedTerracotta.blockID, metaForY);
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
	
	private void initMetaList() {
		allowedTerracottaMetadata.add(1);
		allowedTerracottaMetadata.add(3);
		allowedTerracottaMetadata.add(7);
		allowedTerracottaMetadata.add(11);
		allowedTerracottaMetadata.add(14);
		allowedTerracottaMetadata.add(15);
	}
}