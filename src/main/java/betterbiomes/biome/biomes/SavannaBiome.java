package betterbiomes.biome.biomes;

import java.util.Random;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterbiomes.feature.tree.AcaciaGen;
import betterterrain.BTAVersion;
import betterterrain.biome.BTABiome;
import betterterrain.biome.Climate;
import betterterrain.biome.layer.HillsLayer;
import betterterrain.mixins.GenLayerAccessor;
import betterterrain.world.config.WorldConfigurationInfo;
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