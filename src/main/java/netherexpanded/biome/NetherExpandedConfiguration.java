package netherexpanded.biome;

import netherexpanded.world.generate.surface.AshFieldsSurfaceBuilder;
import netherexpanded.world.generate.surface.BasaltDeltasSurfaceBuilder;
import netherexpanded.world.generate.surface.CrystalCavernsSurfaceBuilder;
import netherexpanded.world.generate.surface.SoulSandValleySurfaceBuilder;
import betterterrain.biome.BTABiome;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.world.generate.surface.NetherSurfaceBuilder;
import netherexpanded.biome.biomes.*;

import java.util.ArrayList;

public class NetherExpandedConfiguration extends BiomeConfiguration {
    public static final int
            NETHER_WASTES_ID = 90,
            GLOWSTONE_CANYON_ID = 91,
            BASALT_DELTAS_ID = 92,
            SOUL_SAND_VALLEY_ID = 93,
            SOULBLIGHT_GROVE_ID = 94,
            CRYSTAL_CAVERNS_ID = 95,
            PETRIFIED_FOREST_ID = 96,

            MAX_ID = 100;

    public static final BTABiome ashFields = new AshFieldsBiome(GLOWSTONE_CANYON_ID, "netherexpanded:ash_fields")
            .setBiomeName("Ash Fields")
            .setSurfaceBuilder(new AshFieldsSurfaceBuilder())
            .setNether();

    public static final BTABiome basaltDeltas = new BasaltDeltasBiome(BASALT_DELTAS_ID, "netherexpanded:basalt_deltas")
            .setBiomeName("Basalt Deltas")
            .setSurfaceBuilder(new BasaltDeltasSurfaceBuilder())
            .setNether();

    public static final BTABiome soulSandValley = new SoulSandValleyBiome(SOUL_SAND_VALLEY_ID, "netherexpanded:soul_sand_valley")
            .setBiomeName("Soul Sand Valley")
            .setSurfaceBuilder(new SoulSandValleySurfaceBuilder())
            .setNether();

    public static final BTABiome soulblightGrove = null;

    public static final BTABiome crystalCaverns = new CrystalCavernsBiome(CRYSTAL_CAVERNS_ID, "netherexpanded:crystal_caverns")
            .setBiomeName("Crystal Caverns")
            .setSurfaceBuilder(new CrystalCavernsSurfaceBuilder())
            .setNether();

    public static final BTABiome petrifiedForest = new PetrifiedForestBiome(PETRIFIED_FOREST_ID, "netherexpanded:petrified_forest")
            .setBiomeName("Petrified Forest")
            .setSurfaceBuilder(new NetherSurfaceBuilder())
            .setNether();

    @Override
    public void addBiomesToList(ArrayList<BTABiome> biomeList) {

    }

    @Override
    public void setBiomeVariants() {

    }
}
