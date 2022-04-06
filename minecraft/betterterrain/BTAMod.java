package betterterrain;

import betterterrain.biome.BTABiomeConfiguration;
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
	
	public final BTAEnumVersionCompat currentVersion;
	
	public static final WorldType BTAWorldType = new BTAWorldType(4, "BTA");
	public static final WorldType BTAWorldTypeDeco = new BTAWorldType(5, "BTADeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldType);
	public static final WorldType BTAWorldTypeBeta = new BTAWorldTypeBeta(6, "BTABeta");
	public static final WorldType BTAWorldTypeBetaDeco = new BTAWorldTypeBeta(7, "BTABetaDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldTypeBeta);
	public static final WorldType BTAWorldTypeSky = new BTAWorldTypeSky(8, "BTASky");
	public static final WorldType BTAWorldTypeSkyDeco = new BTAWorldTypeSky(9, "BTASkyDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldTypeSky);
	public static final WorldType BTAWorldTypeSmall = new BTAWorldType(10, "BTASmall").setCanBeCreated(false).setParent(BTAWorldType);
	public static final WorldType BTAWorldTypeSmallDeco = new BTAWorldType(11, "BTASmallDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldType);

	public static final WorldType BTAWorldTypeHorizons = new BTAWorldTypeHorizons(15, "BTAHorizons").setCanBeCreated(false);
	
	public static Material netherSand;
	
	private BTAMod() {
		super("Better Terrain", "2.0.8", "BTA");
		this.currentVersion = BTAEnumVersionCompat.fromString(this.getVersionString());
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
		BTADecoIntegration.init();
	}
	
	public void initWorldGen() {
		BTABiomeConfiguration.init();
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
		ServerCommandManager.registerAddonCommand(new BTACommandBiome());
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