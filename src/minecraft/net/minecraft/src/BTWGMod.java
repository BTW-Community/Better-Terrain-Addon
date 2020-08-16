package net.minecraft.src;

public class BTWGMod extends DawnAddon {
	private static BTWGMod instance;
	
	public static final WorldType BTWGWorldType = new BTWGWorldType();
	
	private BTWGMod() {
		super("Better Than World Gen", "1.0", "BTWG");
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing");
		initWorldGen();
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public void initWorldGen() {
		BTWGBiomeConfiguration.init();
	}
	
	public static BTWGMod getInstance() {
		if (instance == null) {
			instance = new BTWGMod();
		}
		
		return instance;
	}
}