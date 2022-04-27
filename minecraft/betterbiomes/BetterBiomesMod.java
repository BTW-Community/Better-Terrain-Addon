package betterbiomes;

import betterbiomes.biome.BetterBiomesConfiguration;
import betterterrain.BTAAddon;
import betterterrain.BTAMod;
import betterterrain.BTAVersion;
import betterterrain.block.BTABlockClay;
import betterterrain.entity.BTAEntityCrystalGolem;
import betterterrain.entity.BTARenderCrystalGolem;
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
	
	public static Material netherSand;
	
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
	
	public void initDecoIntegration() {
		DecoIntegration.init();
	}
	
	public void initWorldGen() {
		BetterBiomesConfiguration.init();
	}
	
	public void initDefs() {
		netherSand = new Material(MapColor.sandColor).setRequiresTool().SetNetherMobsCanSpawnOn();
	    
		FCBetterThanWolves.fcItemPileSoulSand = Item.replaceItem(FCBetterThanWolves.fcItemPileSoulSand.itemID, BTAItemPileSoulSand.class, instance);
		
		Block.blockClay = Block.replaceBlock(Block.blockClay.blockID, BTABlockClay.class, instance);
		Item.itemsList[Block.blockClay.blockID] = new ItemMultiTextureTile(Block.blockClay.blockID - 256, Block.blockClay, new String[] {"dirt", "sand", "redSand", "grass"});
		
		FCBetterThanWolves.fcItemBloodMossSpores = Item.replaceItem(FCBetterThanWolves.fcItemBloodMossSpores.itemID, BTAItemBloodMossSpores.class, instance);
		Block.blockNetherQuartz.SetBlockMaterial(FCBetterThanWolves.fcMaterialNetherRock);
		Block.slowSand.SetBlockMaterial(netherSand);
		
		//EntityList.addMapping(BTAEntityCrystalGolem.class, "btaCrystalGolem", 350, 0, 0);
	}
	
	public static BetterBiomesMod getInstance() {
		if (instance == null) {
			instance = new BetterBiomesMod();
		}
		
		return instance;
	}
	
	// ------ Client only ------ //
	public void initEntityRenderers() {
		RenderManager.AddEntityRenderer(BTAEntityCrystalGolem.class, new BTARenderCrystalGolem());
	}
}