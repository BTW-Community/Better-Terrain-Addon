package net.minecraft.src;

public class BTWGMod extends DawnAddon {
	private static BTWGMod instance;
	
	public static final WorldType BTWGWorldType = new BTWGWorldType();
	public static final WorldType BTWGWorldTypeDeco = new BTWGWorldTypeDeco();
	
	private BTWGMod() {
		super("Better Than World Gen", "1.0", "BTWG");
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		initDecoIntegration();
		initWorldGen();
		initOverrides();
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public void initDecoIntegration() {
		BTWGDecoIntegration.init();
	}
	
	public void initWorldGen() {
		BTWGBiomeConfiguration.init();
		if (!BTWGDecoIntegration.isDecoInstalled()) {
			BTWGWorldTypeDeco.setCanBeCreated(false);
		}
	}
	
	public void initOverrides() {
		DawnUtilsReflection.replaceEntityMappingWithAllowanceForOldClass(FCEntitySlime.class, BTWGEntitySlime.class, "Slime");
	}
	
	public static BTWGMod getInstance() {
		if (instance == null) {
			instance = new BTWGMod();
		}
		
		return instance;
	}
}