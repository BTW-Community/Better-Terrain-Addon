package betterterrain;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.world.type.BTADefaultWorldType;
import betterterrain.world.type.BetaWorldType;
import betterterrain.world.type.SkyWorldType;
import btw.AddonHandler;
import btw.BTWAddon;
import btw.block.BTWBlocks;
import net.minecraft.src.*;

public class BTAMod extends BTWAddon {
	private static BTAMod instance;
	
	public final BTAVersion currentVersion;
	
	public static final WorldType BTAWorldType = new BTADefaultWorldType(4, "BTA");
	public static final WorldType BTAWorldTypeDeco = (new BTADefaultWorldType(5, "BTADeco").setCanBeCreated(false)).setIsDeco().setParent(BTAWorldType);
	public static final WorldType BTAWorldTypeBeta = new BetaWorldType(6, "BTABeta");
	public static final WorldType BTAWorldTypeBetaDeco = new BetaWorldType(7, "BTABetaDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldTypeBeta);
	public static final WorldType BTAWorldTypeSky = new SkyWorldType(8, "BTASky");
	public static final WorldType BTAWorldTypeSkyDeco = new SkyWorldType(9, "BTASkyDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldTypeSky);
	public static final WorldType BTAWorldTypeSmall = new BTADefaultWorldType(10, "BTASmall").setCanBeCreated(false).setParent(BTAWorldType);
	public static final WorldType BTAWorldTypeSmallDeco = new BTADefaultWorldType(11, "BTASmallDeco").setIsDeco().setCanBeCreated(false).setParent(BTAWorldType);
	
	public static Material netherSand;
	
	private BTAMod() {
		super("Better Terrain", "3.1.0", "BTA");
		this.currentVersion = BTAVersion.fromString(this.getVersionString());
	}

	@Override
	public void initialize() {
		this.registerAddonCommand(new BiomeCommand());
		
		netherSand = new Material(MapColor.sandColor).setRequiresTool().SetNetherMobsCanSpawnOn();

		Item.itemsList[Block.blockClay.blockID] = null;
		Item.itemsList[Block.blockClay.blockID] = new ItemMultiTextureTile(Block.blockClay.blockID - 256, Block.blockClay, new String[] {"dirt", "sand", "redSand", "grass"});

		Block.blockNetherQuartz.setBlockMaterial(BTWBlocks.netherRockMaterial);
		Block.slowSand.setBlockMaterial(netherSand);
		
		for (BTWAddon mod : AddonHandler.modList.values()) {
			if (mod instanceof BTAAddon) {
				BTAAddon addon = (BTAAddon) mod;
				addon.currentVersion = AddonVersion.fromString(addon.getVersionString(), addon);
			}
		}
	}
	
	@Override
	public void postInitialize() {
		BiomeConfiguration.init();
	}

	public static boolean isDecoInstalled() {
		return AddonHandler.isModInstalled("Deco Addon");
	}
	
	public static BTAMod getInstance() {
		if (instance == null) {
			instance = new BTAMod();
		}
		
		return instance;
	}
}