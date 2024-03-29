package betterbiomes.biome.biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.BTAMod;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlocks;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BadlandsPlateauBiome extends BTABiome {
	private ArrayList<Integer> allowedTerracottaMetadata = new ArrayList();

	public BadlandsPlateauBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		if (BTAMod.isDecoInstalled()) {
			this.topBlockExt = DecoBlocks.terracotta.blockID;
			this.fillerBlockExt = DecoBlocks.terracotta.blockID;
		}
		this.btaBiomeDecorator.sandPerChunk = 0;
		this.btaBiomeDecorator.sandPerChunk2 = 0;
		this.spawnableCreatureList.clear();
		
		this.btaBiomeDecorator.treesPerChunk = 7;
		this.btaBiomeDecorator.flowersPerChunk = -999;
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
						if (world.getBlockId(i, j, k) == DecoBlocks.terracotta.blockID) {
							if (allowedTerracottaMetadata.contains(metaForY)) {
								world.setBlockAndMetadata(i, j, k, DecoBlocks.stainedTerracotta.blockID, metaForY);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean shouldConnectWithEdge(WorldConfigurationInfo generatorOptions) {
		return true;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(TreeGrowers.OAK_TREE, 1);
	}

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random rand)
    {
    	return this.worldGeneratorTrees;
    }

	@Override
	public boolean canLightningStrikeInBiome()
	{
		return true;
	}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
    	return BetterBiomesConfiguration.badlands.getBiomeGrassColor();
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
    	return BetterBiomesConfiguration.badlands.getBiomeFoliageColor();
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