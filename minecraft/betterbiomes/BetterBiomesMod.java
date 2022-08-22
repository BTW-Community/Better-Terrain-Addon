package betterbiomes;

import java.util.Set;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterbiomes.entity.BTAEntityCrystalGolem;
import betterbiomes.entity.BTARenderCrystalGolem;
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
import net.minecraft.src.RenderManager;

public class BetterBiomesMod extends BTAAddon {
	private static BetterBiomesMod instance;
	
	private BetterBiomesMod() {
		super("Better Biomes", "3.0.0", "BB");
	}
	
	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		initDecoIntegration();
		initWorldGen();
		initDefs();
		initEntityRenderers();
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}

	@Override
	public void setValidVersions(Set<AddonVersion> versions) {
		versions.add(BBVersions.V3_0_0);
	}
	
	public void initDecoIntegration() {
		DecoIntegration.init();
	}
	
	public void initWorldGen() {
		BetterBiomesConfiguration.init();
	}
	
	public void initDefs() {
		//EntityList.addMapping(BTAEntityCrystalGolem.class, "btaCrystalGolem", 350, 0, 0);
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
	
	// ------ Client only ------ //
	public void initEntityRenderers() {
		RenderManager.AddEntityRenderer(BTAEntityCrystalGolem.class, new BTARenderCrystalGolem());
	}
}