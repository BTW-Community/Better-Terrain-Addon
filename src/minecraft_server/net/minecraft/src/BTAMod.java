package net.minecraft.src;

public class BTAMod extends AddonExt {
	private static BTAMod instance;
	
	public static final WorldType BTAWorldType = new BTAWorldType();
	public static final WorldType BTAWorldTypeDeco = new BTAWorldTypeDeco();
	
	private BTAMod() {
		super("Better Terrain", "1.0b", "BTA");
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
		if (!BTADecoIntegration.isDecoInstalled()) {
			BTAWorldTypeDeco.setCanBeCreated(false);
		}
	}
	
	public void initOverrides() {
		EntityList.replaceExistingMappingSafe(BTAEntitySlime.class, "Slime");
		Block.blockClay = Block.replaceBlock(Block.blockClay.blockID, BTABlockClay.class);
	}
	
	public void initMisc() {
		
	}
	
	public static BTAMod getInstance() {
		if (instance == null) {
			instance = new BTAMod();
		}
		
		return instance;
	}
}