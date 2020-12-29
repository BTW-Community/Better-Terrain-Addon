package net.minecraft.src;

public class BTAMod extends DawnAddon {
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
		DawnUtilsReflection.replaceEntityMappingWithAllowanceForOldClass(FCEntitySlime.class, BTAEntitySlime.class, "Slime");
		
		DawnUtilsReflection.registerBlockObfuscationMappping("blockClay", "ba");
		int id = Block.blockClay.blockID;
		Block.blocksList[id] = null;
		Block clay = new BTABlockClay(id).setHardness(0.6F).setUnlocalizedName("clay");;
		Item.itemsList[id] = new ItemBlock(id - 256);
	}
	
	public void initMisc() {
		DawnServerCommandManager.registerAddonCommand(new BTACommandBiome());
		
		if (!DawnUtilsReflection.isObfuscated()) {
			DawnServerCommandManager.registerAddonCommand(new BTACommandVillage());
		}
	}
	
	public static BTAMod getInstance() {
		if (instance == null) {
			instance = new BTAMod();
		}
		
		return instance;
	}
}