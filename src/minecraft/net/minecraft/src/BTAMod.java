package net.minecraft.src;

public class BTAMod extends AddonExt {
	private static BTAMod instance;
	
	public static final WorldType BTWGWorldType = new BTAWorldType();
	public static final WorldType BTWGWorldTypeDeco = new BTAWorldTypeDeco();
	
	private BTAMod() {
		super("Better Terrain Addon", "1.0", "BTA");
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
			BTWGWorldTypeDeco.setCanBeCreated(false);
		}
	}
	
	public void initOverrides() {
		EntityList.replaceExistingMappingSafe(BTAEntitySlime.class, "Slime");
		Block.blockClay = Block.replaceBlock(Block.blockClay.blockID, BTABlockClay.class);
		Item.itemsList[Block.blockClay.blockID] = new ItemBlock(Block.blockClay.blockID - 256);
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
}