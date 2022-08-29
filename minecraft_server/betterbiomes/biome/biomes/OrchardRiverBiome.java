package betterbiomes.biome.biomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.biome.biomes.RiverBiome;

public class OrchardRiverBiome extends RiverBiome {
	public OrchardRiverBiome(int id, String internalName) {
		super(id, internalName, BetterBiomesConfiguration.orchard.climate);
	}
}