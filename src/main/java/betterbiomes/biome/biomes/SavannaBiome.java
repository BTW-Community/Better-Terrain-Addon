package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterbiomes.world.feature.tree.legacy.AcaciaGen;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.biome.layer.HillsLayer;
import betterterrain.mixins.GenLayerAccessor;
import betterterrain.world.config.WorldConfigurationInfo;
import betterterrain.world.feature.tree.grower.BTATreeGrowers;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class SavannaBiome extends BTABiome {
	long lastRandSeed = 0;
	Random rand = new Random();

	public SavannaBiome(int id, String internalName, Climate climate) {
		super(id, internalName, climate);
		this.btaBiomeDecorator.treesPerChunk = 2;
		this.btaBiomeDecorator.grassPerChunk = 25;
	}

	public void initTreeGrowerMap() {
		this.treeGrowers.put(BTATreeGrowers.OAK_ACACIA_TREE, 1);
		this.treeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);

		this.decoTreeGrowers.put(BTATreeGrowers.ACACIA_TREE, 1);
		this.decoTreeGrowers.put(BTATreeGrowers.OAK_BUSH, 2);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return rand.nextInt(3) == 0 ? new AcaciaGen(false) : new WorldGenShrub(0,0);
	}

	@Override
	public int getSubVariant(WorldConfigurationInfo generatorOptions, HillsLayer layer) {
		if (this.biomeID == BetterBiomesConfiguration.savanna.biomeID) {
			if (((GenLayerAccessor) layer).getChunkSeed() != lastRandSeed) {
				rand.setSeed(((GenLayerAccessor) layer).getChunkSeed());
				lastRandSeed = ((GenLayerAccessor) layer).getChunkSeed();
			}

			if (generatorOptions.getBTAVersion().isVersionAtLeast(BTAVersion.V2_0_3)) {
				if (rand.nextInt(3) == 0) {
					return BetterBiomesConfiguration.savannaPlateau.biomeID;
				}
				else {
					return this.biomeID;
				}
			}
			else {
				return BetterBiomesConfiguration.savannaHills.biomeID;
			}
		}
		else {
			return this.biomeID;
		}
	}
}