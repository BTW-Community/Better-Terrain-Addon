package net.minecraft.src;

public class BTAMod extends AddonExt {
	private static BTAMod instance;
	
	public static final WorldType BTAWorldType = new BTAWorldType(4, "BTA");
	public static final WorldType BTAWorldTypeDeco = new BTAWorldType(5, "BTADeco").setIsDeco().setCanBeCreated(false);
	public static final WorldType BTAWorldTypeBeta = new BTAWorldTypeBeta(6, "BTABeta");
	public static final WorldType BTAWorldTypeBetaDeco = new BTAWorldTypeBeta(7, "BTABetaDeco").setIsDeco().setCanBeCreated(false);
	public static final WorldType BTAWorldTypeSky = new BTAWorldTypeSky(8, "BTASky");
	public static final WorldType BTAWorldTypeSkyDeco = new BTAWorldTypeSky(9, "BTASkyDeco").setIsDeco().setCanBeCreated(false);
	public static final WorldType BTAWorldTypeHorizons = new BTAWorldTypeHorizons(10, "BTAHorizons").setCanBeCreated(false);
	
	private BTAMod() {
		super("Better Terrain", "1.1.2", "BTA");
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