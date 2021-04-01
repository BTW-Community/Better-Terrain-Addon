package net.minecraft.src;

public class BTAMod extends AddonExt {
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
	
	private BTAMod() {
		super("Better Terrain", "1.3.1", "BTA");
		this.currentVersion = BTAEnumVersionCompat.fromString(this.getVersionString());
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		initDecoIntegration();
		initWorldGen();
		initOverrides();
		initMisc();
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public void initDecoIntegration() {
		BTADecoIntegration.init();
	}
	
	public void initWorldGen() {
		BTABiomeConfiguration.init();
	}
	
	public void initOverrides() {
		//EntityList.replaceExistingMappingSafe(BTAEntitySlime.class, "Slime");
		Block.blockClay = Block.replaceBlock(Block.blockClay.blockID, BTABlockClay.class);
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

	public String GetLanguageFilePrefix()
	{
		return "BTA";
	}
}