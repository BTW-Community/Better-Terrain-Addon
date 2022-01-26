package net.minecraft.src;

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
		super("Better Terrain", "2.0.6", "BTA");
		this.currentVersion = BTAEnumVersionCompat.fromString(this.getVersionString());
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		initDecoIntegration();
		initWorldGen();
		initDefs();
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
	    
		Block.blockClay = Block.replaceBlock(Block.blockClay.blockID, BTABlockClay.class, instance);
		FCBetterThanWolves.fcItemBloodMossSpores = Item.replaceItem(FCBetterThanWolves.fcItemBloodMossSpores.itemID, BTAItemBloodMossSpores.class, instance);
		Block.blockNetherQuartz.SetBlockMaterial(FCBetterThanWolves.fcMaterialNetherRock);
		Block.slowSand.SetBlockMaterial(netherSand);
		
		EntityList.addMapping(BTAEntityCrystalGolem.class, "btaCrystalGolem", 350, 0, 0);
	}
	
	public void initMisc() {
		//ServerCommandManager.registerAddonCommand(new BTACommandBiome());
	}
	
	public static BTAMod getInstance() {
		if (instance == null) {
			instance = new BTAMod();
		}
		
		return instance;
	}
}