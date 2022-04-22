package betterterrain;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.block.BTABlockClay;
import betterterrain.entity.BTAEntityCrystalGolem;
import betterterrain.entity.BTARenderCrystalGolem;
import betterterrain.item.BTAItemBloodMossSpores;
import betterterrain.item.BTAItemPileSoulSand;
import betterterrain.world.type.BTADefaultWorldType;
import betterterrain.world.type.BetaWorldType;
import betterterrain.world.type.HorizonsWorldType;
import betterterrain.world.type.SkyWorldType;
import net.minecraft.src.Block;
import net.minecraft.src.EntityList;
import net.minecraft.src.FCAddOn;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;
import net.minecraft.src.ItemMultiTextureTile;
import net.minecraft.src.MapColor;
import net.minecraft.src.Material;
import net.minecraft.src.RenderManager;
import net.minecraft.src.ServerCommandManager;
import net.minecraft.src.WorldType;

public class BTAMod extends FCAddOn {
	private static BTAMod instance;
	
	public final Version currentVersion;
	
	public static final WorldType BTAWorldType = new BTADefaultWorldType(4, "BTA");
	public static final WorldType BTAWorldTypeDeco = new BTADefaultWorldType(5, "BTADeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldType);
	public static final WorldType BTAWorldTypeBeta = new BetaWorldType(6, "BTABeta");
	public static final WorldType BTAWorldTypeBetaDeco = new BetaWorldType(7, "BTABetaDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldTypeBeta);
	public static final WorldType BTAWorldTypeSky = new SkyWorldType(8, "BTASky");
	public static final WorldType BTAWorldTypeSkyDeco = new SkyWorldType(9, "BTASkyDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldTypeSky);
	public static final WorldType BTAWorldTypeSmall = new BTADefaultWorldType(10, "BTASmall").setCanBeCreated(false).setParent(BTAWorldType);
	public static final WorldType BTAWorldTypeSmallDeco = new BTADefaultWorldType(11, "BTASmallDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldType);

	public static final WorldType BTAWorldTypeHorizons = new HorizonsWorldType(15, "BTAHorizons").setCanBeCreated(false);
	
	public static Material netherSand;
	
	private BTAMod() {
		super("Better Terrain", "3.0.0", "BTA");
		this.currentVersion = Version.fromString(this.getVersionString());
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		initDecoIntegration();
		initWorldGen();
		initDefs();
		initEntityRenderers();
		initMisc();
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public void initDecoIntegration() {
		DecoIntegration.init();
	}
	
	public void initWorldGen() {
		BiomeConfiguration.init();
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
	
	public void initMisc() {
		ServerCommandManager.registerAddonCommand(new BiomeCommand());
	}
	
	public static BTAMod getInstance() {
		if (instance == null) {
			instance = new BTAMod();
		}
		
		return instance;
	}
	
	// ------ Client only ------ //
	public void initEntityRenderers() {
		RenderManager.AddEntityRenderer(BTAEntityCrystalGolem.class, new BTARenderCrystalGolem());
	}
}