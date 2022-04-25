package betterbiomes;

import net.minecraft.src.Block;
import net.minecraft.src.DecoManager;
import net.minecraft.src.FCAddOn;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.Item;

public class DecoIntegration {
	private static boolean isDecoInstalled = false;
	private static FCAddOn decoManager = null;

	public static Block redSand;
	public static Block redSandStone;
	public static Block redSandStoneStairs;
	public static Block terracotta;
	public static Block stainedTerracotta;
	public static Block cherryLog;
	public static Block cherryStump;
	public static Block cherryLeaves;
	public static Block acaciaLog;
	public static Block acaciaStump;
	public static Block acaciaLeaves;
	public static Block flower;
	public static Block flower2;
	public static Block tulip;
	public static Block stoneTypes;
	public static Block autumnLeaves;
	public static Block coarseDirt;
	public static Block podzol;
	public static Block basalt;
	public static Block infusedStone;
	public static Block magma;
	public static Block ash;
	public static Block pumice;
	public static Block amethyst;
	public static Block amethystShardBlock;
	public static Block bonePillar;
	
	public static Item pileRedSand;
	
	public static void init() {
		try {
			if (FCAddOnHandler.isModInstalled("Deco Addon")) {
				decoManager = FCAddOnHandler.getModByName("Deco Addon");
			}
			
			if (decoManager != null) {
				isDecoInstalled = true;
				
				redSand = (Block) getDecoField("redSand");
				redSandStone = (Block) getDecoField("redSandStone");
				redSandStoneStairs = (Block) getDecoField("redSandStoneStairs");
				terracotta = (Block) getDecoField("terracotta");
				stainedTerracotta = (Block) getDecoField("stainedTerracotta");
				cherryLog = (Block) getDecoField("cherryLog");
				cherryStump = (Block) getDecoField("cherryStump");
				cherryLeaves = (Block) getDecoField("cherryLeaves");
				acaciaLog = (Block) getDecoField("acaciaLog");
				acaciaStump = (Block) getDecoField("acaciaStump");
				acaciaLeaves = (Block) getDecoField("acaciaLeaves");
				flower = (Block) getDecoField("flower");
				flower2 = (Block) getDecoField("flower2");
				tulip = (Block) getDecoField("tulip");
				stoneTypes = (Block) getDecoField("stoneTypes");
				autumnLeaves = (Block) getDecoField("autumnLeaves");
				coarseDirt = (Block) getDecoField("coarseDirt");
				podzol = (Block) getDecoField("podzol");
				basalt = (Block) getDecoField("basalt");
				infusedStone = (Block) getDecoField("infusedStone");
				magma = (Block) getDecoField("magma");
				ash = (Block) getDecoField("ashBlock");
				pumice = (Block) getDecoField("pumice");
				amethyst = (Block) getDecoField("amethyst");
				amethystShardBlock = (Block) getDecoField("amethystShardBlock");
				bonePillar = (Block) getDecoField("bonePillar");
				
				pileRedSand = (Item) getDecoField("pileRedSand");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private static Object getDecoField(String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if (isDecoInstalled) {
			return ((DecoManager) decoManager).decoDefs.getClass().getDeclaredField(fieldName).get(null);
		}
		
		return null;
	}
	
	public static boolean isDecoInstalled() {
		return isDecoInstalled;
	}
}