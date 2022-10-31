package betterterrain;

import betterterrain.biome.BiomeConfiguration;
import betterterrain.mixins.WorldTypeAccessor;
import betterterrain.world.type.BTADefaultWorldType;
import betterterrain.world.type.BetaWorldType;
import betterterrain.world.type.SkyWorldType;
import betterterrain.world.util.WorldTypeInterface;
import btw.AddonHandler;
import btw.BTWAddon;
import btw.block.BTWBlocks;
import net.minecraft.src.*;

public class BTAMod extends BTWAddon {
	private static BTAMod instance;
	
	public final BTAVersion currentVersion;
	
	public static final WorldTypeInterface BTAWorldType = (WorldTypeInterface) new BTADefaultWorldType(4, "BTA");
	public static final WorldTypeInterface BTAWorldTypeDeco = ((WorldTypeInterface) ((WorldTypeAccessor) new BTADefaultWorldType(5, "BTADeco")).setCanBeCreatedInvoker(false)).setIsDeco().setParent(BTAWorldType);
	public static final WorldTypeInterface BTAWorldTypeBeta = (WorldTypeInterface) new BetaWorldType(6, "BTABeta");
	public static final WorldTypeInterface BTAWorldTypeBetaDeco = ((WorldTypeInterface) ((WorldTypeAccessor) new BetaWorldType(7, "BTABetaDeco")).setCanBeCreatedInvoker(false)).setIsDeco().setParent(BTAWorldTypeBeta);
	public static final WorldTypeInterface BTAWorldTypeSky = (WorldTypeInterface) new SkyWorldType(8, "BTASky");
	public static final WorldTypeInterface BTAWorldTypeSkyDeco = ((WorldTypeInterface) ((WorldTypeAccessor) new SkyWorldType(9, "BTASkyDeco")).setCanBeCreatedInvoker(false)).setIsDeco().setParent(BTAWorldTypeSky);
	public static final WorldTypeInterface BTAWorldTypeSmall = ((WorldTypeInterface) ((WorldTypeAccessor) new BTADefaultWorldType(10, "BTASmall")).setCanBeCreatedInvoker(false)).setParent(BTAWorldType);
	public static final WorldTypeInterface BTAWorldTypeSmallDeco = ((WorldTypeInterface) ((WorldTypeAccessor) new BTADefaultWorldType(11, "BTASmallDeco")).setCanBeCreatedInvoker(false)).setIsDeco().setParent(BTAWorldType);
	
	public static Material netherSand;
	
	private BTAMod() {
		super("Better Terrain", "3.1.0", "BTA");
		this.currentVersion = BTAVersion.fromString(this.getVersionString());
	}

	@Override
	public void initialize() {
		this.registerAddonCommand(new BiomeCommand());
		
		netherSand = new Material(MapColor.sandColor).setRequiresTool().setNetherMobsCanSpawnOn();

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