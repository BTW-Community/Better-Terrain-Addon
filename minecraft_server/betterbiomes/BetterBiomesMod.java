package betterbiomes;

import java.util.Set;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterbiomes.entity.BTAEntityCrystalGolem;
import betterterrain.AddonVersion;
import betterterrain.BTAAddon;
import betterterrain.BTAMod;
import betterterrain.BTAVersion;
import betterterrain.DecoIntegration;
import betterterrain.biome.BiomeConfiguration;
import betterterrain.block.BTABlockClay;
import betterterrain.item.BTAItemBloodMossSpores;
import betterterrain.item.BTAItemPileSoulSand;
import net.minecraft.src.Block;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;
import net.minecraft.src.ItemMultiTextureTile;
import net.minecraft.src.MapColor;
import net.minecraft.src.Material;

public class BetterBiomesMod extends BTAAddon {
	private static BetterBiomesMod instance;
	
	public static final AddonVersion V3_0_0 = new AddonVersion(3, 0, 0, getInstance());
	
	private BetterBiomesMod() {
		super("Better Biomes", "betterbiomes", "3.0.0", "BB");
	}
	
	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public static BetterBiomesMod getInstance() {
		if (instance == null) {
			instance = new BetterBiomesMod();
		}
		
		return instance;
	}

	@Override
	public BiomeConfiguration getBiomeConfiguration() {
		return BetterBiomesConfiguration.getInstance();
	}
}